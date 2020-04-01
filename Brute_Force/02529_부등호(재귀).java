import java.util.*;

public class Main {
    static int n;
    static char[] a = new char[20];
    static ArrayList<String> ans = new ArrayList<>();
    static boolean[] check = new boolean[10];
    static boolean good(char x, char y, char op) { // 숫자를 추가하기 전에 괜찮은지 미리 판단하는 함수
        if (op == '<') {
            if (x > y) return false;
        }
        if (op == '>') {
            if (x < y) return false;
        }
        return true;
    }
    static void go(int index, String num) {
        if (index == n+1) { // 숫자를 다 채웠으면 정상인게 확실하기에 바로 추가
            ans.add(num);
            return;
        }
        for (int i=0; i<=9; i++) {
            if (check[i]) continue;
            if (index == 0 || good(num.charAt(index-1), (char)(i+'0'), a[index-1])) {
                check[i] = true;
                go(index+1, num+Integer.toString(i));
                check[i] = false;
            }
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
        System.out.println(ans.get(m-1));
        System.out.println(ans.get(0));
    }
}
