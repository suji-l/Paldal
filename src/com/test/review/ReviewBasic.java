package com.test.review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.test.member.memberMain;
import com.test.place.GetInfoOfPlaceMain;
import com.test.place.PlaceBasic;

public class ReviewBasic {

	private String reviewNum; // 리뷰 번호
	private String memberName; // 리뷰 작성자 이름
	private String reviewText; // 리뷰 내용
	private Calendar reviewTime; // 리뷰 작성 시간
	private String placeNum; // 리뷰가 작성된 명소의 번호
	private String placeName;

	public ReviewBasic(String reviewNum, String memberName, String reviewText, Calendar reviewTime, String placeNum,
			String placeName) {
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
		System.out.println("\t\t\t  ※ 종료하려면 'REVIEW END' 를 입력해주세요.");
		System.out.println();
		System.out.println("\t\t\t================ 리뷰 작성 ===============");

		File file = new File("D:\\Paldal\\resource\\Review.dat");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("\t\t\t 작성자 : %s\n", memberMain.getUserId());
		System.out.printf("\t\t\t 장소명 : %s %s\n", place.getAddress(), place.getName());
		System.out.println("\t\t\t---------------------------------------");
		String content = "";
		String flag = "in";
		Scanner scan = new Scanner(System.in);
		// 내용을 입력받는 while문
		while (true) {
			System.out.print("\t\t\t내용 : ");
			content = scan.nextLine();
			
			// 욕 목록
			String[] kindaShit = new String[] { "시발", "개새끼", "ㅅㅂ", "병신", "ㅄ", "호구", "창렬", "ㅈㄴ", "존나", "좆" };
			
			// 사용자에게 리뷰 입력받음
			// review end 
			if(content.equals("REVIEW END")) {break;}
			// 욕 검사
			for (String shit : kindaShit) {
				if (content.contains(shit)) {
					System.out.println("\t\t\t내용에 비속어가 들어있습니다. 다시 입력해주세요");
					flag = shit;
					break;
				} 
			}
			System.out.println("");
			if(flag.equals("in")) {
				break;
			}
		}
		
		if(flag.equals("in")) {
			try {
				writer.write("\n" + memberMain.getUserId() + "■" + content + "■");
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
			try {
				writer.write(place.getPlaceNum() + "■" + place.getName()+"\n");
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
			System.out.println("\t\t\t---------------------------------------");
			System.out.println("\t\t\t리뷰 작성을 마칩니다.");
		} else if(content.equals("REVIEW END")) {
			System.out.println("\t\t\t리뷰 작성을 마칩니다.");
		} else {
			System.out.println("\t\t\t리뷰 작성을 마칩니다.");
		}
	}

	public void moreReview(PlaceBasic place) {
        // 여기서 5개만 보여주다가 전부다를 보여주는데 혹시 5개 이후것만 보여줄 수 있게가 가능하면 그렇게 작업ㄱ
        BufferedReader reader = null;
        String line = null;
        int i = 1;
        try {
           reader = new BufferedReader(new FileReader("D:\\Paldal\\resource\\Review.dat"));
        } catch (FileNotFoundException e1) {
           
           e1.printStackTrace();
        }
        
        //5번째까지 출력하는거랑 같이 num 넣어주고 
        int num = 0;
        
        System.out.println("\t\t\t============================================================================");
        System.out.printf("\t\t\t명소 : %s\n",place.getName());
        System.out.println("\t\t\t============================================================================");
        try {
           while ((line = reader.readLine()) != null) {
              String[] temp = line.split("■");
              //temp[0] = 리뷰번호 temp[1] = 아이디 temp[2] = 리뷰내용 
              //temp[3] =리뷰 날짜 temp[4] = 점수 temp[5] = 장소
              //num이 5이상일때 부터 출력            
              if (line.contains(place.getName()) && num >= 5) {
                 System.out.printf("\t\t\t%s님의 리뷰\t\t 작성일:%s\t 점수 : %s\n",temp[1],temp[3],temp[4]);
                 System.out.println("\t\t\t" +temp[2]);
                 System.out.println("\t\t\t============================================================================");
                 i++;
                 

              }
              num++;
           }
        } catch (IOException e) {
           
           e.printStackTrace();
        }

     

	}

}