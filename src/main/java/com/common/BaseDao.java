package com.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**数据库操作基类
 * @param ID 实体类的ID属性的类型，实现了Serializable接口
 * @param T 实体类的类型
 * @author zhiyu
 * @version 2016-03-04
 * */
@SuppressWarnings("unchecked")
@Transactional
public class BaseDao<ID extends Serializable, T> {

	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> persistentClass;	//实体类类型
	protected String persistentName;	//实体类名
	
	public BaseDao(){
		Type genType = getClass().getGenericSuperclass();//得到这个类的泛型父类
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();//获取泛型的参数类型，如Type<T>则获取T
		persistentClass = (Class<T>)params[1];//获取泛型实体
		persistentName = persistentClass.getSimpleName();
	}
	
	/**获取上下文关联的Session*/
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**保持与数据库数据的同步*/
	protected void flush(){
		getCurrentSession().flush();
	}
	
	/**清除内部缓存的全部数据，及时释放出占用的内存*/
	protected void clear(){
		getCurrentSession().clear();
	}
	
	/**获取持久化类对应的表名
	 * @return 数据表名
	 * */
	protected String getTableName(){
		AbstractEntityPersister classMetadata = (AbstractEntityPersister)sessionFactory.getClassMetadata(persistentClass);
		return classMetadata.getTableName();
	}
	
	/**获取持久化类属性名和对应数据表列名
	 * @return List<Object[]> Object[0]：String属性名，Object[1]：String列名，Object[2]：org.hibernate.type.Type属性对应的hibernate Type.
	 * */
	protected List<Object[]> getPropertyAndColumnNames(){
		List<Object[]> list = new ArrayList<Object[]>();
		AbstractEntityPersister classMetadata = (AbstractEntityPersister)sessionFactory.getClassMetadata(persistentClass);
		String[] propertyNames = classMetadata.getPropertyNames();
		for(String propertyName : propertyNames){
			String columnName = classMetadata.getPropertyColumnNames(propertyName)[0];
			org.hibernate.type.Type type = classMetadata.getPropertyType(propertyName);
			list.add(new Object[]{propertyName,columnName,type});
		}
		return list;
	}
	
	/*========================================
	 * 增、删、改
	 * =======================================*/
	
	public void persist(T entity){
		getCurrentSession().persist(entity);
	}
	
	/**保存实体*/
	public Serializable save(T entity){
		return getCurrentSession().save(entity);
	}
	
	/**更新实体*/
	public void update(T entity){
		getCurrentSession().update(entity);
	}
	
	/**merge实体*/
	public void merge(T entity){
		getCurrentSession().merge(entity);
	}
	
	/**保存或更新实体*/
	public void saveOrUpdate(T entity){
		getCurrentSession().saveOrUpdate(entity);
	}
	
	/**删除实体*/
	public void delete(T entity){
		getCurrentSession().delete(entity);
	}
	
	/**根据ID删除实体*/
	public void deleteById(ID id){
		Query query = getCurrentSession()
				.createQuery("delete from "+persistentClass.getSimpleName()+" where id = :id");
		query.setParameter("id", id).executeUpdate();
	}

	/**使用hql执行更新语句
	 * @param hql 要执行的hql语句
	 * @return 返回更改的行数
	 * */
	public int executeUpdate(String hql){
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		return query.executeUpdate();
	}
	
	/**使用hql执行更新语句。propertyNames数组长度要与values数组长度一致。
	 * @param hql 要执行的hql语句
	 * @param propertyNames 参数名
	 * @param values 参数值
	 * @return 返回更改的行数
	 * */
	public int executeUpdate(String hql, String[] propertyNames,Object[] values){
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		for(int i=0; i<propertyNames.length; i++){
			if(values[i] instanceof Object[])
				query.setParameterList(propertyNames[i], (Object[])values[i]);
			else if(values[i] instanceof Collection)
				query.setParameterList(propertyNames[i], (Collection<?>)values[i]);
			else
				query.setParameter(propertyNames[i], values[i]);
		}
		return query.executeUpdate();
	}
	
	/**批量保存*/
	public void saveBatch(List<T> list){
		Session session = getCurrentSession();
		for(int i=0; i<list.size(); i++){
			session.save(list.get(i));
			if(i%30 == 0){//单次批量操作的数目
				session.flush();
				session.clear();
			}
		}
	}
	
