package com.test.review;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.test.member.memberMain;
import com.test.place.PlaceBasic;

public class ReviewBasic {

   private String reviewNum; // 리뷰 번호
   private String memberName; // 리뷰 작성자 이름
   private String reviewText; // 리뷰 내용
   private Calendar reviewTime; // 리뷰 작성 시간
   private String placeNum; // 리뷰가 작성된 명소의 번호
   private String placeName;
   
   public ReviewBasic(String reviewNum, String memberName, String reviewText, Calendar reviewTime, String placeNum, String placeName) {
      this.reviewNum = reviewNum;
      this.memberName = memberName;
      this.reviewText = reviewText;
      this.reviewTime = reviewTime;
      this.placeNum = placeNum;
      this.placeName = placeName;
   }

   public ReviewBasic(PlaceBasic place) {
      // 매개변수로 들어온 place의 리뷰 넣어주기
   }

   public String getPlaceName() {
      return placeName;
   }

   public void setPlaceName(String placeName) {
      this.placeName = placeName;
   }

   // 리뷰 번호
   public String getReviewNum() {
      return reviewNum;
   }

   public void setReviewNum(String reviewNum) {
      this.reviewNum = reviewNum;
   }

   // 리뷰 작성자 이름
   public String getMemberName() {
      return memberName;
   }

   public void setMemberName(String memberName) {
      this.memberName = memberName;
   }

   // 리뷰 내용
   public String getReviewText() {
      return reviewText;
   }

   public void setReviewText(String reviewText) {
      this.reviewText = reviewText;
   }

   // 리뷰 작성 시간
   public Calendar getReviewTime() {
      return reviewTime;
   }

   public void setReviewTime(Calendar reviewTime) {
      this.reviewTime = reviewTime;
   }

   public String getPlaceNum() {
      return placeNum;
   }

   public void setPlaceNum(String placeNum) {
      this.placeNum = placeNum;
   }
   public void writeReview(PlaceBasic place) {
		System.out.println("리뷰를 작성합니다.");
		File file = new File("D:\\Paldal\\resource\\Review.dat");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();
		try {
			writer.write(memberMain.getUserId() + "■" + place.getName() + "■" + temp + "■");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat time = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
		String writetime = time.format(cal.getTime());
		try {
			writer.write(writetime + "■");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("점수 : ");
		try {
			writer.write(scan.nextLine() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
   public void moreReview(PlaceBasic place) {
      // 기존에 x개만 출력했다가, 메소드 실행 시 전부 보여기 혹은 x+@개 보여주기.
      System.out.println("리뷰 더 출력");
   }

}