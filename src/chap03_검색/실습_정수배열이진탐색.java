package chap03_검색;

//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
//comparator 구현 실습
/*
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class 실습_정수배열이진탐색 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);// 구현 반복 숙달 훈련

		showList("정렬 전 배열[]:: ", data);
		sortData(data);// 구현 반복 숙달 훈련
		//Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련

		int key = 13;
		int resultIndex = linearSearch(data, key);//교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		//Arrays 클래스에 linear search는 없다
		System.out.println("\nlinearSearch(13): result = " + resultIndex);

		key = 19;
		/*
		 * 교재 109~113
		 */
//		data[9] = key;
//		showList("Test: ", data);
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(19): result = " + resultIndex);
		
		key = 10;
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(10): result = " + resultIndex);

	}
	
	private static void showList(String string, int[] data) {
		System.out.println(string);
		
		for(int n:data) {
			System.out.println(n);
		}
	}

	private static void inputData(int[] data) {
		Random rnd = new Random();
		
		for(int i=0; i<data.length; i++) {
			data[i] = rnd.nextInt(25);
		}
		
	}

	static void sortData(int[]data) {
		for(int i=0; i<data.length; i++) {
			for(int j=i; j<data.length; j++) {
				if(data[i] > data[j])
					swap(data, i, j);
			}
		}
	}


	private static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	static int linearSearch(int[]item, int key) {
		int idx = -1;
		
		for(int i=0; i<item.length; i++) {
			if(item[i] == key) {
				idx = i;
				break;
			}
		}
		
		
		return idx;
	}

	static int binarySearch(int[]item, int key) {
		int pl = 0;
		int pr = item.length-1;
		int mid, idx = -1;
		
		while(pl != pr) {
			mid = (pl+pr)/2;
			if(item[mid] > key) {
				pr = mid-1;
			} else if(item[mid] < key) {
				pl = mid+1;
			} else {
				idx = mid;
				break;
			}
		}
		
		if(item[pl] == key)
			idx = pl;
		
		return idx;

	}
}
