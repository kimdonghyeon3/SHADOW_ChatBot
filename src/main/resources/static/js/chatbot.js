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

    var seq = find_seq(1); // 임의로 1번 순서로 시작
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
            '/chat/question',
            {
                question: form.message.value, // 폼 내용, input name, value
                // mainurl : mainurl // url로 shadow id를 판단할 때 사용 -> 아직 배포과정 미정
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

    function find_seq(val){
        // 추후 배포과정 이후, 현재 url 을 기반으로 seq 판단하는 로직 추가 필요
        return val;
    }

   function click_flow_url(url){
     // 현재 주소
     var mainurl = $(location).attr('hostname');
     console.log("url "+mainurl);

     console.log("click flow url : "+url);
     seq++; // 배포 후 수정 필요
     console.log(seq);

     console.log("flowcharts : "+flowcharts);
     var msg = `
        <div class = "incoming">
            <div class="d-flex justify-content-center">
    `;
     send_message(get_scenario(msg));


   }

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
                <button class="btn btn-danger" onclick="click_flow_url('${flowchart.flow.url}');">${flowchart.flow.name}</button>
                <p> -> </p>
            `;
            description = `<br><p>${flowchart.flow.description}</p> `;
            } else {
            btn = `
                <button class="btn btn-primary" onclick="click_flow_url('${flowchart.flow.url}');">${flowchart.flow.name}</button>
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
                    <button class="btn btn-info" onclick="location.href='/chat'">처음으로</button>
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