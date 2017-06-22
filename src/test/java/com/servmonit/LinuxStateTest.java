package com.servmonit;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.RootConfig;
import com.jcraft.jsch.JSchException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class LinuxStateTest {

	@Test
	public void stateTest() throws IOException, JSchException{
		String cpuShell = "top -b -n 1 |grep Cpu";    
	    String diskShell = "df -hl";  
	    String[] commands = {cpuShell,diskShell};  
		String result = LinuxState.runDistanceShell(commands,"root", "ITScai@207", "10.1.30.207"); 
		System.out.println("读取的信息："+result);
		System.out.println("转换后的信息："+LinuxState.disposeResultMessage(result));
	}
}
