package com.base.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.biz.StationBiz;
import com.base.dao.impl.StationDaoImpl;
import com.base.po.Station;
import com.common.BaseBiz;

@Service
public class StationBizImpl extends BaseBiz<StationDaoImpl, Integer, Station>{

	public List<Station> findByRtNumAndDirec(Integer routeNum,Integer direction){
		return dao.findByRtNumAndDirec(routeNum, direction);
	}
	
	public List<Station> findByName(String name){
		return null;
	}
}
