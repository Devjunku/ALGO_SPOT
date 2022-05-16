# 단순 dfs면 시간 초과뜸
import sys
input = sys.stdin.readline

n, p, k = map(int, input().split())
graph = [[] for _ in range(n+1)]
visited = [False for _ in range(n+1)]
visited[1] = True
start = 1000000
end = 0

for _ in range(p):
    s, e, c = map(int, input().split())
    graph[s].append((e, c))
    graph[e].append((s, c))
    start = min(start, c)
    end = max(end, c)


def dfs(c_list, node, cnt):
    global result

    if node == n:
        # TODO 여기서 cnt와 k를 비교해서 answer를 update를 시켜야함.
        # 구체적으로 cnt가 k와 같다면, c_list를 sort(reverse=True)로 만들어서
        # k번째 값을 min으로 저장해주면 됨
        if cnt == k:
            result = min(result, sorted(c_list, reverse=True)[k])
        return
    
    for nxt, cost in graph[node]:
        if not visited[nxt]:
            visited[nxt] = True
            c_list.append(cost)
            if mid <= cost:
                dfs(c_list, nxt, cnt + 1)
            else:
                dfs(c_list, nxt, cnt)
            c_list.pop()
            visited[nxt] = False


ans = 10000001
while start <= end:
    mid = (start+end) // 2

    result = 1000000
    dfs([], 1, 0)
    if result <= mid:
        ans = result
        start = mid + 1
    else:
        end = mid - 1

if ans == 10000001: print(-1)
else: print(ans)