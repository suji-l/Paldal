package com.test.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import com.test.coupon.CouponBasic;
import com.test.reservation.ReservationBasic;
import com.test.review.ReviewBasic;

public class memberMypage {
	Scanner scan = new Scanner(System.in);
	MemberBasic member;

	public memberMypage(MemberBasic member) {
		this.member = member;
	}

	public void mainMenu() {
		while (true) {
			// 마이페이지 메뉴 출력
			myPageMenu();
			System.out.print("\t\t\t번호 입력 : ");

			// 사용자에게 번호 입력받음
			String selectNum = scan.nextLine();

			// MemberBasic 멤버에 값을 넣어준다.
			this.member = settingMemberData(member);

			if (selectNum.equals("1")) {
				myInfo(member);
			} else if (selectNum.equals("2")) {
				myCoupon(member);
			} else if (selectNum.equals("3")) {
				myReservationList(member);
			} else if (selectNum.equals("4")) {
				myReviewList(member);
			} else if (selectNum.equals("0")) {
				break;
			} else {
				System.out.println("\t\t\t번호를 다시 입력해주세요.");
			}

		}

	}

	private MemberBasic settingMemberData(MemberBasic member) { // member의 setter 값을 넣는 메소드를 생성

		// 파일 경로
		String path = "D:\\Paldal\\resource\\Member.dat";

		// 파일 생성
		File dummy = new File(path);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(dummy));

			String line = "";

			while ((line = reader.readLine()) != null) {

				// "■"을 구분자로 해서 배열에 넣어준다
				String[] list = line.split("■");

				if (list[2].equals(member.getId())) {

					String tmp;

					//// 쿠폰을 갖고 있는사람도 있고 안갖고 있는 사람도 있기 때문에 스트링 문자열에 안가지고 있을 경우 null, 가지고있을 경우 쿠폰번호값
					//// 삽입
					if (list.length == 10) {
						tmp = list[9];
					} else {
						tmp = null;
					}

					// member의 setter에 값을넣어준다.
					member.setMemberNum(list[0]);
					member.setName(list[1]);
					member.setId(list[2]);
					member.setPw(list[3]);
					member.setAge(Integer.parseInt(list[4]));
					member.setAddress(list[5]);
					member.setPnum(list[6]);
					member.setGender(list[7]);
					member.setBlackListCount(Integer.parseInt(list[8]));
					member.setCoupon(tmp);

				}
			}
		} catch (Exception e) {

		}
		return member;
	}

	public void myPageMenu() {

		System.out.println("\n\t\t\t==========  My Page ==========");
		System.out.println("\t\t\t1. 내 정보 보기");
		System.out.println("\t\t\t2. 내 쿠폰 보기");
		System.out.println("\t\t\t3. 예약 정보 현황");
		System.out.println("\t\t\t4. 내가 쓴 리뷰");
		System.out.println("\t\t\t0. 뒤로가기");
		System.out.println("\t\t\t==============================\n");
	}

	private void myReviewList(MemberBasic member) {

		// 파일 경로
		String path = "D:\\Paldal\\resource\\Review.dat";

		// 파일 생성
		File reviewdummy = new File(path);

		try {
			// 파일 읽기
			BufferedReader reader = new BufferedReader(new FileReader(reviewdummy));

			String line = "";
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓내 리뷰목록〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// 파일의 내용이 null 일 때 까지 while문 돌린다.
			while ((line = reader.readLine()) != null) {

				// "■"을 구분자로 해서 배열에 넣어준다
				String[] list = line.split("■");

				// 사용자의 아이디가 파일안의 아이디와 동일할 경우 값을 넣어주고 출력
				if (list[1].contains(member.getId())) {

					// 캘린더 생성후 파일에 있는 String 값을 캘린더 값으로 넣어준다.
					Calendar reviewCal = Calendar.getInstance();
					reviewCal.set(Calendar.YEAR, Integer.parseInt(list[3].substring(0, 4)));
					reviewCal.set(Calendar.MONTH, Integer.parseInt(list[3].substring(5, 7)));
					reviewCal.set(Calendar.DATE, Integer.parseInt(list[3].substring(8, 10)));

					// 수정 : 리뷰 더미파일 list[6]부분에 (문화재위치) 넣어야함.
					String tmp;
					if (list.length == 6) {
						tmp = list[5];
					} else {
						tmp = null;
					}

					ReviewBasic rev = new ReviewBasic(list[0], list[1], list[2], reviewCal, list[4],tmp);

					System.out.println("\t\t\t-----------------------------------------------");
					System.out.printf("\t\t\t명소 : %s\n", rev.getPlaceName());
					System.out.print("\t\t\t" + member.getId() + "님의 리뷰\t\t");
					System.out.printf("작성일 : %tF\n", rev.getReviewTime());
					System.out.printf("\t\t\t내용 : %s\n", rev.getReviewText());
					System.out.println("\t\t\t-----------------------------------------------");

				}

			}

		} catch (Exception e) {

			System.out.println("오류");

		}

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.\n");
		scan.nextLine();
	}

