
var calMonthString = new Array("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember");
var calMonthDays = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

function drawCalendar(textDoc, calDoc, cal) {
	if (cal == null) {
		var calVal = new String(document.forms[0][textDoc].value);
		try {
			idx1 = calVal.indexOf('/');
			idx2 = calVal.indexOf('/', idx1+1);
			if (idx1 > 0 && idx2 > idx1) {
				d = calVal.substring(0, idx1);
				m = calVal.substring(idx1+1, idx2);
				y = calVal.substring(idx2+1);
			}
			cal = new Date(y, m-1, d);
		} catch (ex) {
			cal = new Date();
		}
	}

	var calNow = new Date();
	var calNowDate = -1;
	if (calNow.getFullYear() == cal.getFullYear() && calNow.getMonth() == cal.getMonth()) {
		calNowDate = calNow.getDate();
	}
	var calDate = cal.getDate();
	var calStartDay = (50+cal.getDay()-calDate) % 7;
	var calDateItr = 1;
	var calDate = cal.getDate();
	var calMonth = cal.getMonth();
	var calYear = cal.getFullYear();
	var calMaxDate = calMonthDays[calMonth];
	if (((0 == (calYear%4)) && ( (0 != (calYear%100)) || (0 == (calYear%400)))) && calMonth == 1) {
		calMaxDate = 29;
	}

	var calHTML = "<table border=0 cellspacing=1 cellpadding=0 bgcolor='#oooooo' class='maintext'>";
	calHTML += "<tr bgcolor='white'><td colspan=7><table border=0 width='100%' class='maintext'><tr><td align=center>";
	calHTML += "<select onChange='drawCalendar(\""+textDoc+"\", \""+calDoc+"\", new Date("+calYear+", this.value,"+calDate+"));' class='maintext'>";
	for (i=0; i<12; i++) {
		calHTML += "<option value='"+i+"'"+(calMonth == i ? " selected" : "")+">"+calMonthString[i]+"</option>";
	}
	calHTML += "</select>";
	calHTML += "<select class='maintext' onChange='drawCalendar(\""+textDoc+"\", \""+calDoc+"\", new Date(this.value, "+calMonth+","+calDate+"));'>";
	for (i=(calYear - 50), il=(calYear + 50); i<=il; i++) {
		calHTML += "<option value='"+i+"'"+(calYear == i ? " selected" : "")+">"+i+"</option>";
	}
	calHTML += "</select>";
	calHTML += "</td></tr></table></td></tr>";

	calHTML += "<tr bgcolor='white'><td colspan=7><table border=0 width='100%' class='maintext'><tr><td align=left>";
	calHTML += "<a href='javascript:drawCalendar_set(\""+textDoc+"\", \""+calDoc+"\", null)'>Empty</a>";
	calHTML += "</td><td align=center>";
	calHTML += "<a href='javascript:drawCalendar_cancel(\""+textDoc+"\", \""+calDoc+"\")'>Cancel</a>";
	calHTML += "</td><td align=right>";
	calHTML += "<a href='javascript:drawCalendar_set(\""+textDoc+"\", \""+calDoc+"\", new Date())'>Today</a>";
	calHTML += "</td></tr></table></td></tr>";

	calHTML += "<tr bgcolor='white'><td align='center' class='titleField'>S</td><td align='center' class='maintext'>M</td><td align='center' class='titleField'>T</td><td align='center' class='titleField'>W</td><td align='center' class='titleField'>T</td><td align='center' class='titleField'>F</td><td align='center' class='titleField'>S</td></tr>";

	calHTML += "<tr bgcolor='white'>";
	for (i=0; i<calStartDay; i++) calHTML += "<td>&nbsp;&nbsp;&nbsp;</td>";
	for (i=calStartDay; i<7; i++) {
		calHTML += "<td align=right"+(calNowDate == calDateItr ? " bgcolor=#FFCCCC" : "")+"><a style='text-decoration:none' href='javascript:drawCalendar_set(\""+textDoc+"\", \""+calDoc+"\", new Date("+calYear+", "+calMonth+", "+calDateItr+"))'>"+calDateItr+"</a>&nbsp;</td>";
		calDateItr ++;
	}
	calHTML += "</tr>";

	while (calDateItr <= calMaxDate) {
		calHTML += "<tr bgcolor='white'>";
		for (i=0; i<7; i++) {
			if (calDateItr <= calMaxDate) {
				calHTML += "<td align=right"+(calNowDate == calDateItr ? " bgcolor=#B0B0B0" : "")+"><a style='text-decoration:none'  href='javascript:drawCalendar_set(\""+textDoc+"\", \""+calDoc+"\", new Date("+calYear+", "+calMonth+", "+calDateItr+"))'>"+calDateItr+"</a>&nbsp;</td>";
				calDateItr ++;
			} else calHTML += "<td>&nbsp;&nbsp;&nbsp;</td>";
		}
		calHTML += "</tr>";
	}

	calHTML += "</table>";
	document.getElementById(calDoc).innerHTML = calHTML;
	document.getElementById(calDoc).style.display = "block";
}

function calendarToogle(textDoc, calDoc, cal) {
	if (document.getElementById(calDoc).style.display != "none") {
		document.getElementById(calDoc).style.display = "none";
		return;
	}
	date_5Fof_5Fbirth_focus = false;
	drawCalendar(textDoc, calDoc, cal);
}

function drawCalendar_set(textDoc, calDoc, cal) {
	if (cal == null) {
		document.forms[0][textDoc].value = "";
	} else {
		document.forms[0][textDoc].value = cal.getDate()+"/"+(cal.getMonth()+1)+"/"+cal.getFullYear();				
	}
	document.getElementById(calDoc).style.display = "none";
}

function drawCalendar_cancel(textDoc, calDoc) {
	document.getElementById(calDoc).style.display = "none";
}
