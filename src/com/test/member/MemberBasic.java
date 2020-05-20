package com.test.member;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import com.test.common.CommonBasic;

public class MemberBasic {
	private static String memberNum; // 회원번호
	private String name; // 회원 이름
	private String id; // 회원 id
	private String pw; // 회원 비밀번호
	private static int age; // 회원 나이
	private String address; // 회원 주소
	private static String pnum; // 회원 전화번호
	private String gender; // 회원 성별
	private int blackListCount; // 신고 횟수
	private String[] coupon; // 쿠폰 리스트

	static Scanner scan = new Scanner(System.in);
	// 로그인 메소드

	public static void signUp(ArrayList<MemberBasic> dummyDataMember) throws Exception {
		Scanner scan = new Scanner(System.in);

		// return받은 selectNum이 "1"=약관동의 일 경우 회원가입 입력 loop
		if (accept(scan).equals("1")) {

			while (true)

			{

				// 회원가입 입력
				System.out.println("\t\t\t==============================");
				System.out.println("\t\t\t\t정보를 입력해 주세요");
				System.out.println("\t\t\t==============================");
				System.out.println();
				System.out.println("\t\t\t이름은 한글로  2-7자로 입력해주세요.");
				System.out.print("\t\t\t이름 : ");
				String tempName = scan.nextLine();

				System.out.println();
				System.out.println("\t\t\t아이디는 소문자혹은 소문자+숫자로 5-16자로 입력해주세요.");
				System.out.print("\t\t\t아이디 : ");
				String tempId = scan.nextLine();

				System.out.println();
				System.out.println("\t\t\t패스워드는 소문자+숫자로  10-18자로 입력해주세요.");
				System.out.print("\t\t\t패스워드 : ");
				String tempPw = scan.nextLine();

				System.out.print("\t\t\t패스워드 확인 : ");
				String tempPwc = scan.nextLine();

				System.out.println();
				System.out.println(
						"\t\t\t생년월일은 -또는.포함하여 10자로 입력해주세요. " + "\n \t\t\tex) 19920817, 1992-08-17, 1992.08.17");
				System.out.print("\t\t\t생년월일 : ");
				String tempAge = scan.nextLine();

				System.out.println();
				System.out.println("\t\t\t주소는 30자 이내로 입력해주세요. " + "\n \t\t\tex) 서울시 강동구 암사3동 113-1");
				System.out.print("\t\t\t주소 : ");
				String tempAddress = scan.nextLine();

				System.out.println();
				System.out.println("\t\t\t핸드폰 번호는 -을 포함하여 13자로 입력해주세요. " + "\n \t\t\tex) 010-1234-5678");
				System.out.print("\t\t\t핸드폰 번호 : ");
				String tempPnum = scan.nextLine();

				System.out.println();
				System.out.println("\t\t\t성별은 \"여자\"혹은 \"남자\"로 입력해주세요. ");
				System.out.print("\t\t\t성별 : ");
				String tempGender = scan.nextLine();

				// 유효성 검사 메소드 호출 > boolean 값 리턴
				boolean isValid = CommonBasic.signUpValidation(tempName, tempId, tempPw, tempPwc, tempAddress, tempAge,
						tempPnum, tempGender, dummyDataMember);

				// 유효성 검사 리턴값
				if (isValid) {
					int tempNum; // 회원가입 재확인을 위한 변수 선언
					while (true) {

						System.out.println("\t\t\t입력한 정보로 회원가입 하시겠습니까?");
						System.out.println("\t\t\t예 : 1, 아니오 : 0");
						System.out.println("\t\t\t아니오 입력시 첫화면으로 이동됩니다.");
						System.out.print("\t\t\t");
						tempNum = scan.nextInt();
						scan.skip("\r\n");
						System.out.println();
						if (tempNum == 1 || tempNum == 0)
							break;
					}

					if (tempNum == 1) {

						// 나이 계산을 위해 setAge에 입력받은 생년월일을 넘겨줌
						MemberBasic.setAgeForSignUp(tempAge);
						MemberBasic.setPnumForsignUp(tempPnum);
						// dummyDataMember에 입력받은 값을 저장
						dummyDataMember.add(new MemberBasic(memberNum, tempName, tempId, tempPw, age, tempAddress, pnum,
								tempGender, 0, ""));

						// MemberDummy에 입력받은 값을 저장한 dummyDataMember를 매개변수로 넘겨줌
						writeMemberDummy(dummyDataMember);
						System.out.println("\t\t\t==============================\n");
						System.out.println("\t\t\t\t회원가입이 완료되었습니다.\n");
						System.out.println("\t\t\t===============================");
						System.out.println("\t\t\t 초기화면으로 돌아가시려면 엔터를 눌러주세요.");

						scan.nextLine();

						return;

					} else {
						break;
					}
				} else {
					// 유효성 검사 실패 시 재입력을 위한 pause
					System.out.println("\t\t\t다시 입력하려면 엔터를 눌러주세요.");
					scan.nextLine();
				}
			}
			// accept()에게 return 받은 selectNum의 값이 "0"=약관 비동의일 경우 signUp메소드 종료
		} else {
			return;
		}

	}

