package model.bo;

public class UserBO {

	private int userID;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userVerifyCode;
	private String userVerifyCodeCreateDate;
	private String userVerifyCodeExpriedDate;
	private String userCreateDate;
	private String userUpdateDate;
	private boolean deleteFlg;
	
	public UserBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBO(int userID, String userName, String userPassword, String userEmail, String userVerifyCode,
			String userVerifyCodeCreateDate, String userVerifyCodeExpriedDate, String userCreateDate, String userUpdateDate,
			boolean deleteFlg) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userVerifyCode = userVerifyCode;
		this.userVerifyCodeCreateDate = userVerifyCodeCreateDate;
		this.userVerifyCodeExpriedDate = userVerifyCodeExpriedDate;
		this.userCreateDate = userCreateDate;
		this.userUpdateDate = userUpdateDate;
		this.deleteFlg = deleteFlg;
	}

	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserVerifyCode() {
		return userVerifyCode;
	}


	public void setUserVerifyCode(String userVerifyCode) {
		this.userVerifyCode = userVerifyCode;
	}


	public String getUserVerifyCodeCreateDate() {
		return userVerifyCodeCreateDate;
	}


	public void setUserVerifyCodeCreateDate(String userVerifyCodeCreateDate) {
		this.userVerifyCodeCreateDate = userVerifyCodeCreateDate;
	}


	public String getUserVerifyCodeExpriedDate() {
		return userVerifyCodeExpriedDate;
	}


	public void setUserVerifyCodeExpriedDate(String userVerifyCodeExpriedDate) {
		this.userVerifyCodeExpriedDate = userVerifyCodeExpriedDate;
	}


	public String getUserCreateDate() {
		return userCreateDate;
	}


	public void setUserCreateDate(String userCreateDate) {
		this.userCreateDate = userCreateDate;
	}


	public String getUserUpdateDate() {
		return userUpdateDate;
	}


	public void setUserUpdateDate(String userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
	}


	public boolean isDeleteFlg() {
		return deleteFlg;
	}


	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
