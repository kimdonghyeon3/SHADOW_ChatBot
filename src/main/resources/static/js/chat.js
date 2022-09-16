   console.log('test js 로딩됨');

/*    var script = document.createElement('script');
    script.src = 'https://code.jquery.com/jquery-3.5.1.min.js';
    script.type = 'text/javascript';
    document.getElementsByTagName('head')[0].appendChild(script);
*/


    var head = `
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
            <!-- JQUERY JS -->
            <script src="/webjars/jquery/jquery.min.js"></script>

            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="www.shadow.site:8080/css/chatbot.css">
            <script type="text/javascript" src="www.shadow.site:8080/js/chatbot.js"></script>
    `;
    var scripts=`
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="www.shadow.site:8080/js/chatbot.js"></script>
    `;

    function loadHead(){
        console.log('test 시작');
        document.getElementsByTagName("head")[0].insertAdjacentHTML(
            "beforeend",
            scripts);
//        var el = document.createElement('div');
//        console.log('element : '+ el);
//        el.innerHTML = head;
//        document.body.appendChild( el );
    }
    //test();
//    loadHead();
    function addChatScript(){
        console.log('chatbot.js 추가');

        var script = document.createElement('script');
            script.src = 'http://www.shadow.site:8080/js/chatbot.js';
            script.type = 'text/javascript';
            document.getElementsByTagName('head')[0].appendChild(script);
    }
    function addJquery(){
        console.log('jquery 추가');

        var script = document.createElement('script');
            script.src = 'https://code.jquery.com/jquery-3.5.1.min.js';
            script.type = 'text/javascript';
            document.getElementsByTagName('head')[0].appendChild(script);
    }
    function callChat(){
        console.log('callchat() 시작')

/*        $.get( "http://www.shadow.site:8080/chat.html", function( data ) {
          $( ".result" ).html( data );
          alert( "Load was performed." );
        });*/

        const config = {
          method: "get"
        };

        fetch("http://www.shadow.site:8080/chat", config)
          .then(response => response.text())
          .then(data => {

            //console.log(data);
            var el = document.createElement('div');
            console.log('element : '+ el);
            el.innerHTML = data;
            document.body.appendChild( el );
            addChatScript();
            addJquery();
            //loadHead();
          } )
          .catch(error => console.log(error));


    }
    callChat();