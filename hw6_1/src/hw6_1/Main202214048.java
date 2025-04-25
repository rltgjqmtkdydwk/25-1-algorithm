package hw6_1;

import java.util.Scanner;

public class Main202214048 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hw6_1:김은총");

        int n = scanner.nextInt(); // 정수의 개수
        int [] array = new int[n]; // 정수 배열
        
        // 배열 원소 입력
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // BST에 삽입
        BST bst = new BST();
        for (int num : array) {
            bst.insert(num);
        }

        System.out.print("inorder: ");
        bst.inorder(); // inorder 순회

        System.out.print("preorder: ");
        bst.preorder(); // preorder 순회
        
        scanner.close();
    }
}

// 이진 탐색 트리의 노드
class Node {
    int key; // 노드의 값(key)
    Node left; // 왼쪽 자식 노드
    Node right; // 오른쪽 자식 노드

    // 생성자: key 값을 받아서 노드를 생성하고, 자식 노드는 null로 초기화
    Node(int item) {
        key = item;
        left = right = null;
    }
}

// 이진 탐색 트리(Binary Search Tree)
class BST {
    Node root; // 루트 노드

    // 새로운 key를 트리에 삽입하는 메소드 (외부에서 호출)
    void insert(int key) {
        root = insertRec(root, key); // 재귀 호출
    }

    // 재귀 메소드
    Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key); // 현재 위치가 null이면 새 노드를 만들어 삽입
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key); // 현재 노드보다 작으면 왼쪽 서브트리에 삽입
        } else if (key > root.key) {
            root.right = insertRec(root.right, key); // 현재 노드보다 크면 오른쪽 서브트리에 삽입
        } else {
            // key == root.key인 경우 (중복된 값), 아무 작업도 하지 않고 그대로 반환
            // 오류 메시지 없음
        }

        return root;
    }

    // inorder 순회: 왼쪽->루트->오른쪽 (오름차순 출력)
    void inorder() {
        inorderRec(root); // 루트부터 재귀 호출
        System.out.println(); // 줄바꿈
    }

    // inorder 재귀 메소드
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); // 왼쪽 서브트리 방문
            System.out.print(root.key + " "); // 현재 노드
            inorderRec(root.right); // 오른쪽 서브트리 방문
        }
    }

    // preorder 순회: 루트->왼쪽->오른쪽
    void preorder() {
        preorderRec(root); // 루트부터 재귀 호출
        System.out.println(); // 줄바꿈
    }

    // preorder 재귀 메소드
    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " "); // 현재 노드
            preorderRec(root.left); // 왼쪽 서브트리 방문
            preorderRec(root.right); // 오른쪽 서브트리 방문
        }
    }
}


