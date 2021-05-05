package model.business.products;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.ExceptionID;
import common.LogicException;
import common.utils.Utils;
import model.bo.ProductBO;
import model.business.user.UserBusinessImpl;
import model.dao.ProductDao;
import model.dao.daoentities.Tblproduct;

public class ProductBusinessImpl implements ProductBusiness {

	private static final Logger logger = Logger.getLogger(UserBusinessImpl.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private ProductDao productDao = (ProductDao) context.getBean("productdao");

	@Override
	public List<ProductBO> getAllProduct() throws LogicException {
		// creat list product object
		List<Tblproduct> tblproduct = new ArrayList<Tblproduct>();
		try {
			// lay thong tin product
			tblproduct = productDao.queryAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<ProductBO> listProductBO = new ArrayList<ProductBO>();
		if (tblproduct.size() > 0) {
			ProductBO productBO = new ProductBO();
			for (int i = 0; i < tblproduct.size(); i++) {
				productBO = setValueproductBO(tblproduct.get(i));
				listProductBO.add(productBO);
			}
		}

		return listProductBO;
	}

	@Override
	public ProductBO getProductBykey(String productCode) throws LogicException {
		// creat list product object
		Tblproduct tblproduct = new Tblproduct();
		try {
			// lay thong tin product
			tblproduct = productDao.queryProductByKey(productCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// truong hop thong tin product khong ton tai.
		if (Objects.isNull(tblproduct)) {
			Utils utils = new Utils();
			Exception e = utils.getLogicException(null, ExceptionID.EPD001);
			// thực hiện ghi log.
			logger.error("phát sinh NotFountException", e);

			throw new LogicException(ExceptionID.MPD001);
		}
		ProductBO productBO = new ProductBO();
		productBO = setValueproductBO(tblproduct);
		return productBO;
	}

	@Override
	public int insertProduct(ProductBO productBO) throws LogicException {
		int insertFlag = 0;

		try {
			insertFlag = productDao.insertProduct(productBO);
			insertFlag = 1;
		} catch (Exception e) {
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(e, ExceptionID.EPD002);

			// thuc hien ghi log
			logger.error("phát sinh DuplicationException", ex);
			throw new LogicException(ExceptionID.MPD002);
		}
		return insertFlag;
	}

	@Override
	public int updateProduct(ProductBO productBO) throws LogicException {
		int updateFlag = 0;

		try {
			updateFlag = productDao.updateProduct(productBO);
			updateFlag = 1;
		} catch (Exception e) {
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(e, ExceptionID.EPD001);

			// thuc hien ghi log
			logger.error("Phát sinh NotFoundException", ex);
			throw new LogicException(ExceptionID.MPD003);
		}
		return updateFlag;
	}

	@Override
	public int deleteProduct(ProductBO productBO) throws LogicException {
		int deleteFlag = 0;

		try {
			deleteFlag = productDao.deleteProduct(productBO);
			deleteFlag = 1;
		} catch (Exception e) {
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(e, ExceptionID.EPD001);

			// thuc hien ghi log
			logger.error("Phát sinh NotFoundException", ex);
			throw new LogicException(ExceptionID.MPD004);
		}
		return deleteFlag;
	}

	private ProductBO setValueproductBO(Tblproduct tblproduct) {

		ProductBO productBO = new ProductBO();

		productBO.setProductCode(tblproduct.getId().getProductCode());
		productBO.setProductTitle(tblproduct.getProductTitle());
		productBO.setProductVipIngameLevel(tblproduct.getProductVipIngameLevel());
		productBO.setProductVipNumber(tblproduct.getProductVipNumber());
		productBO.setProductImageId(tblproduct.getId().getProductImageId());
		productBO.setProductInfo(tblproduct.getProductInfo());
		productBO.setProductVipIngameImage(tblproduct.getProductVipIngameImage());
		productBO.setProductImage(tblproduct.getProductImage());
		productBO.setProductPrice(tblproduct.getProductPrice());
		productBO.setProductUserAdd(tblproduct.getProductUserAdd());
		productBO.setProductUserUpdate(tblproduct.getProductUserUpdate());
		productBO.setProductCreateDate(tblproduct.getProductCreateDate());
		productBO.setProductUpdateDate(tblproduct.getProductUpdateDate());
		productBO.setDeleteFlg(tblproduct.getProductDeleteFlag());
		productBO.setCount(tblproduct.getCount());

		return productBO;
	}

}