//내 예약 정보
	private void myReservationList(MemberBasic member) {

		// 파일 경로

		String path = "D:\\Paldal\\resource\\Reservation.dat";

		// 파일 생성
		File dummy = new File(path);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(dummy));

			String line = "";

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓내 예약목록〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t예약 번호\t예약장소\t\t예약일");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// 파일의 내용이 null이 아닐때 까지 루프문 돌린다.
			while ((line = reader.readLine()) != null) {
				String[] list = line.split("■");
				// 사용자의 아이디가 파일안의 아이디와 동일할 경우 값을 넣어주고 출력
				if (list[1].contains(member.getId())) {

					// 캘린더 생성후 파일에 있는 String 값을 캘린더 값으로 넣어준다.
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.YEAR, Integer.parseInt(list[4].substring(0, 4)));
					cal.set(Calendar.MONTH, Integer.parseInt(list[4].substring(5, 7)));
					cal.set(Calendar.DATE, Integer.parseInt(list[4].substring(8)));
					ReservationBasic res = new ReservationBasic(list[0], list[1], list[2], Integer.parseInt(list[3]),
							cal, Integer.parseInt(list[5].substring(0, list[5].length() - 1)), list[6]);
					System.out.printf("\t\t\t%s\t%s\t\t%tF\n", res.getReservationNum(), res.getReservationPlace(),
							res.getReservationDate());

				}
			}

		} catch (Exception e) {

		}

		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.\n");
		scan.nextLine();

	}

	// 내 쿠폰 목록
	private void myCoupon(MemberBasic member) {

		// 파일 경로
		String path = "D:\\Paldal\\resource\\Coupon.dat";

		// 파일생성
		File coupondummy = new File(path);

		String line = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(coupondummy));
			reader = new BufferedReader(new FileReader(coupondummy));

			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓내 쿠폰목록〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t쿠폰번호\t\t쿠폰명\t\t할인률\t유효기간");
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			// 파일의 내용이 null값이 아닐때 까지 루프문 돌린다.
			while ((line = reader.readLine()) != null) {

				String[] list = line.split("■");

				// 사용자가 가지고 있는 쿠폰번호와 파일안의 쿠폰 번호가 동일할 경우 값을 넣어주고 출력
				if (member.getCoupon().contains(list[0])) {

					// String 값을 Calendar형태로 바꿔주고 년,월,일 순으로 넣어준다.
					Calendar cal = Calendar.getInstance();

					cal.set(Calendar.YEAR, Integer.parseInt(list[5].substring(0, 4)));
					cal.set(Calendar.MONTH, Integer.parseInt(list[5].substring(5, 7)));
					cal.set(Calendar.DATE, Integer.parseInt(list[5].substring(8)));

					// CouponBasic 멤버에 값을 넣어준다
					CouponBasic cp = new CouponBasic(list[0], list[1], list[2], list[3],
							Double.parseDouble(list[4].substring(0, list[4].length() - 1)), cal);

					System.out.printf("\t\t\t%s\t%s\t%.1f％\t%tF\n", cp.getCouponNum(), cp.getCouponName(),
							cp.getCouponDc(), cp.getCouponDate());

				}

			}

		} catch (Exception e) {

		}
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.\n");
		scan.nextLine();
	}

	// 내 정보 보기
	public void myInfo(MemberBasic member) {

		// 파일 경로
		String path = "D:\\Paldal\\resource\\Member.dat";

		// 파일 생성
		File dummy = new File(path);

		try {

			BufferedReader reader = new BufferedReader(new FileReader(dummy));

			String line = "";

			// 파일의 내용이 null일때까지 루프문을 돌린다.
			while ((line = reader.readLine()) != null) {

				// "■"을 구분자로 해서 배열에 넣어준다
				String[] list = line.split("■");

				// 사용자의 아이디가 파일안의 아이디와 동일할 경우 값을 넣어주고 출력
				if (list[2].equals(member.getId())) {

					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓회원정보〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.printf("\t\t\t아이디    : %s\n", member.getId());
					System.out.printf("\t\t\t이름       : %s\n", member.getName());
					System.out.printf("\t\t\t나이       : %d\n", member.getAge());
					System.out.printf("\t\t\t주소       : %s\n", member.getAddress());
					System.out.printf("\t\t\t성별       : %s\n", member.getGender());
					System.out.printf("\t\t\t전화번호 : %s\n", member.getPnum());
					System.out.printf("\t\t\t쿠폰번호 : %s\n", member.getCoupon());
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

				}

			}

		} catch (Exception e) {

		}

		System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.\n");
		scan.nextLine();
	}

}