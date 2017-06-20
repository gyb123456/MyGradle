package com.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.base.dao.StationDao;
import com.base.po.Station;
import com.common.BaseDao;

@Repository
public class StationDaoImpl extends BaseDao<Integer,Station> implements StationDao{

}
