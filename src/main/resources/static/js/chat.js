   console.log('chat js 로딩됨');

    //const shadowUrl = "https://shadows.site"
    const shadowUrl = "http://localhost:8080"

    console.log("chatUid : "+ window.dyc.chatUid);

    var search = location.search;
    console.log('search : '+search);
    if(search != ''){
        ShowChat();
    }else{
        StartChat();
    }

    function addChatScript(){
        console.log('chatbot.js 추가');

        var script = document.createElement('script');
            script.src = shadowUrl+'/js/chatbot.js';
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

    function StartChat(){
        console.log('StartChat() 시작')
        console.log(shadowUrl+"/chat/" + window.dyc.chatUid);

        const config = {
          method: "get"
        };

        fetch(shadowUrl+"/chat/" + window.dyc.chatUid, config)
          .then(response => response.text())
          .then(data => {

            var el = document.createElement('div');
            el.innerHTML = data;
            // console.log('element : '+ el);
            document.body.appendChild( el );
            addChatScript();
            addJquery();

          } )
          .catch(error => console.log(error));
    }

    function ShowChat(){
        console.log('ShowChat() 시작');
        let searchParams = new URLSearchParams(search);
        var keyword=searchParams.get('keyword');
        var seq = searchParams.get('seq');
        var url = location.protocol + "//"+location.host + location.pathname;

        console.log('keyword : '+keyword+' seq : '+seq + 'url'+ url+'인 chatbot 불러오기');
                const config = {
                  method: "post"
                };

                fetch(shadowUrl+"/chat/"  + window.dyc.chatUid +"?keyword="+keyword+"&seq="+seq+"&url="+url, config)
                  .then(response => response.text())
                  .then(data => {

                    var el = document.createElement('div');
                    el.innerHTML = data;
                    // console.log('element : '+ el);
                    // document.body.appendChild()가 간헐적으로 TypeError: Cannot read properties of null (reading 'appendChild') 발생.
                    // 새로고침 할시, 정상 호출 됨.
                    document.body.appendChild( el );
                    addChatScript();
                    addJquery();

                  } )
                  .catch(error => console.log(error));

    }
