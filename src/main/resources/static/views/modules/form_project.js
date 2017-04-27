define([],function(){
	var layout = {

		"type": "clean",
		"rows":[
			{
				view: "toolbar",
				css: "highlighted_header header2",
				paddingX:5,
				paddingY:5,
				height:40,
				cols:[
					{
						"template": "<span class='webix_icon fa-sliders'></span>切换应用", "css": "sub_title2", borderless: true
					}
				]

			},
			{
				view: "form",
				id: "switchingInterfaceForm",
				name:"switchingInterfaceForm",
				elementsConfig:{
					labelWidth: 150
				},
				elements:[
					{view: "richselect", id:"switchingInterfaceEquipment",name:"switchingInterfaceEquipment", label: "设备",validate:webix.rules.isNotEmpty, options:"/order/equipmentRichselect.howard"},
					{view: "text", label: "packageName",id :"packageNameChange", name:"packageNameChange",validate:webix.rules.isNotEmpty,value:"www.howard.com.addapplication"},
					{
						margin: 10,
						paddingX: 2,
						borderless: true,
						cols:[
							{},
							{view: "button", label: "切换",click:switchingInterfaceHandler, type: "form", align: "right",width: 80}
						]
					}
				]
			}
		]
	};
    function switchingInterfaceHandler(){
    	if($$('switchingInterfaceForm').validate()){
    		webix.ajax("/order/sendOpenOtherAppMessage.howard",$$("switchingInterfaceForm").getValues(),{
        	    error:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"error", text:"发送失败" });
        	    },
        	    success:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"success", text:"发送成功" });
        	    }
        	});
    	}else{
    		webix.message({ type:"error", text:"必须选择要操作的设备 packageName 不能为空" });
    	}
    }
	return { $ui: layout };

});
