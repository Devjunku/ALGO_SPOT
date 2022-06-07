import sys
input = sys.stdin.readline

n, m = map(int, input().split())

dp = [[-1 for _ in range(1 << 14)] for _ in range(14**2)]

# 현재 상태에서 갈 수 있는 상태가 n, m으로 인해 다르다. 따라서 이를 모두 고려하면서 dfs를 돌려야 한다.

def dfs(number, state):

    # stop rule
    if number == n * m and state == 0: return 1
    if number >= n * m: return 0

    if dp[number][state] != -1: return dp[number][state]

    # 첫 시작은 무조건 0임
    dp[number][state] = 0

    # 1임?
    if state & 1:
        dp[number][state] = dfs(number + 1, state >> 1)
    # 아님? → 0?
    else:
        dp[number][state] = dfs(number + 1, state >> 1 | 1 << (m-1))
        
        if number % m != m - 1 and state & 2 == 0:
            dp[number][state] += dfs(number + 2, state >> 2)
    
    dp[number][state] %= 9901
    return dp[number][state]

print(dfs(0, 0))