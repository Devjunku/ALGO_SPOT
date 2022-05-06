import sys
input = sys.stdin.readline

n = int(input())

works = []
for _ in range(n):
    d, w = map(int, input().split())
    works.append((d, w))

works.sort()
todo = []
date = works[-1][0]
answer = 0

while True:
    if date == 0:
        break

    while works and works[-1][0] >= date:
        todo.append(works.pop()[1])
    
    date -= 1

    if not todo:
        continue

    todo.sort()
    answer += todo.pop()

print(answer)

