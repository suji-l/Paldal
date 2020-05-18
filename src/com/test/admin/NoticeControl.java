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

import com.test.notice.NoticeBasic;

public class NoticeControl {
	
	private List<NoticeBasic> list;
	
	// 공지 리스트에 저장
	public NoticeControl() {
		list = new ArrayList<NoticeBasic>();
		try {
			File file = new File("resource\\Notice.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split("■");
				Calendar noticeInsertDate = Calendar.getInstance();
				
				noticeInsertDate.set(
						Integer.parseInt(tmp[1].split("\\.")[0]),
						Integer.parseInt(tmp[1].split("\\.")[1]),
						Integer.parseInt(tmp[1].split("\\.")[2])
						);
				NoticeBasic notice = new NoticeBasic(
						tmp[0], tmp[2], tmp[3], noticeInsertDate);
				list.add(notice);
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println("\t\t\t경로를 확인해주세요.");
		}
	}

	// 공지 등록
	public void insertNotice() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\t\t\t제목 : ");
		String noticeTitle = scan.nextLine();
		System.out.print("\t\t\t내용 : ");
		String noticeContent = scan.nextLine();
		
		Calendar noticeInsertDate = Calendar.getInstance();
		
		String noticeNum = makeRandomNum();
		
		NoticeBasic notice = new NoticeBasic(noticeNum, noticeTitle, noticeContent, noticeInsertDate);
		try {
			writeDummy(notice, true);
			System.out.println("\t\t\t공지사항 등록이 성공하였습니다. 계속하시려면 엔터를 입력해주세요");
		} catch (Exception e) {
			System.out.println();
			System.out.println("\t\t\t다시 등록을 하시려면 엔터를 입력해주시기 바랍니다.");
			scan.nextLine();
			insertNotice();
		}
		
		scan.nextLine();
	}

	// 더미데이터에 값 입력
	private void writeDummy(NoticeBasic notice, boolean exist) throws IOException {
		File file = new File("resource\\Notice.dat");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, exist));
		writer.write(notice.getNoticeNum());
		writer.write("■");
		String insertDate = notice.getNoticeInsertDate().get(Calendar.YEAR) + "."
				+ notice.getNoticeInsertDate().get(Calendar.MONDAY + 1) + "."
				+ notice.getNoticeInsertDate().get(Calendar.DATE) + "."
				+ notice.getNoticeInsertDate().get(Calendar.HOUR_OF_DAY);
		writer.write(insertDate);
		writer.write("■");
		writer.write(notice.getNoticeTitle());
		writer.write("■");
		writer.write(notice.getNoticeContent() + "\n");

		writer.close();
	}



	// 공지번호 난수 생성
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
	
	// 공지사항 출력
	public void printNoticeList() {
		System.out.println("\t\t\t공지 번호\t제목");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("\t\t\t"+(i+1) + "\t"+list.get(i).getNoticeTitle());
		}
			
	}

	// 공지 삭제
	public void deleteNotice(String noticeNum) {
		list.remove(Integer.parseInt(noticeNum)-1);
		try {
			writeDummy(list.get(0), false);
			for (int i = 1; i < list.size(); i++) {
				writeDummy(list.get(i), true);
			}
		} catch (Exception e) {
			System.out.println();
		}
		
	}

}
