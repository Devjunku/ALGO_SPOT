from collections import deque
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)

get_key = {
    "a": 0,
    "b": 1,
    "c": 2,
    "d": 3,
    "e": 4,
    "f": 5
}

use_key = {
    "A": 0,
    "B": 1,
    "C": 2,
    "D": 3,
    "E": 4,
    "F": 5
}

key = ["a", "b", "c", "d", "e", "f"]
set_key = set()
set_use_key = set()


for k in key:
    set_key.add(k)
    set_use_key.add(k.upper())

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

n, m = map(int, input().split())
maze = [list(input().strip()) for _ in range(n)]

sx, sy = -1, -1
for i in range(n):
    for j in range(m):
        if maze[i][j] == "0":
            sx, sy = i, j
            break
    if sx != -1 and sy != -1:
        break

visited = [[[False for _ in range(m)] for _ in range(n)] for _ in range((1 << 6) + 1)]


def find_exit(state, x, y, cnt):
    answer = int(10e9)
    q = deque([])

    q.append((state, x, y, cnt))
    while q:
        state, x, y, cnt = q.popleft()
        if maze[x][y] == "1":
            answer = min(answer, cnt)

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if not (0 <= nx < n and 0 <= ny < m): continue
            if visited[state][nx][ny] or maze[nx][ny] == "#": continue

            if (maze[nx][ny] == "." or maze[nx][ny] == "0" or maze[nx][ny] == "1") and not visited[state][nx][ny]:
                visited[state][nx][ny] = True
                q.append((state, nx, ny, cnt+1))

            elif maze[nx][ny] in set_key and not visited[state | (1 << get_key[maze[nx][ny]])][nx][ny]:
                visited[state | (1 << get_key[maze[nx][ny]])][nx][ny] = True
                q.append((state | (1 << get_key[maze[nx][ny]]), nx, ny, cnt+1))

            elif maze[nx][ny] in set_use_key and state & (1 << use_key[maze[nx][ny]]):
                visited[state][nx][ny] = True
                q.append((state, nx, ny, cnt+1))
    
    return answer

ans = find_exit(0, sx, sy, 0)

if ans == int(10e9):
    print(-1)
else:
    print(ans)
