package chap06_sorting;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);

	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap

	public Heap(int sz) { // 생성자
		n = 0;
		MaxSize = sz;
		heap = new int[MaxSize + 1];
	}

	public void display() { // 출력
		for (int i = 1; i <= n; i++) {
			System.out.printf("[%d] : %d \n", i, heap[i]);
		}
	}

	@Override
	public void Insert(int x) { // 새로운 값 입력
		int i, j;
		if (n == MaxSize) { // heap이 꽉 찼을때
			HeapFull();
			return;
		}

		n++;
		for (i = n; i > 1; i = i / 2) { // root에서 정지
			if (x <= heap[i / 2])
				break;
			heap[i] = heap[i / 2];
		}

		heap[i] = x;

	}

	@Override
	public int DeleteMax() {
		if (n == 0) {
			HeapEmpty();
			return 0;
		}

		int i, j;
		int x = heap[1]; // root
		int last = heap[n]; // 마지막 리프 노트값
		n--;

		for (i = 1, j = 2; j <= n;) {
			if (j < n) // j==n이면 오른쪽 leaf가 없는 경우
				if (heap[j] < heap[j + 1]) // 오른쪽 leaf가 왼쪽 leaf보다 클 경우
					j++;
			if (last >= heap[j])
				break;

			heap[i] = heap[j];
			i = j;
			j = j * 2; // 다음 child로 이동
		}
		heap[i] = last;

		return x;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

public class 과제3_HeapSort {
	static void showData(int[] d) {
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
//		Heap heap = new Heap(20);
		Heap heap = new Heap(10);
		final int count = 10;
		int[] x = new int[count];
		int[] sorted = new int[count];

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1: // 입력
				for(int i=0; i<count; i++) {
					int value = rnd.nextInt(20);
					System.out.println("Input value: " + value);
					x[i] = value;
					heap.Insert(value);
				}
				showData(x);
				heap.display();
				break;
			case 2: // 출력
				heap.display();
				break;
			case 3: // 정렬 (for loop로 deletemax호출하여 정렬 -> 정렬된 결과 출력)
				for(int i=0; i<count; i++) {
					sorted[i] = heap.DeleteMax();
				}
				showData(sorted);
				break;
			case 4:
				return;
			}
		} while (select < 5);

		return;
	}
}
