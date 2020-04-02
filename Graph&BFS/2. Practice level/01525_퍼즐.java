import java.util.*;

public class Main {
    public static final int[] dx = {0, 0, 1, -1}; // 방향들
    public static final int[] dy = {1, -1, 0, 0};
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        int start = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int temp = sc.nextInt();
                if (temp == 0) {
                    temp = 9;
                }
                start = start * 10 + temp; // 9자리 정수로 바꿀꺼다
            }
        }
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer,Integer> d = new HashMap<Integer,Integer>(); // 거리
        d.put(start, 0);
        q.add(start);
        while (!q.isEmpty()) {
            int now_num = q.remove(); // 현재 퍼즐상태
            String now = Integer.toString(now_num); // 현재 퍼즐을 스트링으로
            int z = now.indexOf('9');
            int x = z/3;
            int y = z%3;
            for (int k=0; k<4; k++) { // 네 방향 이동
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) { // 벗어나지않으면
                    StringBuilder next = new StringBuilder(now);
                    char temp = next.charAt(x*3+y); // swap 빈칸을 이동시킴
                    next.setCharAt(x*3+y, next.charAt(nx*3+ny)); // swap
                    next.setCharAt(nx*3+ny, temp); // swap
                    int num = Integer.parseInt(next.toString());
                    if (!d.containsKey(num)) {
                        d.put(num, d.get(now_num)+1);
                        q.add(num);
                    }
                }
            }
        }
        if (d.containsKey(123456789)) {
            System.out.println(d.get(123456789));
        } else {
            System.out.println("-1");
        }
    }
}
