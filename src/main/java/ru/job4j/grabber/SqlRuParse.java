package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SqlRuParse implements Parse {

    private final SqlRuDateTimeParser dateTimeParser;

    public SqlRuParse(SqlRuDateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) throws Exception {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i != 6; i++) {
            Document doc = Jsoup.connect(link + "/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element child = td.parent().child(1);
                if (child.text().toLowerCase().contains("java")) {
                    result.add(detail(child.getAllElements().attr("href")));
                }
            }
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
