package com.gavin.mybatis.test;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import ch.qos.logback.classic.BasicConfigurator;

import com.gavin.mybatis.entity.Blog;
import com.gavin.mybatis.entity.BlogMapper;
import com.gavin.mybatis.entity.Comment;
import com.gavin.mybatis.entity.Post;
import com.gavin.mybatis.util.SqlMapperManager;

public class SimpleMapperTest {

	private static SimpleMapperTest test = new SimpleMapperTest();
	private static SqlSession session = null;
	
	
	public static void main(String[] args) {
		
		
		try {
			SqlSessionFactory factory = SqlMapperManager.getFactory();
			if(factory == null) {
				System.out.println("get SqlSessionFatory failed");
				return;
			}
			
			session = factory.openSession();
			//session = factory.openSession(true);//自动提交事务
			
			//查询
			//test.testSelect();
			
			//更新
			//test.testUpdate();
			
			//插入
			//test.testInsert();
			
			//删除
			//test.testDelete();
			
			//插入测试自动生成主键
			//test.testInsertSequence();
			
			//处理NULL值
			//test.testInsertNull();
			
			//测试接口映射类
			//test.testInterfaceMapper();
			
			//测试resultMap的construct
			//test.testConstructor();
			
			//测试association关联
			test.testAssociation();
			
			session.commit();//提交事务
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	/**
	 * 查询
	 */
	public void testSelect() {
		Blog blog = new Blog();
		blog = (Blog) session.selectOne("com.gavin.mybatis.mapper.blogMapper.selectBlog_by_id",1);
		test.printBlog(blog);
		
		HashMap<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("id", 1);
		
		blog = (Blog) session.selectOne("com.gavin.mybatis.mapper.blogMapper.selectBlog_by_id_Map",paramMap);
		test.printBlog(blog);
		
		Blog myBlog = new Blog();
		myBlog.setId(1);
		
		blog = (Blog) session.selectOne("com.gavin.mybatis.mapper.blogMapper.selectBlog_by_bean",myBlog);
		test.printBlog(blog);
	}
	
	/**
	 * 更新
	 */
	public void testUpdate() {
		Blog myBlog = new Blog();
		myBlog.setId(2);
		myBlog.setTitle("hahaa");
		myBlog.setAuthorId(88);
		int res = session.update("updateBlog_use_bean",myBlog); //res : 更新到数据为 1，没更新到数据为 0 
		System.out.println(res);
	}
	
	/**
	 * 插入
	 */
	public void testInsert() {
		Blog myBlog = new Blog();
		myBlog.setTitle("vv");
		myBlog.setAuthorId(9);
		int res = session.insert("insertBlog_use_bean",myBlog); //res : 插入成功为 1
		System.out.println(res);
	}
	
	/**
	 * 插入测试自动生成主键
	 */
	public void testInsertSequence() {
		Blog myBlog = new Blog();
		myBlog.setTitle("mike");
		myBlog.setAuthorId(67);
		session.insert("insertBlog_use_bean",myBlog);
		session.insert("insertBlog_use_bean",myBlog);
		session.insert("insertBlog_use_bean",myBlog);
	}
	
	/**
	 * 处理NULL值
	 */
	public void testInsertNull() {
		Blog myBlog = new Blog();
		myBlog.setTitle(null);
		myBlog.setAuthorId(67);
		session.insert("insertBlog_use_bean",myBlog);
	}
	
	/**
	 * 删除
	 */
	public void testDelete() {
		Blog myBlog = new Blog();
		myBlog.setId(3);
		int res = session.update("deleteBlog_use_bean",myBlog); //res : 删除成功为  1 , 未删到数据为 0
		System.out.println(res);
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
	
	public void printComment(Comment com) {
		if(com != null) {
			System.out.println("id:" + com.getId());
			System.out.println("comment:" + com.getComment());
			System.out.println("name:" + com.getName());
			System.out.println("postId :" + com.getPostId());
		}else{
			System.out.println("comment is null");
		}
	}
	/**
	 * 测试接口映射类
	 */
	public void testInterfaceMapper() {
		Blog blog = new Blog();
		blog.setId(1);
		blog.setTitle("hello");
		blog.setAuthorId(9);
		
		BlogMapper blogMapper = session.getMapper(BlogMapper.class);
		Blog blog1 = blogMapper.selectBlog_by_id(1);
		printBlog(blog1);
		
		blogMapper.updateBlog_use_bean(blog);
		Blog blog2 = blogMapper.selectBlog_by_id(1);
		printBlog(blog2);
	}

	/**
	 * 测试resultMap的construct
	 */
	public void testConstructor() {
		Comment com = session.selectOne("selectComment_by_id",1);
		printComment(com);
	}
	
	/**
	 * 测试association级联
	 */
	public void testAssociation() {
		Post p = (Post)session.selectOne("selectPost_use_association",1);
		test.printPost(p);
	}
	
	public void printPost(Post p) {
		System.out.println("postId:" + p.getId());
		System.out.println("createdOn:" + p.getCreatedOn());
		System.out.println("body:" + p.getBody());
		System.out.println("section:" + p.getSection());
		System.out.println("subject:" + p.getSubject());
		System.out.println("blog title:" + p.getBlog().getTitle());
		System.out.println("blog authorId:" + p.getBlog().getAuthorId());
		System.out.println("blog id:" + p.getBlog().getId());
		System.out.println("author bio:" + p.getAuthor().getBio());
		System.out.println("author email:" + p.getAuthor().getEmail());
		System.out.println("author password:" + p.getAuthor().getPassword());
		System.out.println("author username" + p.getAuthor().getUsername());
		System.out.println("author id:" + p.getAuthor().getId());
	}
}
