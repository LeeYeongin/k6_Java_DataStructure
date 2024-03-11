package chap03_검색;

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;
import java.util.List;
public class 과제1_스트링배열합병 {
    static void showList(String topic, String [] list) {
    	System.out.print(topic+" ");
    	for(String s: list)
    		System.out.print(s + " ");
    	System.out.println();
    }
    static String[] mergeList(String[]s1, String[] s2) {
    	int i = 0, j = 0,k =0;
    	String[] s3 = new String[10];
    	
//    	while 문 사용해서 남은 요소가 있는지 확인..?
    	while(i<s1.length || j<s2.length) {
    		if(i == s1.length) { // i의 요소가 다 사용됐을떄
    			s3[i+j] = s2[j];
    			j++;
    		} else if(j == s2.length) { // j의 요소가 다 사용됐을떄
    			s3[i+j] = s1[i];
    			i++;
    		} else {
    			if(s1[i].compareTo(s2[j])<0) {
    				s3[i+j] = s1[i];
    				i++;
    			} else {
    				s3[i+j] = s2[j];
    				j++;
    			}
    		}
    	}
    	
    	return s3;
    }
    public static void main(String[] args) {
	String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신" };
	String[] s2 = {"독도", "울릉도", "한산도", "영도", "우도"};
	Arrays.sort(s1); // comparable, comparator도 없다 > comparable의 compareTo() 사용
	Arrays.sort(s2);
	
	// 후속 코딩은 객체들의 정렬: Students 클래스를 만들고 정렬
	showList("s1배열 = ", s1);
	showList("s2배열 = ", s2);
	String[] s3 = mergeList(s1,s2); // 순서대로 병합되야함 ex)강감찬 계백 김유신 독도 영도 우도 울릉도 을지문덕 한산도 홍길동
	showList("스트링 배열 s3 = s1 + s2:: ", s3);
}
}
