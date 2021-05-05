package model.business.user;

import java.sql.SQLException;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.ExceptionID;
import common.LogicException;
import common.utils.Utils;
import model.bo.UserBO;
import model.dao.UserDao;
import model.dao.daoentities.Tbluser;

public class UserBusinessImpl implements UserBusiness {

	private static final Logger logger = Logger.getLogger(UserBusinessImpl.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserDao userDao = (UserDao) context.getBean("userdao");

	@Override
	public UserBO getUser(String userName, String userPassword) throws LogicException {

		// creat list user object
		Tbluser tbluser = new Tbluser();
		try {
			// lay thong tin user
			tbluser = userDao.queryUser(userName, userPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// truong hop thong tin user khong ton tai.
		if (Objects.isNull(tbluser)) {
			Utils utils = new Utils();
			Exception e = utils.getLogicException(null, ExceptionID.EU0001);
			// thực hiện ghi log.
			logger.error("phát sinh NotFountException", e);

			throw new LogicException(ExceptionID.MU0001);
		}

		UserBO userBO = new UserBO();
		userBO = setValueUserBO(tbluser);

		return userBO;
	}

	@Override
	public UserBO getUserVerify(String userName, String userEmail) throws LogicException {

		// creat list user object
		Tbluser tbluser = new Tbluser();
		try {
			// lay thong tin user
			tbluser = userDao.queryUserVerify(userName, userEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// truong hop thong tin user khong ton tai.
		if (Objects.isNull(tbluser)) {
			Utils utils = new Utils();
			Exception e = utils.getLogicException(null, ExceptionID.EU0001);
			// thực hiện ghi log.
			logger.error("phát sinh NotFountException", e);

			throw new LogicException(ExceptionID.MU0002);
		}
		UserBO userBO = new UserBO();
		userBO = setValueUserBO(tbluser);

		return userBO;
	}

	@Override
	public int updateUser(UserBO userBO) throws LogicException {

		int updateFlag = 0;
		try {
			updateFlag = userDao.updateUser(userBO);
			 updateFlag = 1;
		} catch (SQLException e) {
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(e, ExceptionID.EU0001);
			// thực hiện ghi log.
			logger.error("phát sinh NotFountException", ex);
			
			throw new LogicException(ExceptionID.MU0003);
		}

		return updateFlag;
	}

	private static UserBO setValueUserBO(Tbluser tbluser) {

		UserBO userBO = new UserBO();
		userBO.setUserID(tbluser.getId().getUserId());
		userBO.setUserName(tbluser.getId().getUserName());
		userBO.setUserPassword(tbluser.getUserPassword());
		userBO.setUserCreateDate(tbluser.getUserCreateDate());
		userBO.setUserUpdateDate(tbluser.getUserUpdateDate());
		userBO.setDeleteFlg(tbluser.getDeleteFlag());
		userBO.setUserEmail(tbluser.getUserEmail());
		userBO.setUserVerifyCode(tbluser.getUserVerifyCode());
		userBO.setUserVerifyCodeCreateDate(tbluser.getUserVerifyCodeCreateDate());
		userBO.setUserVerifyCodeExpriedDate(tbluser.getUserVerifyCodeExpriedDate());

		return userBO;
	}
}
