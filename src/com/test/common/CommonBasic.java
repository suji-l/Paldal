package com.test.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.test.member.MemberBasic;

public class CommonBasic {
	static Scanner scan = new Scanner(System.in);

	public static boolean signUpValidation(String insertName, String insertId, String insertPw, String insertPwc,
			String insertAddress, String insertAge, String insertPNum, String insertGender,
			List<MemberBasic> dummyDataMember) {
		// 유효성 검사 실시

		try {
			// 한글이 아닌 경우를 에외 처리
			for (int i = 0; i < insertName.length(); i++) {
				if (insertName.charAt(i) < '가' || insertName.charAt(i) > '힣') {
					throw new Exception("이름은 한글만 입력하세요.");
				}
			}

			// 글자 수 예외 처리
			if (insertName.length() < 2 || insertName.length() > 7)
				throw new Exception("이름은 2-7자만 입력하세요.");

			// 더미 데이터 읽어오기
			for (int i = 0; i < dummyDataMember.size(); i++) {
				if (insertId.equals(dummyDataMember.get(i).getId()))
					throw new Exception("중복된 아이디입니다.");
			}

			boolean flag = false;
			for (int i = 0; i < insertId.length(); i++) {
				if ('a' <= insertId.charAt(i) && insertId.charAt(i) <= 'z') {
					flag = true;
					break;
				}
			}
			if (flag == false)
				throw new Exception("소문자를 1자 이상 입력하세요.");

			for (int i = 0; i < insertId.length(); i++) {
				if ('a' <= insertId.charAt(i) && insertId.charAt(i) <= 'z')
					continue;
				if ('0' <= insertId.charAt(i) && insertId.charAt(i) <= '9')
					continue;
				throw new Exception("아이디는 소문자 또는 숫자만 입력해 주세요.");
			}

			if (insertId.length() < 5 || insertId.length() > 16)
				throw new Exception("아이디는 5-16자만 입력하세요.");

			boolean letter = false, number = false;
			for (int i = 0; i < insertPw.length(); i++) {
				if ('a' <= insertPw.charAt(i) && insertPw.charAt(i) <= 'z') {
					letter = true;
					continue;
				}
				if ('0' <= insertPw.charAt(i) && insertPw.charAt(i) <= '9') {
					number = true;
					continue;
				}
				throw new Exception("비밀번호는 소문자 또는 숫자만 입력해 주세요.");
			}

			if (!letter || !number)
				throw new Exception("비밀번호는 소문자와 숫자의 조합으로 입력해 주세요.");

			if (insertPw.length() < 10 || insertPw.length() > 18)
				throw new Exception("비밀번호는 10-18자만 입력해주세요.");

			if (!insertPw.equals(insertPwc))
				throw new Exception("비밀번호가 일치하지 않습니다.");

			if (insertAddress.length() <= 0 || insertAddress.length() > 30)
				throw new Exception("주소를 올바르게 입력해주세요.");

			Calendar c = Calendar.getInstance();
			insertAge = insertAge.replace("-", "");
			insertAge = insertAge.replace(".", "");
			insertAge = insertAge.replace(",", "");
			if (insertAge.length() != 8)
				throw new Exception("생년월일은 8자로 입력해주세요. ex) 19950817");
			for (int i = 0; i < insertAge.length(); i++) {
				if (insertAge.charAt(i) < '0' || insertAge.charAt(i) > '9')
					throw new Exception("생년월일은 숫자만 입력해주세요.");
			}

			int year = Integer.parseInt(insertAge.substring(0, 4));
			int month = Integer.parseInt(insertAge.substring(4, 6));
			int day = Integer.parseInt(insertAge.substring(6, 8));

			if (month < 1 || month > 13)
				throw new Exception("월은 01-12로 입력해주세요.");
			if (day < 1 || day > 31)
				throw new Exception("일은 01-31로 입력하주세요.");

			int age = 0;
			// 나이 계산
			age = c.get(Calendar.YEAR) - year + 1;

			if (age < 14 || age > 65)
				throw new Exception("14-65살 까지만 가입이 가능합니다.");

			insertPNum.replace("-", "");
			if (insertPNum.length() > 13)
				throw new Exception("전화번호의 길이는 \"-\"포함하여 13자로 입력해주세요. ");				

			if (!insertPNum.substring(0, 2).equals("01"))
				throw new Exception("전화번호의 시작은 \"01*\"로 입력해주세요.");

			System.out.println(insertGender);
			if (!insertGender.equals("여자") && !insertGender.equals("남자")) {
				throw new Exception("올바른 성별을 입력해 주세요 ex) 여자,남자");

			}

		} catch (Exception e) {
			System.out.println();
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t\t"+e.getMessage());
			System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");

			return false;
		}
		return true;
	}

	public static void printMain() {
		System.out.println();
		System.out.println("\n\t\t\t    WELCOME TO PALDAL PLACE     \n");
		System.out.println("\t\t\t         ლ(๑╹ω╹๑ )ლ                  ლ(๑╹ω╹๑ )ლ\n");
		System.out.println("\t\t\t〓〓〓〓〓〓〓 M A I N 〓〓〓〓〓〓〓");
		System.out.println("\t\t\t1.회원 사용");
		System.out.println("\t\t\t2.비회원 사용");
		System.out.println("\t\t\t3.회원가입 ");
		System.out.println("\t\t\t4.관리자 모드");
		System.out.println("\t\t\t5.공지사항 보기");
		System.out.println("\t\t\t6.프로그램 종료");
		System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");

	}

	public static boolean loginValidation(String insertId, String insertPw, ArrayList<MemberBasic> dummyDataMember) {

		// 유효성 검사 실시
		try {

			// ID값이 dummy에 존재하지 않을 때
			boolean idflag = false; //

			if (insertId.length() <= 0)
				throw new Exception("아이디를 입력해주세요.");
			for (int i = 0; i < dummyDataMember.size(); i++) {
				if (insertId.equals(dummyDataMember.get(i).getId())) {
					idflag = true;
					break;
				}
			}
			if (idflag == false)
				throw new Exception("\t\t\t" + "일치하는 아이디가 없습니다.");

			// ID값은 있으나 패스워드가 일치하지 않을 때
			boolean pwflag = false;

			for (int i = 0; i < dummyDataMember.size(); i++) {
				if (insertId.equals(dummyDataMember.get(i).getId())
						&& (insertPw.equals(dummyDataMember.get(i).getPw()))) {
					pwflag = true;
					break;
				}
			}

			if (pwflag == false)
				throw new Exception("\t\t\t비밀번호가 일치하지 않습니다.");

		} catch (Exception e) {
			System.out.println(e.getMessage());

			return false;
		}

		return true;
	}

}
