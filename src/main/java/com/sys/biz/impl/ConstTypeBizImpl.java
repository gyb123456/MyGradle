package com.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseBiz;
import com.sys.biz.ConstTypeBiz;
import com.sys.dao.ConstTypeDao;
import com.sys.dao.impl.ConstDictDaoImpl;
import com.sys.dao.impl.ConstTypeDaoImpl;
import com.sys.po.ConstType;

@Service
@Transactional
public class ConstTypeBizImpl extends BaseBiz<ConstTypeDaoImpl, Integer, ConstType>{
//	implements ConstTypeBiz
	@Autowired
	private ConstTypeDaoImpl constTypeDaoImpl; 
	@Autowired
	private ConstDictDaoImpl constDictDaoImpl; 
	
//	@Override
	public List<ConstType> findAll() {
		 List<ConstType> list = new ArrayList<ConstType>();
//		 ConstType constType = new ConstType();
//		 constType.setCode("code1");
//		 list.add(constType);
		 list = constTypeDaoImpl.findAll();
		 return list;
		
	}
	
	/**
	 * 根据id删除 字典常量类型
	 * @param id
	 * @return 1：删除成功；0：不可删除，说明其他表（sys_const_dict）有关联：
	 */
	public int delete(Integer id) {
		int count = constDictDaoImpl.getTypeCount(id);
		if(count==0){
			super.deleteById(id);
			return 1; 
		}
		return 0; 
	}

}
