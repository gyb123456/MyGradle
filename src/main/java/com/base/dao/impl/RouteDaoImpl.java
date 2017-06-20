package com.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.base.dao.RouteDao;
import com.base.po.Route;
import com.common.BaseDao;

@Repository
public class RouteDaoImpl extends BaseDao<Integer,Route> implements RouteDao{

}
