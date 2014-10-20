window.onload = check;

function check() {
	var forms = document.forms;
	for (var i=0; i<forms.length; ++i) {
		forms[i].onsubmit = init;
	}
}
function init() {
	var s = document.getElementById("queryText");
	
	if (Trim(s.value)=="") {
		var forms = document.forms;
		for (var i=0; i<forms.length; ++i)
		{
			forms[i].action = "/aesop/index.jsp";
		}
	} else {
	}
	return true;
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