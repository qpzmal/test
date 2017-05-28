$(function() {

    $(".loginbtn").bind("click",function(){

        var uname = $.trim($("#id_uname").val());

        var passwd = $.trim($("#id_passwd").val());

        var vcode = $.trim($("#id_vcode").val());

        if(uname.length ==0) {
            $(".login_error").html("请输入用户名");
            return;
        }
        if(uname.length >30) {
            $(".login_error").html("用户名不能大于30个字符");
            return;
        }
        if(passwd.length == 0) {
            $(".login_error").html("请输入密码");
            return;
        }
        if(passwd.length < 6 ) {
            $(".login_error").html("密码长度不能少于6个字符");
            return;
        }
        if(passwd.length > 16 ) {
            $(".login_error").html("密码长度不能超过16个字符");
            return;
        }
        /*if(vcode.length == 0) {
            $(".login_error").html("请输入验证码");
            return;
        }
        if(vcode.length != 4) {
            $(".login_error").html("验证码输入不正确");
            return;
        }*/

        var options = {
            url : "/login/dologin",
            type : 'post',
            dataType:'json',

            success : function(data) {
                if (data.success) {
                    window.location.href = $("#id_loginform input[name='returnUrl']").val();
                } else {
                    $(".login_error").html("登录失败:" + data.msg);
                };
            },
            fail : function(data) {
                $(".login_error").html("登录失败:" + data.msg);
            }
        };

        $("#id_loginform").ajaxSubmit(options);

    });

})