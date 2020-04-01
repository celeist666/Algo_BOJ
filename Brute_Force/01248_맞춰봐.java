import java.util.*;
public class Main {
    static int n;
    static int[][] sign;
    static int[] ans;
    static boolean check(int index) { // 써도 되는지 검사
        int sum = 0;
        for (int i=index; i>=0; i--) { // 넣어도 되는 수의 경우 해당 열이 다 부호가 맞아야한다.
            sum += ans[i];
            if (sign[i][index] == 0) { // 0일 경우
                if (sum != 0) return false;
            } else if (sign[i][index] < 0) { // 음수
                if (sum >= 0) return false;
            } else if (sign[i][index] > 0) { // 양수
                if (sum <= 0) return false;
            }
        }
        return true;
    }
    static boolean go(int index) {
        if (index == n) { // 수 꽉 참
            return true;
        }
        if (sign[index][index] == 0) { // 0임
            ans[index] = 0;
            return check(index) && go(index+1);
        }
        for (int i=1; i<=10; i++) { // 해당 부호와 1~10 집어넣기
            ans[index] = sign[index][index]*i;
            if (check(index) && go(index+1)) return true;
        }
        return false;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ans = new int[n];
        sign = new int[n][n];
        String s = sc.next();
        int cnt = 0;
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                char x = s.charAt(cnt); // s를 sign에 매핑
                if (x == '0') {
                    sign[i][j] = 0;
                } else if (x == '+') {
                    sign[i][j] = 1;
                } else {
                    sign[i][j] = -1;
                }
                cnt += 1;
            }
        }
        go(0);
        for (int i=0; i<n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
