package day2205.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472_다리만들기2 {

    private static int[] parents;


    static class Node implements Comparable<Node>{
        int to;
        int from;
        int value;

        public Node(int to, int from, int value) {
            this.to = to;
            this.from = from;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

    }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int num;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int N, M, map[][];
    static boolean visited[][], linked[];
    static List<Point> firstPoint = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //섬에 번호 붙여주기
        visited = new boolean[N][M];
        num = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] == 0) continue;
                bfs(i, j, num);
                num++;
            }
        }
//        print();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    makeBridge(i, j, map[i][j]);
                }
            }
        }

        num--;

        parents = new int[num];
        for(int i=1; i<num; i++) {
            parents[i] = i;
        }
        int answer = shortestPath();
        System.out.println(answer == 0 ? -1 : answer);
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void makeBridge(int r, int c, int idx) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];
        for(int d=0; d<4; d++) {
            q.add(new int[] {r,c,0});
            visited[r][c] = true;

            while(!q.isEmpty()) {
                int[] p = q.poll();
                int pr = p[0];
                int pc = p[1];
                int move = p[2];

                int nr = pr + dr[d];
                int nc = pc + dc[d];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;

                if(map[nr][nc]!=idx) {
                    if(map[nr][nc] !=0) {
                        int from = idx-1;
                        int to = map[nr][nc]-1;
                        int bridgeLen = move;
                        if(bridgeLen>1) {
                            pq.add(new Node(from, to, bridgeLen));
                            break;
                        }
                    }else {
                        visited[nr][nc] = true;
                        q.add(new int[] {nr, nc, move+1});
                    }
                }
            }
            q.clear();
        }
    }

    private static void bfs(int r, int c, int num) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        visited[r][c] = true;
        map[r][c] = num;
        if (num == 1) firstPoint.add(new Point(r, c));

        while (!q.isEmpty()) {
            Point poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = poll.r + dr[i];
                int nc = poll.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                map[nr][nc] = num;
                if (num == 1) firstPoint.add(new Point(nr, nc));
                q.offer(new Point(nr, nc));
            }
        }
    }

    // 3번 로직 (최소 신장트리 -크루스칼)
    static int shortestPath() {
        int sum =0;
        int size = pq.size();
        for(int i=0; i< size; i++) {
            Node node = pq.poll();
            int x = node.from;
            int y = node.to;

            if(find(x) != find(y)) {
                sum += node.value;
                union(x,y);
            }
        }

        int rx = parents[1];
        for(int i=2; i<num; i++) {
            if(rx != find(parents[i])) {
                // System.out.println("연결 x ");
                return 0;
            }
        }

        return sum;
    }


    static int find(int x) {
        if(parents[x] == x) return x;
        int rx = find(parents[x]);
        return rx;

    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x<y) {
            parents[y]=x;
        }
        else {
            parents[x] =y;
        }
    }
}
