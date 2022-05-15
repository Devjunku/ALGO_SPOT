# 1 → 올라감
# 0 → 내려감
import sys
input = sys.stdin.readline

MOD = 1000000000

"""
1. 20까지 이므로 1 << 20을 모두 순회해야함.
2. 시작점은 1 ~ 9까지 모두 순회해야함
3. 돌다가 말이 안 되면(0보다 낮거나 9보다 큰 숫자가 나와야 하는 경우) break
4. 1, 2, 3, 4, 5, 6, 7, 8, 9, 0이 모두 포함되어 있지 않으면 continue
주의: 브루트포스는 절대 못품, DP로 풀어야 함.
"""
n = int(input())
overlap_bit = set()
cnt = 0
# 모든 경우의 수를 따짐
for i in range(1 << n):
    # 시작점
    for j in range(1, 10):
        # 시작점은 기록해놓고
        tmp = [j]
        # 나머지에 대해 진행함
        # 어차피 1 << 1 부터 1 << n 까지임
        for k in range(1, n):
            # 이때 1이면 1칸 +
            if i & (1 << k) and tmp[-1]+1 < 10:
                tmp.append(tmp[-1]+1)
            # 0이면 1칸 -
            elif ~ i & (1 << k) and tmp[-1]-1 >= 0: 
                tmp.append(tmp[-1]-1)
            # 범위에 벗어났으면 끝내야함.
            else:
                break
            # break에 걸리지 않았다면,
        else:
            string = "".join(map(str, tmp))
            if string not in overlap_bit:
                overlap_bit.add(string)
            else:
                break

            # 0부터 9까지 들어있는지 확인
            for k in range(10):
                if k not in tmp:
                    toggle = False
                    break
            else:
                # print(string)
                cnt += 1

                

print(cnt)






