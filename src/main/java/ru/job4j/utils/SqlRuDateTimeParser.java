package ru.job4j.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, Integer> MONTHS = Map.ofEntries(
            Map.entry("янв", 1),
            Map.entry("фев", 2),
            Map.entry("мар", 3),
            Map.entry("апр", 4),
            Map.entry("май", 5),
            Map.entry("июн", 6),
            Map.entry("июл", 7),
            Map.entry("авг", 8),
            Map.entry("сен", 9),
            Map.entry("окт", 10),
            Map.entry("ноя", 11),
            Map.entry("дек", 12));

    @Override
    public LocalDateTime parse(String parse) {
        LocalDateTime result;
        String date = parse.split(",")[0].trim();
        String time = parse.split(",")[1].trim();
        switch (date) {
            case "вчера":
                result = returnDateTime(time).minusDays(1);
                break;
            case "сегодня":
                result = returnDateTime(time);
                break;
            default:
                String[] tmp = date.split(" ");
                result = LocalDateTime.of(
                        Integer.parseInt(tmp[2]) + 2000,
                        MONTHS.get(tmp[1]),
                        Integer.parseInt(tmp[0]),
                        Integer.parseInt(time.split(":")[0]),
                        Integer.parseInt(time.split(":")[1]));
        }
        return result;
    }

    private static LocalDateTime returnDateTime(String time) {
        return LocalDateTime.now()
                .withHour(Integer.parseInt(time.split(":")[0]))
                .withMinute(Integer.parseInt(time.split(":")[1]))
                .truncatedTo(ChronoUnit.MINUTES);
    }
}