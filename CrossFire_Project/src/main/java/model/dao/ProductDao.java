package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bo.ProductBO;
import model.dao.daoentities.Tblproduct;;

public interface ProductDao {
	
	public List<Tblproduct> queryProductByCode(String productCode) throws SQLException;
	
	public Tblproduct queryProductByKey(String productCode, int productImageId) throws SQLException;
	
	public List<Tblproduct> queryAllProduct() throws SQLException;
	
	public int insertProduct(ProductBO productBO) throws SQLException;
	
	public int updateProduct(ProductBO productBO) throws SQLException;
	
	public int deleteProductByKey(String productCode, int productImageId) throws SQLException;
	
	public int deleteProductByCode(String productCode) throws SQLException;
}