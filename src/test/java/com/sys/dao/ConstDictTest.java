package com.sys.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.RootConfig;
import com.common.dto.PagingDto;
import com.sys.biz.impl.ConstTypeBizImpl;
import com.sys.dao.impl.ConstDictDaoImpl;
import com.sys.po.ConstDict;
import com.sys.po.ConstType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)

//@Transactional
public class ConstDictTest {

	@Autowired
	private ConstDictDaoImpl constDictDaoImpl; 
	
	
	@Test
	public void test() {
		Integer x = constDictDaoImpl.getTypeCount(1);
		System.out.println("============="+x);
	}
	
	@Test
	public void findByPageAndParams(){
		PagingDto<ConstDict> pages = constDictDaoImpl.findByPageAndParams(0, 10, null);
		System.out.println(pages.toString());
	}
}
