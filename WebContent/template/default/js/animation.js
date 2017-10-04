// JavaScript Document
function detectBrowser() {
	if (document.childNodes && !document.all && !navigator.taintEnabled && !accentColorName) browser='saf';
	else if (navigator.product=='Gecko') browser='nn'; //nn6+, mozilla and other gecko
	else if (document.all) browser='ie';
//browser='saf';
	return browser;
}
//eg. call detectOS('mac') if returns 0 then false (not mac)
//can also be used to detect specific browsers, eg. firefox
function detectOS(string) {
	place = navigator.userAgent.toLowerCase().indexOf(string) + 1;
	if (place>0) return true;
	else return false;
}
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}
/***********************************************
* Slides a layer horizontally.
* Automatically moves layer in desired direction depending on position values entered.
************************************************
* eg. animSlide("lyr", 80, 50)
* @param [string] myLyrSlide - the id of the LAYER to slide
* @param [int] myStartPos - initial left position of the layer (before sliding)
* @param [int] myEndPos - final left position of the layer (after sliding)
* works on ie6 and nn7.1
***********************************************/
function animSlide(myLyrSlide, myStartPos, myEndPos, myFrame){
	finished = false;		
	frame = myFrame;
	lyrSlide = myLyrSlide;
	startPos = myStartPos;
	endPos = myEndPos;
	distSlide = 10; //dist to slide img each time (5)
	timeSlide = 20; //milliseconds between each call of the function (20)
	
	if (frame=='topFrame') lyrSlideObj = parent.topFrame.document.getElementById(lyrSlide);
	else lyrSlideObj = document.getElementById(lyrSlide);

	lyrSlideObj.style.left = startPos; //set starting position
	lyrSlideObjLeftPos = parseInt(lyrSlideObj.style.left); //get numerical left position of layer
	
	if (lyrSlideObjLeftPos < endPos) //if img is currently to the left of the end position
		lyrSlideObj.style.left = lyrSlideObjLeftPos + distSlide;
	else if (lyrSlideObjLeftPos > endPos)
		lyrSlideObj.style.left = lyrSlideObjLeftPos - distSlide;
	else {
		finished = true;		
		return;
	}
		
	lyrSlideObjLeftPos = parseInt(lyrSlideObj.style.left); //get updated numerical left position of layer
	slideTimer = setTimeout("animSlide(lyrSlide, lyrSlideObjLeftPos, endPos, frame)", timeSlide);
}

function animSlideDiag(myLyrSlide, myHStartPos, myHEndPos, myVStartPos, myVEndPos){
	finished = false;		
	lyrSlide = myLyrSlide;
	HStartPos = myHStartPos;
	HEndPos = myHEndPos;
	VStartPos = myVStartPos;
	VEndPos = myVEndPos;
	distSlide = 10; //dist to slide img each time  (5)
	timeSlide = 20; //milliseconds between each call of the function (20)
	
	lyrSlideObj = document.getElementById(lyrSlide);
	lyrSlideObj.style.left = HStartPos; //set starting position
	lyrSlideObj.style.top = VStartPos;
	lyrSlideObjLeftPos = parseInt(lyrSlideObj.style.left); //get numerical left position of layer
	lyrSlideObjTopPos = parseInt(lyrSlideObj.style.top); //get numerical top position of layer

	if (lyrSlideObjLeftPos < HEndPos) {//if img is currently to the left of the end position
		lyrSlideObj.style.left = lyrSlideObjLeftPos + distSlide;
		if (lyrSlideObjTopPos < VEndPos) //if img is currently above the end position
			lyrSlideObj.style.top = lyrSlideObjTopPos + distSlide;
		else if (lyrSlideObjTopPos > VEndPos)
			lyrSlideObj.style.top = lyrSlideObjTopPos - distSlide;
	}
	else if (lyrSlideObjLeftPos > HEndPos) {
		lyrSlideObj.style.left = lyrSlideObjLeftPos - distSlide;
		if (lyrSlideObjTopPos < VEndPos)
			lyrSlideObj.style.top = lyrSlideObjTopPos + distSlide;
		else if (lyrSlideObjTopPos > VEndPos)
			lyrSlideObj.style.top = lyrSlideObjTopPos - distSlide;
	}
	else {
		finished = true;		
		return;
	}
		
	lyrSlideObjLeftPos = parseInt(lyrSlideObj.style.left); //get updated numerical left position of layer
	lyrSlideObjTopPos = parseInt(lyrSlideObj.style.top);
	slideTimer = setTimeout("animSlideDiag(lyrSlide, lyrSlideObjLeftPos, HEndPos, lyrSlideObjTopPos, VEndPos)", timeSlide);
}


