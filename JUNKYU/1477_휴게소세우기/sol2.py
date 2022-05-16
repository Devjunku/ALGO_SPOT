import sys
input = sys.stdin.readline

n, m, l = map(int, input().split())
stations = [0] + list(map(int, input().split())) + [l]
stations.sort()

left, right, answer = 1, l - 1, 0
while left <= right:
    count = 0
    mid = (left + right) // 2
    for i in range(1, len(stations)):
        if stations[i] - stations[i-1] > mid:
            count += (stations[i] - stations[i-1] - 1) // mid
    
    if count > m:
        left = mid + 1
    else:
        right = mid - 1
        answer = mid

print(answer)