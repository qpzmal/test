/**
 * Created by zhanglei on 2017/1/9.
 */
$(function () {

    //图书版权信息导出
    $(".export-btn").click(function () {
        var ids = "";
        $("[name='id']").each(function(){
            if($(this).prop("checked") == true){
                ids += ","+$(this).val();
            }
        })
        ids = ids.substring(1);
        if(ids == ""){
            alert("请选择导出内容");
            return ;
        }
        window.location.href="/bookcopyright/exportExcel?ids="+ids+"";
    })


    //图书版权信息导入
    $("#import").click(function(){
        var filename = $("#bookFile").val();
        if(filename.length == 0){
            alert('请选择要导入的文件');
            return;
        }
        var index =filename.lastIndexOf(".");
        var subffix = filename.substring(index);
        if(subffix.toUpperCase()!='.XLS' && subffix.toUpperCase()!='.XLSX'){
            alert('请选择Excel类型的文档');
            return;
        }
        $("#uploadForm").submit();
    });


    $(".check-all").click(function () {
        var isChecked = $(this).prop("checked");
        $(".checkboxes input").prop("checked", isChecked);
    });

    $(".checkboxes input").click(function () {
        var all = $(".checkboxes input").length;
        var ck = $(".checkboxes input:checked").length;
        if (ck === all) {
            $(".check-all").prop("checked", true);
        } else {
            $(".check-all").prop("checked", false);
        }
    });

    $(".ck-all").click(function () {
        var isChecked = $(this).prop("checked");
        $(".table-bordered td input").prop("checked", isChecked);
    })
});