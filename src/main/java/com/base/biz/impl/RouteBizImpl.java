package com.base.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.biz.RouteBiz;
import com.base.dao.impl.RouteDaoImpl;
import com.base.po.Route;
import com.common.BaseBiz;

@Service
public class RouteBizImpl extends BaseBiz<RouteDaoImpl, Integer, Route>{
	
	public List<Route> findByPageAndParams(int page,int num,Integer routeNum){
		return null;
	}
	
	public List<Route> findByName(String name){
		return dao.findByName(name);
	}
	
	public List<Map<String,Object>> findNameAndNumByNameLike(String name){
		return dao.findNameAndNumByNameLike(name);
	}
	
	public List<Route> findByNum(Integer routeNum){
		return dao.findByNum(routeNum);
	}
	
	public List<Map<String,Object>> getAllNameAndNum(){
		return null;
	}
	
	public List<Map<String,String>> findTermByNum(Integer routeNum){
		return null;
	}
	
	public List<Map<String,String>> findDepartTimeByNum(Integer routeNum){
		return null;
	}
}
