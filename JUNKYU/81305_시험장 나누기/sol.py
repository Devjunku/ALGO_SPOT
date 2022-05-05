import sys
sys.setrecursionlimit(10**6)

l = [0 for _ in range(10005)]
r = [0 for _ in range(10005)]
x = [0 for _ in range(10005)]
p = [-1 for _ in range(10005)]
n = 0
root = 0
cnt = 0


def dfs(cur, lim):
    global cnt

    lv = 0
    if l[cur] != -1: lv = dfs(l[cur], lim)
    rv = 0
    if r[cur] != -1: rv = dfs(r[cur], lim)

    if x[cur] + lv + rv <= lim:
        return x[cur] + lv + rv
    
    if x[cur] + min(rv, lv) <= lim:
        cnt += 1
        return x[cur] + min(rv, lv)
    
    cnt += 2
    return x[cur]


def solve(lim):
    global cnt
    cnt = 0
    dfs(root, lim)
    cnt += 1
    return cnt


def solution(k, num, links):
    global root

    n = len(num)

    for i in range(n):
        l[i], r[i] = links[i]
        x[i] = num[i]
        if l[i] != -1: p[l[i]] = i
        if r[i] != -1: p[r[i]] = i
    
    for i in range(n):
        if p[i] == -1:
            root = i
            break
    
    start = max(x)
    end = int(10**8)

    while start < end:
        mid = (start + end) // 2
        if solve(mid) <= k:
            end = mid
        else:
            start = mid + 1
        
    return start


if __name__ == "__main__":
    l = [0] * 10005 # 왼쪽 노드
    r = [0] * 10005 # 오른쪽 노드
    x = [0] * 10005 # 시험장의 응시 인원
    p = [-1] * 10005 # 부모 노드의 번호
    n = 0 # 노드의 수
    root = 0 # 루트

    cnt = 0 # 그룹의 수
    print(solution(3, [12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1], [[-1, -1], [-1, -1], [-1, -1], [-1, -1], [8, 5], [2, 10], [3, 0], [6, 1], [11, -1], [7, 4], [-1, -1], [-1, -1]]))
    l = [0] * 10005 # 왼쪽 노드
    r = [0] * 10005 # 오른쪽 노드
    x = [0] * 10005 # 시험장의 응시 인원
    p = [-1] * 10005 # 부모 노드의 번호
    n = 0 # 노드의 수
    root = 0 # 루트

    cnt = 0 # 그룹의 수
    # 40
    print(solution(1, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]]))
    l = [0] * 10005 # 왼쪽 노드
    r = [0] * 10005 # 오른쪽 노드
    x = [0] * 10005 # 시험장의 응시 인원
    p = [-1] * 10005 # 부모 노드의 번호
    n = 0 # 노드의 수
    root = 0 # 루트

    cnt = 0 # 그룹의 수
    # 27
    print(solution(2, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]]))
    l = [0] * 10005 # 왼쪽 노드
    r = [0] * 10005 # 오른쪽 노드
    x = [0] * 10005 # 시험장의 응시 인원
    p = [-1] * 10005 # 부모 노드의 번호
    n = 0 # 노드의 수
    root = 0 # 루트

    cnt = 0 # 그룹의 수
    # 14
    print(solution(4, [6, 9, 7, 5], [[-1, -1], [-1, -1], [-1, 0], [2, 1]]))
    # 9