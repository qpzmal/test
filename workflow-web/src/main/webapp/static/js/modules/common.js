
function popMsg(msg, url){
    layer.msg(msg, {icon: 1});
    setTimeout(function(){
            window.location.href = url;
        }, 500
    );
}

function delItem(callback){
    layer.confirm('确认删除？', {
        btn: ['确认','取消'] //按钮
    }, callback, function(){
    });
}


// 查看（示例：弹出Iframe窗口）
function openPopIframePage(url){
    layer.open({
        type: 2,
        title: '确认',
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['700px', '500px'],
        content: url
    });
}