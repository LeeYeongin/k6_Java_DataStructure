package chap02기본자료구조;

// 과제 1
public class 실습2_14스트링배열정렬 {
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData(data);
		sortData(data);
		System.out.println("\n[result]");
		showData(data);
	}
	
	static void showData(String[]arr) {
		for(String name:arr)
			System.out.println(name);
	}

	static void swap(String[]arr, int ind1, int ind2) {
		String tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
	
	static void sortData(String []arr) {
		for(int i=0; i < arr.length; i++)
			for(int j=i+1; j < arr.length; j++) {
				// compareTo()사용
				/*
				 * compareTo():
				 * 	- 기준값이 비교값보다 클때 > 0 
				 * 	- 기준값이 비교값이랑 같을때 = 0 
				 * 	- 기준값이 비교값보다 작을때 < 0
				 */
				if(arr[i].compareTo(arr[j]) > 0) 
					swap(arr,i,j);
			}
	}

}
