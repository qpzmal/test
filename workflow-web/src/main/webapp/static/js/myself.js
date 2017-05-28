$(function () {
    //1. div展示或者隐藏
    $(".data .data-con").click(function () {
        var result = $(this).next().css("display");
        if(result === "none") {
            $(this).next().show();
            $(this).parent().siblings().children("ul").hide();
        }else {
            $(this).next().hide();
        }
    });
    // $(".data .operation>li").click(function () {
    //     $(this).css("backgroundColor", "green").siblings().css("backgroundColor", "white");
    // });
});
