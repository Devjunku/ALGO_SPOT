import sys
input = sys.stdin.readline


def sol1029():
    n = int(input())
    g = [[*map(int, list(input().rstrip()))] for _ in range(n)]
    bit = [1 << i for i in range(n+1)]

    q = {(0, 1, 0)}
    answer = 0

    while q:

        nq = set()
        answer += 1

        for cur, state, price in q:
            for nxt in range(n):
                nstate, nprice = state | bit[nxt], g[cur][nxt]

                if state == nstate or nprice < price: continue
                
                nq.add((nxt, nstate, nprice))
        q = nq

    return answer

print(sol1029())
    