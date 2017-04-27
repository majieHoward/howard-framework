define([],function(){
	var layout = {

		type: "clean",
		rows:[
			{
				view: "toolbar",
				css: "highlighted_header header3",
				paddingX:5,
				paddingY:5,
				height:40,
				cols:[
					{
						"template": "<span class='webix_icon fa-star-o'></span>更新安装APK", "css": "sub_title2", borderless:true
					},
					{
						/*view: "button", label: "Close", width: 80*/
					}
				]
			},
			{
				view: "form",
				id:"installEquipmentForm",
				name:"installEquipmentForm",
				elementsConfig:{
					labelWidth: 150
				},
				elements:[
					{view: "richselect", id:"installEquipment",name:"installEquipment", label: "设备",validate:webix.rules.isNotEmpty, options:"/order/equipmentRichselect.howard"},
					{view: "text", label: "apk名称" ,id :"apkName", name:"apkName",validate:webix.rules.isNotEmpty,value:"addapplication.apk"},
					{view: "text", label: "apk下载地址",id :"apkNetworkAddress", name:"apkNetworkAddress",value:"http://192.168.1.4:8080/advertisement/app-debug.apk",validate:webix.rules.isNotEmpty},
					{view: "text", label: "versionCode",id :"versionCode", name:"versionCode"},
					{view: "text", label: "versionName",id :"versionName", name:"versionName"},
					{view: "text", label: "packageName",id :"packageName", name:"packageName",validate:webix.rules.isNotEmpty,value:"www.howard.com.addapplication"},
					{
						margin: 10,
						paddingX: 2,
						borderless: true,
						cols:[
							{},
							{},
							{view: "button", label: "安装", type: "form", click:intallApkhandler,align: "right"}
						]
					}
				]

			}
		]
	};
	function intallApkhandler(){
	    if ($$('installEquipmentForm').validate()){
	    	webix.ajax("/order/sendSilentInstallMessage.howard",$$("installEquipmentForm").getValues(),{
        	    error:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"error", text:"发送失败" });
        	    },
        	    success:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"success", text:"发送成功" });
        	    }
        	});
	    }else{
			webix.message({ type:"error", text:"必须选择要操作的设备 apk名称 apk下载地址 packageName 不能为空" });
		}
	}
	return { $ui: layout };

});
