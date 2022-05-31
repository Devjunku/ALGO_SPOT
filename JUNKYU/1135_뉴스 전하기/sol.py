import sys
input = sys.stdin.readline

clerk_n = int(input())
graph = [[] for _ in range(clerk_n)]
real_graph = [[] for _ in range(clerk_n)]
child_node = [0 for _ in range(clerk_n)]
parent = list(map(int, input().split()))

# 시도때도 없이 탐색을 시도해야 한다.
# 트리의 깊이는 해당 트리의 깊이에서 몇번째로 탐색하는지 알아야 알 수 있다.
# 해당 노드가 직접 간접 포함 자식을 몇개 갖고 있는지 탐색한 후
# reverse 정렬을 진행한다.
for i in range(1, clerk_n):
    if parent[i] == -1: continue
    graph[parent[i]].append(i)

# 해당 노드가 갖고 있는 자식이 몇개인지 알아냄
def curcuit(start):

    number = 0
    for nxt in graph[start]:
        number += curcuit(nxt)

    child_node[start] = number

    if number == 0:
        return 1

    return number + 1

curcuit(0)

for depth in range(clerk_n):
    for node in graph[depth]:
        real_graph[depth].append((node, child_node[node]))

for i in range(clerk_n):
    real_graph[i].sort(key=lambda x : x[1], reverse=True)

answer = 0
def dfs(cnt, start):
    global answer

    answer = max(answer, cnt)

    number = len(real_graph[start])
    for i in range(number):
        nxt, count = real_graph[start][i]
        dfs(cnt + i + 1, nxt)

dfs(0, 0)
print(answer)