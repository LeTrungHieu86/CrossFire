package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bo.ProductBO;
import model.dao.daoentities.Tblproduct;;

public interface ProductDao {
	
	public Tblproduct queryProductByKey(String productCode) throws SQLException;
	
	public List<Tblproduct> queryAllProduct() throws SQLException;
	
	public int insertProduct(ProductBO productBO) throws SQLException;
	
	public int updateProduct(ProductBO productBO) throws SQLException;
	
	public int deleteProduct(ProductBO productBO) throws SQLException;
}