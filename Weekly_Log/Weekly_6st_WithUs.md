## 팀 구성원, 개인 별 역할

<br>


### 금주부터 진행 중인 **개인별 역할** <br>
이번주 개인별로 담당한 부분입니다.입니다. <br><br>

### 🙂권재순

- 역할 : 전체 디자인 수정 및 Tutorial 내용 채우기
- 세부 작업 : Page 이름 변경, sidebar, layout 오류 수정, Tutorial 새로운 내용 추가

### 🙂김동현

- 역할 : js를 통해 외부 html을 가져오기, shadow 생성, 수정 페이지 에러 해결
- 세부 작업 : 스크립트로 chatbot.html가져와서 띄우기 (타 사이트에서)

### 🙂김예진

- 역할 : 현재 페이지에 따른, text 하이라이트, static 리소스 정리, web view 버그 수정, login/signup 버그 수정
- 세부 작업 : keword를 통해 나온 순서도가 현재 페이지에 따라 하이라이트 되는 기능

### 🙂박민준

- 역할 : 현재 페이지에 따른, text 하이라이트
- 세부 작업 : keword를 통해 나온 순서도가 현재 페이지에 따라 하이라이트 되는 기능

### 🙂이지원

- 역할 : Tutorial 내용 채우기 
- 세부 작업 : Tutorial내에 Intro부분 내용 추가 , 반응형으로 수정

<br>




### 다음주부터 진행 예정인 **개인별 역할** <br>

다음주 개인별로 담당할 부분입니다. <br><br>


### 🙂권재순

- 역할 : NCP를 사용한 웹서비스 배포
- 세부 작업 : Naver Cloud를 사용해 만들어둔 Spring Server 배포

### 🙂김동현

- 역할 : js를 통해 외부 html을 가져오기
- 세부 작업 : 스크립트로 chatbot.html가져와서 띄우기 (타 사이트에서)

### 🙂김예진

- 역할 : js를 통해 외부 html을 가져오기
- 세부 작업 : 스크립트로 chatbot.html가져와서 띄우기 (타 사이트에서)

### 🙂박민준

- 역할 : NCP를 사용한 웹서비스 배포
- 세부 작업 : Naver Cloud를 사용해 만들어둔 Spring Server 배포

### 🙂이지원

- 역할 : js를 통해 외부 html을 가져오기
- 세부 작업 :  스크립트로 chatbot.html가져와서 띄우기

<br>

<br>


## 팀 내부 회의 진행 회차 및 일자


이번주 팀회의 진행 결과는 아래와 같습니다. 총 “**2.5회차**”의 회의에 **전원 모두** 참석하였습니다.

<details>
<summary> 19회차 (2022.09.05), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 6차, 3순위 기능별 merge-1 </div>
</details> 
<details>
<summary> 20회차 (2022.09.07), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 7차, 3순위 기능별 merge-2, 다음 진행계획</div>
</details> 

<details>
<summary> 20.5회차 (2022.09.11), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  3순위 기능별 merge-3 </div>
</details> 

<br>

<br>

## 현재까지 개발 과정 요약


이번주 개발 과정은 

5,6차 진행상황 공유 및 2순위 기능개발 merge -> `7,8차 진행상황 공유 및 3순위 기능개발 merge - 1차 전체 기능 merge`

3순위 기능 개발로 1차적으로 전체 기능구현을 마쳤습니다. (남은 4순위 는 배포과정과 추가기능입니다.)
1차 전체 merge 작업을 진행하였고, 앞으로 1차 배포 작업을 진행할 예정입니다.

### 
으로 진행 중에 있습니다.


아래는 현재까지의 과정에서 기술적으로 새로 알게된 점과, 어려웠던 점 아쉬웠던 점입니다.  

<br>

### 🙂기술적으로 새로 알게된 점
- JPA에서 CASCASE를 활용해 연관 객체를 삭제할 때, 복잡한 연관관계로 이어져 있는 연관테이블의 경우, DELETE가 되지 않는 오류가 있다. (CASCADE를 없애고, 직접 삭제 로직을 구현해야된다)
- JPA에서 연관된 객체(manytoone, onetomany)를 조회할 때, 부모와 자식의 조회가 반복되어 stackoverflow error가 발생할 수 있다. 이때는 @jsonIgnore 에너테이션을 부모(onetomany)에 붙여주어야 한다.
- 템플릿을 적용해 디자인을 수정하는 방법, Thymeleaf의 여러가지 속성, collapse 적용 방법, anchor 기능

### 😥어려웠던 점
- VIEW를 구성하면서, 시각적으로 그림, 텍스트 등을 어떻게 배치해야 될지 고민되었다. + 적절한 위치로 배치하기위한 부트스트랩 클래스를 익혀보는 것도 어려웠다.
- merge를 진행하면서 각 모듈에 겹치는 entity나 repository, service 로직이 다르게 구현되어 있을 때 적절하게 수정하는 것이 어려웠습니다. 커밋을 꼼꼼하게 작성하는 것이 중요함을 한번더 깨달았습니다.
- 

