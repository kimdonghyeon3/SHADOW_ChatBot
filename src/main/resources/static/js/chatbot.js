
    //const shadowUrl = "http://localhost:8080"

    //챗봇 켜기
    function OpenChat(){
        $("#container").show();
        $("#btnChatOpen").hide();
        console.log("그럼 여기 되냐?");
    }

    //챗봇 닫기
    function CloseChat(){
        $("#container").hide();
        $("#btnChatOpen").show();
    }

    var seq = 1; // 현재 url 이 등록되어 있지 않을 때는 1번 순서로 시작
    var flowcharts= new Array();

    // 입력 메세지에 따른 시나리오 출력
    function ChatMessageReceive(event, form){

        event.preventDefault();
        console.log(form.message.value);
        form.message.value = form.message.value.trim();

        if ( form.message.value.length == 0 ) {
            form.message.focus();
            return false;
        }

        $("#greetings").append("<div class=\"outgoing\">" + form.message.value + "</div>");  //html 추가
        $('#greetings').scrollTop($('#greetings').prop('scrollHeight'));    //스크롤 포커스

        findScenario(form);

        form.message.value = '';
        form.message.focus();
    }

    function findScenario(form){

            $.post(
//            'http://localhost:8080/chat/question/' + window.dyc.chatUid,
            'https://shadows.site/chat/question/' + window.dyc.chatUid,
            {
                question: form.message.value, // 폼 내용, input name, value
            },
            function(result) {
                console.log("받은 메시지 : " + result.message);
                console.log("받은 데이터 : "+ result.data);
                if(result.data==false){
                    var msg = `
                        <div class = "incoming">
                            <p>${result.message}</p>
                        </div>
                    `;
                    send_message(msg);
                } else {
                    flowcharts=result.data;
                    seq=find_seq(find_url());
                    var msg = `
                        <div class = "incoming">
                            <p>${result.message}</p>
                            <div class="d-flex justify-content-center">
                    `;
                    send_message(get_scenario(msg));
                }
            },
            'json'
        );

    }
    function find_url(){
         // var mainurl = $(location).attr('host');
         // var pathname = $(location).attr('pathname');
         var presentUrl = $(location).attr('href');
         console.log("url "+presentUrl);
         return presentUrl;
    }

    function find_seq(url){
         var foundSeq=1;
        // 추후 배포과정 이후, 현재 url 을 기반으로 seq 판단하는 로직 추가 필요

        $.each(flowcharts,function(i,flowchart){
             keyword=flowchart.keyword.name;
             console.log("순서 : "+flowchart.seq);
             console.log("url : "+flowchart.flow.url);
             if(url==flowchart.flow.url){
                console.log("url : "+ url+" 순서 : "+foundSeq);
                foundSeq=flowchart.seq;
             } else{
                console.log("일치하는 url 이 없음, 처음부터 시작");
             }
         });
        return foundSeq;
    }

   function click_flow_url(url,flowSeq,keyword){

     console.log("click flow url : "+url);

     if(flowcharts==null || flowcharts.length == 0){
        seq=flowSeq;
     }else{
        keyword=flowcharts[0].keyword.name;
     }
     moveUrl(url,keyword,flowSeq);

   }
/*
   function check_url_seq(){
         var presentUrl = find_url();

         if(presentUrl == url){
            console.log("이동 성공");
            seq=find_seq(url);
            console.log("seq : "+seq);
         } else {
           console.log("목표 url과 다름, 그대로");
         }

         console.log("flowcharts : "+flowcharts);
         var msg = `
            <div class = "incoming">
                <div class="d-flex justify-content-center">
        `;
         send_message(get_scenario(msg));
   }
*/

   function get_scenario(msg){
        console.log("flowcharts : "+flowcharts);

        var btn
        var description;
        var keyword;
        $.each(flowcharts,function(i,flowchart){
            keyword=flowchart.keyword.name;
            console.log("순서 : "+flowchart.seq);
            console.log("설명 : "+flowchart.flow.description);
            console.log("키워드 : "+ keyword);
            if(seq==flowchart.seq){
            btn = `
                <button class="btn btn-danger" onclick="click_flow_url('${flowchart.flow.url}',${flowchart.seq},'${keyword}');">${flowchart.flow.name}</button>
                <p> -> </p>
            `;
            description = `<br><p>${flowchart.flow.description}</p> `;
            } else {
            btn = `
                <button class="btn btn-primary" onclick="click_flow_url('${flowchart.flow.url}',${flowchart.seq},'${keyword}');">${flowchart.flow.name}</button>
                <p> -> </p>
            `;}
            msg=msg.concat(btn);
        });
        if(description==null){
            description = `
                <p>
                축하합니다. <br>
                \'${keyword}\'을(를) 해결 하였습니다. <br>
                더 궁금한 것이 있다면, \'처음으로\' 를 눌러주세요. <br>
                </p>
                <div class="d-flex justify-content-center">
                    <button class="btn btn-info" onclick="LoadChat();">처음으로</button>
                    <button class="btn btn-info" onclick="CloseChat()">종료하기</button>
                </div>
            `;
        }
        return msg+"</div>"+description+"</div>"
   }

   function send_message(msg){
        $("#greetings").append(msg);
        $('#greetings').scrollTop($('#greetings').prop('scrollHeight'));
   }

   function LoadChat(){
        html = `
            <div class="outgoing">
                hello
            </div>

            <div class="outgoing-box">
                <img src="https://shadows.site/image/icon.png" style="width: 35px; height: 35px; vertical-align: top" />
                <div class="incoming">
                    안녕하세요. 😊 <br/>
                    Shadow 챗봇 입니다! <br/>
                    <br/>
                    ※ Shadow 서비스 이용 안내 <br/>
                    자주 찾는 목적지는 챗봇 하단 버튼을 눌러주세요.<br/>
                    원하는 목적지가 없다면, 궁금한 사항을 채팅해주세요.
                </div>
            </div>
        `;
        $("#greetings").html(html);
        $('#greetings').scrollTop($('#greetings').prop('scrollHeight'));
   }

   function moveUrl(url,keyword,seq){

        location.href=url+"?"+window.dyc.chatUid+"&shadow_keyword="+keyword+"&shadow_seq="+seq;
   }