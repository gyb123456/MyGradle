package com.sys.dao;

import java.util.List;

import com.sys.po.ConstType;

public interface ConstTypeDao {

	/**
	 * 获取所有的字典常量类型
	 * @return List<ConstType> 
	 */
	public List<ConstType> findAll();
	
	
}