### 😥아쉬운 점
- 로직을 구현하면서, DB의 설계가 잘못된 것인지, Spring 로직이 잘못 구현된 것인지, 정확한 문제를 파악하지 못했다.(데이터를 꺼내오고 수정하는 로직이 쉽게 짜여지지 않았다)
- DB 설계의 중요성을 매우 느꼈습니다. 처음 모듈별로 분담하기 전에 DB를 구체적으로 정해놓고 진행하였다면 좀더 효율적인 과정이 나오지 않았을까 하는 생각이 들었습니다.
- Sidebar의 Shadow List를 동적으로 출력시키지 못해 아쉽습니다..

<br>

<br>




## 개발 과정에서 나왔던 질문


### 🤔회의 별 질문 및 내용

이번주 2.5회차의 회의 중에 나왔던 질문, 회의내용 모음입니다.  

매 회의마다 많은 질문이 오고가고, 서로의 의견을 취합하여 다음 과정을 밟아가고 있습니다.
<br>


### Q1. 6차,7차 개발과정 공유
<details>
<summary> 세부 내용 </summary>
<div>  

#### 지난주 작업 결과
1. 재순, 지원 :
- front 템플릿 수정 완료
- documentation, tutorial 합쳐서 → documentation
- documentation/docs :  소개글
- documentation/tutorial : shadow 만드는방법 순서대로 이미지로 설명
2. 민준, 예진 :
- 시나리오형 안내 구현 완료
- 추가 view 수정
3. 동현
- 자주찾는 목록, 버튼 구현 완료
- chat view 템플릿 적용
- create, update 프론트 수정

#### 다음 역할 분담
1. 재순, 민준 : spring 서버 ncp 에 배포
2. 동현, 예진, 지원 : chat java 스크립트로 배포



</div>
</details> 




### Q2. 3순위 기능 개발 머지 - 1차 전체 머지(master)
<details>
<summary> 세부 내용 </summary>
<div>

<br>

전체 기능을 크게 chat / web 으로 구분하였고
이번 3순위 기능개발을 끝으로 chat + web 기능을 병합하였습니다.
1차 전체 merge 작업을 진행하였고, 1차 배포 작업을 진행할 예정입니다.
merge 작업 중간중간 수정작업은 병행되어질 예정입니다.

1. 순서도 CRUD 구현 부분 전체 web페이지 (feature-web-view)에 머지
   
   [https://github.com/likelion-backendschool/Withus/pull/6](https://github.com/likelion-backendschool/Withus/pull/6)

<br>

2. clova 연동 부분 ajax chat 구현 부분(feature-chat)에 머지

   [https://github.com/likelion-backendschool/Withus/pull/5](https://github.com/likelion-backendschool/Withus/pull/5)

<br>

</div>
</details>

<br>

### 🤔 해결되지 않은 질문 리스트

배포 작업에 대한 전반적인 의문이 있는 상황이며, 팀원들끼리 자료조사를 진행중에 있습니다.

### Q3. 추후 배포작업 질문

<details>
<summary> 세부 내용 </summary>
<div>

1. 어떻게 javaScript로 chat을 다른 web 서비스에 띄울것인가?

   현재 저희가 생각하고 있는 방식입니다. 아직 직접 구현에 넘어가보지 못해 구현가능성이 있는 지 판단해보고 있습니다.

   spring 서버 내부에 chat.js 가 구현되어 있고,
   chat.js는 spring 서버 내부에 있는 chat.html를 불러서 화면에 띄우는 스크립트이며,
   각각의 쇼핑몰의 layout.html 과 같은 레이아웃에 해당 chat.js를 직접 넣는 형태로 구성

   - [참고자료 1](https://velog.io/@console/JS-%EC%99%B8%EB%B6%80-HTML%ED%8C%8C%EC%9D%BC-%EC%9D%BD%EC%96%B4%EC%99%80-%ED%8C%8C%EC%8B%B1)
   - [참고자료 2](https://github.com/likelion-backendschool/Withus/blob/master/src/main/resources/templates/chatbot/chat.js)

<br>

2. spring 서버를 ncp에 어떻게 배포할 것인가?
   - 수업시간에 배운 것과 같이 ncp에서 server를 구매하여 직접 infra 를 (톰캣+nginx+mysql) 구성 후 배포
   - ncp에서 제공하는 어플리케이션 배포 방식을 이용


</div>
</details>

<br>


<br>






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

![6주차 With us 팀원들의 모습](https://i.imgur.com/fFFEb2e.jpg)
