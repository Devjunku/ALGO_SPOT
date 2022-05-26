package day2205.day26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6209_제자리멀리뛰기 {

    static int d, n, m, stoneIsland[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        stoneIsland = new int[n+2];

        for (int i = 0; i < n; i++) {
            stoneIsland[i] = Integer.parseInt(br.readLine());
        }

        stoneIsland[n] = 0;
        stoneIsland[n+1] = d;

        Arrays.sort(stoneIsland);
        int start = 0;
        int end = 1000000000;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (solve(mid) > m) end = mid - 1;
            else start = mid + 1;
        }

        System.out.println(start-1);
    }

    static int solve(int dist) {
        int cnt = 0;
        for (int i = 1, cur = 0; i <= n + 1; i++) {
            if (stoneIsland[i] - stoneIsland[cur] < dist) cnt++;
            else cur = i;
        }
        return cnt;
    }
}
