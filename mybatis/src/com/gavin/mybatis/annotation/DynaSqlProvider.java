package com.gavin.mybatis.annotation;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.gavin.mybatis.entity.Tutor;

/**
 * 
 * @author wenguang.xu
 *
 */
public class DynaSqlProvider {

	/**
	 * 简单sql
	 * 
	 * @param tutorId
	 * @return
	 */
	/*
	 * public String findTutorByIdSql(int tutorId) { return
	 * "SELECT TUTOR_ID AS tutorId, NAME, EMAIL FROM TUTORS WHERE TUTOR_ID=" +
	 * tutorId; }
	 */

	/**
	 * 使用工具类拼接sql
	 * 
	 * @param tutorId
	 * @return
	 */
	public String findTutorByIdSql(final int tutorId) {
		return new SQL() {
			{
				SELECT("tutor_id as tutorId, name, email");
				FROM("tutors");
				WHERE("tutor_id=" + tutorId);
			}
		}.toString();
	}

	/**
	 * 多个参数使用map
	 * 
	 * @param map
	 * @return
	 */
	public String findTutorByNameAndEmailSql(Map<String, Object> map) {
		String name = (String) map.get("param1");
		String email = (String) map.get("param2");
		// you can also get those values using 0,1 keys
		// String name = (String) map.get("0");
		// String email = (String) map.get("1");
		return new SQL() {
			{
				SELECT("tutor_id as tutorId, name, email");
				FROM("tutors");
				WHERE("name=#{name} AND email=#{email}");
			}
		}.toString();
	}

	/**
	 * 插入
	 * 
	 * @param tutor
	 * @return
	 */
	public String insertTutor(final Tutor tutor) {
		return new SQL() {
			{
				INSERT_INTO("TUTORS");
				if (tutor.getName() != null) {
					VALUES("NAME", "#{name}");
				}
				if (tutor.getEmail() != null) {
					VALUES("EMAIL", "#{email}");
				}
			}
		}.toString();
	}

	/**
	 * 更新
	 * 
	 * @param tutor
	 * @return
	 */
	public String updateTutor(final Tutor tutor) {
		return new SQL() {
			{
				UPDATE("TUTORS");
				if (tutor.getName() != null) {
					SET("NAME = #{name}");
				}
				if (tutor.getEmail() != null) {
					SET("EMAIL = #{email}");
				}
				WHERE("TUTOR_ID = #{tutorId}");
			}
		}.toString();
	}

	/**
	 * 删除
	 * @param tutorId
	 * @return
	 */
	public String deleteTutor(int tutorId) {
		return new SQL() {
			{
				DELETE_FROM("TUTORS");
				WHERE("TUTOR_ID = #{tutorId}");
			}
		}.toString();
	}
}
