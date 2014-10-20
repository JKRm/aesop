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
    				title: '�û���ϸ��Ϣ',
                    layout: 'fit',
                    iconCls: 'x-icon-users',
                    tabTip: '��ʾ�û���ϸ��Ϣ',
                    style: 'padding: 10px;',
                    html: "<iframe width=100% height=100% src='../userlist.jsp'>"
    			}, 
                {
                    xtype: 'portal',
                    title: '�ſ�',
                    tabTip: 'Dashboard tabtip', 
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../Back_Main.jsp'>"
                },
                {
    				title: '��������ϸ��Ϣ',
                    iconCls: 'x-icon-subscriptions',
                    tabTip: '��ʾ��������ϸ��Ϣ',
                    style: 'padding: 10px;',
					layout: 'fit',
    				html: "<iframe width=100% height=100% src='../server.jsp'>"
    			}, 
    			{
    				title: '��Դ��ϸ��Ϣ',
                    iconCls: 'x-icon-tickets',
                    tabTip: '��ʾ��Դ��ϸ��Ϣ',
                    style: 'padding: 10px;',
    				html: "<iframe width=100% height=100% src='../SourceList.jsp'>"		
    			}]
            }, {
                expanded: true,
                items: [{
                    title: '�û�����',
                    iconCls: 'x-icon-configuration',
                    tabTip: '�û�����',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: 'ɾ���û�',
                    iconCls: 'x-icon-users',
                    tabTip: 'ɾ���û�',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../UserDel.jsp'>"
                },
                {
                    title: '��ӹ���Ա',
                    iconCls: 'x-icon-users',
                    tabTip: '��ӹ���Ա',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../regist.jsp'>"
                },
                {
                    title: '�˳���¼',
                    iconCls: 'x-icon-users',
                    tabTip: '�˳���¼',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }]
            },
            {
                expanded: true,
                items: [{
                    title: '����������',
                    iconCls: 'x-icon-configuration',
                    tabTip: '����������',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '����������',
                    iconCls: 'x-icon-subscriptions',
                    tabTip: '����������',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../serverSetting.jsp'>"
                }]
            },
            {
                expanded: true,
                items: [{
                    title: 'ƴ����������',
                    iconCls: 'x-icon-configuration',
                    tabTip: 'ƴ����������',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: 'ƴ�������б�',
                    iconCls: 'x-icon-templates',
                    tabTip: 'ƴ�������б�',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../pinyinTransform.jsp'>"
                },
                {
                    title: 'ƴ�������Զ�����',
                    iconCls: 'x-icon-templates',
                    tabTip: 'ƴ�������Զ�����',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }
                ]
            },
            {
                expanded: true,
                items: [{
                    title: '��Դ����',
                    iconCls: 'x-icon-configuration',
                    tabTip: '��Դ����',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '��ӱ�����Դ',
                    iconCls: 'x-icon-tickets',
                    tabTip: '��ӱ�����Դ',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../upload.jsp'>"
                },
                {
                    title: 'ɾ��������Դ',
                    iconCls: 'x-icon-tickets',
                    tabTip: 'ɾ��������Դ',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../DelResource.jsp'>"
                },
                {
                    title: 'ָ����������Դ',
                    iconCls: 'x-icon-tickets',
                    tabTip: 'ָ����������Դ',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }]
            },{
                expanded: true,
                items: [{
                    title: '��Ϣ��������',
                    iconCls: 'x-icon-configuration',
                    tabTip: '��Ϣ��������',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='" + "VIP_List.jsp'>"
                }, {
                    title: '���λ����',
                    iconCls: 'x-icon-templates',
                    tabTip: '���λ����',
                    style: 'padding: 10px;',
                    html:"<iframe width=100% height=100% src='../ad.jsp'>"
                }]
            }]
		}]
    });
});
