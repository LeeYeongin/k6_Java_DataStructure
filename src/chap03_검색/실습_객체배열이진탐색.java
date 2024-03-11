package chap03_검색;

/*
 * Comparable 구현
 */
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현(p.123)
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;
	
	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "이름: " + name + ", 키: " + height + ", 시력: " + vision;
	}
	
	@Override
	public int compareTo(PhyscData2 p) {
		if(vision > p.vision)
			return 1;
		else if(vision < p.vision)
			return -1;
		else if(height > p.height)
			return 1;
		else if(height < p.height)
			return -1;
		else
			return 0;
			
	}
	public int equals(PhyscData2 p) {
		if(this.name.equals(p.name)	&& this.height == p.height)
			if(Double.compare(this.vision, p.vision) == 0)
				return 1;
		return 0; // 하나라도 같지 않음면 0을 return
	}
}
public class 실습_객체배열이진탐색 {


	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("이동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data); // override된 compareTo함수가 자동으로 호출되어 비교 후 정렬됨
		showData("Arrays.sort() 정렬후", data);
		
		PhyscData2 key = new PhyscData2("길동", 167, 0.2);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,0.2>): result = " + resultIndex);
		
		key = new PhyscData2("박동", 182, 0.6);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,182,0.6>): result = " + resultIndex);
		key = new PhyscData2("이동", 167, 0.6);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);//comparable를 사용
		System.out.println("\nArrays.binarySearch(<이동,167,0.6>): result = " + resultIndex);
	}

	private static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		int pl = 0;
		int pr = data.length-1;
		int mid, comp, idx = -1;
		
		do {
			mid = (pl+pr)/2;
			comp = data[mid].compareTo(key);
			if(comp == 0) {
				idx = mid;
				break;
			}
			else if(comp > 0)
				pr = mid - 1;
			else
				pl = mid + 1;
		} while(pl <= pr);
		
		return idx;
	}

	private static void reverse(PhyscData2[] data) {
		for(int i=0; i<data.length/2; i++) {
			swap(data, i, data.length-i-1);
		}
	}

	private static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		int idx = -1;
		for(int i=0; i<data.length; i++){
			if(data[i].equals(key) == 1) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	private static void showData(String string, PhyscData2[] data) {
		System.out.println(string);
		for(PhyscData2 d:data )
			System.out.println(d.toString());
		
	}

	private static void sortData(PhyscData2[] data) {
		// 확장형 for문으로 인덱스가 없어서 swap() 처리 안됨
		for(int i=0; i<data.length; i++) {
			for(int j=i+1; j<data.length; j++) {
				if(data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}	
		}
	}

	private static void swap(PhyscData2[] data, int i, int j) {
		PhyscData2 tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	
	
	
	

}
