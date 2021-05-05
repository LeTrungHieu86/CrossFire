package controller.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import common.utils.Utils;
import model.bo.UserBO;

public class UserFormSendVeirifyCodeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserBO user = (UserBO) target;

		// validate user name
		if (user.getUserName().isEmpty()) {
			ValidationUtils.rejectIfEmpty(errors, "userName", null, "Vui lòng nhập tên tài khoản");
			return;
		} else {
			if (user.getUserName().length() < 5 || user.getUserName().length() > 15) {
				errors.rejectValue("userName", null,
						"Vui lòng nhập lại tên tài khoản.Độ dài Tên tài khoản từ 5 đến 15 ký tự");
				return;
			}
		}
		
		// validate user Email
		if (user.getUserEmail().isEmpty()) {
			ValidationUtils.rejectIfEmpty(errors, "userEmail", null, "Vui lòng nhập email");
			return;
		} else {
			if (!Utils.isValidEmail(user.getUserEmail())) {
				errors.rejectValue("userEmail", null, "Nhập Sai định dạng định dạng email.Xin vui lòng nhập lại");
				return;
			}
			
			if(user.getUserEmail().length() < 11 || user.getUserEmail().length() > 45) {
				errors.rejectValue("userEmail", null, "Vui lòng nhập lại email.Độ dài email từ 11 đến 45 ký tự");
				return;
			}
		}
	}

}
