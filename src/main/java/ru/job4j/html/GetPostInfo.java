package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetPostInfo {
    public static void main(String[] args) throws Exception {
        Document doc =
                Jsoup.connect(
                                "https://www.sql.ru"
                                        + "/forum/1325330/"
                                        + "lidy-be-fe-senior-"
                                        + "cistemnye-analitiki-qa-i-devops-moskva-do-200t")
                        .get();
        Elements message = doc.select(".msgBody");
        Elements date = doc.select(".msgFooter");
        System.out.println(message.get(1).text());
        System.out.println(date.get(0).text().substring(0, date.get(0).text().indexOf("[")).trim());

    }
}
