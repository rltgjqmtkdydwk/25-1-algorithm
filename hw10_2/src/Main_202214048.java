import java.util.Scanner;

public class Main_202214048 {
	static int n;
	static int[][] matrix;
	static int[] visited;    // int 배열로 - 0: 미방문, 1: 방문완료, 2: 방문중
	static int[] result;     // 위상 정렬 결과 저장용 배열
	static int idx;          // result 배열 인덱스
	static boolean isDAG;

	public static void main(String[] args) {
		System.out.println("hw10_2:김은총");
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
		result = new int[n];
		idx = n - 1; // 결과 배열은 뒤에서부터 채움
		isDAG = true; 

		// 방문하지 않은 정점을 시작 정점으로 깊이우선탐색
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				if (!dfs(i)) {
					isDAG = false;
					break;
				}
			}
		}
		// 결과 출력
		if (isDAG) {
			System.out.println("DAG입니다.");
		} else {
			System.out.println("사이클이 존재하므로 DAG가 아닙니다.");
		}
		
	}

	// 시작 정점을 i로 하여 깊이우선탐택
	static boolean dfs(int i) {
		visited[i] = 2; // 방문중인 상태

		for (int j = 0; j < n; j++) {
			if (matrix[i][j] == 1) {
				if (visited[j] == 0) {
					if (!dfs(j)) {
						return false;
					}
				} else if (visited[j] == 2) {	// 사이클 발견
					return false;
				}
			}
		}
		visited[i] = 1; // 방문완료 상태로 전환
		result[idx--] = i;
		return true;
	}	
}