<!--
//A="block";B="none";C="hide";D="show";E="Expand";F="Collapse";
var co=new Image();co.src="StateMachine_data_003/off_button.gif";
var ex=new Image();ex.src="StateMachine_data_003/on_button.gif";
var resizeMode= false;

if(document.classes){
	document.classes.parent.all.visibility='visible';
	document.classes.child.all.visibility='visible';
	document.classes.parent.all.position='absolute';
	document.classes.child.all.position='absolute';
}
if(document.styleSheets){
	if(document.styleSheets[0].rules)document.styleSheets[0].rules[1].style.display='none';
	else if(document.styleSheets[0].cssRules)document.styleSheets[0].cssRules[1].style.display='none';
}
function a1(n){
	//alert("in a1 n"+n);
	for(i=0;i<document.layers.length;i++)if(document.layers[i].id==n) return i;
}
function a2(f){
	//alert("in a2 f"+f);
	y=document.layers[f].pageY+document.layers[f].document.height;
	x = resizeMode ? 10 : 0;
	for(i=f+1;i<document.layers.length;i++){
		l=document.layers[i];
		//alert("Layer id="+l.id);
		if(eval(l.id.indexOf("Content")==-1)){
			if(l.visibility!="hide"){
				z= resizeMode && (l.id.indexOf("Child") != -1) ? z=-x : 0;
				l.pageY=y + x +z;
				y+=l.document.height + x + z;

			}
		}
	}
	//alert("After a2");
}
function a3(){
		//alert("in a3");
	if(document.layers){
		for(i=0;i<document.layers.length;i++) if(document.layers[i].id.indexOf("Child")!=-1)document.layers[i].visibility="hide";
		a2(a1("el0Parent")); 
	}
}
function a4(n){
		//alert("in a4 n"+n);
	l=a7(n);
	l=(l.style)?l.style:l;
	i=a8(n);
	//alert("image src = "+i.src);
	//alert("id = "+i.id);
	if(document.layers){
		l.visibility=(l.visibility=='hide')?'show':'hide';
		i.src=(l.visibility=='hide')?ex.src:co.src;
		//alert("i.src "+i.src);
		a2(a1("el0Parent"));
	}else{
		l.display= (l.display=='none'||l.display=="")?'block':'none';
		i.src=(l.display=='none'||l.display=="")?co.src:ex.src;
	}
	i.alt=(i.alt=='Collapse')?'Expand':'Collapse';
}
function a5(i){
		//alert("in a5 i"+i);
	l=a7(i);
	l=(l.style)?l.style:l;
	if(document.getElementById && l.display!='block')return true;
	else if(document.layers && l.visibility=='hide')return true;
	else return false;
}
function a6(id){
		//alert("in a6 id"+id);
	a4(id);
//	for(var i=0;i<MAXMENUS;i++)if(!a5(i))a4(i);
//	Changed to allow "Expand all" option
	for(var i=0;i<MAXMENUS;i++)  a5(i);

}
function a7(i){
		//alert("in a7 i"+i);
	if(document.getElementById)return document.getElementById("el"+i+"Child");
	else if(document.all)return document.all["el"+i+"Child"];
	else if(document.layers)return eval("document.el"+i+"Child");
	else return new Object();
}
function a8(n){
		//alert("in a8 n"+n);
	if(document.getElementById)return document.getElementById("el"+n+"Parent").childNodes[1].childNodes[0];
	else if(document.all)return document.all["el"+n+"img"];
	else if(document.layers)return eval("document.el"+n+"Parent.document.el"+n+"img");
	else return new Object();
}

function a9(){
// To cater for Netscape 4 window resize bug involving layers. Redraw the tree
		//alert("in a9 ");
	var expandedArr= new Array(MAXMENUS);
	var expandedCounter=0;
	resizeMode = true;
	if(document.layers)   
	{
                                for(var i=0;i<MAXMENUS;i++) 
                                { 
			if (!a5(i)) { expandedArr[expandedCounter]=i; expandedCounter ++;};
                                }
		a3();
		for(var j=0;j<expandedCounter;++j) a6(expandedArr[j]);
	}
}
window.onload=a3;
//-->
