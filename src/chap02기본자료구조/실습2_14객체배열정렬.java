package chap02기본자료구조;
// 과제 2
import java.util.Arrays;

// 구글링:comparator, comparable 차이가 무엇인지 사용예 파악
//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;

	public PhyscData(String string, int i, double d) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "";
	}
	@Override
	public int compareTo(PhyscData p) {
		return 1;
	}
	public int equals(PhyscData p) {
		return 1;
	}
}
public class 실습2_14객체배열정렬 {
	static void swap(PhyscData[]arr, int ind1, int ind2) {
		
	}
	static void sortData(PhyscData []arr) {
//		for(int i=0;)
//			for(int j=i+1;)
//				if(arr[i]> arr[j])
//					swap(arr, i, j);
	}
	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
		};
		showData(data);
		sortData(data);
		//Arrays.sort(null, null);//comparator가 필요하다 
		showData(data);
	}
	static void showData(PhyscData[]arr) {

	}

}
