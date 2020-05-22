package com.test.place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class placeSelectMain {
	Scanner scan = new Scanner(System.in);
	String totalLocal = "";
	boolean flag = false;

	public String selectLocal() {
		while (true) {
			if (totalLocal.equals("")) {
				// 한반도 사진 출력
				printSelcetLocalKorea();

				// 지역 번호 출력
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓지역선택 [특별시 & 도] 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("\t\t\t1: 서울특별시\t");
				System.out.print("2: 경기도\t\t");
				System.out.print("3: 인천광역시\t");
				System.out.print("4: 대전광역시\n");
				System.out.print("\t\t\t5: 대구광역시\t");
				System.out.print("6: 울산광역시\t");
				System.out.print("7: 부산광역시\t");
				System.out.print("8: 광주광역시\n");
				System.out.print("\t\t\t9: 제주도\t\t");
				System.out.print("10: 강원도\t");
				System.out.print("11: 충청도\t");
				System.out.print("12: 전라도\n");
				System.out.print("\t\t\t13: 경상도\n");
				System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

				System.out.print("\t\t\t번호 입력:");
				String selectNum = scan.nextLine();

				if (selectNum.equals("1")) {
					totalLocal = "서울특별시";
				} else if (selectNum.equals("2")) {
					totalLocal = "경기도";
				} else if (selectNum.equals("3")) {
					totalLocal = "인천광역시";
				} else if (selectNum.equals("4")) {
					totalLocal = "대전광역시";
				} else if (selectNum.equals("5")) {
					totalLocal = "대구광역시";
				} else if (selectNum.equals("6")) {
					totalLocal = "울산광역시";
				} else if (selectNum.equals("7")) {
					totalLocal = "부산광역시";
				} else if (selectNum.equals("8")) {
					totalLocal = "광주광역시";
				} else if (selectNum.equals("9")) {
					totalLocal = "제주도";
				} else if (selectNum.equals("10")) {
					totalLocal = "강원도";
				} else if (selectNum.equals("11")) {
					totalLocal = "충청도";
				} else if (selectNum.equals("12")) {
					totalLocal = "전라도";
				} else if (selectNum.equals("13")) {
					totalLocal = "경상도";
				}

				// 지역들 번호 쭉 입력해주세요
				else if (selectNum.equals("0")) {
					break;
				} else {
					System.out.println("\t\t\t올바른 번호를 입력해주세요.");
					break;
				}
				// 입력받은 특별시, 시 출력

			}
			// selectLocal에서 입력받은 시들의 구를 출력해주는 메소드
			selectLocal2(totalLocal);
			if (flag) {
				System.out.printf("\t\t\t최종 선택 지역: %s\n", totalLocal);
				break;
			}
		}
		return totalLocal;
	}

	private void selectLocal2(String local) {

		while (true) {
			System.out.printf("\t\t\t선택한 지역 : %s\n", local);
			// 총 행정구역을 갖는 totalLocalMap 선언
			HashMap<String, ArrayList<String>> totalLocalMap = makeLocalList();

			// 구
			List<String> local2List = new ArrayList<String>();
			System.out.println("\t\t\t\t                       jw  ,w          \r\n"
					+ "\t\t\t\t                     W5WwjjjWD         \r\n"
					+ "\t\t\t\t              ,W55, DWwwWWWWj         \r\n"
					+ "\t\t\t\t              WWwjjjWWWWWWWwWy        \r\n"
					+ "\t\t\t\t              wwwwwwwwWwWWWjWj5       \r\n"
					+ "\t\t\t\t           wWWwwWWwwwjWwwWWWwW5       \r\n"
					+ "\t\t\t\t     W   wDyWWWWwwwjWWWwWjwWWj        \r\n"
					+ "\t\t\t\t   wWwWyw    yDyjWwwwWjyy55yjjzW      \r\n"
					+ "\t\t\t\t  yyyjwWWWyw    jDyy55y   Wj5D   5B8Z \r\n"
					+ "\t\t\t\t       ,wwwWjyj,               5Wj    \r\n"
					+ "\t\t\t\t       wWwwwWWwj5j   W5yy5BDBDDjj     \r\n"
					+ "\t\t\t\t      jy5 ywWWwWWjjyyjWwWWjWWjWjjyE   \r\n"
					+ "\t\t\t\t         jywwwWwwWjyDj55jWWWjDBzW    \r\n"
					+ "\t\t\t\t            yjyyy5DDw jj Byyj         \r\n"
					+ "\t\t\t\t                                   \r\n" + "");

			// totalLocalMap을 돌며 사용자에게 입력받은 곳의 구들을 출력
			for (String local1 : totalLocalMap.keySet()) {
				if (local1.equals(local)) {
					local2List = totalLocalMap.get(local1);
					System.out.println("\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓 지역선택 [ 시 & 군 & 구 ] 〓〓〓〓〓〓〓〓〓〓〓〓〓");
					int i = 1;
					int j = 0;
					System.out.print("\t\t\t");
					for (String valueName : local2List) {
						System.out.printf("%d. %s     ", i, valueName);
						i++;
						j++;
						if (j % 5 == 0) {
							System.out.print("\n\t\t\t");
						}
					}
					System.out.println("\n\t\t\t〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				}

			}

			System.out.print("\t\t\t번호 입력 : ");
			// 사용자에게 입력받은 행정구역을 totalLocal에 넣어줌
			String selectNum = scan.nextLine();
			// 엔터를 쳤을때 java.lang.NumberFormatException: For input string: ""
			if (Integer.parseInt(selectNum) > 0 && Integer.parseInt(selectNum) < local2List.size()) {
				totalLocal += " " + local2List.get(Integer.parseInt(selectNum) - 1);
				flag = true;
				break;
			} else if (selectNum.equals("0")) {
				totalLocal = "";
				break;
			} else {
				System.out.println("\t\t\t올바른 번호를 입력해주세요.");
				break;
			}
		}
	}

	public static void printSelcetLocalKorea() {

		System.out.println("                         \r\n" + "\t\t\t\t\t                  ZZB    \r\n"
				+ "\t\t\t\t\t              B88ZZ      \r\n" + "\t\t\t\t\t          ZZBwW8BE       \r\n"
				+ "\t\t\t\t\t        W98Bz8z8ZZ,      \r\n" + "\t\t\t\t\t     BZZ9BD5B9Z9         \r\n"
				+ "\t\t\t\t\t    3EE8D55D8            \r\n" + "\t\t\t\t\t       5D55DB            \r\n"
				+ "\t\t\t\t\t      y9D5558Zj          \r\n" + "\t\t\t\t\t     wZZE8D55BZ          \r\n"
				+ "\t\t\t\t\t         y8D55BZ    DD(독도)   \r\n" + "\t\t\t\t\t          jD555BZw       \r\n"
				+ "\t\t\t\t\t         ZZD55558B       \r\n" + "\t\t\t\t\t          955555z8       \r\n"
				+ "\t\t\t\t\t          E555DBZE       \r\n" + "\t\t\t\t\t         8989ZZEy        \r\n"
				+ "\t\t\t\t\t        WZEBW            \r\n" + "\t\t\t\t\t                         \r\n"
				+ "\t\t\t\t\t       JJ(제주도)                \r\n" + "");
	}

	public static void printMainKorea() {
		System.out.println("                         \r\n" + "\t\t\t                  ZZB    \r\n"
				+ "\t\t\t              B88ZZ      \r\n" + "\t\t\t          ZZBwW8BE       \r\n"
				+ "\t\t\t        W98Bz8z8ZZ,      \r\n" + "\t\t\t     BZZ9BD5B9Z9         \r\n"
				+ "\t\t\t    3EE8D55D8            \r\n" + "\t\t\t       5D55DB            \r\n"
				+ "\t\t\t      y9D5558Zj          \r\n" + "\t\t\t     wZZE8D55BZ          \r\n"
				+ "\t\t\t         y8D55BZ    DD(독도)   \r\n" + "\t\t\t          jD555BZw       \r\n"
				+ "\t\t\t         ZZD55558B       \r\n" + "\t\t\t          955555z8       \r\n"
				+ "\t\t\t          E555DBZE       \r\n" + "\t\t\t         8989ZZEy        \r\n"
				+ "\t\t\t        WZEBW            \r\n" + "\t\t\t                         \r\n"
				+ "\t\t\t       JJ(제주도)                \r\n" + "");
	}

	public HashMap<String, ArrayList<String>> makeLocalList() {
		// 나머지 구역도 시간 남으면 추가작업 필요
		HashMap<String, ArrayList<String>> localList = new HashMap<String, ArrayList<String>>();

		ArrayList<String> seoul = new ArrayList<String>();

		seoul.add("강남구");
		seoul.add("강동구");
		seoul.add("강북구");
		seoul.add("강서구");
		seoul.add("관악구");
		seoul.add("광진구");
		seoul.add("구로구");
		seoul.add("노원구");
		seoul.add("도봉구");
		seoul.add("동대문구");
		seoul.add("동작구");
		seoul.add("마포구");
		seoul.add("서대문구");
		seoul.add("송파구");
		seoul.add("도봉구");
		seoul.add("서초구");
		seoul.add("양천구");
		seoul.add("은평구");
		seoul.add("성동구");
		seoul.add("영등포구");
		seoul.add("종로구");
		seoul.add("성북구");
		seoul.add("용산구");
		seoul.add("중구");

		ArrayList<String> Kyonggi = new ArrayList<String>();
		Kyonggi.add("수원시");
		Kyonggi.add("안산시");
		Kyonggi.add("군포시");
		Kyonggi.add("안성시");
		Kyonggi.add("성남시");
		Kyonggi.add("과천시");
		Kyonggi.add("부천시");
		Kyonggi.add("오산시");
		Kyonggi.add("용인시");
		Kyonggi.add("광명시");
		Kyonggi.add("시흥시");
		Kyonggi.add("의왕시");
		Kyonggi.add("안양시");
		Kyonggi.add("광주시");
		Kyonggi.add("김포시");
		Kyonggi.add("이천시");
		Kyonggi.add("평택시");
		Kyonggi.add("하남시");
		Kyonggi.add("화성시");
		Kyonggi.add("여주시");

		localList.put("서울특별시", seoul);
		localList.put("경기도", Kyonggi);

		return localList;
	};
}
