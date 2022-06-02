import sys
input = sys.stdin.readline

def update(x, y, val):
    while x <= n:
        pos = y
        while pos <= n:
            BIT[x][pos] += val
            pos += (pos & -pos)
        
        x += (x & -x)


def search(x, y):
    ret = 0
    while x > 0:
        pos = y

        while pos > 0:
            ret += BIT[x][pos]
            pos -= (pos & -pos)
        
        x -= (x & -x)
        
    return ret


n, m = map(int, input().split())

BIT = [[0 for _ in range(n+1)] for _ in range(n+1)]

temp = []

for i in range(n):
    temp.append(list(map(int, input().split())))

for i in range(1, n+1):
    for j in range(1, n+1):
        update(i, j, temp[i-1][j-1])

for i in range(m):
    op = list(map(int, input().split()))

    if op[0] == 0:
        x1, y1, c = op[1], op[2], op[3]
        update(x1, y1, c - temp[x1-1][y1-1])
        temp[x1-1][y1-1] = c
    
    elif op[0] == 1:
        x1, y1, x2, y2 = op[1], op[2], op[3], op[4]
        ret = search(x2, y2) - search(x2, y1-1) - search(x1-1, y2) + search(x1-1, y1-1)
        # print(ret)
