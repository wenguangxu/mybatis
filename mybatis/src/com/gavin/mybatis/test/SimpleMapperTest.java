package com.gavin.mybatis.test;

import java.util.HashMap;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.gavin.mybatis.entity.Blog;
import com.gavin.mybatis.entity.BlogMapper;
import com.gavin.mybatis.entity.Comment;
import com.gavin.mybatis.entity.Post;
import com.gavin.mybatis.util.SqlMapperManager;

/**
 * 注意：JUnit推荐的做法是以test作为待测试的方法的开头，这样这些方法可以被自动找到并被测试。
 * 
 * junit3.x
 * 
 * 我们通常使用junit 3.8
 * (1)、使用junit3.x版本进行单元测试时，测试类必须要继承于TestCase父类；
 * (2)、测试方法需要遵循的原则：
 * 	A、public的
 * 	B、void的
 * 	C、无方法参数
 * 	D、方法名称必须以test开头
 * (3)、不同的Test Case之间一定要保持完全的独立性，不能有任何的关联。
 * (4)、我们要掌握好测试方法的顺序，不能依赖于测试方法自己的执行顺序。
 * 
 * 在实际的测试中我们测试某个类的功能是常常需要执行一些共同的操作，完成以后需要销毁所占用的资源（例如网络连接、数据库连接，关闭打开的文件等），
 * TestCase类给我们提供了setUp方法和tearDown方法，setUp方法的内容在测试你编写的TestCase子类的每个testXxxx方法之前都会运行，
 * 而tearDown方法的内容在每个testXxxx方法结束以后都会执行。这个既共享了初始化代码，又消除了各个测试代码之间可能产生的相互影响。
 * 
 */
public class SimpleMapperTest extends TestCase{

	private static SqlSession session = null;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		SqlSessionFactory factory = SqlMapperManager.getFactory();
		if(factory == null) {
			System.out.println("get SqlSessionFatory failed");
			return;
		}
		
		session = factory.openSession();
		//session = factory.openSession(true);//自动提交事务
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		session.commit();//提交事务
		session.close();
	}
	
	/**
	 * 查询
	 */
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
		this.printPost(p);
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
