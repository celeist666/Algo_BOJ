import java.util.*;
class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[n][m];
        for (int i=0; i<n; i++) {
            String s = sc.nextLine();
            for (int j=0; j<m; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }
        int[] dx = {0,0,1,-1}; // four directions
        int[] dy = {1,-1,0,0};
        int[][] d = new int [n][m]; // Creating field
        Queue<Pair> q = new LinkedList<Pair>();
        Queue<Pair> next_queue = new LinkedList<Pair>();
        q.offer(new Pair(0, 0)); // push
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                d[i][j] = -1;
            }
        }
        d[0][0] = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove(); // pop
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) { // four directions
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) { // boundary
                    if (d[nx][ny] == -1) { // never visited
                        if (a[nx][ny] == 0) { // no wall
                            d[nx][ny] = d[x][y];
                            q.offer(new Pair(nx, ny)); // push
                        } else {
                            d[nx][ny] = d[x][y]+1; // yes wall so, +1
                            next_queue.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
            if (q.isEmpty()) {
                q = next_queue;
                next_queue = new LinkedList<Pair>();
            }
        }
        System.out.println(d[n-1][m-1]);
    }
}
