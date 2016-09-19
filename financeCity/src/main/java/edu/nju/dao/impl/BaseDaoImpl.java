package edu.nju.dao.impl;

import java.util.List;

import edu.nju.dao.UserDao;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;


@Repository
public class BaseDaoImpl implements BaseDao, CommonDao, UserDao {
	/** * Autowired 自动装配 相当于get() set() */
	@Autowired
	protected SessionFactory sessionFactory;

	/** * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	/** * openSession 需要手动关闭session 意思是打开一个新的session * * @return */
	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	/** * 根据 id 查询信息 * * @param id * @return */
	@SuppressWarnings("rawtypes")
	public Object load(Class c, int id) {
		Session session = getSession();
		return session.get(c, id);
	}

	/** * 获取所有信息 * * @param c * * @return */

	public List getAllList(Class c) {
		String hql = "from " + c.getName()+ " object";
		Session session = getSession();
		return session.createQuery(hql).list();

	}

	/** * 获取总数量 * * @param c * @return */

	public Long getTotalCount(Class c) {
		Session session = getSession();
		String hql = "select count(*) from " + c.getName();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return count != null ? count.longValue() : 0;
	}

	/** * 保存 * * @param bean * */
	public void save(Object bean) {
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.save(bean);
			session.flush();
			session.clear();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * 更新 * * @param bean * */
	public void update(Object bean) {
		Session session = getSession();
		session.update(bean);
		session.flush();
		session.clear();
	}

	/** * 删除 * * @param bean * */
	public void delete(Object bean) {

		Session session = getSession();
		session.delete(bean);
		session.flush();
		session.clear();
	}

	@Override
	public void saveOrUpdate(Object bean) {
		Session session = getSession();
		session.saveOrUpdate(bean);
		session.flush();
		session.clear();
	}

	/** * 根据ID删除 * * @param c 类 * * @param id ID * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, int id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Object obj = session.get(c, id);
		session.delete(obj);
		flush();
		clear();
		tx.commit();
	}

	/** * 批量删除 * * @param c 类 * * @param ids ID 集合 * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String[] ids) {
		for (String id : ids) {
			Object obj = getSession().get(c, id);
			if (obj != null) {
				getSession().delete(obj);
			}
		}
	}

	// 根据HQL语句进行查询
	@SuppressWarnings("unchecked")
	public List find(String queryString) {
		Session session = getSession();
		return session.createQuery(queryString).list();
	}

	@Override
	public List find(String queryString, int maxResult) {
		Session session = getSession();
		Query query = session.createQuery(queryString);
		query.setMaxResults(maxResult);
		return query.list();
	}

	@Override
	public List sql_find(String sql, Class cls) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(cls);
		return query.list();
	}

	@Override
	public List sql_find(String sql, Class cls, int maxResult) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(cls);
		query.setMaxResults(maxResult);
		return query.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 根据HQL语句进行查询
	public List login(String queryString,String uname,String pass) {
		Session session = getSession();
		return session.createQuery(queryString).setParameter(0, uname).setParameter(1, pass).list();
	}

	@Override
	public void query(String query) {
		Session session = getSession();
		session.createQuery(query);
	}
}
