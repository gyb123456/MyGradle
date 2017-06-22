package com.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.dao.StationDao;
import com.base.po.Route;
import com.base.po.Station;
import com.common.BaseDao;

@Repository
public class StationDaoImpl extends BaseDao<Integer,Station>{

	@SuppressWarnings("unchecked")
	public List<Station> findByRtNumAndDirec(Integer routeNum,Integer direction){
		String hql = "from Station where routeNum = :routeNum and direction =:direction";
		return (List<Station>)super.find(hql,new String[]{"routeNum","direction"}, new Integer[]{routeNum,direction});
	}
	
	public List<Station> findByName(String name){
		return null;
	}
}
