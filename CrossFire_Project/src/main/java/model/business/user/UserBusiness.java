package model.business.user;

import common.LogicException;
import model.bo.UserBO;

public interface UserBusiness {
	
	public UserBO getUser(String userName, String userPassword) throws LogicException;
	
	public UserBO getUserVerify(String userName, String userEmail) throws LogicException;
	
	public int updateUser(UserBO userBO) throws LogicException;

}
