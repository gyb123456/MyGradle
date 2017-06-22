package com.base.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.base.dao.RouteDao;
import com.base.po.Route;
import com.common.BaseDao;

@Repository
public class RouteDaoImpl extends BaseDao<Integer,Route>{

	public List<Route> findByPageAndParams(int page,int num,Integer routeNum){
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Route> findByName(String name){
		String hql = "from Route where name = :name";
		return (List<Route>)super.find(hql,new String[]{"name"}, new String[]{name});
	}
	
	@SuppressWarnings({ "unchecked", "serial" })
	public List<Map<String,Object>> findNameAndNumByNameLike(String name){
		String hql = "select new map (routeNum as routeNum,name as name) from Route where name like ?";
		return (List<Map<String,Object>>)super.find(hql,new ArrayList<String>(){{add("%"+name+"%");}});
	}
	
	@SuppressWarnings("unchecked")
	public List<Route> findByNum(Integer routeNum){
		String hql = "from Route where routeNum = :routeNum";
		return (List<Route>)super.find(hql,new String[]{"routeNum"}, new Integer[]{routeNum});
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
