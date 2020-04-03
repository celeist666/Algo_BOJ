// 이동 큐와 각각 문에 세워둘 큐를 만들고
// 각각 문에 세워둘 큐를 만들어 이동큐로 이동하다가 문에 다다랐을때 키가 있으면 들어가고,
// 키가 없으면 문에 사람을 세워둔다.


import java.util.*;
public class Main {
    static char[][] a = new char[111][111]; // 지도상태 // 귀찮아서 그런지 전부 사이즈 111으로 함;;
    static boolean[][] c = new boolean[111][111]; // 가봤는지 여부
    static boolean[] key = new boolean[111];
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) { // 테스트 케이스 만큼
            int n = sc.nextInt();
            int m = sc.nextInt();
            for (int i=0; i<111; i++) { // 지도 입력
                for (int j=0; j<111; j++) {
                    a[i][j] = 0;
                }
            }
            for (int i=2; i<n+2; i++) {
                String line = sc.next();
                for (int j=0; j<m; j++) {
                    a[i][j+2] = line.charAt(j);
                }
            }
            n += 4;
            m += 4;
            for (int i=0; i<n; i++) {
                a[i][0] = '*';
                a[i][1] = '.';
                a[i][m-2] = '.';
                a[i][m-1] = '*';
            }
            for (int j=1; j<m-1; j++) {
                a[0][j] = '*';
                a[1][j] = '.';
                a[n-2][j] = '.';
                a[n-1][j] = '*';
            }
            Arrays.fill(key, false);
            String temp = sc.next();
            int len = temp.length();
            if (temp.charAt(0) != '0') { // 키가 하나도 없으면 0이 출력이니까
                for (int i=0; i<len; i++) {
                    key[temp.charAt(i)-'a'] = true;
                }
            }
            int ans = 0;
            for (int i=0; i<111; i++) {
                Arrays.fill(c[i], false);
            }
            Queue<Integer> q = new LinkedList<>(); // 이동하는큐
            Queue<Integer>[] door = new LinkedList[26]; // 각 문(대문자 알파벳)에서 기다리는 큐
            for (int i=0; i<26; i++) {
                door[i] = new LinkedList<Integer>();
            }
            q.add(1); q.add(1);
            c[1][1] = true;
            while (!q.isEmpty()) {
                int x = q.remove();
                int y = q.remove();
                for (int k=0; k<4; k++) {
                    int nx = x+dx[k];
                    int ny = y+dy[k];
                    if (c[nx][ny]) {
                        continue;
                    }
                    char w = a[nx][ny];
                    if (w == '*') {
                        continue;
                    }
                    c[nx][ny] = true;
                    if (w == '.') {
                        q.add(nx); q.add(ny);
                    } else if (w == '$') {
                        ans += 1;
                        q.add(nx); q.add(ny);
                    } else if (w >= 'A' && w <= 'Z') {
                        if (key[w-'A']) {  // 키가 있으면
                            q.add(nx); q.add(ny); // 문열고 들어감
                        } else {
                            door[w-'A'].add(nx); // 키 없으면 문앞에 기다림
                            door[w-'A'].add(ny);
                        }
                    } else if (w >= 'a' && w <= 'z') { // 키를 만났으면
                        q.add(nx); q.add(ny); // 이동하고
                        if (!key[w-'a']) { // 해당 키가 없으면
                            key[w-'a'] = true; // 키를 습득
                            while (!door[w-'a'].isEmpty()) { // 문에 있는 사람을 이동시키기 위해
                                q.add(door[w-'a'].remove()); // 문 큐에 있는 내용을 이동큐로 옮긴다
                            }
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
