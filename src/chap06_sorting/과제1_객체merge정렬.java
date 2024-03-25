package chap06_sorting;

/*
 * 6장 구현 실습과제1 
 */
class PhyscData implements Comparable<PhyscData> {

	String name; // 이름
	int height; // 키
	double vision; // 시력

	public PhyscData(String name, int h, double v) {
		this.name = name;
		this.height = h;
		this.vision = v;
	}
	
	@Override
	public int compareTo(PhyscData o) {

		if(height > o.height) // 키 비교
			return 1;
		else if(height < o.height)
			return -1;
		else if(name.compareTo(o.name) > 0) // 이름 비교
			return 1;
		else if(name.compareTo(o.name) < 0)
			return -1;
		else if(Float.compare(height, o.height) > 0) // 시력 비교
			return 1;
		else if(Float.compare(height, o.height) < 0)
			return -1;
		else
			return 0;
	}
}

public class 과제1_객체merge정렬 {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb) {
		PhyscData temp[] = new PhyscData[a.length];
		int idx=0;
		int l=lefta, r=leftb;
		
		while(l<=righta && r<=rightb) {
			if(a[l].compareTo(a[r]) > 0) 
				temp[idx++] = a[r++]; 
			else if(a[l].compareTo(a[r]) < 0) 
				temp[idx++] = a[l++];
			else {
				temp[idx++] = a[l++];
				temp[idx++] = a[r++];
			}
		}
		
		while(l<=righta && r>rightb) {
			temp[idx++] = a[l++];
		}
		
		while(r<=rightb && l>righta) {
			temp[idx++] = a[r++];
		}
		
		for (int j = 0; j < idx; j++) { // temp를 a에 복사
			a[lefta+ j] = temp[j];
		}
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);
		merge(a, left, mid, mid + 1, right);
		return;
	}

	public static void main(String[] args) {
		PhyscData[] x = { new PhyscData("강민하", 162, 0.3), new PhyscData("김찬우", 173, 0.7),
				new PhyscData("박준서", 171, 2.0), new PhyscData("유서범", 171, 1.5), new PhyscData("이수연", 168, 0.4),
				new PhyscData("장경오", 171, 1.2), new PhyscData("황지안", 169, 0.8), };
		int nx = x.length;

		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		System.out.println("오름차순으로 정렬했습니다."); // 키 > 이름 > 시력 순으로 오름차순
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (int i = 0; i < x.length; i++)
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