	/**批量保存或更新*/
	public void saveOrUpdateBatch(List<T> list){
		Session session = getCurrentSession();
		for(int i=0; i<list.size(); i++){
			session.saveOrUpdate(list.get(i));
			if(i%30 == 0){//单次批量操作的数目
				session.flush();
				session.clear();
			}
		}
	}
	
	/**根据Id数组批量删除
	 * @param ids id数组
	 * */
	public void deleteByIds(ID[] ids){
		Query query = getCurrentSession()
				.createQuery("delete "+persistentClass.getSimpleName()+" where id in (:ids)");
		query.setParameterList("ids", ids).executeUpdate();
	}
	
	
	/*==============================================
	 * 查询
	 * =============================================*/
	
	/**根据ID加载实体，实体类型为Dao的泛型参数类型
	 * @param id 主键
	 * @return 若未查找到返回null
	 * */
	public T findById(ID id){
		return (T)getCurrentSession().get(persistentClass, id);
	}
	
	/**根据实体类型，ID加载实体。此重载方法是为了便于查找非Dao泛型参数的实体。
	 * @param entityClass 要加载的实体类型
	 * @param id 主键
	 * @return 若未查找到返回null
	 */
	public T findById(Class<T> entityClass, Serializable id){
		return (T)getCurrentSession().get(entityClass, id);
	}
	
	/**根据Ids集合查询*/
	public List<T> findByIds(Collection<? extends Serializable> ids){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		Criterion criterion = Restrictions.in("id", ids);
		criteria.add(criterion);
		return criteria.list();
	}
	
	//============================获取总数=============================
	
