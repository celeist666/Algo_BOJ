import java.util.*;

public class Main {
    public static final int MAX = 1000000;
    static void print(int[] from, int n, int m) {  // n에서 m으로 가는데 필요한 경로
        if (n != m) { // 목적지에 도달하지 못함
            print(from, n, from[m]);
        }
        System.out.print(m + " ");
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] check = new boolean[MAX];
        int[] dist = new int[MAX]; // 거리
        int[] from = new int[MAX]; // 어디서왔는지
        check[n] = true; // 갔었는지
        dist[n] = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(n); // 시작
        while (!q.isEmpty()) {
            int now = q.remove(); // 현재지점
            if (now-1 >= 0) { // -1 이동
                if (check[now-1] == false) {
                    q.add(now-1);
                    check[now-1] = true; // 갔음을 표시
                    dist[now-1] = dist[now] + 1;
                    from[now-1] = now;
                }
            }
            if (now+1 < MAX) { // +1 이동
                if (check[now+1] == false) {
                    q.add(now+1);
                    check[now+1] = true;
                    dist[now+1] = dist[now] + 1;
                    from[now+1] = now;
                }
            }
            if (now*2 < MAX) { // 순간이동
                if (check[now*2] == false) {
                    q.add(now*2);
                    check[now*2] = true;
                    dist[now*2] = dist[now] + 1;
                    from[now*2] = now;
                }
            }
        }
        System.out.println(dist[m]);
        print(from, n, m);
        /* print with stack
        Stack<Integer> ans = new Stack<>();
        for (int i=m; i!=n; i=from[i]) {
            ans.push(i);
        }
        ans.push(n);
        while (!ans.isEmpty()) {
            System.out.print(ans.pop() + " ");
        }
        */
        System.out.println();
    }
}
