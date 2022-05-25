package day220521;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1114_통나무자르기 {

    static int L, K, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int location[] = new int[2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            location[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = L;
        int answer = 0;
        while (start < end) {
            int mid = (start + end) / 2;

            //mid이 가장 긴 조각일 때 solve에 전달하면 K개의 위치로 잘라보면서 가장 긴조각임이
            //맞다면 true 아니면 false?
            if (solve(mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean solve(int k) {
        for (int i = 0; i < K; i++) {

        }

        return true;
    }
}
