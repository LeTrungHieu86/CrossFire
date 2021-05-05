package model.dao;

import java.sql.SQLException;

import model.bo.UserBO;
import model.dao.daoentities.Tbluser;

public interface UserDao {

	// select User
	public Tbluser queryUser(String userName, String userPassword) throws SQLException;
	
	// select UserVerify
	public Tbluser queryUserVerify(String userName, String userEmail) throws SQLException;
	
	// update user
	public int updateUser(UserBO userBO) throws SQLException;

}
