from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

visited = [[False for _ in range(m)] for _ in range(n)]

def bfs(x, y, cnt):
    visited[x][y] = True
    board[x][y] = cnt
    q = deque([])
    q.append((x, y))
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if not (0 <= nx < n and 0 <= ny < m): continue
            if visited[nx][ny]: continue
            if board[nx][ny] == 0: continue
            visited[nx][ny] = True
            q.append((nx, ny))
            board[nx][ny] = cnt

cnt = 1
for i in range(n):
    for j in range(m):
        if board[i][j] != 0 and not visited[i][j]:
            bfs(i, j, cnt)
            cnt += 1

start_point_dir = []
overlap_set = set()
for i in range(n):
    for j in range(m):
        if board[i][j] != 0:
            for k in range(4):
                x, y = i, j
                origin = board[i][j]
                count_d = 0
                while True:
                    nx, ny = x + dx[k], y + dy[k]
                    if not (0 <= nx < n and 0 <= ny < m): break
                    if board[nx][ny] == origin: break
                    count_d += 1
                    if board[nx][ny] != 0 and board[nx][ny] != origin and count_d <= 2: break
                    if board[nx][ny] != 0 and board[nx][ny] != origin and count_d > 2:
                        if (origin, board[nx][ny], count_d-1) in overlap_set or (board[nx][ny], origin, count_d-1) in overlap_set:
                            break
                        start_point_dir.append((origin, board[nx][ny], count_d-1))
                        overlap_set.add((origin, board[nx][ny], count_d-1))
                        overlap_set.add((board[nx][ny], origin, count_d-1))
                        break
                    x, y = nx, ny

start_point_dir.sort(key=lambda x: x[2])
parents = [x for x in range(cnt)]

def find_set(x):
    while x != parents[x]:
        x = parents[x]
    return x

is_visited = [0 for _ in range(cnt)]
distance, cnt_c = 0, 0
for a, b, cost in start_point_dir:
    if find_set(a) != find_set(b):
        parents[find_set(b)] = find_set(a)
        is_visited[a] = 1
        is_visited[b] = 1
        distance += cost
        cnt_c += 1

        if cnt_c > cnt:
            break

print(distance) if distance != 0 and sum(is_visited) == cnt-1 else print(-1)