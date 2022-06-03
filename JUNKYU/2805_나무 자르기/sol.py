import sys
input = sys.stdin.readline

n, m = map(int, input().split())
trees = list(map(int, input().split()))

start = 0
end = 2000000000

while (start <= end):
    mid = (start + end) // 2

    length = 0
    for tree in trees:
        if tree > mid:
            length += tree - mid
    
    if length >= m:
        start = mid + 1
    elif length < m:
        end = mid - 1

print(end)