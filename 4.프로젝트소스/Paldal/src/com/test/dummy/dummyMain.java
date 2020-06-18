package com.test.dummy;

public class dummyMain {
	
	public static void createDummy() throws Exception {
//		String path = "C:\\Users\\82109\\Documents\\GitHub\\Paldal\\resource";
		String path = "resource\\";
		
//		CouponDummy couponDummy = new CouponDummy(path);
//		MemberDummy memberDummy = new MemberDummy(path);
//		PlaceDummy placeDummy = new PlaceDummy(path);
		ReservationDummy reservationDummy = new ReservationDummy(path);
//		ReviewDummy reviewDummy = new ReviewDummy(path);
//		StatisticsDummy statisticsDummy = new StatisticsDummy(path);
//		WeatherDummy weatherDummy = new WeatherDummy(path);
		System.out.println("더미 데이터가 생성되었습니다.");
		

	}
}
