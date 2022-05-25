package day2205.day25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {

    static class Point {
        int r, c, bit, dist;

        public Point(int r, int c, int bit, int dist) {
            this.r = r;
            this.c = c;
            this.bit = bit;
            this.dist = dist;
        }
    }

    private static final int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static int N, M, sr, sc;
    static char map[][];
    static boolean visited[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[(1 << 7)-1][N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j];
                if (chars[j] == '0') {
                    sr = i;
                    sc = j;
                    map[i][j] = '.';
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc, 0, 0));
        visited[0][sr][sc] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (map[p.r][p.c] == '1') {
                return p.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[p.bit][nr][nc]) continue;
                if (map[nr][nc] == '#') continue;
                if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                    int nextBit = p.bit | 1 << (map[nr][nc] - 'a' + 1);
                    visited[nextBit][nr][nc] = true;
                    queue.offer(new Point(nr, nc, nextBit, p.dist + 1));
                } else if (map[nr][nc] == '.' || map[nr][nc] == '1') {
                    visited[p.bit][nr][nc] = true;
                    queue.offer(new Point(nr, nc, p.bit, p.dist + 1));
                } else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F' && (p.bit & 1 << (map[nr][nc] - 'A' + 1)) > 0) {
                    visited[p.bit][nr][nc] = true;
                    queue.offer(new Point(nr, nc, p.bit, p.dist + 1));
                }
            }
        }

        return -1;
    }
}
