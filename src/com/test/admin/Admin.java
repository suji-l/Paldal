package com.test.admin;

import java.util.Scanner;

import com.test.coupon.CouponBasic;

public class Admin {

	private Scanner scan = new Scanner(System.in);
	private String pw;

	public String getPw() {
		return pw;
	}
	
	public void place() {
		System.out.println("\t\t\t========== M  E  N   U =========");
		System.out.println("\t\t\t1: 명소 등록");
		System.out.println("\t\t\t2: 명소 수정");
		System.out.println("\t\t\t3: 명소 삭제");
		System.out.println("\t\t\t0: 뒤로 가기");
		System.out.print("\t\t\t번호 입력 : ");
		String insertPlaceControl = scan.nextLine();
		PlaceControl placeControl = new PlaceControl();
		if (insertPlaceControl.equals("1")) {
			placeControl.registPlace();			// 명소 등록
		} else if(insertPlaceControl.equals("2")) {
			System.out.println("\t\t\t========== L  I  S   T =========");
			// 명소 리스트 출력
			placeControl.printPlaceList();
			while(true) {
				System.out.println("\t\t\t수정할 명소의 번호를 선택해주세요. 뒤로가기를 원하시면 0을 입력해주세요.");
				String placeNum = scan.nextLine();
				if(placeNum.equals("0")) {
					place();		// 뒤로가기
					break;
				}
				try {
					// 명소 삭제
					placeControl.deletePlace(placeNum);			
					break;
				} catch (Exception e) {
					System.out.println("\t\t\t올바른 명소 번호를 입력해주세요!");
				}
				
			}
			
			
			System.out.println("\t\t\t수정할 내용을 입력해주세요.");
			// 명소 삭제 후 재등록하는 작업
			placeControl.registPlace();
		} else if(insertPlaceControl.equals("3")) {
			System.out.println("\t\t\t========== L  I  S   T =========");
			// 명소 리스트 출력
			placeControl.printPlaceList();
			while(true) {
				System.out.println("\t\t\t삭제할 명소의 번호를 선택해주세요. 뒤로가기를 원하시면 0을 입력해주세요.");
				String placeNum = scan.nextLine();
				if(placeNum.equals("0")) {
					// 뒤로가기
					place();	
					break;
				} 
				try {
					placeControl.deletePlace(placeNum);		// 명소 삭제
					System.out.println("\t\t\t명소가 삭제되었습니다. 계속하시려면 엔터를 입력해주세요.");
					scan.nextLine();
					break;
				} catch (Exception e) {
					System.out.println("\t\t\t올바른 명소 번호를 입력해주세요!");
				}
				
			}
			
			
		} else if(!insertPlaceControl.equals("0")) {
			System.out.println("\t\t\t올바른 숫자를 입력해주세요!");
			place();	// 숫자 입력 오류시 다시 출력
		}
	}
	
	public void blackList() {
		BlackList blackList = new BlackList();
		// 블랙리스트 목록 출력
		blackList.showBlackList();
		System.out.println("\t\t\t목록을 출력하였습니다. 뒤로 가시려면 0을 입력해주세요.");
		while(true) {
			String input = scan.nextLine();
			if(input.equals("0")) {
				break;
			} else {
				System.out.println("\t\t\t올바른 숫자을 입력해주세요");
			}
		}
		
	}

	public void member() {
		MemberControl memberControl = new MemberControl();
		// 회원 목록 출력
		memberControl.printMemberList();	
		while(true) {
			System.out.print("\t\t\t자세히 보고 싶은 회원의 번호를, 뒤로가기를 원하시면 0을 입력해주세요 : ");
			String memberNum = scan.nextLine();
			
			if(!memberNum.equals("0")) {
				try {
					// 회원 한명의 상세 정보
					memberControl.showDetail(memberNum);
					System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.");
					scan.nextLine();
					member();	// 회원 정보 출력 후 다시 회원 목록 띄우기
					break;
				} catch (Exception e) {
					System.out.println("\t\t\t회원 번호를 정확하게 입력해주세요.");
				}
			} else {	// 입력이 0이면 뒤로가기
				break;
			}
		}
		
		
	}

