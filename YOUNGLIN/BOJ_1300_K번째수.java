package day2205.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1300_K번째수 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long K = Long.parseLong(br.readLine());
        // 아이디어 : B[K] : X 일 때,배열 B 안에 X 이하인 정수는 최소 K개가 된다.
        // 여기서 배열 B는 행렬 A와 같다.
        // 예를 들어 N:3, K:7, X=9이면, 행렬 A 안에 9이하의 정수가 7개인지 확인해보면 된다. 9개니까 틀렸다.
        // 그렇다면 X를 5로 줄여보자. 1+9 / 2 = 5니까. 그러면 A 안에 5이하의 정수는 1 2 2 3 3 4 6개다. 틀렸다.
        // 그렇다면 s = 5 e = 9 -> X = 7 이라면 7이하의 정수가 8개니까 K보다 크다.
        // 그렇다면 s = 5, e = 6 -> X = 5 이라면 5이하의 정수는 6개니까 K보다 작다.
        // 그렇다면 s = 6 e = 6 -> x = 6 이라면 6이하의 정수는 7개니까 K와 같아서 X = 7이다.

        long start = 1;
        long end = K;

        while (end > start) {
            long mid = (start + end) / 2;

            if (solve(mid) >= K) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    private static long solve(long mid) {
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Math.min(mid / i, N);
        }
        return sum;
    }
}
