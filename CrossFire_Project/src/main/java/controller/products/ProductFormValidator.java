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
		
		ProductBO productBO = (ProductBO) target;
		// validate productTile
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productTitle","valid.productTile");
		if(productBO.getProductTitle().length() > 25) {
			
			errors.rejectValue("productTitle","valid.productTileLength");
		}
		
		// validate product Code
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productCode", "valid.productCode");
		if(productBO.getProductCode().length() > 10) {
			errors.rejectValue("productCode","valid.productCodeLength");
		}
		
		// validate productInfo
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productInfo", "valid.productInfo");
		if(productBO.getProductInfo().length() > 200) {
			errors.rejectValue("productInfo","valid.productInfoLength");
		}
		
		
		
	}
}