	public void coupon() {
		CouponControl coupon = new CouponControl();
		System.out.println("\t\t\t========== M  E  N   U =========");
		System.out.println("\t\t\t1. 쿠폰 목록 조회");
		System.out.println("\t\t\t2. 쿠폰 발급");
		System.out.println("\t\t\t3. 쿠폰 삭제");
		System.out.println("\t\t\t4. 쿠폰 부여");
		System.out.println("\t\t\t0. 뒤로 가기");
		System.out.print("\t\t\t번호 입력 : ");
		String insertCouponControl = scan.nextLine();
		if(insertCouponControl.equals("1")) {	
			System.out.println("\t\t\t========== L  I  S   T =========");
			// 쿠폰 리스트 출력
			coupon.printCouponList();
			System.out.println("\t\t\t자세히 볼 쿠폰 번호를 입력해주세요. 뒤로 가실 고객께서는 0을 입력해주세요");
			
			while(true) {
				System.out.print("\t\t\t입력 : ");
				String inputCouponNum = scan.nextLine();
				
				if(!inputCouponNum.equals("0")) {
					try {
						coupon.showDetail(inputCouponNum);
						System.out.println();
						System.out.println("\t\t\t계속하시려면 엔터를 입력해주시기 바랍니다.");
						scan.nextLine();
						coupon();
						break;
					} catch (Exception e) {
						System.out.println("\t\t\t올바른 쿠폰 번호를 입력해주세요.");
					}
				}else {
					coupon();
					break;
				}
			}
			 
		} else if(insertCouponControl.equals("2")) {
			// 쿠폰 등록
			coupon.insertCoupon();
			coupon();
			
		} else if(insertCouponControl.equals("3")) {
			System.out.println("\t\t\t========== L  I  S   T =========");
			// 쿠폰 목록 호출
			coupon.printCouponList();
			while(true){
				System.out.println("\t\t\t삭제할 쿠폰 번호를 입력해주세요. 뒤로가기를 원하시면 0을 입력해주세요.");
				System.out.print("\t\t\t입력 : ");
				String inputCouponNum = scan.nextLine();
				if(inputCouponNum.equals("0")) {
					coupon();
					break;
				} else {
					try {
						coupon.deleteCoupon(inputCouponNum);
						System.out.println("\t\t\t쿠폰이 삭제되었습니다. 계속하시려면 엔터를 입력해주세요");
						scan.nextLine();
						coupon();
						break;
					} catch (Exception e) {
						System.out.println("\t\t\t올바른 쿠폰 번호를 입력해주세요.");
					}
				}
				
			}
			
		} else if(insertCouponControl.equals("4")) {
			
			while(true) {
				System.out.println("\t\t\t========== L  I  S   T =========");
				coupon.printCouponList();
				System.out.println("\t\t\t부여할 쿠폰 번호를 입력해주세요. 뒤로가기를 원하시면 0을 입력해주세요.");
				System.out.print("\t\t\t입력 : ");
				String inputCouponNum = scan.nextLine();
				if(inputCouponNum.equals("0")) {
					coupon();
					break;
				} else {
					CouponBasic couponToMember = coupon.getList().get(Integer.parseInt(inputCouponNum)-1);
					MemberControl member = new MemberControl();
					member.printMemberList();
					System.out.println("\t\t\t부여할 회원 번호를 입력해주세요. 뒤로가기를 원하시면 0을 입력해주세요.");
					String memberNum = scan.nextLine();
					member.addCoupon(memberNum, couponToMember);
					System.out.println("\t\t\t쿠폰을 부여하였습니다. 계속하시려면 엔터를 입력해주세요");
					scan.nextLine();
					break;
				}
			}
			
		} else if(!insertCouponControl.equals("0")) {
			System.out.println("\t\t\t올바른 숫자를 입력해주세요!");
			coupon();
		}		
	}

