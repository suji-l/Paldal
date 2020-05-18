package com.test.place;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PlaceBasic {

	/*
	 * 
	 * 1. 명소찾기 > 2. 인기List 출력 3. 지역선택(overriding) 4. 카테고리선택(if)- 맛집,놀거리,명소 5. 명소
	 * List출력(sort > DurationTime or '가나다' 선택) 6. 명소선택 (if) 7. 데이터 출력
	 * 
	 */
	
	private String placeNum; // 장소 고유번호
	private String name; // 업소명
	private String description; // 설명
	private String address; // 지역
	private double starAverage; // 별점
	private int durationTime; // 체류시간
	private int category; // 카테고리
	private boolean reservationPosibility; // 예약 가능 여부
	private int price; // 가격

	public static void hotPlaceList() {
		// 이 부분은 예시로 해놓았습니다. String 받아와서 String.format 혹은 printf로 출력해주시면 됩니다.
		String[] hotList = new String[] { "구스토타코", "서울특별시 마포구", "4.8", "맛집" };
		System.out.println("\n\t\t\t                    🔥  오늘의 HOT 🔥");
		System.out.println("\t\t\t");
		System.out.println("\t\t 👉   순위        이름                       위치              별점       카테고리 ");
		System.out.println("\t\t    1    망원 한강공원     서울특별시 마포구      4.8       문화재");
		System.out.printf("\t\t    2     %s        %s      %s       %s", hotList[0], hotList[1], hotList[2],
				hotList[3]);
	}

	public static void printMain() {
		System.out.println("\n\t\t\t==============================");
		System.out.println("\t\t\t1. My Page");
		System.out.println("\t\t\t2. 명소 찾기");
		System.out.println("\t\t\t0. 로그아웃");
		System.out.println("\t\t\t==============================\n");
	}

	public static void findPlace(boolean loginStatus) {
		String selectNum = "";
		Scanner scan = new Scanner(System.in);

		// 인기 리스트 출력
		PlaceBasic.hotPlaceList();

		// 장소 선택 화면 출력
		boolean placeFlag = true;
		while (placeFlag) {
			placeSelectMain placeMain = new placeSelectMain();
			System.out.println("\n\t\t\t==============================");
			System.out.println("\t\t\t1. 지역선택 [ 특별시 & 도 ]");
			System.out.println("\t\t\t0. 뒤로가기");
			System.out.println("\t\t\t==============================\n");
			System.out.println("\t\t\t번호 입력:");
			
			selectNum = scan.nextLine();
			if (selectNum.equals("1")) {
				String totalPlace = "";
				// 사용자에게 지역을 입력받음 selectLocal()
				// totalPlace에는 사용자가 선택한 지역이름이 들어있음 ex) "서울특별시 강남구"
				totalPlace = placeMain.selectLocal();
				
				if (!totalPlace.equals("")) {
					// 위에서 입력받은 "서울특별시 강남구"의 명소 리스트를 출력하기 위한 개체 선언
					GetInfoOfPlaceMain getListOfLocal = new GetInfoOfPlaceMain();
					
					// 로그인 여부를 넣어줌 ( 장소 설명창에서 비회원의 예약하기, 리뷰쓰기를 금지하기 위해서)
					getListOfLocal.setLoginStatus(loginStatus);
					while (true) {
						// 날씨 출력
						getListOfLocal.weather();
						
						// 카테고리 출력
						System.out.println("\n\t\t\t=========== 카테고리 ===========");
						System.out.println("\t\t\t1. 문화재");
						System.out.println("\t\t\t2. 맛집");
						System.out.println("\t\t\t3. 놀거리");
						System.out.println("\t\t\t0. 뒤로가기");
						System.out.println("\t\t\t==============================\n");
						System.out.println("\t\t\t번호 입력:");
						
						// 카테고리 입력받음
						String categoryNum = scan.nextLine();
						
						if (categoryNum.equals("0")) {
							break;
						}
						// 사용자가 선택한 지역 & 해당되는 카테고리의 명소 리스트 출력
						List<PlaceBasic> allPlaceThatSelectedbyUser = ChoiceLocalData.filterArea(totalPlace.replaceAll(" ", ""),categoryNum);
						// allPlaceThatSelectedbyUser에는 Place객체가 들어가있는 List
						getListOfLocal.getListOfLocal(allPlaceThatSelectedbyUser,totalPlace);
					}

				}
			}
			// 뒤로가기
			else if (selectNum.equals("0")) {
				System.out.println("\n초기 화면으로 돌아갑니다.\n");
				placeFlag = false;
			}
		}
	}

	// 생성자
	public PlaceBasic(String placeNum, String name, String description, String address, double starAverage,
			int durationTime, int category, boolean reservationPosibility, int price) {
		super();
		this.placeNum = placeNum;
		this.name = name;
		this.description = description;
		this.address = address;
		this.starAverage = starAverage;
		this.durationTime = durationTime;
		this.category = category;
		this.reservationPosibility = reservationPosibility;
		this.price = price;
	}

	// getter & setter
	public PlaceBasic(String placeName, String localName) {
		// 더미데이터를 활용해서 PlaceBasic 개체에 지역명 + 가게명과 일치하는 데이터의 정보를 넣어줍니다.
	}

	public String getPlaceNum() {
		return placeNum;
	}

	public void setPlaceNum(String placeNum) {
		this.placeNum = placeNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getStarAverage() {
		return starAverage;
	}

	public void setStarAverage(double starAverage) {
		this.starAverage = starAverage;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public boolean getReservationPosibility() {
		return reservationPosibility;
	}

	public void setReservationPosibility(boolean reservationPosibility) {
		this.reservationPosibility = reservationPosibility;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PlaceBasic [placeNum=" + placeNum + ", name=" + name + ", description=" + description + ", address="
				+ address + ", starAverage=" + starAverage + ", durationTime=" + durationTime + ", category=" + category
				+ ", reservationPosibility=" + reservationPosibility + ", price=" + price + "]";
	}

}