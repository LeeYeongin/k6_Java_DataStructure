package chap02기본자료구조;

import java.util.HashSet;
//메소드에 배열 전달 실습부터
//실습 설명한다 
//매개변수로 배열 전달
import java.util.Random;
import java.util.Set;

public class 실습2_4배열전달함수 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);
	}
	
	static void showData(int[]data) {
		for (int num: data) { // 확장형 for문
			System.out.print(num+" ");
		}
	}
	
	public static void inputData(int []data) {//교재 63 - 난수의 생성
		Random rnd = new Random();
		
		// 중복 신경쓰지 않는 경우
//		for(int i=0; i<data.length; i++)
//			data[i] = rnd.nextInt(100);
		
		// 중복 숫자를 받지 않는 경우
		Set<Integer> d = new HashSet<Integer>();
		
		while(d.size() != data.length) {			
			d.add(rnd.nextInt(100)+1);
		}
		
		int i = 0;
	    for (Integer value : d) {
	        data[i++] = value;
	    }
	}
	
	static int findMax(int []items) {
		int max = -1;
		for(int num: items) {
			if(num > max)
				max = num;
		}
		return max;
	}
	
	static boolean findValue(int []items, int value) {
		//items[]에 value 값이 있는지를 찾는 알고리즘 구현
		for(int num: items) {
			if (num == value)
				return true;
		}
		return false;
	}

}
