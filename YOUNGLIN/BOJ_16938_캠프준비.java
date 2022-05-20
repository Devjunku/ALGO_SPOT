package day2205.day20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16938_캠프준비 {

    static int N, L, R, X, A[], answer;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);


        for (int i = 0; i < (1 << N); i++) {
            int bit = i;
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if ((bit & (1<<j)) > 0) {
                    list.add(A[j]);
                }
            }

            if (list.size() >= 2) {
                int min = list.get(0);
                int max = list.get(list.size()-1);
                if ((max - min) >= X) {
                    int sum = list.stream().reduce(0, Integer::sum);
                    if (sum >= L && sum <= R) answer++;
                }
            }
        }
        
        System.out.println(answer);
    }
}
