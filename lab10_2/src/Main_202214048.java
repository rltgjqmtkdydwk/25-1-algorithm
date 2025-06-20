import java.util.Scanner;

public class Main_202214048 {
	static int n;
	static int[][] matrix;
	static int[] visited;    // hw10_2에서 상태를 확장하기 위해 boolean 배열로 하지 말고 int 배열로 - 방문하지 않았으면 0, 방문했으면 1(상수를 지정해도 됨) 
	static int[] result;     // 위상 정렬 결과 저장용 배열
	static int idx;          // result 배열 인덱스

	public static void main(String[] args) {
		System.out.println("lab10_2:김은총");
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
	}


	// 그래프 전체를 깊이우선탐색
	static void graphTraversal() {
		// 상태 배열을 생성하고 초기화
		visited = new int[n];
		result = new int[n];
		idx = n - 1; // 결과 배열은 뒤에서부터 채움

		// 방문하지 않은 정점을 시작 정점으로 깊이우선탐색
		for (int i = 0; i < n; i++) {
			if (visited[i] == 0) {
				dfs(i);
			}
		}
		// 결과 배열 출력
		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + " ");
		}
		
	}

	// 시작 정점을 i로 하여 깊이우선탐택
	static void dfs(int i) {
		visited[i] = 1;

		for (int j = 0; j < n; j++) {
			if (matrix[i][j] == 1) { // i에 인접한 정점 j에 대해
				if (visited[j] == 0) {
					dfs(j);
				}
			}
		}
		result[idx--] = i; // 탐색 완료된 노드를 배열에 저장
	}	
}