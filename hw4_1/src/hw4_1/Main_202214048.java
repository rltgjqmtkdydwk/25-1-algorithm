package hw4_1;

import java.util.Random;
import java.util.Scanner;

public class Main_202214048 {

	public static void main(String[] args) {
		System.out.println("hw4_1:김은총");
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		// 원소 수(n)를 입력받아 크기가 n인 정수 배열 생성
		int n = scanner.nextInt();
		int[] array = new int[n];
		
		// 원소의 하한(lb), 상한(ub)을 입력받음
		int lb = scanner.nextInt();
		int ub = scanner.nextInt();
		
		
		// 배열을 lb~ub 범위의 랜덤 넘버로 채움
		for (int i=0; i<n; i++) {
			array[i] = random.nextInt(ub - lb + 1)+lb;
		}

		// 정렬 전 배열 원소 출력 - 한 줄에 100개씩
		printArray(array);

		// 배열을 퀵정렬
		quickSort(array, 0, array.length - 1);

		// 배열 원소 출력 - 한 줄에 100개씩
		printArray(array);
		
		// 배열을 다시 한 번 퀵정렬
		quickSort(array, 0, array.length - 1);
		
		// 배열 원소 출력 - 한 줄에 100개씩
		printArray(array);
	}
	
	// 퀵 정렬 알고리즘 : 배열 array[p...r]을 퀵 정렬
	public static void quickSort(int[] array, int p, int r) {
		if (p<r) {
			int q = partition(array, p, r); //q=피벗
			quickSort(array, p, q-1);       //q 제외 left 정렬
			quickSort(array, q+1, r);       //q 제외 right 정렬 
		}
	}

	// 분할 알고리즘 : array[p...r]을 분할하여 기준원소 인덱스를 리턴
	public static int partition(int[] array, int p, int r) {
		int x = array[r]; //피벗 : 배열의 맨끝원소
		int i = p-1;      //시작 위치
		for (int j = p; j<r; j++) { // 배열 정렬 시작
			if (array[j] < x) {     // j가 피벗보다 작으면
				i++;  				// 왼쪽 배열 포인터 이동
				int tmp = array[i]; // 왼쪽 배열의 끝 다음 원소로 넣기
				array[i] = array[j];
				array[j] = tmp;
			}
		}
		int tmp = array[i+1];  // 피벗 가운데로 정렬(가운데 원소와 위치 바꿈)
		array[i+1] = array[r];
		array[r] = tmp;
		
		return i+1; //피벗 인덱스 리턴
	}
	
	// printArray 구현 : 배열 원소 출력 한 줄에 100개씩
	public static void printArray(int[] array) {
	    for (int i = 0; i < array.length; i++) {
	        System.out.print(array[i] + " ");
	        if ((i + 1) % 100 == 0) { // 100개 이상이면 줄바꿈
	            System.out.println();
	        }
	    } System.out.println();
	}

}
