package model.bo;

public class ProductBO {

	private String productCode;
	private String productTitle;
	private int productVipIngameLevel;
	private int productVipNumber;
	private int productImageId;
	private String productInfo;
	private String productVipIngameImage;
	private String productImage;
	private int productPrice;
	private String productUserAdd;
	private String productUserUpdate;
	private String productCreateDate;
	private String productUpdateDate;
	private int count;
	private boolean deleteFlg;

	public ProductBO() {
	}

	public ProductBO(String productCode, String productTitle, int productVipIngameLevel, int productVipNumber,
			int productImageId, String productInfo, String productVipIngameImage,
			String productImage, int productPrice, String productCreateDate, String productUpdateDate,
			boolean deleteFlg, int count) {
		super();
		this.productCode = productCode;
		this.productTitle = productTitle;
		this.productVipIngameLevel = productVipIngameLevel;
		this.productVipNumber = productVipNumber;
		this.productImageId = productImageId;
		this.productInfo = productInfo;
		this.productVipIngameImage = productVipIngameImage;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productCreateDate = productCreateDate;
		this.productUpdateDate = productUpdateDate;
		this.deleteFlg = deleteFlg;
		this.count = count;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public int getProductVipIngameLevel() {
		return productVipIngameLevel;
	}

	public void setProductVipIngameLevel(int productVipIngameLevel) {
		this.productVipIngameLevel = productVipIngameLevel;
	}

	public int getProductVipNumber() {
		return productVipNumber;
	}

	public void setProductVipNumber(int productVipNumber) {
		this.productVipNumber = productVipNumber;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductCreateDate() {
		return productCreateDate;
	}

	public void setProductCreateDate(String productCreateDate) {
		this.productCreateDate = productCreateDate;
	}

	public String getProductUpdateDate() {
		return productUpdateDate;
	}

	public void setProductUpdateDate(String productUpdateDate) {
		this.productUpdateDate = productUpdateDate;
	}

	public boolean isDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getProductVipIngameImage() {
		return productVipIngameImage;
	}

	public void setProductVipIngameImage(String productVipIngameImage) {
		this.productVipIngameImage = productVipIngameImage;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductUserAdd() {
		return productUserAdd;
	}

	public void setProductUserAdd(String productUserAdd) {
		this.productUserAdd = productUserAdd;
	}

	public String getProductUserUpdate() {
		return productUserUpdate;
	}

	public void setProductUserUpdate(String productUserUpdate) {
		this.productUserUpdate = productUserUpdate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
