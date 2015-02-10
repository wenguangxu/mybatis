package com.gavin.mybatis.entity;
/**
 * 接口映射类
 * @author Administrator
 *
 */
public interface BlogMapper {
	
	//note：方法名与sql语句的id一致,接口的全限定名要与mapper文件的namespace一致
	public Blog selectBlog_by_id(int id);
	
	public Blog selectBlog_by_bean(Blog blog);
	
	public void updateBlog_use_bean(Blog blog);
	
	public void insertBlog_use_bean(Blog blog);
	
	public void insertBlog_use_autokey(Blog blog);
	
}
