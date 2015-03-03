package com.gavin.mybatis.javaApi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

/**
 * 
 * @author wenguang.xu
 * 当前有一些流行的第三方类库，如commons-dbcp 和 c3p0 实现了 java.sql.DataSource,可以用它们来创建DataSource。
 */
public class MybatisDataSourceFactory {

	/**
	 * JDBC POOLED数据源
	 * @return
	 */
	public static DataSource getJdbcDataSource() {
		String driver = "";
		String url = "";
		String username = "";
		String password = "";
		PooledDataSource dataSource = new PooledDataSource(driver,url,username,password);
		return dataSource;
	}
	
	/**
	 * JNDI (production)
	 * @return
	 */
	public static DataSource getJNDIDataSource() {
		String jndiName = "java:comp/env/jdbc/MyBatisDemoDS";
		DataSource dataSource = null;
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
}
