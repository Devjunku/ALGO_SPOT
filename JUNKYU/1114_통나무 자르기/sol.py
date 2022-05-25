import sys
input = sys.stdin.readline

l, k, c = map(int, input().split())
cuts = [0, l] + list(map(int, input().split()))
cuts.sort()

start = 0
end = l
answer = 0
first_cut = 0

def cut_tree(mid):
    before = []
    cnt = 0
    cut_start = l
    first = 0

    for i in range(len(cuts)-1, -1, -1):
        length = cuts[i] - cuts[i-1]
        total = cut_start - cuts[i]
        if length > mid:
            return 10001, 0
        elif total > mid:
            cnt += 1
            cut_start = cuts[i+1]
            before.append(cuts[i+1])
    
    if cnt < c:
        first = cuts[1]
    else:
        first = before[-1]
    
    return cnt, first


while start <= end:
    mid = (start + end) // 2
    
    cnt, first = cut_tree(mid)

    if c < cnt:
        start = mid + 1
    else:
        answer = mid
        first_cut = first
        end = mid - 1

print(*[answer, first_cut])