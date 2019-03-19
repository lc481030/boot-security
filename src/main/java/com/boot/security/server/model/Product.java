package com.boot.security.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Product extends BaseEntity<Long> {

	private String title;
	private String detail;
	private String productMode;
	private String productType;
	private String startTime;
	private String imgs;
	private String price;
	private String startCity;
	private String targetCountry;
	private String days;
	private String afterCity;
	private String vehicle;
	private String brightSpot;
	private String costDescription;
	private String reservationNotes;
	private Integer deleted;
	private Integer recommendIndex;/*推荐*/


	public Integer getRecommendIndex() {
		return recommendIndex;
	}

	public void setRecommendIndex(Integer recommendIndex) {
		this.recommendIndex = recommendIndex;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getProductMode() {
		return productMode;
	}
	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getTargetCountry() {
		return targetCountry;
	}
	public void setTargetCountry(String targetCountry) {
		this.targetCountry = targetCountry;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getAfterCity() {
		return afterCity;
	}
	public void setAfterCity(String afterCity) {
		this.afterCity = afterCity;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getBrightSpot() {
		return brightSpot;
	}
	public void setBrightSpot(String brightSpot) {
		this.brightSpot = brightSpot;
	}
	public String getCostDescription() {
		return costDescription;
	}
	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}
	public String getReservationNotes() {
		return reservationNotes;
	}
	public void setReservationNotes(String reservationNotes) {
		this.reservationNotes = reservationNotes;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
