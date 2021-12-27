package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

public class GetPostInfo {
    public static Post returnPost(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements header = doc.select(".messageHeader");
        Elements message = doc.select(".msgBody");
        Elements date = doc.select(".msgFooter");
        SqlRuDateTimeParser ldt = new SqlRuDateTimeParser();
        return new Post(header.get(0).text(),
                url,
                message.get(1).text(),
                ldt.parse(date.get(0).text().substring(0, date.get(0).text().indexOf("[")).trim()));
    }
}