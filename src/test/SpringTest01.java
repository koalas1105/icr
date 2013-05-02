import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hisun.ics.icr.dao.CrawlDataImpl;
import com.hisun.ics.icr.dao.IICRCrawlDataDAO;
import com.hisun.ics.icr.dao.IICRRelationPeoplesDAO;
import com.hisun.ics.icr.model.ICRRelationPeople;
import com.hisun.ics.icr.model.ICRRelationPeoples;

public class SpringTest01 {
	public static void main01(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application-service.xml");
		IICRRelationPeoplesDAO dao = (IICRRelationPeoplesDAO) context
				.getBean("ICRRelationPeoplesDAO");
		ICRRelationPeoples peoples = new ICRRelationPeoples();
		peoples.setName("ttt001");
		peoples.setLastUpdateTime("20130502105233");
		ICRRelationPeople people = new ICRRelationPeople();
		people.setName("test01");
		people.setRelationType("0");
		peoples.addRelationPeople(people);
		people = new ICRRelationPeople();
		people.setName("test03");
		people.setRelationType("0");
		peoples.addRelationPeople(people);
		dao.save(peoples);
		peoples = dao.findOne("ttt001");
		System.out.println(peoples.getRelationPeoples());
		System.out.println(peoples);
	}

	public static void main02(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application-service.xml");
		IICRCrawlDataDAO dao = (IICRCrawlDataDAO) context
				.getBean("ICRCrawlDataDAO");
		System.out.println(dao.findOne("ttt001"));
	}

	public static void main03(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"application-service.xml");
		CrawlDataImpl crawlData = (CrawlDataImpl) context.getBean("crawlData");
		crawlData.work();
	}

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.sina.com.cn").get();
	}
}
