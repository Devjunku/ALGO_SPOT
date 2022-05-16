package day2205.day13;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1562_계단수 {

    static int dp[][][], VISIT = 1 << 10, MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1][10][VISIT];
        //dp[i][j][k] = i개의 수 중에서 j로 끝나고, k를 사용한 수의 갯수
        //ex) dp[10][9][1023] = 10자리수중 9로 끝나면서 0~9의 수를 다 사용한 수의 갯수
        //ex) dp[10][9][1022] = 10자리수중 9로 끝나면서 1~9의 수를 다 사용한 수의 갯수
        //1022이 1~9인 이유는 1111111110 이기 때문에 0은 사용하지 않은것으로 본다.

        for (int i = 1; i < 10; i++) {
            dp[1][i][1<<i] = 1;
        }
        int bit;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < VISIT; k++) {
                    bit = k | (1 << j);
                    dp[i][j][bit] = (dp[i][j][bit] +
                            ((0<j ? dp[i-1][j-1][k] : 0) +
                                    (j<9 ? dp[i-1][j+1][k] : 0)) % MOD
                    ) % MOD;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[N][i][VISIT - 1]) % MOD;
        }
        System.out.println(sum);
    }
}

