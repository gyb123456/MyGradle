package com.base.biz.impl;

import org.springframework.stereotype.Service;

import com.base.biz.StationBiz;
import com.base.dao.impl.StationDaoImpl;
import com.base.po.Station;
import com.common.BaseBiz;

@Service
public class StationBizImpl extends BaseBiz<StationDaoImpl, Integer, Station> implements StationBiz{

}
