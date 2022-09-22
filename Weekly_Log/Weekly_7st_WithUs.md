## 팀 구성원, 개인 별 역할

<br>


## 팀 구성원, 개인 별 역할


### 금주부터 진행 중인 **개인별 역할** <br>
이번주 개인별로 담당한 부분입니다.입니다. <br><br>

### 🙂권재순

- 역할 : Web application 배포
- 세부 작업 : Shadow 챗봇 생성을 위한 Web 배포, ncp 내 Docker에 NginX + Tomcat + Mysql 구성

### 🙂김동현

- 역할 : chat 스크립트 배포, API키, 기타 프로젝트 설정
- 세부 작업 : chat 스크립트 배포, API키 생성, 구글 애널리스트, 오픈 그래프

### 🙂김예진

- 역할 : chat 스크립트 배포
- 세부 작업 : 채팅 기능 javascript 형태로 배포, ncp 도커에 nginx + tomcat + mysql 구성

### 🙂박민준

- 역할 : NCP를 사용한 웹서비스 배포
- 세부 작업 : Naver Cloud를 사용해 만들어둔 Spring Server 배포

### 🙂이지원

- 역할 : chat 스크립트 배포
- 세부 작업 : chat 스크립트 배포

<br>




### 다음주부터 진행 예정인 **개인별 역할** <br>
다음주 개인별로 담당할 부분입니다. <br><br>

### 🙂권재순

- 역할 : 추가 개발
- 세부 작업 : API KEY 적용, DOCS 및 Main페이지 내용 추가

### 🙂김동현

- 역할 : 발표 자료 작성, 포트폴리오 작성
- 세부 작업 : 발표 자료 작성, 포트폴리오 작성

### 🙂김예진

- 역할 : 통계 페이지 작성
- 세부 작업 : 통계( 사용자별 api 호출 횟수, 사용자별 쇼핑몰 조회 횟수) 페이지 작성, chat 시나리오 페이지 버그 수정, jar 배포

### 🙂박민준

- 역할 : 통계 페이지 작성
- 세부 작업 : 통계( 사용자별 api 호출 횟수, 사용자별 쇼핑몰 조회 횟수) 페이지 작성, chat 시나리오 페이지 버그 수정, jar 배포

### 🙂이지원

- 역할 : API KEY 적용, 포트폴리오 작성
- 세부 작업 : API KEY 적용 , DOCS 페이지 내용 추가 , 발표 자료 작성

<br>



<br>

## 팀 내부 회의 진행 회차 및 일자


이번주 팀회의 진행 결과는 아래와 같습니다. 총 “**3회차**”의 회의에 **전원 모두** 참석하였습니다.

<details>
<summary> 21회차 (2022.09.13), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  개발과정 공유 8차, chat+web 기능 모두 머지</div>
</details> 
<details>
<summary> 22회차 (2022.09.15), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  ncp 배포 중간 확인(강사님 질문), 개발과정 공유 9차, 3차 멘토님 중간점검 </div>
</details> 

<details>
<summary> 23회차 (2022.09.19), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  배포 확인, 개발과정 공유 10차, 강사님 최종점검 </div>
</details> 

<br>

<br>


## 현재까지 개발 과정 요약


이번주 개발 과정은 

7,8차 진행상황 공유 및 3순위 기능개발 merge - 1차 전체 기능 merge -> `1차 배포 및 최종 점검`



### 
으로 진행 중에 있습니다.


아래는 현재까지의 과정에서 기술적으로 새로 알게된 점과, 어려웠던 점 아쉬웠던 점입니다.  

<br>

### 🙂기술적으로 새로 알게된 점
- javascript 에서 createElement()를 통해 HTML 요소를 생성하고, 그 생성한 요소에 fetch()의 형태로 html을 불러오는 방법을 배웠습니다.
- spring boot 2.7은 현재 tomcat 9에 최적화 되어있으며, 외장 톰캣을 사용할 경우 java.servlet 패키지를 jakarta.servlet으로 변경해야 함을 배웠습니다.
- 동적으로 html을 삽입하여 원하는 view를 띄워주는 방식이 단순 http통신으로도 가능하다는 점을 알 수 있었습니다.
- window.dyc

### 😥어려웠던 점
- url에 대하여 html를 리로딩 할때, head에들어가는 css나 js 등이 같이 반영되는 점이 어려웠습니다.
- 이전부터 발생하던, shadow create 부분에서 계속해서 나오는 오류로 인해서, 원인을 분석하는 것이 어려웠습니다.



### 😥아쉬운 점
- javascript 에서 cors의 모든 method에 허용하였으나, OPTIONS 메소드로 preflight 요청이 403 이 발생하는 점을 아직 해결하지 못해 아쉬웠습니다.
- js를 통해서 head를 동적으로 삽입하는 과정에서 script의 내용이 적용되지 않는 문제가 발생하여 script를 초기부터 넣어주는 형태로 변경하여 아쉬웠습니다.


<br>

<br>







## 개발 과정에서 나왔던 질문


### 🤔회의 별 질문 및 내용

이번주 3회차의 회의 중에 나왔던 질문, 회의내용 모음입니다.  
매 회의마다 많은 질문이 오고가고, 서로의 의견을 취합하여 다음 과정을 밟아가고 있습니다.
<br>


### Q1. 8차,9차,10차 개발과정 공유
<details>
<summary> 세부 내용 </summary>
<div>  

