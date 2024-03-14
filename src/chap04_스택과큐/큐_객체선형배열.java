package chap04_스택과큐;

//List를 사용한 선형 큐 구현  - 큐는 배열 사용한다 
import java.util.Random;
import java.util.Scanner;

import chap04_스택과큐.objectQueue2.EmptyQueueException;
import chap04_스택과큐.objectQueue2.NoSpaceQueueException;

/*
* Queue of ArrayList of Point
*/

class Point3 {
	private int ix;
	private int iy;

	public Point3(int x, int y) {
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
		if ((this.ix == ((Point3) p).ix) && (this.iy == ((Point3) p).iy))
			return true;
		else
			return false;
	}
}

//int형 고정 길이 큐
class objectQueue2 {
	private Point3[] que; // que는 참조변수
	private int capacity; // 큐의 크기 - 배열의 length
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	// private int num; // 현재 데이터 개수

	//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

	//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}
	
	//--- 실행시 예외: 가득 차지는 않았으나 더 넣을 수 없음 ---//
	public class NoSpaceQueueException extends Exception {
		private static final long serialVersionUID = 1L;

		public NoSpaceQueueException(String msg) {
			super(msg);
		}
	}
	
	//--- 생성자(constructor) ---//
	public objectQueue2(int maxlen) { // 배열의 크기만 매개변수로 받음
		front = rear = 0;
		capacity = maxlen;
		try {
			que = new Point3[maxlen];
		}catch(OutOfMemoryError e) {
			capacity = 0;
		}
	}

	//--- 큐에 데이터를 인큐 ---//
	public int enque(Point3 x) throws OverflowQueueException, NoSpaceQueueException {
		if(isFull())
			throw new OverflowQueueException("enque: Queue is full");
		
		if(rear == capacity)
			throw new NoSpaceQueueException("enque: No space to input new data");
		
		que[rear++] = x;
		
		return 0;
	}

	//--- 큐에서 데이터를 디큐 ---//
	public Point3 deque() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("deque: Queue is empty");
		
		return que[front++];
	}

	//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public Point3 peek() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("peek: Queue is empty");
		
		return que[front];
	}

	//--- 큐를 비움 ---peek처럼 구현//
	public void clear() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("clear: Queue is already empty");
		
		front = rear = 0;
	}
	
	//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(Point3 x) {
		for (int i = 0; i < size(); i++) {
			if (que[i].equals(x)) // 검색 성공
				return i;
		}
		return -1; // 검색 실패
	}

	//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return rear-front;
	}

	//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return size() <= 0;
	}

	//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return size() >= capacity;
	}

	//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if(isEmpty())
			throw new EmptyQueueException("dump: Queue is empty");
		
		for(int i = front; i<rear; i++) {
			System.out.print(que[i] + " ");
		}
	}
}

public class 큐_객체선형배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue2 oq = new objectQueue2(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point3 p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0) {
				System.out.println("프로그램이 종료되었습니다.");
				break;
			}
			switch (menu) {
			case 1: // 인큐

				rndx = random.nextInt(20);

				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point3(rndx, rndy);
				try {
					oq.enque(p);
				} catch (objectQueue2.OverflowQueueException | NoSpaceQueueException e) {
					System.out.println("\n" + e.getMessage());
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				try {
					oq.dump();
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5: // clear
				try {
					oq.clear();
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
	}
}