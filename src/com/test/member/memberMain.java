package com.test.member;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.common.CommonBasic;

public class memberMain {
	static Scanner scan = new Scanner(System.in);
	private static String userId;

	public static MemberBasic login(ArrayList<MemberBasic> dummyDataMember) {
//		System.out.println(dummyDataMember.get(0).getCoupon());
		boolean loginFlag;

		System.out.print("\t\t\t ID : ");
		String insertId = scan.nextLine();
		System.out.print("\t\t\t PW : ");
		String insertPw = scan.nextLine();
		System.out.println();

		// 유효성 검사
//		boolean isVaild = CommonBasic.loginValidation(insertId, insertPw, dummyDataMember);
		MemberBasic logInMember =CommonBasic.loginValidation(insertId, insertPw, dummyDataMember); 
//		if (isVaild) {
//
//			// 로그인을 성공하면 static 변수인 Id에 id를 넣어준다.
//			System.out.println("\n\t\t\t로그인에 성공했습니다.");
//			setUserId(insertId);
//			loginFlag = true;
//
//			// 로그인 실패시 다시시도를 위한 pause
//		} else {
//			loginFlag = false;
//			System.out.printf("\t\t\t[다시 시도하려면 엔터를 눌러주세요.]\n");
//			scan.nextLine();
//
//		}
//
//		return loginFlag;
		if(logInMember == null) {
			loginFlag = false;
			System.out.printf("\t\t\t[다시 시도하려면 엔터를 눌러주세요.]\n");
			scan.nextLine();
			return null;
		} else {
			System.out.println("\n\t\t\t로그인에 성공했습니다.");
			setUserId(insertId);
			loginFlag = true;
			return logInMember;
		}
		
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		memberMain.userId = userId;
	}

}
