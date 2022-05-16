package day220516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1800_인터넷설치 {

    static class Point implements Comparable<Point>{
        int vertex, weight;

        public Point(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }


        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }

    static class Node {
        int vertex, price;
        Node next;

        public Node(int vertex, int price, Node next) {
            this.vertex = vertex;
            this.price = price;
            this.next = next;
        }
    }

    private final static int INF = 1000001;
    static int N, P, K, dijk[];
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new Node[N + 1];

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            graph[from] = new Node(to, price, graph[from]);
            graph[to] = new Node(from, price, graph[to]);
        }
        int ans = -1;
        int start = 0;
        int end = 1000000;
        while (start <= end) {
            dijk = new int[N + 1];
            Arrays.fill(dijk, INF);
            int mid = (start + end) / 2;
            //mid값 미만으로 모든 간선을 통과할 수 있는지
            if (dijkstra(mid)) {
                ans = end;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(ans);
    }

    // k값 이하의 간선으로 N에 도착할 수 잇는지 확인하는 함수
    private static boolean dijkstra(int price) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(1, 0));
        dijk[1] = 0;
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if (now.vertex == N) {
                return true;
            }
            for (Node temp = graph[now.vertex]; temp != null; temp = temp.next) {
                // 갱신 가능하면 갱신
                if (dijk[temp.vertex] > temp.price) {
                    dijk[temp.vertex] = temp.price;
                    if (temp.price > price && now.weight < K) {
                        pq.offer(new Point(temp.vertex, now.weight + 1));
                    } else if (temp.price <= price) {
                        pq.offer(new Point(temp.vertex, now.weight));
                    }
                }
            }
        }

        return false;
    }
}
