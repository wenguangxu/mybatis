package com.gavin.mybatis.annotation;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author wenguang.xu
 * 
 */
public interface OneToOneMapper {

	/*使用嵌套select查询加载一对一关联（全注解）
	@Select("SELECT ADDR_ID AS ADDRID, STREET, CITY, STATE, ZIP, COUNTRY FROM ADDRESSES WHERE ADDR_ID=#{id}")
	Address findAddressById(int id);

	@Select("SELECT * FROM STUDENTS WHERE STUD_ID=#{studId} ")
	@Results({
			@Result(id = true, column = "stud_id", property = "studId"),
			@Result(column = "name", property = "name"),
			@Result(column = "email", property = "email"),
			@Result(property = "address", column = "addr_id", one = @One(select = "com.mybatis3.mappers.StudentMapper. findAddressById")) 
	})
	Student selectStudentWithAddress(int studId);
	*/
	
	
	/*<mapper namespace="com.mybatis3.mappers.StudentMapper"> 
		<resultMap type="Address" id="AddressResult"> 
			<id property="addrId" column="addr_id" /> 
			<result property="street" column="street" /> 
			<result property="city" column="city" /> 
			<result property="state" column="state" /> 
			<result property="zip" column="zip" /> 
			<result property="country" column="country" /> 
		</resultMap> 
		<resultMap type="Student" id="StudentWithAddressResult">
			<id property="studId" column="stud_id" /> 
			<result property="name" column="name" /> 
			<result property="email" column="email" /> 
			<association property="address" resultMap="AddressResult" />
		</resultMap> 
	</mapper>*/
	
	/*（半注解）
	@Select("select stud_id, name, email, a.addr_id, street, city, state, zip, country" + " FROM students s left outer join addresses a on s.addr_id=a.addr_id" + " where stud_id=#{studId} ") 
	@ResultMap("com.mybatis3.mappers.StudentMapper. StudentWithAddressResult") 
	Student selectStudentWithAddress(int id);*/
}
