
function LRCPlayer(wmp, strLRC, container, width, height, color1, color2) {
	this.wmp = wmp;
	this.height = height;
	
	this.checkInterval = 0;
	this.transInterVal = 0;
	this.currentFocus  = -1;
	
	var ls = this.ls = this.parseLRC(strLRC);
	var div = document.createElement('div');
	div.style.cssText = "display:block;width:"+width+"px; height:" + height + "px; overflow:hidden; background:#000;";
	var lrcPanel = this.lrcPanel = document.createElement('div');
	div.appendChild(lrcPanel);
	container.appendChild(div);
	this.colorTrans = this.colorCal(color1, color2);
	this.color1 = color1;
	var span = document.createElement('div');
	span.style.cssText = "height:" + (height/2) + "px;";
	lrcPanel.appendChild(span);
	for (var i=0; i<ls.length; i++) {
		span = document.createElement('div');
		span.style.cssText = 'font-size:12px; height:16px; line-height:16px; text-align:center; overflow: hidden; white-space: nowrap; color:#' + color1;
		span.appendChild(document.createTextNode(ls[i].text));
		lrcPanel.appendChild(span);
	}
	span = document.createElement('div');
	span.style.cssText = "height:" + (height/2) + "px;";
	lrcPanel.appendChild(span);
	this.start();
}

$.extend(LRCPlayer.prototype, {
	start: function() {
		var me = this;
		!function() {
			setTimeout(arguments.callee, 128);
			me.checkPos();
		}();
	}
	, stop: function() {
		if (this.checkInterval)
			clearTimeout(this.checkInterval);
	}
	, checkPos: function() {
		try {
			var cPos = this.wmp.controls.currentPosition;
			if (isNaN(cPos)) return;
			if (this.currentFocus<this.ls.length-1 && cPos > this.ls[this.currentFocus+1].pos) {
				this.focusThis(this.currentFocus+1);
			} else if (this.currentFocus>-1 && cPos<this.ls[this.currentFocus].pos) {
				this.focusThis(this.currentFocus-1);
			}
			if (this.currentFocus >= this.ls.length-1) return;
			var pos = this.ls[this.currentFocus].pos;
			var nPos = this.ls[this.currentFocus+1].pos;
			var lrct = (cPos - pos)/(nPos - pos);
			lrct = Math.max(Math.min(lrct, 1), 0);
			this.lrcPanel.parentNode.scrollTop = (this.currentFocus+lrct) * 16;
		} catch(e) { }
	}
	, focusThis: function(index) {
		if (this.transInterVal) {
			clearTimeout(this.transInterVal);
		}
		if (this.currentFocus > -1) {
			this.lrcPanel.childNodes[this.currentFocus+1].style.color = '#'+this.color1;
		}
		this.currentFocus = index;
		var me = this;
		var i = 0;
		!function() {
			if (i>=8) return;
			me.lrcPanel.childNodes[me.currentFocus+1].style.color = '#' + me.colorTrans[i++];
			me.transInterVal = setTimeout(arguments.callee, 128);
		}();
	}

	, colorCal: function(c1, c2) {
		var oC2 = c2;
		c1 = parseInt(c1, 16); c2 = parseInt(c2, 16);
		var r1 = c1 >> 16, g1 = (c1 >> 8) & 0xff; b1 = c1 & 0xff;
		var r2 = c2 >> 16, g2 = (c2 >> 8) & 0xff; b2 = c2 & 0xff;
		var cs = [], d1 = r2 - r1, d2 = g2 - g1, d3 = b2 - b1, r, g, b;
		for (var i=1; i<8; i++) {
			r = r1+parseInt(Math.round(d1*i*0.125, 0));
			g = g1+parseInt(Math.round(d2*i*0.125, 0));
			b = b1+parseInt(Math.round(d3*i*0.125, 0));
			cs.push( ("00000"+((r<<16)|(g<<8)|b).toString(16)).slice(-6) );
		}
		return cs.concat(oC2);
	}
	
	, parseLRC: function(str) {
		var map = {'ti':'标题：', 'ar':'艺术家：', 'al':'专辑：', 'by':'歌词制作：'};
		str = str.replace(/\[(ti|ar|al|by):([^[\]]*)\]/ig, function($, $1, $2) {
			return '[00:00.00]' + (map[$1.toLowerCase()]||'') + $2;
		});
		var offset = 0;
		str = str.replace(/\[offset:(-?\d+)\]/gi, function($, $1) {
			offset = parseInt($1, 10) * 0.001;
		});
		var regex1 = /(\[[^[\]]+\])(\[[^[\]]+\])([^[\]]*?)$/m;
		var regex2 = /(\[[^[\]]+\])(\[[^[\]]+\])([^[\]]*?)$/mg;
		while (regex1.test(str)) {
			str = str.replace(regex2, "$1$3\n$2$3");
		}
		var ls = [];
		str.replace(/\[(\d+):([\d.]+)\](.*)/g, function($, $1, $2, $3) {
			ls.push({ pos:parseInt($1,10)*60+parseFloat($2,10)*1+offset, text:$3 });
		});
		ls.sort(function(a, b) {
			return a.pos-b.pos;
		});
		return ls;
	}
});




























