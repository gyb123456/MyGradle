package com.base.dao;

import java.util.List;
import java.util.Map;

import com.base.po.Route;

/**线路表相关接口
 * @author yaomin
 * */
public interface RouteDao {
	/**分页查询
	 * @param page 当前页数
	 * @param num 每页的数据量
	 * @param routeNum 线路编号
	 * @return List<Route>
	 */
	public List<Route> findByPageAndParams(int page,int num,Integer routeNum);
	
	/**根据线路名称查询线路信息
	 * @param name 线路名称
	 * @return List<Route>
	 */
	public List<Route> findByName(String name);
	
	/**根据线路名称模糊查询线路名称和线路编号
	 * @param name 线路名称
	 * @return List<Map>  [{routeNum:12,name:""},{routeNum:13,name:""}]
	 */
	public List<Map<String,Object>> findNameAndNumByNameLike(String name);	
	
	/**根据线路编号查询线路信息
	 * @param routeNum 线路编号
	 * @return List<Route>
	 */	
	public List<Route> findByNum(Integer routeNum);
	
	/**查询所有线路的线路名和线路编号
	 * @return List<Map>  [{routeNum:12,name:""},{routeNum:13,name:""}]
	 */	
	public List<Map<String,Object>> getAllNameAndNum();	
	
	/**根据线路编号查询首末站信息
	 * @param routeNum 线路编号
	 * @return List<Map>  [{upTrmlStation:"",downTrmlStation:""},{upTrmlStation:"",downTrmlStation:""}]
	 */	
	public List<Map<String,String>> findTermByNum(Integer routeNum);	
	
	/**根据线路编号查询首末班车信息
	 * @param routeNum 线路编号
	 * @return List<Map>  [{upFirstTime:"",upLastTime:"",downFirstTime:"",downLastTime:""}]
	 */
	public List<Map<String,String>> findDepartTimeByNum(Integer routeNum);	
}
