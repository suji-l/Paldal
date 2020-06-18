package com.test.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.test.coupon.CouponBasic;

public class CouponControl {
	
	private List<CouponBasic> list;
	
	public CouponControl() {
		list = new ArrayList<CouponBasic>();
		
		try {
			File file = new File("resource\\Coupon.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				
				double couponDc = Double.parseDouble((tmp[4].split("%")[0])) / 100.0;
				
				Calendar couponDate = Calendar.getInstance();
				couponDate.set(
						Integer.parseInt(tmp[5].split("\\.")[0]), 
						Integer.parseInt(tmp[5].split("\\.")[1]), 
						Integer.parseInt(tmp[5].split("\\.")[2]));
				
				CouponBasic coupon = new CouponBasic
						(tmp[0], tmp[1], tmp[2], tmp[3], couponDc, couponDate);
				list.add(coupon);
			}
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 쿠폰 리스트 출력 메소드
	public void printCouponList() {
		System.out.println("\t\t\t[번호]\t   [쿠폰 이름]\t\t[사용 기한]");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("\t\t\t" + (i + 1) + "\t" + list.get(i).getCouponName() + "\t"
		+ String.format("%tF", list.get(i).getCouponDate()));
		}
	}
	
	// 쿠폰 등록 메소드
	public void insertCoupon() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		while(true) {
			System.out.println("\t\t\t========== I  N  F   O =========");
			System.out.print("\t\t\t쿠폰 이름 : ");
			String couponName = scan.nextLine();
			System.out.print("\t\t\t할인율(ex.50%) : ");
			String couponDcString = scan.nextLine();
			System.out.print("\t\t\t쿠폰 만료일(2020.01.01) : ");
			String couponDateString = scan.nextLine();
			System.out.print("\t\t\t쿠폰 적용 대상(서울-명소) : ");
			String couponTarget = scan.nextLine();
			System.out.print("\t\t\t쿠폰 설명 : ");
			String couponDescription = scan.nextLine();
			String couponNum = makeRandomNum();
			try {
				// 할인율 뒤에 % 제거
				if(couponDcString.contains("%")) {
					couponDcString = couponDcString.substring(0,couponDcString.length()-1);
				}		
				double couponDc = Integer.parseInt(couponDcString) / 100.0;
				
				Calendar couponDate = Calendar.getInstance();
				couponDate.set(
						Integer.parseInt(couponDateString.split("\\.")[0]), 
						Integer.parseInt(couponDateString.split("\\.")[1]), 
						Integer.parseInt(couponDateString.split("\\.")[2])); 
				
				couponTarget = couponTarget.replace("문화재", "1");
				couponTarget = couponTarget.replace("맛집", "2");
				couponTarget = couponTarget.replace("놀거리", "3");
				
				CouponBasic coupon = new CouponBasic(couponNum, couponName, couponTarget, couponDescription, couponDc, couponDate);
				writeDummy(coupon, true);
				list.add(coupon);
				System.out.println("\t\t\t쿠폰이 등록되었습니다. 추가 등록을 원하시면 엔터를, 뒤로 가시려면 0을 입력해주세요.");
				String input = scan.nextLine();
				if(input.equals("0")) {
					break;
				} else if(input.equals("")) {
					System.out.println("");
				}
			} catch (Exception e) {
				System.out.println("\t\t\t양식에 맞춰 다시 입력해주세요. 뒤로 가시려면 0을, 재입력을 원하시면 엔터를 입력해주세요.");
				String input = scan.nextLine();
				if(input.equals("0")) {
					break;
				} 
			}
		}
		
		
	}
	
	// 더미데이터 추가
	private void writeDummy(CouponBasic coupon, boolean exist) throws Exception {
		File file = new File("resource\\Coupon.dat");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, exist));
		writer.write(coupon.getCouponNum());
		writer.write("■");
		writer.write(coupon.getCouponName());
		writer.write("■");
		writer.write(coupon.getCouponTarget());
		writer.write("■");
		writer.write(coupon.getCouponDescription());
		writer.write("■");
		writer.write((int) (coupon.getCouponDc() * 100) + "%");
		writer.write("■");

		writer.write((String.format("%tF", coupon.getCouponDate())).replace("-", ".") + "\n");
		writer.close();
	}

	// 쿠폰번호 난수 생성
	private String makeRandomNum() {
		Random rnd = new Random();
		int length = 0;
		String result = "";
		for (int i = 0; i < 8; i++) {
			int tmp = rnd.nextInt(12);
			if (tmp % 3 == 0) {
				result += rnd.nextInt(10);
			} else if (tmp % 3 == 1) {
				result += (char) (rnd.nextInt(26) + 65);

			} else {
				result += (char) (rnd.nextInt(26) + 97);
			}
		}
		return result;
	}

	// 쿠폰 상세 얻기
	public void showDetail(String inputCouponNum) {
		CouponBasic coupon = list.get(Integer.parseInt(inputCouponNum) -1 );
		System.out.println();
		System.out.println("\t\t\t=================== I  N  F   O ==================");
		System.out.println();
		System.out.print("\t\t\t쿠폰 이름     : ");
		System.out.println(coupon.getCouponName());
		System.out.print("\t\t\t사용처        : ");
		System.out.println(coupon.getCouponTarget());
		System.out.print("\t\t\t할인율        : ");
		System.out.println((int)(coupon.getCouponDc()*100) + "%");
		System.out.print("\t\t\t쿠폰 설명     : ");
		System.out.println(coupon.getCouponDescription());
		System.out.print("\t\t\t쿠폰 만료기간 : ");
		System.out.println(String.format("%tF", coupon.getCouponDate()));
		
	}

	// 쿠폰 삭제 메소드
	public void deleteCoupon(String inputCouponNum) {
		list.remove(Integer.parseInt(inputCouponNum) -1);
		try {
			writeDummy(list.get(0), false);
			for (int i = 1; i < list.size(); i++) {
				writeDummy(list.get(i), true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<CouponBasic> getList() {
		return list;
	}
	
	
	

}
