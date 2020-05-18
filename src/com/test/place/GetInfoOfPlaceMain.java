package com.test.place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.test.reservation.ReservationBasic;
import com.test.review.ReviewBasic;

public class GetInfoOfPlaceMain {
	Scanner scan = new Scanner(System.in);
	boolean loginStatus;

	public void getListOfLocal(List<PlaceBasic> allPlaceThatSelectedbyUser, String totalPlace) {
		String placeName = "";
		System.out.printf("\n\t\t\t============ %s ============\n", totalPlace);
		System.out.println("\t\t\t[순서]\t[장소명]\t[위치]\t\t[별점]\n");
		
		// form 맞추기 및 인기순 가나다순 구현 해야함
		while (true) {
			int i = 1;
			for (PlaceBasic place : allPlaceThatSelectedbyUser) {
				System.out.printf("\t\t\t %d  %s %s \t%.1f \n", i, place.getName(), place.getAddress(),
						place.getStarAverage());
				i++;
			}

			System.out.println("\t\t\t=====================================");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t번호 입력 : ");

			// 사용자에게 명소 번호를 입력받음
			String selectNum = scan.nextLine();

			// 선택된 장소의 placeName과 지역이름 넣어줌
			if (Integer.parseInt(selectNum) > 0 && Integer.parseInt(selectNum) <= allPlaceThatSelectedbyUser.size()) {
				
				// 만약 번호가 알맞은 번호라면 해당 장소의 정보를 출력 - getInfoOfPlace()
				getInfoOfPlace(allPlaceThatSelectedbyUser.get(Integer.parseInt(selectNum) - 1));
			} else if (selectNum.equals("0")) {
				break;
			} else {
				System.out.println("\t\t\t번호를 다시 입력해주세요.");
			}
		}

	}

	private void getInfoOfPlace(PlaceBasic selectedPlace) {
		while (true) {
			// 회원 케이스
			if (loginStatus) {
				// 해당 지역의 가게 정보를 갖고있는 place객체 생성
				// 고유번호 이름 설명 주소 별점 체류시간 카테고리 예약가능여부 가격

				// 해당 장소의 review를 출력하기 위한 review 객체 생성
				ReviewBasic review = new ReviewBasic(selectedPlace);
				
				// 장소 정보 출력
				printInfo(selectedPlace);
				// 장소 리뷰 출력
				printReview(selectedPlace);

				System.out.println("\t\t\t1. 예약하기");
				System.out.println("\t\t\t2. 리뷰쓰기");
				System.out.println("\t\t\t3. 리뷰 더보기");
				System.out.println("\t\t\t0. 뒤로가기");

				// 사용자에게 번호를 입력받음
				String selectNum = scan.nextLine();
				
				// 예약하기, 리뷰쓰기, 리뷰 더보기 
				// 장소가 예약 가능할 경우
				if (selectNum.equals("1") && selectedPlace.getReservationPosibility() == true) {
					// 예약을 위한 예약 객체 생성
					ReservationBasic reservation = new ReservationBasic();
					// 예약을 진행하는 makeReservation 메소드 
					reservation.makeReservation(selectedPlace);
				
				// 장소가 예약 불가능할 경우 안내문 출력
				} else if (selectNum.equals("1") && selectedPlace.getReservationPosibility() == false) {
					System.out.println("\t\t\t죄송합니다 예약이 불가능한 지점입니다.");
				}
				
				// 리뷰쓰기
				else if (selectNum.equals("2")) {
					review.writeReview(selectedPlace);

				// 리뷰 더보기
				} else if (selectNum.equals("3")) {
					review.moreReview(selectedPlace);

				} else if (selectNum.equals("0")) {
					System.out.println("리스트로 돌아갑니다.");
					break;
				}
				// 비회원
			} else if (!loginStatus) {
				ReviewBasic review = new ReviewBasic(selectedPlace);
				printInfo(selectedPlace);
				printReview(selectedPlace);

				// 해당 장소의 리뷰를 보여주기 위해 review 선언
				System.out.println("\t\t\t1. 리뷰 더보기");
				System.out.println("\t\t\t0. 뒤로가기");

				String selectNum = scan.nextLine();
				// 예약하기, 리뷰쓰기, 리뷰 더보기
				if (selectNum.equals("1")) {
					review.moreReview(selectedPlace);
				} else if (selectNum.equals("0")) {
					System.out.println("\t\t\t리스트로 돌아갑니다.");
					break;
				} else {
					System.out.println("\t\t\t번호를 다시 입력해주세요.");
				}
			}

		}

	}

	private void printReviewMore(PlaceBasic selectedPlace) {
		// 여기서 5개만 보여주다가 전부다를 보여주는데 혹시 5개 이후것만 보여줄 수 있게가 가능하면 그렇게 작업ㄱ
		BufferedReader reader = null;
		String line = null;
		int i = 1;
		try {
			reader = new BufferedReader(new FileReader("D:\\Paldal\\resource\\Review.dat"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while ((line = reader.readLine()) != null) {

				if (line.contains(selectedPlace.getName())) {
					System.out.print(i + "■");
					System.out.printf("%s\r\n", line);
					i++;

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void printReview(PlaceBasic selectedPlace) {
		// selectedPlace의 장소번호와 review의 장소번호가 일치하는 것만 골라와서 출력
		BufferedReader reader = null;
		String line = null;
		int i = 1;
		try {
			reader = new BufferedReader(new FileReader("D:\\Paldal\\resource\\Review.dat"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int num = 0;
		try {
			while ((line = reader.readLine()) != null) {

				if (line.contains(selectedPlace.getName())) {
					System.out.print(i + "■");
					System.out.printf("%s\r\n", line);
					i++;
					num++;
					// 처음엔 5개만 보여줌
					if (num == 5) {
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printInfo(PlaceBasic selectedPlace) {
		// 형식에 맞게 출력
		// 여기선 그냥 getter만 써서 작성하시면 됩니다.
		System.out.println(selectedPlace.getName());

	}

	public static void weather() {
		String path = "D:\\Paldal\\resource\\weather.dat";
		File dummy = new File(path);

		try {

			// weatherDummy읽어오기
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = "";

			System.out.println("\t\t\t        ◆      주               간                날               씨     ◆");
			System.out.println(
					"  ===========================================================================================");
			// ArrayList 명 수정 필요
			ArrayList<String[]> weatherDataList = new ArrayList<String[]>();

			while ((line = reader.readLine()) != null) {

				// ■ 를 구분자로 list 배열에 데이터 넣어줌
				String[] weatherData = line.split("■");
				// ArrayList에 쪼갠 배열 넣기.
				weatherDataList.add(weatherData);

			}

			// 처음 시작하는 숫자를 변수로 받아서 날짜의 시작 변경
			Calendar cal = Calendar.getInstance();

			int start = cal.get(Calendar.DAY_OF_YEAR) + 2;
			// 날짜 출력
			for (int i = start; i < start + 7; i++) {
				System.out.printf("%13s", weatherDataList.get(i)[0]);
			}
			System.out.println();
			System.out.println(
					"  -------------------------------------------------------------------------------------------");
			// 날씨 출력
			for (int i = start; i < start + 7; i++) {
				System.out.printf(" %15s  ", weatherDataList.get(i)[1]);
			}
			System.out.println();
			System.out.println(
					"  -------------------------------------------------------------------------------------------");
			// 온도 출력
			for (int i = start; i < start + 7; i++) {
				System.out.printf("%13s        ", weatherDataList.get(i)[2]);
			}
			System.out.println();
			System.out.println(
					"  ===========================================================================================");

		} catch (Exception e) {

		}

		System.out.println();

	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
}
