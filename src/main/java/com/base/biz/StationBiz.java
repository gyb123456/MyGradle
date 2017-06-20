package com.base.biz;

import java.util.List;

import com.base.po.Station;

public interface StationBiz {
	//根据线路编号和方向查询站点信息
	List<Station> findByRtNumAndDirec(Integer routeNum,Integer direction);
	//根据站点名查询站点信息
	List<Station> findByName(String name);
}
