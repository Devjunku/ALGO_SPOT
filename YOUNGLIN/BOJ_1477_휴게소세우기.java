package day2205.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1477_휴게소세우기 {

    static int N, M, L, H[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        H = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }
        H[N+1] = L;
        Arrays.sort(H);
        System.out.println(binary_search());

        // 생각 : 휴게소가 없는 구간의 길이를 최소화하려한다 -> 휴게소가 없는 구간의 길이를 구하는것. 이 길이를 조절한다.
        // 구간을 정했을 때 휴게소를 몇개 지을 수 있는지 이분탐색한다. (간격을 기준으로 두고 이분탐색 한다)

    }

    private static int binary_search() {
        int left = 1, right = L-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (getCount(mid) > M) left = mid + 1;
            else right = mid -1;
        }
        return left;
    }

    private static int getCount(int mid) {
        int count = 0;
        for (int i = 1; i < H.length; i++) {
            int section = H[i] - H[i-1]; //구간
            count += section / mid;
            if (section % mid == 0) count--;
        }
        return count;
    }
}
