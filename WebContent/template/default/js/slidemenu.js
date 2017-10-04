/**********************************************************************************
SlideMenu 2.0
*   Copyright (C) 2002 Thomas Brattli
*   This script was released at DHTMLCentral.com
*   Visit for more great scripts!
*   This may be used and changed freely as long as this msg is intact!
*   We will also appreciate any links you could give us.
*
*   Made by Thomas Brattli
*
*Script date: 08/20/2002 (keep this date to check versions)

** Feel free to remove all comments in this file (and the HTML file)
** as long the copyright message is intact to make the files smaller.
*********************************************************************************/

/************************************************************************************
Making cross-browser objects
************************************************************************************/
function makeMenuObj(obj,nest){
	nest=(!nest) ? "":'document.'+nest+'.'
	this.evnt = document.getElementById?document.getElementById(obj):document.all?document.all[obj]:document.layers?eval(nest+'document.'+obj):0;	
	this.css = this.evnt.style?this.evnt.style:this.evnt;										
	this.ref=document.layers?this.evnt.document:document;		
	this.x=this.css.left||this.evnt.offsetLeft;
	this.y=this.css.top||this.evnt.offsetTop;		
	this.hideIt=b_hideIt; this.showIt=b_showIt; this.movey=b_movey
	this.moveIt=b_moveIt; this.moveBy=b_moveBy; this.status=0; 
	this.bgImg=b_bgImg;	this.obj = obj + "Object"; eval(this.obj + "=this"); 
	this.clipTo=b_clipTo;
	return this
}
function b_showIt(){this.css.visibility="visible"; this.status=1}
function b_hideIt(){this.css.visibility="hidden"; this.status=0}
function b_movey(y){this.y=y; this.css.top=this.y}	
function b_moveIt(x,y){this.x=x; this.y=y; this.css.left=this.x;this.css.top=this.y}
function b_moveBy(x,y){this.x=this.x+x; this.y=this.y+y; this.css.left=this.x;this.css.top=this.y}
function b_bgImg(img){
	if(document.getElementById) document.getElementById(this.id+"_img").src = img
	else if(document.layers) this.ref.layers[0].document.images[0].src = img
	else document.images[this.id+"_img"].src = img
}
function b_clipTo(t,r,b,l,w){if(document.layers){this.css.clip.top=t;this.css.clip.right=r
this.css.clip.bottom=b;this.css.clip.left=l
}else{this.css.clip="rect("+t+"px,"+r+"px,"+b+"px,"+l+"px)"; if(w){this.css.pixelWidth=this.css.width=r; this.css.pixelHeight=this.css.height=b}}}
/********************************************************************************
Initiating page, making objects..
********************************************************************************/
createSlideMenu.prototype.init = function(){
	document.write("</div>")
	this.cont=new makeMenuObj(this.name+'cont')
	this.cont.moveIt(this.menux,this.menuy)
	var yy = 0, el 	//Make top menus
	for(var j=0;j<this.topmenus.length;j++){
		el = this.menus[this.topmenus[j]]
		el.o = new makeMenuObj(this.name+"_"+el.num,this.name+"cont")
		el.o.moveIt(this.l[0].left,yy); el.top = j; el.origy = yy
		el.o.id = el.id; el.o.status=1; 
		el.o.h = this.l[0].height; el.img = this.l[0].regImage
		yy+=el.o.h + this.l[0].between; el.o.clipTo(0,this.l[0].width,el.o.h,0,1)
		if(j!=this.topmenus.length-1)	el.nexttop = this.topmenus[el.top+1] 
		this.loop(el.num,j)
	}
	this.cont.showIt(); this.cont = null; this.level = null;
}
createSlideMenu.prototype.loop = function(num,j){
	var el = this.menus[num],temp,p,h,w,y,img
	for(var i=0;i<el.childs.length;i++){
		temp = el.childs[i]
		w = this.l[temp.lev].width; h = this.l[temp.lev].height
		y = el.o.y+el.o.h + h*i + this.l[temp.lev].between*i + this.l[temp.lev].between
		temp.o = new makeMenuObj(this.name+"_"+temp.num,this.name+"cont")
		temp.o.hideIt(); temp.o.clipTo(0,w,h,0,1)
		temp.o.moveIt(this.l[temp.lev].left,y)
		el.endy =y+h; temp.origy = y
		temp.o.zIndex=temp.num
		temp.o.h = h; temp.top = j 
		temp.chnum = i; temp.o.id = temp.id 		
		if(this.useImages){ //Setting bgimage
			img=""
			if(temp.childs.length>0){ //With subs
				if(i==el.childs.length-1) img=this.l[temp.lev].subRound
				else img=this.l[temp.lev].subImg
			}else	if(i==el.childs.length-1){
				img=this.l[temp.lev].roundImg2
			}
			if(img){
				temp.o.bgImg(img); temp.img = img
			}else temp.img = this.l[temp.lev].regImage
		}
		p = this.menus[temp.parent] //Setting the next elements
		if(temp.chnum != p.childs.length-1){ //Not the last el
			temp.nextel = p.childs[temp.chnum+1].num
			temp.nexttop = temp.lev==1?p.nexttop:p.nextel
		}else{ //Last el, so we only have nexttop
			temp.nexttop = p.nextel
			if(temp.nexttop==-1) temp.nexttop = p.nexttop
		}
		if(temp.childs.length>0){
			this.loop(temp.num,j) //Recursive call
		}	
	}
}