	/**获取实体总数*/
	public long getCount(){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	/**根据HQL查询记录总数
	 * @param hql hql语句
	 * @param paramNames 命名查询参数名
	 * @param values 命名查询参数值
	 * */
	public long getCount(String hql, String[] paramNames, Object[] values){
		Query query = getCurrentSession().createQuery(hql);
		for(int i=0; i<paramNames.length; i++)
		{
			if(values[i] instanceof Object[])
				query.setParameterList(paramNames[i], (Object[])values[i]);
			else if(values[i] instanceof Collection)
				query.setParameterList(paramNames[i], (Collection<?>)values[i]);
			else
				query.setParameter(paramNames[i], values[i]);
		}
		return (Long)query.uniqueResult();
	}
	
	/**QBE查询方式
	 * @param exampleEntity 包含查询条件的实例
	 * @param excludeProperty 不需要查询的属性名
	 * */
	public List<T> findByExample(T exampleEntity,String... excludeProperty){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		Example example = Example.create(exampleEntity);
		for(String exclude : excludeProperty){
			example.excludeProperty(exclude);
		}
		criteria.add(example);
		return criteria.list();
	}
	
	/**获取所有实体*/
	public List<T> getAll(){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
	/**分页获取所有数据
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public List<T> getAllByPage(int offset, int limit){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		criteria.setFirstResult(offset)
				.setMaxResults(limit);
		return criteria.list();
	}
	
	public List<T> findByCriteria(Criterion... criterion){
		Criteria criteria = getCurrentSession().createCriteria(persistentClass);
		for(Criterion c : criterion){
			criteria.add(c);
		}
		return criteria.list();
	}
	
	/**根据 HQL语句查询实体*/
	public List<T> find(String hql){
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	/**根据带占位符参数的 HQL语句查询实体*/
	public List<?> find(String hql, List<?> params){
		Query query = getCurrentSession().createQuery(hql);
		// 为包含占位符的 HQL 语句设置参数
		for(int i=0; i<params.size(); i++)
		{
			query.setParameter(i, params.get(i));
		}
		return query.list();
	}
	
	/**HQL命名查询
	 * @param paramNames 参数名
	 * @param params 参数值
	 * */
	public List<?> find(String hql, String[] paramNames, Object[] values){
		Query query = getCurrentSession().createQuery(hql);
		for(int i=0; i<paramNames.length; i++)
		{
			if(values[i] instanceof Object[])
				query.setParameterList(paramNames[i], (Object[])values[i]);
			else if(values[i] instanceof Collection)
				query.setParameterList(paramNames[i], (Collection<?>)values[i]);
			else
				query.setParameter(paramNames[i], values[i]);
		}
		return query.list();
	}
	
	/**HQL命名查询
	 * @param paramNames 参数名
	 * @param values 参数值
	 * @param clazz 要转换成的Class类型
	 * */
	public <T2> List<T2> find(String hql, String[] paramNames, Object[] values, Class<T2> clazz){
		Query query = getCurrentSession().createQuery(hql);
		for(int i=0; i<paramNames.length; i++)
		{
			if(values[i] instanceof Object[])
				query.setParameterList(paramNames[i], (Object[])values[i]);
			else if(values[i] instanceof Collection)
				query.setParameterList(paramNames[i], (Collection<?>)values[i]);
			else
				query.setParameter(paramNames[i], values[i]);
		}
		return query.setResultTransformer(Transformers.aliasToBean(clazz))
				.list();
	}
	
	/**
	* 使用 HQL 语句进行分页查询操作
	* @param hql 需要查询的 HQL语句
	* @param params 如果hql带占位符参数， params用于传入占位符参数
	* @param offset 偏移量，即记录索引位置
	* @param limit 每页记录数
	* @return 当前页的所有记录
	*/
	public List<?> findByPage(String hql , int offset, int limit, String[] paramNames, Object[] params)
	{
		Query query = getCurrentSession().createQuery(hql);
		//为包含占位符的 HQL语句设置参数
		for(int i=0; i<paramNames.length; i++)
		{
			if(params[i] instanceof Collection)
				query.setParameterList(paramNames[i], (Collection<?>)params[i]);
			else
				query.setParameter(paramNames[i], params[i]);
		}
		// 执行分页，并返回查询结果
		return query.setFirstResult(offset)
				.setMaxResults(limit)
				.list();
	}
	
	public <T2> List<T2> findByPage(String hql , int offset, int limit, String[] paramNames, Object[] params, Class<T2> clazz)
	{
		Query query = getCurrentSession().createQuery(hql);
		//为包含占位符的 HQL语句设置参数
		for(int i=0; i<paramNames.length; i++)
		{
			if(params[i] instanceof Collection)
				query.setParameterList(paramNames[i], (Collection<?>)params[i]);
			else
				query.setParameter(paramNames[i], params[i]);
		}
		// 执行分页，并返回查询结果
		return query.setFirstResult(offset)
				.setMaxResults(limit)
				.setResultTransformer(Transformers.aliasToBean(clazz))
				.list();
	}
	
	//=============================本地SQL查询============================
	/**使用sql执行更新语句。propertyNames数组长度要与values数组长度一致。
	 * @param sql 要执行的sql语句
	 * @param propertyNames 参数名
	 * @param values 参数值
	 * @return 返回更改的行数
	 * */
	public int executeUpdateByNativeSql(String sql, String[] propertyNames,Object[] values){
		Session session = getCurrentSession();
		Query query = session.createSQLQuery(sql);
		for(int i=0; i<propertyNames.length; i++){
			query.setParameter(propertyNames[i], values[i]);
		}
		return query.executeUpdate();
	}
	
	/**根据本地SQL执行查询，并返回List<?>
	 * */
	public List<?> findByNativeSql(String sql, String[] propertyNames, Object[] values){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.length; i++){
			if(values[i] instanceof Object[])
				sqlQuery.setParameterList(propertyNames[i], (Object[])values[i]);
			else if(values[i] instanceof Collection)
				sqlQuery.setParameterList(propertyNames[i], (Collection<?>)values[i]);
			else
				sqlQuery.setParameter(propertyNames[i], values[i]);
		}
		return sqlQuery.list();
	}
	
	/**根据本地SQL执行查询，并返回List<?>
	 * */
	public List<?> findByNativeSql(String sql, List<String> propertyNames, List<Object> values){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.size(); i++){
			if(values.get(i) instanceof Object[])
				sqlQuery.setParameterList(propertyNames.get(i), (Object[])values.get(i));
			else if(values.get(i) instanceof Collection)
				sqlQuery.setParameterList(propertyNames.get(i), (Collection<?>)values.get(i));
			else
				sqlQuery.setParameter(propertyNames.get(i), values.get(i));
		}
		return sqlQuery.list();
	}
	
	/**根据本地SQL执行查询，并返回需要封装成的Bean类型
	 * @param scalars Object[0] SQL语句中的列别名 Object[1] hibernate Type类型
	 * */
	public List<T> findByNativeSql(String sql, String[] propertyNames, List<?> values, List<Object[]> scalars){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.length; i++){
			if(values.get(i) instanceof Object[])
				sqlQuery.setParameterList(propertyNames[i], (Object[])values.get(i));
			else if(values.get(i) instanceof Collection)
				sqlQuery.setParameterList(propertyNames[i], (Collection<?>)values.get(i));
			else
				sqlQuery.setParameter(propertyNames[i], values.get(i));
		}
		for(Object[] scalar : scalars){
			sqlQuery.addScalar((String)scalar[0],(org.hibernate.type.Type)scalar[1]);
		}
		return sqlQuery.setResultTransformer(Transformers.aliasToBean(persistentClass)).list();
	}
	
