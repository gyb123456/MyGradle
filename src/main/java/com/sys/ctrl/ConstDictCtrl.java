package com.sys.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.BaseCtrl;
import com.sys.biz.impl.ConstDictBizImpl;
import com.sys.po.ConstDict;

@Controller
@RequestMapping("ConstDict")
public class ConstDictCtrl extends BaseCtrl<ConstDictBizImpl, Integer, ConstDict>{
	
	/**
	 * 找出某一字典类型的所有字典常量
	 * @param typeId
	 * @return
	 */
	public List<ConstDict> getByTypeId(Integer typeId){
		return biz.getByTypeId(typeId);
	}

}
