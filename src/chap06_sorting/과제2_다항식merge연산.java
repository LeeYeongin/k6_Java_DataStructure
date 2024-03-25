package chap06_sorting;

/*
 * f(x) = 5x^2 + 4x + 7
 * -> (5,2)(4,1)(7,0)
 * g(x) = 3x^3 + 6x^2
 * 
 * 곱셈:
 * (5x^2 + 4x + 7) * 3x^3
 * 			+				=> merge
 * (5x^2 + 4x + 7) * 6x^2
 */

class Polynomial implements Comparable<Polynomial>{
    double coef;           // 계수
    int    exp;            // 지수
    
	public Polynomial(double d, int i) {
		coef = d;
		exp = i;
	}
	
	@Override
	public int compareTo(Polynomial o) {
		if(exp > o.exp)
			return 1;
		else if(exp < o.exp)
			return -1;
		else if(coef > o.coef)
			return 1;
		else if(coef < o.coef)
			return -1;
		else
			return 0;
	}
}

public class 과제2_다항식merge연산 {

	static void merge(Polynomial[] a, int lefta, int righta, int leftb, int rightb ) {
		Polynomial tmp[] = new Polynomial[a.length]; 
		int idx = 0;
		int l = lefta, r = leftb;
		
		while(l<=righta && r<=rightb) {
			if(a[l].compareTo(a[r]) > 0)
				tmp[idx++] = a[l++];
			else if(a[l].compareTo(a[r]) < 0)
				tmp[idx++] = a[r++];
			else {
				tmp[idx++] = a[l++];
				tmp[idx++] = a[r++];
			}
		}
		
		while(l<=righta && r>rightb) {
			tmp[idx++] = a[l++];
		}
		
		while(r<=rightb && l>righta) {
			tmp[idx++] = a[r++];
		}
		
		for (int j = 0; j < idx; j++) { // temp를 a에 복사
			a[lefta+ j] = tmp[j];
		}
		
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		Polynomial[] x = {
		         new Polynomial(1.5, 3),
		         new Polynomial(2.5, 7),
		         new Polynomial(3.3, 2),
		         new Polynomial(4.0, 1),
		         new Polynomial(2.2, 0),
		         new Polynomial(3.1, 4),
		         new Polynomial(3.8, 5),
		     };
		
		Polynomial[] y = {
		         new Polynomial(1.5, 1),
		         new Polynomial(2.5, 2),
		         new Polynomial(3.3, 3),
		         new Polynomial(4.0, 0),
		         new Polynomial(2.2, 4),
		         new Polynomial(3.1, 5),
		         new Polynomial(3.8, 6),
		     };
		
		int nx = x.length;


		ShowPolynomial(x);
		ShowPolynomial(y);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬 1.지수순으로 2. 지수가 같으면 계수순으로
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		System.out.println("-------------------------After sort-------------------------");
		ShowPolynomial(x);
		ShowPolynomial(y);
		Polynomial[] z = new Polynomial[20];
		AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		ShowPolynomial(z);
//		ShowPolynomial(y);
//		MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
//		ShowPolynomial(z);
//		int result = EvaluatePolynomial(z, 10);//다항식 값 계산 함수 z(10) 값 계산한다 
//		System.out.println(" result = " + result );
	}

	private static void ShowPolynomial(Polynomial[] x) {
	
		for(int i=0; i<x.length; i++) {
			System.out.printf("%.1fx^%d", x[i].coef, x[i].exp);
			if(i+1 < x.length)
				if(x[i+1] == null)
					break;
			System.out.print(i < x.length-1 ? " + " : "");
		}
		System.out.println();
	}
	
	private static void AddPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int i=0, j=0, idx=0;
		
		while(i<x.length && j<y.length) {
			if(x[i].exp > y[j].exp) {
				z[idx] = new Polynomial(x[i].coef, x[i].exp);
				idx++; i++;
			} else if(x[i].exp < y[j].exp) {
				z[idx] = new Polynomial(y[j].coef, y[j].exp);
				idx++; j++;
			} else {
				z[idx] = new Polynomial(x[i].coef + y[j].coef, x[i].exp);
				idx++; i++; j++;
			}
		}
		
		while(i == x.length && j < y.length) {
			z[idx] = new Polynomial(y[j].coef, y[j].exp);
			idx++; j++;
		}
		
		while(j == y.length && i < x.length) {
			z[idx] = new Polynomial(x[i].coef, x[i].exp);
			idx++; i++;
		}		
	}
	
	private static int EvaluatePolynomial(Polynomial[] z, int i) {
		
		return 0;
	}

	private static void MultiplyPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		
		
	}


}
