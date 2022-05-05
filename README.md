# 서울 4반 알고 가자!!(어딜요..?)
#### 부제 : Algorithm Study
---


## ❗올라갈⬆ 일만 남았다!! 왜냐하면! 내려갈⬇ 곳이 없기 때문에 ..

### Study Rule
- 주중: 하루 1일 알고리즘을 개인 실력에 따라서 풉니다.
- 주말: 다른 PR을 살펴보며 리뷰를 남깁니다.
- PR은 모든 문제가 아닌, 리뷰를 받고싶은 문제만 남깁니다.
- 월요일 첫 커밋 전에 PR 날린 당사자가 merge합니다.

## 폴더구조
- 이름(영문) / 사이트 / 문제번호

## Commit Rule
- 문제 사이트: 문제번호
```
Ex)

BOJ: 11234
Programmers: 2341
SWEA: 3456
```

## PR Form &  Rule
PR title : `문제번호 : 문제이름`
```
## 문제번호 :  문제이름

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





