package chap08_LinkedList;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
		System.out.println(first);
	}

	public boolean Delete(int element) // delete the element
	{
		Node1 q = null, p = first;
		
		while(p != null) {
			if(p.data < element) {
				q = p;
				p = p.link;
			} else {
				if(p.data == element) {
					if(q == null) // 처음값이 삭제될 때
						first = p.link;
					else {
						q.link = p.link;
					}
					return true;
				}
				break;
			}
		}
		return false;// 삭제할 대상이 없다.
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		
		System.out.println("***리스트 출력***");
		
		while(p != null) {
			System.out.print(p.data + " > ");
			p = p.link;
		}
		System.out.println();

	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		Node1 newNode = new Node1(element);
		Node1 p = first, q = null;
		
		if(first == null) {
			first = newNode;
			return;
		}
		
		while(p != null) {
			if(element > p.data) {
				q = p;
				p = p.link;
			} else {
				if(q == null) { // 맨 앞에 연결할때
					newNode.link = p;
					first = newNode;
//					newNode.link = first;
//					first = newNode;
					return;
				}else {
					q.link = newNode;
					newNode.link = p;
					return;
				}
			}
		}
		
		// 맨 마지막에 연결할 경우
		q.link = newNode;
	}

	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.
		Node1 ptr = first;
		
		while(ptr != null) {
			if(ptr.data == data)
				return true;
			ptr = ptr.link;
		}
		
		return false;
	}
}

public class 실습9_1정수연결리스트 {
	// enum: 숫자화 한다(enable numbering)
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}
		//"Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		//생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		//enum 상수가 언제 정의되는가를 찾아 보아야 한다 
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다 
			message = string;
			System.out.println("\nMenu 생성자 호출:: " + string);
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
			for (Menu m : Menu.values()) { // Menu 생성자 호출됨
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
		Menu menu; // 메뉴 참조 변수일 뿐이다 
		Random rand = new Random();
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int count = 10; //난수 생성 갯수
		int data = 0;
		do {
			switch (menu = SelectMenu()) {//Menu 생성자 호출 - menu 객체를 리턴한다 
			case Add: // 난수를 삽입하는데 올림차순으로 정렬되도록 구현
				for (int i =0; i < count; i++) {
					data = rand.nextInt(20);
					l.Add(data);
				}
				break;
			case Delete:
				System.out.println("삭제할 값을 입력: ");
				data = sc.nextInt();
				boolean tag = l.Delete(data);
				System.out.println("삭제 데이터 존재여부: " + tag);
				break;
			case Show: //리스트 전체를 출력
				l.Show();
				break;
			case Search: //입력 숫자 n을 검색한다.
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
//			case Merge://리스트 l과 l2를 합병하여 올림차순 정렬이 되게 구현한다 
//				LinkedList1 l2 = new LinkedList1();
//				for (int i =0; i < count; i++) {
//					data = rand.nextInt(20);
//					l2.Add(data);
//				}
//				l.Merge(l2);//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
//				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
