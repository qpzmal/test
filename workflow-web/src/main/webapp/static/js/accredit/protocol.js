/**
 * Created by zhanglei on 2017/1/5.
 */
    //点击查询按钮查询授权方
    $("#query").click(function () {
        $.ajax({
            type: "POST",
            url: "/accredit/queryfranchisor",
            async : "false	",
            dataType:"json",
            success: function (data) {
                $("#franchisorTable").text("");
                $("#franchisorTable").append("<tr><th >请选择</th><th>授权方名称</th><th>授权方编号</th></tr>");
                for(var i in data){
                    $("#franchisorTable").append("<tr><td><input name='franchisorNameTable'type='radio' value='"+data[i].id+"'/></td><td>"+data[i].name+"</td><td>"+data[i].id+"</td></tr>");
                }
            },
            error: function () {
                alert("服务器错误，请稍后再试");
            }
        })
        $(".mask").show();
        $(".popup").show();
    })
$(".mask").click(function () {
    $(".mask").hide();
    $(".popup").hide();
})
    //点击完成按钮重新给授权方复制（名称&id）
    $(".submit-end").click(function () {
        var id = "";
        $("[name='franchisorNameTable']").each(function(){
            if($(this).prop("checked") == true){
                id = $(this).val();
            }
        })

        if(id != null && id != ""){
            $.ajax({
                type: "POST",
                url: "/accredit/queryfranchisorbyid",
                async : "false	",
                data: {id : id},
                dataType:"json",
                success: function (data) {
                    if(data != null ){
                        $("#franchisorNumber").val(data.id);
                        $("#franchisorName").val(data.name);
                    }
                },
                error: function () {
                    alert("服务器错误，请稍后再试");
                }
            })
        }

        $(".mask").hide();
        $(".popup").hide();
    })

    //上传图片
    function upload() {
        var filename = $("#uploadFile").val();
        if(filename.length == 0){
            alert('请选择要上传的图片');
            return;
        }
        $.ajaxFileUpload({
            url:  '/fileupload/imgfileupload', //服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'uploadFile', //文件上传域的ID
            dataType: 'text', //返回值类型 一般设置为json
            enctype:'multipart/form-data',//注意一定要有该参数
            success: function (data)  //服务器成功响应处理函数
            {
                alert("上传成功");
                var filename = data.split(",");
                $("#img").attr("href","/upload/"+filename[0]);
                $("#ProtocolImg").val("/upload/"+filename[0]);
                $("#img").text(filename[1]);

            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        })
    }

    function saveProtocol() {
        var number = new RegExp("^[0-9]*$");
        var decimals = new RegExp("^[0-9]+\.?[0-9]*$");
        if($("#franchisorName").val().length == 0){
            alert("请选择授权方");
            return ;
        }
        if(!number.test($("#onlineProportion").val())){
            alert("在线分成比例请输入数字类型");
            return ;
        }
        if(!number.test($("#nolineProportion").val())){
            alert("无线分成比例请输入数字类型");
            return ;
        }
        if(!number.test($("#wordCount").val())){
            alert("保底字数请输入数字类型");
            return ;
        }
        if(!decimals.test($("#money").val())){
            alert("保底(支付)金额请输入数字或小数类型");
            return ;
        }
        if(!decimals.test($("#price").val())){
            alert("千字价格请输入数字或小数类型");
            return ;
        }


        $.ajax({
            type: "POST",
            url: "/accredit/saveprotocol",
            data: $("form").serialize(),
            dataType:"json",
            success: function (data) {
                if (data.success) {
                    alert(data.msg);
                    window.location.href = "/accredit/protocollist";
                } else {
                    alert(data.msg);
                }
            },
            error: function () {
                alert("服务器错误，请稍后再试");
            }
        })
    }

    function cancelProtocol(){
        window.location.href = "/accredit/protocollist";
    }


