import sys
input = sys.stdin.readline

n = int(input())

head_tail = [list(input().strip()) for _ in range(n)]

ans = n * n

for bit in range(1 << n):
    temp = [head_tail[i][:] for i in range(n)]
    for i in range(n):
        if bit & (1 << i):
            for j in range(n):
                if temp[i][j] == "T":
                    temp[i][j] = "H"
                else:
                    temp[i][j] = "T"
    cnt = 0
    for i in range(n):
        cnt_t = 0
        for j in range(n):
            if temp[j][i] == "T":
                cnt_t += 1
        cnt += min(cnt_t, n-cnt_t)
    ans = min(ans, cnt)

print(ans)