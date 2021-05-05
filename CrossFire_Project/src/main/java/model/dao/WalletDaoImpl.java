package model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import model.bo.WalletBO;
import model.dao.daoentities.Tblwallet;
import model.dao.daoentities.TblwalletId;

public class WalletDaoImpl implements WalletDao {

	// khoi tao JdbcTemplate.
	private JdbcTemplate jdbcTemplate;
	// date time
	private Date date = new Date(System.currentTimeMillis());

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * Sumary: thuc hien lay thong tin cua Wallet
	 * 
	 * @param: WalletBO walletBO
	 * 
	 * Return: tblwalletList danh sach thong tin Wallet
	 * 
	 * Throws: SQLException
	 */
	@Override
	public List<Tblwallet> queryWallet(WalletBO walletBO) throws SQLException {

		// khoi tao cau lenh lay thong tin
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT WALLET_ID, WALLET_NAME, WALLET_ACOUNT, ");
		sql.append("       WALLET_TITLE, WALLET_BRANCH, WALLET_MASTER, ");
		sql.append("       WALLET_IMAGE, WALLET_USER_ADD, WALLET_USER_UPDATE, ");
		sql.append("       WALLET_CREATE_DATE, WALLET_UPDATE_DATE, WALLET_DELETE_FLAG ");
		sql.append(" FROM TBLWALLET ");
		sql.append(" WHERE WALLET_ID = ? AND WALLET_NAME = ? AND WALLET_ACOUNT = ? ");

		// khoi tao objecct chua sql parameter.
		Object[] sqlParameter = { walletBO.getWalletId(), walletBO.getWalletName(), walletBO.getWalletAcount() };

		List<Tblwallet> tblwalletList = jdbcTemplate.query(sql.toString(), sqlParameter, new WalletRowMapper());

		return tblwalletList;
	}

	/*
	 * Sumary: thuc hien them thong tin Wallet.
	 * 
	 * @param: WalletBO walletBO.
	 * 
	 * Return: trang thai insert.
	 * 
	 * Throws: SQLException.
	 */
	@Override
	public int insertWallet(WalletBO walletBO) throws SQLException {

		// khoi tao StringBuilder
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO TBLWALLET( ");
		sql.append("       WALLET_ID, WALLET_NAME, WALLET_ACOUNT, ");
		sql.append("       WALLET_TITLE, WALLET_BRANCH, WALLET_MASTER, ");
		sql.append("       WALLET_IMAGE, WALLET_USER_ADD, WALLET_USER_UPDATE, ");
		sql.append("       WALLET_CREATE_DATE, WALLET_UPDATE_DATE, WALLET_DELETE_FLAG ");
		sql.append(" ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setInt(1, walletBO.getWalletId());
				ps.setString(2, walletBO.getWalletName());
				ps.setString(3, walletBO.getWalletAcount());
				ps.setString(4, walletBO.getWalletTitle());
				ps.setString(5, walletBO.getWalletBranch());
				ps.setString(6, walletBO.getWalletMaster());
				ps.setString(7, walletBO.getWalletImage());
				ps.setString(8, walletBO.getWalletUserAdd());
				ps.setString(9, walletBO.getWalletUserUpdate());
				ps.setDate(10, date);
				ps.setDate(11, date);
				ps.setBoolean(12, false);
			}
		};

		return jdbcTemplate.update(sql.toString(), pss);
	}

	/*
	 * Sumary: thuc hien update thong tin Wallet.
	 * 
	 * @param: WalletBO walletBO.
	 * 
	 * Return: trang thai update.
	 * 
	 * Throws: SQLException.
	 */
	@Override
	public int updateWallet(WalletBO walletBO) throws SQLException {

		// tao cau lenh update
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE TBLWALLET SET ");
		sql.append("       WALLET_TITLE = ?, WALLET_BRANCH = ?, WALLET_MASTER = ?, ");
		sql.append("       WALLET_IMAGE = ?, WALLET_USER_ADD = ?, WALLET_USER_UPDATE = ?, ");
		sql.append("       WALLET_CREATE_DATE = ?, WALLET_UPDATE_DATE = ?, WALLET_DELETE_FLAG = ? ");
		sql.append(" WHERE WALLET_ID = ? AND WALLET_NAME = ? AND WALLET_ACOUNT = ? ");

		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, walletBO.getWalletTitle());
				ps.setString(2, walletBO.getWalletBranch());
				ps.setString(3, walletBO.getWalletMaster());
				ps.setString(4, walletBO.getWalletImage());
				ps.setString(5, walletBO.getWalletUserAdd());
				ps.setString(6, walletBO.getWalletUserUpdate());
				ps.setDate(7, date);
				ps.setDate(8, date);
				ps.setBoolean(9, walletBO.isDeleteFlg());
				ps.setInt(10, walletBO.getWalletId());
				ps.setString(11, walletBO.getWalletName());
				ps.setString(12, walletBO.getWalletAcount());
			}
		};

		return jdbcTemplate.update(sql.toString(), pss);
	}

	/*
	 * Sumary: thuc hien xoa thong tin Wallet.
	 * 
	 * @param: WalletBO walletBO.
	 * 
	 * Return: trang thai delete.
	 * 
	 * Throws: SQLException.
	 */
	@Override
	public int deleteWallet(WalletBO walletBO) throws SQLException {

		// taoj cau lenh delete
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM TBLWALLET ");
		sql.append("       WHERE WALLET_ID = ? AND WALLET_NAME = ? AND WALLET_ACOUNT = ? ");

		Object[] sqlParameter = { walletBO.getWalletId(), walletBO.getWalletName(), walletBO.getWalletAcount() };
		int[] argType = { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };

		return jdbcTemplate.update(sql.toString(), sqlParameter, argType);
	}

	/*
	 * Sumary: thuc hien setting gia tri lay ra tu DB vao Tblwallet
	 * 
	 * @param: ResultSet rs data
	 * 
	 * @param: int rowNum so dong Return: Tblwallet tblwallet
	 * 
	 * Return: tblwallet
	 * 
	 * Throws: SQLException
	 */
	class WalletRowMapper implements RowMapper<Tblwallet> {

		@Override
		public Tblwallet mapRow(ResultSet rs, int rowNum) throws SQLException {

			// khoi tao Tblwallet va settng gia tri cho Tblwallet.
			// gia tri lay ra tu table.
			Tblwallet tblwallet = new Tblwallet();
			TblwalletId tblwalletId = new TblwalletId();

			// setting gia tri.
			tblwalletId.setWalletId(rs.getInt("WALLET_ID"));
			tblwalletId.setWalletName(rs.getString("WALLET_NAME"));
			tblwallet.setId(tblwalletId);
			tblwallet.setWalletTitle(rs.getString("WALLET_TITLE"));
			tblwallet.setWalletBranch(rs.getString("WALLET_BRANCH"));
			tblwallet.setWalletMaster(rs.getString("WALLET_MASTER"));
			tblwallet.setWalletImage(rs.getString("WALLET_IMAGE"));
			tblwallet.setWalletUserAdd(rs.getString("WALLET_USER_ADD"));
			tblwallet.setWalletUserUpdate(rs.getString("WALLET_USER_UPDATE"));
			tblwallet.setWalletCreateDate(rs.getDate("WALLET_CREATE_DATE"));
			tblwallet.setWalletUpdateDate(rs.getDate("WALLET_UPDATE_DATE"));
			tblwallet.setWalletDeleteFlag(rs.getBoolean("WALLET_DELETE_FLAG"));

			return tblwallet;
		}

	}

}
