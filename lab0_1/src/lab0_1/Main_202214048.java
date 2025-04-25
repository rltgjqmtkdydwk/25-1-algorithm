package lab0_1;

import java.util.Scanner;

public class Main_202214048 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("lab0_1:김은총");
        
        // n, lb, ub 입력
        String[] firstLine = scanner.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int lb = Integer.parseInt(firstLine[1]);
        int ub = Integer.parseInt(firstLine[2]);
        
        // n개의 크기의 입력한 정수 리스트
        String[] secondLine = scanner.nextLine().split(" ");
        int[] N = new int[n];
        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(secondLine[i]);
        }
        
        // lb-ub 범위에 존재하는 정수 개수를 저장하는 리스트
        int[] NinB = new int[ub - lb + 1];
        
        // 각 숫자의 등장 횟수 계산
        for (int i = 0; i < N.length; i++) {
            if (lb<=N[i] && N[i]<=ub) {
                NinB[N[i] - lb]++;
            }
        }
        
        // 결과 출력
        for (int i = 0; i < NinB.length; i++) {
            System.out.print(NinB[i]);
            if (i < NinB.length - 1) {
                System.out.print(", ");
            }
        }
        
        scanner.close();
    }
}