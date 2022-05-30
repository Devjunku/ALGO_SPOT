import sys
input = sys.stdin.readline

n = int(input())
n_arr = list(map(int, input().split()))
m = int(input())
dp = [[0 for _ in range(n)] for _ in range(n)]

def dp_c(s, e):

    if s == e: return 1
        
    elif n_arr[s] == n_arr[e]:
        
            if s + 1 == e: return 1
            elif dp[s+1][e-1] == 1: return 1
    
    return 0

for l in range(n):
    for s in range(n - l):
        e = s + l
        if s > e: continue
        dp[s][e] = dp_c(s, e)        

for _ in range(m):
    s, e = map(int, input().split())
    print(dp[s-1][e-1])