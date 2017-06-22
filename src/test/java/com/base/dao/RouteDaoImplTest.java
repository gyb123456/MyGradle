package com.base.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.RootConfig;
import com.base.dao.impl.RouteDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class RouteDaoImplTest {
	
	@Autowired
	private RouteDaoImpl rdi;
	
	@Test
	@Transactional
	public void findByName(){
		System.out.println(rdi.findByName("85路"));
	}
	
	@Test
	@Transactional
	public void findNameAndNumByNameLike(){
		System.out.println(rdi.findNameAndNumByNameLike("85路"));
	}
	
	@Test
	@Transactional
	public void findByNum(){
		System.out.println(rdi.findByNum(10086));
	}
}
