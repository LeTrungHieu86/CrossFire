package controller.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import model.bo.UserBO;

public class UserFormLoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserBO user = (UserBO) target;

		// validate user name
		if (user.getUserName().isEmpty()) {
			ValidationUtils.rejectIfEmpty(errors, "userName", null, "Vui lòng nhập tên tài khoản.");
			return;
		} else {
			if (user.getUserName().length() < 5 || user.getUserName().length() > 15) {
				errors.rejectValue("userName", null,
						"Vui lòng nhập tên tài khoản.Độ dài Tên tài khoản từ 5 đến 15 ký tự.");
				return;
			}
		}

		// validate password
		if (user.getUserPassword().isEmpty()) {
			ValidationUtils.rejectIfEmpty(errors, "userPassword", null, "Vui lòng nhập mật khẩu.");
			return;
		} else {
			if (user.getUserPassword().length() < 5 || user.getUserPassword().length() > 15) {
				errors.rejectValue("userPassword", null, "Vui lòng nhập mật khẩu.Độ dài mật khẩu từ 5 đến 15 ký tự.");
				return;
			}
		}
	}
}
