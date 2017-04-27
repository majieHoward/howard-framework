define([],function(){
	var layout = {
		type: "clean",
		rows:[
			{
				view: "toolbar",
				css: "highlighted_header header4",
				paddingX:5,
				paddingY:5,
				height:40,
				cols:[
					{
						"template": "<span class='webix_icon fa-paint-brush'></span>定时任务", "css": "sub_title2", borderless:true
					}
				]
			},
			{
				view: "form",
				id:"performTasksForm",
				name:"performTasksForm",
				elementsConfig:{
					labelWidth: 100
				},
				elements:[
					{view: "richselect", id:"closeEquipment",name:"closeEquipment", label: "设备",validate:webix.rules.isNotEmpty, options:"/order/equipmentRichselect.howard"},
					{view: "richselect", id:"performTasksType",name:"performTasksType", label: "任务类型", value:"STREAM_SYSTEM",validate:webix.rules.isNotEmpty, options:[
						{id:"closingDevice", value: "关机"}
					]},
					/**{ view:"datepicker", label:"年/月/日", name:"start",value:new Date(), stringResult:true },
					{ view:"datepicker", type:"time",label:"时/分",value:new Date(), editable:true, name:"time",stringResult:true },**/
					{
						margin: 10,
						paddingX: 2,
						borderless: true,
						cols:[
							{},
							{},
							{view: "button", type:"form", label: "执行任务", click:performTasksHandler, align: "right"}
						]
					}

				]

			}
		]
	};
    function performTasksHandler(){
    	if($$('performTasksForm').validate()){
    		webix.ajax("/order/sendShutDownMessage.howard",$$("performTasksForm").getValues(),{
        	    error:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"error", text:"发送失败" });
        	    },
        	    success:function(text, data, XmlHttpRequest){
        	    	webix.message({ type:"success", text:"发送成功" });
        	    }
        	});
    	}else{
    		webix.message({ type:"error", text:"必须选择要操作的设备 以及任务类型" });
    	}
    }
	return { $ui: layout };

});
