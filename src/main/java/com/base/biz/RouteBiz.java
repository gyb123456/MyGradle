package com.base.biz;

import java.util.List;
import java.util.Map;

import com.base.po.Route;

public interface RouteBiz {
	//分页查询
	List<Route> findByPageAndParams(int page,int num,Integer routeNum);
	//根据线路名查询线路信息
	List<Route> findByName(String name);
	//根据线路名模糊查询线路信息
	List<Route> findByNameLike(String name);	
	//根据线路编号查询线路信息
	Route findByNum(Integer routeNum);
	//查询所有线路的线路名和线路编号
	List<Map> findNameAndNum();	
	//根据线路编号查询首末站信息
	List<Map> findTermByNum(Integer routeNum);	
	//根据线路编号查询首末班车信息
	List<Map> findDepartTimeByNum(Integer routeNum);	
}
