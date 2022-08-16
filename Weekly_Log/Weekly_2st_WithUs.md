
## 팀 구성원, 개인 별 역할

이번주 개인별로 담당한 부분입니다.입니다. <br><br>

### 금주부터 진행 중인 **개인별 역할** <br>

### 🙂권재순

- 역할 : CLOVA + Spring server 연결
- 세부 작업 : WebSocket을 이용해 문장을 CLOVA 서버에 연결 후 전송하여 분석하고, 분석한 결과를 간단한 Web application에 출력.

### 🙂김동현

- 역할 : 기본 채팅 페이지 제작 및 채팅 기능 구현
- 세부 작업 : websocket을 활용한 채팅 구현(chat DTO 규약 변경 (content -> question, answer))

### 🙂김예진

- 역할 : 회원가입,로그인 페이지 제작 및 기능 구현
- 세부 작업 : 기본 회원가입 페이지 입력,출력 구현, 로그인 페이지 입력, 출력 구현

### 🙂박민준

- 역할 :  CLOVA + Spring server 연결
- 세부 작업 : WebSocket을 이용해 문장을 CLOVA 서버에 연결 후 전송하여 분석하고, 분석한 결과를 간단한 Web application에 출력.

### 🙂이지원

- 역할 : 기본 채팅 페이지 제작 및 채팅 기능 구현
- 세부 작업 :  websocket을 활용한 채팅 구현, 채팅 ui 구현 (기본 ui 변경 )

<br>



## 팀 내부 회의 진행 회차 및 일자

이번주 팀회의 진행 결과는 아래와 같습니다. 총 “**5회차**”의 회의에 **전원 모두** 참석하였습니다.

<details>
<summary> 9회차 (2022.08.09), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  ERD 2차 작성, 중간 체크리스트 작성, 멘토 중간점검일 확정 </div>
</details> 
<details>
<summary> 10회차 (2022.08.10), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  안성진 멘토님 중간 점검 </div>
</details> 
<details>
<summary> 11회차 (2022.08.11), 디코 음성 채널 진행, 전원 참석</summary>
   <div>  git 규약 확정, 코드컨벤션 확정, 멘토님 피드백 확인, spring프로젝트 생성, 회고작성 </div>
</details> 
<details>
<summary> 12회차 (2022.08.12), 디코 음성 채널 진행, 전원 참석</summary>
   <div> 개인회고, 팀회고 규칙 설정, git branch규칙, clova 요금 확인  </div>
</details>
<details>
<summary> 13회차 (2022.08.13), 디코 음성 채널 진행, 전원 참석</summary>
   <div> 개인별 개발 진행상황 공유- 0회차(branch생성), 위클리 2차 작성  </div>
</details>
<br>



## 현재까지 개발 과정 요약

이번주 개발 과정은 

ERD 확정 -> 중간 점검 (멘토님) -> Git 규약, 코드 컨벤션 -> <U>***1순위 기능 개발***</U>

### 
으로 진행 중에 있습니다.

<br>
아래는 현재까지의 과정에서 기술적으로 새로 알게된 점과, 어려웠던 점 아쉬웠던 점입니다. <br> 


### 🙂기술적으로 새로 알게된 점
- Spring boot와 CLOVA 서버를 WebSocket을 이용하여 문장 전송 및 키워드의 답이 출력 가능했습니다. 
- WebSocket 기술을 순수 Socket, SOCKJS, STOMP 총 3가지로 나뉘어 사용된다는 것을 알 수 있었다.
- Http와 Websocket의 차이점 
- spring security 를 이용하여 보안(로그인) 구현을 위한 여러가지 방법을 알게되었습니다.(세션,쿠키,토큰 등)
### 😥어려웠던 점
- Spring boot와 CLOVA 서버에서 연동 시 SECRET KEY, APIURL 등 설정할 것들이 많아 어려웠습니다.
- 처음 접하는 STOMP 형식 SOCK 통신에 대해서 전체 흐름을 이해하기 어려웠다.
- 프론트 name 속성에 따라, Mapping의 형식을 맞춰야 해서 어려웠다.
- 프로젝트의 전체적인 구조(mvc)를 어떻게 구성해야 하는지가 어려웠습니다.(domain, global로 나누어보았는데 merge할때 합의가 필요할 것 같습니다)
- 실제 로그인 액션, 로그아웃 액션에 대한 spring security의 전체적인 구조를 알아야 하는 뿌분이 어려웠습니다.
### 😥아쉬운 점
- WebSocket과 CLOVA api를 아직 이해하지 못하여 연동에 실패하여 아쉬웠습니다.
- 템플릿 UI의 한계가 있어, 프로젝트에 맞게 변형이 필요해 보인다.
<br>


