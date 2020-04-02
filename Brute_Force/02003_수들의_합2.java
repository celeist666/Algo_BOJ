// i = 0~n, j=i~n 의 두 포문으로 할 수 있지만 그럼 너무 오래걸리니까 안되는 경우를 줄인다.
// 이 경우 n^2에서 n으로 확 줄어든다

import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n+1]; // 탐색할 때 인덱스 초과를 막기위해 +1 해줌
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int left=0; // 시작점, i
        int right=0; // 끝점, j
        int sum=a[0]; // 첫값을 바로 넣음
        int ans=0;
        while (left <= right && right < n) { // 시작과 끝이 어긋나지 않고, 끝점을 넘지 않았을때
            if (sum < s) { // 목표값보다 작으면
                right += 1; // 배열을 늘린다
                sum += a[right];
            } else if (sum == s) { // 목표값과 같으면
                ans += 1;
                right += 1;
                sum += a[right];
            } else if (sum > s) {
                sum -= a[left];
                left++;
                if (left > right && left < n) {
                    right = left;
                    sum = a[left];
                }
            }
        }
        System.out.println(ans);
    }
}
