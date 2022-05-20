import sys
input = sys.stdin.readline

n, l, r, x = map(int, input().split())
level = list(map(int, input().split()))

cnt = 0
for i in range(1 << n):

    c = 0
    for j in range(n):
        if i & (1 << j):
            c += 1
    
    if c < 2: continue

    l_s = 0
    min_l = int(10e9)
    max_l = 0
    diff = 0
    for j in range(n):
        if i & (1 << j):
            l_s += level[j]
            min_l = min(min_l, level[j])
            max_l = max(max_l, level[j])
            diff = max_l - min_l
    
    if not (l <= l_s <= r):
        continue

    if not x <= diff:
        continue

    cnt += 1

print(cnt)