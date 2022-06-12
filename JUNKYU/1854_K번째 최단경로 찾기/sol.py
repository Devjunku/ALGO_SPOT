from heapq import heappop, heappush
import sys
input = sys.stdin.readline

INF = sys.maxsize
n, m, k = map(int, input().split())
graph = [[] for _ in range(n+1)]

for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))


def dijkstra(start):
    
    distance = [[INF for _ in range(k)] for _ in range(n+1)]
    distance[1][0] = 0

    pq = []
    heappush(pq, (0, start))

    while pq:
        dist, node = heappop(pq)

        for nxt, cost in graph[node]:
            n_dist = dist + cost

            if distance[nxt][k-1] <= n_dist : continue

            distance[nxt][k-1] = n_dist
            distance[nxt].sort()
            heappush(pq, (n_dist, nxt))

    return distance

answer = dijkstra(1)
for i in range(1, n+1):
    print(answer[i][k-1]) if answer[i][k-1] != INF else print(-1)