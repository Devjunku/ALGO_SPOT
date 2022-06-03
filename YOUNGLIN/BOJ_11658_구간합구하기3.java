package day2206.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11658_구간합구하기3 {

    static int N, M, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (j == 0)
                    map[i][j] = Integer.parseInt(st.nextToken());
                else
                    map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                int r1 = Integer.parseInt(st.nextToken())-1;
                int c1 = Integer.parseInt(st.nextToken())-1;
                int r2 = Integer.parseInt(st.nextToken())-1;
                int c2 = Integer.parseInt(st.nextToken())-1;
                int sum = 0;
                for (int j = r1; j <= r2; j++) {
                    sum += map[j][c2];
                    if (c1 > 0) sum -= map[j][c1-1];
                }
                sb.append(sum).append("\n");
            } else {
                int r = Integer.parseInt(st.nextToken())-1;
                int c = Integer.parseInt(st.nextToken())-1;
                int v = Integer.parseInt(st.nextToken());
                int diff;
                if (c > 0) {
                    diff = v - (map[r][c] - map[r][c-1]);
                } else {
                    diff = v - map[r][c];
                }

                for (int j = c; j < N; j++) {
                    map[r][j] += diff;
                }
            }
        }

        System.out.println(sb);
    }
}
