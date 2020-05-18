package com.test.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.test.coupon.CouponBasic;
import com.test.member.MemberBasic;

public class MemberControl {
	
	private List<MemberBasic> list;
	
	// 회원 더미에서 값 읽어오기
	public MemberControl() {	
		list = new ArrayList<MemberBasic>();
		try {
			File file = new File("resource\\Member.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			System.out.println("\t\t\t========== L  I  S   T =========");
			System.out.println("\t\t\t번호\t이름\t아이디");
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				MemberBasic member = new MemberBasic(
						tmp[0], tmp[1], tmp[2], tmp[3], Integer.parseInt(tmp[4]), tmp[5], 
						tmp[6], tmp[7], Integer.parseInt(tmp[8]), tmp.length == 10 ? tmp[9]: "");
				list.add(member);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 리스트에 있는 회원 내용 출력
	public void printMemberList() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("\t\t\t" + (i + 1) + "\t" + list.get(i).getName() + "\t" + list.get(i).getId());
		}

	}

	// 회원 한명의 상세 페이지
	public void showDetail(String memberNum) {
		System.out.println();
		System.out.println("\t\t\t========== I  N  F   O =========");
		MemberBasic member = list.get(Integer.parseInt(memberNum)-1);
		System.out.print("\t\t\t회원 이름 : ");
		System.out.println(member.getName());
		System.out.print("\t\t\t아이디 : ");
		System.out.println(member.getId());
		System.out.print("\t\t\t나이 : ");
		System.out.println(member.getAge());
		System.out.print("\t\t\t전화번호 : ");
		System.out.println(member.getPnum());
		System.out.print("\t\t\t주소 : ");
		System.out.println(member.getAddress());
		System.out.print("\t\t\t성별 : ");
		System.out.println(member.getGender());
		System.out.print("\t\t\t신고 횟수 : ");
		System.out.println(member.getBlackListCount()+"회");
		
	}
	
	public MemberBasic memberByNum(String inputMemberNum) {
		return list.get(Integer.parseInt(inputMemberNum) -1);
	}

}
