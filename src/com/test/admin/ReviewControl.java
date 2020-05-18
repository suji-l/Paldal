package com.test.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
						Integer.parseInt(tmp[3].split("\\.")[1]), 
						Integer.parseInt(tmp[3].split("\\.")[2]), 
						Integer.parseInt(tmp[3].split("\\.")[3]), 
						Integer.parseInt(tmp[3].split("\\.")[4]));
				ReviewBasic review = new ReviewBasic
						(tmp[0], tmp[1], tmp[2], reviewTime, tmp[4], tmp[5]);
				list.add(review);
			}
			reader.close();
		}catch (Exception e) {
			System.out.println("경로 확인");
		}
	}
	
	public void listAllReview() {
		list.sort((o1, o2) -> (int)(o2.getReviewTime().getTimeInMillis() - o1.getReviewTime().getTimeInMillis()));
		System.out.println("\t\t\t작성자\t명소\t댓글\t작성시간");
		for (ReviewBasic reviewBasic : list) {
			System.out.println("\t\t\t"+reviewBasic.getMemberName() + "\t" + reviewBasic.getPlaceName() + 
					"\t" + reviewBasic.getReviewText() + "\t" + String.format("%tF %tT", reviewBasic.getReviewTime(), reviewBasic.getReviewTime()));
		}
	}
	
	public void listByPlace() {
		Scanner scan = new Scanner(System.in);
		PlaceControl place = new PlaceControl();
		place.printPlaceList();
		System.out.print("\t\t\t장소 번호 입력 : ");
		String inputPlaceNum = scan.nextLine();
		
		PlaceBasic placeBasic = place.placeByNum(inputPlaceNum);

		List<ReviewBasic> result = new ArrayList<ReviewBasic>();
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getPlaceNum().equals(placeBasic.getPlaceNum())) {
				result.add(list.get(i));
			}
		}
		result.sort((o1, o2) -> (int)(o2.getReviewTime().getTimeInMillis() - o1.getReviewTime().getTimeInMillis()));
		System.out.println("\t\t\t작성자\t명소\t댓글\t작성시간");
		for (ReviewBasic reviewBasic : result) {
			System.out.println("\t\t\t"+reviewBasic.getMemberName() + "\t" + reviewBasic.getPlaceName() + 
					"\t" + reviewBasic.getReviewText() + "\t" + String.format("%tF %tT", reviewBasic.getReviewTime(), reviewBasic.getReviewTime()));
		}
		
	}
	
	public void listByMember() {
		Scanner scan = new Scanner(System.in);
		MemberControl member = new MemberControl();
		member.printMemberList();
		System.out.print("\t\t\t회원 번호 입력 : ");
		String inputMemberNum = scan.nextLine();
		
		MemberBasic memberBasic = member.memberByNum(inputMemberNum);
		
		List<ReviewBasic> result = new ArrayList<ReviewBasic>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getMemberName().equals(memberBasic.getId())) {
				result.add(list.get(i));
			}
		}
		System.out.println();
		if(result.size() == 0) {
			System.out.println("\t\t\t회원이 작성한 댓글이 존재하지 않습니다.");
		}
		else {
			result.sort((o1, o2) -> (int)(o2.getReviewTime().getTimeInMillis() - o1.getReviewTime().getTimeInMillis()));
			System.out.println("\t\t\t작성자\t명소\t댓글\t작성시간");
			for (ReviewBasic reviewBasic : result) {
				System.out.println("\t\t\t"+reviewBasic.getMemberName() + "\t" + reviewBasic.getPlaceName() + 
						"\t" + reviewBasic.getReviewText() + "\t" + String.format("%tF %tT", reviewBasic.getReviewTime(), reviewBasic.getReviewTime()));
			}
		}
		
		
	}

}
