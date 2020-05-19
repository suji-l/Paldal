package com.test.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.test.place.PlaceBasic;

public class StatisticsControl {
	
	public void timeStatistics() throws Exception {

		File file = new File("resource\\Statistics.dat");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat time = new SimpleDateFormat("yyyy.MM.dd");
		String writetime = time.format(cal.getTime());
		String line = "";

		ArrayList<String> Person = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
			String[] temp = line.split("■");

			if (line.contains(writetime)) {
				Person.add(temp[1]);

			}

		}

		String[] timePerson = Person.get(0).split(",");

		System.out.println();

		String[][] graph = new String[10][23];

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < 23; j++) {
				if (Integer.parseInt(timePerson[j]) >= 10 - i) {
					graph[i][j] = "■";
				} else {
					graph[i][j] = " ";
				}
			}
		}

		for (int i = 0; i < graph.length; i++) {
			System.out.print("\t\t\t");
			for (int j = 0; j < graph[0].length; j++) {
				
				System.out.printf("%s   ", graph[i][j]);
			}
			System.out.println();
		}
		System.out.println(
				"\t\t\t----------------------------------------------------------------------------------------------");
		System.out.println(
				"\t\t\t0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23 ");
	}

	public void reservationStatistics(String path) {

		try {

			// 파일 읽어오기
			int mar = 0; // 예약정보 3월저장 변수
			int apr = 0; // 예약정보 4월저장 변수
			int may = 0; // 예약정보 5월저장 변수
			int jun = 0; // 예약정보 6월저장 변수

			// 파싱하기
			File file = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {

				String[] tmp = line.split("■");
				// tmp[4] = 2020.06.20 >> 월을 추출해내기 위한 작업
				String[] tmpDate = tmp[4].split("\\.");

				String month = tmpDate[1];

				// 변수에 월별 예약카운트 저장
				if (month.equals("03")) // 3월 카운트
					mar++;
				else if (month.equals("04")) // 4월 카운트
					apr++;
				else if (month.equals("05")) // 5월 카운트
					may++;
				else if (month.equals("06")) // 6월 카운트
					jun++;

			}
			int avg = ((mar + apr + may + jun) / 100);

			// 그래프 배열 선언
			String[][] graph = new String[avg][4];

			// 그래프 초기화
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < 4; j++) {
					graph[i][j] = "";
				}
			}

			// 예약(월별) 그래프 선언
			for (int i = avg - 1; i >= 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (j == 0) {
						for (int k = i; k >= avg - mar / 50; k--) {
							graph[k][0] = "■";

						}

					} else if (j == 1) {
						for (int k = i; k >= avg - (apr / 50); k--) {
							graph[k][1] = "■";

						}
					} else if (j == 2) {
						for (int k = i; k >= avg - may / 50; k--) {
							graph[k][2] = "■";

						}
					} else if (j == 3) {
						for (int k = i; k >= avg - jun / 50; k--) {
							graph[k][3] = "■";
						}
					}

				}
			}

			// 그래프 찍기
			for (int i = 0; i < graph.length; i++) {
				System.out.print("\t\t\t");

				for (int j = 0; j < graph[0].length; j++) {
					System.out.printf("\t%s", graph[i][j]);
				}
				System.out.println();
			}
			System.out.println("\t\t\t---------------------------------------");
			System.out.println("\t\t\t\t3월\t4월\t5월\t6월 ");
			System.out.println();

		} catch (

		Exception e) {
			System.out.println("StatisticsBasic.reservationStatistics()");
			e.printStackTrace();

		}

	}

	public void palceDurationTimeStatistics(String path) {

		try {
			int culturePlaceDT1 = 0;// 문화재 체류시간
			int foodPlaceDT = 0; // 맛집 체류시간
			int entertainPlaceDT = 0; // 놀거리 체류시간
			ArrayList<PlaceBasic> list2 = new ArrayList<PlaceBasic>();
			ArrayList<String> list = new ArrayList<String>();
			HashMap<Integer, String> hash = new HashMap<Integer, String>();

			// 파싱하기
			File file = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				list2.add(new PlaceBasic(tmp[0], tmp[1], tmp[2], tmp[3], 0,
						Integer.parseInt(tmp[5]), Integer.parseInt(tmp[6]), true, 0));

			}

			// 카테고리 1 : 문화재, 카테고리 2 : 맛집, 카테고리 3 : 놀거리
			// 카테고리별 체류시간 합산
			for (int i = 0; i < list2.size(); i++) {
				if (list2.get(i).getCategory() == 1)
					culturePlaceDT1 += list2.get(i).getDurationTime();
				if (list2.get(i).getCategory() == 2)
					foodPlaceDT += list2.get(i).getDurationTime();
				if (list2.get(i).getCategory() == 3)
					entertainPlaceDT += list2.get(i).getDurationTime();

			}

			// 좌측 기준 명소별 체류시간 합산 /1000
			int avg = ((culturePlaceDT1 + entertainPlaceDT + foodPlaceDT) / 1000);

			System.out.println();
			String[][] graph = new String[avg][3];

			// 그래프 초기화 (NULL값 제거)
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < 3; j++) {
					graph[i][j] = "";
				}
			}

			// 카테고리별로 그래프 선언
			for (int i = avg - 1; i >= 0; i--) {
				for (int j = 0; j < 3; j++) {
					if (j == 0) { // j인자가 0인경우 문화재
						for (int k = i; k >= avg - culturePlaceDT1 / 600; k--) {
							graph[k][0] = "■";
						}

					} else if (j == 1) { // j인자가 1인경우 맛집
						for (int k = i; k >= avg - (foodPlaceDT / 600); k--) {
							graph[k][1] = "★";
						}
					} else if (j == 2) { // j인자가 2인경우 놀거리
						for (int k = i; k >= avg - entertainPlaceDT / 600; k--) {
							graph[k][2] = "●";
						}
					}

				}
			}

			// 그래프 찍기
			for (int i = 0; i < graph.length; i++) {
				System.out.print("\t\t\t");
				for (int j = 0; j < graph[0].length; j++) {
					System.out.printf("\t%s", graph[i][j]);
				}
				System.out.println();
			}

			
			System.out.println("\t\t\t---------------------------------------");
			System.out.println("\t\t\t\t문화재\t맛집\t놀거리\t ");
			System.out.println();

		} catch (Exception e) {
			System.out.println("StatisticsBasic.palceDurationTimeStatistics()");
			e.printStackTrace();
		}

	}

}
