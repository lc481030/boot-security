package com.boot.security.server.model;



public class ProductType extends BaseEntity<Long> {

	private String productType;
	/*旅游方式1地区2主题3景点4交通*/
	private String productMode;
	private String isShow;
	private Integer deleted;
	private Integer sort;

	public String getProductMode() {
		return productMode;
	}
	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
