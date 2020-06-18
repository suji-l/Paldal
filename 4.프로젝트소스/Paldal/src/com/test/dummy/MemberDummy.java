package com.test.dummy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class MemberDummy {
	static Random rnd;

	static {
		rnd = new Random();
	}
	public MemberDummy(String path) throws Exception {
		File file = new File(path+"\\Member.dat");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < 1000; i++) {
			String member = makeMemberInfo();
			member = i + 1 + "■" + member;
			writer.write(member);
		}
		writer.close();

	}

	private static String makeMemberInfo() {
		String name = makeName();
		String id = makeId();
		String pw = makePw();
		int age = makeAge();
		String address = makeAddr();
		String pnum = makePnum();
		String gender = makeGender();
		int blackListCount = makeBlackListCount();
		String coupon = makeCoupon();
		

		String member = String.format("%s■%s■%s■%d■%s■%s■%s■%d■%s\n", name, id, pw, age, address, pnum, gender, blackListCount, coupon);
		return member;
	}

	private static String makeCoupon() {
		String coupon = "";
		for (int i = 0; i < rnd.nextInt(6); i++) {
			coupon += rnd.nextInt(90000000) + 10000000 + ",";
		}
		if(coupon.length() != 0) {
			coupon = coupon.substring(0,coupon.length()-1);
		}
		
		return coupon;
	}

	private static int makeBlackListCount() {
		return rnd.nextInt(6);
	}

	private static String makeGender() {
		return rnd.nextInt(2) == 1 ? "남자" : "여자";
	}

	private static String makePnum() {
		return "010-" + (rnd.nextInt(9000) + 1000) + "-" + (rnd.nextInt(9000) + 1000);
	}

	private static String makeAddr() {
		String address = "";
		String[] state = { "서울특별시", "인천광역시", "부산광역시", "대전광역시", "대구광역시", "울산광역시", "광주광역시", "경기도", "강원도", "충청북도", "충청남도",
				"전라남도", "전라북도", "경상북도", "경상남도" };
		String[] city = { "가평군", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "남양주시", "동두천시", "부천시", "수원시", "안산시", "안성시",
				"성남시", "하남시", "포천시", "용인시", "의정부시" };
		String[] gu = { "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구",
				"마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구" };
		String[] load = { "고덕로", "장안로", "경인로", "호국로", "팔달로", "강남대로", "양재천로", "광평로", "밤고개로", "삼성로", "봉은사로", "선릉로", "역삼로",
				"테헤란로", "도곡로", "자곡로", "논현로", "언주로" };

		address += state[rnd.nextInt(state.length)] + " ";
		if (!address.contains("특별시") && !address.contains("광역시")) {
			address += city[rnd.nextInt(city.length)] + " ";
		}
		address += gu[rnd.nextInt(gu.length)] + " ";
		address += load[rnd.nextInt(load.length)] + " ";
		address += rnd.nextInt(1000) + 1;
		return address;
	}

	private static int makeAge() {
		return rnd.nextInt(50) + 15;
	}

	private static String makePw() {
		String pw = "";
		for (int i = 0; i < rnd.nextInt(4) + 5; i++) {
			pw += (char) (rnd.nextInt(26) + 97);
		}
		for (int i = 0; i < rnd.nextInt(10) + 3; i++) {
			pw += rnd.nextInt(10);
		}
		return pw;
	}

	private static String makeId() {

		// 'a' -> 97, 'z' -> 122
		String name = "";
		for (int i = 0; i < rnd.nextInt(4) + 3; i++) {
			name += (char) (rnd.nextInt(26) + 97);
		}
		for (int i = 0; i < rnd.nextInt(10); i++) {
			name += rnd.nextInt(10);
		}
		if (name.length() <= 4 && name.length() >= 17)
			name = makeId();
		return name;
	}

	private static String makeName() {
		// name = name1 + name2 + name2
		String[] name1 = { "김", "이", "박", "최", "백", "권", "윤", "장", "정", "신" };
		String[] name2 = { "동", "현", "지", "호", "진", "영", "태", "성", "민", "승", "환", "종", "선", "찬", "우", "식", "윤", "예",
				"혜", "대" };

		return name1[rnd.nextInt(name1.length)] + name2[rnd.nextInt(name2.length)] + name2[rnd.nextInt(name2.length)];
	}

}
