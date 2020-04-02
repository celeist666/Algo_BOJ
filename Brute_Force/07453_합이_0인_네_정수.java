import java.util.*;
import java.io.*;
public class Main {
  // lower bound와 upper bound를 알아야 하는데
  // lower bound는 원하는 수보다 크거나 같으면서 가장 작은수,
  // upper bound는 원하는 수보다 크면서 가장 작은수
    static int upper_bound(int[] a, int val) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    static int lower_bound(int[] a, int val) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] >= val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        for (int i=0; i<n; i++) {
            String[] line = bf.readLine().split(" ");
            a[i] = Integer.valueOf(line[0]);
            b[i] = Integer.valueOf(line[1]);
            c[i] = Integer.valueOf(line[2]);
            d[i] = Integer.valueOf(line[3]);
        }
        int[] first = new int[n*n]; // a+b
        int[] second = new int[n*n]; // c+d
        int p=0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                first[p] = a[i]+b[j]; // a+b를 모두 구함
                second[p] = c[i] + d[j];
                p += 1;
            }
        }
        Arrays.sort(second); // second만 정렬해서 first의 값의 음수인 값이 몇개인지 알면된다.
        long ans = 0;
        for (int num : first) {
            int lower = lower_bound(second, -num);
            int upper = upper_bound(second, -num);
            ans += upper - lower;
        }
        System.out.println(ans);
    }
}
