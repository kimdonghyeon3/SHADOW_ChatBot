
    var urlPath = $(location).attr('pathname');

    var username;
    var email;
    var isCheckedUsername="false";
    var isCheckedEmail="false";

    function SubmitForm(form) {
        var submit=0;

        /* 아이디 중복 체크 했는지 확인 */
        $.ajax({
        url: '/isCheckedUsername',
        type: 'POST',
        data: {
            isCheckedUsername : isCheckedUsername,
            isChangedUsername : (form.username.value != username)
        },
        success: function(result){
            console.log(result);
            if(result.data){
                submit=1;
            }else{
                $('#fail-username-msg').removeClass('d-none');
                $('#fail-username-msg').text(result.message);
               // $('#isCheckedUsername').val("false");
                isCheckedUsername="false";
                $('#username').focus();
                return;
            }
        },
        error: function (){
        }
      });

      /* 이메일 중복 체크 했는지 확인 */
        $.ajax({
        url: '/isCheckedEmail',
        type: 'POST',
        data: {
            isCheckedEmail : isCheckedEmail,
            isChangedEmail : (form.email.value != email)
        },
        success: function(result){

            console.log(result);
            if(result.data){
                console.log(submit);
                if(submit==1){
                    form.submit();
                }else {
                    return;
                }
            }else{
                $('#fail-email-msg').removeClass('d-none');
                $('#fail-email-msg').text(result.message);
               // $('#isCheckedEmail').val("false");
                isCheckedEmail="false";
                $('#email').focus();
                return;
            }
        },
        error: function (){
        }
      });
    }

     /* 아이디 중복 체크*/
    $('#checkUsernameBtn').on('click', function(){

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
    });

    /* 이메일 중복 체크*/
    $('#checkEmailBtn').on('click', function(){
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
    });
