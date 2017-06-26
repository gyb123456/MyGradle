package com.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.sys.dao.ConstTypeDao;
import com.sys.po.ConstType;

@Repository
public class ConstTypeDaoImpl extends BaseDao<Integer,ConstType> {

	 
//	@Override  implements ConstTypeDao
	public List<ConstType> findAll() {
//		List<ConstType> list =  getCurrentSession().createQuery("from ConstType").list();
//		return list;
		return super.getAll();
		
	}

}