import java.util.Scanner;

public class Main_202214048 {
	static int n;
	static int[][] matrix;
	static int[] visited;    // int 배열로 - 0: 미방문, 1: 방문완료
	static boolean hasCycle;

	public static void main(String[] args) {
		System.out.println("hw10_3:김은총");
		Scanner scanner = new Scanner(System.in);

		// (1) 방향그래프의 정점수 n을 입력받음
		n = scanner.nextInt();

		// (2) 인접행렬을 생성하고 인접행렬 내용을 입력받아 채움
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = scanner.nextInt();
			}
		}

		// (3) 그래프를 깊이우선탐색하여 순서대로 정점 번호 출력
		graphTraversal();
		scanner.close();
	}


	// 그래프 전체를 깊이우선탐색
	static void graphTraversal() {
		// 상태 배열을 생성하고 초기화
		visited = new int[n];
		hasCycle = false;

		// 방문하지 않은 정점을 시작 정점으로 깊이우선탐색
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				if (dfs(i, -1)) { // 시작 정점, 부모 없음
					hasCycle = true;
					break;
				}
			}
		}

		// 결과 출력
		if (hasCycle) {
			System.out.println("사이클이 존재합니다.");
		} else {
			System.out.println("사이클이 존재하지 않습니다.");
		}
	}

	// 시작 정점을 i로 하여 깊이우선탐택
	// 부모 정점 정보로 사이클 판별
	static boolean dfs(int current, int parent) {
		visited[current] = 1;

		for (int next = 0; next < n; next++) {
			if (matrix[current][next] == 1) {
				if (visited[next] == 0) {
					if (dfs(next, current)) return true;
				} else if (next != parent) {	// 이미 방문한 정점이 부모가 아니라면 사이클
					return true;
				}
			}
		}
		return false;
	}
}