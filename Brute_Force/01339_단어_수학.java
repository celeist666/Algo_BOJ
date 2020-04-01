import java.util.*;
public class Main {
    static boolean next_permutation(int[] a) { // 다음 순열 찾는 함수
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
    static int[] alpha = new int[256]; // 각 알파벳의 매핑값
    static int calc(String[] a, Character[] letters, int[] d) {
        int m = letters.length;
        int sum = 0;
        for (int i=0; i<m; i++) {
            alpha[letters[i]] = d[i];
        }
        for (String s : a) { // 각 입력받은 단어들 포문
            int now = 0;
            for (char x : s.toCharArray()) { // 단어의 각 글자들의 수
                now = now * 10 + alpha[x]; // 숫자를 앞에서 부터 채우기
            }
            sum += now;
        }
        return sum;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] a = new String[n]; // 단어들 저장할 스트링 배열
        HashSet<Character> s = new HashSet<>(); // 중복 없앰
        for (int i=0; i<n; i++) { // 단어 한 줄 저장
            a[i] = sc.next();
            for (char x : a[i].toCharArray()) {
                s.add(x); // 중복없앰
            }
        }
        Character[] letters = s.toArray(new Character[s.size()]); // HashSet을 다시 Char배열로
        int m = letters.length;
        int[] d = new int[m]; // 중복없앤 문자들을 각각 '값'으로 변경
        for (int i=0; i<m; i++) { // 9부터 넣음
            d[i] = 9-i;
        }
        Arrays.sort(d);
        int ans = 0;
        do {
            int now = calc(a, letters, d);
            if (ans < now) { // 최대값과 비교
                ans = now;
            }
        } while(next_permutation(d));
        System.out.println(ans);
    }
}
