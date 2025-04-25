package hw6_2;

import java.util.Scanner;

public class Main202214048 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hw6_2:김은총");

        int n = scanner.nextInt(); // 정수의 개수
        int [] array = new int[n]; // 정수 배열
        
        // 배열 원소 입력
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // 레드블랙트리(RBT)에 삽입
        RBT rbt = new RBT();
        for (int num : array) {
            rbt.insert(num);
        }

        System.out.print("inorder: ");
        rbt.inorder(); // inorder 순회

        System.out.print("preorder: ");
        rbt.preorder(); // preorder 순회

        scanner.close();
    }
}

class Node {
    int key; // 노드의 값(key)
    String color;
    Node left, right, parent; // 왼쪽, 오른쪽, 부모 노드

    // 생성자: key 값을 받아서 노드를 생성하고, 노드는 null로 초기화
    Node(int item, String color) {
        this.key = item;
        this.color = color; // "RED" or "BLACK"
        left = right = parent = null;
    }
}

// 레드 블랙 트리(Red Black Tree)
class RBT {
    Node root; // 루트 노드
    final Node NIL = new Node(-1, "BLACK"); // 모든 리프는 검정

    public RBT() {
        root = NIL; // 초기 루트는 리프노드 => NIL
    }

    // 삽입 호출
    void insert(int key) {
        Node newNode = new Node(key, "RED");
        newNode.left = newNode.right = newNode.parent = NIL;

        Node parent = NIL;
        Node current = root;

        // 이진트리(BST) 삽입
        while (current != NIL) {
            parent = current;
            if (key < current.key)
                current = current.left;
            else if (key > current.key)
                current = current.right;
            else
                return; // 중복된 키 무시
        }

        newNode.parent = parent;

        if (parent == NIL) {
            root = newNode; // 새 노드가 루트일 경우
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        insertFixup(newNode); // 삽입 후 균형 조정을 위해
    }

    // 삽입 후 레드블랙 규칙 유지를 위해(균형 조정)
    private void insertFixup(Node node) {
        while (node.parent.color.equals("RED")) {
            Node grandparent = node.parent.parent;

            // 부모가 왼쪽 자식인 경우
            if (node.parent == grandparent.left) {
                Node uncle = grandparent.right;

                // Case 1: uncle이 RED인 경우 => 색깔 바꾸기
                if (uncle.color.equals("RED")) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    grandparent.color = "RED";
                    node = grandparent;
                } else {
                    // Case 2: node가 오른쪽 자식인 경우 => 왼쪽 회전
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }

                    // Case 3: node가 왼쪽 자식인 경우 => 오른쪽 회전
                    node.parent.color = "BLACK";
                    grandparent.color = "RED";
                    rotateRight(grandparent);
                }
            } else { // 부모가 오른쪽 자식인 경우 (위에꺼랑 대칭)
                Node uncle = grandparent.left;

                if (uncle.color.equals("RED")) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    grandparent.color = "RED";
                    node = grandparent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }

                    node.parent.color = "BLACK";
                    grandparent.color = "RED";
                    rotateLeft(grandparent);
                }
            }
        }

        root.color = "BLACK"; // 루트는 항상 black
    }

    // 왼쪽 회전 구현 : 회전 대상 노드 x를 기준으로 오른쪽 자식(y)을 x의 자리에 올리고, x는 y의 왼쪽 자식으로 내려가는 구조
    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left; // y의 왼쪽 서브트리를 x의 오른쪽 자식으로 옮김

        // y의 왼쪽 자식이 NIL이 아니라면 => 부모를 x로 설정
        if (y.left != NIL)
            y.left.parent = x;

        y.parent = x.parent; // y가 x의 부모를 대신하도록 부모 설정

        // x가 루트 노드였다면 => y가 새로운 루트
        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y; // x가 부모의 왼쪽 자식이었다면, y를 부모의 왼쪽 자식으로
        } else {
            x.parent.right = y; // x가 부모의 오른쪽 자식이었다면, y를 부모의 오른쪽 자식으로
        }

        y.left = x; // x는 y의 왼쪽 자식
        x.parent = y; // x의 부모는 y
    }

    // 오른쪽 회전 구현 : : 회전 대상 노드 x를 기준으로 왼쪽 자식(y)을 x의 자리에 올리고, x는 y의 오른쪽 자식으로 내려가는 구조
    private void rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right; // y의 오른쪽 서브트리를 x의 왼쪽 자식으로 옮김

        // x의 오른쪽 자식이 NIL이 아니라면 => 부모를 y로 설정
        if (x.right != NIL)
            x.right.parent = y;

        x.parent = y.parent; // x가 y의 부모를 대신하도록 부모 설정

        // y가 루트 노드였다면 => x가 새로운 루트
        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x; // y가 부모의 오른쪽 자식이었다면, x를 부모의 오른쪽 자식으로
        } else {
            y.parent.left = x; // y가 부모의 왼쪽 자식이었다면, x를 부모의 왼쪽 자식으로
        }

        x.right = y; // x는 y의 오른쪽 자식
        y.parent = x; // x의 부모는 y
    }

    // inorder 순회: 왼쪽->루트->오른쪽 (오름차순 출력)
    void inorder() {
        inorderRec(root); // 루트부터 재귀 호출
        System.out.println(); // 줄바꿈
    }

    // inorder 재귀 메소드
    void inorderRec(Node node) {
        if (node != NIL) {
            inorderRec(node.left); // 왼쪽 노드 방문
            System.out.print(node.key + " "); // 현재 노드
            inorderRec(node.right); // 오른쪽 노드 방문
        }
    }

    // preorder 순회: 루트->왼쪽->오른쪽
    void preorder() {
        preorderRec(root); // 루트부터 재귀 호출
        System.out.println(); // 줄바꿈
    }

    // preorder 재귀 메소드
    void preorderRec(Node node) {
        if (node != NIL) {
            System.out.print(node.key + " "); // 현재 노드
            preorderRec(node.left); // 왼쪽 노드 방문
            preorderRec(node.right); // 오른쪽 노드 방문
        }
    }
}