var cSpeed=5;
var cWidth=110;
var cHeight=64;
var cTotalFrames=10;
var cFrameWidth=110;
	
var cImageTimeout=false;
var cIndex=0;
var cXpos=0;
var SECONDS_BETWEEN_FRAMES=0;
var animation;

function startAnimation(){
	
	clearTimeout(animation);
	animation = 0;
	
	cImageTimeout = setTimeout(function() {

		//document.getElementById('loaderImage').style.backgroundImage='url('+cImageSrc+')';
		document.getElementById('loaderImage').style.width=cWidth+'px';
		document.getElementById('loaderImage').style.height=cHeight+'px';
		
		//FPS = Math.round(100/(maxSpeed+2-speed));
		FPS = Math.round(100/cSpeed);
		SECONDS_BETWEEN_FRAMES = 1 / FPS;
		
		animation = setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES/1000);

	},0);
	
}

function continueAnimation(){
	
	cXpos += cFrameWidth;
	//increase the index so we know which frame of our animation we are currently on
	cIndex += 1;
	 
	//if our cIndex is higher than our total number of frames, we're at the end and should restart
	if (cIndex >= cTotalFrames) {
		cXpos =0;
		cIndex=0;
	}
	
	document.getElementById('loaderImage').style.backgroundPosition=(-cXpos)+'px 0';
	
	animation = setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES*1000);
}