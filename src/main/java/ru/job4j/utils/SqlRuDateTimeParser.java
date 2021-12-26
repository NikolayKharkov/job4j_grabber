package ru.job4j.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) {
        LocalDateTime result = null;
        String date = parse.split(",")[0].trim();
        String time = parse.split(",")[1].trim();
        switch (date) {
            case "вчера":
                LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
                String yesterdayStr = String.format("%s-%s-%s %s",
                        yesterday.getYear(),
                        yesterday.getMonthValue(),
                        yesterday.getDayOfMonth(),
                        time);
                result = LocalDateTime.parse(yesterdayStr, FORMATTER);
                break;
            case "сегодня":
                LocalDateTime today = LocalDateTime.now();
                String todayStr = String.format("%s-%s-%s %s",
                        today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        time);
                result = LocalDateTime.parse(todayStr, FORMATTER);
                break;
            default:
                String[] tmp = date.split(" ");
                String dateStr = String.format("%d-%s-%s %s",
                        (Integer.parseInt(tmp[2]) + 2000),
                        MONTHS.get(tmp[1]),
                        tmp[0],
                        time);
                result = LocalDateTime.parse(dateStr, FORMATTER);
        }
        return result;
    }
}