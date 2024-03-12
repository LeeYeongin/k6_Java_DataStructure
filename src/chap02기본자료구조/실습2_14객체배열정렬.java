package chap02기본자료구조;
// 과제 2
import java.util.Arrays;

// 구글링:comparator, comparable 차이가 무엇인지 사용예 파악 (둘다 인터페이스)
// Comparable: compareTo(T o), Comparator: compare(T o1, T o2)
//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;

	public PhyscData(String string, int i, double d) {
		this.name = string;
		this.height = i;
		this.vision = d;
	}
	
	@Override
	public String toString() {
		return "이름: " + this.name + ", 키: " + this.height + ", 시력: " + this.vision;
	}
	
	@Override
	public int compareTo(PhyscData p) {
		// 비교하고 싶은 항목을 정해 원하는대로 구현
		if(p.height > this.height)
			return -1;
		else if(p.height == this.height)
			return 0;
		else
			return 1;
	}
	
	public int equals(PhyscData p) {
		// 같다고할 경우에 대한 조건 및 return값을 직접 구현
		// 이름, 키, 시력 모두 같을 경우 1을 return
		if(this.name.equals(p.name)	&& this.height == p.height)
			if(Double.compare(this.vision, p.vision) == 0)
				return 1;
		return 0; // 하나라도 같지 않음면 0을 return
	}
}

public class 실습2_14객체배열정렬 {
	static void swap(PhyscData[]arr, int ind1, int ind2) {
		PhyscData tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
	
	static void sortData(PhyscData []arr) {
		for(int i=0; i<arr.length; i++)
			for(int j=i+1; j<arr.length; j++)
				if(arr[i].compareTo(arr[j])>0)
					swap(arr, i, j);
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
		System.out.println("\n[Result]"); // 내가 추가한 코드
		showData(data);
		
		// 내가 추가한 코드
		System.out.println();
		System.out.println("data[1], data[2]가 일치합니까?");
		System.out.println(data[1].equals(data[2])==1 ? "일치합니다." : "일치하지 않습니다.");
	}
	
	static void showData(PhyscData[]arr) {
		for(PhyscData data:arr) {
			System.out.println(data);
		}
	}

}
