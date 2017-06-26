package com.sys.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.common.dto.PagingDto;
import com.sys.po.ConstDict;

@Repository
public class ConstDictDaoImpl extends BaseDao<Integer, ConstDict>{

	/**
	 * 查找某个type的个数
	 * @param typeId
	 * @return
	 */
	public int getTypeCount(Integer typeId){
		String sql = "SELECT COUNT(*) FROM `sys_const_dict` WHERE type_id =?;";
		BigInteger count = (BigInteger) super.getCurrentSession().createSQLQuery(sql).setParameter(0, typeId).uniqueResult();
		return count.intValue();
	}
	
	/**
	 * 找出某一字典类型的所有字典常量
	 * @param typeId
	 * @return
	 */
	public List<ConstDict> getByTypeId(Integer typeId){
		List<Integer> params = new ArrayList<Integer>(); 
		params.add(typeId);
		List<ConstDict> list = (List<ConstDict>) super.find("from ConstDict where typeId=?", params);
		return list;
	}
	
	/*
	 * 分页查询
	 */
	public PagingDto<ConstDict> findByPageAndParams(int page,int num,Integer typeId){
		String hql = " from ConstDict";
		String[] paramNames;
		Object[] params;
		if(typeId != null){
			hql = " from ConstDict where typeId=:typeId";
			paramNames = new String[]{"typeId"};
			params = new Object[]{typeId};
		}else{
			paramNames = new String[]{};
			params = new Object[]{};
		}
		List<ConstDict> list = (List<ConstDict>) super.findByPage(hql, page, num, paramNames, params);
		Long total = super.getCount("select count(id)"+hql, paramNames, params);
		
		PagingDto<ConstDict> dto = new PagingDto<ConstDict>();
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	
}
