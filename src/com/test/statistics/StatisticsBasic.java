package com.test.statistics;

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