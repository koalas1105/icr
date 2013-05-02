package com.hisun.ics.icr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hisun.ics.icr.dao.IICRCrawlDataDAO;
import com.hisun.ics.icr.model.ICRCrawlData;

public class QueryCutomerRiskInfoContentController extends AbstractController {

	private IICRCrawlDataDAO crawlDataDAO;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ICRCrawlData crawlData= crawlDataDAO.findOneById(id);
		return new ModelAndView("QueryCutomerRiskInfo2", "crawlData", crawlData);
	}

	public IICRCrawlDataDAO getCrawlDataDAO() {
		return crawlDataDAO;
	}

	public void setCrawlDataDAO(IICRCrawlDataDAO crawlDataDAO) {
		this.crawlDataDAO = crawlDataDAO;
	}

}
