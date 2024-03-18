package chap05_recursive;

import java.util.Scanner;

public class Recur {
	static void recur(int n) {
		//while(n>0) { // if (n>0)
//		while(true) {
//			if (n>0) { //recur(n-1);
//				s.push(n);
//				n = n-1;
//				continue;
//			}
//			if (s.isEmtpy() != true) {
//				n = s.pop();
//				System.out.println(n);
//				n = n-2; //recur(n-2);
//				continue;
//			}
//			break;
//		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요.: ");
		int x = stdIn.nextInt();
		
		recur(x);
	}
}
