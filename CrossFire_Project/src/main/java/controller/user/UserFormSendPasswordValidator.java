package controller.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import model.bo.UserBO;

public class UserFormSendPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserBO user = (UserBO) target;

		// validate user name
		if (user.getUserVerifyCode().isEmpty()) {
			ValidationUtils.rejectIfEmpty(errors, "userVerifyCode", null, "Vui lòng nhập mã xác thực");
			return;
		} else {
			if (user.getUserVerifyCode().length() != 6) {
				errors.rejectValue("userVerifyCode", null,
						"Vui lòng nhập lại mã xác thực.Độ dài mã xác thực phải bằng 6 ký tự");
				return;
			}
		}
	}

}
