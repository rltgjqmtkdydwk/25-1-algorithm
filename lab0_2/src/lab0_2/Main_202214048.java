package lab0_2;

import java.util.Scanner;

public class Main_202214048 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
        System.out.println("lab0_2:김은총");
        
        // 정렬된 배열 array를 초기화
		int array[] = {20, 40, 60, 80, 100, 110, 120, 130, 150, 190, 200, 300, 400};
		
		// 검색할 원소를 입력받음
		int item = scanner.nextInt();
		
		// 정렬된 배열 array에서 원소를 이진 검색하여 위치(인덱스)를 출력
		int index = binarySearch(array, 0, array.length-1, item);
		System.out.println(index);

		scanner.close();
	}

	// 정렬된 배열 array[from...to] 에서 item을 이진검색하여 위치(인덱스)를 리턴하는 재귀 메소드
	private static int binarySearch(int[] array, int from, int to, int item) {
	    if (from > to) {
	    	return -1;   // 검색 실패
	    }
	    
	    int p = (from + to) / 2;  // 중간 인덱스
	        
	    if (item > array[p]) {
	    	return binarySearch(array, p+1, to, item); // 중간값보다 크면 from+1
	    } else if (item < array[p]) {
	    	return binarySearch(array, from, p-1, item); // 중간값보다 작으면 to-1
	    } else {
	        return p;  // from = to 경우 인덱스 반환
	    }
	}
        
}
