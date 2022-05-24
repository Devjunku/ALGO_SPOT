package day2205.day24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471_게리맨더링 {

    static class Node {
        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    static int N, p[], answer = Integer.MAX_VALUE, fullStat;
    static Node[] graph;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        p = new int[N + 1];
        graph = new Node[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                graph[i] = new Node(Integer.parseInt(st.nextToken()), graph[i]);
            }
        }

        fullStat = (1 << N + 1) - 1;
        for (int i = 0; i < N - 1; i++) {
            int stat = 1 << 1;
            dfs(1, p[1], i, stat);
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void dfs(int idx, int cost, int r, int stat) {
        if (r == 0) {
            if (set.add(stat)) {
                getDiff(stat);
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            stat |= 1 << (i + 1);
            dfs(i + 1, cost + p[i+1], r-1, stat);
            stat ^= 1 << (i + 1);
        }
    }

    private static void getDiff(int stat) {
        int a = stat;
        int b = fullStat^(a); //고른 a의 반대 도시들

        int aCost, bCost;
        if((aCost = linkTesting(a)) != -1 & (bCost = linkTesting(b)) != -1) {
            answer = Math.min(answer, Math.abs(aCost - bCost));
        }
    }

    private static int linkTesting(int stat) {
        List<Integer> city = new ArrayList<>();
        for (int i = 1; i < N+1; i++) {
            if((stat & (1 << i)) == 1 << i) {
                city.add(i);
            }
        }

        boolean[] check = new boolean[N + 1];
        int st = city.get(0);
        check[st] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(st);
        int cost = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            cost += p[cur];

            for (Node temp = graph[cur]; temp != null ; temp = temp.next) {
                int vertex = temp.vertex;
                if (!check[vertex] && city.contains(vertex)) {
                    check[vertex] = true;
                    q.offer(vertex);
                }
            }
        }

        for (int i : city) {
            if (!check[i]) return -1;
        }

        return cost;
    }
}
