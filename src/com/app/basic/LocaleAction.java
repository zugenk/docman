package com.app.basic;

 
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.*;

/*
import com.app.docmgr.model.*;
import com.app.docmgr.service.*;
*/

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public final class LocaleAction extends Action {
	private static Logger log = Logger.getLogger("com.app.basic.LocaleAction");	
	
	/**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @return Action to forward to
     * @exception Exception if an input/output error or servlet exception occurs
     */
    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {

	    // Extract attributes we will need
	    HttpSession session = request.getSession();
	    Locale locale = getLocale(request);
	
	    String language = null;
	    String country = null;
	
	    try {
	       language = (String) PropertyUtils.getSimpleProperty(form, "language");
	       country = (String) PropertyUtils.getSimpleProperty(form, "country");
        } catch (Exception e) {
           log.error(e.getMessage(), e);
        }

        if ((language != null && language.length() > 0) &&
            (country != null && country.length() > 0)) {
           locale = new java.util.Locale(language, country);
        } else if (language != null && language.length() > 0) {
           locale = new java.util.Locale(language, "");
	    }

        session.setAttribute(Globals.LOCALE_KEY, locale);
        return mapping.findForward("success");
    }

}

