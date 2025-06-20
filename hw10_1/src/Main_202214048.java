import java.util.List;
import java.util.Scanner;

public class Main_202214048 {
    public static void main(String[] args) {
        System.out.println("hw10_1:김은총");

        Scanner scanner = new Scanner(System.in);

        // (1) 가중치가 있는 방향그래프의 정점수 n을 입력받음
        int n = scanner.nextInt();
        scanner.nextLine();

        // (2) 인접행렬 내용(간선이 없으면 0, 있으면 가중치)을 입력받아 그래프 간선 목록을 생성
        int[][] adj = new int[n][n];    // 인접행렬 배열
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");  // 공백으로 나눠서 배열에 저장
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(line[j]); // 문자열을 정수로 변환
            }
        }

        // 간선 목록 생성
        Edge[] edgeList = new Edge[n * n];  // n*n개의 간선 저장
        int edgeCount = 0;  // 실제 저장된 간선 수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int weight = adj[i][j]; // 인접 행렬의 가중치 값
                if (weight != 0 && i < j) { // 간선이 존재하고, i < j인 경우만(중복 회피)
                    edgeList[edgeCount++] = new Edge(i, j, weight); //목록에 추가
                }
            }
        }

        for (int i = 0; i < edgeCount; i++) {
            Edge e = edgeList[i];
            System.out.print("(" + e.u + "," + e.v + "," + e.weight + ") ");
        }
        System.out.println();

        // (3) 크루스칼 알고리즘을 이용하여 최소신장트리를 구함
        kruskal(edgeList, edgeCount, n);

        scanner.close();
    }

    // 간선 클래스(u, v, weight)
    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        // 가중치 오름차순 정렬을 위한 비교
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    // 가중치 크기순 정렬 알고리즘
    public static void sortEdges(Edge[] E, int count) {
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (E[j].weight < E[minIndex].weight) {
                    minIndex = j;
                }
            }
            Edge temp = E[i];
            E[i] = E[minIndex];
            E[minIndex] = temp;
        }
    }

    // 크루스칼 알고리즘을 이용하여 최소신장트리를 구하고, 트리 간선 목록을 출력
    public static void kruskal(Edge E[], int edgeCount, int n) {
        //1) 단 하나의 정점만으로 구성된 n개의 집합을 초기화 한다.
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        //2) 모든 간선을 가중치의 크기순으로 정렬하여 배열 A[1~E]에 저장한다.
        sortEdges(E, edgeCount);    // 가중치 크기순 정렬 알고리즘

        Edge[] T = new Edge[n - 1];
        int tCount = 0; //t의 간선수

        //3)while (T의 간선 수 < n-1)
        int i = 0;
        while (i < edgeCount && tCount < n - 1) {
            Edge edge = E[i];
            //4) A에서 최소비용의 간선 (u, v)를 제거한다.
            int u = edge.u;
            int v = edge.v;
            //5) if(정점 u와 v가 다른 집합에 속함)
            if (findSet(u) != findSet(v)) {
                //6) T <- T Union {(u,v)}
                T[tCount] = new Edge(u, v, edge.weight);
                tCount++;
                //7) 정점 u, v가 속한 두 집합을 하나로 합친다.
                union(u, v);
            }
            i++;
        }

        // 트리 간선 목록 출력
        int j = 0;
        while (j < tCount) {
            Edge edge = T[j];
            System.out.print("(" + edge.u + "," + edge.v + "," + edge.weight + ") ");
            j++;
        }
    }

    // 트리를 이용한 상호배타적 집합 표현
    static int[] parent;
    static int[] rank;

    // 경로 압축을 이용한 Find-Set 연산
    public static int findSet(int x) {
        if (parent[x] != x) // makeset 형태면 본인이 부모
            parent[x] = findSet(parent[x]); //아니면 findSet 호출
        return parent[x];
    }

    // 랭크를 이용한 Union 연산
    public static void union(int x, int y) {
        int xRoot = findSet(x);
        int yRoot = findSet(y);

        if (xRoot == yRoot) return; //이미 같은 집합에 속해 있음

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;  // xRoot의 트리 높이가 더 작으면 yRoot 밑으로 붙임
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;  // yRoot의 트리 높이가 더 작으면 xRoot 밑으로 붙임
        } else {
            parent[yRoot] = xRoot;  // 두 트리의 높이가 같으면 하나를 다른 쪽 밑으로 붙임
            rank[xRoot]++;  // 높이(rank)를 1 증가
        }
    }
}
