package com.test.dummy;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class ReviewDummy {
	public ReviewDummy(String path) throws Exception {
      
      File file = new File(path+"\\Review.dat");
      FileWriter writer = new FileWriter(file);
      Random rnd = new Random();
      
      
      String[] n1 = { "김", "이", "박", "최", "정", "한", "지", "임", "홍", "유", "윤", "백"};
      
      String[] n2 = { "하", "늘", "연", "나", "다", "대", "은", "주", "현", "동", "지", "효", "수", "상", "병", "석", "훈"};
      
      String[] n3 = { "너무 좋아요~", "남자친구랑 기념일에 갔는데 너무 좋았어요", "역에서 가까워서 좋았어요", "생각보다 별루^^", "가족끼리 가기에 좋아요", "추천합니다 ~ ^^", "만족 만족 ", "취향에 따라 평이 갈릴거 같아요" , "뜻깊은 추억 남기고 가요~" ,"최고에요", "아이들이 완전 좋아했네요 ^^", "다음에 또 방문하고 싶네요><", "평일에 갔는데 한적해서 좋았어요", "주말에는 상황봐서 가세요 사람 너무 많아요 ㅠㅠ", "너무 비싸요 ㅠㅠ", "드라이브 할겸 가기 좋아요", "대박사건~ ", "인스타 갬성 사진찍기 좋아영~", "교통편이 좋아요", "재방문 의사 있음!!!", "사람이 미어 터져서 사진찍기도 힘들어요", "쏘쏘", "굿", "대기시간 길어요 점심시간 피하세요", "혼자가지마세요 커플지옥", "낫배드"};
      
            
      for (int i=1; i<1000; i++) {
      String name = n1[rnd.nextInt(n1.length)]
            + n2[rnd.nextInt(n2.length)]
            + n2[rnd.nextInt(n2.length)];
      String temp = n3[rnd.nextInt(n3.length)];
      writer.write(i+"■");
      writer.write(name+"■");
      writer.write(temp+"■");
      writer.write("2020.0"+rnd.nextInt(5)+1+"."+ (rnd.nextInt(20)+10)+ "■");
      writer.write((rnd.nextInt(3)+3) + "■");
      writer.write(rnd.nextInt(100) + 1 + "\n");
      
      }
      writer.close();
   }

}