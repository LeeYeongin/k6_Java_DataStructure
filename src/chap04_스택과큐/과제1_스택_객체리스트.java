
package chap04_스택과큐;
import java.util.ArrayList;
import java.util.List;
/*
* objectStack에 Point2 객체를 push, pop하는 코드를 구현 실습한다
*/
import java.util.Random;
import java.util.Scanner;

class Point2 {
	private int ix;
	private int iy;

	public Point2(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
	@Override
	public boolean equals(Object p) {
		if ((this.ix == ((Point2)p).ix) && (this.iy == ((Point2)p).iy))
			return true;
		else return false;
	}
}

class objectStack{
	//--- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;
		public EmptyGenericStackException(String message) {
			super(message);
		}
	}
/*
* public class Exception extends Throwable {
 private static final long serialVersionUID = -3387516993124229948L;
 private String detailMessage;

 public Exception(String message) {
     super(message); // 부모 클래스의 생성자 호출
     detailMessage = message; // detailMessage 필드에 메시지 저장
 }

 // 다른 필드, 메서드 등이 여기에 정의될 수 있음
}
*/
	//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Point2> data;           // 스택용 배열
	//private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	//--- 생성자(constructor) ---//
	public objectStack(int capacity) {
		data = new ArrayList<>();
		this.capacity = capacity;
		top = 0;
	}

	//--- 스택에 x를 푸시 ---//
	public boolean push(Point2 x) throws OverflowGenericStackException {
		if(isFull())
			throw new OverflowGenericStackException("push: stack overflow");
		top++;
		return data.add(x);
	}

	//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point2 pop() throws EmptyGenericStackException  {
		if (isEmpty())
			throw new EmptyGenericStackException("pop: stack empty");
		return data.remove(--top);
	}

	//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point2 peek() throws EmptyGenericStackException  {
		if (isEmpty())
			throw new EmptyGenericStackException("peek: stack empty");
		return data.get(top-1);
	}

	//--- 스택을 비움 ---//
	public void clear() throws EmptyGenericStackException {
		/*
		 * stack을 empty로 만들어야 한다.
		 * stack이 empty일 때 clear()가 호출된 예외 발생해야 한다 
		 * pop()으로 구현하지 않고 대신에 while 문으로 remove()를 반복 실행한다
		 */
		if (isEmpty()) // 스택이 빔
			throw new EmptyGenericStackException("clear: stack is already clear");
		
		// 모두 비우기 코드1
		data.removeAll(data);
		top = 0;
		
		// 코드2
		//while(top > 0) {
		//	data.remove(--top);
		//}
	}
	
	//--- 스택의 바닥부터 꼭대기까지 출력하는 함수 ---//
	public void dump() throws EmptyGenericStackException {
		if (isEmpty())
			throw new EmptyGenericStackException("dump: stack is empty");
		for(int i=0; i<top; i++) {
			System.out.println(data.get(i));
		}
	}
	
	//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point2 x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}


}
public class 과제1_스택_객체리스트 {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectStack s = new objectStack(8); // 최대 8 개를 push할 수 있는 stack
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point2 p = null;
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(5)clear  (0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			switch (menu) {
			case 1: // 푸시
				System.out.print("데이터: ");
				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				p = new Point2(rndx,rndy);
				try {
					s.push(p);
				} catch(objectStack.OverflowGenericStackException e) {
					System.out.println(e.getMessage()); // 생성자에서 입력했던 메시지 출력
					e.printStackTrace();
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 팝
				try {
					p = s.pop();
					System.out.println("pop한 데이터는 " + p + "입니다.");
				} catch(objectStack.EmptyGenericStackException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("stack이 비어있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = s.peek();
					System.out.println("peek한 데이터는 " + p + "입니다.");
				} catch (objectStack.EmptyGenericStackException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("stack이 비어있습니다.");
				}
				break;

			case 4: // 덤프도 피크처럼 구현
				try {
					s.dump();
				} catch (objectStack.EmptyGenericStackException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("stack이 비어있습니다.");
				}
				break;
			case 5: //clear도 피크처럼 구현 
				try {
					s.clear();
				} catch (objectStack.EmptyGenericStackException e) {
					System.out.println(e.getMessage());
					e.getStackTrace();
					System.out.println("stack이 비어있습니다.");
				}
				break;
			}
		}
	}
}