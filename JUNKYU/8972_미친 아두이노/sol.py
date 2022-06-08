import sys
from copy import deepcopy
input = sys.stdin.readline

r, c = map(int, input().split())
board = [list(input().strip()) for _ in range(r)]

sx, sy = -1, -1
crazy_adu = []
for i in range(r):
    for j in range(c):
        if board[i][j] == "I":
            sx, sy = i, j
        elif board[i][j] == "R":
            crazy_adu.append([i, j])

d_order = list(input().strip())
d_order = [int(i)-1 for i in d_order]

n = len(d_order)
dx = [1, 1, 1, 0, 0, 0, -1, -1, -1]
dy = [-1, 0, 1, -1, 0, 1, -1, 0, 1]

cdx = [1, 1, 1, 0, 0, -1, -1, -1]
cdy = [-1, 0, 1, -1, 1, -1, 0, 1]

def mht(jongsu, crazy):
    return abs(jongsu[0] - crazy[0]) + abs(jongsu[1] - crazy[1])

for t in range(n):

    # TODO 1. 종수 움직임
    nx, ny = sx + dx[d_order[t]], sy + dy[d_order[t]]

    # TODO 2. 종수 움직였는데 그 칸이 미친 아두이누임
    if board[nx][ny] == "R":
        print(f"kraj {t+1}")
        exit()

    # TODO 3. 미친 아두이누 움직임
    toggle = False
    after_crazy_adu_moving = []
    for x, y in crazy_adu:
        # TODO 4. 미친 아두이누의 움직임 중에서 가장 가까운 거리를 찾아야 함.
        new_loc = [[-1, -1], int(1e9)]
        for i in range(8):
            ncx, ncy = x + cdx[i], y + cdy[i]
            if not (0 <= ncx < r and 0 <= ncy < c): continue

            d = mht([nx, ny], [ncx, ncy])
            if d < new_loc[1]:
                new_loc[0][0], new_loc[0][1] = ncx, ncy
                new_loc[1] = d
        # TODO 5. 가장 가깝게 되는 곳을 찾았고 그 곳이 현재 종수와의 움직임과 같은지 확인해야함.
        # 같다면, 멈춰야함.
        if new_loc[0][0] == nx and new_loc[0][1] == ny:
            toggle = True
            break

        after_crazy_adu_moving.append(tuple(new_loc[0]))
    
    # 확인 후 끝
    if toggle:
        print(f"kraj {t+1}")
        exit()
        
    # TODO 6. 미친 아두이노 2개 이상인지 체크
    crazy_adu_dic = {}
    for adu in after_crazy_adu_moving:
        if adu in crazy_adu_dic.keys():
            crazy_adu_dic[adu] += 1
        else:
            crazy_adu_dic[adu] = 1

    # TODO 7. 미친 아두이노 1개만 넣어줌
    crazy_adu.clear()
    for k, v in crazy_adu_dic.items():
        if v < 2: crazy_adu.append(list(k))

    # TODO 8. 새로 배열 만듬
    new_board = [["." for _ in range(c)] for _ in range(r)]
    new_board[nx][ny] = "I"

    # TODO 9. 아두이노 넣어주기
    for x, y in crazy_adu: new_board[x][y] = "R"
    
    # TODO 10. 새로운 정보로 업데이트
    sx, sy = nx, ny
    board = deepcopy(new_board)

for b in board:
    print(*b)