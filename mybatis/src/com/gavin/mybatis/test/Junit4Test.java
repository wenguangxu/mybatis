package com.gavin.mybatis.test;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gavin.mybatis.entity.Blog;
import com.gavin.mybatis.util.SqlMapperManager;

/**
 * junit4.x
 * 
 * (1)、使用junit4.x版本进行单元测试时，不用测试类继承TestCase父类，因为，junit4.x全面引入了Annotation来执行我们编写的测试。
 * (2)、junit4.x版本，引用了注解的方式，进行单元测试；
 * (3)、junit4.x版本我们常用的注解：
 * 	A、@Before 注解：与junit3.x中的setUp()方法功能一样，在每个测试方法之前执行；
 * 	B、@After 注解：与junit3.x中的tearDown()方法功能一样，在每个测试方法之后执行；
 * 	C、@BeforeClass 注解：在所有方法执行之前执行；
 * 	D、@AfterClass 注解：在所有方法执行之后执行；
 * 	E、@Test(timeout = xxx) 注解：设置当前测试方法在一定时间内运行完，否则返回错误；
 * 	F、@Test(expected = Exception.class) 注解：设置被测试的方法是否有异常抛出。抛出异常类型为：Exception.class；
 * 	G、@Ignore 注解：注释掉一个测试方法或一个类，被注释的方法或类，不会被执行。
 * 
 */
public class Junit4Test{

	private static SqlSession session = null;
	
	@Before
	public void before(){
		
		SqlSessionFactory factory = SqlMapperManager.getFactory();
		if(factory == null) {
			System.out.println("get SqlSessionFatory failed");
			return;
		}
		
		session = factory.openSession();
		//session = factory.openSession(true);//自动提交事务
	}

	@After
	public void after(){
		
		session.commit();//提交事务
		session.close();
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("BeforeClass");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("AfterClass");
	}
	
	/**
	 * 查询
	 */
	@Test
	public void testSelect() {
		Blog blog = new Blog();
		blog = (Blog) session.selectOne("com.gavin.mybatis.mapper.blogMapper.selectBlog_by_id",1);
		this.printBlog(blog);
		
		HashMap<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("id", 1);
		
		blog = (Blog) session.selectOne("com.gavin.mybatis.mapper.blogMapper.selectBlog_by_id_Map",paramMap);
		this.printBlog(blog);
		
		Blog myBlog = new Blog();
		myBlog.setId(1);
		
		blog = (Blog) session.selectOne("com.gavin.mybatis.mapper.blogMapper.selectBlog_by_bean",myBlog);
		this.printBlog(blog);
	}
	
	/**
	 * 打印Blog
	 * @param blog
	 */
	public void printBlog(Blog blog) {
		if(blog != null) {
			System.out.println("ID:" + blog.getId());
			System.out.println("title:" + blog.getTitle());
			System.out.println("authorID:" + blog.getAuthorId());
		}else{
			System.out.println("blog is null");
		}
	}
	
}
