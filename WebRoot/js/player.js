!function() {
	var code = [
		'<object id="wmp"', ( !window.ActiveXObject ? ' type="application/x-ms-wmp"' : ' classid="clsid:6BF52A52-394A-11d3-B153-00C04F79FAA6"')
		, ' width="230" height="64">'
		, '<param name="autoStart" value="true" />'
		, '<param name="invokeURLs" value="false" />'
		, '<param name="playCount" value="1" />'
		, '<param name="volume" value="100" />'
		, "<param name='baseURL' value='' />"
		, "<param name='currentPosition' value='0' />"
		, "<param name='currentMarker' value='0' />"
		, "<param name='enableContextMenu' value='false' />"
		, "<param name='enableErrorDialogs' value='false' />"
		, "<param name='fullScreen' value='false' />"
		, "<param name='mute' value='false' />"
		, "<param name='rate' value='1' />"
		// , "<param name='uiMode' value='invisible' />"
		, "</object>"
	].join("");
	document.write(code);
}();

var WMPlayer = {
	play: function(URL) {
		if (!URL) {
			return this.controls.play();
		}
		this.wmp.URL = URL;
		this.play();
	}
	, stop: function() {
		this.controls.stop();
	}
	, pause: function() {
		this.controls.pause();
	}
	, pausePlay: function() {
		if (this.controls.isAvailable("play")) {
			this.play();
		} else {
			this.pause();
		}
	}
	, addEventListener: function(eventName, listener) {
		if (window.ActiveXObject) {
			return this.wmp.attachEvent(eventName, listener);
		} else {
			var wmp = this.wmp;
			var func = window["OnDS" + eventName + "Evt"];
			if (!func) window["OnDS" + eventName + "Evt"] = func = function() {
				var handle = function() {
					var funcs = arguments.callee.listeners;
					for (var i=0; i<funcs.length; i++) {
						funcs[i].apply(wmp, arguments);
					}
				};
				handle.listeners = [];
				return handle;
			}();
			func.listeners.push(listener);
		}
	}
	, removeEventListener: function(listener) {
		if (window.ActiveXObject) {
			return this.wmp.detachEvent(eventName, listener);
		} else {
			var func = window["OnDS" + eventName + "Evt"];
			if (!func) return;
			var funcs = func.listeners;
			for (var i=0; i<funcs.length; i++) {
				if (funcs[i] == listener) {
					funcs.splice(i, 1);
					break;
				}
			}
		}
	}
	, addPositionChangeListener: function(eventName, listener) {
		this.addEventListener("PositionChange", listener);
	}
	, removePositionChangeListener: function(listener) {
		this.removeEventListener("PositionChange", listener);
	}
};

$(function() {
	WMPlayer.wmp = document.getElementById('wmp');
	WMPlayer.controls = WMPlayer.wmp.controls;
})


