package day2205.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2143_배열의합 {
    //생각했던 것 메모
    //1. 부 배열의 합을 모두 구한다.
    //2. 몇가지의 경우가 있는지 이분탐색한다. 그렇다면 f(k) = x 에서 x를 구하는건데, k의 값을 조정한다 할때
    //f(k)는 부배열의 합이 k인 수의 갯수를 의미해야한다. 이게 가능?
    //그러면 부배열의 합의 갯수를 dp[i]에 저장???
    //a[] = {1, 2}
    //b[] = {3, 4, 5} 일 때 갯수는 2의 a.length-1제곱 * 2의 b.length-1제곱 -> 그러면 2의 1000제곱을 구하는건 말도안됨
    //그러니 규칙을 찾아보기위해 다 적어본다.
    //ab[0] = a[0] + b[0] = 1 + 3 = 4
    //ab[1] = a[0] + b[1] = 1 + 4 = 5
    //ab[2] = a[0] + b[2] = 1 + 5 = 6
    //ab[3] = a[0] + b[0] + b[1] = 1 + 3 + 4 = 8
    //ab[4] = a[0] + b[0] + b[2] = 1 + 3 + 5 = 9
    //ab[5] = a[0] + b[1] + b[2] = 1 + 4 + 5 = 10
    //ab[6] = a[0] + b[0] + b[1] + b[2] = 1 + 3 + 4 + 5 = 13
    //ab[7] = a[1] + b[0] = 2 + 3 = 5
    //ab[8] = a[1] + b[1] = 2 + 4 = 6
    //ab[9] = a[1] + b[2] = 2 + 5 = 7
    //ab[10] = a[1] + b[0] + b[1] = 2 + 3 + 4 = 9
    //ab[11] = a[1] + b[0] + b[2] = 2 + 3 + 5 = 10
    //ab[12] = a[1] + b[1] + b[2] = 2 + 4 + 5 = 11
    //ab[13] = a[1] + b[0] + b[1] + b[2] = 2 + 3 + 4 + 5 = 14
    //ab[14] = a[0] + a[1] + b[0] = 1 + 2 + 3 = 6
    //ab[15] = a[0] + a[1] + b[1] = 1 + 2 + 4 = 7
    //ab[16] = a[0] + a[1] + b[2] = 1 + 2 + 5 = 8
    //ab[17] = a[0] + a[1] + b[0] + b[1] = 1 + 2 + 3 + 4 = 10
    //ab[18] = a[0] + a[1] + b[0] + b[2] = 1 + 2 + 3 + 5 = 11
    //ab[19] = a[0] + a[1] + b[1] + b[2] = 1 + 2 + 4 + 5 = 12
    //ab[20] = a[0] + a[1] + b[0] + b[1] + b[2] = 1 + 2 + 3 + 4 + 5 = 15

    //,,,,,,,,,,,,, 여기서 10이 5가되는 수의 합을 찾는다하면? a[1], a[7] 이렇게 찾을 수 있다.음...
    //n이 1000이면 1000*1000해서 10만의 경우의 수까지는 찾을만하다. 그런 방법이 있을까?
    //만약 a배열을 누적합을 하면 어떤 의미가 있나? a[i]는 i까지 더한 값이니까, i까지의 부분배열의 합인가? 아니다.
    //a[0] = 1 a[1] = 3
    //b[0] = 3 b[1] = 7 b[2] = 12
    //위의 누적합 상태의 a와 b를 더해보면? 6가지가 나온다.
    //ab[0] = a[0] + b[0] = 4
    //ab[1] = a[0] + b[1] = 8
    //ab[2] = a[0] + b[2] = 13
    //ab[3] = a[1] + b[0] = 6
    //ab[4] = a[1] + b[1] = 10
    //ab[5] = a[1] + b[2] = 15
    //곱해도 보고
    //ab[0] = a[0] * b[0] = 3
    //ab[1] = a[0] * b[1] = 7
    //ab[2] = a[0] * b[2] = 12
    //ab[3] = a[1] * b[0] = 9
    //ab[4] = a[1] * b[1] = 21
    //ab[5] = a[1] * b[2] = 36
    //아닌것같다.
    //그럼 위에서 부분배열의 합이 k가 나올때의 갯수를 정리해보자.
    //왜냐하면 f(k)에서 k값을 찾는다하면 어쨋든 k를 넣으면 합이 나오는 함수가 만들어져야 하니깐 일단 만든다.
    //f(4) = 1
    //f(5) = 2
    //f(6) = 3
    //f(7) = 2
    //f(8) = 2
    //f(9) = 2
    //f(10) = 3
    //f(11) = 2
    //f(12) = 1
    //f(13) = 1
    //f(14) = 1
    //f(15) = 1
    //이랬을 때 특정 원소를 찾는 이분탐색? 그냥 이렇게 만들어지면 인덱스로 접근하니 이분탐색이 아니네
    //이제보니 연속하는 부분 배열만이네?
    //위에 예시는 띄어서도 포함하는거니깐... 바보짓했다
    //하지만 생각한게 아까우니 지우진 않겠다.
    //a, b의 배열에서 모든 누적합을 구해볼까? aa[0][0] aa[1][1] aa[0][1], bb[0][0], bb[0][1], bb[0][2], bb[1][1], bb[1][2], bb[2][2]
    //원소가 네개라면? cc[0][0], cc[1][1], cc[2][2], cc[3][3], cc[0][1], cc[0][2], cc[0][3], cc[1][2], cc[1][3], cc[2][3]
    //2, 3, 4일 때 갯수는 3, 6, 10, 다음은 15예상된다. 3, 4, 5, 6 으로 늘어나는 수열이다.
    //1~n까지 더한 갯수다 -> 1000이면 (1 + 1000) * 1000 / 2 니까 대충 5만이라 할만하긴하다. 두 배열 합치면 10만
    //그러나 10만의 수를 이제 서로 더하면 10만의 제곱......... ㅠㅠㅠㅠㅠㅠㅠㅠㅠ 이것도 안된다.
    //걍 답 보자......... 시팔..............................
    //답을 봣는데 접근 방법은 이렇다. 부 배열의 합을 다 구하는거 까진 맞는데 B로 만들 수 있는 부배열의 합을 다 구한다면
    //B배열을 정렬하고 A의 원소를 하나씩 넣어 이분탐색을 해보면서 T와의 차이 값이 B에 몇개 존재하는지를 찾는 것이였다.
    //너무어렵다.ㅣ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        int b[] = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> aSumList = new ArrayList<>();
        ArrayList<Integer> bSumList = new ArrayList<>();

        //1. A와 B의 부배열의 합을 모두 구한다.
        for (int i = 0; i < N; i++) {
            int sum = a[i];
            aSumList.add(sum);
            for (int j = i+1; j < N; j++) {
                sum += a[j];
                aSumList.add(sum);
            }
        }

        for (int i = 0; i < M; i++) {
            int sum = b[i];
            bSumList.add(sum);
            for (int j = i+1; j < M; j++) {
                sum += b[j];
                bSumList.add(sum);
            }
        }

        Collections.sort(aSumList);
        Collections.sort(bSumList);

        //2. 투포인터로 접근하는 풀이 발견해서 풀어본다.
        int aSize = aSumList.size();
        int bSize = bSumList.size();
        int ap = 0;
        int bp = bSize - 1;
        long answer = 0;

        while (ap < aSize && bp >= 0) {
            int aTmp = aSumList.get(ap);
            int bTmp = bSumList.get(bp);
            int aCnt = 0;
            int bCnt = 0;
            if (aTmp + bTmp == T) {
                while (ap < aSize && aSumList.get(ap) == aTmp) {
                    ap++;
                    aCnt++;
                }


                while (bp >= 0 && bSumList.get(bp) == bTmp) {
                    bp--;
                    bCnt++;
                }

                answer += (long) aCnt * (long) bCnt;
            } else if (aTmp + bTmp > T) {
                bp--;
            } else {
                ap++;
            }
        }

        System.out.println(answer);
    }





}
