package model.bo;

import java.sql.Date;

public class WalletBO {

	private int walletId;
	private String walletName;
	private String walletAcount;
	private String walletTitle;
	private String walletBranch;
	private String walletMaster;
	private String walletImage;
	private String walletUserAdd;
	private String walletUserUpdate;
	private Date walletCreateDate;
	private Date walletUpdateDate;
	private boolean deleteFlg;
	
	public WalletBO() {
		// TODO Auto-generated constructor stub
	}

	public WalletBO(int walletId, String walletName, String walletAcount, String walletTitle, String walletBranch,
			String walletMaster, Date walletCreateDate, Date walletUpdateDate, boolean deleteFlg) {
		super();
		this.walletId = walletId;
		this.walletName = walletName;
		this.walletAcount = walletAcount;
		this.walletTitle = walletTitle;
		this.walletBranch = walletBranch;
		this.walletMaster = walletMaster;
		this.walletCreateDate = walletCreateDate;
		this.walletUpdateDate = walletUpdateDate;
		this.deleteFlg = deleteFlg;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public String getWalletAcount() {
		return walletAcount;
	}

	public void setWalletAcount(String walletAcount) {
		this.walletAcount = walletAcount;
	}

	public String getWalletTitle() {
		return walletTitle;
	}

	public void setWalletTitle(String walletTitle) {
		this.walletTitle = walletTitle;
	}

	public String getWalletBranch() {
		return walletBranch;
	}

	public void setWalletBranch(String walletBranch) {
		this.walletBranch = walletBranch;
	}

	public String getWalletMaster() {
		return walletMaster;
	}

	public void setWalletMaster(String walletMaster) {
		this.walletMaster = walletMaster;
	}

	public Date getWalletCreateDate() {
		return walletCreateDate;
	}

	public void setWalletCreateDate(Date walletCreateDate) {
		this.walletCreateDate = walletCreateDate;
	}

	public Date getWalletUpdateDate() {
		return walletUpdateDate;
	}

	public void setWalletUpdateDate(Date walletUpdateDate) {
		this.walletUpdateDate = walletUpdateDate;
	}

	public boolean isDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getWalletImage() {
		return walletImage;
	}

	public void setWalletImage(String walletImage) {
		this.walletImage = walletImage;
	}

	public String getWalletUserAdd() {
		return walletUserAdd;
	}

	public void setWalletUserAdd(String walletUserAdd) {
		this.walletUserAdd = walletUserAdd;
	}

	public String getWalletUserUpdate() {
		return walletUserUpdate;
	}

	public void setWalletUserUpdate(String walletUserUpdate) {
		this.walletUserUpdate = walletUserUpdate;
	}
}
