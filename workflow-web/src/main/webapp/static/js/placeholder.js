   (function($) {  
      
      var placeholder = {  
        focus: function(s) {  
          s = $(s).hide().prev().show().focus();  
          var idValue = s.attr("id");  
          if (idValue) {  
            s.attr("id", idValue.replace("placeholder", ""));  
          }  
          var clsValue = s.attr("class");  
          if (clsValue) {  
            s.attr("class", clsValue.replace("placeholder", ""));  
          }  
        }  
      }  
      
      //判断是否支持placeholder  
      function isPlaceholer() {  
        var input = document.createElement('input');  
        return "placeholder" in input;  
      }  
      //不支持的代码  
      if (!isPlaceholer()) {  
        $(function() {  
      
          var form = $(this);  
      
          //遍历所有文本框，添加placeholder模拟事件  
          var elements = form.find("input[type='text'][placeholder]");  
          elements.each(function() {  
            var s = $(this);  
            var pValue = s.attr("placeholder");  
            var sValue = s.val();  
            if (pValue) {  
              if (sValue == '') {  
                s.val(pValue);  
              }  
            }  
          });  
      
          elements.focus(function() {  
            var s = $(this);  
            var pValue = s.attr("placeholder");  
            var sValue = s.val();  
            if (sValue && pValue) {  
              if (sValue == pValue) {  
                s.val('');  
              }  
            }  
          });  
      
          elements.blur(function() {  
            var s = $(this);  
            var pValue = s.attr("placeholder");  
            var sValue = s.val();  
            if (!sValue) {  
              s.val(pValue);  
            }  
          });  
		  
		  //对password框的特殊处理1.创建一个text框 2获取焦点和失去焦点的时候切换  
		  var pwdField    = $(".login_passwordmain input[type=password]");  
		  var pwdVal      = pwdField.attr('placeholder');  
		  pwdField.after('<input class="login_passwordpla" type="text" value='+pwdVal+' autocomplete="off" />');  
		  var pwdPlaceholder = $('.login_passwordpla');  
		  pwdPlaceholder.show();  
		  pwdField.hide();  
			  
		  pwdPlaceholder.focus(function(){  
				pwdPlaceholder.hide();  
				pwdField.show();  
				pwdField.focus();  
		  });  
			  
		  pwdField.blur(function(){  
				if(pwdField.val() == '') {  
					pwdPlaceholder.show();  
					pwdField.hide();  
				}  
		  });
      
        });  
      }  
      window.placeholderfocus = placeholder.focus;  
    })(jQuery);  