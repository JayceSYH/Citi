package edu.nju.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;


public interface BaseDao {

	public Session getSession();

	public Session getNewSession();
	
	public void flush();

	public void clear() ;

	public Object load(Class c, int id) ;

	 List find(String queryString);

	List find(String queryString, int maxResult);

	List sql_find(String sql, Class cls);

	List sql_find(String sql, Class cls, int maxResult);

	void query(String query);
	 
	public List getAllList(Class c) ;
	
	public List login(String queryString,String uname,String pass);
	
	public Long getTotalCount(Class c) ;

	public void save(Object bean) ;

	public void update(Object bean) ;

	public void delete(Object bean) ;

	public void saveOrUpdate(Object bean);
	
	public void delete(Class c, int id) ;

	public void delete(Class c, String[] ids) ;
}
