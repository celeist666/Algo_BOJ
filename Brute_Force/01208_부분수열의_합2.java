import java.util.*;

// 두개의 배열을 하나는 비오름차순, 하나는 비내림차순으로 해서 값을 구하는 방식

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int m = n/2; // 정확한 반띵을 위해 둘 다 함.
        n = n-m; // 정확한 반띵을 위해
        int[] first = new int[1<<n];
        for (int i=0; i<(1<<n); i++) { // 모든 부분집합의 합을 비트마스크를 이용해서 구함
            for (int k=0; k<n; k++) {
                if ((i&(1<<k)) == (1<<k)) {
                    first[i] += a[k];
                }
            }
        }
        int[] second = new int[1<<m];
        for (int i=0; i<(1<<m); i++) {
            for (int k=0; k<m; k++) {
                if ((i&(1<<k)) == (1<<k)) {
                    second[i] += a[k+n];
                }
            }
        }
        Arrays.sort(first);
        Arrays.sort(second);
        n = (1<<n);
        m = (1<<m);
        for (int i=0; i<m/2; i++) {// 역순으로 뒤집음
            int temp = second[i];
            second[i] = second[m-i-1];
            second[m-i-1] = temp;
        }
        int i = 0;
        int j = 0;
        long ans = 0;
        while (i < n && j < m) {
            if (first[i] + second[j] == s) { // 답과 같으면
                long c1 = 1;
                long c2 = 1;
                i += 1;
                j += 1;
                while (i < n && first[i] == first[i-1]) { //혹시 같은 수가 있나
                    c1 += 1;
                    i += 1;
                }
                while (j < m && second[j] == second[j-1]) {// 다른 수가 있나
                    c2 += 1;
                    j += 1;
                }
                ans += c1*c2;
            } else if (first[i] + second[j] < s) {
                i += 1;
            } else {
                j += 1;
            }
        }
        if (s == 0) ans -= 1;

        System.out.println(ans);
    }
}