/********************************************************************************
Moving all objects in the array after a provided array number
********************************************************************************/
createSlideMenu.prototype.moveItems = function(px,num){
	for(i=num;i<this.menus.length;i++){
		if(this.menus[i].o.status){ //Moving all visible menus
			this.menus[i].o.movey(this.menus[i].o.y+px)
		}
	}	
}
/********************************************************************************
Switch menu function.
********************************************************************************/
createSlideMenu.prototype.switchMenu = function(num){	
	if(this.going) return
	var m = this.menus[num]
	if(m.childs.length==0) return
	this.going = 1
	var y=m.o.y+ m.o.h +this.l[m.lev].between
	var clnum = m.num, olev = m.lev, onext = m.nextel
	if(this.active[0]>-1){
		var n = -1
		if(m.lev>0){
			if(this.active[m.lev]>-1) n=this.active[m.lev]
			else n=this.active[m.lev-1]
		}else n = this.active[0]
		m = this.menus[n]
		if(m.lev == 0 && olev==0) y = m.nexttop!=-1?this.menus[m.nexttop].origy:-1
		else if(clnum!=m.num){
			var ny = m.o.y + m.o.h + this.l[m.lev].between
			if(clnum>m.num && olev==m.lev) y = Math.min(ny,y)
			else	if(onext==m.num) y = Math.max(ny,y)
		}
	}
	this.moveUp(m.nextel!=-1?m.nextel:m.nexttop,num,y)
}
/********************************************************************************
Move items up
********************************************************************************/
createSlideMenu.prototype.moveUp = function(top,num,y){
	var move = 0, m, yy=0;
	if(this.active[0]>-1 && top>-1){
		m = this.menus[top]
		if(m.o.y>y){
			if((m.o.y-this.pxspeed)<y) yy = -(((m.o.y-y)))
			else yy=-this.pxspeed; this.moveItems(yy,top)
			move=1
		}
	}
	if(move) setTimeout(this.name+".moveUp("+top+","+num+","+y+")",this.timspeed)
	else{
		m = this.menus[num] 
		for(var i=m.lev;i<this.active.length;i++){ //Reset images - actives et cetera
			if(this.active[i]>-1){
				temp = this.menus[this.active[i]]
				temp.o.moveIt(temp.o.x-this.inset,temp.o.y)
				if(this.useImages) temp.o.bgImg(temp.img) 		//Change bg
				if(this.arrow) temp.ar.replaceChild(document.createTextNode("4"),temp.ar.childNodes[0])
				ch = temp.childs
				for(var j=0;j<ch.length;j++){
					ch[j].o.hideIt(); ch[j].o.movey(ch[j].origy)
				}
				this.active[i] = -1
			}
		}
		//Then open the current one, if childs
		if(m.childs.length == 0){this.going = 0; return}
		for(i=0;i<m.childs.length;i++){ //Show all
			m.childs[i].o.showIt()
			y = m.childs[i].o.y + m.childs[i].o.h
		}
		y+=this.l[m.lev].between
		if(m.lev>0 && m.chnum!=this.menus[m.parent].childs.length-1){
			yy=y; y = this.menus[m.parent].endy + this.l[m.lev].between
		}
		m.o.moveIt(m.o.x+this.inset,m.o.y) 	//Inset current item:
		if(this.useImages) m.o.bgImg(this.l[m.lev].roundImg) 	//Change bg
		this.active[m.lev] = num //Set active
		if(this.arrow) m.ar.replaceChild(document.createTextNode("6"),m.ar.childNodes[0])
		if(m.nexttop>-1 || m.nextel>-1) this.moveDown(m.nexttop,m.nextel,y,yy) //Then start moving all other elements down
		else this.going=0
	}
}
/********************************************************************************
Move items down
********************************************************************************/
createSlideMenu.prototype.moveDown = function(top,num,y,yyy){
	var m = this.menus[top],yy
	if(m && m.o.y<y){
		if((m.o.y+this.pxspeed)>y) yy = y-m.o.y
		else yy=this.pxspeed
		this.moveItems(yy,top)
		setTimeout(this.name+".moveDown("+top+","+num+","+y+","+yyy+")",this.timspeed)
	}else if(num>-1){
		this.moveDown(num,-1,yyy,0)
	}else this.going=0
}
/********************************************************************************
Slide menu object
********************************************************************************/
function createSlideMenu(name){
	document.write('<div id="'+name+'cont">') //The cont div start
	this.name = name
	this.menus = new Array()
	this.curr = 0
	this.lasttype = new Array()
	this.writesubsonly = 0
	this.level = new Array()
	this.l = new Array()
	this.topmenus = new Array()
	this.active = new Array()
	return this
}
/********************************************************************************
Function to create the objects and the divs
********************************************************************************/
createSlideMenu.prototype.makeMenu = function(type,text,lnk,target,end){
	var str="",tg="",parent,m,cl
	m = this.menus[this.curr] = new Object()
	this.lasttype[type] = this.curr; if(!lnk) lnk="#"
	m.lev = 0; m.type = type; m.childs = new Array()
	m.num = this.curr; m.parent = -1; m.nextel = -1
	m.nexttop = -1; parent = 0
	//DEBUGGING::::::
	m.text = text
	
	if(type.indexOf("sub")>-1){ 	//Find parent type
		if(type.length == 3){ //sub
			parent = "top"; m.lev = 1
		}else{
			parent = type.substr(3,1) //sub2+
			m.lev = parent; parent--
			if(parent==1){
				parent = "sub"; m.lev = 2
			}else parent = "sub"+parent
		}	
	}
  if(m.lev>=this.l.length){//Level control (same as CM4)
    var p1,p2=0; 
		if(m.lev>=this.level.length) p1=this.l[this.level.length-1];
    else p1=this.level[m.lev]; 
		this.l[m.lev]=new Array(); 
		if(!p2) p2=this.l[m.lev-1]
    if(m.lev!=0 && p1){ 
      for(i in p1){ //Copying an entire object
				if(p1[i]==null) this.l[m.lev][i]=p2[i]; 
				else this.l[m.lev][i]=p1[i]
			}
    }else{
			this.l[m.lev]=this.level[0]; 
		}
    this.level[m.lev]=p1=p2=null 
  } 
	this.active[m.lev] = -1
	if(parent){ //Find parent element
		m.parent = this.lasttype[parent]
		parent = this.menus[this.lasttype[parent]]
		parent.childs[parent.childs.length] = m
		if(this.arrow && parent.childs.length==1){str='<span class="'+this.arrow+'">4</span>'
			if(document.getElementById && document.getElementById(parent.id).childNodes){
				parent.ar = document.getElementById(parent.id).childNodes[this.useImage?1:0].childNodes[0]
				parent.ar.innerHTML = str+parent.ar.innerHTML; parent.ar=parent.ar.childNodes[0]} else this.arrow=""
		}
	}else this.topmenus[this.topmenus.length] = this.curr
	if(!this.writesubsonly || type!="top"){
		cl = this.l[m.lev].className
		m.id = id = this.name + "_" + this.curr
		str= '<div id="'+id+'" class="'+cl+'">' //main div start
		if(this.useImages){
			str+='<div class="'+this.bgClass+'">'//bgdiv
			str+='<img id="'+id+'_img" src="'+this.l[m.lev].regImage+'" />' 
			str+='</div>' //bgdiv
		}
		if(target) tg =' target="'+target+'" '
		str+='<div class="'+this.txtClass+'"><a href="'+lnk+'" '+tg+' onclick="'+this.name+'.switchMenu('+this.curr+'); if(document.getElementById) this.blur(); '
   	if(lnk=="#") str+='return false'
    str+='" class="'+this.l[m.lev].classNameA+'"> '+text+'</a><br /></div>\n'
		str+="</div>\n\n" //main div end
		document.write(str)
	}
	this.curr++
}

/********************************************************************************
Level object
********************************************************************************/
function slideMenu_makeLevel(){
  var c=this, a=arguments; 
	c.left=a[0]||0; c.width=a[1]||null; c.height=a[2]||null;
	c.between=a[3]||null; c.className=a[4]||null; c.classNameA=a[5]||null;
	c.regImage=a[6]||null; c.roundImg=a[7]||null; c.roundImg2=a[8]||null; 
	c.subImg=a[9]||null; c.subRound=a[10]||null; return c
}
/********************************************************************************
Preloading images
********************************************************************************/
function preLoadBackgrounds(){
  for(i=0;i<arguments.length;i++){
    this[i]=new Image()
    this[i].src=arguments[i]
  }
  return this
}