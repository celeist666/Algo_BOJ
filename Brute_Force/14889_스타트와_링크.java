import java.util.*;
public class Main {
    static boolean next_permutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
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
        int[] b = new int[n]; // 팀으로 나뉠 사람들. 0과 1로 구분할 예정
        for (int i=0; i<n/2; i++) {
            b[i] = 1;
        }
        Arrays.sort(b); // 오름차순 정렬
        int ans = 2147483647; // int 최대값. 최소값을 구하는거라서 비교를 최대 가능값과 함.
        do {
            ArrayList<Integer> first = new ArrayList<>(); // 팀1
            ArrayList<Integer> second = new ArrayList<>(); // 팀2
            for (int i=0; i<n; i++) { // 0이냐 1이냐에 따라 팀을 가름
                if (b[i] == 0) {
                    first.add(i);
                } else {
                    second.add(i);
                }
            }
            int one = 0;
            int two = 0;
            for (int i=0; i<n/2; i++) { // first, second로 각 팀의 값들을 알아내 더함
                for (int j=0; j<n/2; j++) {
                    if (i == j) continue;
                    one += a[first.get(i)][first.get(j)];
                    two += a[second.get(i)][second.get(j)];
                }
            }
            int diff = one-two;
            if (diff < 0) diff = -diff;
            if (ans > diff) ans = diff;
        } while(next_permutation(b));
        System.out.println(ans);
    }
}
