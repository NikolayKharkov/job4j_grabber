package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final SqlRuDateTimeParser dateTimeParser;

    public SqlRuParse(SqlRuDateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) throws Exception {
        List<Post> result = new ArrayList<>();
        Document doc = Jsoup.connect(link).get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            Element parent = td.parent();
            result.add(detail(parent.child(1).getAllElements().attr("href")));
        }
        return result;
    }

    @Override
    public Post detail(String link) throws Exception {
        Document doc = Jsoup.connect(link).get();
        Elements header = doc.select(".messageHeader");
        Elements message = doc.select(".msgBody");
        Elements date = doc.select(".msgFooter");
        return new Post(header.get(0).text(),
                link,
                message.get(1).text(),
                dateTimeParser.parse(date.get(0)
                        .text().substring(0, date.get(0).text().indexOf("[")).trim()));
    }
}
