package com.gavin.mybatis.javaApi;


import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;

import com.gavin.mybatis.entity.Author;
import com.gavin.mybatis.entity.BlogMapper;
import com.gavin.mybatis.handler.PhoneTypeHandler;

/**
 * 
 * @author wenguang.xu
 * 使用 Java API 创建 sqlSessionFactory
 */
public class MybatisSqlSessionFactory {

	private static SqlSessionFactory sqlSessionFactory = null;
	
	public static SqlSessionFactory getSqlSessionFactory() {
		
		if(sqlSessionFactory == null) {
			try {
				
				DataSource dataSource = MybatisDataSourceFactory.getJdbcDataSource();
				//DataSource dataSource = MybatisDataSourceFactory.getJNDIDataSource();
				
				TransactionFactory transactionFactory = new JdbcTransactionFactory();
				//TransactionFactory transactionFactory = new ManagedTransactionFactory();
				
				Environment environment = new Environment("development", transactionFactory, dataSource);
				Configuration configuration = new Configuration(environment);
				
				//typeAlias
				configuration.getTypeAliasRegistry().registerAlias(Author.class); //根据默认的别名规则，使用一个类的首字母小写、非完全限定的类名作为别名注册。
				configuration.getTypeAliasRegistry().registerAlias("author",Author.class); //指定别名注册
				configuration.getTypeAliasRegistry().registerAlias("author","com.gavin.mybatis.entity.Author"); //通过类的完全限定名注册相应类别名
				configuration.getTypeAliasRegistry().registerAliases("com.gavin.mybatis.entity"); //为某一个包中的所有类注册别名
				configuration.getTypeAliasRegistry().registerAliases("com.gavin.mybatis.entity",Exception.class); //为在指定包中所有的继承自Exception类型的类注册别名
				
				//typeHandlers
				configuration.getTypeHandlerRegistry().register(PhoneTypeHandler.class);//注册一个类处理器
				configuration.getTypeHandlerRegistry().register(Author.class,PhoneTypeHandler.class);//为某个特定的类注册类处理器
				configuration.getTypeHandlerRegistry().register("com.gavin.mybatis.handler");//注册某个包中的所有类型处理器
				
				//settings 默认参数：
				configuration.setCacheEnabled(true); 
				configuration.setLazyLoadingEnabled(false); 
				configuration.setMultipleResultSetsEnabled(true);
				configuration.setUseColumnLabel(true); 
				configuration.setUseGeneratedKeys(false); 
				configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
				configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
				configuration.setDefaultStatementTimeout(25); 
				configuration.setSafeRowBoundsEnabled(false); 
				configuration.setMapUnderscoreToCamelCase(false);
				configuration.setLocalCacheScope(LocalCacheScope.SESSION);
				configuration.setAggressiveLazyLoading(true);
				configuration.setJdbcTypeForNull(JdbcType.OTHER); 
				Set<String> lazyLoadTriggerMethods = new HashSet<String>();
				lazyLoadTriggerMethods.add("equals");
				lazyLoadTriggerMethods.add("clone"); 
				lazyLoadTriggerMethods.add("hashCode"); 
				lazyLoadTriggerMethods.add("toString");
				configuration.setLazyLoadTriggerMethods(lazyLoadTriggerMethods );
				
				//mappers
				configuration.addMapper(BlogMapper.class);//添加一个mapper接口
				configuration.addMappers("com.gavin.mybatis.mapper");//添加指定包中的所有Mapper XML文件或Mapper 接口
				//configuration.addMappers("com.gavin.mybatis.mapper",BaseMapper.class);//添加指定包中的拓展了特定Mapper接口的Mapper 接口
				
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
				
				
				
				//自定义MyBatis日志
				//Mybatis使用其内部LoggerFactory作为真正的日志类库使用的门面。其内部的LoggerFactory会将日志记录任务委托给如下某个日志实现，日志记录优先级由上到下递减：
				/*
				 * SLF4J
				 * Apache commons logging
				 * log4j 2
				 * log4j
				 * JDK logging
				 */
				//如果在运行环境中，有多个可用的日志类库，并且希望Mybatis使用某个特定的日志实现，可以调用如下方法：
				LogFactory.useSlf4jLogging();
				LogFactory.useLog4JLogging();
				LogFactory.useLog4J2Logging();
				LogFactory.useJdkLogging();
				LogFactory.useCommonsLogging();
				LogFactory.useStdOutLogging();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sqlSessionFactory;
	}
	
}
