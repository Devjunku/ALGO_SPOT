package day2205.day24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {

    static int R, C, map[][], answer;
    static boolean[][] visited;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (chars[j] == 'x')
                    map[i][j] = 0;
                else
                    map[i][j] = 1;
            }
        }

        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            visited[i][0] = true;
            flag = false;
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    private static void dfs(int r, int c) {
        if (c == C-1) {
            answer++;
            flag = true;
            return;
        }

        for (int i = -1; i <= 1; i++) {
            if (r+i < 0 || r+i >= R) continue;
            if (map[r+i][c+1] == 0) continue;
            if (visited[r+i][c+1]) continue;
            visited[r+i][c+1] = true;
            dfs(r+i, c+1);
            if (flag) return;
        }
    }
}
