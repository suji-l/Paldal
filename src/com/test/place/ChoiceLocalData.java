package com.test.place;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChoiceLocalData {
	
	
	//명소 데이터 배열에 넣는 작업 

	private List<List<PlaceBasic>> list;


	public static List<PlaceBasic> filterArea(String totalPlace, String category) {
		List<List<PlaceBasic>> list = new ArrayList<List<PlaceBasic>>();
		
		List<PlaceBasic>	seoul = new ArrayList<PlaceBasic>();     
		List<PlaceBasic>	inchun = new ArrayList<PlaceBasic>();    
		List<PlaceBasic>	daegu = new ArrayList<PlaceBasic>();     
		List<PlaceBasic>	daejeun = new ArrayList<PlaceBasic>();   
		List<PlaceBasic>	ulsan = new ArrayList<PlaceBasic>();     
		List<PlaceBasic>	busan = new ArrayList<PlaceBasic>();     
		List<PlaceBasic>	guanju = new ArrayList<PlaceBasic>();    
		List<PlaceBasic>	jeju = new ArrayList<PlaceBasic>();      
		List<PlaceBasic>	gyungi = new ArrayList<PlaceBasic>();    
		List<PlaceBasic>	ganwon = new ArrayList<PlaceBasic>();    
		List<PlaceBasic>	chungchung = new ArrayList<PlaceBasic>();
		List<PlaceBasic>	junra = new ArrayList<PlaceBasic>();     
		List<PlaceBasic>	gyunsang = new ArrayList<PlaceBasic>();  

//		File file = new File("C:\\Users\\82109\\Documents\\GitHub\\Paldal\\resource\\Place.dat");
		File file = new File("D:\\Paldal\\resource\\Place.dat");
		
		String line = "";
		int i = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");

				PlaceBasic pb = new PlaceBasic(temp[0], temp[1], temp[2], temp[3], Double.parseDouble(temp[4]),
						Integer.parseInt(temp[5]), Integer.parseInt(temp[6]), temp[7].equals("0") ? true : false, Integer.parseInt(temp[8]));
				// 예약가능여부 
				switch (pb.getAddress().split(" ")[0]) {
					case "서울특별시":
						seoul.add(pb);
						break;
					case "인천광역시":
						inchun.add(pb);
						break;
					case "대전광역시":
						daejeun.add(pb);
						break;
					case "대구광역시":
						daegu.add(pb);
						break;
					case "울산광역시":
						ulsan.add(pb);
						break;
					case "부산광역시":
						busan.add(pb);
						break;
					case "광주광역시":
						guanju.add(pb);
						break;
					case "제주특별시":
						jeju.add(pb);
						break;
					case "경기도":
						gyungi.add(pb);
						break;
					case "강원도":
						ganwon.add(pb);
						break;
					case "충청도":
						chungchung.add(pb);
						break;
					case "전라도":
						junra.add(pb);
						break;
					case "경상도":
						gyunsang.add(pb);
						break;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list.add(seoul);
		list.add(inchun);
		list.add(daegu);
		list.add(daejeun);
		list.add(busan);
		list.add(guanju);
		list.add(jeju);
		list.add(gyungi);
		list.add(ganwon);
		list.add(chungchung);
		list.add(junra);
		list.add(gyunsang);
		
		List<PlaceBasic> sortedList = new ArrayList<PlaceBasic>();
		
		for(int k=0;k<list.size();k++) {
			for(int j=0;j<list.get(k).size();j++) {
				// 지역명과 일치하며 사용자가 선택한 카테고리와 일치하는 장소만 selectedList에 넝어준다.
				if(list.get(k).get(j).getAddress().replaceAll(" ","").contains(totalPlace) && list.get(k).get(j).getCategory()==(Integer.parseInt(category))) {
					sortedList.add(list.get(k).get(j));
				}
			}
		}
		return sortedList;
		

		
	}
		
	

	
}
