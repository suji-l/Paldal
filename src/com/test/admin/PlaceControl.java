package com.test.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.test.place.PlaceBasic;
import com.test.review.ReviewBasic;

public class PlaceControl {
	private List<PlaceBasic> list;		// 모든 장소의 정보를 담고있는 리스트
	
	public PlaceControl() {
		list = new ArrayList<PlaceBasic>();
		
		try {
			File file = new File("resource\\Place.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				PlaceBasic place = new PlaceBasic(tmp[0], tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]),
						Integer.parseInt(tmp[5]), Integer.parseInt(tmp[6]), tmp[7].equals("1") ? true : false,
						Integer.parseInt(tmp[8]));
				list.add(place);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("경로를 확인해주세요.");
		}
	}
	
	
	
	public List<PlaceBasic> getList() {
		return list;
	}



	public void registPlace() {
		Scanner scan = new Scanner(System.in);
		
		// 정보 입력, trim 메소드로 양 옆 탭 자르기
		System.out.print("\t\t\t업소 명 : ");
		String name = scan.nextLine();
		System.out.print("\t\t\t주소(도로명 주소) : ");
		String address = scan.nextLine();
		System.out.print("\t\t\t카테고리(맛집, 놀거리, 문화재) : ");
		String category = scan.nextLine();
		System.out.print("\t\t\t예약 가능 여부(가능, 불가능) : ");
		String reservationPosibility = scan.nextLine();
		System.out.print("\t\t\t입장료 (원) : ");
		String price = scan.nextLine();
		System.out.print("\t\t\t설명(종료를 원하시면 q를 입력해주세요) : ");
		String description = "";
		String line = "";
		// 정보 입력(다중 처리)
		while (!(line = scan.nextLine()).equals("q")) {
			description = description + line + " ";
		}
		description = description.trim();
		
		try {
			checkInfo(address, category, reservationPosibility, price); // 유효성 검사

			String placeNum = makeRandomNum(); // 난수 8자리 생성
			PlaceBasic place = new PlaceBasic(placeNum, name, description, address, 0, 0,
					category.equals("맛집") ? 1 : 
						category.equals("놀거리") ? 2 : 
							category.equals("문화재") ? 3 : 0 // PlaceBasic
																											// 객체 생성
					, reservationPosibility.equals("가능") ? true : false, Integer.parseInt(price));

			// 더미데이터에 입력
			writeDummy(place, true);
			System.out.println("\t\t\t명소 정보가 등록되었습니다. 계속하시려면 엔터를 눌러주세요.");
			scan.nextLine();
		} catch (Exception e) { // 예외 발생시 메소드 재호출
			System.out.println();
			System.out.println("\t\t\t양식을 다시 확인해 주시기 바랍니다.");
			System.out.println("\t\t\t다시 등록을 하시려면 엔터를 쳐주시기 바랍니다.");
			scan.nextLine();
			registPlace();
		}

	}

	private void writeDummy(PlaceBasic place, boolean exist) throws IOException {
		// 더미데이터에 추가
		File file = new File("resource\\Place.dat");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, exist));
		writer.write(place.getPlaceNum());
		writer.write("■");
		writer.write(place.getName());
		writer.write("■");
		writer.write(place.getDescription());
		writer.write("■");
		writer.write(place.getAddress());
		writer.write("■");
		writer.write(String.format("%.1f", place.getStarAverage()));
		writer.write("■");
		writer.write(place.getDurationTime() == 0 ? "0" : place.getDurationTime() + "");
		writer.write("■");
		writer.write(place.getCategory() + "");
		writer.write("■");
		writer.write(place.getDurationTime()+"");
		writer.write("■");
		writer.write(place.getPrice() + "\n");
		writer.close();
	}

	// 장소번호 난수 생성
	private String makeRandomNum() {
		Random rnd = new Random();
		int length = 0;
		String result = "";
		for (int i = 0; i < 8; i++) {
			int tmp = rnd.nextInt(12);
			if (tmp % 3 == 0) {
				result += rnd.nextInt(10);
			} else if (tmp % 3 == 1) {
				result += (char) (rnd.nextInt(26) + 65);

			} else {
				result += (char) (rnd.nextInt(26) + 97);
			}
		}
		return result;
	}

	// 유효성 검사
	private void checkInfo(String address, String category, String reservationPosibility, String price)
			throws Exception {
		// 정보 유효성 검사 플래그
		boolean addressCheck = false;
		boolean categoryCheck = false;
		boolean reservationCheck = false;
		boolean priceCheck = false;
		

		// 주소 유효성 검사(특별시, 광역시, 도)
		String[] addrDo = address.split(" ");
		if (addrDo[0].equals("서울특별시") || addrDo[0].equals("인천광역시") || addrDo[0].equals("부산광역시")
				|| addrDo[0].equals("대전광역시") || addrDo[0].equals("대구광역시") || addrDo[0].equals("울산광역시")
				|| addrDo[0].equals("광주광역시")) {
			if (addrDo[1].contains("구")) {
				addressCheck = true;
			}
		} else if (addrDo[0].equals("경기도") || addrDo[0].equals("강원도") || addrDo[0].equals("충청도")
				|| addrDo[0].equals("전라도") || addrDo[0].equals("경상도") || addrDo[0].equals("제주도")) {
			if (addrDo[1].contains("시") || addrDo[1].contains("군")) {
				addressCheck = true;
			}

		}

		// 카테고리 유효성검사(놀거리, 맛집, 문화재)
		if (category.equals("놀거리") || category.equals("맛집") || category.equals("문화재")) {
			categoryCheck = true;
		}

		// 예약 가능 여부 유효성 검사
		reservationCheck = reservationPosibility.equals("가능") || reservationPosibility.equals("불가능") ? true : false;

		// 가격 유효성 검사
		priceCheck = Integer.parseInt(price) >= 0 ? true : false;
		if (addressCheck == false || categoryCheck == false || reservationCheck == false || priceCheck == false)
			throw new Exception();
	}
	
	public void printPlaceList() {
		System.out.println("\t\t\t명소 번호\t업소명\t주소\t카테고리 ");

		for (int i = 0; i < list.size(); i++) {
			String a = list.get(i).getCategory() == 1 ? "맛집" : list.get(i).getCategory() == 2 ? "놀거리" : "문화재";
			System.out.println("\t\t\t" + (i + 1) + "\t" + list.get(i).getName() + "\t" + list.get(i).getAddress()
					+ "\t" + a );
		}

	}

	// 명소 삭제 메소드
	public void deletePlace(String placeNum) {
		list.remove(Integer.parseInt(placeNum)-1);
		try {
			writeDummy(list.get(0), false);
			for (int i = 1; i < list.size(); i++) {
				writeDummy(list.get(i), true);
			}
		} catch (IOException e) {
			System.out.println();
		}
		
	}
	
	public PlaceBasic placeByNum(String inputPlaceNum) {
		return list.get(Integer.parseInt(inputPlaceNum) - 1);
	}


}
