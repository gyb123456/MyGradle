package com.sys.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.biz.impl.ConstTypeBizImpl;
import com.sys.po.ConstType;

@Controller
@RequestMapping("ConstType")
public class ConstTypeCtrl {

	@Autowired
	private ConstTypeBizImpl ConstTypeBizImpl;

	/**
	 * 获取所有的字典常量类型
	 * @return List<ConstType> 
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<ConstType> findAll(){
		return ConstTypeBizImpl.findAll();
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		return "test";
	}
}
