// n x m의 mod 연산은 각각의 mod를 곱한 것과 같다는것을 이용한다.
// 그래서 수를 하나 늘릴때마다

import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] from = new int[n]; // 이전수
            int[] how = new int[n]; // 뭘 추가
            int[] dist = new int[n]; //
            for (int i=0; i<n; i++) {
                from[i] = how[i] = dist[i] = -1;
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(1%n); // 1은 왠만하면 1이 나오지만 N=1이면 0이기에 확인은 해야한다
            dist[1%n] = 0;
            how[1%n] = 1;
            while (!q.isEmpty()) {
                int now = q.remove();
                for (int i=0; i<=1; i++) {
                    int next = (now*10+i)%n; // 뒷자리에 하나 추가
                    if (dist[next] == -1) { // 가본적이 없으면
                        dist[next] = dist[now] + 1; // 지금 +1
                        from[next] = now; // 지금에서 온거니까
                        how[next] = i; // 뭘 추가했나
                        q.add(next);
                    }
                }
            }
            if (dist[0] == -1) {
                System.out.println("BRAK");
            } else {
                StringBuilder ans = new StringBuilder();
                for (int i=0; i!=-1; i=from[i]) {
                    ans.append(Integer.toString(how[i])); // 백트래킹
                }
                System.out.println(ans.reverse());
            }
        }
    }
}
