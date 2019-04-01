package com.boot.security.server.model;



public class TResourceSupportImg extends BaseEntity<Long> {

	private String img;
	private Integer sort;
	private String LinkAddress;

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getLinkAddress() {
		return LinkAddress;
	}
	public void setLinkAddress(String LinkAddress) {
		this.LinkAddress = LinkAddress;
	}

}
