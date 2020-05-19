package com.test.dummy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class PlaceDummy {

   // 명소 더미 데이터
   // 고유번호 / 이름 / 위치 / 별점 AVG / 평균 체류시간 / 카테고리
   public PlaceDummy(String path) throws Exception {
      // 파일 생성 및 Path 설정
      File file = new File(path + "\\Place.dat");
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      Random rnd = new Random();

      // 더미 데이터는 ArrayList 안에 [이름, 위치, 별점] 형식의 List가 들어감.
      List<List<String>> dummyDataFoodPlace = new ArrayList<List<String>>();

      // 가게 이름, 시-구-군 더미 데이터 리스트
      List<String> storeName1 = Arrays.asList("갈비", "진미", "노랑", "호야", "멘야", "가츠오");
      List<String> storeName2 = Arrays.asList("파티", "백반", "냉면", "라멘", "돈부리", "김치찌개");

      List<String> stateList = Arrays.asList("서울특별시", "인천광역시", "대전광역시", "대구광역시", "울산광역시", "부산광역시", "광주광역시", "제주특별시",
            "경기도", "강원도", "충청도", "전라도", "경상도");

      List<String> guList = Arrays.asList("강남구", "강동구", "강북구", "관악구", "광진구", "구로구", "금천구", "도봉구", "동대문구", "동작구",
            "마포구", "서대문구", "서초구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "동구", "미추홀구", "연수구", "남동구",
            "부평구", "계양구", "대덕구", "서구", "유성구", "중구", "수성구", "달서구", "금정구", "사하구", "영도구", "연제구");
      List<String> siList = Arrays.asList("수원시", "성남시", "용인시", "안양시", "안산시", "과천시", "광명시", "군포시", "부천시", "시흥시", "김포시",
            "의왕시", "이천시", "원주시", "춘천시", "강릉시", "동해시", "속초시", "삼척시", "태백시", "홍천군", "철원군", "횡성군", "평창군", "천안시", "공주시",
            "보령시", "아산시", "서산시", "논산시", "금산군", "목포시", "여수시", "나주시", "진도군", "완도군");

      List<String> roList1 = Arrays.asList("이", "가", "미", "지", "수", "한", "판", "교", "의", "왕");
      List<String> roList2 = Arrays.asList("용", "인", "안", "산", "과", "천", "광", "명", "군", "포");
      
      List<String> descriptionList = Arrays.asList("는 오래된 한글 간판이 매력적이며 내부에 빈티지 가구 및 소품이 어우러져있는 곳으로써 연남동의 클래식한 느낌을 그대로 간직하고 있다.", 
                                       "는 서울의 또 다른 핫플레이스인 을지로에서 볼 수 있는 ‘뉴트로’한 감성을 즐길 수 있는 곳이다. 한번쯤은 가볼만한 곳이니 가보는 것을 추천한다.\"", 
                                       "는 강남 중심에 있는 저명한 20년 장인 권택진이 운영하는 오마카세집이다. 세련된 감성과 버거킹의 합작을 맛볼 수 있다. 한번쯤은 가볼만한 곳이니 가보는 것을 추천한다.",
                                       "는 송파 변두리에 있지만 택진감성의 따뜻한 손길로 조리되어 고향의 맛을 떠올리게 한다. 연남동의 클래식한 느낌을 그대로 간직하고 있다. 한번쯤은 가볼만한 곳이니 가보는 것을 추천한다.",
                                       "는 저렴하게 즐길 수 있는 젊은 사람에게 인기가 많은 곳이다. 야경을 바라볼 수 있는 야외 테라스도 준비되어 있다. 연남동의 클래식한 느낌을 그대로 간직하고 있다.",
                                       "는 서울에서 가장 높은 택진빌딩 최상층에 있는 패밀리 레스토랑입니다. 데이트 코스로도 많이 애용되고있으며 최소 3개월 전에는 예약을 하셔야 이용하실 수 있습니다.");
                                       

      
      // 더미 데이터 생성을 위한 for문
      int numOfDummy = 100;
      for (int i = 0; i < numOfDummy; i++) {

         // 부분 변수
         String total = "";
         String storeName = "";
         String state = "";
         String ro = "";
         String temp = "";
         String description = "";
         boolean reservationPosibility = false;
         int price = 0;
         
         // 맛집 이름;
         storeName = storeName1.get((int) (Math.random() * storeName1.size()))
               + storeName2.get((int) (Math.random() * storeName2.size()));
         
         // 설명
         description = storeName + descriptionList.get(rnd.nextInt(5));
         
         // 특별시, 도
         state = stateList.get((int) (Math.random() * stateList.size()));

         // 구, 시
         if (state.contains("특별") || state.contains("광역")) {
            state += " " + guList.get((int) (Math.random() * guList.size()));
         } else {
            state += " " + siList.get((int) (Math.random() * siList.size()));
         }
         
         // 도로명주소
         Supplier<Integer> roNum = () -> rnd.nextInt(50) + 5;
         ro = " " + roList1.get((int) (Math.random() * roList1.size()))
               + roList2.get((int) (Math.random() * roList2.size())) + "로 " + roNum.get();

         // 별점 평균값
         String starAverage = Float.toString((float) (rnd.nextInt(50) * 0.1));

         // 평균 체류시간
         String DurationTime = Integer.toString(rnd.nextInt(40) + 1) +"(" + Integer.toString(rnd.nextInt(15) + 1); 

         // 문화재(1), 맛집(2), 놀거리(3) 카테고리
         String category = Integer.toString(rnd.nextInt(3)+1);

         // 고유번호 / 이름 / 위치 / 별점 AVG / 평균 체류시간 / 카테고리
         reservationPosibility = rnd.nextBoolean();
         String s_reservationPosibility = reservationPosibility == true ? "0" : "1";
         if(reservationPosibility) {
            // 예약 가능할 때에 예약 가능 여부와 가격 추가
            price = rnd.nextInt(20) * 1000;
            // 예약가능하면 true와
            temp = storeName + "■" + description + "■" + state + ro + "■" + starAverage + "■" + DurationTime + "■" + category + "■" + s_reservationPosibility + "■" + Integer.toString(price);   
         } else {
            temp = storeName + "■" + description + "■" + state + ro + "■" + starAverage + "■" + DurationTime + "■" + category + "■" + s_reservationPosibility + "■" + 0;   
         }
         total = Integer.toString(i + 1) + "■" + temp + '\n';
         // 파일에 작성
         writer.write(total);

      }
      writer.close();

   } // main
} // class