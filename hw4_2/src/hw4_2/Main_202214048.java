package hw4_2;

import java.util.Scanner;

public class Main_202214048 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hw4_2:김은총");
        System.out.println("삽입정렬");
        
        int n = scanner.nextInt(); // 실수의 개수
        double[] array = new double[n]; // 실수 배열
        
        // 배열 원소 입력
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextDouble();
        }
        
        insertionSort(array, n); // 삽입정렬 호출
        
        // 결과 출력
        for (double item : array) {
            System.out.print(item + " ");
        }
        scanner.close();
    }
    
    // 삽입 정렬 알고리즘
    public static void insertionSort(double[] array, int n) {
        for (int i = 1; i < n; i++) { // i는 두번째 원소부터 반복 
            double item = array[i]; // 원래 값 저장
            double item_decimal = item - Math.floor(item); // 소수부만
            int j = i - 1; // i와 비교할 인덱스 반복문 밖에 선언
            // i 앞부분 돌면서 비교 : array[i]<array[j]라면 계속 반복
            while (j >= 0 && (array[j] - Math.floor(array[j])) > item_decimal) {
            	array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = item; // 반복 나가면 array[i]>array[j] 이므로 j+1에 저장
        }
    }
}
