package com.app.shared;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */



public class ApplicationPaging {
	static long totalpage;
	static long currentpage;
	private static int maxitemperpage = ApplicationConstant.MAX_ITEM_PER_PAGE;
	private static long maxpagepersection=10;
	
	public static String generatePaging(int start,long total) {
		total = total - 1;
		if (total < 0) total = 0;
		String pager = "";
		totalpage = (total+maxitemperpage)/maxitemperpage;
		currentpage = (start+maxitemperpage)/maxitemperpage;
		pager = "Page <b>"+currentpage+"</b> of "+totalpage+" pages.";
		return pager;
	}
	
	public static String generatePagingItem(int start,long total) {
		total = total - 1;
		if (total < 0) total = 0;
		long prev=0;
		String pagerItem = "";
		totalpage = (total+maxitemperpage)/maxitemperpage;
		long last=(totalpage-1)*maxitemperpage;
		currentpage = (start+maxitemperpage)/maxitemperpage;
		if (start > 0) prev = start - maxitemperpage;
		long next = start + maxitemperpage;
		if (start >= maxitemperpage) {
			pagerItem = pagerItem +"<a href=javascript:page("+0+","+maxitemperpage+")>First</a>";
		} else {
			pagerItem = pagerItem +"First";
		}
		pagerItem = pagerItem + "&nbsp;|&nbsp;";
		if (start >= maxitemperpage) {
			pagerItem = pagerItem +"<a href=javascript:page("+prev+","+maxitemperpage+")>Prev</a>";
		} else {
			pagerItem = pagerItem +"Prev";
		}
		pagerItem = pagerItem + "&nbsp;|&nbsp;";
		long z;
		long sectionstart = currentpage - maxpagepersection/2;
		if (sectionstart+maxpagepersection-1 > totalpage) sectionstart = sectionstart - (sectionstart+maxpagepersection-1-totalpage);		      														      				     					
		if (sectionstart < 1) sectionstart = 1;
		for (z=sectionstart; (z<sectionstart+maxpagepersection) && (z<=totalpage); z++) {
			if (z == currentpage) {
				pagerItem = pagerItem + "<b>" + currentpage + "</b>";
			} else {
				pagerItem = pagerItem + "<a href=javascript:page("+(z-1)*maxitemperpage+","+maxitemperpage+")>"+z+"</a>";
			}
			pagerItem = pagerItem + "&nbsp;";
		}
		pagerItem = pagerItem + "|&nbsp;";
		if (currentpage < totalpage) {
			pagerItem = pagerItem + "<a href=javascript:page("+next+","+maxitemperpage+")>Next</a>";
		} else {
			pagerItem = pagerItem + "Next";
		}
		pagerItem = pagerItem + "&nbsp;|&nbsp;";
		if (currentpage < totalpage) {
			pagerItem = pagerItem + "<a href=javascript:page("+last+","+maxitemperpage+")>Last</a>";
		} else {
			pagerItem = pagerItem + "Last";
		}
		return pagerItem;
	}
}
