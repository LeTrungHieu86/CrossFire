package controller.user;


import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import model.bo.UserBO;

public interface UserController {

	// login form
	public String LoginForm();

	// forgot password
	public String UserForgotPassword();

	public String SendPassword();

	// login
	public String UserLogin(UserBO userBO, BindingResult result, ModelMap model);

	// logout
	public String UserLogout(UserBO userBO);

}
