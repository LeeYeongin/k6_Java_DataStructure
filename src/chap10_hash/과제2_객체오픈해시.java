package chap10_hash;

import java.util.Comparator;
import java.util.Scanner;

import chap10_hash.OpenHash2.Bucket;
import chap10_hash.OpenHash2.Status;

//오픈 주소법에 의한 해시

class SimpleObject2 {
	static final int NO = 1;
	static final int NAME = 2;
	int sno; // 회원번호
	String sname; // 이름
	
	// --- 문자열 표현을 반환 ---//
		public String toString() {
			return "(" + sno + ") " + sname;
		}
	
	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {//sw가 3이면 11 비트 연산 >  NO, NAME을 모두 입력받는다 
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요."+ sw);

		if ((sw & NO) == NO) { //& 는 bit 연산자임 sw가 3(011)이면 &는 비트 연산이므로 결과는 3(011) & 1(001) = 1(001)
			System.out.print("번호: ");
			sno = sc.nextInt();
		}
		if ((sw & NAME) == NAME) {//sw가 3이고 NAME과 비트 & 연산하면 결과는 2 3(011) & 2(010) = 2(010)
			System.out.print("이름: ");
			sname = sc.next();
		}
	}
	
	// --- 회원번호로 순서를 매기는 comparator ---//
		public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

		private static class NoOrderComparator implements Comparator<SimpleObject2> {
			public int compare(SimpleObject2 d1, SimpleObject2 d2) {
				return d1.sno - d2.sno;
			}
		}

		// --- 이름으로 순서를 매기는 comparator ---//
		public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

		private static class NameOrderComparator implements Comparator<SimpleObject2> {
			public int compare(SimpleObject2 d1, SimpleObject2 d2) {
				return d1.sname.compareTo(d2.sname);
			}
		}

	
}
//*
class OpenHash {

	// --- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	}; // {데이터 저장, 비어있음, 삭제 완료}

	// --- 버킷 ---//
	static class Bucket { 
		private SimpleObject2 data; // 데이터
		private Status stat; // 상태
		
		Bucket(){
			data = null;
			stat = Status.EMPTY;
		}
		
		public void set(SimpleObject2 data, Status s) {
			this.data = data;
			this.stat = s;
		}

	}

	private int size; // 해시 테이블의 크기
	private Bucket[] table; // 해시 테이블

	// --- 생성자(constructor) ---//
	public OpenHash(int size) {
		this.size = size;
		table = new Bucket[size];
		
		for(int i=0; i<size; i++) {
			table[i] = new Bucket();
		}
		
	}

	// --- 해시값을 구함 ---//
	public int hashValue(SimpleObject2 key) {
		return Integer.hashCode(key.sno) % size;
	}

	// --- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	// --- 키값 key를 갖는 버킷 검색 ---//
	private Bucket searchNode(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		int hash = hashValue(key);
		Bucket p = table[hash];
		
		for(int i=0; p.stat != Status.EMPTY && i < size; i++) {
			if(p.stat == Status.OCCUPIED && c.compare(p.data, key) == 0)
				return p;
			hash = rehashValue(hash);
			p = table[hash];
		}
		return null;
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환)---//
	public SimpleObject2 search(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		Bucket p = searchNode(key, c);
		if(p!=null)
			return p.data;
		else
			return null;
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		int hash = hashValue(key);
		Bucket p = table[hash];
		
		if(searchNode(key, c) != null)
			return 1;
		
		for(int i=0; i<size; i++) {
			if(p.stat == Status.EMPTY || p.stat == Status.DELETED) {
				p.set(key, Status.OCCUPIED);
				return 0;
			}
			hash = rehashValue(hash);
			p = table[hash];
		}
		
		return 2;
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int remove(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		Bucket p = searchNode(key, c);
		if (p == null)
			return 1;
		
		p.stat = Status.DELETED;
		return 0;
	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for(int i=0; i<size; i++) {
			System.out.print("[" + i + "]: " );
			if(table[i].stat == Status.OCCUPIED && table[i].data != null)
				System.out.print(table[i].data);
			System.out.println();
		}
	}
}
//*/
public class 과제2_객체오픈해시 {

	static Scanner stdIn = new Scanner(System.in);

//--- 메뉴 열거형 ---//
	enum Menu {
		ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

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

//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
	
		SimpleObject2 temp; // 읽어 들일 데이터
		int result;
		OpenHash hash = new OpenHash(13);
		do {
			switch (menu = SelectMenu()) {
			case ADD: // 추가
				temp = new SimpleObject2();
				temp.scanData("추가", SimpleObject2.NO | SimpleObject2.NAME);
				int k = hash.add(temp, SimpleObject2.NO_ORDER);
				switch (k) {
				case 1:
					System.out.println("그 키값은 이미 등록되어 있습니다.");
					break;
				case 2:
					System.out.println("해시 테이블이 가득 찼습니다.");
					break;
				case 0:
				}
				break;

			case REMOVE: // 삭제
				temp = new SimpleObject2();
				temp.scanData("삭제", SimpleObject2.NO);
				result = hash.remove(temp, SimpleObject2.NO_ORDER);
				if (result == 0)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;

			case SEARCH: // 검색
				temp = new SimpleObject2();
				temp.scanData("검색", SimpleObject2.NO);
				SimpleObject2 t = hash.search(temp, SimpleObject2.NO_ORDER);
				if (t != null)
					System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
