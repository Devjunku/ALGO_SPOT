import sys
input = sys.stdin.readline

n, l, r, x = map(int, input().split())
level = list(map(int, input().split()))

"""
따라서, 문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다.
또, 다양한 문제를 경험해보기 위해 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다.
"""

cnt = 0
for i in range(1 << n):
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
    
    # TODO  문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다.
    if not (l <= l_s <= r):
        continue

    if not x <= diff:
        continue

    cnt += 1

print(cnt)