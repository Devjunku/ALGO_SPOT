from heapq import heappop, heappush
import sys
input = sys.stdin.readline

n = int(input())
arr = []
for i in range(1, n+1):
    dead_line, cup_ramen = map(int, input().split())
    arr.append((dead_line, cup_ramen))

arr.sort()

pq = []

for a in arr:
    heappush(pq, a[1])
    if a[0] < len(pq):
        heappop(pq)

print(sum(pq))