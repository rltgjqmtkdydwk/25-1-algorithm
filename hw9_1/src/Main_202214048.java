import java.util.*;

public class Main_202214048 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("hw9_1:김은총");

        int m = scanner.nextInt();  // 목표 금액(m)
        int n = scanner.nextInt();  // 동전종류(n)
        int[] coins = new int[n];   // n가지 동전액면
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        int[] dp = new int[m + 1];  // 금액 i를 채울때 필요한 최소 동전 수(dp)
        int[] use = new int[m + 1]; // 금액 i를 채울때 마지막으로 사용한 동전 수(use)
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;  // 금액 0 초기화

        for (int i = 1; i <= m; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {   // 금액을 만들 수 있는 방법이 존재할 경우
                    if (dp[i - coin] + 1 < dp[i]) { // (coin을 사용했을 때의 동전 수 < 현재 저장된 최소 동전 수) 일 경우
                        dp[i] = dp[i - coin] + 1;   // 최소 동전 수 갱신
                        use[i] = coin;              // 현재 금액 i를 만들기 위해 마지막으로 사용한 동전 기록
                    }
                }
            }
        }

        if (dp[m] == Integer.MAX_VALUE) {
            System.out.println("해 없음");  // 출력: 답이 없는 경우
        } else {
            System.out.println(dp[m]);  // 출력: 최소 동전수

            // 선택과제 : 사용한 액면별 동전수 출력
            Map<Integer, Integer> countMap = new LinkedHashMap<>();
            List<Integer> usedOrder = new ArrayList<>();

            int cur = m;    // 목표 금액부터 사용한 동전 추적
            while (cur > 0) {
                int c = use[cur];   // cur을 만들기 위해 마지막으로 사용한 동전
                usedOrder.add(c);   // 사용한 동전을 순서대로 기록
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);   // 해당 동전의 사용 횟수 누적
                cur -= c;   // 사용한 동전만큼 금액 줄임
}

            for (int coin : usedOrder) {
                System.out.println(coin + " x " + countMap.get(coin) + "개");   // 액면별 사용 개수
            }
        }

        scanner.close();
    }
}