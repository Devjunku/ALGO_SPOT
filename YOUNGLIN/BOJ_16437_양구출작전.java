package day2205.day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
15
S 100 1
W 500 1
W 100 1
S 1000 2
W 1000 2
S 900 6
W 900 5
S 600 8
S 1000 3
S 100 10
S 100 11
S 200 6
S 100 4
S 200 4

answer = 2100
*/
public class BOJ_16437_양구출작전 {

    static class Node {
        int vertex;
        long wc;
        long sc;
        Node next;

        public Node(int vertex, long wc, long sc, Node next) {
            this.vertex = vertex;
            this.wc = wc;
            this.sc = sc;
            this.next = next;
        }
    }

    static int N;
    static Node[] graph;
    static boolean visited[], isWolf[];

    public static void main(String[] args) throws IOException {
        input();
        //1번 노드의 연결되어있는 노드들로부터 최종 생존된 양의 갯수를 받아온다.
        long sum = dfs(1, 0, 0);

        System.out.println(sum);
    }


    private static long dfs(int now, long wolfCnt, long sheepCnt) {
        long sum = sheepCnt;
        //연결된 자식노드의 양의 마리수를 합산해옴
        for (Node temp = graph[now]; temp != null; temp = temp.next) {
            long wc = temp.wc;
            long sc = temp.sc;
            sum += dfs(temp.vertex, wc, sc);
        }

        //현재 섬이 늑대섬이면 현재 섬이 가지고있는 늑대 수를 한번만 빼줌
        if (isWolf[now]) sum -= wolfCnt;
        sum = sum < 0 ? 0 : sum;

        return sum;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new Node[N + 1];
        visited = new boolean[N + 1];
        isWolf = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            long a = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            if (t.equals("W")) {
                isWolf[i] = true;
                graph[p] = new Node(i, a, 0, graph[p]);
            } else {
                graph[p] = new Node(i, 0, a, graph[p]);
            }
        }
    }
}
