package com.gavin.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.gavin.mybatis.entity.Author;

/**
 * 
 * @author wenguang.xu
 *
 */
public class PhoneTypeHandler extends BaseTypeHandler<Author>{

	@Override
	public Author getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		//从数据集中取出在数据库中对应类型的数据，然后构造成新的类型(Author)
		//return new Author(rs.getString(columnName));
		return null;
	}

	@Override
	public Author getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		//从数据集中取出在数据库中对应类型的数据，然后构造成新的类型(Author)
		//return new Author(rs.getString(columnIndex));
		return null;
	}

	@Override
	public Author getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		//从数据集中取出在数据库中对应类型的数据，然后构造成新的类型(Author)
		//return new Author(cs.getString(columnIndex));
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Author parameter, JdbcType jdbcType) throws SQLException {
		//根据parameter设置sql中参数类型
		ps.setString(i, parameter.toString());
	}

}
