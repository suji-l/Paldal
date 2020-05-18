package com.test.dummy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class WeatherDummy {

   // 날씨 더미 데이터
   // 날짜(년-월-일) /날씨/최저/최고 온도

   public WeatherDummy(String path) throws Exception {

      // 총 생성할 더미 데이터의 갯수
      BufferedWriter writer = new BufferedWriter(new FileWriter(path + "\\weather.dat", true));
      Random rnd = new Random();
      int numOfDummy = 1;

      // 날씨를 출력할 날짜->연속된 숫자를 출력해야 함
      String dateData = "";

      // 날씨
      String[] w = { "맑음", "구름많음", "비", "맑은뒤 흐림", "흐림" };

      // 온도
      String temperature = "";

      // 더미 데이터 생성을 위한 for문
      String total = "";
      for (int i = 0; i < numOfDummy; i++) {

         String monthDate = "";
         
         //연속되는 날짜 ■ 날씨 ■ 온도
         for (int j = 1; j < 13; j++) {

            for (int k = 1; k < 32; k++) {
               monthDate = j + "." + k;
               String weather = w[rnd.nextInt(w.length)];
               temperature = Integer.toString(rnd.nextInt(5) + 4) + "℃/" + Integer.toString(rnd.nextInt(10) + 15)
                     + "℃";
               total += "2020." + monthDate + "■" + weather + "■" + temperature + "\n";
               // 파일에 넣기
            }

         }
         writer.write(total);
         
      }
      
      writer.close();
      
   }

}