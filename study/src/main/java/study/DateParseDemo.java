package study;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.Locale;

public class DateParseDemo {
  public static void main(String[] args) {
    LocalDate now = LocalDate.now();
    // 長短
    System.out.println(
        now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));

    // 格式
    System.out.println(
        now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.JAPAN)));

    // 算出各時區偏移值,ZoneId,time
    Instant instant = Instant.now();
    ZonedDateTime current = instant.atZone(ZoneId.systemDefault());
    // equivalent to ZonedDateTime.now()
    System.out.printf("Current time is %s%n%n", current);

    System.out.printf("%10s %20s %13s%n", "Offset", "ZoneId", "Time");
    ZoneId.getAvailableZoneIds().stream()
        .map(ZoneId::of) // Stream<ZoneId>>
        .filter(zoneId -> {
          ZoneOffset offset = instant.atZone(zoneId).getOffset();
          return offset.getTotalSeconds() % (60 * 60) != 0;
        })
        .sorted(Comparator.comparingInt(zoneId -> instant.atZone(zoneId).getOffset().getTotalSeconds()))
        .forEach(zoneId -> {
          ZonedDateTime zdt = current.withZoneSameInstant(zoneId);
          System.out.printf("%10s %25s %10s%n", zdt.getOffset(), zoneId,
              zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        });
  }
}
