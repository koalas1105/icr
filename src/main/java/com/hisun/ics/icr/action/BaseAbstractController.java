package com.hisun.ics.icr.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hisun.ics.base.MessageContext;

public class BaseAbstractController extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		MessageContext messageContext = new MessageContext(); 
		Enumeration nameEnum = arg0.getParameterNames();
		while( nameEnum.hasMoreElements() ) {
			String name = (String)nameEnum.nextElement();
			String value = arg0.getParameter(name);
			messageContext.getReqDataContext().put(name, value);
		}
		nameEnum = arg0.getAttributeNames();
		while( nameEnum.hasMoreElements() ) {
			String name = (String)nameEnum.nextElement();
			Object o = arg0.getAttribute(name);
			String value = o.toString();
			messageContext.getReqDataContext().put(name, value);
		}
		
		return null;
	}

}
