package com.gavin.mybatis.entity;

public class Comment {

	private Integer id;
	
	private Integer postId;
	
	private String name;
	
	private String comment;

	public Comment () {}
	
	public Comment(Integer id, Integer postId, String name, String comment) {
		this.id = id;
		this.postId = postId;
		this.name = name;
		this.comment = comment;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
