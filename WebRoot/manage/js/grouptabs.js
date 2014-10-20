/*!
 * Ext JS Library 3.4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
    
    // create some portlet tools using built in Ext tool ids
        
    var viewport = new Ext.Viewport({
        layout:'fit',
        items:[{
            xtype: 'grouptabpanel',
    		tabWidth: 130,
    		activeGroup: 0,
    		items: [{
    			mainItem: 1,
    			items: [{
    				title: '用户详细信息',
                    layout: 'fit',
                    iconCls: 'x-icon-users',
                    tabTip: '显示用户详细信息',
                    style: 'padding: 10px;',
                    html: "<iframe width=100% height=100% src='../userlist.jsp'>"
    			}, 
                {
                    xtype: 'portal',
                    title: '概况',
                    tabTip: 'Dashboard tabtip', 
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../Back_Main.jsp'>"
                },
                {
    				title: '服务器详细信息',
                    iconCls: 'x-icon-subscriptions',
                    tabTip: '显示服务器详细信息',
                    style: 'padding: 10px;',
					layout: 'fit',
    				html: "<iframe width=100% height=100% src='../server.jsp'>"
    			}, 
    			{
    				title: '资源详细信息',
                    iconCls: 'x-icon-tickets',
                    tabTip: '显示资源详细信息',
                    style: 'padding: 10px;',
    				html: "<iframe width=100% height=100% src='../SourceList.jsp'>"		
    			}]
            }, {
                expanded: true,
                items: [{
                    title: '用户管理',
                    iconCls: 'x-icon-configuration',
                    tabTip: '用户管理',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '删除用户',
                    iconCls: 'x-icon-users',
                    tabTip: '删除用户',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../UserDel.jsp'>"
                },
                {
                    title: '添加管理员',
                    iconCls: 'x-icon-users',
                    tabTip: '添加管理员',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../regist.jsp'>"
                },
                {
                    title: '退出登录',
                    iconCls: 'x-icon-users',
                    tabTip: '退出登录',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }]
            },
            {
                expanded: true,
                items: [{
                    title: '服务器管理',
                    iconCls: 'x-icon-configuration',
                    tabTip: '服务器管理',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '服务器设置',
                    iconCls: 'x-icon-subscriptions',
                    tabTip: '服务器设置',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../serverSetting.jsp'>"
                }]
            },
            {
                expanded: true,
                items: [{
                    title: '拼音检索设置',
                    iconCls: 'x-icon-configuration',
                    tabTip: '拼音检索设置',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '拼音索引列表',
                    iconCls: 'x-icon-templates',
                    tabTip: '拼音索引列表',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../pinyinTransform.jsp'>"
                },
                {
                    title: '拼音索引自动更新',
                    iconCls: 'x-icon-templates',
                    tabTip: '拼音索引自动更新',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }
                ]
            },
            {
                expanded: true,
                items: [{
                    title: '资源管理',
                    iconCls: 'x-icon-configuration',
                    tabTip: '资源管理',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '添加本地资源',
                    iconCls: 'x-icon-tickets',
                    tabTip: '添加本地资源',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../upload.jsp'>"
                },
                {
                    title: '删除本地资源',
                    iconCls: 'x-icon-tickets',
                    tabTip: '删除本地资源',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../DelResource.jsp'>"
                },
                {
                    title: '指定服务器资源',
                    iconCls: 'x-icon-tickets',
                    tabTip: '指定服务器资源',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }]
            },{
                expanded: true,
                items: [{
                    title: '信息发布管理',
                    iconCls: 'x-icon-configuration',
                    tabTip: '信息发布管理',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '广告位设置',
                    iconCls: 'x-icon-templates',
                    tabTip: '广告位设置',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../ad.jsp'>"
                }]
            }]
		}]
    });
});
