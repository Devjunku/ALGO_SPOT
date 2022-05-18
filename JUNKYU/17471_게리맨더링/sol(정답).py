import sys
input = sys.stdin.readline

n = int(input())
population = [0] + list(map(int,input().split()))
G = {}
for i in range(1, n+1):
    G[i] = list(map(int,input().split()))[1:]

def powerset(s):
    masks = [1 << i for i in range(len(s))]
    for i in range(1 << len(s)):
        yield [ss for mask, ss in zip(masks, s) if mask&i], [ss for mask, ss in zip(masks, s) if mask & (-i-1)]

def bfs(start, other_p):
    check = {i: 1 for i in other_p}
    check[start] = 1
    q = [start]
    while q:
        nxt = []
        for v in q:
            for u in G[v]:
                if u not in check:
                    check[u] = 1
                    nxt.append(u)
        q = nxt
    
    if len(check) == len(G):
        return 1
    else:
        return 0

sol = []

for p1, p2 in powerset(list(G.keys())):
    if p1 and p2:
        s1, s2 = 0, 0
        if bfs(p1[0], p2) and bfs(p2[0], p1):
            for i in p1:
                s1 += population[i]
            for i in p2:
                s2 += population[i]
            
            sol.append(abs(s1-s2))

if sol:
    print(min(sol))
else:
    print(-1)