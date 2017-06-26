package com.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseBiz;
import com.common.dto.PagingDto;
import com.sys.dao.impl.ConstDictDaoImpl;
import com.sys.po.ConstDict;

@Service
@Transactional
public class ConstDictBizImpl extends BaseBiz<ConstDictDaoImpl, Integer, ConstDict>{
	
	/**
	 * 分页查询
	 */
	public PagingDto<ConstDict> findByPageAndParams(int page,int num,Integer typeId){
		return dao.findByPageAndParams(page, num, typeId);
	}

	/**
	 * 找出某一字典类型的所有字典常量
	 * @param typeId
	 * @return
	 */
	public List<ConstDict> getByTypeId(Integer typeId){
		return dao.getByTypeId(typeId);
	}
}
