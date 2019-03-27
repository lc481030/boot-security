package com.boot.security.server.model;

import java.util.Date;

public class FootPoint extends BaseEntity<Long> {

	private String title;
	private Integer footPointType;
	private String img;
	private Integer deleted;
	private String detail;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getFootPointType() {
		return footPointType;
	}
	public void setFootPointType(Integer footPointType) {
		this.footPointType = footPointType;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
