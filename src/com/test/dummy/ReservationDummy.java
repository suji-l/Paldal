package com.test.dummy;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class ReservationDummy {
   
   public ReservationDummy(String path) throws Exception{
      
      File file = new File(path+"\\Reservation.dat");
      FileWriter writer = new FileWriter(file, true);
      Random rnd = new Random();
      
      
      String[] n1 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "z", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
      
      String[] n3 = { "팔달문", "청계천 연등회", "국제핸드메이드페어", "경복궁 별빛야행", "움; 트다 헤올 전시회", "어린이 공원", "뷰티인사이드 홍대 전시회" , "KT&G 상상마당" ,"난지캠핑장", "카린 플래그쉽 스토어", "남산 N 서울타워", "해동용궁사", "베니스 비엔날레 아르코미술관 전시회", "공덕시장", "올림픽공원 장미 축제", "팔달문 ", "무주리조트", "에버랜드", "롯데월드", "대한민국 국제 포토페스티벌", "난타전용관", "대둔산도립공원", "무주리조트"};
      
            
      //writer.write("[고유번호]\t[고객명]\t[예약장소명]\t[인원]\t[날짜]\t[가격]\t[적용된쿠폰]\n");
      
      for (int i=1; i<100; i++) {
      String id = n1[rnd.nextInt(n1.length)] 
          + n1[rnd.nextInt(n1.length)]
           + n1[rnd.nextInt(n1.length)]
            + (rnd.nextInt(1000)+100);
      String place = n3[rnd.nextInt(n3.length)];
      writer.write(i+"■");
      writer.write(id+"■");
      writer.write(place+"■");
      writer.write((rnd.nextInt(5)+1)+ "■");
      writer.write("2020.0"+(rnd.nextInt(6)+1)+"."+ (rnd.nextInt(20)+10)+ "■");
      writer.write((rnd.nextInt(60)+1)+"000원"+"■" );
      writer.write((rnd.nextInt(100000000)+1)+"\n");
      }
      
      
      writer.close();
      
      
   }

}