	/**根据本地SQL执行查询，并返回需要封装成的Bean类型
	 * @param scalars Object[0] SQL语句中的列别名 Object[1] hibernate Type类型
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public List<T> findByNativeSql(String sql, String[] propertyNames, Object[] values, List<Object[]> scalars, int offset, int limit){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.length; i++){
			if(values[i] instanceof Object[])
				sqlQuery.setParameterList(propertyNames[i], (Object[])values[i]);
			else if(values[i] instanceof Collection)
				sqlQuery.setParameterList(propertyNames[i], (Collection<?>)values[i]);
			else
				sqlQuery.setParameter(propertyNames[i], values[i]);
		}
		for(Object[] scalar : scalars){
			sqlQuery.addScalar((String)scalar[0],(org.hibernate.type.Type)scalar[1]);
		}
		return sqlQuery.setFirstResult(offset)
				.setMaxResults(limit)
				.setResultTransformer(Transformers.aliasToBean(persistentClass)).list();
	}
	
	/**根据本地SQL执行查询，并返回需要封装成的Bean类型
	 * @param scalars Object[0] SQL语句中的列别名 Object[1] hibernate Type类型
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public List<T> findByNativeSql(String sql, List<String> propertyNames, List<Object> values, List<Object[]> scalars, int offset, int limit){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.size(); i++){
			if(values.get(i) instanceof Object[])
				sqlQuery.setParameterList(propertyNames.get(i), (Object[])values.get(i));
			else if(values.get(i) instanceof Collection)
				sqlQuery.setParameterList(propertyNames.get(i), (Collection<?>)values.get(i));
			else
				sqlQuery.setParameter(propertyNames.get(i), values.get(i));
		}
		for(Object[] scalar : scalars){
			sqlQuery.addScalar((String)scalar[0],(org.hibernate.type.Type)scalar[1]);
		}
		return sqlQuery.setFirstResult(offset)
				.setMaxResults(limit)
				.setResultTransformer(Transformers.aliasToBean(persistentClass)).list();
	}
	
	/**根据本地SQL执行查询，并返回需要封装成的Bean类型
	 * @param scalars Object[0] SQL语句中的列别名 Object[1] hibernate Type类型
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public <T2> List<T2> findByNativeSql(String sql, List<String> propertyNames, List<Object> values, List<Object[]> scalars, int offset, int limit, Class<T2> clazz){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.size(); i++){
			if(values.get(i) instanceof Object[])
				sqlQuery.setParameterList(propertyNames.get(i), (Object[])values.get(i));
			else if(values.get(i) instanceof Collection)
				sqlQuery.setParameterList(propertyNames.get(i), (Collection<?>)values.get(i));
			else
				sqlQuery.setParameter(propertyNames.get(i), values.get(i));
		}
		for(Object[] scalar : scalars){
			sqlQuery.addScalar((String)scalar[0],(org.hibernate.type.Type)scalar[1]);
		}
		return sqlQuery.setFirstResult(offset)
				.setMaxResults(limit)
				.setResultTransformer(Transformers.aliasToBean(clazz)).list();
	}
	
	/**根据本地SQL执行查询，并返回需要封装成的Bean类型
	 * @param scalars Object[0] SQL语句中的列别名 Object[1] hibernate Type类型
	 * */
	public <T2> List<T2> findByNativeSql(String sql, String[] propertyNames, List<?> values, List<Object[]> scalars, Class<T2> clazz){
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		for(int i=0; i<propertyNames.length; i++){
			if(values.get(i) instanceof Object[])
				sqlQuery.setParameterList(propertyNames[i], (Object[])values.get(i));
			else if(values.get(i) instanceof Collection)
				sqlQuery.setParameterList(propertyNames[i], (Collection<?>)values.get(i));
			else
				sqlQuery.setParameter(propertyNames[i], values.get(i));
		}
		for(Object[] scalar : scalars){
			sqlQuery.addScalar((String)scalar[0],(org.hibernate.type.Type)scalar[1]);
		}
		return sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz)).list();
	}
}
