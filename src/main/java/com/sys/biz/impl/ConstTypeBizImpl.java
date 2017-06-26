package com.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseBiz;
import com.sys.biz.ConstTypeBiz;
import com.sys.dao.ConstTypeDao;
import com.sys.dao.impl.ConstTypeDaoImpl;
import com.sys.po.ConstType;

@Service
@Transactional
public class ConstTypeBizImpl extends BaseBiz<ConstTypeDaoImpl, Integer, ConstType>{
//	implements ConstTypeBiz
	@Autowired
	private ConstTypeDaoImpl ConstTypeDaoImpl; 
	
	
//	@Override
	public List<ConstType> findAll() {
		 List<ConstType> list = new ArrayList<ConstType>();
//		 ConstType constType = new ConstType();
//		 constType.setCode("code1");
//		 list.add(constType);
		 list = ConstTypeDaoImpl.findAll();
		 return list;
		
	}

}
