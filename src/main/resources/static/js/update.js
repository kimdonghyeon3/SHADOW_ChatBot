      /* 모달 이벤트 시작 */
      function openModal(){
            $('#modalBox').modal('show');
            console.log("click open");
      }
      function closeModal(){
              $('#modalBox').modal('hide');
      }

/*      $('#openModalBtn').on('click', function(){
      $('#modalBox').modal('show');
      console.log("click open");
      });
      $('#closeModalBtn').on('click', function(){
      $('#modalBox').modal('hide');
      console.log("click close");
      });
      $('#modalBox').on('show.bs.modal', function (e) {
      console.log("show.bs.modal");
      });
      $('#modalBox').on('shown.bs.modal', function (e) {
      console.log("shown.bs.modal");
      });
      $('#modalBox').on('hide.bs.modal', function (e) {
      console.log("hide.bs.modal");
      });
      $('#modalBox').on('hidden.bs.modal', function (e) {
      console.log("hidden.bs.modal");
      });*/
      /* 모달 이벤트 종료 */

      /* 패스워드 변경 시작 */
      function savePassword(form) {
        $('#password1').val($('#mpassword1').val());
        $('#password2').val($('#mpassword2').val());
        $('#modalBox').modal('hide');
      }
      /* 패스워드 변경 끝 */


    /* 이메일 중복 체크 시작 */
    var email;
    var isCheckedEmail="false";

    function SubmitForm(form) {
        var submit=0;

        $.ajax({
        url: '/isChecked',
        type: 'POST',
        data: {
            isCheckedUsername : "true",
            isChangedUsername : "false",
            isCheckedEmail : isCheckedEmail,
            isChangedEmail : (form.email.value != email)
        },
        success: function(result){

            console.log(result);
            if(result.data.email){
               form.submit();
            }else{
                if($('#success-email-msg').hasClass('d-none')!="true"){
                    $('#success-email-msg').addClass('d-none');
                }
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

    var urlPath = $(location).attr('pathname');
    function checkEmail(){
          console.log('click check email');
          email=$("#email").val();

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
                if(result.data=='same'){
                    console.log("same");
                    isCheckedEmail="true";
                    isChangedEmail="false";
                }
                else {
                    if(result.data){
                        $('#success-email-msg').removeClass('d-none');
                        $('#success-email-msg').text(result.message);
                        //console.log($('#success-email-msg').text());
                        isCheckedEmail="true";
                    }else {
                        $('#fail-email-msg').removeClass('d-none');
                        $('#fail-email-msg').text(result.message);
                        $('#email').focus();
                        isCheckedEmail="false";
                    }
                }
            },
            error: function (){
            }
          });
    }
    /* 이메일 중복 체크 끝 */