package chap04_스택과큐;

//선형 큐 구현
import java.util.Scanner;

import chap04_스택과큐.Queue4.EmptyQueueException;

/*
 * Queue of ArrayList
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//int형 고정 길이 큐

class Queue4 {
	private List<Integer> que;// 원형큐로 구현하지 않는다
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
//	private int num; // 현재 데이터 개수

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
	
	//--- 생성자(constructor) ---//
	public Queue4(int maxlen) {
		que = new ArrayList<Integer>();
		capacity = maxlen;
		front = rear = 0;
	}

	//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowQueueException {
		if (isFull())
			throw new OverflowQueueException("enque: Queue is full");
		
		rear++;
		que.add(x);
		
		return x;
	}

	//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("enque: Queue is empty");
		
		rear--;
		
		return que.remove(front);
	}

	//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("peek: Queue is empty");
		
		return que.get(front);
	}

	//--- 큐를 비움 --- peek() 처럼 예외 발생 구현//
	public void clear() throws EmptyQueueException {
		/*
		 * queue을 empty로 만들어야 한다.
		 * queue이 empty일 때 clear()가 호출된 예외 발생해야 한다 
		 */
		if (isEmpty()) // queue이 빔
			throw new EmptyQueueException("clear: Queue is already emtpy");
		
		rear = 0;
		que.removeAll(que);
	}
	
	//--- 큐 안의 모든 요소 출력 --- //
	public void dump() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("dump: Nothing to print(Queue is empty)");
		
		for(int v : que)
			System.out.print(v + " ");
	}

	//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {
		for (int i = 0; i < size(); i++) {
			int idx = (i + front) % capacity;
			if (que.get(idx) == x) // 검색 성공
				return idx;
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
		return (rear-front) <= 0;
	}

	//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return (rear-front) >= capacity;
	}

}

public class 큐_정수선형리스트 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue4 oq = new Queue4(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ")");
				try {
					oq.enque(rndx);
				} catch (Queue4.OverflowQueueException e) {
					System.out.println("queue이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;
			case 4: // 덤프
				try {
					oq.dump();
				} catch (Queue4.EmptyQueueException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5: // clear
				try {
					oq.clear();
				} catch (Queue4.EmptyQueueException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
	}
}