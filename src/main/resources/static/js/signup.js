    console.log('signup.js 시작');
    var urlPath = $(location).attr('pathname');

    var username;
    var email;
    var isCheckedUsername="false";
    var isCheckedEmail="false";

    function checkUsername(btn){
      console.log('click check username');
      username=$("#username").val();
      console.log(username);
      if($('#success-username-msg').hasClass('d-none')!="true" || $('#fail-username-msg').hasClass('d-none')!="true"){
        $('#success-username-msg').addClass('d-none');
        $('#fail-username-msg').addClass('d-none');
      }

      $.ajax({
        url: urlPath+'/checkUsername',
        type: 'POST',
        data: { username : username
        },
        success: function(result){

            //console.log(result);
            if(result.data){
            $('#success-username-msg').removeClass('d-none');
            $('#success-username-msg').text(result.message);
            //console.log($('#success-username-msg').text());
            isCheckedUsername="true";
            console.log(isCheckedUsername);
            }else{
            $('#fail-username-msg').removeClass('d-none');
            $('#fail-username-msg').text(result.message);
            isCheckedUsername="false";
            $('#username').focus();
            }

        },
        error: function (){
        }
      });
    }

    function checkEmail(btn){
      console.log('click check email');
      email=$("#email").val();
      console.log(username);
      if($('#success-email-msg').hasClass('d-none')!="true" || $('#fail-email-msg').hasClass('d-none')!="true"){
        $('#success-email-msg').addClass('d-none');
        $('#fail-email-msg').addClass('d-none');
      }

      $.ajax({
        url: urlPath+'/checkEmail',
        type: 'POST',
        data: { email : email
        },
        success: function(result){
            if(result.data){
            $('#success-email-msg').removeClass('d-none');
            $('#success-email-msg').text(result.message);
            //console.log($('#success-email-msg').text());
            isCheckedEmail="true";
            }else{
            $('#fail-email-msg').removeClass('d-none');
            $('#fail-email-msg').text(result.message);
            $('#email').focus();
            isCheckedEmail="false";
            }
        },
        error: function (){
        }
      });
    }

    function SubmitForm(form) {
        var submit=0;

        /* 아이디 이메일 중복 체크 했는지 확인 */
        console.log('아이디 이메일 중복 체크 여부 확인');
        $.ajax({
        url: '/isChecked',
        type: 'POST',
        data: {
            isCheckedUsername : isCheckedUsername,
            isChangedUsername : (form.username.value != username),
            isCheckedEmail : isCheckedEmail,
            isChangedEmail : (form.email.value != email)
        },
        success: function(result){
            console.log(result);
            isCheckedUsername=result.data.username;
            isCheckedEmail=result.data.email;
            console.log('isChecked username? '+ isCheckedUsername);
            console.log('isChecked Email? '+ isCheckedEmail);
            if(isCheckedUsername && isCheckedEmail){
                console.log('username checked ');
                console.log('email checked ');
                form.submit();
            }else {
                if($('#success-username-msg').hasClass('d-none')!="true" || $('#success-email-msg').hasClass('d-none')!="true"){
                    $('#success-username-msg').addClass('d-none');
                    $('#success-email-msg').addClass('d-none');
                  }
                if(!isCheckedUsername){
                    $('#fail-username-msg').removeClass('d-none');
                    $('#fail-username-msg').text(result.message);
                    $('#username').focus();
                }
                if(!isCheckedEmail){
                    $('#fail-email-msg').removeClass('d-none');
                    $('#fail-email-msg').text(result.message);
                    $('#email').focus();
                }
                return;
            }
        },
        error: function (){
        }
      });
    }
