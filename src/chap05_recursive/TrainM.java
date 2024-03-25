//Test_MazingProblem_미로찾기
package chap05_recursive;

import java.util.ArrayList;
import java.util.List;

enum Direcrions21 {
	N, NE, E, SE, S, SW, W, NW
}

class Items31 {
	int x;
	int y;
	int dir;

	public Items31(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.dir = d;
	}

	@Override
	public String toString() {
		return "x = " + x + ", y = " + y + ", dir = " + dir;
	}
}

class Offsets31 {
	int a;
	int b;

	public Offsets31(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

class StackList1 {
	private List<Items31> data; // 스택용 배열
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 실행시 예외 : 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외 : 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException(String message) {
			super(message);
		}
	}

	// --- 생성자(constructor) ---//
	public StackList1(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
			data = new ArrayList<>(0); // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	// --- 스택에 x를 푸시 ---//
	public void push(Items31 p) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException("push: stack overflow");
		data.add(p);
		top++;
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Items31 pop() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException("pop: stack empty");
		return data.remove(--top);
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Items31 peek() throws EmptyIntStackException {
//		System.out.println("피크");
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException("peek: stack empty");
		return data.get(top - 1);
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(벌견하지 못하면 –1)를 반환 ---//
	public int indexOf(Items31 x) {
		for (int i = top - 1; i >= 0; i--) // 정상 쪽에서 선형검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("스택이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

public class TrainM {

	static Offsets31[] moves = new Offsets31[8];// static을 선언하는 이유를 알아야 한다

	public static void path(int[][] maze, int[][] mark, int ix, int iy) {

		mark[1][1] = 1;
		StackList1 st = new StackList1(50);
		Items31 temp = new Items31(0, 0, 0);// N :: 0
		temp.x = 1;
		temp.y = 1;
		temp.dir = 2;// E:: 2
		mark[temp.x][temp.y] = 2;// 미로 찾기 궤적은 2로 표시
		st.push(temp);
		boolean flag = false;

		while (!st.isEmpty()) // stack not empty
		{
			Items31 tmp = st.pop(); // unstack 팝을 한 경우 이전 값으로 돌아가는 기능을 수행
			int i = tmp.x;
			int j = tmp.y;
			int d = tmp.dir;
			System.out.printf("i: %d, j: %d, d: %d ", i, j, d);
			mark[i][j] = 1; // backtracking 궤적은 1로 표시
			
//			if(!st.isEmpty()) {				
//				temp = st.peek();
//				i = temp.x;
//				j = temp.y;
//			}

			while (d < 8 ) // moves forward
			{	//st.dump();
				int g = i + moves[d].a;
				int h = j + moves[d].b;
				if ((maze[g][h] == 0) && (mark[g][h] == 0)) { // new position
					mark[g][h] = 2;
					i = g;
					j = h;
					st.push(new Items31(i, j, d));
					d = 0;
					System.out.println();
					showMatrix(mark, 13, 16);
					System.out.println("--------------------");
				} else {
               	 d++;
				}
						
				if((i == ix) && (j == iy)) { // reached exit
					flag = true;
					break;
				}
			}
			
			if(flag)
				break;
			System.out.println("no path in maze");
		}

	}

	static void showMatrix(int[][] d, int row, int col) {
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println();
		}
	}

//----------------------------------------------------------------------------------------------------------main

	public static void main(String[] args) {
		int[][] maze = new int[14][17];
		int[][] mark = new int[14][17];

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 }, 
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 }, 
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 }, 
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 }, 
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 } };

		for (int ia = 0; ia < 8; ia++)
			moves[ia] = new Offsets31(0, 0);// 배열에 offsets 객체를 치환해야 한다.

		moves[0].a = -1; moves[0].b = 0; // N	
		moves[1].a = -1; moves[1].b = 1; // NE
		moves[2].a = 0;	 moves[2].b = 1; // E
		moves[3].a = 1;	 moves[3].b = 1; // SE
		moves[4].a = 1;	 moves[4].b = 0; // S
		moves[5].a = 1;	 moves[5].b = -1; // SW
		moves[6].a = 0;	 moves[6].b = -1; // W
		moves[7].a = -1; moves[7].b = -1; // NW
		
		//Direcrions21 d;
		//d = Direcrions21.N;
		// d = d + 1;//java는 지원안됨

		// input[][]을 maze[][]로 변환
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				maze[i][0] = 1;
				maze[i][16] = 1;
				maze[0][j] = 1;
				maze[13][j] = 1;
			}
		}
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 15; j++) {
				maze[i + 1][j + 1] = input[i][j];
			}
		}

		System.out.println("maze[13,16]::");
		showMatrix(maze, 13, 16);

//		System.out.println("mark::");
//		showMatrix(mark, 13, 16);

		path(maze, mark, 12, 15);
		System.out.println("mark[13, 16]::");
		showMatrix(mark, 13, 16);
	}
}
