package day2206.day20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15787_기차가어둠을헤치고은하수를 {

    static int N, M;
    static int[][] train;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        train = new int[N][20];
        checked = new boolean[1 << 21 - 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int x = 0;
            if (o < 3)
                x = Integer.parseInt(st.nextToken()) - 1;

            switch (o) {
                case 1:
                    add(n, x);
                    break;
                case 2:
                    delete(n, x);
                    break;
                case 3:
                    goBack(n);
                    break;
                case 4:
                    goFront(n);
                    break;
            }
//            System.out.println("=================================");
//            for (int j = 0; j < N; j++) {
//                System.out.println(Arrays.toString(train[j]));
//            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int bit = 0;
            for (int j = 0; j < 20; j++) {
                bit |= train[i][j] << j;
            }
            if (checked[bit]) continue;
            answer++;
            checked[bit] = true;
        }

        System.out.println(answer);
    }

    private static void add(int n, int x) {
        train[n][x] = 1;
    }

    private static void delete(int n, int x) {
        train[n][x] = 0;
    }

    private static void goBack(int n) {
        for (int i = 19; i >= 1; i--) {
            train[n][i] = train[n][i - 1];
        }
        train[n][0] = 0;
    }

    private static void goFront(int n) {
        for (int i = 0; i <= 18; i++) {
            train[n][i] = train[n][i + 1];
        }
        train[n][19] = 0;
    }
}
