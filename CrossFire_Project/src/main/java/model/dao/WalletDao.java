package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.bo.WalletBO;
import model.dao.daoentities.Tblwallet;

public interface WalletDao {
	
	public List<Tblwallet> queryWallet(WalletBO walletBO) throws SQLException;
	
	public int insertWallet(WalletBO walletBO) throws SQLException;
	
	public int updateWallet(WalletBO walletBO) throws SQLException;
	
	public int deleteWallet(WalletBO walletBO) throws SQLException;

}
