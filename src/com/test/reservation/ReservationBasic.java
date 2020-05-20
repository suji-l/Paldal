package com.test.reservation;

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

import com.test.member.memberMain;
import com.test.place.PlaceBasic;

public class ReservationBasic {

   List<String> dummyDataCoupon = new ArrayList<String>();
   List<String> dummyDataReservation = new ArrayList<String>();

   private String reservationNum; // 예약번호
   private String userId;// 사용자 아이디
   private String reservationPlace;// 예약장소
   private int reservationPerson; // 예약인원
   private Calendar reservationDate; // 예약날짜
   private int reservationPay; // 예약비용
   private String reservationCoupon; // 예약시사용쿠폰

   public ReservationBasic(String reservationNum, String userId, String reservationPlace, int reservationPerson,
         Calendar reservationDate, int reservationPay, String reservationCoupon) {
      this.reservationNum = reservationNum;
      this.userId = userId;
      this.reservationPlace = reservationPlace;
      this.reservationPerson = reservationPerson;
      this.reservationDate = reservationDate;
      this.reservationPay = reservationPay;
      this.reservationCoupon = reservationCoupon;
   }

   public ReservationBasic() {
   }

// 예약하기
   public void makeReservation(PlaceBasic place) {
      Scanner scan = new Scanner(System.in);
      System.out.println("\t\t\t ====================== 예약 정보 입력 =======================");
      System.out.print("\t\t\t 인원(명) : ");
      String personCount = scan.nextLine();
      System.out.print("\t\t\t 날짜 : ");
      System.out.println("\t\t\t ex) 2020.05.20");
      String date = scan.nextLine();
      System.out.printf("\t\t\t 가격 : %,d원\n", place.getPrice());

      // 쿠폰 목록 출력
      File file = new File("resource\\Member.dat");
      ArrayList<String> couponList = new ArrayList<String>();
      ArrayList<String[]> possibleCouponList = new ArrayList<String[]>();
      try {
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line = "";

         // 파일 탐색
         while ((line = reader.readLine()) != null) {
            // 멤버 데이터 추출
            String[] data = line.split("■");
            // 로그인 시 입력된 id와 같은 데이터 추출
            if (data[2].equals(memberMain.getUserId())) {
               // 멤버 더미 데이터에서 idx 9 가 쿠폰 번호인데 null값인 경우 때문에 예외처리
               try {
                  // 해당 ID의 보유쿠폰 추출
                  reader = new BufferedReader(new FileReader(new File("resource\\Coupon.dat")));
                  while ((line = reader.readLine()) != null) {
                     // 회원이 가진 쿠폰 번호와 쿠폰번호를 비교
                     if (data[9].contains(line.split("■")[0])) {
                        if (// 지역 유효성 검사
                        line.split("■")[2].substring(0, 2).equals(place.getAddress().substring(0, 2)) &&
                        // 카테고리 유효성 검사
                              Integer.parseInt(line.split("■")[2].substring(3)) == place.getCategory()) {
                           possibleCouponList.add(line.split("■"));
                        }
                     }
                  }
               } catch (Exception e) {
                  System.out.println("\t\t\t사용가능한 쿠폰이 없습니다.");

               }
            }

         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      // 사용가능한 쿠폰 목록 출력
      System.out.println("\t\t\t================= 사용 가능한 쿠폰 =================");
      System.out.println("\t\t\t 쿠폰번호         내용\t설명\t할인율\t유효기간");
      int i = 1;
      for (String[] cp : possibleCouponList) {
         System.out.printf("\t\t\t [%d] %s  %s  %s  %s  %s \n", i, cp[0], cp[1], cp[3], cp[4], cp[5]);
         i++;
      }
      System.out.println("\t\t\t====================================================");

      System.out.println("\t\t\t 사용할 쿠폰 번호 입력:");
      System.out.println("\t\t\t 0번 입력시 사용 안함");
      String couponNum = scan.nextLine();
      double dicountPrice = 0;
      int price = 0;
      if (Integer.parseInt(couponNum) > 0 && Integer.parseInt(couponNum) <= possibleCouponList.size()) {
         double dcPercetage = Double
               .parseDouble(possibleCouponList.get(Integer.parseInt(couponNum) - 1)[4].substring(0, 2)) * 0.01;
         price = (place.getPrice() * Integer.parseInt(personCount));

         dicountPrice = price * dcPercetage;
      } else if (couponNum.equals("0")) {
         System.out.println("\t\t\t쿠폰을 사용하지 않습니다.");
      } else {
         System.out.println("\t\t\t번호를 다시 입력해주세요");
      }

      // 할인된 가격 출력
      System.out.printf("\t\t\t 최종 가격 : %,d원\n", (int) (price - dicountPrice));
      System.out.println("\t\t\t1. 예약 완료");
      System.out.println("\t\t\t0. 예약 취소");
      String selectNum = scan.nextLine();

      if (selectNum.equals("1")) {
         try {
            Calendar cal = Calendar.getInstance();
            BufferedWriter writer = new BufferedWriter(
                  new FileWriter("resource\\Reservation.dat", true));
            writer.write("\n" + makeRandomNum() + "■" + memberMain.getUserId() + "■" + // ID
                  place.getName() + "■" + // 장소명
                  String.valueOf(place.getCategory()) + "■" + // 카테고리
                  date + "■" + String.valueOf(dicountPrice) + "■" + possibleCouponList.get(0)[0] + "\n"); // 사용가능한 쿠폰이 없으면 indexboundation 오류
            writer.close();
            System.out.println("\t\t\t예약이 완료되었습니다.");
            System.out.println("\t\t\t계속하시려면 엔터를 눌러주세요.");
            scan.nextLine();
         } catch (Exception e) {
            Calendar cal = Calendar.getInstance();
            BufferedWriter writer;
            try {
               writer = new BufferedWriter(
                     new FileWriter("resource\\Reservation.dat", true));
               writer.write("\n" + makeRandomNum() + "■" + memberMain.getUserId() + "■" + // ID
                     place.getName() + "■" + // 장소명
                     String.valueOf(place.getCategory()) + "■" + // 카테고리
                     date + "■" + String.valueOf(dicountPrice) + "■" + " " + "\n");
               writer.close();
               System.out.println("\t\t\t예약이 완료되었습니다.");
               System.out.println("\t\t\t계속하시려면 엔터를 눌러주세요.");
               scan.nextLine();
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            
         }

      } else if (selectNum.equals("0")) {
         System.out.println("\t\t\t 이전 화면으로 이동합니다.");
         System.out.println("\t\t\t 계속하시려면 엔터를 입력해주세요.");
         scan.nextLine();
      } else {
         System.out.println("\t\t\t 번호를 다시 입력해주세요.");
      }
   }

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

   // 예약번호
   public String getReservationNum() {
      return reservationNum;
   }

   public void setReservationNum(String reservationNum) {
      this.reservationNum = reservationNum;
   }

   // 예약 장소
   public String getReservationPlace() {
      return reservationPlace;
   }

   public void setReservationPlace(String reservationPlace) {
      this.reservationPlace = reservationPlace;
   }

   // 예약인원
   public int getReservationPerson() {
      return reservationPerson;
   }

   public void setReservationPerson(int reservationPerson) {
      this.reservationPerson = reservationPerson;
   }

   // 예약날짜
   public Calendar getReservationDate() {
      return reservationDate;
   }

   public void setReservationDate(Calendar reservationDate) {
      this.reservationDate = reservationDate;
   }

   // 예약비용
   public int getReservationPay() {
      return reservationPay;
   }

   public void setReservationPay(int reservationPay) {
      this.reservationPay = reservationPay;
   }

   // 예약시 사용 쿠폰
   public String getReservationCoupon() {
      return reservationCoupon;
   }

   public void setReservationCoupon(String reservationCoupon) {
      this.reservationCoupon = reservationCoupon;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

}