	public static void setPnumForsignUp(String insertPnum) {

		// pnum format 변경 ex) 010-1234-5678
		if (!insertPnum.contains("-"))
			insertPnum = String.format("%s-%s-%s", insertPnum.substring(0, 3), insertPnum.substring(3, 7),
					insertPnum.substring(7, 11));

		pnum = insertPnum;
	}

	public static void writeMemberDummy(ArrayList<MemberBasic> dummyDataMember) throws Exception {
		// 더미데이터에 추가
		File file = new File("resource\\Member.dat");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

		// MemberDummy의 맨마지막줄에 입력받은 값을 추가해줌.
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getMemberNum());
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getName());
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getId());
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getPw());
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getAge()+"");
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getAddress());
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getPnum());
		writer.write("■");
		writer.write(dummyDataMember.get(dummyDataMember.size() - 1).getGender());
		writer.write("■");
		writer.write(0+"");
		writer.write("■");
		writer.write("");

		writer.close();

	}

	private static String accept(Scanner scan) {

		// 동의/비동의를 위한 변수 선언
		String selectNum = "";

		while (true) {
			System.out.println();
			System.out.println("\t\t\t==============================");
			System.out.println("\t\t\t팔달문 이용약관 문의");
			System.out.println("\t\t\t개인정보 수집 및 이용에 대한 안내");
			System.out.println("\t\t\t위치정보 이용약관 동의");
			System.out.println("\t\t\t이벤트 등 프로모션 알림 메일 수신");
			System.out.println("\t\t\t==============================");
			System.out.println();
			System.out.println("\t\t\t동의하고 회원가입하기 :  1");
			System.out.println("\t\t\t동의하지 않고 뒤로가기 : 0");
			System.out.println();
			System.out.print("\t\t\t입력 :");
			selectNum = scan.nextLine();
			System.out.println();
			System.out.println();

			// selectNum값을 MemberBaisc.signUp()에게 리턴해줌
			if (selectNum.equals("1")) {
				selectNum = "1";
				break;
			} else if (selectNum.equals("0")) {
				selectNum = "0";
				break;
			} else {
				System.out.println("\t\t\t번호를 다시 입력해주세요.");
				System.out.println();
			}
		}

		return selectNum;
	}

	// 멤버 생성자
	public MemberBasic(String memberNum, String name, String id, String pw, int age, String address, String pnum,
			String gender, int blackListCount, String coupon) {
		this.memberNum = memberNum;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.age = age;
		this.address = address;
		this.pnum = pnum;
		this.gender = gender;
		this.blackListCount = blackListCount;
		this.coupon = coupon.split(",");
	}

	public void mainMenu(MemberBasic member) {
		// TODO Auto-generated method stub

	}

	public MemberBasic(String id) {
		this.id = id;
		// getterId를 받아서 이 아이디와 일치하는 정보들을 더미데이터에서 꺼내와서 setter를 통해 데이터를 넣어주면 됨
	}

	// 회원 번호
	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	// 회원 이름
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 회원 id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 회원 pw
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	// 회원 나이
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static void setAgeForSignUp(String insertAge) {
		Calendar c = Calendar.getInstance();
		insertAge = insertAge.replace("-", "");
		insertAge = insertAge.replace(".", "");
		insertAge = insertAge.replace(",", "");
		int year = Integer.parseInt(insertAge.substring(0, 4));
		int month = Integer.parseInt(insertAge.substring(4, 6));
		int day = Integer.parseInt(insertAge.substring(6, 8));
		int tage = 0;
		// 나이 계산
		tage = c.get(Calendar.YEAR) - year + 1;
		age = tage;
	}

	// 회원 주소
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// 회원 전화번호
	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
	}

	// 회원 성별
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// 신고 횟수
	public int getBlackListCount() {
		return blackListCount;
	}

	public void setBlackListCount(int blackListCount) {
		this.blackListCount = blackListCount;
	}

	// 쿠폰 리스트
	public String getCoupon() {
		return Arrays.toString(coupon).substring(1, Arrays.toString(coupon).length() - 1);
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon.split(",");
	}

	@Override
	public String toString() {
		return "Member_Basic [name=" + name + ", id=" + id + ", pw=" + pw + ", age=" + age + ", address=" + address
				+ ", pnum=" + pnum + ", gender=" + gender + ", blackListCount=" + blackListCount + ", coupon="
				+ Arrays.toString(coupon) + "]";
	}

}
