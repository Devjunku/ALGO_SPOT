import sys
input = sys.stdin.readline

d, n, m = map(int, input().split())
arr = [int(input()) for _ in range(n)]
arr.sort()
arr = [0] + arr + [d]

start = 0
end = 1000000000
answer = 0

while start <= end:
    mid = (start + end) // 2

    # 제거한 돌의 개수
    cnt = 0
    prev = 0
    nxt = 1
    while nxt < n + 2:
        if arr[nxt] - arr[prev] < mid:
            cnt += 1
            nxt += 1
        else:
            prev = nxt
            nxt += 1

    if cnt <= m:
        start = mid + 1
        answer = mid
    elif cnt > m:
        end = mid - 1

print(answer)