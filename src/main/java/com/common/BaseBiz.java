package com.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.dto.PagingDto;

/**基础业务类
 * */
@SuppressWarnings({"rawtypes","unchecked"})
public class BaseBiz<DAO extends BaseDao, ID extends Serializable, PO> {

	@Autowired
	protected DAO dao;
	
	public void save(PO entity){
		dao.save(entity);
	}
	
	public void update(PO entity){
		dao.update(entity);
	}
	
	public void delete(PO entity){
		dao.delete(entity);
	}
	
	public void deleteById(ID id){
		dao.deleteById(id);
	}
	
	public void deleteByIds(ID[] ids){
		dao.deleteByIds(ids);
	}
	
	public PO getById(ID id){
		return (PO)dao.findById(id);
	}
	
	public List<PO> findByExample(PO example){
		return dao.findByExample(example);
	}
	
	public List<PO> getAll(){
		return dao.getAll();
	}
	
	/**分页获取所有数据
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public PagingDto<PO> getAllByPage(Long total, int offset, int limit){
		PagingDto<PO> bt = new PagingDto<PO>();
		//若前端没有传递数据记录总数，则查询数据总记录数
		if(total == null)
			total = dao.getCount();
		bt.setTotal(total);
		bt.setRows(dao.getAllByPage(offset,limit));
		return bt;
	}
}