#### 지난주 작업 결과
1. 재순/민준 : 
1) 완료 
	- ncp + server 구성 완료 
	- shadows.site 도메인 구매 완료(1900원 재순 사비) 
	- mysql+ nginx proxy manager+ tomcat (docker)에 설치완료
2) 특이사항 : 현재 nginx proxy manager+tomcat (war) 배포시 (npm)사이즈로 인한 에러발생
--> 강사님께 질문 : 배포는 아직 급하지 않으니 다음주 중에 젠킨스를 이용하는 것 수업진행할 예정이니 그때 참고바람
--> 예진 :tomcat에 war로 단순 배포 완료

2. 예진/동현/지원 : 스크립트로 배포하는 부분 개발 
	- 소유한 챗봇 판단 구현 완료
	- 이미 spring에서 랜더링된 html을 그대로 js을 통해(createElement) 불러오는 형태 -> URL 이 변경되면 다시 불러옴  구현완료
--> 버그 수정 필요, 강사님 피드백 반영 필요


#### 다음 역할 분담
최종 점검 이후 강사님 피드백을 받아 아래의 두가지 추가기능을 도입하고, 마지막 주에는 포트폴리오 형태의 발표,시현 자료를 작성하기로 하였습니다.
1. 재순, 동현, 지원 : 보안을 위한 API 키 도입
2. 민준, 예진 : 통계 페이지 구현


</div>
</details> 



### Q2. 3차 중간점검 결과
<details>
<summary> 세부 내용 </summary>
<div>

1. 칭찬 
- 현재 5개 멘토님 담당 팀 중에 가장 잘했다고 해주셨습니다.
- UI가 아주 이쁘다고 해주셨습니다.

2. 남은 기간 동안 추가기능 구현해볼 것을 권장
- chat , 자주찾는 목록, 시나리오 : 배포 이후 url 판단하여 링크로 넘어가는 부분 수정 필요합니다.
- 그외 버그 수정

<br>


<br>
</div>
</details>
<br>

### 🤔 해결되지 않은 질문 리스트

금일 강사님께 받은 최종 점검을 바탕으로 해결되지 않던 배포 문제에 해결점을 얻은 상황입니다.
따라서 받은 피드백을 기준으로 내용을 작성하였습니다.

### Q3. spring 서버에 대한 배포 질문

<details open>
<summary> 세부 내용 </summary>
<div>

1. spirng을 war로 배포하는 것이 옳은지에 대한 질문

1) 현재 구성상황
  - nginx proxy manager + 톰캣 + mysql 로 구성하여, spring 을 war로 외장 톰캣에 배포

2) 강사님 피드백
  - war 형태보다는 jar 형태로 application 단위로 배포하는 것이 요즈음 더 많이 사용된다.
	- 수업 중 jenkins로 docker에 자동 배포하는 CI/CD 배울 예정이니, 이후에 반영 하면 될 것 같다.


2. 어떻게 javaScript로 chat을 다른 web 서비스에 띄우고, 페이지가 넘어갔을 때 chat 정보가 유지될 것인가?

1) 현재 구성 상황
	- chatbot 서버/chat.html 을 그대로 외부 쇼핑몰 서비스에서 createElement로 요소를 만들어 해당 값만을 그대로 fetch로 넘겨줌
  - 외부 쇼핑몰 url 이 이동되었을 때, url 파라미터로 값을 넘겨서 다시 전부 로딩하는 형태

2) 강사님 피드백
	- createElement와 fetch 로 구성하는 것이 가장 일반적이다. 그외에는 방법이 복잡하다.
	- url 이 이동되었을 때 오히려 파라미터로 값을 보내는 것이 링크형태로 고객에게 전달할 수 있어 더 좋은 방법이다. 
		실제 서비스되는 네이버 쿠팡도 url이 복잡하다.
	- url 이 이동될때 전부 페이지를 로딩하는 것은 어쩔수 없는 부분이다. 실제 외부 쇼핑몰이 react로 구현되어 있지 않는 이상, 모두 재로딩해야한다.
	- 현재 구성상황 그대로 가되, 추가적인 기능을 좀더 보완하면 좋을 것 같다.


<br>



</div>
</details>

### Q4. 최종 점검 강사님 피드백

<details open>
<summary> 세부 내용 </summary>
<div>

1. 전체적인 프로젝트 내용이 뻔하지 않고 신선해서 포트폴리오(이력서) 작성에 도움이 될것 같다.
    - clova API를 사용했던 점이나, java script를 다른 서비스에 띄워본 것과 같은 신선한 내용을 강조해보면 좋을 것 같다.

2. 추가적으로 구현해보면 좋을 기능들을 이번주까지만 할수 있는 것들만 구현해보면 좋을 것 같다.
    - API 키
    - 통계 서비스
    - Redirect URI
    - 기타 간단한 기능(검색엔진, favicon, 구글 애널리스트, 오픈 그래프)

3. 마지막 주에는 시현겸 포트폴리오를 자세히 잘 작성해서 이력서를 미리 작성하면 좋을 것같다.
    - 시현 youtube (요즘에는 시현 유튜브는 꼭 한다고 합니다.)
    - erd, use diagram, 스펙, 배포 구조 등 이런 시각자료 작성을 풍부하게 하여 있어보이게 포장을 잘하는 것이 중요하다. 
			(사실 비중이 기능구현이 4고, 이렇게 포장 잘하는게 6이다. )
    - 예시 링크 : [https://wiken.io/ken/4789](https://wiken.io/ken/4789)

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

![7주차 With us 팀원들의 모습](https://i.imgur.com/c7CbmUi.jpg)
