package com.test.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class StatisticsBasic {

	private Calendar dateData;// 날짜
	private int[] numOfvisitorByHourList;// 시간대별 방문자수
	private int countReservation;// 일별 예약자

	// 생성자
	public StatisticsBasic(Calendar dateData, String numOfvisitorByHourList, int countReservation) {
		this.dateData = dateData;
		String[] tmp = numOfvisitorByHourList.split(",");
		for (int i = 0; i < tmp.length; i++) {
			this.numOfvisitorByHourList[i] = Integer.parseInt(tmp[i]);
		}
		this.countReservation = countReservation;

	}

	public StatisticsBasic() {
		// TODO Auto-generated constructor stub
	}

	public void visitorGraph() {
		// 더미파일 로드
		File file = new File("resource\\Statistics.dat");

		// 오늘날짜
		Calendar cal = Calendar.getInstance();
		// 날짜 형식
		SimpleDateFormat time = new SimpleDateFormat("yyyy.MM.dd");
		// 오늘 날짜에 년 월 일을 writetime에 넣어주고
		String writetime = time.format(cal.getTime());
		System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		System.out.print(writetime);
		System.out.print(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
		String line = "";

		ArrayList<String> Person = new ArrayList<String>();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((line = reader.readLine()) != null) {

				// 시간별 인원수를 파일에서 스플릿해서 temp에 넣어주기
				String[] temp = line.split("■");
				// 파일에서 오늘 날짜 연월일인것만! person에 넣어주기
				if (line.contains(writetime)) {
					Person.add(temp[1]);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// 시간별 인원수 전체를 ,로 짤라서 배열에 넣어주기
		String[] timePerson = Person.get(0).split(",");

		System.out.println();

		String[][] graph = new String[10][23];

		// 인원수에 따라 출력 ㅇㅇ
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
			for (int j = 0; j < graph[0].length; j++) {
				System.out.printf("%s   ", graph[i][j]);
			}
			System.out.println();
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
		System.out.println(
				"0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23 ");
		System.out.println("\n\t\t\t\t\t시간별 방문자 추이");
	}


	// 날짜
	public Calendar getDateData() {
		return dateData;
	}

	public void setDateData(Calendar dateData) {
		this.dateData = dateData;
	}

	// 일별 예약자
	public int getCountReservation() {
		return countReservation;
	}

	public void setCountReservation(int dailyReservation) {
		this.countReservation = dailyReservation;
	}

	// 시간대별 방문자수
	public int[] getNumOfvisitorByHourList() {
		return numOfvisitorByHourList;
	}

	public void setNumOfvisitorByHourList(String numOfvisitorByHourList) {
		String[] tmp = numOfvisitorByHourList.split(",");
		for (int i = 0; i < tmp.length; i++) {
			this.numOfvisitorByHourList[i] = Integer.parseInt(tmp[i]);
		}
	}

}