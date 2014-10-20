// JavaScript Document
// JavaScript Document
	var arrOptions = new Array();
	var strLastValue = "";
	var theTextBox;
	var currentValueSelected = -1;
	var req;
	
	function initSuggest(){
	document.Form1.queryText.onkeyup = GiveOptions;
	};
		
	function GiveOptions()
	{
		var intKey = -1;
		if(window.event){
		intKey = window.event.keyCode;
		theTextBox = window.event.srcElement;
		} else {
			var event = arguments[0];
			intKey = event.charCode;
			theTextBox = event.target;
		}
				
		if(theTextBox.value.length == 0){
			HideTheBox();
			strLastValue = "";
			return false;
		}
		if(intKey == 13){			
			GrabHighlighted();
			theTextBox.blur();
			return false;
		}else if(intKey == 38){		
			MoveHighlight(-1);
			return false;
		}else if(intKey == 40){		
			MoveHighlight(1);
			return false;
		}
		
		if(theTextBox.value.indexOf(strLastValue)!=0||
			arrOptions.length==0||(strLastValue.length==0&&theTextBox.value.length>0)||
			(theTextBox.value.length<= strLastValue.length))
		{
			strLastValue = theTextBox.value;
			TypeAhead(theTextBox.value);
		}else
		{		
			BuildList(theTextBox.value);
		//	strLastValue = theTextBox.value;
		}
	}
	
	function createXMLHTTP() {
		var request;
		var browser = navigator.appName;
		
		if (browser == "Microsoft Internet Explorer") {
			var arrVersions = ["MSXML2.XMLHttp.5.0", "MSXML2.XMLHttp.4.0", 
			                   "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp",
			                   "Microsoft.XMLHttp"];
			for (var i = 0; i < arrVersions.length; ++i) {
				try {
					request = new ActiveXObject(arrVersions[i]);
					return request;
				} catch (exception) {
					//ºöÂÔ
				}
			}
		} else {
			request = new XMLHttpRequest();
			return request;
		}
	}

function TypeAhead(xStrText)
	{
         var url = "http://localhost:8080/aesop/suggest?param=" + encodeURI(xStrText);

         req = createXMLHTTP();

        if(req){
        	
            req.open("GET", url, true);

             req.onreadystatechange = callback;

            req.send(null);        

         }	
	}
	
	function callback() {
	
    if (req.readyState == 4) {

        if (req.status == 200) {

                 parseMessage();

        }else{

           // alert ("Not able to retrieve description" + req.statusText);

        }       

    }
    else
    {
    	
    }
   }
   
   
   function parseMessage() {
   
    var node = req.responseXML.getElementsByTagName("info");
    arrOptions = new Array();
    
    for(var i=0;i<node.length;i++)
    {
    	arrOptions[i]=decodeURI(node[i].firstChild.nodeValue);
    }
    
    BuildList(theTextBox.value);
    strLastValue = theTextBox.value;
	}


	function BuildList(theText){
	
		SetElementPosition();
		
		var inner = "";
				
		var theMatches = MakeMatches(theText);
		
		for(var i=0;i<theMatches.length;i++)
		{
		 inner +=theMatches[i];
		}
		
						
		if(theMatches.length>0)
		{	document.getElementById("spanOutput").innerHTML = inner;
		
			document.getElementById("OptionsList_0").className ="spanHighElement";
			currentValueSelected = 0;
		}
		else
		{
			HideTheBox();
		}
		
	}
		
	
	function SetElementPosition(){
	var selectedPosX = 0;
	var selectedPosY = 0;
	var theElement = document.Form1.queryText;
	var theTextBoxInt = document.Form1.queryText;
	if (!theElement) return;
	var theElemHeight = theElement.offsetHeight;
	var theElemWidth = theElement.offsetWidth;
	while(theElement != null){
	selectedPosX += theElement.offsetLeft;
	selectedPosY += theElement.offsetTop;
	theElement = theElement.offsetParent;
	}
	xPosElement = document.getElementById("spanOutput");
	xPosElement.style.left = selectedPosX;

	
	xPosElement.style.width = theElemWidth;
	xPosElement.style.top = selectedPosY + theElemHeight;
	xPosElement.style.display = "block";
}
	
		
	var countForId = 0;
	
function MakeMatches(xCompareStr)
{
	countForId = 0;
	var matchArray = new Array();
	
	for(var i=0;i<arrOptions.length;i++)
	{
	
		var regExp = new RegExp(xCompareStr,"ig");
		
		if((arrOptions[i].search(regExp))>=0)
		{			
			matchArray[matchArray.length]=CreateUnderline(arrOptions[i],xCompareStr,i);
		}
		else
		{
			continue;
		}
	}

		return matchArray;
}
		
		
	
	var undeStart = "<span class='spanMatchText'>";
	var undeEnd = "</span>";
	var selectSpanStart = "<span	 style='width:100%;display:block;' class='spanNormalElement' onmouseover='SetHighColor(this)' ";
	var selectSpanEnd ="</span>";
	
	function CreateUnderline(xStr,xTextMatch,xVal){
	selectSpanMid = "onclick='SetText(" + xVal + ")'" +
	" id='OptionsList_" +
	countForId + "' theArrayNumber='"+ xVal +"'>";
	countForId++;
	
	var regExp = new RegExp(xTextMatch,"ig");
	
	var start = xStr.search(regExp);
	
	var matchedText = xStr.substring(start,start + xTextMatch.length);
	
	var Replacestr = xStr.replace(regExp,undeStart+matchedText+undeEnd);
	
	return selectSpanStart + selectSpanMid +Replacestr+ selectSpanEnd;
	}
	
	function SetHighColor(theTextBox){
		if(theTextBox){
			currentValueSelected =
			theTextBox.id.slice(theTextBox.id.indexOf("_")+1,
			theTextBox.id.length);
		}
		for(var i = 0; i < countForId; i++){
			document.getElementById('OptionsList_' + i).className =
		'spanNormalElement';
		}
		document.getElementById('OptionsList_' +
		currentValueSelected).className = 'spanHighElement';
	}
	
	function SetText(xVal){
		theTextBox = document.Form1.queryText;
		theTextBox.value = arrOptions[xVal]; //set text value
		document.getElementById("spanOutput").style.display = "none";
		currentValueSelected = -1; //remove the selected index
	}
	
	
	function GrabHighlighted(){
		if(currentValueSelected >= 0){
		xVal = document.getElementById("OptionsList_" +
		currentValueSelected).getAttribute("theArrayNumber");
		SetText(xVal);
		HideTheBox();
		}
	}
	
	
	function HideTheBox(){
		document.getElementById("spanOutput").style.display = "none";
		currentValueSelected = -1;
	}
	
	
	function MoveHighlight(xDir){
		if(currentValueSelected >= 0){
			newValue = parseInt(currentValueSelected) + parseInt(xDir);
			if(newValue > -1 && newValue < countForId){
				currentValueSelected = newValue;
				SetHighColor (null);
			}
		}
	}
	
	function ReDraw()
	{
		BuildList(document.Form1.queryText.value);
	}