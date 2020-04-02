import java.util.*;
public class Main {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static final int LIMIT = 5;
    static int[] gen(int k) { // 방향생성
        int[] a = new int[LIMIT];
        for (int i=0; i<LIMIT; i++) {
            a[i] = (k&3);
            k >>= 2;
        }
        return a;
    }
    static int check(int[][] a, int[] dirs) { // 실행
        int n = a.length;
        int[][] d = new int[n][n];
        boolean[][] merged = new boolean[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                d[i][j] = a[i][j]; // d = 이동시킬거 a는 원본
            }
        }
        // 0: down, 1: up, 2: left, 3: right
        for (int dir : dirs) {
            boolean ok = false;
            for (int i=0; i<n; i++) { // 이동을 시작할때는 전부 합쳐지 않은 상태로 초기화
                for (int j=0; j<n; j++) {
                    merged[i][j] = false; // 합쳐진상태 초기화
                }
            }
            while (true) { // 한번만 움직이는게 아니고 그 방향으로 쭉 움직이는거라
                ok = false; // 움직여지지않음
                if (dir == 0) { // 밑에서부터 위로 살펴봐야함 그래서 첫 열부터
                    for (int i=n-2; i>=0; i--) { // 인덱스가 n-1까지고 제일 마지막은 안움직이니까.
                        for (int j=0; j<n; j++) {
                            if (d[i][j] == 0) continue; // 빈칸이면 다음꺼 확인
                            if (d[i+1][j] == 0) { // 다음칸이 비어있으면
                                d[i+1][j] = d[i][j]; // 이동
                                merged[i+1][j] = merged[i][j]; // 합친상태도 이동
                                d[i][j] = 0; // 원래 위치는 빈칸
                                ok = true;
                            } else if (d[i+1][j] == d[i][j]) { // 이동하려는 칸에 같은 수가 있으면
                                if (merged[i][j] == false && merged[i+1][j] == false) { // 둘다 안합쳐졌었으면
                                    d[i+1][j] *= 2; // 합치고
                                    merged[i+1][j] = true; // 합친상태로 바꾸고
                                    d[i][j] = 0; // 이동전 위치는 삭제
                                    ok = true;
                                }
                            }
                        }
                    }
                } else if (dir == 1) {
                    for (int i=1; i<n; i++) {
                        for (int j=0; j<n; j++) {
                            if (d[i][j] == 0) continue;
                            if (d[i-1][j] == 0) {
                                d[i-1][j] = d[i][j];
                                merged[i-1][j] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i-1][j] == d[i][j]) {
                                if (merged[i][j] == false && merged[i-1][j] == false) {
                                    d[i-1][j] *= 2;
                                    merged[i-1][j] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                } else if (dir == 2) {
                    for (int j=1; j<n; j++) {
                        for (int i=0; i<n; i++) {
                            if (d[i][j] == 0) continue;
                            if (d[i][j-1] == 0) {
                                d[i][j-1] = d[i][j];
                                merged[i][j-1] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i][j-1] == d[i][j]) {
                                if (merged[i][j] == false && merged[i][j-1] == false) {
                                    d[i][j-1] *= 2;
                                    merged[i][j-1] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                } else if (dir == 3) {
                    for (int j=n-2; j>=0; j--) {
                        for (int i=0; i<n; i++) {
                            if (d[i][j] == 0) continue;
                            if (d[i][j+1] == 0) {
                                d[i][j+1] = d[i][j];
                                merged[i][j+1] = merged[i][j];
                                d[i][j] = 0;
                                ok = true;
                            } else if (d[i][j+1] == d[i][j]) {
                                if (merged[i][j] == false && merged[i][j+1] == false) {
                                    d[i][j+1] *= 2;
                                    merged[i][j+1] = true;
                                    d[i][j] = 0;
                                    ok = true;
                                }
                            }
                        }
                    }
                }
                if (ok == false) break;
            }
        }
        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (ans < d[i][j]) {
                    ans = d[i][j];
                }
            }
        }
        return ans;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        for (int k=0; k<(1<<(LIMIT*2)); k++) { // 4방향이라 마스크 2개 필요해서 LIMIT * 2
            int[] dir = gen(k); // 방향들 생성
            int cur = check(a, dir); // 실행
            if (ans < cur) ans = cur; // 최대값 갱
        }
        System.out.println(ans);
    }
}
