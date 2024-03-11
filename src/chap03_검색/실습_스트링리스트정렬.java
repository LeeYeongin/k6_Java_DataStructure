package chap03_검색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
public class 실습_스트링리스트정렬 {

	    public static String[] removeElement1(String[] arr, String item) {
	    	// 코드1: ArrayList로 변경하여 삭제하는 코드
	    	// 배열을 리스트로 변환 > list.remove() 사용
	    	// 삭제된 리스트를 배열로 변환하여 리턴
	    	ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
	    	int cnt = Collections.frequency(list, item)-1; // list에 포함된 item개수 세기
	    	while(cnt > 0) {
	    		list.remove(item);	
	    		cnt--;
	    	}
	    	return list.toArray(String[]::new);
	    	
	    	// 코드2: 배열인 상태로 삭제하는 코드
//	    	int cnt = 0;
//	    	int firstIdx = -1;
//	    	
//	    	for(int i=0; i<arr.length; i++) {
//	    		if(arr[i].equals(item)) {
//	    			cnt++;
//	    			if(firstIdx == -1) {
//	    				firstIdx = i;
//	    			}
//	    		}
//	    	}
//	    	
//	    	String[] tmp = new String[arr.length-cnt+1];
//	    	int idx=0;
//	    	for(int i=0; i<arr.length; i++) {
//	    		if(arr[i].equals(item) && i != firstIdx) {
//	    			continue;
//	    		} else {
//	    			tmp[idx++] = arr[i];
//	    		}
//	    	}
//	    	
//	    	return tmp;
	    	
	    }
	    
	    static void getList(List<String> list) {
			list.add("서울");	list.add("북경");
			list.add("상해");	list.add("서울");
			list.add("도쿄");	list.add("뉴욕");


			list.add("런던");	list.add("로마");
			list.add("방콕");	list.add("북경");
			list.add("도쿄");	list.add("서울");

			list.add(1, "LA");
	    }
	    
	    static void showList(String topic, List<String> list) {
	    	System.out.println(topic + " :: ");
	    	for(String st : list) {
	    		System.out.println(st);
	    	}
	    }
	    
	    static void sortList(List<String> list) {
	    	// 방법1: 
//	    	list.sort(String.CASE_INSENSITIVE_ORDER); // 대소문자 구분없이 오름차순 정렬
	    	
	    	// 방법2: 리스트를 스트링 배열로 변환(toArray) > 
	    	String cities[] = new String[0];
	    	cities = list.toArray(cities);
	    	
	    	for(int i=0; i<cities.length; i++) {
	    		for(int j=i+1; j<cities.length; j++) {
	    			if(cities[i].compareToIgnoreCase(cities[j]) > 0) {
	    				swap(cities, i, j);
	    			}
	    		}
	    	}
	    	
	    	// 정렬된 배열을 다시 리스트에 적용
	        list.clear();
	        list.addAll(Arrays.asList(cities));
	    }
	    
	    private static void swap(String[] cities, int i, int j) {
			String tmp = cities[i];
			cities[i] = cities[j];
			cities[j] = tmp;
		}

		static String[] removeDuplicateList(List<String> list) {
			/*
			 * 리스트를 배열로 변환 > 배열에서 중복을 찾는다
			 */
		    String cities[] = new String[0];
		    // list를 배열 cities[]로 변환
		    cities = list.toArray(cities);
		    
		    // for문으로 도시가 중복인 것을 체크 = compareTo를 사용
		    for(int i=0; i<cities.length; i++) {
		    	for(int j=i+1; j<cities.length; j++) {
		    		if(cities[i].compareTo(cities[j]) == 0) { // 중복이면
		    			cities = removeElement1(cities, cities[i]); // 중복 제거를 위해 배열 전달
		    		}
		    	}
		    	
		    	// while문으로 작성할 경우
//		    	int j = i+1;
//		    	while(j<cities.length) {
//		    		if(cities[i].compareTo(cities[j]) == 0) {
//		    			cities = removeElement1(cities, cities[i]);
//		    		}
//		    		j++;
//		    	}
		    }
		    
		    return cities;
	    }
	    
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>();
			getList(list);
			showList("입력후", list);
			//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩
			
//		    Collections.sort(list); Arrays.sort(); list.sort();

			//배열의 정렬
			sortList(list);
		    System.out.println();
		    showList("정렬후", list);
		    // 배열에서 중복제거
		    System.out.println();
		    System.out.println("중복제거::");
		  
		    String[] cities = removeDuplicateList(list);
	        ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		    showList("중복제거후", lst);
		}
	}

