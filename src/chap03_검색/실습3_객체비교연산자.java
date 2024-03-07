package chap03_검색;

import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData3 {
	String name;
	int height;
	double vision;

}
class NameOrderComparator2 implements Comparator<PhyscData3>{

	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
		if (p1.name.compareTo(p2.name)>0)
			return 1;
		else if(p1.name.compareTo(p2.name)<0)
			return -1;
		else
			return 0;
	}

}

class HeightOrderComparator2 implements Comparator<PhyscData3>{

	@Override
	public int compare(PhyscData3 p1, PhyscData3 p2) {
		if (p1.height > p2.height)
			return 1;
		else if(p1.height < p2.height)
			return -1;
		else
			return 0;
	}

}

public class 실습3_객체비교연산자 {	
	static final Comparator<PhyscData3> HEIGHT_ORDER = new HeightOrderComparator2();

	public static void main(String[] args) {
		PhyscData3[] data = {
				new PhyscData3("홍길동", 162, 0.3),
				new PhyscData3("홍동", 164, 1.3),
				new PhyscData3("홍길", 152, 0.7),
				new PhyscData3("김홍길동", 172, 0.3),
				new PhyscData3("길동", 182, 0.6),
				new PhyscData3("길동", 167, 0.2),
				new PhyscData3("길동", 167, 0.5),
		}; // data는 참조변수(객체 X) -> data.해서 메소드 사용 불가
		
		showData("정렬전 객체 배열", data);
		Arrays.sort(data, HEIGHT_ORDER);
		
		showData("정렬후 객체 배열", data);
		PhyscData3 key = new PhyscData3("길동", 167, 0.2);
		
		Arrays.binarySearch(data, key, new NameOrderComparator2());
		int idx = Arrays.binarySearch(data, key, HEIGHT_ORDER); // binarySearch는 index가 있는 배열만 가능
		System.out.println("\nArrays.binarySearch(): result = " + idx);
	}

}
