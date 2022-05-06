package day2205.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13904_과제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int num = Integer.parseInt(br.readLine());
        ArrayList<Integer> day[] = new ArrayList[1001];
        for (int i = 0; i < 1001; i++) day[i] = new ArrayList<>();
        int lastDay = 0; //과제 마감일이 가장 늦은 날
        //각 마감일 별 과제 목록 생성
        while (num --> 0) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            day[time].add(score);
            if (time > lastDay) lastDay = time;
        }
        //마지막 날짜부터 1일까지 각각의 날짜마다 진행할 수 있는 과제 갱신하며 제일 높은 과제 제출
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1); //내림차순 정렬
        int sum = 0;
        for (int i = lastDay; i > 0; i--) {
            for (int score : day[i]) {
                pq.offer(score);
            }
            if (pq.isEmpty()) continue;
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
