package com.sys.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.BaseCtrl;
import com.sys.biz.impl.ConstTypeBizImpl;
import com.sys.po.ConstType;

@Controller
@RequestMapping("ConstType")
public class ConstTypeCtrl extends BaseCtrl<ConstTypeBizImpl, Integer, ConstType>{

	/**
	 * 获取所有的字典常量类型
	 * @return List<ConstType> 
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<ConstType> findAll(){
		return biz.findAll();
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "test";
	}
	
	/**
	 * 根据id删除 字典常量类型
	 * @param id:字典常量类型id
	 * @return 1：删除成功；0：不可删除，说明其他表（sys_const_dict）有关联：
	 */
	public int delete(Integer id) {
		return biz.delete(id);
	}
}
