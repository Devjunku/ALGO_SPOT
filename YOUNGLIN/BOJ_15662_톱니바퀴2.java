package day220608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;

public class BOJ_15662_톱니바퀴2 {

    static LinkedList<Integer>[][] top;
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        top = new LinkedList[T][2];
        for (int i = 0; i < T; i++) {
            char[] chars = br.readLine().toCharArray();
            top[i][0] = new LinkedList<>();
            top[i][1] = new LinkedList<>();
            for (int j = 6; j < 8; j++) {
                top[i][0].offer(chars[j] - '0');
            }
            for (int j = 0; j < 2; j++) {
                top[i][0].offer(chars[j] - '0');
            }
            for (int j = 2; j < 6; j++) {
                top[i][1].offer(chars[j] - '0');
            }
        }

        int K = Integer.parseInt(br.readLine());
        while (K --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            rotate(n, d, true, true);
        }

        int answer = 0;
        for (int i = 0; i < T; i++) {
            top[i][0].pollLast();
            if (top[i][0].peekLast() == 1) answer++;
        }

        System.out.println(answer);
    }

    private static void rotate(int n, int d, boolean left, boolean right) {
        if (n > 0 && left && !Objects.equals(top[n][0].peekFirst(), top[n-1][1].peekFirst()))
            rotate(n - 1, d * -1, true, false);
        if (n < T-1 && right && !Objects.equals(top[n][1].peekFirst(), top[n+1][0].peekFirst()))
            rotate(n + 1, d * -1, false, true);

        if (d == 1) {
            top[n][0].offerFirst(top[n][1].pollLast());
            top[n][1].offerFirst(top[n][0].pollLast());
        } else {
            top[n][1].offerLast(top[n][0].pollFirst());
            top[n][0].offerLast(top[n][1].pollFirst());
        }
    }
}
