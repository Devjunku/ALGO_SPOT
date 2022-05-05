# ALGO SPOT
#### 부제 : Algorithm Study
---


## ❗올라갈⬆ 일만 남았다!! 왜냐하면! 내려갈⬇ 곳이 없기 때문에 ..

### Study Rule
- 주중: 선택된 1일 알고리즘 문제를 풉니다.
- Branch명은 algorithm 문제 명으로 올려주세요.
- 상대가 수락해야 merge 가능합니다.

## 폴더구조
- 이름(영문)

## Commit Rule
- 문제 사이트: 문제번호_문제명
```
Ex)

11234_BOJ
2341_Programmers
3456_SWEA
```

## PR Form &  Rule
PR title : `문제번호_문제이름`
```
## 아래 양식을 이용해서 문제의 대강을 작성해주세요.

- **문제 사이트** : 
  - [ ] 백준
  - [ ] Programmers
  - [ ] SWEA

- **난이도**:
  - 

- **사용한 알고리즘**
  - 

- **어려웠던 점**:
  - 

- **Reference** :
  - 

- **etc**:
  - 
```

## 협업 방법

### Fork 이후 로컬에 클론 및 원격 저장소 가져오기

**Fork = 레포지토리를 복사해서 그대로 가져온다.**

```
$ git clone

$ git remote add upstream [프로젝트 url]
```

### 브랜치 만들기

```
$ git branch -b [브랜치 이름]
```

### 작업하기

```
$ git add .

$ git commit ="문제번호: 문제이름"

$ git push origin [브랜치 이름]
```



### fork한 레포에 가기

**PR 생성하기**



> 만약 그 브랜치 내 코드에서 수정할 내용이 있다면? 

> 그 브랜치에서 코드를 수정하고 커밋하고 푸쉬하면 PR에 자동으로 등록됨

**Review!!!**
**PR Merge 이후 과정**

### 중앙 저장소에 마스터 브랜치에서 New code 가져오기



```
$ git checkout master

$ git fetch upstream master

$ git merge upstream/master
```

or

```
$ git checkout master

$ git pull upstream master
```


**여기서 git pull upstream master 써도 된다.**

fetch, pull은 둘 다 코드를 가져오는 개념이지만 pull은 내 코드에 가져온 코드를 병합까지 해주는 애.

Pull = fetch + pull




### 로컬 및 원격에서 브랜치 삭제해주기
-> 마스터브랜치에서 하세요!

**로컬**

```
$ git branch -d [브랜치이름]
```

**원격**
```
$ git push origin :[브랜치이름]
```



### 자기 레포지토리에 중앙저장소 코드 합치기
-> 완성본은 언제나 마스터 브랜치에 있다는거!!

```
$ git push origin master
```





