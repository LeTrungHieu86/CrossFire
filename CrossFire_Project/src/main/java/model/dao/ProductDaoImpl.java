package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import model.bo.ProductBO;
import model.dao.ProductDao;
import model.dao.daoentities.Tblproduct;
import model.dao.daoentities.TblproductId;

public class ProductDaoImpl implements ProductDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Tblproduct> queryAllProduct() throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT PRODUCT_CODE, PRODUCT_TITLE, PRODUCT_VIP_INGAME_LEVEL, ");
		sql.append("       PRODUCT_VIP_NUMBER, PRODUCT_IMAGE_ID, PRODUCT_INFO, ");
		sql.append("       PRODUCT_VIP_INGAME_IMAGE, PRODUCT_IMAGE, PRODUCT_PRICE, ");
		sql.append("       PRODUCT_USER_ADD, PRODUCT_USER_UPDATE, PRODUCT_CREATE_DATE, ");
		sql.append("       PRODUCT_UPDATE_DATE, PRODUCT_DELETE_FLAG , COUNT(PRODUCT_CODE) AS COUNT ");
		sql.append(" FROM TBLPRODUCT ");
		sql.append(" GROUP BY PRODUCT_CODE ");
		List<Tblproduct> tblProduct = new ArrayList<Tblproduct>();
		try {
			tblProduct = jdbcTemplate.query(sql.toString(), new ProductRowMapperGetAll());
		} catch (Exception e) {
			tblProduct = null;
		}
		return tblProduct;
	}

	@Override
	public Tblproduct queryProductByKey(String productCode, int productImageId) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT PRODUCT_CODE, PRODUCT_TITLE, PRODUCT_VIP_INGAME_LEVEL, ");
		sql.append("       PRODUCT_VIP_NUMBER, PRODUCT_IMAGE_ID, PRODUCT_INFO, ");
		sql.append("       PRODUCT_VIP_INGAME_IMAGE, PRODUCT_IMAGE, PRODUCT_PRICE, ");
		sql.append("       PRODUCT_USER_ADD, PRODUCT_USER_UPDATE, PRODUCT_CREATE_DATE, ");
		sql.append("       PRODUCT_UPDATE_DATE, PRODUCT_DELETE_FLAG ");
		sql.append(" FROM TBLPRODUCT ");
		sql.append(" WHERE PRODUCT_CODE = ?, PRODUCT_IMAGE_ID = ? ");

		Object[] sqlParameter = { productCode, productImageId };
		Tblproduct tblProduct = new Tblproduct();
		try {
			tblProduct = (Tblproduct) jdbcTemplate.query(sql.toString(), sqlParameter, new ProductRowMapper());
		} catch (Exception e) {
			tblProduct = null;
		}
		return tblProduct;
	}

	@Override
	public List<Tblproduct> queryProductByCode(String productCode) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT PRODUCT_CODE, PRODUCT_TITLE, PRODUCT_VIP_INGAME_LEVEL, ");
		sql.append("       PRODUCT_VIP_NUMBER, PRODUCT_IMAGE_ID, PRODUCT_INFO, ");
		sql.append("       PRODUCT_VIP_INGAME_IMAGE, PRODUCT_IMAGE, PRODUCT_PRICE, ");
		sql.append("       PRODUCT_USER_ADD, PRODUCT_USER_UPDATE, PRODUCT_CREATE_DATE, ");
		sql.append("       PRODUCT_UPDATE_DATE, PRODUCT_DELETE_FLAG ");
		sql.append(" FROM TBLPRODUCT ");
		sql.append(" WHERE PRODUCT_CODE = ?");

		Object[] sqlParameter = { productCode };
		List<Tblproduct> tblProduct = new ArrayList<Tblproduct>();
		try {
			tblProduct = jdbcTemplate.query(sql.toString(), sqlParameter, new ProductRowMapper());
		} catch (Exception e) {
			tblProduct = null;
		}
		return tblProduct;
	}

	@Override
	public int insertProduct(ProductBO productBO) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBLPRODUCT( ");
		sql.append("       PRODUCT_CODE, PRODUCT_TITLE, PRODUCT_VIP_INGAME_LEVEL, ");
		sql.append("       PRODUCT_VIP_NUMBER, PRODUCT_IMAGE_ID, PRODUCT_INFO, ");
		sql.append("       PRODUCT_VIP_INGAME_IMAGE, PRODUCT_IMAGE, PRODUCT_PRICE, ");
		sql.append("       PRODUCT_USER_ADD, PRODUCT_USER_UPDATE, PRODUCT_CREATE_DATE, ");
		sql.append("       PRODUCT_UPDATE_DATE, PRODUCT_DELETE_FLAG ");
		sql.append(" ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, productBO.getProductCode());
				ps.setString(2, productBO.getProductTitle());
				ps.setInt(3, productBO.getProductVipIngameLevel());
				ps.setInt(4, productBO.getProductVipNumber());
				ps.setInt(5, productBO.getProductImageId());
				ps.setString(6, productBO.getProductInfo());
				ps.setString(7, productBO.getProductVipIngameImage());
				ps.setString(8, productBO.getProductImage());
				ps.setInt(9, productBO.getProductPrice());
				ps.setString(10, productBO.getProductUserAdd());
				ps.setString(11, productBO.getProductUserUpdate());
				ps.setString(12, productBO.getProductCreateDate());
				ps.setString(13, productBO.getProductUpdateDate());
				ps.setBoolean(14, false);
			}
		};

		return jdbcTemplate.update(sql.toString(), pss);
	}

	@Override
	public int updateProduct(ProductBO productBO) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE TBLPRODUCT SET ");
		sql.append("       PRODUCT_TITLE = ?, PRODUCT_VIP_INGAME_LEVEL = ?, PRODUCT_VIP_NUMBER = ?, ");
		sql.append("       PRODUCT_INFO = ?, PRODUCT_VIP_INGAME_IMAGE = ?, PRODUCT_IMAGE = ?, ");
		sql.append("       PRODUCT_PRICE = ?, PRODUCT_USER_ADD = ?, PRODUCT_USER_UPDATE = ?, ");
		sql.append("       PRODUCT_CREATE_DATE = ?, PRODUCT_UPDATE_DATE = ?, PRODUCT_DELETE_FLAG = ? ");
		sql.append(" WHERE PRODUCT_CODE = ? AND PRODUCT_IMAGE_ID = ?");

		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, productBO.getProductTitle());
				ps.setInt(2, productBO.getProductVipIngameLevel());
				ps.setInt(3, productBO.getProductVipNumber());
				ps.setString(4, productBO.getProductInfo());
				ps.setString(5, productBO.getProductVipIngameImage());
				ps.setString(6, productBO.getProductImage());
				ps.setInt(7, productBO.getProductPrice());
				ps.setString(8, productBO.getProductUserAdd());
				ps.setString(9, productBO.getProductUserUpdate());
				ps.setString(10, productBO.getProductCreateDate());
				ps.setString(11, productBO.getProductUpdateDate());
				ps.setBoolean(12, productBO.isDeleteFlg());
				ps.setString(13, productBO.getProductCode());
				ps.setInt(14, productBO.getProductImageId());
			}
		};

		return jdbcTemplate.update(sql.toString(), pss);
	}

	@Override
	public int deleteProductByKey(String productCode, int productImageId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TBLPRODUCT ");
		sql.append("       WHERE PRODUCT_CODE = ? AND PRODUCT_IMAGE_ID = ? ");

		Object[] sqlParameter = { productCode, productImageId };
		int[] argType = { Types.VARCHAR, Types.INTEGER };

		return jdbcTemplate.update(sql.toString(), sqlParameter, argType);
	}

	@Override
	public int deleteProductByCode(String productCode) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TBLPRODUCT ");
		sql.append("       WHERE PRODUCT_CODE = ? ");

		Object[] sqlParameter = { productCode };
		int[] argType = { Types.VARCHAR};

		return jdbcTemplate.update(sql.toString(), sqlParameter, argType);
	}

	class ProductRowMapperGetAll implements RowMapper<Tblproduct> {

		@Override
		public Tblproduct mapRow(ResultSet rs, int rowNum) throws SQLException {

			Tblproduct tblproduct = new Tblproduct();
			TblproductId tblproductId = new TblproductId();
			tblproductId.setProductCode(rs.getString("PRODUCT_CODE"));
			tblproductId.setProductImageId(rs.getInt("PRODUCT_IMAGE_ID"));
			tblproduct.setId(tblproductId);
			tblproduct.setProductTitle(rs.getString("PRODUCT_TITLE"));
			tblproduct.setProductVipIngameLevel(rs.getInt("PRODUCT_VIP_INGAME_LEVEL"));
			tblproduct.setProductVipNumber(rs.getInt("PRODUCT_VIP_NUMBER"));
			tblproduct.setProductInfo(rs.getString("PRODUCT_INFO"));
			tblproduct.setProductVipIngameImage(rs.getString("PRODUCT_VIP_INGAME_IMAGE"));
			tblproduct.setProductImage(rs.getString("PRODUCT_IMAGE"));
			tblproduct.setProductPrice(rs.getInt("PRODUCT_PRICE"));
			tblproduct.setProductUserAdd(rs.getString("PRODUCT_USER_ADD"));
			tblproduct.setProductUserUpdate(rs.getString("PRODUCT_USER_UPDATE"));
			tblproduct.setProductCreateDate(rs.getString("PRODUCT_CREATE_DATE"));
			tblproduct.setProductUpdateDate(rs.getString("PRODUCT_UPDATE_DATE"));
			tblproduct.setProductDeleteFlag(rs.getBoolean("PRODUCT_DELETE_FLAG"));
			tblproduct.setProductDeleteFlag(rs.getBoolean("PRODUCT_DELETE_FLAG"));
			tblproduct.setCount(rs.getInt("COUNT"));

			return tblproduct;
		}
	}

	class ProductRowMapper implements RowMapper<Tblproduct> {

		@Override
		public Tblproduct mapRow(ResultSet rs, int rowNum) throws SQLException {

			Tblproduct tblproduct = new Tblproduct();
			TblproductId tblproductId = new TblproductId();
			tblproductId.setProductCode(rs.getString("PRODUCT_CODE"));
			tblproductId.setProductImageId(rs.getInt("PRODUCT_IMAGE_ID"));
			tblproduct.setId(tblproductId);
			tblproduct.setProductTitle(rs.getString("PRODUCT_TITLE"));
			tblproduct.setProductVipIngameLevel(rs.getInt("PRODUCT_VIP_INGAME_LEVEL"));
			tblproduct.setProductVipNumber(rs.getInt("PRODUCT_VIP_NUMBER"));
			tblproduct.setProductInfo(rs.getString("PRODUCT_INFO"));
			tblproduct.setProductVipIngameImage(rs.getString("PRODUCT_VIP_INGAME_IMAGE"));
			tblproduct.setProductImage(rs.getString("PRODUCT_IMAGE"));
			tblproduct.setProductPrice(rs.getInt("PRODUCT_PRICE"));
			tblproduct.setProductUserAdd(rs.getString("PRODUCT_USER_ADD"));
			tblproduct.setProductUserUpdate(rs.getString("PRODUCT_USER_UPDATE"));
			tblproduct.setProductCreateDate(rs.getString("PRODUCT_CREATE_DATE"));
			tblproduct.setProductUpdateDate(rs.getString("PRODUCT_UPDATE_DATE"));
			tblproduct.setProductDeleteFlag(rs.getBoolean("PRODUCT_DELETE_FLAG"));
			tblproduct.setProductDeleteFlag(rs.getBoolean("PRODUCT_DELETE_FLAG"));

			return tblproduct;
		}

	}

}
