package com.gavin.mybatis.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.gavin.mybatis.entity.Course;
import com.gavin.mybatis.entity.Tutor;

/**
 * 
 * @author wenguang.xu
 * 
 */
public interface OneToManyMapper {

	/*（全注解）
	@Select("select addr_id as addrId, street, city, state, zip, country from addresses where addr_id=#{id}") 
	Address findAddressById(int id); 
	@Select("select * from courses where tutor_id=#{tutorId}") 
	@Results( { 
		@Result(id = true, column = "course_id", property = "courseId"),
		@Result(column = "name", property = "name"),
		@Result(column = "description", property = "description"),
		@Result(column = "start_date" property = "startDate"),
		@Result(column = "end_date" property = "endDate") 
	}) 
	List<Course> findCoursesByTutorId(int tutorId); 
	@Select("SELECT tutor_id, name as tutor_name, email, addr_id FROM tutors where tutor_id=#{tutorId}") 
	@Results( { 
		@Result(id = true, column = "tutor_id", property = "tutorId"), 
		@Result(column = "tutor_name", property = "name"), 
		@Result(column = "email", property = "email"), 
		@Result(property = "address", column = "addr_id", 
		one = @One(select = " com.mybatis3. mappers.TutorMapper.findAddressById")), 
		@Result(property = "courses", column = "tutor_id", 
		many = @Many(select = "com.mybatis3.mappers.TutorMapper. findCoursesByTutorId"))
	}) 
	Tutor findTutorById(int tutorId);*/
	
	
	
	/*<mapper namespace="com.mybatis3.mappers.TutorMapper"> 
		<resultMap type="Address" id="AddressResult"> 
			<id property="addrId" column="addr_id" /> 
			<result property="street" column="street" /> 
			<result property="city" column="city" /> 
			<result property="state" column="state" /> 
			<result property="zip" column="zip" /> 
			<result property="country" column="country" /> 
		</resultMap> 
		<resultMap type="Course" id="CourseResult"> 
			<id column="course_id" property="courseId" /> 
			<result column="name" property="name" /> 
			<result column="description" property="description" />
			<result column="start_date" property="startDate" />
			<result column="end_date" property="endDate" /> 
			</resultMap> <resultMap type="Tutor" id="TutorResult"> 
			<id column="tutor_id" property="tutorId" /> 
			<result column="tutor_name" property="name" /> 
			<result column="email" property="email" /> 
			<association property="address" resultMap="AddressResult" />
			<collection property="courses" resultMap="CourseResult" /> 
		</resultMap>
	</mapper>*/
	
	/*（半注解）
	@Select("SELECT T.TUTOR_ID, T.NAME AS TUTOR_NAME, EMAIL, A.ADDR_ID, STREET, CITY, STATE, ZIP, COUNTRY, COURSE_ID, C.NAME, DESCRIPTION, START_DATE, END_DATE FROM TUTORS T LEFT OUTER JOIN ADDRESSES A ON T.ADDR_ID=A.ADDR_ID LEFT OUTER JOIN COURSES C ON T.TUTOR_ID=C.TUTOR_ID WHERE T.TUTOR_ID=#{tutorId}") 
	@ResultMap("com.mybatis3.mappers.TutorMapper.TutorResult") 
	Tutor selectTutorById(int tutorId);*/
}
