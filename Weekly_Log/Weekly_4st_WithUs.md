## 팀 구성원, 개인 별 역할

<br>

### 금주부터 진행 중인 **개인별 역할** <br>
이번주 개인별로 담당한 부분입니다.입니다. <br><br>

### 🙂권재순

- 역할 : CLOVA로 도출한 Keyword를 사용하여 순서도 출력
- 세부 작업 : CLOVA로 도출한 Keyword로 Flow와 FlowChart 엔티티를 통해 순서도를 도출해 
view에 출력한다.

### 🙂김동현

- 역할 : shadow 생성, 업데이트 
- 세부 작업 :  shadow 생성, 업데이트 기능 추가

### 🙂김예진

- 역할 : 홈페이지 view, shadow detail
- 세부 작업 : 홈페이지 전체 view 틀(nav,sidebar), shadow detail(flow list)

### 🙂박민준

- 역할 : CLOVA로 도출한 Keyword를 사용하여 순서도 출력
- 세부 작업 : 도출된 Keyword를 엔티티를 통해 테스트 샘플데이터 다 넣어보고 순서도 출력 목표

### 🙂이지원

- 역할 : Shadow 목록 
- 세부 작업 : Shadow 목록조회 기능 추가
  
<br>

<br>


### 다음주부터 진행 예정인 **개인별 역할** <br>
다음주 개인별로 담당할 부분입니다. <br><br>

### 🙂권재순

- 이번주 작업 이어서 진행

### 🙂김동현

- 역할 : shadow 연동된 챗봇에서 키워드에따른 답변
- 세부 작업 : 현재 위치에따른 순서도 하이라이트 + 답변 

### 🙂김예진


- 역할 : shadow 연동된 챗봇에서 키워드에따른 답변
- 세부 작업 : 현재 위치에따른 순서도 하이라이트 + 답변 

### 🙂박민준

- 이번주 작업 이어서 진행

### 🙂이지원

- 역할 : shadow 연동된 챗봇에서 키워드에따른 답변
- 세부 작업 : 현재 위치에따른 순서도 하이라이트 + 답변 

<br>

## 팀 내부 회의 진행 회차 및 일자


이번주 팀회의 진행 결과는 아래와 같습니다. 총 “**2회차**”의 회의에 **전원 모두** 참석하였습니다.

<details>
<summary> 15회차 (2022.08.22), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 2차, 1차 머지 진행(master), 2순위 작업 분할 </div>
</details> 
<details>
<summary> 16회차 (2022.08.25), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 3차, 문제점 분석 및 피드백, 다음 작업 일정 확인</div>
</details> 

<br>

## 현재까지 개발 과정 요약



이번주 개발 과정은 

1,2차 진행상황 공유 -> `3,4차 진행상황 공유 및 merge`

### 
으로 진행 중에 있습니다.


아래는 현재까지의 과정에서 기술적으로 새로 알게된 점과, 어려웠던 점 아쉬웠던 점입니다.  

<br>

### 🙂기술적으로 새로 알게된 점
- js에서 동적으로 태그를 생성하고 삭제하는 방식에 대해 이해해볼 수 있었습니다.
- ajax로 받은 데이터를 view에 적용하는 방법을 알게되었습니다.(ResultEntity 로 받은 값을 전달)

### 😥어려웠던 점
- 키워드를 통해 순서도를 도출할 때 DB구조를 이해하기에 어려움이 있었습니다.
- 어떠한 값을 통해 원하는 데이터를 가져올지 고민이 많았습니다
- javascript의 로딩 순서와, bootstrap, jquery의 버전별 충돌여부 등으로 인해, UI 설정에 어려움을 겪었습니다.

### 😥아쉬운 점
- 엔티티를 구성할 때 관계에 대한 내용을 더 공부할걸 이란 아쉬움이 큽니다.
- 설계가 잘 되었다고 생각 했지만 개발을 하다보니 부족한 부분이 많았다는 것을 알 수있었습니다.(제약조건, 관계 등)
- 데이터를 처리할 때 Repository로 바로 접근하기 보다 Service를 작성해서 처리하는 것이 좋다는 점을 미리 생각하지 못한점이 아쉬웠습니다.( 알려주신 예진님 감사드려요 ! )
- 회원가입 처리시 적절하지 않은 로직으로 구성하여, 회원가입에 에러가 발생한부분에서 ajax에 대한 부분이 공부가 부족해 아쉬웠습니다.


<br>



## 개발 과정에서 나왔던 질문


### 🤔회의 별 질문 및 내용

이번주 2회차의 회의 중에 나왔던 질문, 회의내용 모음입니다.  
매 회의마다 많은 질문이 오고가고, 서로의 의견을 취합하여 다음 과정을 밟아가고 있습니다.
<br>


### Q1. 3차 진행상황 공유 내용 
<details>
<summary> 세부 내용 </summary>
<div>  

#### 지난주 작업 결과
1. 예진 : 금주까지 회원정보 수정, 로그인 UI 수정 - 완료
2. 재순, 민준 : DB 캐싱 추가 - 완료
3. 동현, 지원 : ajax 수정 - 완료 , 모든 서비스에 플로팅 - 추후 installation과 함께 작업

