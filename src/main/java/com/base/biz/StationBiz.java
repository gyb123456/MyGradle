package com.base.biz;

import java.util.List;

import com.base.po.Station;

/**站点表相关接口
 * @author yaomin
 * */
public interface StationBiz {
	
	/**根据线路编号和方向查询站点信息
	 * @param routeNum 线路编号
	 * @param direction 方向
	 * @return List<Station>
	 */
	public List<Station> findByRtNumAndDirec(Integer routeNum,Integer direction);
	
	/**根据站点名查询站点信息
	 * @param name 名称
	 * @return List<Station>
	 */
	public List<Station> findByName(String name);
}
