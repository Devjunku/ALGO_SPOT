package day220614;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1854_K번째최단경로찾기 {

    static class Node {
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    static int N, M, K;
    static Node[] graph;
    static PriorityQueue<Integer>[] answerPQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new Node[N + 1];
        answerPQ = new PriorityQueue[N + 1];
        for (int i = 0; i < N + 1; i++) {
            answerPQ[i] = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from] = new Node(to, weight, graph[from]);
        }

        dijkstra();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            int dist;
            if (answerPQ[i].size() == K) {
                dist = answerPQ[i].peek();
            } else {
                dist = -1;
            }
            sb.append(dist).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node(1, 0, null));
        answerPQ[1].offer(0);

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int vertex = poll.vertex;
            int weight = poll.weight;

            for (Node temp = graph[vertex]; temp != null; temp = temp.next) {
                //인접한 모든 노드 경로값 추가
                if (answerPQ[temp.vertex].size() < K) {
                    answerPQ[temp.vertex].offer(weight + temp.weight);
                    pq.offer(new Node(temp.vertex, temp.weight + weight, null));
                } else {
                    if (answerPQ[temp.vertex].peek() > weight + temp.weight) {
                        answerPQ[temp.vertex].poll();
                        answerPQ[temp.vertex].offer(weight + temp.weight);
                        pq.offer(new Node(temp.vertex, temp.weight + weight, null));
                    }
                }
            }
        }
    }
}
