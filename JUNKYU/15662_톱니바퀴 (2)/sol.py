from collections import deque
import sys

# 최대 재귀 깊이는 1000이지만, 조금만 더 늘려주자. (불안..)
sys.setrecursionlimit(2000)
input = sys.stdin.readline

t = int(input())
wheel = [deque(list(map(int, list(input().strip())))) for _ in range(t)]

# index 2와 6에서 확인한다.
# dfs로 현재 어떤 상태인지를 전달해야한다.
def dfs(number, d):

    prev = number - 1
    nxt = number + 1

    # number와 number - 1은 number의 6과 number-1의 2이다.
    if prev > -1 and not visited[prev]:
        if wheel[prev][2] != wheel[number][6]:
            visited[prev] = True
            dfs(prev, -d)
    
    # number와 number + 1은 number의 2과 number+1의 6이다.
    if nxt < t and not visited[nxt]:
        if wheel[nxt][6] != wheel[number][2]:
            visited[nxt] = True
            dfs(nxt, -d)
    
    # 다 전달되면 하나씩 돌려주자.
    wheel[number].rotate(d)

# 바퀴를 돌려준다.
k = int(input())
for _ in range(k):
    number, d = map(int, input().split())
    visited = [False for _ in range(t)]
    number -= 1
    visited[number] = True
    dfs(number, d)

# 개수를 세어준다.
cnt = 0
for w in wheel:
    if w[0] == 1:
        cnt += 1

print(cnt)