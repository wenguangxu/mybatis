package com.gavin.mybatis.annotation;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.gavin.mybatis.entity.Tutor;

/**
 * 
 * @author wenguang.xu
 *
 */
public interface DynaSqlMapper {

	/**
	 * 简单sql
	 * 
	 * @param tutorId
	 * @return
	 */
	@SelectProvider(type = DynaSqlProvider.class, method = "findTutorByIdSql")
	Tutor findTutorById(int tutorId);

	/**
	 * 多个参数使用map
	 * 
	 * @param name
	 * @param email
	 * @return
	 */
	@SelectProvider(type = DynaSqlProvider.class, method = "findTutorByNameAndEmailSql")
	Tutor findTutorByNameAndEmail(String name, String email);

	/**
	 * 插入
	 * 
	 * @param tutor
	 * @return
	 */
	@InsertProvider(type = DynaSqlProvider.class, method = "insertTutor")
	@Options(useGeneratedKeys = true, keyProperty = "tutorId")
	int insertTutor(Tutor tutor);

	/**
	 * 更新
	 * 
	 * @param tutor
	 * @return
	 */
	@UpdateProvider(type = DynaSqlProvider.class, method = "updateTutor")
	int updateTutor(Tutor tutor);

	/**
	 * 删除
	 * @param tutorId
	 * @return
	 */
	@DeleteProvider(type = DynaSqlProvider.class, method = "deleteTutor")
	int deleteTutor(int tutorId);
}
