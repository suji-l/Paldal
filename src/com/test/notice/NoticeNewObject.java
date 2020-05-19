package com.test.notice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class NoticeNewObject {

   public static void notice() {

      Scanner scan = new Scanner(System.in);
      String path = "resource\\\\notice.dat";
      File file = new File(path);
      ArrayList<NoticeBasic> noticeList = new ArrayList<NoticeBasic>();

      try {
         BufferedReader reader = new BufferedReader(new FileReader(path));
         String line = "";

         System.out.println("\t\t\t\t   ◆      공       지       사       항      ◆                      ");
         System.out.println("\t\t\t====================================");
         System.out.println("\t\t\t       번호                  제목                      날짜");
         System.out.println("\t\t\t====================================");

         while ((line = reader.readLine()) != null) {

            String[] tmp = line.split("■");
            Calendar noticeInsertDate = Calendar.getInstance();
            noticeInsertDate.set(Integer.parseInt(tmp[1].split("\\.")[0]),
                  Integer.parseInt(tmp[1].split("\\.")[1]) - 1, Integer.parseInt(tmp[1].split("\\.")[2]));
            NoticeBasic notice = new NoticeBasic(tmp[0], tmp[2], tmp[3], noticeInsertDate);
            noticeList.add(notice);

            noticeList.sort((o1, o2) -> o2.getNoticeInsertDate().compareTo(o1.getNoticeInsertDate()));

         }
         reader.close();

         int printNum = 5;
         for (int i = 0; i < printNum; i++) {

            System.out.println("\t\t\t   [" + (i + 1) + "]\t" + noticeList.get(i).getNoticeTitle() + "\t"
                  + String.format("%tF", noticeList.get(i).getNoticeInsertDate()));

         }

         while (true) {

            System.out.println("\t\t\t====================================");
            System.out.println("\t\t\t 1. 공지사항 더보기 ");
            System.out.println("\t\t\t 2. 공지사항 자세히 보기 ");
            System.out.println("\t\t\t 0. 뒤로가기");
            System.out.println("\t\t\t====================================");
            System.out.print("\t\t\t 번호 입력 : ");

            String selectNum = scan.nextLine();

            if (selectNum.equals("1")) {
               // 공지사항 더보기

               System.out.println("\t\t\t====================================");
               System.out.println("\t\t\t       번호                제목          날짜");
               System.out.println("\t\t\t====================================");

               for (int j = 0; j < noticeList.size(); j++) {
                  System.out.println("\t\t\t  [" + (j + 1) + "]\t" + noticeList.get(j).getNoticeTitle() + "\t"
                        + String.format("%tF", noticeList.get(j).getNoticeInsertDate()));

               }

            } else if (selectNum.equals("2")) {
               // 번호를 입력받아 사용자에게 보고싶은 번호를 입력 받으면 그 번호의 공지사항 내용 출력
               System.out.println();
               System.out.println("\t\t\t====================================");
               for (int j = 0; j < noticeList.size(); j++) {
                  System.out.println("\t\t\t  [" + (j + 1) + "]\t" + noticeList.get(j).getNoticeTitle() + "\t"
                        + String.format("%tF", noticeList.get(j).getNoticeInsertDate()));

               }

               // 번호 선택시 번호에 해당하는 공지사항 호출
               System.out.println("\t\t\t====================================");
               System.out.print("\t\t\t 공지사항 번호를 입력해주세요. ");
               String selectNotice = scan.nextLine();

               System.out.println();

               if (Integer.parseInt(selectNotice) > 0 && Integer.parseInt(selectNotice) <= noticeList.size()) {
                  System.out.println("\t\t\t   날짜 : " + String.format("%tF",
                        noticeList.get(Integer.parseInt(selectNotice) - 1).getNoticeInsertDate()));
                  System.out.println(
                        "\t\t\t   제목 : " + noticeList.get(Integer.parseInt(selectNotice) - 1).getNoticeTitle());
                  System.out.println(String.format("\t\t\t   내용 : \r\n %s", "\t\t\t"+ noticeList.get(Integer.parseInt(selectNotice) - 1).getNoticeContent().replace("●", "\n\t\t\t")));
                  
                  
                  ;
                  
                  System.out.println();
               } else {
                  System.out.print("\t\t\t 번호를 다시 입력해주세요.");
               }

            } else if (selectNum.equals("0")) {
               break;
            } else
               System.out.print("\t\t\t 번호를 다시 입력해주세요.");
         }

      } catch (Exception e) {

         System.out.println(e);

      }

   }

}