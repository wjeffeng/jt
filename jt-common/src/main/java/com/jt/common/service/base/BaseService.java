package com.jt.common.service.base;

import java.util.List;

public interface BaseService<T> {

	/**
	 * 根据主键查询数据
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Object id);

	/**
	 * 根据条件查询，多条件之间是 and 关系
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryListByWhere(T t);

	/**
	 * 根据条件查询单条数据
	 * 
	 * @param t
	 * @return
	 */
	public T queryByWhere(T t);

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public List<T> queryAll();

	/**
	 * 新增数据，使用全部字段
	 * 
	 * @param t
	 */
	public void save(T t);

	/**
	 * 新增数据，使用不为null的字段
	 * 
	 * @param t
	 */
	public void saveSelective(T t);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteById(Object id);

	/**
	 * 根据ids删除
	 * 
	 * @param ids
	 * @return
	 */
	public Integer deleteByIds(Object[] ids);

	/**
	 * 根据条件删除
	 * 
	 * @param t
	 */
	public Integer deleteByWhere(T t);

	/**
	 * 根据主键id更新数据
	 * 
	 * @param t
	 */
	public Integer update(T t);

	/**
	 * 根据主键id更新数据
	 * 
	 * @param t
	 */
	public Integer updateSelective(T t);
}
