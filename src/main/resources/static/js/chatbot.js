

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

        // 입력 메세지에 따른 시나리오 출력
        function ChatMessageWrite(event, form){

            event.preventDefault();
            console.log(form.message.value);
            form.message.value = form.message.value.trim();

            if ( form.message.value.length == 0 ) {
                form.message.focus();
                return false;
            }

            $("#greetings").append("<div class=\"incoming\">" + form.message.value + "</div>");  //html 추가
            $('#greetings').scrollTop($('#greetings').prop('scrollHeight'));    //스크롤 포커스

            // 현재 주소
            var mainurl = $(location).attr('hostname');
            console.log("url "+mainurl);

            $.post(
                '/chat/write', // 주소, action
                {
                    chatMessage: form.message.value, // 폼 내용, input name, value
                    // mainurl : mainurl // url로 shadow id를 판단할 때 사용 -> 아직 배포과정 미정
                },
                function(result) { // 콜백 메서드, 통신이 완료된 후, 실행
                    console.log("받은 메시지 : " + result.message);
                    console.log("받은 데이터 : "+ result.data);
                    var msg = `
                        <div class = "outgoing">
                            <p>${result.message}</p>
                            <div class="d-flex justify-content-center">
                    `;
                    $.each(result.data,function(i,flowchart){
                        console.log("순서 : "+flowchart.seq);
                        console.log("설명 : "+flowchart.flow.description);
                        var btn = `
                            <button class="btn btn-primary" onclick="click_flow_url('${flowchart.flow.url}');">${flowchart.flow.name}</button>
                            <p> -> </p>
                        `;
                        msg=msg.concat(btn);
                    });
                    $("#greetings").append(msg+"</div></div>");
                    $('#greetings').scrollTop($('#greetings').prop('scrollHeight'));    //스크롤 포커스
                },
                'json'
            );

            form.message.value = '';
            form.message.focus();
        }
        function find_seq(seq){
            // 추후 배포과정 이후, 현재 url 을 기반으로 seq 판단하는 로직 추가 필요
            return seq;
        }
        function click_flow_url(url){
             console.log("click flow url : "+url);
             location.href="http://"+url;
        }

