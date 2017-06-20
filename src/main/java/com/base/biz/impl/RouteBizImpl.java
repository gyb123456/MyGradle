package com.base.biz.impl;

import org.springframework.stereotype.Service;

import com.base.biz.RouteBiz;
import com.base.dao.impl.RouteDaoImpl;
import com.base.po.Route;
import com.common.BaseBiz;

@Service
public class RouteBizImpl extends BaseBiz<RouteDaoImpl, Integer, Route> implements RouteBiz{

}
