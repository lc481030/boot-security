package com.boot.security.server.model;



public class TResourceSupportImg extends BaseEntity<Long> {

	private String img;
	private Integer sort;
	private String linkAddress;

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
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

}
