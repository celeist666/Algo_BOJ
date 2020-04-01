// 재귀함수를 이용해서 최소와 최대만 해본

import java.util.*;

public class Main {
    static int n;
    static char[] a = new char[20];
    static ArrayList<String> ans = new ArrayList<>();
    static boolean[] check = new boolean[10];
    static boolean ok(String num) { // 해당 위치에 해당 수를 넣을수 있는지 체크
        for (int i=0; i<n; i++) { // 하나라도 틀리는지 검사
            if (a[i] == '<') {
                if (num.charAt(i) > num.charAt(i+1)) return false;
            } else if (a[i] == '>') {
                if (num.charAt(i) < num.charAt(i+1)) return false;
            }
        }
        return true; // 안틀리면 true
    }
    static void go(int index, String num) {
        if (index == n+1) { // 꽉채웠는지 검사
            if (ok(num)) { // 부등호 성립검사
                ans.add(num);
            }
            return;
        }
        for (int i=0; i<=9; i++) {
            if (check[i]) continue; // 사용되었는지 검사
            check[i] = true;
            go(index+1, num+Integer.toString(i));
            check[i] = false;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            a[i] = sc.next().toCharArray()[0];
        }
        go(0, "");
        Collections.sort(ans);
        int m = ans.size();
        System.out.println(ans.get(m-1)); // 최대
        System.out.println(ans.get(0)); // 최소
    }
}
