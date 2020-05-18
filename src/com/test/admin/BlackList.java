package com.test.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.test.member.MemberBasic;

public class BlackList {
	
	private List<MemberBasic> list;
	
	// 블랙리스트 리스트에 저장(신고횟수 5회 이상)
	public BlackList() {
		list = new ArrayList<MemberBasic>();
		try {
			File file = new File("resource\\Member.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			System.out.println("\t\t\t========== L  I  S   T =========");
			System.out.println("\t\t\t번호\t이름\t아이디");
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				// 신고횟수가 5번 이상인 사람 저장
				if(Integer.parseInt(tmp[8]) >= 5) {			
					MemberBasic member = new MemberBasic(
							tmp[0], tmp[1], tmp[2], tmp[3], Integer.parseInt(tmp[4]), tmp[5], 
							tmp[6], tmp[7], Integer.parseInt(tmp[8]), tmp.length == 10 ? tmp[9]: "");	// 쿠폰이 없는 사람은 tmp의 길이가 9가 되기 때문에 3항연산자로 "" 으로 입력하도록 하였다.
					list.add(member);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("\t\t\t오류 발생");
		}
	}
	
	// 블랙리스트 내용 출력
	public void showBlackList() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("\t\t\t" + (i + 1) + "\t" + list.get(i).getName() + "\t" + list.get(i).getId());
		}

	}

}
