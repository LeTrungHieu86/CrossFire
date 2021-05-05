package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import common.utils.MD5Utils;
import model.bo.UserBO;
import model.dao.daoentities.Tbluser;
import model.dao.daoentities.TbluserId;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Tbluser queryUser(String userName, String userPassword) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, ");
		sql.append("       USER_VERIFY_CODE, USER_VERIFY_CODE_CREATE_DATE, USER_VERIFY_CODE_EXPRIED_DATE, ");
		sql.append("       USER_CREATE_DATE, USER_UPDATE_DATE, DELETE_FLAG ");
		sql.append(" FROM TBLUSER ");
		sql.append(" WHERE USER_NAME = ? AND USER_PASSWORD = ? ");

		Object[] sqlParameter = { userName, MD5Utils.MD5Code(userPassword) };
		Tbluser tbluser = new Tbluser();
		try {
			tbluser = jdbcTemplate.queryForObject(sql.toString(), sqlParameter, new UserRowMapper());
		} catch (Exception e) {
			tbluser = null;
		}

		return tbluser;
	}

	@Override
	public Tbluser queryUserVerify(String userName, String userEmail) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, ");
		sql.append("       USER_VERIFY_CODE, USER_VERIFY_CODE_CREATE_DATE, USER_VERIFY_CODE_EXPRIED_DATE, ");
		sql.append("       USER_CREATE_DATE, USER_UPDATE_DATE, DELETE_FLAG ");
		sql.append(" FROM TBLUSER ");
		sql.append(" WHERE USER_NAME = ? AND USER_EMAIL = ? ");

		Object[] sqlParameter = { userName, userEmail };
		Tbluser tbluser = new Tbluser();
		try {
			tbluser = jdbcTemplate.queryForObject(sql.toString(), sqlParameter, new UserRowMapper());
		} catch (Exception e) {
			tbluser = null;
		}

		return tbluser;
	}

	@Override
	public int updateUser(UserBO userBO) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE TBLUSER SET ");
		sql.append("       USER_PASSWORD = ?, USER_CREATE_DATE = ?, USER_UPDATE_DATE = ?, ");
		sql.append("       DELETE_FLAG = ?, USER_EMAIL = ?, USER_VERIFY_CODE = ?, ");
		sql.append("       USER_VERIFY_CODE_CREATE_DATE = ?, USER_VERIFY_CODE_EXPRIED_DATE = ? ");
		sql.append("WHERE USER_NAME = ? ");

		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userBO.getUserPassword());
				ps.setString(2, userBO.getUserCreateDate());
				ps.setString(3, userBO.getUserUpdateDate());
				ps.setBoolean(4, userBO.isDeleteFlg());
				ps.setString(5, userBO.getUserEmail());
				ps.setString(6, userBO.getUserVerifyCode());
				ps.setString(7, userBO.getUserVerifyCodeCreateDate());
				ps.setString(8, userBO.getUserVerifyCodeExpriedDate());
				ps.setString(9, userBO.getUserName());
			}
		};

		return jdbcTemplate.update(sql.toString(), pss);
	}

	class UserRowMapper implements RowMapper<Tbluser> {

		@Override
		public Tbluser mapRow(ResultSet rs, int rowNum) throws SQLException {

			Tbluser tbluser = new Tbluser();
			TbluserId idTbluserId = new TbluserId();
			idTbluserId.setUserId(rs.getInt("USER_ID"));
			idTbluserId.setUserName(rs.getString("USER_NAME"));
			tbluser.setId(idTbluserId);
			tbluser.setUserPassword(rs.getString("USER_PASSWORD"));
			tbluser.setUserEmail(rs.getString("USER_EMAIL"));
			tbluser.setUserVerifyCode(rs.getString("USER_VERIFY_CODE"));
			tbluser.setUserVerifyCodeCreateDate(rs.getString("USER_VERIFY_CODE_CREATE_DATE"));
			tbluser.setUserVerifyCodeExpriedDate(rs.getString("USER_VERIFY_CODE_EXPRIED_DATE"));
			tbluser.setUserCreateDate(rs.getString("USER_CREATE_DATE"));
			tbluser.setUserUpdateDate(rs.getString("USER_UPDATE_DATE"));
			tbluser.setDeleteFlag(rs.getBoolean("DELETE_FLAG"));
			return tbluser;
		}
	}
}
