from collections import deque
import sys
input = sys.stdin.readline

k = int(input())
w, h = map(int, input().split())

INF = int(1e9)

arr = [list(map(int, input().split())) for _ in range(h)]
visited = [[[INF for _ in range(w)] for _ in range(h)] for _ in range(k+1)]
delta = [(0, 1), (1, 0), (0, -1), (-1, 0)]
horse_delta = [
    (-1, -2),
    (-2, -1),
    (-1, 2),
    (-2, 1),
    (1, 2),
    (2, 1),
    (2, -1),
    (1, -2)
]

sx, sy = 0, 0
ex, ey = h-1, w-1
def bfs(sx, sy):

    q = deque([(sx, sy, 0, 0)])

    while q:
        x, y, cnt, c = q.popleft()

        if x == ex and y == ey:
            return cnt

        for i in range(4):
            nx, ny = x + delta[i][0], y + delta[i][1]

            if not (0 <= nx < h and 0 <= ny < w): continue
            if arr[nx][ny] == 1: continue
            if visited[c][nx][ny] != INF : continue

            visited[c][nx][ny] = cnt + 1
            q.append((nx, ny, cnt + 1, c))
        
        if c >= k: continue

        for i in range(8):
            nx, ny = x + horse_delta[i][0], y + horse_delta[i][1]

            if not (0 <= nx < h and 0 <= ny < w): continue
            if arr[nx][ny] == 1: continue
            if visited[c+1][nx][ny] != INF: continue

            visited[c+1][nx][ny] = cnt + 1
            q.append((nx, ny, cnt + 1, c + 1))
    
    return -1

print(bfs(sx, sy))