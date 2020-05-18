package com.test.place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class writeReviewBasic {	
	
//	private String reviewNum; // 리뷰 번호
	private String reviewName; // 사용자 이름
	private String reviewPlace; // 리뷰 장소
	private String reviewText; // 리뷰 내용
	private String reviewDate; // 리뷰 작성 시간 
	private String reviewScore; // 리뷰 점수
	
	public writeReviewBasic(String reviewName, String reviewPlace, String reviewText, String reviewDate, String reviewScore) {
//		this.reviewNum = reviewNum;
		this.reviewName = reviewName;
		this.setReviewPlace(reviewPlace);
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
		this.reviewScore =reviewScore;
	}

//	public String getReviewNum() {
//		return reviewNum;
//	}
//
//	public void setReviewNum(String reviewNum) {
//		this.reviewNum = reviewNum;
//	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewPlace() {
		return reviewPlace;
	}

	public void setReviewPlace(String reviewPlace) {
		this.reviewPlace = reviewPlace;
	}

	public String getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(String reviewScore) {
		this.reviewScore = reviewScore;
	}

	
	
	
	

	
	

	

}
