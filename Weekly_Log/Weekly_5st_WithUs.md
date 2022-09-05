## 팀 구성원, 개인 별 역할

<br>




### 금주부터 진행 중인 **개인별 역할** <br>

이번주 개인별로 담당한 부분입니다.입니다. <br><br>

### 🙂권재순

- 역할 : Email 전송 기능 추가, 전체 디자인
- 세부 작업 : Email을 원하는 사용자에게 전송할 수 있는 기능 추가, 기존 view를 템플릿으로 변경작업

### 🙂김동현

- 역할 : create, update view 디자인 교체, chatbot view 디자인 교체
- 세부 작업 : create, update view와 chatbot view를 템플릿을 적용시키는 작업 수행

### 🙂김예진

- 역할 : keyword에 따른 전체 순서도 제공
- 세부 작업 :  DB 또는 API 로부터 얻은 keyword -> 시나리오 대로 버튼 출력 

### 🙂박민준

- 역할 : keyword에 따른 전체 순서도 or 링크 제공
- 세부 작업 : DB 또는 API 로부터 얻은 keyword -> 시나리오 대로 버튼 출력
### 🙂이지원

- 역할 : Email 전송 기능 추가, 전체 디자인
- 세부 작업 : Email을 원하는 사용자에게 전송할 수 있는 기능 추가, 기존 view를 템플릿으로 변경작업

<br>




### 다음주부터 진행 예정인 **개인별 역할** <br>
다음주 개인별로 담당할 부분입니다. <br><br>

### 🙂권재순

- 역할 : 전체 디자인
- 세부 작업 : 기존 view를 템플릿으로 변경작업, docs / tutorial 페이지 생성

### 🙂김동현

- 역할 : create, update view 디자인 교체, chatbot view 디자인 교체 + 배포 준비
- 세부 작업 : create, update view와 chatbot view를 템플릿을 적용시키는 작업 수행 

### 🙂김예진


- 역할 : 안내 버튼 - 시나리오형
- 세부 작업 :  DB 또는 API 로부터 얻은 keyword -> 시나리오 대로 버튼 출력

### 🙂박민준

- 역할 : 현재 페이지에 따른, text 하이라이트
- 세부 작업 : keword를 통해 나온 순서도가 현제 페이지에 따라 하이라이트 되는 기능

### 🙂이지원

- 역할 : 전체 디자인
- 세부 작업 :  기존 view를 템플릿으로 변경작업, docs / tutorial 페이지 생성

<br>


## 팀 내부 회의 진행 회차 및 일자


이번주 팀회의 진행 결과는 아래와 같습니다. 총 “**2.5회차**”의 회의에 **전원 모두** 참석하였습니다.

<details>
<summary> 17회차 (2022.08.29), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 4차,  </div>
</details> 
<details>
<summary> 17.5회차 (2022.08.31), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  PR 도입 설명, 버그 수정</div>
</details> 

<details>
<summary> 18회차 (2022.09.01), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 5차, 멘토님 중간 점검 2차</div>
</details> 

<br>



<br>

## 개발 과정에서 나왔던 질문


### 🤔회의 별 질문 및 내용

이번주 3회차의 회의 중에 나왔던 질문, 회의내용 모음입니다.  
매 회의마다 많은 질문이 오고가고, 서로의 의견을 취합하여 다음 과정을 밟아가고 있습니다.
<br>


### Q1. 4차,5차 개발과정 공유
<details>
<summary> 세부 내용 </summary>
<div>  

#### 지난주 작업 결과
1. 재순, 민준 : 서버 - DB - 서버 - 클로바 -서버 - DB 기능 - 완료, keyword 버그 수정
2. 예진,동현, 지원 : shadow CRUD 구현 - 완료, update 버그 수정, delte 버그 수정 필요

#### 다음 역할 분담
1. 재순, 지원 : 템플릿 찾기, docs,contact 구현
2. 동현 : 링크형 안내 버튼 구현
3. 예진, 민준 : 시나리오형 안내 버튼 구현

</div>
</details> 


### Q2. 2순위 기능 개발 머지 및 Pull Request 도입
<details>
<summary> 세부 내용 내용 </summary>
<div>

2순위 기능개발 머지 부터는 PR 을 도입하여 PR을 이용한 이슈관리를 진행하였습니다.
<br>

