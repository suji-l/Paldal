package com.test.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.test.member.MemberBasic;
import com.test.place.PlaceBasic;
import com.test.review.ReviewBasic;

public class ReviewControl {
	
	List<ReviewBasic> list;
	
	public ReviewControl() {
		list = new ArrayList<ReviewBasic>();
		
		try {
			File file = new File("resource\\Review.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				Calendar reviewTime = Calendar.getInstance();
				reviewTime.set(
						Integer.parseInt(tmp[3].split("\\.")[0]), 
						Integer.parseInt(tmp[3].split("\\.")[1])-1, 
						Integer.parseInt(tmp[3].split("\\.")[2]), 
						Integer.parseInt(tmp[3].split("\\.")[3]), 
						Integer.parseInt(tmp[3].split("\\.")[4]));
				
				ReviewBasic review = new ReviewBasic
						(tmp[0], tmp[1], tmp[2], reviewTime, tmp[4], tmp[5], tmp[6]);
				list.add(review);
			}
			reader.close();
		}catch (Exception e) {
			System.out.println("경로 확인");
		}
	}
	
	public void listAllReview() {
		list.sort((o1, o2) -> (int)(o2.getReviewTime().getTimeInMillis() - o1.getReviewTime().getTimeInMillis()));
		System.out.println("\t\t\t[작성자]\t   [명소]\t\t\t[리뷰]\t\t\t\t[작성시간]");
		for (ReviewBasic reviewBasic : list) {
			String memberName =String.format("%-15s", reviewBasic.getMemberName()); 
			String placeName = reviewBasic.getPlaceName().length() > 10 ? String.format("%-10.10s..", reviewBasic.getPlaceName()) : String.format("%-11.11s", reviewBasic.getPlaceName());
			String reviewTest =  String.format("%25s", reviewBasic.getReviewText());
			String result = memberName + placeName + reviewTest + "\t\t" + String.format("%tF %tT", reviewBasic.getReviewTime(), reviewBasic.getReviewTime());
			System.out.println("\t\t\t" + result);
		}
	}
	
	public void listByPlace(String inputPlaceNum) throws Exception {
		PlaceControl place = new PlaceControl();

		PlaceBasic placeBasic = place.placeByNum(inputPlaceNum);

		List<ReviewBasic> resultList = new ArrayList<ReviewBasic>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPlaceNum().equals(placeBasic.getPlaceNum())) {
				resultList.add(list.get(i));
			}
		}
		if (resultList.size() == 0) {
			System.out.println("\t\t\t댓글이 존재하지 않습니다.");
		} else {
			resultList.sort(
					(o1, o2) -> (int) (o2.getReviewTime().getTimeInMillis() - o1.getReviewTime().getTimeInMillis()));
			System.out.println();
			System.out.println("\t\t\t[작성자]\t   [명소]\t\t\t[리뷰]\t\t\t\t[작성시간]");
			for (ReviewBasic reviewBasic : resultList) {
				String memberName = String.format("%-15s", reviewBasic.getMemberName());
				String placeName = reviewBasic.getPlaceName().length() > 10
						? String.format("%-10.10s..", reviewBasic.getPlaceName())
						: String.format("%-11.11s", reviewBasic.getPlaceName());
				String reviewTest = String.format("%25s", reviewBasic.getReviewText());
				String result = memberName + placeName + reviewTest + "\t\t"
						+ String.format("%tF %tT", reviewBasic.getReviewTime(), reviewBasic.getReviewTime());
				System.out.println("\t\t\t" + result);
			}

		}

	}
	
	public void listByMember(String inputMemberNum) {
		MemberControl member= new MemberControl();
		
		MemberBasic memberBasic = member.memberByNum(inputMemberNum);
		
		List<ReviewBasic> resultList = new ArrayList<ReviewBasic>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getMemberName().equals(memberBasic.getId())) {
				resultList.add(list.get(i));
			}
		}
		System.out.println();
		if(resultList.size() == 0) {
			System.out.println("\t\t\t회원이 작성한 댓글이 존재하지 않습니다.");
		}
		else {
			resultList.sort((o1, o2) -> (int)(o2.getReviewTime().getTimeInMillis() - o1.getReviewTime().getTimeInMillis()));
			System.out.println();
			System.out.println("\t\t\t작성자\t          명소\t             댓글\t                 작성시간");
			for (ReviewBasic reviewBasic : resultList) {
				String memberName =String.format("%-15s", reviewBasic.getMemberName()); 
				String placeName = reviewBasic.getPlaceName().length() > 10 ? String.format("%-10.10s..", reviewBasic.getPlaceName()) : String.format("%-11.11s", reviewBasic.getPlaceName());
				String reviewTest =  String.format("%25s", reviewBasic.getReviewText());
				String result = memberName + placeName + reviewTest + "\t\t" + String.format("%tF %tT", reviewBasic.getReviewTime(), reviewBasic.getReviewTime());
				System.out.println("\t\t\t" + result);
			}
		}
		
		
	}

}
