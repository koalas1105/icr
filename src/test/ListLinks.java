import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
	public static void main(String[] args) throws IOException {
		//        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
		//        String url = args[0];
		String url = "http://www.cnblogs.com/shapherd/archive/2011/03/16/crawler_cmp.html";
		print("Fetching %s...", url);

		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		print("\nMedia: (%d)", media.size());
		for (Element src : media) {
        	String href = src.attr("abs:src");
    		URL url1 = new URL(href);
    		src.attr("href", url1.getFile());
    		saveContent(href);
    		
			if (src.tagName().equals("img")) {
				print(" * %s: <%s> %sx%s (%s)", src.tagName(), src
						.attr("abs:src"), src.attr("width"),
						src.attr("height"), trim(src.attr("alt"), 20));
			} else {
				print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			}
		}

		print("\nImports: (%d)", imports.size());
		for (Element link : imports) {
			String href = link.attr("abs:href");
			URL url1 = new URL(href);
			link.attr("href", url1.getFile());
			saveContent(href);
			print(" * %s <%s> (%s)", link.tagName(), href, link.attr("rel"));
		}

		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			String href = link.attr("abs:href");
			if (StringUtils.isNotEmpty(href)) {
				print(" * a: <%s>  (%s)", href, trim(link.text(), 35));
			}
		}
		System.out.println(doc.toString());
	}

	private static void saveContent(String href) throws HttpException,
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
		File f = new File("./logs" + path);
		if (f.isFile()) {
			f.getParentFile().mkdirs();
		}
		FileUtils.writeByteArrayToFile(f, data);
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}
}
