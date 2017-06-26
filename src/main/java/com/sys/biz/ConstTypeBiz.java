package com.sys.biz;

import java.util.List;

import com.sys.po.ConstType;

public interface ConstTypeBiz {

	/**
	 * 获取所有的字典常量类型
	 * @return List<ConstType> 
	 */
	public List<ConstType> findAll();
}
