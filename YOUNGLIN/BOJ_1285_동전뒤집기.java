package day2205.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1285_동전뒤집기 {

    static int n, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (chars[j] == 'H') map[i][j] = 0;
                else map[i][j] = 1;
            }
        }
        
        int answer = n*n;

        for (int bit = 0; bit < (1 << n); bit++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int back = 0;
                for (int j = 0; j < n; j++) {
                    int cur = map[i][j];

                    if ((bit & (1 << j)) != 0) {
                        cur = (cur == 1 ? 0 : 1);
                    }

                    if (cur == 1) {
                        back++;
                    }
                }
                sum += Math.min(back, n - back);
            }
            answer = Math.min(answer, sum);
        }

        System.out.println(answer);
    }
}
