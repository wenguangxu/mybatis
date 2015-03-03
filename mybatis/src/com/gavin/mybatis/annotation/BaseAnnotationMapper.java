package com.gavin.mybatis.annotation;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author wenguang.xu
 *
 */
public interface BaseAnnotationMapper {
	
	/*插入
	@Insert("insert into students(stud_id,name,email,addr_id,phone)"
			+ "values(#{studId},#{name},#{email},#{address.addrId},#{phone})")
	int insertStudent(Student student);
	*/
	
	/*自动生成主键
	 * 使用@Options注解的 useGeneratedKeys 和 keyProperty 属性让数据库产生 auto_increment(自增长)列的值，然后将生成的值设置到输入参数对象的属性中。（oracle不支持）
	@Insert("insert into students(name,email,addr_id,phone)"
			+ "values(#{name},#{email},#{address.addrId},#{phone})")
	@Options(useGeneratedKeys = true,keyProperty = "studId")
	int insertStudent(Student student);
	*/
	
	/*使用序列生成主键(oracle)
	@Insert("insert into students(stud_id,name,email,addr_id,phone)"
			+ "values(#{studId},#{name},#{email},#{address.addrId},#{phone})")
	@SelectKey(statement = "select stud_id_seq.nextval from dual",keyProperty = "studId",resultType = int.class,before = true)
	int insertStudent(Student student);
	*/
	
	/*使用序列作为触发器来设置主键值(oracle)
	 * 在insert语句执行后，从sequence_name.currval获取数据库产生的主键值
	@Insert("insert into students(name,email,addr_id,phone)"
			+ "values(#{name},#{email},#{address.addrId},#{phone})")
	@SelectKey(statement = "select stud_id_seq.currval from dual",keyProperty = "studId",resultType = int.class,before = false)
	int insertStudent(Student student);
	*/
	
	/*更新
	@Update("update students set name=#{name},email=#{email},phone=#{phone} where stud_id = #{studId}")
	int updateStudent(Student student);
	*/
	
	/*删除
	@Delete("DELETE FROM STUDENTS WHERE STUD_ID=#{studId}")
	int deleteStudent(int studId);
	*/
	
	/*Select单条记录
	@Select("SELECT STUD_ID AS STUDID, NAME, EMAIL, PHONE FROM STUDENTS WHERE STUD_ID=#{studId}")
	Student findStudentById(Integer studId);
	*/
	
	/*结果映射
	@Select("SELECT * FROM STUDENTS")
	@Results( 
	{ 
		@Result(id = true, column = "stud_id", property = "studId"), 
		@Result(column = "name", property = "name"), 
		@Result(column = "email", property = "email"), 
		@Result(column = "addr_id", property = "address.addrId") 
	})
	List<Student> findAllStudents();
	*/
	
	/*结果映射（引用xml中的resultMap）
	@Select("SELECT * FROM STUDENTS WHERE STUD_ID=#{studId}")
	@ResultMap("com.mybatis3.mappers.StudentMapper.StudentResult")
	Student findStudentById(int studId);
	
	@Select("SELECT * FROM STUDENTS")
	@ResultMap("com.mybatis3.mappers.StudentMapper.StudentResult")
	List<Student> findAllStudents();*/
}
