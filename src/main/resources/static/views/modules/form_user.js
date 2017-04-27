define([],function(){
	var layout = {
		type: "clean",
		rows:[
			{
				view: "toolbar",
				css: "highlighted_header header1",
				paddingX:5,
				paddingY:5,
				height:40,
				cols:[
					{
						"template": "<span class='webix_icon fa-male'></span>声音设置", "css": "sub_title2", borderless:true
					},
					{
						/*view: "button", label: "Close", width: 80*/
					}
				]
			},
			{
				view: "form",
				id: "soundSettingsForm",
				name:"soundSettingsForm",
				elementsConfig:{
					labelWidth: 120
				},
				//AudioManager.STREAM_MUSIC  音乐回放即媒体音量
				//AudioManager.STREAM_RING 铃声
				//AudioManager.STREAM_ALARM  警报
				//AudioManager.STREAM_NOTIFICATION 窗口顶部状态栏通知声
				//AudioManager.STREAM_SYSTEM  系统
				//AudioManager.STREAM_VOICECALL 通话 
				//AudioManager.STREAM_DTMF 双音多频,不是很明白什么东西 
				elements:[
					{view: "richselect", id:"soundSettingsEquipment",name:"soundSettingsEquipment", label: "设备", options:"/order/equipmentRichselect.howard",validate:webix.rules.isNotEmpty},
					{view: "richselect", id:"taskKey",name:"taskKey", label: "声音类型", value:"STREAM_SYSTEM", options:[
						{id:"STREAM_MUSIC", value: "音乐回放即媒体音量"},
						{id:"STREAM_RING", value: "铃声"},
						{id:"STREAM_ALARM", value: "警报"},
						{id:"STREAM_NOTIFICATION", value: "窗口顶部状态栏通知声"},
						{id:"STREAM_SYSTEM", value: "系统"},
						{id:"STREAM_VOICECALL", value: "通话"},
						{id:"STREAM_DTMF", value: "双音多频"}
					],validate:webix.rules.isNotEmpty},
					{ view:"slider", css: "slider3", label:"声音大小", value:"80",step:1,id:"taskValue", name:"taskValue", title: webix.template("#value#%")},
					{
						margin: 10,
						paddingX: 2,
						borderless: true,
						cols:[
							{},
							{},
							{view: "button", label: "设置声音", type: "form", click:soundSettingsHandler, align: "right"}
						]
					}

				]

			}
		]
	};
    function soundSettingsHandler(){
    	if($$('soundSettingsForm').validate()){
    		var data = $$('taskValue').getValue();
        	data=parseInt((data/100)*7);
        	var param=$$("soundSettingsForm").getValues();
        	param.taskValue=data;
        	webix.ajax("/order/sendThemeMessage.howard",param,{
        	    error:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"error", text:"发送失败" });
        	    },
        	    success:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"success", text:"发送成功" });
        	    }
        	});
    	}else{
    		webix.message({ height :500,type:"error", text:"必须选择要操作的设备 声音类型" });
    	}
    	
    }
	return { $ui: layout };

});
