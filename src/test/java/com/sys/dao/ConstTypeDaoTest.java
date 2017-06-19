package com.sys.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class ConstTypeDaoTest {

	@Autowired
	private ConstTypeDao constTypeDao;
	
	@Test
	@Transactional
	public void getAll(){
		System.out.println(constTypeDao.getAll());
	}
}
