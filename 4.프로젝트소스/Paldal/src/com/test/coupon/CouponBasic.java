package com.test.coupon;

import java.util.Calendar;

public class CouponBasic {
	
	private String couponNum;
	private String couponName;
	private String couponTarget;
	private String couponDescription;
	private double couponDc;
	private Calendar couponDate;

	public CouponBasic(String couponNum, String couponName, String couponTarget, String couponDescription,
			double couponDc, Calendar couponDate) {
		this.couponNum = couponNum;
		this.couponName = couponName;
		this.couponTarget = couponTarget;
		this.couponDescription = couponDescription;
		this.couponDc = couponDc;
		this.couponDate = couponDate;
	}

	// 쿠폰 번호
	public String getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}

	// 쿠폰이름
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	// 적용대상
	public String getCouponTarget() {
		return couponTarget;
	}

	public void setCouponTarget(String couponTarget) {
		this.couponTarget = couponTarget;
	}

	// 쿠폰 설명
	public String getCouponDescription() {
		return couponDescription;
	}

	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}

	// 쿠폰 할인율
	public double getCouponDc() {
		return couponDc;
	}

	public void setCouponDc(double couponDc) {
		this.couponDc = couponDc;
	}

	// 쿠폰 유효기간
	public Calendar getCouponDate() {
		return couponDate;
	}

	public void setCouponDate(Calendar couponDate) {
		this.couponDate = couponDate;
	}

}