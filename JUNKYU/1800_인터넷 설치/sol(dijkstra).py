from heapq import heappop, heappush
import sys
input = sys.stdin.readline

n, p, k = map(int, input().split())
graph = [[] for _ in range(n+1)]

start = 0
end = 100000001
for _ in range(p):
    s, e, c = map(int, input().split())
    graph[s].append((e, c))
    graph[e].append((s, c))


def dijkstra(start):

    INF = 10001
    dist = [INF] * (n+1)
    dist[start] = 0

    pq = []
    heappush(pq, (0, start))

    while pq:
        cost, now = heappop(pq)

        if cost > dist[now]: continue

        for nxt, c in graph[now]:
            if c > mid:
                if dist[nxt] > 1 + cost:
                    dist[nxt] = 1 + cost
                    heappush(pq, (1 + cost, nxt))
            else:
                if dist[nxt] > cost:
                    dist[nxt] = cost
                    heappush(pq, (cost, nxt))

    return dist

answer = 100000001
while start <= end:
    
    mid = (start + end) // 2
    result = dijkstra(1)

    if result[n] > k:
        start = mid + 1
    elif result[n] <= k:
        answer = mid
        end = mid - 1

if answer == 100000001:
    print(-1)
else:
    print(answer)