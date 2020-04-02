import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][m];
        for (int i=0; i<n; i++) { // 초기입력
            String s = sc.next();
            for (int j=0; j<m; j++) {
                a[i][j] = s.charAt(j)-'0';
            }
        }
        int ans = 0;
        // 0: -, 1 : |      0이 가로, 1이 세로
        for (int s=0; s<(1<<(n*m)); s++) { // n x m개의 비트마스크로 만듬
            int sum = 0;
            for (int i=0; i<n; i++) { // 가로
                int cur = 0; // 현재 수
                for (int j=0; j<m; j++) {
                    int k = i*m+j;
                    if ((s&(1<<k)) == 0) { // 가로인지
                        cur = cur * 10 + a[i][j];
                    } else { // 세로이기 때문에 앞의 수를 끊고 sum에 더함
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur; // 마지막자리까지가 가로면 더해주는걸 안했기에
            }
            for (int j=0; j<m; j++) { // 세로
                int cur = 0;
                for (int i=0; i<n; i++) {
                    int k = i*m+j;
                    if ((s&(1<<k)) != 0) { // 세로인지
                        cur = cur * 10 + a[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            ans = Math.max(ans,sum);
        }
        System.out.println(ans);
    }
}
