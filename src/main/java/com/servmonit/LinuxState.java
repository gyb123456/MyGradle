package com.servmonit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jcraft.jsch.*;

public class LinuxState {

    private static Session session;  
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");  
    
    /** 
     * 连接到指定的HOST 
     * 
     * @return isConnect 
     * @throws JSchException JSchException 
     */  
    public static boolean connect(String user, String passwd, String host) {  
        JSch jsch = new JSch();  
        try {  
            session = jsch.getSession(user, host, 22);  
            session.setPassword(passwd);  
  
            Properties config = new Properties();  
            config.put("StrictHostKeyChecking", "no");  
            session.setConfig(config);  
            session.connect();  
        } catch (JSchException e) {  
            e.printStackTrace();  
            System.out.println("connect error !");  
            return false;  
        }  
        return true;  
    } 
    
    /** 
     * 远程连接Linux 服务器 执行相关的命令 
     * 
     * @param user     远程连接的用户名 
     * @param passwd   远程连接的密码 
     * @param host     远程连接的主机IP 
     * @return 最终命令返回信息 
     * @throws IOException 
     * @throws JSchException 
     */  
    public static String runDistanceShell(String user, String passwd, String host) throws IOException, JSchException {  
        if (!connect(user, passwd, host)) {  
            return null;  
        } 
        try{
        	ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        	channelExec.setCommand("df -hl");//磁盘信息命令df -hl,内存信息top -b -n 1 |grep Mem
        	channelExec.setInputStream(null);
        	channelExec.setErrStream(System.err);
        	channelExec.connect();
        	InputStream in = channelExec.getInputStream();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        	String buf = null;
        	StringBuffer sb = new StringBuffer();
        	while ((buf = reader.readLine()) != null) {
        		sb.append(buf.trim()).append(LINE_SEPARATOR);
        	}
        	reader.close();
        	channelExec.disconnect();
        	session.disconnect();
            return sb.toString();
        }catch(Exception e){
        	e.printStackTrace();
        	return null;  
        }     
    } 
    
    /** 
     * 处理 shell 返回的信息（具体得看Linux返回的数据格式）
     * @param result shell返回的信息 
     * @return 最终处理后的信息 
     */  
    public static List<Map> disposeResultMessage(String result) {
    	String[] strings = result.split(LINE_SEPARATOR);    	
    	List<Map> disposeResult = new ArrayList<Map>();
    	String [] tittle = strings[0].split("\\s+");
        for (int i = 1; i < strings.length; i++) {         	
        	String [] arr = strings[i].split("\\s+");  
        	Map<String, String> disposeResultMap = new HashMap<String, String>();
        	for(int j=0;j<6;j++){
        		disposeResultMap.put(tittle[j], arr[j]);  
        	} 
        	disposeResult.add(disposeResultMap);
        }
        return disposeResult;
    }   
}
