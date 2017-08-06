
// 字符验证
jQuery.validator.addMethod("stringCheck", function(value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "只能包括中文字、英文字母、数字和下划线");

// 中文字两个字节
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for(var i = 0; i < value.length; i++){
        if(value.charCodeAt(i) > 127){
            length++;
        }
    }
    return this.optional(element) || ( length >= param[0] && length <= param[1] );
}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(1[0-9]{10})$/; // /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

// 钱
jQuery.validator.addMethod("isMoney", function(value, element) {
    return this.optional(element) || /^(\-|\+)?\d{1,16}(\.\d{1,2})?$/.test(value);
}, "整数位不超过16位，小数位不超过2位");

// 百分比
jQuery.validator.addMethod("isPercent", function(value, element) {
    return this.optional(element) || /^(\+)?\d{1,3}(\.\d{1,2})?$/.test(value) && value <=  100;
}, "填写非负数、小数位不超过2位、不超过100");

// 高精度百分比
jQuery.validator.addMethod("isPercentHighPrecision", function(value, element) {
    return this.optional(element) || /^(\+)?\d{1,3}(\.\d{1,4})?$/.test(value) && value <=  100;
}, "填写非负数、小数位不超过2位、不超过100");