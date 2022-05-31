import sys
input = sys.stdin.readline

clerk_n = int(input())
parent = list(map(int, input().split()))
graph = [[] for _ in range(clerk_n)]

for i in range(1, clerk_n):
    if parent[i] == -1: continue
    graph[parent[i]].append(i)

time = [0 for _ in range(clerk_n)]

def dfs(now):

    child = []

    for nxt in graph[now]:
        dfs(nxt)
        child.append(time[nxt])
    
    if not graph[now]:
        child.append(0)
    
    child.sort(reverse = True)

    need = [child[i] + i + 1 for i in range(len(child))]
    time[now] = max(need)

dfs(0)
print(time[0]-1)
