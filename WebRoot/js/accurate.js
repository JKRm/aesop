window.onload = check;

function check() {
	// 验证检索条件是否为空
	var forms = document.forms;
	for (var i=0; i<forms.length; ++i)
	{
		forms[i].onsubmit = init;
	}
	// 设定检索类型
	autoSelected();
	// 为检索类型添加单击事件
	document.getElementById("all").onclick = autoSelected;
	document.getElementById("document").onclick = autoSelect;
	document.getElementById("audio").onclick = autoSelect;
	document.getElementById("video").onclick = autoSelect;
	document.getElementById("other").onclick = autoSelect;
}

function init() {
	var s = document.getElementById("queryText");
	if (Trim(s.value) == "") {
	//	alert("请输入搜索条件");
		return true;
	} else {
		return true;
	}
}

function autoSelected(){
	if (document.getElementById("all").checked == true) {
		document.getElementById("document").checked = true;
		document.getElementById("audio").checked = true;
		document.getElementById("video").checked = true;
		document.getElementById("other").checked = true;
	}
}

function autoSelect() {
	if (this.checked == false) {
		document.getElementById("all").checked = false;
	} else {
		if (document.getElementById("document").checked == true
		 && document.getElementById("audio").checked == true
		 && document.getElementById("video").checked == true
		 && document.getElementById("other").checked == true) {
			document.getElementById("all").checked = true;
		}
	}
}

function LTrim(str) {
    var i;
    for(i=0;i<str.length;i++)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(i,str.length);
    return str;
}
function RTrim(str) {
    var i;
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(0,i+1);
    return str;
}
function Trim(str) {
    return LTrim(RTrim(str));
}