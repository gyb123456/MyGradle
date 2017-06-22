package com.base.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.RootConfig;
import com.base.dao.impl.StationDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class StationDaoImplTest {
	
	@Autowired
	private StationDaoImpl rsi;
	
	@Test
	@Transactional
	public void findByRtNumAndDirec(){
		System.out.println(rsi.findByRtNumAndDirec(10086,0));
	}
}
