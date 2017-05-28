$(function(){
    //日期范围限制
    var start = {
        elem: '#startTime',
        format: 'YYYY-MM-DD ',
        /*min: laydate.now(), *///设定最小日期为当前日期
        max: '2099-06-16 ', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#endTime',
        format: 'YYYY-MM-DD ',
        /*  min: laydate.now(),*/
        max: '2099-06-16 ',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
});

function Search(){
    $("#pageIndex").val(1);
    $("#cnid").val($("#channelid").val());
    $("#form1").submit();
}

function deleteone(id){

    $.ajax({
        url:"/pc/channelshare/deletechanneldatashare" ,
        type: "POST",
        dataType:"json",
        data:"id="+id,
        success: function(data){
            alert('删除成功！');
            window.location.reload();
        },
        error: function(){
            alert('删除失败');
        }
    });
}
function  updateone (id){
    window.location.href="/pc/channelshare/toUpdatechanneldata?id="+id;
}
