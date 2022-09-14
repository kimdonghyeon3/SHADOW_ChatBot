   console.log('test js 로딩됨');

    var head = `
            <title>SHADOW</title>

            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
            <!-- JQUERY JS -->
            <script src="/webjars/jquery/jquery.min.js"></script>

            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="/css/chatbot.css">
            <script type="text/javascript" src="/js/chatbot.js"></script>
    `;

    function loadHead(){
        console.log('test 시작');
        var el = document.createElement('head');
        console.log('element : '+ el);
        el.innerHTML = head;
        document.body.appendChild( el );
    }
    //test();
    function callChat(){
        console.log('callchat() 시작')

        const config = {
          method: "get"
        };

        fetch("http://www.shadow.site:8080/chat", config)
          .then(response => response.text())
          .then(data => {

            console.log(data);
            var el = document.createElement('div');
            console.log('element : '+ el);
            el.innerHTML = data;
            document.body.appendChild( el );
          } )
          .catch(error => console.log(error));


    }
    callChat();