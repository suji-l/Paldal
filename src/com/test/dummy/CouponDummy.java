package com.test.dummy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

//쿠폰 더미 데이터
public class CouponDummy {
   
	public CouponDummy(String path) throws Exception {
      //쿠폰 데이터를 넣을 경로
      
      int totalCount = 100;
      
      
      //랜덤을 돌릴 배열 
      String[] name1 = {"강원", "경기", "서울", "인천", "대전", "대구", "울산", "부산", "광주", "제주", "충청", "전라","경상" };
      String[] name2 = {"레져", "커피", "입장료", "음료", "상영관", "주차장", "케이크", "바이크", "오리배"};
      
      String[] target1 = {"놀거리", "맛집", "문화재"};
      
      String[] des1 = {"최소 25,000원 이상시 사용 가능합니다"
                     , "쿠폰사용 후 재발급 받으실 수 있습니다."
                     , "다른 쿠폰과 중복 사용 하실 수 없습니다."
                     , "쿠폰은 다른계정으로 양도 할 수 없습니다."
                     , "배달주문에선 사용할 수 없습니다."};
         
      String[] dc1 = {"10%", "20%", "30%", "40%", "50%", "15%", "25%", "35%", "45%"};
      
      String[] day1 = {"2020.05.20", "2020.06.07", "2020.06.12", "2020.06.27", "2020.06.25"
                     , "2020.06.27", "2020.06.15", "2020.07.01"};
                     
      //중복검사를 해줄 Set 생성
      Set<String> placeSet = new HashSet<String>();
      //Set을 담아둘 문자열
      String temp = "";
      
      //set을 iter한 것을 담아둘 문자열
      String result = "";

      
      // 선언한 Set의 사이즈가 ()개가 되기 전까지 while문을 돌림
      while(placeSet.size() < totalCount) {
         temp = "■"+name1[(int)(Math.random() * name1.length)]
               +"지역"
               +name2[(int)(Math.random() * name2.length)]
                     +"쿠폰";
         placeSet.add(temp);
      }
      
      
      //set을 iterator를 통해 전진 커서로 읽는다 
      Iterator<String> iter = placeSet.iterator();
      
      //랜덤 돌린것을 담아둘 문자열
      String target;
      String des;
      String dc;
      String day;

      //난수 담을 문자열
      String rand;
      
      //BufferedWriter
      BufferedWriter writer = new BufferedWriter(new FileWriter(path+"\\Coupon.dat",true));
      
      
      while(iter.hasNext()) {
         result = iter.next();
         
         //난수 생성
         Random rnd = new Random();
         rand = Integer.toString(rnd.nextInt(90000000+10000000));
         
         
         target = "■"+name1[(int)(Math.random() * name1.length)] + "-" 
                  +target1[(int)(Math.random() * target1.length)];
         
         des = "■" + des1[(int)(Math.random() * des1.length)]; 
         
         
         dc = "■" + dc1[(int)(Math.random() * dc1.length)];

         day = "■" + day1[(int)(Math.random() * day1.length)];
         
         writer.write(rand + result + target + des + dc + day + "\r\n");
      }
      
      //이름 + 대상 + 설명 + 할인률 + 유효기간 순으로 입력한것을 저장
      writer.close();
         
   }

}