var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#msg").html("");
}

function connect() {
    var socket = new SockJS('/ws'); // websocket 실행 : url이 /ws인 곳에 연결
    stompClient = Stomp.over(socket); // 서버 연결, 메시지 전송, 상대방 구독 관력 값을 추가 할당 가능
    stompClient.connect({}, function (frame) { //Connect 프레임 전송 : 서버 연결
        setConnected(true);
        console.log('Connected: ' + frame);
        // message : 요청한 질문에 대한 챗봇에서의 메시지
        stompClient.subscribe('/topic/shadow', function (message) { // 특정 url에 구독해야 하는데, 특정 메세지를 보내거나 받을 곳 -> 우리 UI
            showMessage("받은 메시지: " + message.body); //서버에 메시지 전달 후 리턴받는 메시지
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    let message = $("#msg").val()
    showMessage("보낸 메시지: " + message);

    stompClient.send("/app/sendMessage", {}, JSON.stringify(message)); // 서버에 보낼 메시지
}

function showMessage(message) {
    $("#communicate").append("<tr><td>" + message + "</td></tr>");
}

(function($) {
    $(function() {
        $("form").on('submit', function (e) {
            e.preventDefault();
        });
        $( "#connect" ).click(function() { connect(); });
        $( "#disconnect" ).click(function() { disconnect(); });
        $( "#send" ).click(function() { sendMessage(); });
    });
}) (jQuery);
