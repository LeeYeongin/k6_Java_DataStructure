package chap10_hash;


import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;
	int no; // 회원번호
	String name; // 이름
	
	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}
	
	// 생성자
	public SimpleObject5() {
		no = -1; name = null;
	}

	public SimpleObject5(int no, String name) {
		this.no = no; 
		this.name = name;
	}
	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {//sw가 3이면 11 비트 연산 >  NO, NAME을 모두 입력받는다 
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요."+ sw);

		if ((sw & NO) == NO) { //& 는 bit 연산자임 sw가 3(011)이면 &는 비트 연산이므로 결과는 3(011) & 1(001) = 1(001)
			System.out.print("번호: ");
			no = sc.nextInt();
		}
		if ((sw & NAME) == NAME) {//sw가 3이고 NAME과 비트 & 연산하면 결과는 2 3(011) & 2(010) = 2(010)
			System.out.print("이름: ");
			name = sc.next();
		}
	}
	
	// --- 회원번호로 순서를 매기는 comparator ---//
		public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

		private static class NoOrderComparator implements Comparator<SimpleObject5> {
			public int compare(SimpleObject5 d1, SimpleObject5 d2) {
				return d1.no - d2.no;
			}
		}

		// --- 이름으로 순서를 매기는 comparator ---//
		public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

		private static class NameOrderComparator implements Comparator<SimpleObject5> {
			public int compare(SimpleObject5 d1, SimpleObject5 d2) {
				return d1.name.compareTo(d2.name);
			}
		}
	
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		
		// --- 생성자(constructor) ---//
		public Node2(SimpleObject5 data) {
			this.data = data;
			this.next = null;
		}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

	//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		this.size = capacity;
		table = new Node2[capacity];
	}

	//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int idx = st.no % size;
		Node2 p = table[idx];

		while (p != null) {
			if (c.compare(p.data, st)<0)
				p = p.next;
			else {
				if (c.compare(p.data, st)==0)
					return 1;
				else
					return -1;
			}
		}

		return -1;
	}

	//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int idx = st.no % size;
		Node2 p = table[idx], q = null;
		Node2 newNode = new Node2(st);

		if (table[idx] == null) {
			table[idx] = newNode;
			return 0;
		} else {
			while (p != null) {
				if (c.compare(p.data, newNode.data)<0) {
					q = p;
					p = p.next;
				} else {
					// 중복되는 값이 들어갈때
					if (c.compare(p.data, newNode.data)==0)
						return 1;
					else { // 중복되지 않는 값인 경우
						if (q == null) { // 맨 처음에 넣는 경우
							newNode.next = p;
							table[idx] = newNode;
							return 0;
						} else { // 중간에 넣는 경우
							q.next = newNode;
							newNode.next = p;
							return 0;
						}
					}
				}

			}

			// 맨 뒤에 넣는 경우
			q.next = newNode;
			return 1;
		}
	}

	//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int idx = st.no % size;
		Node2 p = table[idx], q = null;

		while (p != null) {
			if (c.compare(p.data, st) < 0) {
				q = p;
				p = p.next;
			} else {
				if (c.compare(p.data, st) == 0) {
					if (q == null) // 맨 앞에 삭제
						table[idx] = p.next;
					else // 중간 삭제
						q.next = p.next;
					return 1;
				}
				return 0;
			}
		}
		
		return 0;
	}

	//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < table.length; i++) {
			System.out.printf("[%d] :", i);
			if (table[i] != null) {
				Node2 p = table[i];
				while (p != null) {
					System.out.print(p.next != null ? p.data + " > " : p.data);
					p = p.next;
				}
			}
			System.out.println();
		}
	}
}

public class 과제1_객체체인해시 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}
		// --- 메뉴 선택 ---//
		static Menu SelectMenu() {
			Scanner sc = new Scanner(System.in);
			int key;
			do {
				for (Menu m : Menu.values()) {
					System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
					if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
						System.out.println();
				}
				System.out.print(" : ");
				key = sc.nextInt();
			} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
			return Menu.MenuAt(key);
		}


	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
