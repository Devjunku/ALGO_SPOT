import sys
input = sys.stdin.readline

r, c = map(int, input().split())
board = [list(input().strip()) for _ in range(r)]
visited = [[False for _ in range(c)] for _ in range(r)]

dx = [-1, 0, 1]
dy = [1, 1, 1]

def dfs(x, y):

    if y == c-1:
        return True

    toggle = False
    for i in range(3):
        nx, ny = x + dx[i], y + dy[i]
        if not (0 <= nx < r and 0 <= ny < c): continue
        if visited[nx][ny]: continue
        if board[nx][ny] == "." and not toggle:
            visited[nx][ny] = True
            toggle = dfs(nx, ny)

    return toggle

answer = 0
for i in range(r):
    visited[i][0] = True
    if dfs(i, 0):
        answer += 1

print(answer)