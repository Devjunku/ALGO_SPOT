from pprint import pprint

import sys
from heapq import heappop, heappush
input = sys.stdin.readline

n, m, l = map(int, input().split())
rest_area = list(map(int, input().split()))
rest_area.sort()
rest_area = [0] + rest_area + [l-1]
pprint(rest_area)
s_e_internal = []
for i in range(1, n+2):
    heappush(s_e_internal, [-(rest_area[i]-rest_area[i-1]), rest_area[i-1], rest_area[i]])

pprint(s_e_internal)

i = 0
while i < m:
    dist, s, e = heappop(s_e_internal)
    mid = (s + e) // 2
    
    print(s, e, -dist)

    heappush(s_e_internal, [-(mid-s), s, mid])
    heappush(s_e_internal, [-(e-mid), mid, e])
    i += 1

pprint(s_e_internal)

# 각 구간의 거리를 모두 구한 다음
# pq에 넣어서 중앙값을 기준으로 구간을 나눠서 이를 다시 pq에 넣어준다.
# pq 풀이는 실패..
