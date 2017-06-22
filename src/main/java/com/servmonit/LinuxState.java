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
    public static String runDistanceShell(String[] commands,String user, String passwd, String host) throws IOException, JSchException {  
        if (!connect(user, passwd, host)) {  
            return null;  
        } 
        try{
        	ChannelExec channelExec = null;
        	BufferedReader reader = null;
        	StringBuffer stringBuffer;  
        	StringBuffer bufferResult = new StringBuffer();
        	for (String command : commands) {
        		channelExec = (ChannelExec) session.openChannel("exec");
        		channelExec.setCommand(command);//磁盘信息命令df -hl,内存信息top -b -n 1 |grep Mem,cpu信息top -b -n 1 |grep Cpu
            	channelExec.setInputStream(null);
            	channelExec.setErrStream(System.err);
            	channelExec.connect();
            	InputStream in = channelExec.getInputStream();
            	reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            	String buf = null;
            	stringBuffer = new StringBuffer();
            	while ((buf = reader.readLine()) != null) {
            		stringBuffer.append(buf.trim()).append(LINE_SEPARATOR);
            		bufferResult.append(buf.trim()).append(LINE_SEPARATOR);
            	}
        	}        	
        	reader.close();
        	channelExec.disconnect();
        	session.disconnect();
            return bufferResult.toString();
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
    public static Map<String, Object> disposeResultMessage(String result) {
    	String[] strings = result.split(LINE_SEPARATOR);    	
    	List<Map> disposeResult = new ArrayList<Map>();
    	Map<String, String> cpuResultMap = new HashMap<String, String>();
    	if (strings[0].startsWith("Cpu(s):")) {      		
    		cpuResultMap.put("CpuUsed",strings[0].split("\\s+")[1].replace("us,", ""));
    		disposeResult.add(cpuResultMap);
    	}
    	int diskSize = 0;
    	int diskUsed = 0;
        for (int i = 2; i < strings.length; i++) {         	
        	String [] arr = strings[i].split("\\s+"); 
        	diskSize+=disposeUnit(arr[1]);
        	diskUsed+=disposeUnit(arr[2]);      	
        }
        Map<String, String> diskResultMap = new HashMap<String, String>();
        diskResultMap.put("diskSize", diskSize+"M");
        diskResultMap.put("diskUsed", diskUsed+"M");
        diskResultMap.put("diskAvail", (diskSize-diskUsed)+"M");
        disposeResult.add(diskResultMap);
        Map<String, Object> disposeResultMap = new HashMap<String, Object>();
        disposeResultMap.put("cpu", cpuResultMap);
        disposeResultMap.put("disk", diskResultMap);
        return disposeResultMap;
    }
    
    /** 
     * 处理单位转换 
     * K/KB/M/T 最终转换为M 处理 
     * @param s 带单位的数据字符串 
     * @return 以M 为单位处理后的数值 
     */
    private static int disposeUnit(String s) {      	  
        try {  
            s = s.toUpperCase();  
            String lastIndex = s.substring(s.length() - 1);  
            String num = s.substring(0, s.length() - 1); 
            if(num.equals("")){
            	return 0;  
            }
            double parseInt = Double.parseDouble(num);              
            if (lastIndex.equals("G")) {  
                return (int)parseInt * 1024;  
            } else if (lastIndex.equals("T")) {  
                return (int)parseInt * 1024 * 1024;  
            } else if (lastIndex.equals("M")) {  
                return (int)parseInt;  
            } else if (lastIndex.equals("K") || lastIndex.equals("KB")) {  
                return (int)parseInt / 1024;  
            }  
        } catch (NumberFormatException e) {  
            e.printStackTrace();  
            return 0;  
        }  
        return 0;  
    }  
}