<br>

## 개발 과정에서 나왔던 질문

### 🤔회의 별 질문 리스트

이번주 5회차의 회의 중에 나왔던 질문, 회의내용 모음입니다.  
매 회의마다 많은 질문이 오고가고, 서로의 의견을 취합하여 다음 과정을 밟아가고 있습니다.
<br>

### Q1. 체크리스트 확인 시 질문 모음 
<details>
<summary> 세부 질문 내용 </summary>
<div>  

**q. Github으로 협업을 어떻게 하면 좋나요?**
main branch → **기능별로 나눠서 branch 나누고**, 회의할때 merge 하고 (코드리뷰후에 merge)
source tree 툴을 사용하면 GUI로 작업하기 편합니다.

**q. 변수명이나 코드 컨벤션 을 정하면 좋을것 같습니다.**

**q.  애자일하게 개발은 어떻게 하는 것인가요?**
요구사항이 변경되면, 변경된 것을 스프린트 돌리고? , 계획을 수정하고 다시 돌리는 과정이므로, 위클리로 작업을 머지하는 지금의 과정이
애자일 방식으로 개발을 진행하는 것입니다.

</div>
</details> 

### Q2. 중간점검 체크리스트 작성 시 질문 모음

<details>
<summary> 세부 질문 내용 </summary>
<div>



