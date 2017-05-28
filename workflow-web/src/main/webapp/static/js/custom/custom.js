function JsUtil(){}

/*设定header版本信息下拉框内容, 产品信息在各自的页面中确定*/
JsUtil.prototype.setHeaderInfo = function(rootPath){
	var product = $("#productSel").val();
	var url = rootPath;
	if(product == 1){
		url += "/zwsc/getVersions.json";
	} else if(product == 2){
		url += "/cxb/getVersions.json";
	} else{
		$("#versionSel").html("<option value='-1'>全部</option>");
		return;
	}
	$.ajax({
		url: url,
		type: "GET",
		success: function(versions){
			var versionTxt = "<option value='-1'>全部</option>";
			for(var i=0; i<versions.length; i++){
				versionTxt += "<option value='" + versions[i] + "'>" + versions[i] + "</option>";
			}
			$("#versionSel").html(versionTxt);
		}
	});
};

$_ = jsUtil = new JsUtil();