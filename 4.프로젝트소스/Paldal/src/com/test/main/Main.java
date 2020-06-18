package com.test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.test.admin.Admin;
import com.test.admin.BlackList;
import com.test.admin.CouponControl;
import com.test.admin.MemberControl;
import com.test.admin.NoticeControl;
import com.test.admin.PlaceControl;
import com.test.admin.ReviewControl;
import com.test.common.CommonBasic;
import com.test.dummy.dummyMain;
import com.test.member.MemberBasic;
import com.test.member.memberMain;
import com.test.member.MemberMypage;
import com.test.notice.NoticeNewObject;
import com.test.place.ChoiceLocalData;
import com.test.place.PlaceBasic;
import com.test.place.placeSelectMain;
import com.test.statistics.StatisticsBasic;

public class Main {
	public static boolean loginStatus = false;
	static boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		// 더미데이터
//		dummyMain.createDummy();
		
		boolean flag = true;
		while (flag) {
			// 시간대별 방문자 추이 작성을 위한 메소드
//			StatisticsBasic.writeVisitorData();
			String path = "resource\\";
			ArrayList<MemberBasic> dummyDataMember = loadMember(path + "Member.dat");
			
			// main 화면 출력
			Scanner scan = new Scanner(System.in);
			placeSelectMain.printMainKorea();
			
			CommonBasic.printMain();
			
			// 사용자에게 번호를 입력받을 scanner 선언
			System.out.print("\t\t\t번호 입력 : ");
			String selectNum = scan.nextLine();

			// 회원 선택 시0
			if (selectNum.equals("1")) {
				while (true) {
					// 로그인

					System.out.println("\n\t\t\t〓〓〓〓〓〓〓〓〓〓 L O G I N 〓〓〓〓〓〓〓〓〓〓");
					System.out.println("\t\t\t1. 로그인");
					System.out.println("\t\t\t0. 뒤로 가기");
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
					System.out.println("\t\t\t번호 입력 : ");
					selectNum = scan.nextLine();
					if (selectNum.equals("1")) {
						MemberBasic loginMember = memberMain.login(dummyDataMember);
						
						if(loginMember != null) {
							loginStatus = true; // 로그인 성공
							// 회원 객체 생성
							// 로그인할때 사용한 ID를 갖는 MemberBasic 객체 생성
							while (true) {
								// 로그인 성공화면
								System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
								System.out.printf("\t\t\tID : %s", loginMember.getId());
								PlaceBasic.printMain();
								System.out.println("\t\t\t번호 입력 : ");
								selectNum = scan.nextLine();
								// 마이페이지 메뉴
								if (selectNum.equals("1")) {
									// My Page 메뉴를 반복하는 while문
									// 위에서 생성한 객체를 넣어줌
									MemberMypage memberMypage = new MemberMypage(loginMember);
									memberMypage.mainMenu();
								} else if (selectNum.equals("2")) {
									// 장소찾기
									PlaceBasic.findPlace(true);

								} else if (selectNum.equals("0")) {
									loginStatus = false;
									break;
								}
							}
						} else {
							
						}

					} else if (selectNum.equals("0")) {
						break;
					} else {
						System.out.println("\t\t\t번호를 다시 입력해주세요.");
					}
				}
			}

			// 비회원
			else if (selectNum.equals("2")) {
				// 비로그인 상태에서는 리뷰쓰기랑 예약쓰기 없애야함
				PlaceBasic.findPlace(false);
			}
			// 회원가입
			else if (selectNum.equals("3")) {
				MemberBasic.signUp(dummyDataMember); // 이부분도 매개변수로 더미데이터 넣어줘야함
			}
			// 관리자
			else if (selectNum.equals("4")) {
				String pw = "pw";
				while (true) {
					placeSelectMain.printMainKorea();
					System.out.println("\t\t\t========== 관리자 모드 =========");
					System.out.println("\t\t\t패스워드를 입력해주세요!");
					System.out.println("\t\t\t=============================\n");
					System.out.println("\t\t\t입 력 : ");
					System.out.println("\t\t\t0. 메인 화면");
					String insertPw = scan.nextLine();

					// 패스워드 유효성검사
					if (pw.equals(insertPw)) {
						System.out.println("\t\t\t로그인에 성공했습니다.");
						Admin admin = new Admin();

						while (true) {

							// 관리자 로그인 성공
							System.out.println("\t\t\t========== M  E  N   U =========");
							System.out.println("\t\t\t1: 명소 등록/수정/삭제");
							System.out.println("\t\t\t2: 회원 리스트 보기");
							System.out.println("\t\t\t3: 블랙 리스트 보기");
							System.out.println("\t\t\t4: 쿠폰 관리");
							System.out.println("\t\t\t5: 공지사항 보기");
							System.out.println("\t\t\t6: 리뷰 관리");
							System.out.println("\t\t\t7: 통계 보기");
							System.out.println("\t\t\t0: 로그아웃");
							System.out.println("\t\t\t================================");
							System.out.print("\t\t\t번호 입력 : ");
							if (!scan.hasNext()) {
								selectNum = scan.nextLine();
							}
							selectNum = scan.nextLine();

							// 명소 등록 / 수정 / 삭제
							if (selectNum.equals("1")) {
								admin.place();
							}
							// 회원 리스트
							else if (selectNum.equals("2")) {
								admin.member();

							}
							// 블랙 리스트
							else if (selectNum.equals("3")) {
								admin.blackList();

							}
							// 쿠폰 관리
							else if (selectNum.equals("4")) {
								admin.coupon();

							}
							// 공지사항
							else if (selectNum.equals("5")) {
								admin.notice();
							}
							// 리뷰 관리
							else if (selectNum.equals("6")) {
								admin.review();
							}
							// 통계
							else if (selectNum.equals("7")) {
								admin.Statistics();
							} else if (selectNum.equals("0")) {
								break;
							}
						}

					} else if (insertPw.equals("0")) {
						System.out.println("\t\t\t메인 화면으로 돌아갑니다.");
						System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.");
						scan.nextLine();
						break;
					} else if (!pw.equals(insertPw)) {
						System.out.println("\t\t\t비밀번호가 틀립니다!");
						System.out.println("\t\t\t");
					}
				}

			}
			// 공지사항
			else if (selectNum.equals("5")) {
				// 공지사항 리스트 출력
				NoticeNewObject.notice();
			}
			// 종료
			else if (selectNum.equals("6")) {
				System.out.println("\n\t\t\t========== Good Bye ==========");
				System.out.println("\t\t\t       지금까지 PALDAL PLACE 였습니다");
				System.out.println("\t\t\t                   행복한 하루 되세요 :)");
				System.out.println("\t\t\t==============================\n");
				flag = false;
			} else {
				System.out.println("\n\t\t\t 번호를 다시 입력해주세요.");
			}
		}
	}// main
	public static ArrayList<MemberBasic> loadMember(String path) throws Exception {

		ArrayList<MemberBasic> list = new ArrayList<MemberBasic>();
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
		while ((line = reader.readLine()) != null) {
			String[] tmp = line.split("■");
			list.add(new MemberBasic(tmp[0], tmp[1], tmp[2], tmp[3], Integer.parseInt(tmp[4]), tmp[5], tmp[6], tmp[7],
					Integer.parseInt(tmp[8]), tmp.length == 10 ? tmp[9] : ""));

			// 블랙리스트 카운트, 쿠폰번호 왜 이렇게 넣었는지
		}

		return list;
	}
}