package model.business.products;

import java.util.List;

import common.LogicException;
import model.bo.ProductBO;

public interface ProductBusiness {
	
	public ProductBO getProductBykey(String productCode) throws LogicException;
	
	public List<ProductBO> getAllProduct() throws LogicException;
	
	public int insertProduct(ProductBO productBO) throws LogicException;
	
	public int updateProduct(ProductBO productBO) throws LogicException;
	
	public int deleteProduct(ProductBO productBO) throws LogicException;

}
