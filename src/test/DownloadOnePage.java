import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 下载一整个页面（包括html、css、img、js）
 */
public class DownloadOnePage {
	public static void main(String[] args) throws IOException {
		String url = "http://www.gov.cn/test/2008-07/01/content_1032304.htm";
		String siteId = DigestUtils.md5Hex(url);
		
		System.out.println(String.format("Fetching %s...", url));
		Document doc = Jsoup.parse(new URL(url), 30*1000);
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");
		Elements links = doc.select("a[href]");

		Elements http_equivs = doc.select("meta[http-equiv]");
		for (Element http_equiv : http_equivs) {
			http_equiv.attr("content", "text/html; charset=gbk");
		}

		for (Element src : media) {
        	String href = src.attr("abs:src");
    		URL url1 = new URL(href);
    		src.attr("src", "/ecr/" + siteId + url1.getFile());
    		saveContent(siteId, href);
		}

		for (Element link : imports) {
			String href = link.attr("abs:href");
			URL url1 = new URL(href);
			link.attr("href", "/ecr/" + siteId + url1.getFile());
			saveContent(siteId, href);
		}
		
		for (Element link : links) {
			link.attr("href", "#");
		}
		
		URL url1 = new URL(url); 
		String path = url1.getPath();
		if( StringUtils.isEmpty(path) || "/".equals(path)) {
			path = "/" + siteId + ".html";
		}
		File file = new File(siteId + path);
		if (file.isFile()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("html file:[" + file + "]");
		FileUtils.writeStringToFile(file, doc.toString());
	}

	private static void saveContent(String siteId, String href) throws HttpException,
			IOException {
		System.out.println("Fetching href:[" + href + "]");
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(href);
		String path = method.getPath();
		client.executeMethod(method);
		if (method.getStatusCode() != 200) {
			return;
		}
		byte[] data = method.getResponseBody();
		method.releaseConnection();
		File f = new File(siteId + path);
		if (f.isFile()) {
			f.getParentFile().mkdirs();
		}
		FileUtils.writeByteArrayToFile(f, data);
	}
}
