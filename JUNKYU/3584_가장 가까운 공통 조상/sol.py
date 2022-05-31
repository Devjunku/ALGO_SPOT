import sys
input = sys.stdin.readline

t= int(input())

for _ in range(t):
    n = int(input())
    parent = [0 for _ in range(n+1)]
    for _ in range(n-1):
        p, c = map(int, input().split())
        parent[c] = p

    a, b = map(int, input().split())

    a_parent, b_parent = [0, a], [0, b]

    while parent[a]:
        a_parent.append(parent[a])
        a = parent[a]

    while parent[b]:
        b_parent.append(parent[b])
        b = parent[b]
    
    i = 1
    while a_parent[-i] == b_parent[-i]:
        i += 1
    
    print(a_parent[-i + 1])