1. 순서도 CRUD 구현 부분 전체 web페이지 (feature-web-view)에 머지
   
   [https://github.com/likelion-backendschool/Withus/pull/6](https://github.com/likelion-backendschool/Withus/pull/6)

<br>

2. clova 연동 부분 ajax chat 구현 부분(feature-chat)에 머지
   
   [https://github.com/likelion-backendschool/Withus/pull/5](https://github.com/likelion-backendschool/Withus/pull/5)

<br>

</div>
</details>

<br>

### 🤔해결되지 않은 질문 리스트

이번주 어려웠던 점에 대해 멘토님의 피드백과 팀원들간의 협의를 통해 1차 해결책을 얻은 부분입니다.


### Q3. entity 구성에 대한 어려움
<details>
<summary> 세부 내용 </summary>
<div>

#### 어려움 : 중복값에 대한 처리

서로 연관관계가 있는 table을 한번에 수정, 삭제하는 로직이라서 중복값에 대한 처리에 어려움을 겪었습니다.

#### 고민 1) manytoone 으로 이루어진 flowchart entity의 필요성

현재 db 의 구성이 옳게 된 것인지에 대한 의문

[https://github.com/likelion-backendschool/Withus/tree/feature-web-view/src/main/java/com/example/shadow/domain/shadow/entity](https://github.com/likelion-backendschool/Withus/tree/feature-web-view/src/main/java/com/example/shadow/domain/shadow/entity)
<br>

#### 고민 2) create 시 flow가 중복될 때, flow.name : uniq=true

중복된 flow 을 create 할때, 500 발생 : flow name -> uniq=true

[https://github.com/likelion-backendschool/Withus/pull/7](https://github.com/likelion-backendschool/Withus/pull/7)
<br>

#### 고민 3) update 시 flow가 중복될 때, lazy vs eager

update 시, flow 가 중복될때 버그 수정 (eager, flow_id 추가)

[https://github.com/likelion-backendschool/Withus/pull/10](https://github.com/likelion-backendschool/Withus/pull/10)

<br>
</div>
</details>

### Q4. 멘토님 중간점검 - 진행상황 피드백
<details>
<summary> 세부 내용 </summary>
<div>

#### 중간점검 - 프로젝트 진척도

[https://bold-cardigan-771.notion.site/2-a44e0fc17fc14576ae9948f2bd217eea](https://bold-cardigan-771.notion.site/2-a44e0fc17fc14576ae9948f2bd217eea)

<br>

#### 멘토님 피드백

전체적으로 잘 진행되고 있으며, 앞으로도 이대로 진행하면 완성가능하다고 봅니다.
기간내에 기능개발이 완료된다면, 어떻게 진행할 예정인지?  

-> 4순위 추가 기능으로 적어놓은 부분들을 진행할 것 으로 보이는데, 배포 작업에 어려움이 있을 것으로 예상되어 4순위는 못할 것으로 예상됩니다.

</div>
</details>





<br>

## 개발 결과물 공유

이번주는 전체적인 개발 진행 중으로 WBS 변동사항은 없으며 Github 결과물을 공유드립니다.

### 😀결과물

#### Github Repository URL

[https://github.com/likelion-backendschool/Withus](https://github.com/likelion-backendschool/Withus)


<br>

### 😀WBS

#### WBS 엑셀
[https://docs.google.com/spreadsheets/d/1mbyUlXgf4MniXAPvGZXqFZ4QALEV3PRuGO6nmig7BFU/edit#gid=0](https://docs.google.com/spreadsheets/d/1mbyUlXgf4MniXAPvGZXqFZ4QALEV3PRuGO6nmig7BFU/edit#gid=0)

<br>

#### 로드맵/진행상황 노션
[https://bold-cardigan-771.notion.site/758df6c483da4c4fb676910e725dca32?v=21e9cd36b03f4558af878e8cc8ec8ff4](https://bold-cardigan-771.notion.site/758df6c483da4c4fb676910e725dca32?v=21e9cd36b03f4558af878e8cc8ec8ff4)

<br>   

### 😀With Us 팀원들의 모습

![5주차 With us 팀원들의 모습](https://i.imgur.com/02BzvjH.jpg)
