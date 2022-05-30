from collections import deque
import sys
input = sys.stdin.readline

n = int(input())
price = [list(input().strip()) for _ in range(n)]
state = 1
answer = 1
is_buy = [[[1 for _ in range(n)] for _ in range(n)] for _ in range(1 << (n+1))]


def bfs(state, p, now):
    global answer

    q = deque([(state, p, now, 1)])

    while q:
        state, p, now, cnt = q.popleft()

        if answer < cnt: answer = max(answer, cnt)

        for nxt in range(n):
            if state & (1 << nxt): continue
            if p > int(price[now][nxt]): continue
            if is_buy[state][now][nxt] >= cnt+1:
                continue

            is_buy[state][now][nxt] = cnt+1
            q.append((state | (1 << nxt), int(price[now][nxt]), nxt, cnt + 1))

bfs(1, 0, 0)
print(answer)