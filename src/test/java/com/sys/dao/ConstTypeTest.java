package com.sys.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.RootConfig;
import com.WebConfig;
import com.sys.biz.impl.ConstDictBizImpl;
import com.sys.ctrl.ConstDictCtrl;
import com.sys.ctrl.ConstTypeCtrl;
import com.sys.po.ConstDict;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RootConfig.class)

@WebAppConfiguration //(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
//@Transactional
public class ConstTypeTest {

	@Autowired
	private ConstTypeCtrl ConstTypeBizImpl; 
	@Autowired
	private ConstDictCtrl ConstDictCtrl;
	@Autowired
	private ConstDictBizImpl constDictBizImpl;
	@Test
	public  void findAll() {
//		List<ConstType> list = ConstTypeBizImpl.findAll();
		ConstDict po = new ConstDict();
		po.setVal("lll");
		po.setName("啦啦啦");
		po.setTypeId(1);
		ConstDictCtrl.save(po, null);
//		constDictBizImpl.save(po);
		
		 
		
		System.out.println("=============");
	}
}
