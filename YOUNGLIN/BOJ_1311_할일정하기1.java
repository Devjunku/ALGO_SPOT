package day2206.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1311_할일정하기1 {

    static int N, map[][], dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = solve(0, 0);
        System.out.println(answer);
    }

    private static int solve(int now, int state) {
        if (now == N) return 0;
        if (dp[now][state] != 0) return dp[now][state];

        int result = 99999999;
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) == 0)
                result = Math.min(result, map[now][i] + solve(now + 1, state | (1 << i)));
        }
        dp[now][state] = result;
        return dp[now][state];
    }
}
