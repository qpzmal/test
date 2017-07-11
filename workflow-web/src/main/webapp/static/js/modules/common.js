
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