function animFade(myImgFade, myStartFade, myEndFade, myFrame){
	imgFade = myImgFade;
	startFade = myStartFade;
	endFade = myEndFade;
	frame = myFrame;

	if (frame=='leftFrame') imgFadeObj = parent.leftFrame.document.getElementById(imgFade);
	else if (frame=='topFrame') imgFadeObj = parent.topFrame.document.getElementById(imgFade);
	else imgFadeObj = document.getElementById(imgFade);
	
	if (detectBrowser()=='nn') {
		imgFadeObj.style.MozOpacity = startFade/100; //set initial opacity"
		if (endFade==100) endFade=95; //dont use 100% to prevent blink bug
	}
	else if (detectBrowser()=='ie')
		imgFadeObj.filters.alpha.opacity = startFade;
		
	animFader();
}	
function animFader() {
	finished = false;		
	timeFade = 50; //milliseconds between each call of the function
	percentageFade = 5; //factor to fade out by each time
	if (startFade > endFade) percentageFade *= -1; //if fade out
	
	if (detectBrowser()=='nn') {
		if (imgFadeObj.style.MozOpacity != endFade/100) {
			imgFadeObj.style.MozOpacity=parseFloat(imgFadeObj.style.MozOpacity) + percentageFade/100;
		}
		else {
			clearTimeout(fadeTimer);
			finished = true;
			return;
		}		
	}
	else if (detectBrowser()=='ie') {
		if (imgFadeObj.filters.alpha.opacity != endFade) {	
			imgFadeObj.filters.alpha.opacity=parseInt(imgFadeObj.filters.alpha.opacity) + percentageFade;
		} 
		else {
			clearTimeout(fadeTimer);
			finished = true;
			return;
		}
	}
	
	fadeTimer = setTimeout('animFader()', timeFade);
}

// auto scroll script
var targetPos;
var scrollDist;
var currentPos;
function scrollPage(target, myTargetPos){
	targetPos = myTargetPos;
	scrollDist = 4;
	currentPos = 0;
	
	if (target=='top') {
		currentPos = 700; 
		targetPos = 0; 
		scrollDist = 20; 
		setInterval("scrollUp()",10);
	}
	else {
		if (target=='invstaccts') {targetPos = 700; scrollDist = 20;}
		else if (target=='invstacctsSaf') {targetPos = 660; scrollDist = 20;}
		//else targetPos = 10000; //ensure scroll down to bottom of page
		setInterval("scrollDown()",10)
	}
}
function scrollDown() {
	if (currentPos < targetPos) {
		currentPos += scrollDist;
		window.scroll(0, currentPos);
	}
}
function scrollUp() {
	if (currentPos > targetPos) {
		currentPos -= scrollDist;
		window.scroll(0, currentPos);
	}
}

<!-- Image fading script http://www.designplace.org. -->
<!-- adapted from tux tux@designplace.org -->
value=0
var plus=1
var minus=0
var faderR;
function fadeCycle(myImgBlink, frame) {
	clearInterval(faderR);
	imgBlink = myImgBlink;
	if (frame=='leftFrame') imgBlinkObj = parent.leftFrame.document.getElementById(imgBlink);
	else if (frame=='topFrame') imgBlinkObj = parent.topFrame.document.getElementById(imgBlink);
	else imgBlinkObj = document.getElementById(imgBlink);
	faderR = setInterval("fadeCycler()",50);
}
function fadeCycler(){
if (detectBrowser()=='nn') fadeto=95; //to prevent moz bug before MozillaSuite1.7 that causes flickering when fading to 100%
else fadeto=100;

	if (value<fadeto&&plus)
		value+=5;
	else{
		plus=0;minus=1;
	}
	
	if (value>0&&minus)
		value-=2;
	else{
		plus=1;minus=0;
	}
	imgBlinkObj.style.filter = "alpha(opacity=" + value + ")"; 
	imgBlinkObj.style.MozOpacity=(value/100);
	imgBlinkObj.style.opacity=(value/100);
	imgBlinkObj.style.KhtmlOpacity=(value/100);
}

// detailsBox draggable script
// Script Source: CodeLifter.com
// Copyright 2003
// Do not remove this header

isIE=document.all;
isNN=!document.all&&document.getElementById;
isN4=document.layers;
isHot=false;

function ddInit(e){
  topDog=isIE ? "BODY" : "HTML";
  whichDog=isIE ? document.all.detailsBox : document.getElementById("detailsBox");  
  hotDog=isIE ? event.srcElement : e.target;  
  while (hotDog.id!="detailsBox"&&hotDog.tagName!=topDog){
    hotDog=isIE ? hotDog.parentElement : hotDog.parentNode;
  }  
  if (hotDog.id=="detailsBox"){
    offsetx=isIE ? event.clientX : e.clientX;
    offsety=isIE ? event.clientY : e.clientY;
    nowX=parseInt(whichDog.style.left);
    nowY=parseInt(whichDog.style.top);
    ddEnabled=true;
    document.onmousemove=dd;
  }
}

function dd(e){
  if (!ddEnabled) return;
  whichDog.style.left=isIE ? nowX+event.clientX-offsetx : nowX+e.clientX-offsetx; 
  whichDog.style.top=isIE ? nowY+event.clientY-offsety : nowY+e.clientY-offsety;
  return false;  
}

function ddN4(whatDog){
  if (!isN4) return;
  N4=eval(whatDog);
  N4.captureEvents(Event.MOUSEDOWN|Event.MOUSEUP);
  N4.onmousedown=function(e){
    N4.captureEvents(Event.MOUSEMOVE);
    N4x=e.x;
    N4y=e.y;
  }
  N4.onmousemove=function(e){
    if (isHot){
      N4.moveBy(e.x-N4x,e.y-N4y);
      return false;
    }
  }
  N4.onmouseup=function(){
    N4.releaseEvents(Event.MOUSEMOVE);
  }
}

function hideMe(){
  if (isIE||isNN) whichDog.style.visibility="hidden";
  else if (isN4) document.detailsBox.visibility="hide";
}

function showMe(){
  if (isIE||isNN) whichDog.style.visibility="visible";
  else if (isN4) document.detailsBox.visibility="show";
}

document.onmousedown=ddInit;
document.onmouseup=Function("ddEnabled=false");