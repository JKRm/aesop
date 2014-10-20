function initVoice(){
	var voiceButton = document.getElementById("voice");
	voiceButton.onclick = listen;
	}
function listen()
	{
		document.getElementById("wait").src="images/loading.gif";
         var url = "/aesop/speak";
         
         if (window.XMLHttpRequest) {

             req = new XMLHttpRequest();
             
         }else if (window.ActiveXObject) {

             req = new ActiveXObject("Microsoft.XMLHTTP");

         }

        if(req){
        	
            req.open("GET", url, true);

             req.onreadystatechange = voiceCallback;

            req.send(null);        

         }	
	}
	
	function voiceCallback() {
	
    if (req.readyState == 4) {

        if (req.status == 200) {
        	var str = req.responseText;
        	document.getElementById("queryText").value = decodeURI(str);
        	document.getElementById("wait").src="images/blank.gif";
        }else{

       //     alert ("Not able to retrieve description" + req.statusText);
            document.getElementById("wait").src="images/blank.gif";

        }       

    }
    else
    {
    	
    }
   }


