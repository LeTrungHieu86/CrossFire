package model.business.products;

import java.util.List;

import common.LogicException;
import model.bo.ProductBO;

public interface ProductBusiness {
	
	public ProductBO getProductBykey(String productCode, int productImageId) throws LogicException;
	
	public List<ProductBO> getProductByCode(String productCode) throws LogicException;
	
	public List<ProductBO> getAllProduct() throws LogicException;
	
	public int insertProduct(ProductBO productBO) throws LogicException;
	
	public int updateProduct(ProductBO productBO) throws LogicException;
	
	public int deleteProductByKey(String productCode, int productImageId) throws LogicException;
	
	public int deleteProductByCode(String productCode) throws LogicException;

}
