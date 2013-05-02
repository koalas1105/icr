package com.hisun.ics.icr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hisun.ics.icr.dao.IICRCrawlDataDAO;
import com.hisun.ics.icr.model.ICRCrawlData;

public class QueryCutomerRiskInfoController extends AbstractController {

	private IICRCrawlDataDAO crawlDataDAO;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		List<ICRCrawlData> crawlDatas= crawlDataDAO.findAll(name);
		return new ModelAndView("QueryCutomerRiskInfo", "crawlDatas", crawlDatas);
	}

	public IICRCrawlDataDAO getCrawlDataDAO() {
		return crawlDataDAO;
	}

	public void setCrawlDataDAO(IICRCrawlDataDAO crawlDataDAO) {
		this.crawlDataDAO = crawlDataDAO;
	}

}