	public void notice() {
		System.out.println("\t\t\t========== M  E  N   U =========");
		System.out.println("\t\t\t1.공지사항 추가");
		System.out.println("\t\t\t2.공지사항 수정");
		System.out.println("\t\t\t3.공지사항 삭제");
		System.out.println("\t\t\t0.뒤로 가기");
		
		while(true) {
			System.out.print("\t\t\t번호 입력 : ");
			String insertNoticeControl = scan.nextLine();
			NoticeControl noticeControl = new NoticeControl();
			try {
				if(insertNoticeControl.equals("1")) {
					noticeControl.insertNotice();
					notice();	// 공지 등록 후 다시 공지 메소드 실행
					break;
				} else if(insertNoticeControl.equals("2")) {
					noticeControl.printNoticeList();	// 공지 목록 출력
					System.out.print("\t\t\t수정할 공지 번호를 입력해주세요. 뒤로가기를 원하시면 0을 입력해주세요 : ");
					String noticeNum = scan.nextLine();
					if(noticeNum.equals("0")) {			// 뒤로 가기
						notice();
						break;
					}
					noticeControl.deleteNotice(noticeNum);	// 공지 삭제
					System.out.println("\t\t\t수정할 내용을 입력해주세요.");
					noticeControl.insertNotice();			// 삭제 후 재등록
					scan.nextLine();
					notice();								// 공지 등록 후 다시 공지 메소드 실행
					break;
				} else if(insertNoticeControl.equals("3")) {
					noticeControl.printNoticeList();
					System.out.print("\t\t\t삭제할 공지 번호를 입력해주세요. 뒤로가기를 원하시면 0을 입력해주세요 : ");
					String noticeNum = scan.nextLine();
					if(noticeNum.equals("0")) {		// 뒤로가기
						notice();
						break;
					}
					noticeControl.deleteNotice(noticeNum);
					System.out.println("\t\t\t공지가 삭제되었습니다. 계속하시려면 엔터를 입력해주세요.");
					scan.nextLine();
					notice();						// 공지 삭제 후 다시 공지 메소드 실행
					break;
				} else if(!insertNoticeControl.equals("0")) {
					System.out.println("\t\t\t올바른 숫자를 입력해주세요!");
					notice();
					break;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("\t\t\t올바른 숫자를 입력해주세요.");
			}
			
			
		}
		
	}

	public void review() {
		ReviewControl review = new ReviewControl();
		while(true) {
			System.out.println("\t\t\t========== M  E  N   U =========");
			System.out.println("\t\t\t1.모든 리뷰 보기");
			System.out.println("\t\t\t2.장소별 리뷰 보기");
			System.out.println("\t\t\t3.작성자별 리뷰 보기");
			System.out.println("\t\t\t0.뒤로 가기");
			
			System.out.print("\t\t\t입력 : ");
			String reviewInput = scan.nextLine();
			if(reviewInput.equals("1")) {
				review.listAllReview();			// 리뷰 전부 출력
				System.out.println();
				System.out.println("\t\t\t날짜순으로 리뷰를 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
				scan.nextLine();
			} else if(reviewInput.equals("2")) {
				PlaceControl place = new PlaceControl();
				place.printPlaceList();			// 장소 리스트 출력
				System.out.print("\t\t\t장소 번호 입력, 뒤로가기를 원하시면 0을 입력해주세요.: ");
				String inputPlaceNum = scan.nextLine();
				if(inputPlaceNum.equals("0")) {
					review();					// 뒤로가기
					break;
				}
				try{
					review.listByPlace(inputPlaceNum);
					System.out.println();
					System.out.println("\t\t\t장소별 리뷰를 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
					scan.nextLine();
				} catch (Exception e) {
					System.out.println("\t\t\t올바른 번호를 입력해주세요.");
					System.out.println();
				}
				
			} else if(reviewInput.equals("3")) {
				MemberControl member = new MemberControl();
				member.printMemberList();
				System.out.print("\t\t\t회원 번호 입력, 뒤로가기를 원하시면 0을 입력해주세요. : ");
				String inputMemberNum = scan.nextLine();
				
				if(inputMemberNum.equals("0")) {
					review();
					break;
				}
				try {
					review.listByMember(inputMemberNum);
					System.out.println();
					System.out.println("\t\t\t작성자별 리뷰를 출력하였습니다. 계속하시려면 엔터를 입력해주세요.");
					scan.nextLine();
				} catch (Exception e) {
					System.out.println("\t\t\t올바른 번호를 입력해주세요.");
				}
				
			} else if(!reviewInput.equals("0")) {
				System.out.println("\t\t\t올바른 번호를 입력해주세요.");
			} else {
				break;
			}
			
		}
		
	}

	public void Statistics() {
		
		StatisticsControl statistics = new StatisticsControl();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("\t\t\t========== M  E  N   U =========");
			System.out.println("\t\t\t1. 시간대별 방문자 추이 보기");
			System.out.println("\t\t\t2. 월별 예약 추이 보기");
			System.out.println("\t\t\t3. 카테고리별 체류시간 추이");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.print("\t\t\t입력 : ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				try {
					statistics.timeStatistics();
					System.out.println("\t\t\t계속하시려면 엔터를 입력해주세요.");
					scan.nextLine();
				} catch (Exception e) {
					System.out.println("\t\t\t시간대별 방문자 에러 발생");
				}
			} else if (input.equals("2")) {
				statistics.reservationStatistics("resource\\Reservation.dat");
				System.out.println("\t\t\t계속하시려면 엔터를 입력해주세요.");
				scan.nextLine();
			} else if (input.equals("3")) {
				statistics.palceDurationTimeStatistics("resource\\Place.dat");
				System.out.println("\t\t\t계속하시려면 엔터를 입력해주세요.");
				scan.nextLine();
			} else if(input.equals("0")) {
				break;
			}
		}
		
	}


}