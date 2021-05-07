package controller.products;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import model.bo.ProductBO;

@Component
public class ProductFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductBO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ProductBO productBO = (ProductBO)target;
		
		// validate 
		if(productBO.getProductTitle().isEmpty()) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productTitle","Vui lòng nhập loại tài khoản.");
			errors.rejectValue("productTitle", "Vui lòng nhập loại tài khoản.");
		}
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productCode", "Vui lòng nhập mã tài khoản.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productInfo", "Vui lòng nhập thông tin tài khoản.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice", "Vui lòng nhập giá tài khoản.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productImage", "Vui lòng chọn ảnh tài khoản.");
	}
}
