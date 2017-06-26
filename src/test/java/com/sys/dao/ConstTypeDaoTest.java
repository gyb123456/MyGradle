package com.sys.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.RootConfig;
import com.sys.ctrl.ConstTypeCtrl;
import com.sys.po.ConstType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)

//@Transactional
public class ConstTypeDaoTest {

	@Autowired
	private ConstTypeCtrl ConstTypeBizImpl; 
	
	
	@Test
	public  void findAll() {
		List<ConstType> list = ConstTypeBizImpl.findAll();
		System.out.println("============="+list);
	}
}
