import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input())

graph = [[] for _ in range(n+1)]
s = [0 for _ in range(n+1)]
w = [0 for _ in range(n+1)]
for i in range(2, n+1):
    sw, number, to = map(str, input().split())

    if sw == "S": s[i] = int(number)
    else: w[i] = int(number)
    
    graph[int(to)].append(i)

answer = 0
def dfs(start):
    global answer

    number = 0
    for nxt in graph[start]:
        number += dfs(nxt)

    if w[start] != 0:
        return number - w[start] if number - w[start] > 0 else 0
    
    if s[start] != 0:
        return number + s[start]
    
    if start == 1:
        answer += number
        return

dfs(1)
print(answer)