**q. 현재 DB 구성 상황에서 중복을 피할 수 있는 방법이 있을까요?, 지금의 방식이 보편적으로 사용되는 방법일까요?**
flowchart_tbl 중복현황 : 
[https://docs.google.com/spreadsheets/d/1OCrIUnf_JQt7pkoYsxs7ozqrSPUEorzPOvI4Ev1tc20/edit](https://docs.google.com/spreadsheets/d/1OCrIUnf_JQt7pkoYsxs7ozqrSPUEorzPOvI4Ev1tc20/edit)

a.   
    중복된다고 크게 문제될 것 같지 않습니다.   
 '  이름만 중복' 으로 진행하시면 될것 같습니다.

[중간점검 체크리스트](https://www.notion.so/1f70c8eaa4cb4bd0b5a3f06efebb81f8)  
[ERD-결과](https://dbdiagram.io/d/62f07ca7c2d9cf52fa61b1ee) 

<br>

**q. 현재 이대로 진행해도 괜찮을지?**

a.  문제없이 잘 되고 있고, 실제 개발을 진행해보면 더 확실하게 알게됩니다.

</div>
</details>

### Q3. Git 규칙 관련 질문 모음
<details>
<summary> 세부 질문 내용 </summary>
<div>

**q. Git 규약**

```css
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 관련
style : 스타일 변경 (포매팅 수정, 들여쓰기 추가, …)
refactor : 코드 리팩토링
test : 테스트 관련 코드
build : 빌드 관련 파일 수정
ci : CI 설정 파일 수정
perf : 성능 개선
chore : 그 외 자잘한 수정
```

#### **q. Git branch 규칙** 

어제 디코에 여쭤봤을 때, 이름으로 하면 더 쉬울 것 같지만 기능별로 명명하는 것이 좋을 것 같다는 의견이 있어서 다들 괜찮으시면 “기능”으로 가심이 어떠실지요!

- 기능별로 명명하기
    제가 생각하는 형식을 예시로 적어봤는데, 여러분의 생각과 맞는지 모르겠어서 어떤 형식이 좋을지 말해주시면 제가 간단히 [README.md](https://github.com/likelion-backendschool/QUPP) (참고 QUPP 팀 리드미) 에 적어놓겠습니다.

```java
feature-login
feature-signup
feature-chat-websocket
feature-clova-apigw
```

</div>
</details>

### Q4. 개발 1차 과정 질문 모음
<details>
<summary>세부 질문 내용</summary>
<div>

**q.  InteliJ에서 git branch를 어떻게 만들고, 해당 branch로 어떻게 clone 받나요?**
- branch를 만들때, 권한이 없어서 develop-setting에서 token을 받아 token으로 연동해얗
- branch를 만든 다음 해당 branch로 바로 커밋하면됩니다.
- Intelij에서 Git을 바로 연동하면, commit, push가 바로되고, 충돌이 일어났을때 GUI로 보기좋게 표기해줍니다.

</div>
</details>

<br>

### 🤔해결되지 않은 질문 리스트

이번주 해결되지 않은 질문 리스트입니다.
Naver에서 제공하는 유료 플랫폼 Clova를 사용하다 보니, 가격적인 측면에서 어려움을 겪고 있습니다.


<details>
<summary>세부 질문 내용</summary>
<div>

**q. 가격 확인 - clova**

그 또 요금 관련해서 추가적인 사항인데.. 
현재 custom 연동을 하려면 APIGW 연동이 필요한데, 
이 기능을 사용하기 위해서는 Naver API GateWay 서비스가 필요하다고 합니다.. 
그런데 무료가 아닌 사용 요금이 있는 것 같습니다ㅠㅠ

블로그 : [[https://blog.naver.com/n_cloudplatform/221123434939](https://blog.naver.com/n_cloudplatform/221123434939)]([https://blog.naver.com/n_cloudplatform/221123434939](https://blog.naver.com/n_cloudplatform/221123434939))

사용요금 안내 : [[https://www.ncloud.com/product/applicationService/apiGateway](https://www.ncloud.com/product/applicationService/apiGateway)]([https://www.ncloud.com/product/applicationService/apiGateway](https://www.ncloud.com/product/applicationService/apiGateway))

가이드 : [[https://guide.ncloud-docs.com/docs/apigw-apigw-1](https://guide.ncloud-docs.com/docs/apigw-apigw-1)]([https://guide.ncloud-docs.com/docs/apigw-apigw-1](https://guide.ncloud-docs.com/docs/apigw-apigw-1))

#### 1차 방안
무료 크래딧으로 구매, 팀원 계정 공유하면서 크래딧으로 최대한 해결

#### 2차 방안
naver cloud platform 내부에 있는 서비스로, ncp 10만원이 가능해질 경우, 해당 비용으로 충당

</div>
</details>

<br>


## 개발 결과물 공유

### 😀결과물

#### Github Repository URL

[https://github.com/likelion-backendschool/Withus](https://github.com/likelion-backendschool/Withus)


### WBS - 중간 확정
[https://docs.google.com/spreadsheets/d/1mbyUlXgf4MniXAPvGZXqFZ4QALEV3PRuGO6nmig7BFU/edit#gid=0](https://docs.google.com/spreadsheets/d/1mbyUlXgf4MniXAPvGZXqFZ4QALEV3PRuGO6nmig7BFU/edit#gid=0)

<br>

지난주 결과물에 수정된 설계 내용입니다. 

#### 1. 요구사항 명세서 - 확정

[https://docs.google.com/spreadsheets/d/10tixE9Z94O-7i12VkGGxMz72z4DWV35PUQhmG1XZ8tA/edit?usp=sharing](https://docs.google.com/spreadsheets/d/10tixE9Z94O-7i12VkGGxMz72z4DWV35PUQhmG1XZ8tA/edit?usp=sharing)

#### 2. UI 설계도 - 5차

[https://ovenapp.io/view/jSbwxzEdtxi0O4uNSLbHhLrnclkWEnJB/1CuKg](https://ovenapp.io/view/jSbwxzEdtxi0O4uNSLbHhLrnclkWEnJB/1CuKg)

#### 3. ERD - 5차

[https://dbdiagram.io/d/62f07ca7c2d9cf52fa61b1ee](https://dbdiagram.io/d/62f07ca7c2d9cf52fa61b1ee)

<br>   

### 😀With Us 팀원들의 모습

![2주차 With us 팀원들의 모습](https://i.imgur.com/OY0BXJw.jpg)



