package day220614;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_22254_공정컨설턴트호석 {

    static int arr[], X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = 1000000000;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (getTime(mid, N)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(l);
    }

    private static boolean getTime(int mid, int N) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int max = 0;
        for (int i = 0; i < N; i++) {
            int nowSize = pq.size();
            if (nowSize < mid) {
                pq.offer(arr[i]);
                max = Math.max(max, arr[i]);
            } else {
                int nowValue = pq.poll() + arr[i];
                pq.offer(nowValue);
                max = Math.max(nowValue, arr[i]);
            }
            if (max > X) return false;
        }

        return true;
    }
}
