package com.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.dto.PagingDto;

@SuppressWarnings({"rawtypes","unchecked"})
public class BaseCtrl<BIZ extends BaseBiz, ID extends Serializable, PO> {

	@Autowired
	protected BIZ biz;

	protected String defaultPage; //设置Ctrl默认显示的页面，该属性由子类初始化时设置
	
	/**显示默认的页面*/
	@RequestMapping
	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(defaultPage);
		return mav;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public PO save(PO po, HttpSession session){
		try {
			Field field = po.getClass().getDeclaredField("updateBy");
			if(field != null){//说明此实例类含有updateBy属性
				field.set(po, 100);//设置updateBy为当前登录用户的id，但是要求updateBy属性必须为public类型的！！！，否则抛异常
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		biz.save(po);
		return po;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public PO update(PO po, HttpSession session){
		biz.update(po);
		return po;
	}
	
	/**@param id 主键
	 * @return 0:删除失败，1:删除成功
	 * */
	@RequestMapping("delete/{id}")
	@ResponseBody
	public Integer delete(@PathVariable ID id, HttpSession session){
		try{
			biz.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<PO> getAll(){
		return biz.getAll();
	}
	
	/**分页查询所有数据
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	@RequestMapping("getAllByPage")
	@ResponseBody
	public PagingDto<PO> getAllByPage(Long total, int offset, int limit){
		return biz.getAllByPage(total, offset, limit);
	}
}
