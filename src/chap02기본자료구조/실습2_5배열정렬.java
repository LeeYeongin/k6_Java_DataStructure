package chap02기본자료구조;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class 실습2_5배열정렬 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);

		sortData(data);
		showData(data);

		reverse(data);//역순으로 재배치 - 정렬 아님 
		showData(data);
		
		reverseSort(data);//역순으로 재배치 - 정렬 아님 
		showData(data);
		
		Arrays.sort(data); // 자바 라이브러리를 사용
	}
	
	static void showData(int[]data) {
		for(int num: data){
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	static void inputData(int []data) { // 난수 생성 데이터 입력
		// data 배열은 참조변수이므로 return하지 않아도 변경된 값 저장
		Random rnd = new Random();
		for(int i=0; i<data.length; i++)
			data[i] = rnd.nextInt(100);
	}
	
	static void swap(int[]arr, int ind1, int ind2) {//교재 67페이지
		int t = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = t;
	}
	
	static void sortData(int []arr) { // 오름차순
		for(int i=0; i<arr.length; i++)
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i] > arr[j]) 
					swap(arr, i, j);
			}
	}
	
	static void reverse(int[] a) {//교재 67페이지
		for(int i = 0; i<a.length/2; i++) 
			swap(a, i, a.length-i-1);
	}
	
	static void reverseSort(int []arr) {
//		sort에서 조건문만 변경(큰수부터 작은수로: 내림차순)
		for(int i=0; i<arr.length; i++)
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i] < arr[j])
					swap(arr, i, j);
			
			}
	}
}
