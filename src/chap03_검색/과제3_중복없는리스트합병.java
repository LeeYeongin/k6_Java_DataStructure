package chap03_검색;

//중복이 없는 리스트를 merge하는 버젼

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class 과제3_중복없는리스트합병 {
//string 정렬, binary search 구현
//1단계: string, 2단계: string 객체,  Person 객체들의 list
//file1: 서울,북경,상해,서울,도쿄, 뉴욕,부산
//file2: 런던, 로마,방콕, 도쿄,서울,부산
//file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장

	static ArrayList<String> removeDuplicate(ArrayList<String> al) {
		//구현할 부분 : 리스트에서 중복을 제거한다, 정렬후 호출하는 것을 전제로 구현
		int cnt = 0;
		for(int i=0; i<al.size(); i++) {
			cnt = Collections.frequency(al, al.get(i));
			if(cnt > 1) {
				cnt--;
				while(cnt > 0) {
					al.remove(al.get(i));
					cnt--;
				}
			}
		}
		
		return al;
	}
	
	static void trimSpace(String[]arr) {
		for(int i=0; i<arr.length; i++) {
			arr[i]=arr[i].replaceAll(" ", "");
		}
	}
	
	static void makeList(String[] sarray1, List<String>list1) {
		for(String st : sarray1) {
			list1.add(st);
		}
	}
	
	static List<String> mergeList(List<String> list1, List<String> list2) {  
		/*
		 * list3 = merge(list1, list2);으로서 새로운 리스트에 정렬 값 순서로 merge하는 알고리즘 구현 
		 */
		ArrayList<String> list3 = new ArrayList<>();
		
		int i = 0, j = 0;
		while(i < list1.size() || j < list2.size()) {
			if(i == list1.size()) {
				while(j < list2.size()) {
					list3.add(list2.get(j));
					j++;
				}
			} else if(j == list2.size()) {
				while(i < list1.size()) {
					list3.add(list1.get(i));
					i++;
				}
			} else {
				if(list1.get(i).compareTo(list2.get(j)) > 0) {
					list3.add(list2.get(j));
					j++;
				} else if(list1.get(i).compareTo(list2.get(j)) < 0) {
					list3.add(list1.get(i));
					i++;
				} else {
					// 중복되는 값은 하나만 넣기
					list3.add(list1.get(i));
					i++;
					j++;
				}
			}
		}
		
		return list3;
		
	}
	
	public static void main(String[] args) {
		try {
			/*
			 * 자바 교재 547: 이클립스 > edu 프로젝트 - 마우스 우측 > New>File >a.txt 생성
			 * 입력 데이터를 다음과 같이 만든다:
			 *    서울,도쿄,북경,상해,서울,도쿄, 뉴욕,부산
			 *        상해,도쿄
			 *          서울, 도쿄
			 * 자바 교재 580: Path 클래스 - 파이썬 유사 
			 */
			Path input1 = Paths.get("a1.txt");
			byte[] bytes1 = Files.readAllBytes(input1);

			Path input2 = Paths.get("a2.txt");
			byte[] bytes2 = Files.readAllBytes(input2);
			//readAllBytes: 파일의 모든 바이트를 읽어오는 메서드입니다. 
			//이 메서드는 파일을 열고 파일의 크기만큼 바이트를 읽어서 바이트 배열로 반환합니다.
			String s1 = new String(bytes1);
			String s2 = new String(bytes2);
			
			System.out.println("입력 스트링: s1 = " + s1);
			System.out.println("입력 스트링: s2 = " + s2);
//			String[] sarray1 = s1.split("[,\\s]+\r\n"); // 자바 regex \n으로 검색
//			String[] sarray2 = s2.split("[,\\s]+\r\n"); //file에서 enter키는 \r\n으로 해야 분리됨 (이 정규식으로 안돼서 아래 정규식으로 실행)
			String[] sarray1 = s1.split("[,\\s]+");
			String[] sarray2 = s2.split("[,\\s]+");

			showData("스트링 배열 sarray1", sarray1);
			showData("스트링 배열 sarray2", sarray2);

			trimSpace(sarray1); // 공백 제거
			trimSpace(sarray2);

			showData("trimSpace() 실행후 :스트링 배열 sarray1", sarray1);
			showData("trimSpace() 실행후 :스트링 배열 sarray2", sarray2);
			System.out.println("+++++++\n");
			
			// file1에서 read하여 list1.add()한다.
			// 배열을 list로 만드는 방법
			// 방법1:
			ArrayList<String> list1 = new ArrayList<>();
			makeList(sarray1, list1);
			showList("리스트1: ", list1);

			// 방법2
			ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sarray2));
			showList("리스트2: ", list2);
			
			System.out.println("+++++++ collection.sort()::");
			Collections.sort(list1);
			showList("정렬후 리스트1: ", list1);

//			Arrays.sort(list2, null);//에러 발생 확인하고 이유는? ->  ArrayList는 배열 기반이 아닌 동적인 크기를 가진 리스트이므로 사용 불가
			Collections.sort(list2);
			showList("정렬후 리스트2: ", list2);	
	
			//정렬된 list에서 중복 제거 코드
			list1 = removeDuplicate(list1);
			list2 = removeDuplicate(list2);
			showList("중복 제거후 리스트1: ", list1);	
			showList("중복 제거후 리스트2: ", list2);	

			List<String> list3 = new ArrayList<>();
			
			// 방법3:
			list3 = mergeList(list1, list2);
			showList("merge후 합병리스트: ", list3);	

			// ArrayList를 배열로 전환
			String[] st = list3.toArray(new String[list3.size()]);
			
			// binary search 구현
			String item = "도쿄";
			int idx = binarySearch(st,item);
			System.out.println("\nbinarySearch로 찾은 "+item+"의 위치: "+ (idx < 0 ? "찾을 수 없습니다." : idx));
			
			// 정렬된 list3을 file에 출력하는 코드 완성
			System.out.println("\n" + "file에 출력:");
			int bufferSize = 10240;
			
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			writeFile(list3, buffer);
			
			// FileOutputStream 이용
			FileOutputStream file = new FileOutputStream("c.txt");
			FileChannel channel = file.getChannel(); 
			
			channel.write(buffer);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void showList(String string, List<String> list1) {
		System.out.println(string);
		for(String st: list1) {
			System.out.println(st);
		}
	}
	
	static void showData(String string, String[] sarray1) {
		System.out.println(string);
		for(String s:sarray1) {
			System.out.println(s);
		}
	
	}
	
	static void writeFile(List<String> list3, ByteBuffer buffer) {
		String b = " ";
		for (String sx : list3) {
			System.out.println(" " + sx);
			buffer.put(sx.getBytes());
			buffer.put(b.getBytes());
		}
		buffer.flip(); // 읽기 모드로 변경
	/*	L : limit, P : position
	 * 			L<------L
	 *	P<------P
	 * 	|0|1|2|3|4|5|6|7|
	 *	|a|b|c|d| | | | |
	 *	
	 *	L = P
	 *	P = 0
	 */
	}
	
	static int binarySearch(String[] st, String item) {
		int pl = 0, pr = st.length;
		int mid, idx = -1;
		
		do {
			mid = (pl + pr) / 2;
			if(st[mid].compareTo(item) > 0) {
				pr = mid -1;
			} else if(st[mid].compareTo(item) < 0) {
				pl = mid + 1;
			} else {
				idx = mid;
				break;
			}
		} while(pl <= pr);
		return idx;
	}
	
	
}
