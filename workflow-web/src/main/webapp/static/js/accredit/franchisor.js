/**
 * Created by zhanglei on 2017/1/5.
 */
function saveFranchisor() {
    var phone = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if($("#name").val().length == 0 ){
        alert("请输入协议方名称");
        return ;
    }
    if($("#franchisorType").val().length == 0 ){
        alert("请选择授权方类型");
        return ;
    }
    if($("#author").val().length == 0 ){
        alert("请输入作者署名");
        return ;
    }
    if($("#idType").val().length == 0 ){
        alert("请选择证件类型");
        return ;
    }
    if($("#idNumber").val().length == 0 ){
        alert("请输入证件号码");
        return ;
    }
    if($("#address").val().length == 0 ){
        alert("请输入联系地址");
        return ;
    }
    if($("#email").val().length == 0 ){
        alert("请输入联系邮编");
        return ;
    }
    if($("#tel").val().length == 0 ){
        alert("请输入联系电话");
        return ;
    }
    if(!phone.test($("#tel").val())){
        alert('请输入有效的手机号码！');
        return ;
    }

    if($("#franchisorClassify").val().length == 0 ){
        alert("请选择授权方分类");
        return ;
    }


    $.ajax({
        type: "POST",
        url: "/accredit/savefranchisor",
        data: $("form").serialize(),
        dataType:"json",
        success: function (data) {
            if (data.success) {
                alert(data.msg);
                window.location.href = "/accredit/franchisorlist";
            } else {
                alert(data.msg);
            }
        },
        error: function () {
            alert("服务器错误，请稍后再试");
        }
    })
}

function cancelFranchisor(){
    window.location.href = "/accredit/franchisorlist";
}
