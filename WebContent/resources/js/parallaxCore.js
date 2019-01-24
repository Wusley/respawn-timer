var parallax = function(e) {
		$(".background").css({
			"margin-top" : Math.round(e.pageY * 0.1) * (-1),
			"margin-left" :  Math.round(e.pageX * 0.1) * (-1)
		});
		
		$(".nuvem1").css({
			"margin-top" : Math.round(e.pageY * 0.2) * (-1),
			"margin-left" :  Math.round(e.pageX * 0.2) * (-1)
		});
		
		$(".nuvem2").css({
			"margin-top" : Math.round(e.pageY * 0.4) * (-1),
			"margin-left" :  Math.round(e.pageX * 0.4) * (-1)
		});
		
		$(".nuvem3").css({
			"margin-top" : Math.round(e.pageY * 0.6) * (-1),
			"margin-left" :  Math.round(e.pageX * 0.6) * (-1)
		});
	};
	
var background = function(w) {
		var width = w;
			
		$(".cidade").css({
			"width" : width * 1.2
		});
		
		$(".nuvem").css({
			"width" : width * 1.3
		});
		
		var cidadeHeight = $(".cidade").height();
		$(".parallax-viewport").css({
			"width" : width,
			"height" : cidadeHeight
		});
		
		$(".cidade").css({
			"margin-top" : (cidadeHeight * 0.25) * (-1),
			"margin-left" : ((width * 1.2) / 2) * (-1)
		});
	};