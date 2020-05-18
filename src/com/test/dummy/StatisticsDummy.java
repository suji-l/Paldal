package com.test.dummy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StatisticsDummy {

	// 통계 더미 데이터
	// 날짜, 시간별 방문자, 일별 예약수
	/*
	 * ArrayList 한줄로 초기화하는 방법 List<자료형> 변수명 = Arrays.asList( 내용) ex) List<String>
	 * nameList2 = Arrays.asList("파티", "백반", "냉면", "라멘", "돈부리", "김치찌개");
	 * 
	 * 
	 */
	public StatisticsDummy(String path) throws Exception {

		// 총 생성할 더미 데이터의 갯수
		int numOfDummy = 100;

		BufferedWriter writer = new BufferedWriter(new FileWriter(path + "\\Statistics.dat", true));

		// 더미 데이터 생성을 위한 for문
		for (int i = 0; i < numOfDummy; i++) {

			// ----------날짜 -------------
			String dateData = "";
			Random rnd = new Random();
			String Month = Integer.toString(rnd.nextInt(12) + 1);
			String dailyReservation = "";
			String Date = "";

			// 31일인 월
			if (Month.equals(1) || Month.equals(3) || Month.equals(5) || Month.equals(7) || Month.equals(8)
					|| Month.equals(10) || Month.equals(12)) {

				Date = Integer.toString(rnd.nextInt(31) + 1);

				// 30일인 월
			} else if (Month.equals(4) || Month.equals(6) || Month.equals(11)) {

				Date = Integer.toString(rnd.nextInt(30) + 1);

				// 2월
			} else {

				Date = Integer.toString(rnd.nextInt(29) + 1);

			}

			// 연월일 정보 String 변수에 받기

			// ------------- 월별 예약자 ---------------------------

			dateData = "2020." + Month + "." + Date;
			dailyReservation = Integer.toString(rnd.nextInt(10) + 1);

			// --------------시간대별 방문자 count ---------------

			// 시간별 방문자 count를 위한 ArrayList 선언
			List<String> visitcountByHour = new ArrayList<String>();

			// Random 객체 선언

			// 총 23개의 시간대의 방문자 수 삽입(난수)
			for (int j = 0; j < 23; j++) {
				visitcountByHour.add(Integer.toString(rnd.nextInt(10)));
			}

			// 총 시간별 방문자를 출력해주는 String 변수 선언
			String s_visitcountByHour = "";
			for (String key : visitcountByHour) {
				s_visitcountByHour += key + ",";
			}

			// 결과값
			s_visitcountByHour = s_visitcountByHour.substring(0, s_visitcountByHour.length() - 1);

			String result = "";
			result = dateData + "■" + s_visitcountByHour + "■" + dailyReservation;

			// 데이터 String변수에 합치기
			// analistic파일에 모은 데이터 넣기
			writer.write(result);
			writer.newLine();

		}
		writer.close();
	}

}
