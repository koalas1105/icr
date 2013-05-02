package com.hisun.ics.icr.dao;

import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hisun.ics.icr.model.ICRCrawlData;
import com.hisun.ics.icr.model.ICRCrawlDataContent;
import com.hisun.ics.icr.model.ICRRelationPeoples;

public class CrawlDataImpl {
	private int tmOut = 30;
	private IICRRelationPeoplesDAO relationPeoplesDAO;
	private IICRCrawlDataDAO crawlDataDAO;
	private Logger log = Logger.getLogger(CrawlDataImpl.class);

	public int getTmOut() {
		return tmOut;
	}

	public void setTmOut(int tmOut) {
		this.tmOut = tmOut;
	}

	public IICRRelationPeoplesDAO getRelationPeoplesDAO() {
		return relationPeoplesDAO;
	}

	public void setRelationPeoplesDAO(IICRRelationPeoplesDAO relationPeoplesDAO) {
		this.relationPeoplesDAO = relationPeoplesDAO;
	}

	public IICRCrawlDataDAO getCrawlDataDAO() {
		return crawlDataDAO;
	}

	public void setCrawlDataDAO(IICRCrawlDataDAO crawlDataDAO) {
		this.crawlDataDAO = crawlDataDAO;
	}

	public void work() {
		List<ICRRelationPeoples> peoples = relationPeoplesDAO.findAll();
		for (int i = 0; i < peoples.size(); i++) {
			for (int j = 0; j < peoples.get(i).getRelationPeoples().size(); j++) {
				try {
					doWork(peoples.get(i).getName(), peoples.get(i)
							.getRelationPeoples().get(i).getName());
				} catch (Exception e) {
					log.error(e, e);
				}

			}
		}
	}

	public void doWork(String name, String queryStr) throws Exception {
		log.info(name + ":" + queryStr);
		String queryStr1 = URLEncoder.encode(queryStr, "UTF-8");
		Document doc = Jsoup
				.connect(
						"http://www.baidu.com/s?wd="
								+ queryStr1
								+ "&rsv_bp=0&ch=&tn=baidu&bar=&rsv_spt=3&ie=utf-8&rsv_sug3=3&rsv_sug=0&rsv_sug1=1&rsv_sug4=201&inputT=2258&rn=100")
				.get();
		Elements records = doc.select("#content_left table tbody tr td");
		for (int i = 0; i < records.size(); i++) {
			Element tt = records.get(i);
			Elements titles = tt.select("h3");
			for (int j = 0; j < titles.size(); j++) {
				Elements t2 = titles.get(j).getElementsByTag("a");
				String title = t2.get(0).html();
				String href = t2.get(0).attr("href");
				String content = "";
				try {
					log.info("access url:[" + href + "]");
					content = getContent(href);
					ICRCrawlData entity = new ICRCrawlData();
					entity.setName(name);
					entity.setKeyWords(queryStr);
					entity.setUrl(href);
					entity.setTitle(title);
					entity.setContent(content);
					crawlDataDAO.save(entity);
				} catch (Exception e) {
					log.error("process url:[" + href + "] failure, "
							+ e.getMessage());
				}
			}
		}
	}

	public String getContent(String url) {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				tmOut * 1000);
		client.getHttpConnectionManager().getParams()
				.setSoTimeout(tmOut * 1000);
		GetMethod method = new GetMethod(url);
		try {
			client.executeMethod(method);
			if (method.getStatusCode() != 200) {
				log
						.info("access url:[" + url + "] failure, "
								+ method.getStatusCode() + ":"
								+ method.getStatusText());
				return "";
			}
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			log.error("access url:[" + url + "] failure, " + e.toString());
			return "";
		} finally {
			method.releaseConnection();
		}
	}
}
