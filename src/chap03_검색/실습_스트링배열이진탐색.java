package chap03_검색;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class 실습_스트링배열이진탐색 { // p.121

	static void reverse(String[] a) {//교재 67페이지
		for(int i=0; i<a.length/2; i++) {
			swap(a, i, a.length-i-1);
		}
	}
	
	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};//홍봉희 재배 과수

		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);//역순으로 재배치
		showData("역순 재배치후", data);
		Arrays.sort(data);//Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후",data);
      
		String key = "포도";
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(포도): result = " + resultIndex);

		key = "배";
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(배): result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): result = " + resultIndex);

	}
	
	private static void sortData(String[] data) {
		for(int i=0; i<data.length; i++)
			for(int j=i+1; j<data.length; j++) {
				if(data[i].compareTo(data[j])>0)
					swap(data,i,j);
			}
		
	}
	
	private static void swap(String[] data, int i, int j) {
		String tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
		
	}
	
	private static void showData(String string, String[] data) {
		System.out.println(string);
		for(String d : data)
			System.out.print(d + " ");
		System.out.println();
	}
	
	private static int linearSearch(String[] data, String key) {
		for(int i=0; i<data.length; i++) {
			if(data[i].compareTo(key)==0) {
				return i;
			}
		}
		return -1;
	}
	
	private static int binarySearch(String[] data, String key) {
		int pl = 0, pr = data.length-1;
		int mid, idx = -1;
		
		do {
			mid = (pl + pr)/2;
			if(data[mid].compareTo(key)>0)
				pr = mid -1;
			else if(data[mid].compareTo(key)<0)
				pl = mid + 1;
			else {
				idx = mid;
				break;
			}
		} while(pl<=pr);
		return idx;
	}


}
