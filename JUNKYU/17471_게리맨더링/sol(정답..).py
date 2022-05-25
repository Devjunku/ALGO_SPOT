import sys
input = sys.stdin.readline

n = int(input())
populations = list(map(int, input().split()))

graph = [[] for _ in range(n+1)]
for i in range(1, n+1):
    data = list(map(int, input().split()))
    for j in range(data[0]):
        graph[i].append(data[j+1])

def dfs(start, state):
    for nxt in graph[start]:
        if not visited[nxt] and (1 << nxt-1) & state:
            visited[nxt] = True
            dfs(nxt, state)

total_state = 1
for i in range(n):
    total_state = total_state | (1 << i)

answer = 1001
for i in range(1 << n):

    state1 = i
    state2 = i^total_state
    
    visited = [False for _ in range(n+1)]

    for j in range(n):
        if state1 & (1 << j) and not visited[j]:
            visited[j+1] = True
            dfs(j+1, state1)
            break
    
    for j in range(n):
        if state2 & (1 << j) and not visited[j]:
            visited[j+1] = True
            dfs(j+1, state2)
            break

    for j in range(1, n+1):
        if not visited[j]:
            break
    else:
        p1 = 0
        p2 = 0
        
        for k in range(n):
            if (1 << k) & state1:
                p1 += populations[k]
            else:
                p2 += populations[k]

        answer = min(answer, abs(p1-p2))

if answer == 1001:
    print(-1)
else:
    print(answer)