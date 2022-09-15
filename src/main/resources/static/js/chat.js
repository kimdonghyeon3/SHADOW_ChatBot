console.log('test js 로딩됨');

// var head = `
//         <title>SHADOW</title>
//
//         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
//         <!-- JQUERY JS -->
//         <script src="/webjars/jquery/jquery.min.js"></script>
//
//         <!-- Bootstrap CSS -->
//         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.0/css/bootstrap.min.css">
//         <link rel="stylesheet" href="localhost:8080/css/chatbot.css">
//         <script type="text/javascript" src="localhost:8080/js/chatbot.js"></script>
// `;

function callChat(){
    console.log('callchat() 시작')

    window.dyc = {
        chatUid:1
    }

    console.log(window.dyc)
    window.dyc.chatUid = 2;

    const config = {
      method: "get"
    };

    fetch("http://localhost:8080/chat/head", config) //chat body 가져오기
        .then(response => response.text())
        .then(data => {
            console.log(data);

            var el = document.createElement('div');

            el.innerHTML = data;  //body태그 추가

            var collection = el.children;

            [...collection].map(node =>
                document.head.appendChild(node)
            );
            document.getElementsByClassName("html")[0].setAttribute("xmlns:th","http://www.thymeleaf.org");

        } ).catch(error => console.log(error));

    fetch("http://localhost:8080/chat", config) //chat body 가져오기
      .then(response => response.text())
      .then(data => {

        console.log(data);

        var el = document.createElement('div');

        el.innerHTML = data;  //body태그 추가

        document.body.appendChild( el );
      } )
      .catch(error => console.log(error));
}
callChat();