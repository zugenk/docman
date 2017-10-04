<SCRIPT language=JavaScript src="template/<%=currentTemplate%>/js/JSCookMenu.js" type=text/javascript></SCRIPT>
<SCRIPT language=JavaScript src="template/<%=currentTemplate%>/js/theme.js" type=text/javascript></SCRIPT>
<SCRIPT language=JavaScript src="template/<%=currentTemplate%>/js/datepicker.js" type=text/javascript></SCRIPT>

<LINK href="template/<%=currentTemplate%>/css/theme.css" type=text/css rel=stylesheet>
<LINK href="template/<%=currentTemplate%>/css/template_css.css" type=text/css rel=stylesheet>
<script language="javascript" type="text/javascript">
		function popup(poppage,popwidth,popheight){
			var popwd=window.open(poppage,'Popup','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,copyhistory=yes,width='+popwidth+',height='+popheight+'');
			popwd.focus()
		}
</script>

<TABLE class=menubar cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <tr>
  	<td background="template/<%=currentTemplate%>/images/hbgrline.png" colspan="3" align="right"><img src="template/<%=currentTemplate%>/images/hbgr.png" border="0" alt=""></td>
  </tr>
  <TR>
    <TD class=menubackgr>
      <DIV id=myMenuID></DIV>
      <SCRIPT language=JavaScript type=text/javascript>
		var myMenu =
		[
			[null,'Home','index.jsp',null,'Home Page'],
			[null,'Login','login.jsp',null,'Login Page'],
			_cmSplit,
			[null,'Module',null,null,'ERP Module',
				['<img src="template/<%=currentTemplate%>/images/menus.png" />','Basic Module',null,null,'Basic Module Page',
  					['<img src="template/<%=currentTemplate%>/images/user.png" />','App User','appUser.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/template.png" />','Attribute','attribute.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/home.png" />','Organization','organization.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/db.png" />','Privilege','privilege.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/db.png" />','Role','role.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/help.png" />','Status','status.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/install.png" />','Uom','uom.do',null,'Basic Module'],
  					['<img src="template/<%=currentTemplate%>/images/db.png" />','Territory','territory.do',null,'Basic Module'],
  					
  				],
				['<img src="template/<%=currentTemplate%>/images/menus.png" />','Inventory Module',null,null,'Inventory Module Page',
  					['<img src="template/<%=currentTemplate%>/images/component.png" />','Delivery Order','deliveryOrder.do?',null,'Inventory Module'],
  					['<img src="template/<%=currentTemplate%>/images/add_section.png" />','Item Menu',null,null,'Inventory Module Page',
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Item Category','itemCategory.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Item Master','itemMaster.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Item Unit','itemUnit.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Grey','grey.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Grey Rib','greyRib.do',null,'Inventory Module'],
	  					//['<img src="template/<%=currentTemplate%>/images/menus.png" />','Yarn','yarn.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Jacquard','jacquard.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Rib','rib.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Dyed','dyed.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Collar','collar.do',null,'Inventory Module'],	  					
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Printing','printing.do',null,'Inventory Module'],
	  				],
	  				
  					['<img src="template/<%=currentTemplate%>/images/home.png" />','Opname Menu',null,null,'Inventory Module Page',
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Opname','opname.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Opname Audit','opnameAudit.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Opname Item','opnameItem.do',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Opname Suspect','opnameSuspect.do',null,'Inventory Module'],
	  				],
	  					
  					['<img src="template/<%=currentTemplate%>/images/install.png" />','Inventory Accounting','inventoryAccounting.do',null,'Inventory Module'],
  					['<img src="template/<%=currentTemplate%>/images/edit.png" />','Price Table','priceTable.do',null,'Inventory Module'],
  					['<img src="template/<%=currentTemplate%>/images/credits.png" />','Receipt Order','receiptOrder.do',null,'Inventory Module'],
  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Spare Part','sparePart.do',null,'Inventory Module'],
  					['<img src="template/<%=currentTemplate%>/images/globe1.png" />','Trucker Task','truckerTask.do',null,'Inventory Module'],
  					['<img src="template/<%=currentTemplate%>/images/home.png" />','Warehouse Menu',null,null,'Inventory Module Page',
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Warehouse','warehouse.do?',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Warehouse Rack','warehouseRack.do?',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Warehouse Transaction','warehouseTransaction.do?',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Item Stock','warehouseTransaction.do?action=stock',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Check In','warehouseTransaction.do?action=check_in',null,'Inventory Module'],
	  					['<img src="template/<%=currentTemplate%>/images/config.png" />','Check Out','warehouseTransaction.do?action=check_out',null,'Inventory Module'],
	  				],

  				],
			],
			
			_cmSplit,
			[null,'Help','index.jsp',null,null]
		];
		cmDraw ('myMenuID', myMenu, 'hbr', cmThemeOffice, 'ThemeOffice');
		</SCRIPT>
    </TD>
        <TD class=menubackgr align=right>&nbsp;
      </TD>
    <TD class=menubackgr align=right>&nbsp;
    Welcome, <b>
    <%
    	if(request.getSession().getAttribute("login.user") == null){
    		try{
    			//response.sendRedirect("/login.jsp");
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    	}else{
    		//com.ehanoman.erp.basic.model.AppUser user = (com.ehanoman.erp.basic.model.AppUser)request.getSession().getAttribute("login.user");
    		//out.print(user.getName());
    	}
    %>
    
    </b>&nbsp;|&nbsp;
    <A 
      style="COLOR: #333333" 
      href="login.do?action=logout">Logout</A> 
      <STRONG></STRONG>&nbsp;</TD></TR></TBODY></TABLE>