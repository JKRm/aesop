<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>aesop后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="/aesop/manage/resources/css/ext-all.css" />
    
    <style type="text/css">
    html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
    p {
        margin:5px;
    }
    .settings {
        background-image:url(/aesop/manage/admin/shared/icons/fam/folder_wrench.png);
    }
    .nav {
        background-image:url(/aesop/manage/admin/shared/icons/fam/folder_go.png);
    }
    </style>

    <!-- GC -->
    <!-- LIBS -->
    <script type="text/javascript" src="/aesop/manage/adapter/ext/ext-base.js"></script>
    <!-- ENDLIBS -->

    <script type="text/javascript" src="/aesop/manage/ext-all.js"></script>

    <!-- EXAMPLES -->
    <script type="text/javascript" src="/aesop/manage/admin/shared/examples.js"></script>
  
    <script type="text/javascript">
    Ext.onReady(function(){
    
        // NOTE: This is an example showing simple state management. During development,
        // it is generally best to disable state management as dynamically-generated ids
        // can change across page loads, leading to unpredictable results.  The developer
        // should ensure that stable state ids are set for stateful components in real apps.
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

		var addPanel = function(btn, event) {
			var n;
			n = tabPanel.getComponent(btn.id);
			if(n) {
				tabPanel.setActiveTab(n);
				return;
			}
			n = tabPanel.add( {
				id : btn.id,
				title : btn.id,
				html : "<iframe width=100% height=100% src='/aesop/manage/admin/pages/" + btn.id + ".jsp'>",      //可能路径写的不对
				//autoLoad : '',
				closable : 'true'
			});
			tabPanel.setActiveTab(n);
		};
		
		var item1 = new Ext.Panel( {
			title : '管理员管理',
			//html : '&lt;empty panel&gt;',
			cls : 'empty',
			border: false,
			iconCls: 'settings',
			items : [ 
				new Ext.Button({
					id : 'ModifyPassword',
					text : '登录密码修改',
					width : '100%',
					listeners : {
						click : addPanel
					}
				}),
				]
		});

		
        
		var item2 = new Ext.Panel( {
			title : '服务器管理',
			//html : '&lt;empty panel&gt;',
			cls : 'empty',
			border: false,
			iconCls: 'settings',
			items : [ 
				new Ext.Button({
					id : 'serverSetting',
					text : '服务器设置',
					width : '100%',
					listeners : {
						click : addPanel
					}
				}),

				new Ext.Button({
					id : 'server',
					text : '服务器信息',
					width : '100%',
					listeners : {
						click : addPanel
					}
				})
				]
		});

		var item3 = new Ext.Panel( {
			title : '资源管理',
			//html : '&lt;empty panel&gt;',
			cls : 'empty',
			border: false,
			iconCls: 'nav',
			items : [ 
				new Ext.Button({
					id : 'SourceList',
					text : '资源信息',
					width : '100%',
					listeners : {
						click : addPanel
					}

				}),

				new Ext.Button({
					id : 'upload',
					text : '添加本地资源',
					width : '100%',
					listeners : {
						click : addPanel
					}

				}),
				new Ext.Button({
					id : 'DelResource',
					text : '删除本地资源',
					width : '100%',
					listeners : {
						click : addPanel
					}

				})

				]
		});

		var item4 = new Ext.Panel( {
			title : '拼音检索设置',
			//html : '&lt;empty panel&gt;',
			cls : 'empty',
			border: false,
			iconCls: 'settings',
			items : [ 
				new Ext.Button({
					id : 'pinyinTransform',
					text : '拼音索引列表',
					width : '100%',
					listeners : {
						click : addPanel
					}
				}),

				new Ext.Button({
					id : 'pinyinTransform',
					text : '拼音索引自动更新',
					width : '100%',
					listeners : {
						click : addPanel
					}
				})

				]
		});

		var item5 = new Ext.Panel( {
			title : '信息发布管理',
			//html : '&lt;empty panel&gt;',
			cls : 'empty',
			border: false,
			iconCls: 'nav',
			items : [ 
				new Ext.Button({
					id : 'ad',
					text : '广告位添加',
					width : '100%',
					listeners : {
						click : addPanel
					}
				}),
				new Ext.Button({
					id : 'DelAd',
					text : '广告位删除',
					width : '100%',
					listeners : {
						click : addPanel
					}
				})


				]
		});

		var data=[ 
			[1, '郝庭毅', '2009','计算机科学与技术','1'],
			[2, '张俊强', '2009','软件工程','2'],
			[3, '孙奉刚', '2009','软件工程','3'],
			[4, '寇睿明', '2009','计算机科学与技术','4'],
			[4, '李贝贝', '2009','软件工程','5'] 
		];

		var store=new Ext.data.SimpleStore({
			data:data,
			fields:["id","name","grade","major","special"]
		});

		var tabPanel = new Ext.TabPanel({
                region: 'center', // a center region is ALWAYS required for border layout
                deferredRender: false,
                activeTab: 0,     // first tab initially active
                items: [{
                    title: '欢迎',
                    closable: true,
                    autoScroll: true,
					html:"<iframe width=100% height=100% src='/aesop/manage/admin/pages/Back_Main.jsp'>"
                }]
            });



        var viewport = new Ext.Viewport({
            layout: 'border',
            items: [
            // create instance immediately
            new Ext.BoxComponent({
                region: 'north',
                height: 15, // give north and south regions a height
                autoEl: {
                    tag: 'div',
                    html:'<center><b>欢迎使用aesop后台管理系统</b></center>'
                }
            }), 
			{
                // lazily created panel (xtype:'panel' is default)
                region: 'south',
                contentEl: 'south',
                split: true,
                height: 100,
                minSize: 100,
                maxSize: 200,
                collapsible: true,
                title: '广告位预览',
                margins: '0 0 0 0'
            }, 
			{
                region: 'east',
                title: '我的团队',
                collapsible: true,
                split: true,
                width: 225, // give east and west regions a width
                minSize: 175,
                maxSize: 400,
                margins: '0 5 0 0',
                layout: 'fit', // specify layout manager for items
                items:            // this TabPanel is wrapped by another Panel so the title will be applied
                new Ext.TabPanel({
                    border: false, // already wrapped so don't add another border
                    activeTab: 0, // second tab initially active
                    tabPosition: 'bottom',
                    items: [{
                        html: '<p>A TabPanel component can be a region.</p>',
                        title: '团队标志',
                        autoScroll: true
                    },new Ext.grid.GridPanel({
						title:"团队成员",
						//height:150,
						//width:300, 
						columns:[{header:"成员姓名",dataIndex:"name"},
						{header:"年级",dataIndex:"grade"},
						{header:"专业",dataIndex:"major"},
						{header:"分工",dataIndex:"special"}],
						store:store,
						viewConfig:{
							forceFit:true
							}
						//autoExpandColumn:2
					})
				]
                })
            }, {
                region: 'west',
                id: 'west-panel', // see Ext.getCmp() below
                title: '后台管理',
                split: true,
                width: 200,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins: '0 0 0 5',
                layout: {
                    type: 'accordion',
                    animate: true
                },
                items: [item1,item2,item3,item4,item5]
            },
            tabPanel
            // in this instance the TabPanel is not wrapped by another panel
            // since no title is needed, this Panel is added directly
            // as a Container
            ]
        });
        // get a reference to the HTML element with id "hideit" and add a click listener to it 
        Ext.get("hideit").on('click', function(){
            // get a reference to the Panel that was created with id = 'west-panel' 
            var w = Ext.getCmp('west-panel');
            // expand or collapse that Panel based on its collapsed property state
            w.collapsed ? w.expand() : w.collapse();
        });
    });
    </script>
</head>
<body>
    <!-- use class="x-hide-display" to prevent a brief flicker of the content -->
    <div id="west" class="x-hide-display">
    	<a id="hideit" href="#">Toggle the west region</a>
        <p>Hi. I'm the west panel.</p>
    </div>
    <div id="props-panel" class="x-hide-display" style="width:200px;height:200px;overflow:hidden;">
    </div>
    <div id="south" class="x-hide-display">
        <p>广告位预览</p>
    </div>
</body>
</html>