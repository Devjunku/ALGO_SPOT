package day2206.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1648_격자판채우기 {

    private static final int MOD = 9901;
    private static int N, M, dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N*M+1][1 << N];
        for (int i = 1; i <= N*M; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tiling(1, 0));
    }

    private static int tiling(int board, int bit) {
        if (board == N * M) {
            if ((bit & 1) == 0) {
                dp[board][bit] = 0;
            } else {
                dp[board][bit] = 1;
            }
            return dp[board][bit];
        }

        if (dp[board][bit] != -1) return dp[board][bit];

        if ((bit & 1) == 1) return tiling(board+1, bit >> 1);

        int result = 0;

        if (board % N > 0 && (bit & 3) == 0) {
            result += tiling(board + 1, (bit >> 1) | 1);
        }

        if (board <= N * (M - 1)) {
            result += tiling(board + 1, (bit >> 1) | (1 << N - 1));
        }

        dp[board][bit] = result % MOD;

        return dp[board][bit];
    }
}
