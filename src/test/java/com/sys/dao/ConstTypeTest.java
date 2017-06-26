package com.sys.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.RootConfig;
import com.sys.ctrl.ConstDictCtrl;
import com.sys.ctrl.ConstTypeCtrl;
import com.sys.po.ConstDict;
import com.sys.po.ConstType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)

//@Transactional
public class ConstTypeTest {

	@Autowired
	private ConstTypeCtrl ConstTypeBizImpl; 
	@Autowired
	private ConstDictCtrl ConstDictCtrl; 
	
	@Test
	public  void findAll() {
//		List<ConstType> list = ConstTypeBizImpl.findAll();
		ConstDict po = new ConstDict();
		po.setVal("lll");
		po.setName("啦啦啦");
		po.setTypeId(1);
		ConstDictCtrl.save(po, null);
		System.out.println("=============");
	}
}
