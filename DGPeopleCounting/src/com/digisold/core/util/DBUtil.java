package com.digisold.core.util;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class DBUtil {

	@Autowired
	public DataSource dataSource;

	private QueryRunner qr;

	/**
	 * 插入数据，返回最后的主键ID
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public long insert(String sql, Object... params) {
		qr = new QueryRunner(dataSource);
		try {
			return (Long) qr.insert(sql, new ScalarHandler<Long>(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 新增，删除，修改，返回受影响的行记录数
	 * 
	 * @param sql
	 * @return
	 */
	public int update(String sql) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.update(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * 新增，删除，修改，返回受影响的行记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.update(sql, params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	/**
	 * 查询返回单条封装到map的记录数
	 * 
	 * @param sql
	 * @return
	 */
	public Map<String, Object> queryMap(String sql) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new MapHandler());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回单条封装到map的记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryMap(String sql, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new MapHandler(), params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回单条封装到数组的记录数
	 * 
	 * @param sql
	 * @return
	 */
	public Object[] queryArray(String sql) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new ArrayHandler());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回单条封装到数组的记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object[] queryArray(String sql, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new ArrayHandler(), params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回单条封装到Bean的记录数
	 * 
	 * @param sql
	 * @param cls
	 * @return
	 */
	public <T> T queryBean(String sql, Class<T> cls) {
		try {
			qr = new QueryRunner(dataSource);
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			return qr.query(sql, new BeanHandler<T>(cls, processor));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回单条封装到Bean的记录数
	 * 
	 * @param sql
	 * @param cls
	 * @param params
	 * @return
	 */
	public <T> T queryBean(String sql, Class<T> cls, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			return qr.query(sql, new BeanHandler<T>(cls, processor), params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回多条封装到数组的记录数
	 * 
	 * @param sql
	 * @return
	 */
	public List<Object[]> queryArrayList(String sql) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new ArrayListHandler());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回多条封装到数组的记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> queryArrayList(String sql, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new ArrayListHandler(), params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回多条封装到map的记录数
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryMapList(String sql) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new MapListHandler());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回多条封装到map的记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryMapList(String sql, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			return qr.query(sql, new MapListHandler(), params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回多条封装到Bean的记录数
	 * 
	 * @param sql
	 * @param cls
	 * @return
	 */
	public <T> List<T> queryListBean(String sql, Class<T> cls) {
		try {
			qr = new QueryRunner(dataSource);
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			return qr.query(sql, new BeanListHandler<T>(cls, processor));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询返回多条封装到Bean的记录数
	 * 
	 * @param sql
	 * @param cls
	 * @param params
	 * @return
	 */
	public <T> List<T> queryListBean(String sql, Class<T> cls, Object... params) {
		try {
			qr = new QueryRunner(dataSource);
			BeanProcessor bean = new GenerousBeanProcessor();
			RowProcessor processor = new BasicRowProcessor(bean);
			return qr.query(sql, new BeanListHandler<T>(cls, processor), params);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 统计记录数
	 * 
	 * @param tableName
	 * @return
	 */
	public long count(String tableName) {
		String sql = "select count(1) as records from " + tableName;
		Map<String, Object> dataMap = this.queryMap(sql);
		return (long) dataMap.get("records");
	}

	/**
	 * 统计记录数
	 * 
	 * @param tableName
	 * @param filter
	 * @param params
	 * @return
	 */
	public long count(String tableName, String filter, Object... params) {
		StringBuffer sql = new StringBuffer("select count(1) as records from ");
		{
			sql.append(tableName);
			sql.append(!filter.toString().startsWith("where") ? " where " : " ");
			sql.append(filter);
		}
		Map<String, Object> dataMap = this.queryMap(sql.toString(), params);
		return (long) dataMap.get("records");
	}
}
