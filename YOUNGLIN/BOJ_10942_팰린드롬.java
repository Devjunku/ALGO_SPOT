package day2205.day30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10942_팰린드롬 {

    static int N, dp[][], arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        solve();

        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(dp[from][to]).append("\n");
        }

        System.out.println(sb);
    }

    private static void solve() {
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i <= N-1; i++)
            if (arr[i] == arr[i + 1])
                dp[i][i + 1] = 1;


        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }
    }
}
