package com.test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
import com.test.member.memberMypage;
import com.test.place.ChoiceLocalData;
import com.test.place.PlaceBasic;

public class Main {
	public static boolean loginStatus = false;
	static boolean flag = true;
	static Scanner scan = new Scanner(System.in);
	static String path = "resource\\";
	public static void main(String[] args) throws Exception {
		// 더미데이터
//      dummyMain.createDummy();
		// 더미 데이터 불러오기
		// 회원 더미데이터
		ArrayList<MemberBasic> dummyDataMember = loadMember("resource\\Member.dat");
		List<String> dummyDataPlace = new ArrayList<String>();

		// 변수
		boolean flag = true;
		while (flag) {

			// main 화면 출력
			CommonBasic.printMain();

			// 사용자에게 번호를 입력받을 scanner 선언
			System.out.print("\t\t\t번호 입력 : ");
			String selectNum = scan.nextLine();

			// 회원 선택 시
			if (selectNum.equals("1")) {
				while (true) {
					// 로그인

					System.out.println("\n\t\t\t=========== 로 그 인 ===========");
					System.out.println("\t\t\t1. 로그인");
					System.out.println("\t\t\t0. 뒤로 가기");
					System.out.println("\t\t\t==============================\n");
					selectNum = scan.nextLine();
					if (selectNum.equals("1")) {
						boolean loginflag = memberMain.login(dummyDataMember); // 매개변수에 더미데이터 넣어주기
						if (loginflag) {
							loginStatus = true; // 로그인 성공
							// 회원 객체 생성
							// 로그인할때 사용한 ID를 갖는 MemberBasic 객체 생성
							MemberBasic member = new MemberBasic(memberMain.getUserId());
							while (true) {
								// 로그인 성공화면
								System.out.printf("\n\t\t\tID : %s", member.getId());
								PlaceBasic.printMain();
								System.out.println("\t\t\t번호 입력 : ");
								selectNum = scan.nextLine();
								// 마이페이지 메뉴
								if (selectNum.equals("1")) {
									// My Page 메뉴를 반복하는 while문
									// 위에서 생성한 객체를 넣어줌
									memberMypage memberMypage = new memberMypage(member);
									memberMypage.mainMenu();
								} else if (selectNum.equals("2")) {
									// 장소찾기
									PlaceBasic.findPlace(true);

								} else if (selectNum.equals("0")) {
									loginStatus = false;
									break;
								}
							}
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
							System.out.println("\t\t\t번호 입력 : ");
							if (!scan.hasNext()) {
								selectNum = scan.nextLine();
							}
							selectNum = scan.nextLine();

							// 명소 등록 / 수정 / 삭제
							if (selectNum.equals("1")) {
								System.out.println("\t\t\t========== M  E  N   U =========");
								System.out.println("\t\t\t1: 명소 등록");
								System.out.println("\t\t\t2: 명소 수정");
								System.out.println("\t\t\t3: 명소 삭제");
								System.out.println("\t\t\t번호 입력 : ");
								String insertPlaceControl = scan.nextLine();
								PlaceControl placeControl = new PlaceControl();
								if (insertPlaceControl.equals("1")) {
									placeControl.registPlace();
								} else if(insertPlaceControl.equals("2")) {
									System.out.println("\t\t\t========== L  I  S   T =========");
									// 명소 리스트 출력
									placeControl.printPlaceList();
									System.out.println("\t\t\t수정할 명소의 번호를 선택해주세요.");
									String placeNum = scan.nextLine();
									System.out.println("수정할 내용을 입력해주세요.");
									// 명소 삭제 후 재등록하는 작업
									placeControl.deletePlace(placeNum);
									placeControl.registPlace();
									scan.nextLine();
								} else if(insertPlaceControl.equals("3")) {
									System.out.println("\t\t\t========== L  I  S   T =========");
									// 명소 리스트 출력
									placeControl.printPlaceList();
									System.out.println("\t\t\t삭제할 명소의 번호를 선택해주세요.");
									String placeNum = scan.nextLine();
									placeControl.deletePlace(placeNum);
									System.out.println("\t\t\t명소가 삭제되었습니다. 계속하시려면 엔터를 입력해주세요.");
									scan.nextLine();
								}
							}
							// 회원 리스트
							else if (selectNum.equals("2")) {
								MemberControl memberControl = new MemberControl();
								memberControl.printMemberList();
								System.out.print("\t\t\t자세히 보고 싶은 회원의 번호를 입력해주세요 : ");
								String memberNum = scan.nextLine();
								try {
									memberControl.showDetail(memberNum);
									System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.");
									scan.nextLine();
								} catch (Exception e) {
									System.out.println("\t\t\t회원 번호를 정확하게 입력해주세요.");
								}
							}
							// 블랙 리스트
							else if (selectNum.equals("3")) {
								BlackList blackList = new BlackList();
								blackList.showBlackList();
								System.out.println("\t\t\t목록을 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
								scan.nextLine();
								
							}
							// 쿠폰 관리
							else if (selectNum.equals("4")) {
								CouponControl coupon = new CouponControl();
								System.out.println("\t\t\t========== M  E  N   U =========");
								System.out.println("\t\t\t1. 쿠폰 목록 조회");
								System.out.println("\t\t\t2. 쿠폰 발급");
								System.out.println("\t\t\t3. 쿠폰 삭제");
								System.out.println("\t\t\t번호 입력 : ");
								String insertCouponControl = scan.nextLine();
								if(insertCouponControl.equals("1")) {	
									System.out.println("\t\t\t========== L  I  S   T =========");
									coupon.printCouponList();
									System.out.println("\t\t\t자세히 볼 쿠폰 번호를 입력해주세요. 뒤로 가실 고객께서는 엔터만 입력해주세요.");
									String inputCouponNum = scan.nextLine();
									if(!inputCouponNum.equals("")) {
										coupon.showDetail(inputCouponNum);
										System.out.println();
										System.out.println("\t\t\t계속하시려면 엔터를 입력해주시기 바랍니다.");
										scan.nextLine();
									}
								} else if(insertCouponControl.equals("2")) {
									coupon.insertCoupon();
									
								} else if(insertCouponControl.equals("3")) {
									System.out.println("\t\t\t========== L  I  S   T =========");
									coupon.printCouponList();
									System.out.println("\t\t\t삭제할 쿠폰 번호를 입력해주세요.");
									String inputCouponNum = scan.nextLine();
									coupon.deleteCoupon(inputCouponNum);
									System.out.println("\t\t\t쿠폰이 삭제되었습니다. 계속하시려면 엔터를 입력해주세요");
									scan.nextLine();
									
								}
								
							}
							// 공지사항
							else if (selectNum.equals("5")) {
								System.out.println("\t\t\t========== M  E  N   U =========");
								System.out.println("\t\t\t1.공지사항 추가");
								System.out.println("\t\t\t2.공지사항 수정");
								System.out.println("\t\t\t3.공지사항 삭제");
								System.out.print("\t\t\t번호 입력 : ");
								String insertNoticeControl = scan.nextLine();
								NoticeControl noticeControl = new NoticeControl();
								if(insertNoticeControl.equals("1")) {
									noticeControl.insertNotice();
								} else if(insertNoticeControl.equals("2")) {
									noticeControl.printNoticeList();
									System.out.println("\t\t\t수정할 공지 번호를 입력해주세요.");
									String noticeNum = scan.nextLine();
									noticeControl.deleteNotice(noticeNum);
									System.out.println("\t\t\t수정할 내용을 입력해주세요.");
									noticeControl.insertNotice();
									scan.nextLine();
								} else if(insertNoticeControl.equals("3")) {
									noticeControl.printNoticeList();
									System.out.println("\t\t\t삭제할 공지 번호를 입력해주세요.");
									String noticeNum = scan.nextLine();
									noticeControl.deleteNotice(noticeNum);
									System.out.println("\t\t\t공지가 삭제되었습니다. 계속하시려면 엔터를 입력해주세요.");
									scan.nextLine();
								}
								
							}
							// 리뷰 관리
							else if (selectNum.equals("6")) {
								ReviewControl review = new ReviewControl();
								
								System.out.println("\t\t\t========== M  E  N   U =========");
								System.out.println("\t\t\t1.모든 리뷰 보기");
								System.out.println("\t\t\t2.장소별 리뷰 보기");
								System.out.println("\t\t\t3.작성자별 리뷰 보기");
								System.out.print("\t\t\t입력 : ");
								String reviewInput = scan.nextLine();
								if(reviewInput.equals("1")) {
									review.listAllReview();
									System.out.println();
									System.out.println("\t\t\t날짜순으로 리뷰를 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
									scan.nextLine();
								} else if(reviewInput.equals("2")) {
									review.listByPlace();
									System.out.println();
									System.out.println("\t\t\t장소별 리뷰를 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
									scan.nextLine();
								} else if(reviewInput.equals("3")) {
									review.listByMember();
									System.out.println();
									System.out.println("\t\t\t작성자별 리뷰를 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
									scan.nextLine();
								}
							}
							// 통계
							else if (selectNum.equals("7")) {
								System.out.println("통계 메소드");
							} 
							else if (selectNum.equals("0")) {
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
				CommonBasic.noticeListMember();
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
		System.out.println(path);
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
		while ((line = reader.readLine()) != null) {
			String[] tmp = line.split("■");
			list.add(new MemberBasic(tmp[0], tmp[1], tmp[2], tmp[3], Integer.parseInt(tmp[4]), tmp[5], tmp[6], tmp[7],
					0, ""));
		}

		return list;
	}

}