package com.test.reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.test.place.PlaceBasic;

public class ReservationBasic {

	List<String> dummyDataCoupon = new ArrayList<String>();
	List<String> dummyDataReservation = new ArrayList<String>();

	private String reservationNum; // 예약번호
	private String userId;// 사용자 아이디
	private String reservationPlace;// 예약장소
	private int reservationPerson; // 예약인원
	private Calendar reservationDate; // 예약날짜
	private int reservationPay; // 예약비용
	private String reservationCoupon; // 예약시사용쿠폰

	public ReservationBasic(String reservationNum, String userId, String reservationPlace, int reservationPerson,
			Calendar reservationDate, int reservationPay, String reservationCoupon) {
		this.reservationNum = reservationNum;
		this.userId = userId;
		this.reservationPlace = reservationPlace;
		this.reservationPerson = reservationPerson;
		this.reservationDate = reservationDate;
		this.reservationPay = reservationPay;
		this.reservationCoupon = reservationCoupon;
	}

	public ReservationBasic() {
	}

// 예약하기
	public void makeReservation(PlaceBasic place) {

		// 쿠폰, 예약 더미데이터 생성

		// 장소에 대한 예약을 진행주시면 됩니다.

		// 사용할 수 있는 쿠폰 출력하고 예약하기
		System.out.println("예약하기");

		// 여기선 뒤로가기를 구현할지 말지 모르겠어서.. 추후에 결정하는 걸로 하겠습니다.
	}

	// 예약번호
	public String getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}

	// 예약 장소
	public String getReservationPlace() {
		return reservationPlace;
	}

	public void setReservationPlace(String reservationPlace) {
		this.reservationPlace = reservationPlace;
	}

	// 예약인원
	public int getReservationPerson() {
		return reservationPerson;
	}

	public void setReservationPerson(int reservationPerson) {
		this.reservationPerson = reservationPerson;
	}

	// 예약날짜
	public Calendar getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Calendar reservationDate) {
		this.reservationDate = reservationDate;
	}

	// 예약비용
	public int getReservationPay() {
		return reservationPay;
	}

	public void setReservationPay(int reservationPay) {
		this.reservationPay = reservationPay;
	}

	// 예약시 사용 쿠폰
	public String getReservationCoupon() {
		return reservationCoupon;
	}

	public void setReservationCoupon(String reservationCoupon) {
		this.reservationCoupon = reservationCoupon;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}