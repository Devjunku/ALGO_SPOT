import sys
input = sys.stdin.readline

n, m = map(int, input().split())

alpha = {
    "a": 1,
    "b": 2,
    "c": 3,
    "d": 4,
    "e": 5,
    "f": 6,
    "g": 7,
    "h": 8,
    "i": 9,
    "j": 10,
    "k": 11,
    "l": 12,
    "m": 13,
    "n": 14,
    "o": 15,
    "p": 16,
    "q": 17,
    "r": 18,
    "s": 19,
    "t": 20,
    "u": 21,
    "v": 22,
    "w": 23,
    "x": 24,
    "y": 25,
    "z": 26
}


init = 1
for i in range(26):
    init |= 1 << i

words = []
for _ in range(n):
    word = input().strip()
    bb = 1
    for w in word:
        bb |= 1 << alpha[w]
    words.append(bb)

for _ in range(m):
    toggle, alpha_info = map(str, input().split())

    cnt = 0
    if toggle == "1":
        init &= ~(1 << alpha[alpha_info])
    else:
        init |= (1 << alpha[alpha_info])
    
    for word in words:
        if word & init == word:
            cnt += 1

    print(cnt)