#### 다음 역할 분담
1. 재순, 민준 : 서버 - DB - 서버 - 클로바 -서버 - DB 기능 
2. 예진 ,동현, 지원 : shadow CRUD 구현

</div>
</details> 

### Q2. 1차 머지
<details>
<summary> 세부 내용 </summary>
<div>  

1. db 접속 정보를 위한 application-mysql.properties 따로 만들기  <br>

   각자의 프로젝트에서 master 또는 feature-signup, feature-web 을 pull 받을 때에는
`src/main/resource/application-mysql.properties` 파일을 아래와 같이 추가한 후,   각자의 mysql 세팅을으로 변경하여 작업하시면 됩니다.
   수정된 `application.properties, .gitignore` 
   feature-chat과 merge할때는 위 부분이 추가되었으니, 추후 머지할때 참고하면 될것 같습니다.

2. build.gradle 정리 <br>
   추후 master와 feature-chat 과 충돌을 피하기 위해서 `build.gradle`을  동현님이 수정해주신 `feature-chat`의 형태로 맞추었습니다.

   spring security와 lombok annotaionProcessor, spring validation 에 대한 dependencies 들이 추가되었고, 그부분은 추후 merge할때 참고하면 될 것 같습니다.
``
</div>
</details> 

### Q2. 4차 진행상황 공유 내용
<details>
<summary> 세부 내용 내용 </summary>
<div>

1. 민준, 재순 : 엔티티 생성 및 DB에서 spring으로 데이터 전달
   
2. 예진, 동현, 지원 : 홈페이지 전체적인 view 생성, 기본 enttity 및 create 부분 완료
   
</div>
</details>

<br>

### 🤔해결되지 않은 질문 리스트

이번주 어려웠던 점에 대해 강사님 피드백과 팀원들간의 협의를 통해 1차 해결책을 얻은 부분입니다.


### Q3. flowchart 테이블 미생성 - 강사님 피드백
<details>
<summary> 세부 내용 </summary>
<div>

#### 현상
Entity 필드 이름중 예약어로 인한 테이블 미생성 오류

#### 해결
Flowchart 테이블의 필드이름을 `order -> seq` 로 변경
<br>
[https://github.com/likelion-backendschool/Withus/commit/793285dbbfe7aa986b6612013ccf8268ce3d0c4c](https://github.com/likelion-backendschool/Withus/commit/793285dbbfe7aa986b6612013ccf8268ce3d0c4c)

</div>
</details>

### Q4.연관된 엔티티 한번에 json으로 받기
<details>
<summary> 세부 내용 </summary>
<div>

#### 현상
JSON으로 데이터를 받을 시, list 데이터가 잘못된 형식으로 들어옴

#### 해결 과정
팀원들의 방안 참고  
[https://github.com/likelion-backendschool/Withus/commits/feature-web-view-create](https://github.com/likelion-backendschool/Withus/commits/feature-web-view-create)
[https://github.com/likelion-backendschool/Withus/commits/feature-web-view-create-yejin](https://github.com/likelion-backendschool/Withus/commits/feature-web-view-create-yejin)


</div>
</details>


### Q5. DB 구조 이해하기
<details>
<summary> 세부 내용 </summary>
<div>

샘플 데이터 생성하여 이해해보기  
[샘플 데이터 생성용 sql](https://bold-cardigan-771.notion.site/sql-95959a84e8484f8f9d0924db7d3713c5)  
[샘플 데이터 확인용 엑셀](https://docs.google.com/spreadsheets/d/1OCrIUnf_JQt7pkoYsxs7ozqrSPUEorzPOvI4Ev1tc20/edit#gid=1260904759)


</div>
</details>


<br>


<br>



<br>

## 개발 결과물 공유

이번주는 설계도에서 수정된 부분은 없으며, 전체적인 개발 진행 중으로, Github 결과물을 공유드립니다.

### 😀결과물

#### Github Repository URL

[https://github.com/likelion-backendschool/Withus](https://github.com/likelion-backendschool/Withus)


<br>

### 😀WBS
<br>

#### WBS 엑셀
[https://docs.google.com/spreadsheets/d/1mbyUlXgf4MniXAPvGZXqFZ4QALEV3PRuGO6nmig7BFU/edit#gid=0](https://docs.google.com/spreadsheets/d/1mbyUlXgf4MniXAPvGZXqFZ4QALEV3PRuGO6nmig7BFU/edit#gid=0)

<br>

#### 로드맵/진행상황 노션
[https://bold-cardigan-771.notion.site/758df6c483da4c4fb676910e725dca32?v=21e9cd36b03f4558af878e8cc8ec8ff4](https://bold-cardigan-771.notion.site/758df6c483da4c4fb676910e725dca32?v=21e9cd36b03f4558af878e8cc8ec8ff4)

<br>   

### 😀With Us 팀원들의 모습

![4주차 With us 팀원들의 모습](https://i.imgur.com/6uKH1ra.jpg)
