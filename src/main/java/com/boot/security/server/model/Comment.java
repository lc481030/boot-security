package com.boot.security.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comment extends BaseEntity<Long> {

	private String detail;
	private Integer commentType;
	private String img;
	private Integer deleted;

	private String commentTimeStr;

	public String getCommentTimeStr() {
		return commentTimeStr;
	}

	public void setCommentTimeStr(String commentTimeStr) {
		this.commentTimeStr = commentTimeStr;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date commentTime;

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getCommentType() {
		return commentType;
	}
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
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
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}
