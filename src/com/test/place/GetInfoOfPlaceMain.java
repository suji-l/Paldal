package com.test.place;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.test.member.memberMain;
import com.test.reservation.ReservationBasic;
import com.test.review.ReviewBasic;

public class GetInfoOfPlaceMain {
	Scanner scan = new Scanner(System.in);
	boolean loginStatus;

	public void getListOfLocal(List<PlaceBasic> allPlaceThatSelectedbyUser, String totalPlace, String sortNum) {
		System.out.printf("\n\t\t\t================= %s =======================\n", totalPlace);
		System.out.print("\t\t\t[순서]\t   [장소명]\t\t [위치]\t       [별점]\n");
		while (true) {
			// 사용자에게 명소 번호를 입력받음
			if (sortNum.equals("1")) {
				// Comparator 인터페이스 구현
				allPlaceThatSelectedbyUser.sort(new Comparator<PlaceBasic>() {

					@Override
					public int compare(PlaceBasic o1, PlaceBasic o2) {

						// 문자코드값을 통한 문자열 우위 비교(가나다순으로 정렬)
						return o1.getName().compareTo(o2.getName());
					}
				});
				int i = 1;
				// 장소명에 대한 가나다순으로 정렬후 순차탐색을한다
				for (PlaceBasic place : allPlaceThatSelectedbyUser) {

					// 순차탐색을 하면서 값을 넣어준다
					System.out.printf("\t\t\t  %d\t%10s\t   %15s\t%.1f\n", i, place.getName(), place.getAddress(),
							place.getStarAverage());
					i++;

				}
				// 받은 번호가 2번이면
			} else if (sortNum.equals("2")) {

				// Comparator 인터페이스 구현
				allPlaceThatSelectedbyUser.sort(new Comparator<PlaceBasic>() {

					// 별점에 대한 내림차순으로 정렬 해준다.
					@Override
					public int compare(PlaceBasic o1, PlaceBasic o2) {

						return (int) ((int) (o2.getStarAverage() * 10.0) - (o1.getStarAverage() * 10.0));
					}

				});

				int i = 1;

				// 체류시간에 대한 내림차순정렬을 순차탐색한다
				for (PlaceBasic place : allPlaceThatSelectedbyUser) {

					// 순차탐색을 하면서 값을 넣어준다
					System.out.printf("\t\t\t  %d\t%10s\t   %15s\t%.1f\n", i, place.getName(), place.getAddress(),
							place.getStarAverage());

					i++;

				}

			}
			// 사용자에게 명소 번호를 입력받음
			System.out.println("\t\t\t==================================================");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.print("\t\t\t번호 입력 : ");

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
				// 체류시간 캘린더 생성
				Calendar startCal = Calendar.getInstance();
				// 정보 진입시 시간측정 start
				long startTick = startCal.getTimeInMillis();
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
				System.out.println("\t\t\t=====================================");
				System.out.print("\t\t\t번호 입력 : ");

				// 사용자에게 번호를 입력받음
				String selectNum = scan.nextLine();
				/// //번호를 받아 다른화면으로 넘어갈때의 시간 측정
				Calendar endCal = Calendar.getInstance();
				long endTick = endCal.getTimeInMillis();
				long durationTime = (endTick - startTick) / 1000;
				selectedPlace.setDurationTime((int) durationTime);
				getDurationTimeOfAllVisitor(selectedPlace);

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
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.");
					scan.nextLine();
					// 리뷰 더보기
				} else if (selectNum.equals("3")) {
					review.moreReview(selectedPlace);
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("\t\t\t계속 하시려면 엔터를 입력해주세요.");
					scan.nextLine();
				} else if (selectNum.equals("0")) {
					System.out.println("\t\t\t리스트로 돌아갑니다.");
					System.out.println();
					System.out.println();
					System.out.println();
					break;
				}

				// 비회원
			} else if (!loginStatus) {
				ReviewBasic review = new ReviewBasic(selectedPlace);
				Calendar startCal = Calendar.getInstance();
				long startTick = startCal.getTimeInMillis();
				printInfo(selectedPlace);
				printReview(selectedPlace);

				// 해당 장소의 리뷰를 보여주기 위해 review 선언
				System.out.println("\t\t\t1. 리뷰 더보기");
				System.out.println("\t\t\t0. 뒤로가기");

				String selectNum = scan.nextLine();
				Calendar endCal = Calendar.getInstance();
				long endTick = endCal.getTimeInMillis();
				long durationTime = (endTick - startTick) / 1000;
				selectedPlace.setDurationTime((int) durationTime);
				getDurationTimeOfAllVisitor(selectedPlace);
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

	public void getDurationTimeOfAllVisitor(PlaceBasic selectedPlace) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("resource\\Place.dat"));
			String line = "";
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("resource\\PlaceForMod.dat"));
				while ((line = reader.readLine()) != null) {

					if ((line.split("■")[1].equals(selectedPlace.getName())
							&& line.split("■")[3].equals(selectedPlace.getAddress()))) {

						int lineDurationTime = Integer.parseInt(line.split("■")[5].substring(0, 2));
						int lineVisitCount = Integer
								.parseInt(line.split("■")[5].substring(line.split("■")[5].indexOf("(") + 1));

						writer.write(selectedPlace.getPlaceNum() + "■" + selectedPlace.getName() + "■"
								+ selectedPlace.getDescription() + "■" + selectedPlace.getAddress() + "■"
								+ String.valueOf(selectedPlace.getStarAverage()) + "■"
								+ String.valueOf(
										(((lineDurationTime * lineVisitCount) + selectedPlace.getDurationTime())
												/ (lineVisitCount + 1)))
								+ "(" + String.valueOf(lineVisitCount + 1) + "■"
								+ String.valueOf(selectedPlace.getCategory()) + "■"
								+ String.valueOf(selectedPlace.getReservationPosibility() == true ? "0" : "1") + "■"
								+ String.valueOf(selectedPlace.getPrice()) + '\n');
					} else {
						writer.write(line + '\n');
					}
				}
				reader.close();
				writer.close();
				BufferedReader reader2 = new BufferedReader(new FileReader("resource\\PlaceForMod.dat"));
				BufferedWriter writer2 = new BufferedWriter(new FileWriter("resource\\Place.dat"));
				line = "";
				while ((line = reader2.readLine()) != null) {
					writer2.write(line + '\n');
				}
				reader2.close();
				writer2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getBlackListCount() {
		try {
			// 멤버 더미 데이터 불러오기
			BufferedReader reader = new BufferedReader(new FileReader("resource\\Member.dat"));
			String line = "";
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("resource\\MemberForMod.dat"));
				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");
					if (line.split("■")[2].equals(memberMain.getUserId())) {
						temp[8] = String.valueOf(Integer.parseInt(temp[8]) + 1);
						if (temp.length == 10) {
							writer.write(temp[0] + "■" + temp[1] + "■" + temp[2] + "■" + temp[3] + "■" + temp[4] + "■"
									+ temp[5] + "■" + temp[6] + "■" + temp[7] + "■" + temp[8] + "■" + temp[9] + '\n');
						} else if (temp.length == 9) {
							writer.write(temp[0] + "■" + temp[1] + "■" + temp[2] + "■" + temp[3] + "■" + temp[4] + "■"
									+ temp[5] + "■" + temp[6] + "■" + temp[7] + "■" + temp[8] + '\n');
						}
					} else {
						writer.write(line + '\n');
					}
				}
				reader.close();
				writer.close();
				reader = new BufferedReader(new FileReader("resource\\MemberForMod.dat"));
				writer = new BufferedWriter(new FileWriter("resource\\Member.dat"));
				line = "";
				while ((line = reader.readLine()) != null) {
					writer.write(line + '\n');
				}
				reader.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printReviewMore(PlaceBasic selectedPlace) {
		// 여기서 5개만 보여주다가 전부다를 보여주는데 혹시 5개 이후것만 보여줄 수 있게가 가능하면 그렇게 작업ㄱ
		BufferedReader reader = null;
		String line = null;
		int i = 1;
		try {
			reader = new BufferedReader(new FileReader("resource\\Review.dat"));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		// 5번째까지 출력하는거랑 같이 num 넣어주고
		int num = 0;

		System.out.println("\t\t\t============================================================================");
		System.out.printf("\t\t\t명소 : %s\n", selectedPlace.getName());
		System.out.println("\t\t\t============================================================================");
		try {
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				// temp[0] = 리뷰번호 temp[1] = 아이디 temp[2] = 리뷰내용
				// temp[3] =리뷰 날짜 temp[4] = 점수 temp[5] = 장소
				// num이 5이상일때 부터 출력
				if (line.contains(selectedPlace.getName()) && num >= 5) {
					System.out.printf("\t\t\t%s님의 리뷰\t\t 작성일:%s\t 점수 : %s\n", temp[1], temp[3], temp[4]);
					System.out.println("\t\t\t" + temp[2]);
					System.out.println(
							"\t\t\t============================================================================");
					i++;

				}
				num++;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void printReview(PlaceBasic selectedPlace) {

		// selectedPlace의 장소번호와 review의 장소번호가 일치하는 것만 골라와서 출력
		BufferedReader reader = null;
		String line = null;
		int i = 1;

		try {
			reader = new BufferedReader(new FileReader("resource\\Review.dat"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int num = 0;
		System.out.println("\t\t\t============================================================================");
		System.out.printf("\t\t\t명소 : %s\n", selectedPlace.getName());
		System.out.println("\t\t\t============================================================================");
		try {
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				if (line.contains(selectedPlace.getName())) {
					System.out.printf("\t\t\t%s님의 리뷰\t\t 작성일:%s\t 점수 : %s\n", temp[1], temp[3], temp[4]);
					System.out.println("\t\t\t" + temp[2]);
					System.out.println(
							"\t\t\t============================================================================");
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
		System.out.println(selectedPlace.getDescription().replace("●", "\n\t\t\t"));

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
				System.out.printf("%15s          ", weatherDataList.get(i)[1]);
			}
			System.out.println();
			System.out.println(
					"  -------------------------------------------------------------------------------------------");
			// 온도 출력
			for (int i = start; i < start + 7; i++) {
				System.out.printf("%10s                ", weatherDataList.get(i)[2]);
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
