package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.bo.ProductBO;
import model.bo.UserBO;
import model.bo.WalletBO;
import model.dao.daoentities.Tblproduct;
import model.dao.daoentities.Tbluser;

public class TestConnection {

	private static ApplicationContext ctx;

	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao dao = (UserDao) ctx.getBean("userdao");

		UserBO userBO = new UserBO();
		userBO.setUserID(1);
		userBO.setUserName("admin");
		try {
			Tbluser tblUserList = dao.queryUser("","");

				System.out.println(tblUserList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProductDao productDao = (ProductDao) ctx.getBean("productdao");

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		ProductBO productBO = new ProductBO();

		productBO.setProductCode("1501");
		productBO.setProductTitle("Chuyên C5");
		productBO.setProductVipIngameLevel(2);
		productBO.setProductVipNumber(2);
		productBO.setProductImageId(1);
		productBO.setProductInfo("thông tin trắng + sdt trắng 4");
		productBO.setProductVipIngameImage("image2.jpg4");
		productBO.setProductImage("image3.jpg4");
		productBO.setProductPrice(3000004);
		productBO.setProductUserAdd("admin4");
		productBO.setProductUserUpdate("admin4");
//		productBO.setProductCreateDate(date);
//		productBO.setProductUpdateDate(date);
		productBO.setDeleteFlg(false);

//		try {
//			// int i = productDao.insertProduct(productBO);
//			// int i = productDao.updateProduct(productBO);
//			// int i = productDao.updateProduct(productBO);
//			// int i = productDao.deleteProduct(productBO);
//			// System.out.println(i);
//
//			Tblproduct tt = new Tblproduct();
//			tt = productDao.queryProductByKey("");
//
////				System.out.println(tblproduct.getProductInfo());
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		WalletDao walletDao = (WalletDao) ctx.getBean("walletdao");
		WalletBO walletBO = new WalletBO();

		walletBO.setWalletId(1);
		walletBO.setWalletName("MoMo");
		walletBO.setWalletAcount("0389576197");
		walletBO.setWalletBranch("walletBranch");
		walletBO.setWalletTitle("Ví điện tử");
		walletBO.setWalletMaster("Lê Trung Hiếu");
		walletBO.setWalletImage("image1.jpg");
		walletBO.setWalletUserAdd("admin");
		walletBO.setWalletUserUpdate("admin");
		walletBO.setWalletCreateDate(date);
		walletBO.setWalletUpdateDate(date);
		walletBO.setDeleteFlg(false);

		try {
			int i = walletDao.insertWallet(walletBO);
			// walletDao.updateWallet(walletBO);
			// walletDao.deleteWallet(walletBO);

//			List<Tblwallet> tt = new ArrayList<Tblwallet>();
//			tt = walletDao.queryWallet(walletBO);
//
//			for (Tblwallet tblwallet : tt) {
//				System.out.println(tblwallet.getWalletMaster());
//			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
