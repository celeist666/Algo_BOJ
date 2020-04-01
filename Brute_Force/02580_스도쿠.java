import java.util.*;

public class Main {
    public static final int n = 9;
    public static int square(int x, int y) { // 몇번째 정사각형인지
        return (x/3)*3+(y/3);
    }
    public static boolean go(int[][] a, boolean[][][] c, int z) {
        if (z == 81) { // 모든 칸을 다 채움
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
            return true;
        }
        int x = z/n; // 행
        int y = z%n; // 열
        if (a[x][y] != 0) { // 이미 수가 있음
            return go(a, c, z+1);
        } else { // 수가 없으면
            for (int i=1; i<=9; i++) {
                if (!c[0][x][i] && !c[1][y][i] && !c[2][square(x,y)][i]) { // 각각 행,열,몇번네모 체크
                    c[0][x][i] = c[1][y][i] = c[2][square(x,y)][i] = true; // 집어넣기위해 전처리
                    a[x][y] = i; // 집어넣음
                    if (go(a, c, z+1)) { // 다음 실행
                        return true;
                    }
                    a[x][y] = 0; // 원상복귀
                    c[0][x][i] = c[1][y][i] = c[2][square(x,y)][i] = false; // 원상복귀 2
                }
            }
        }
        return false;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[][] a = new int[n][n];
        boolean[][][] c = new boolean[3][n][10]; // 검사가 세가지이기에
        for (int i=0; i<n; i++) { // 스도쿠 입력받기
            for (int j=0; j<n; j++) {
                a[i][j] = sc.nextInt(); // 수 입력
                if (a[i][j] != 0) { // 수가 들어갔을 경우 다들 체크해놓음.
                    c[0][i][a[i][j]] = true;
                    c[1][j][a[i][j]] = true;
                    c[2][square(i,j)][a[i][j]] = true;
                }
            }
        }
        go(a, c, 0);
    }
}
