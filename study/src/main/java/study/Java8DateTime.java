package study;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Java8DateTime {
  public static void main(String[] args) {
    System.out.println("Instant: " + Instant.now());
    System.out.println("LocalDate: " + LocalDate.now());
    System.out.println("LocalTime: " + LocalTime.now());
    System.out.println("LocalDateTime: " + LocalDateTime.now());
    System.out.println("ZonedDateTime: " + ZonedDateTime.now());
    System.out.println("=====================");
    System.out.println(LocalDate.of(1998, Month.AUGUST, 1));
    System.out.println(LocalTime.of(20, 10));
    System.out.println(LocalDateTime.of(1998, Month.AUGUST, 1, 20, 10, 56, 150_000_000));
    System.out.println("=====================");
    // 取得時區 Id
    ZoneId zoneId = ZoneId.systemDefault();
    // zoneId.getAvailableZoneIds().forEach(System.out::println);
    ZoneId of = ZoneId.of("UTC");
    System.out.println(of);
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), of);
    System.out.println(zonedDateTime);
    System.out.println("=====================");
    System.out.println("2月有幾天:" + Month.FEBRUARY.length(true));
    System.out.println("一年之中第幾天:" + Month.AUGUST.firstDayOfYear(true));
    System.out.println("數字轉換月份:" + Month.of(1));
    System.out.println("十二月加兩個月" + Month.DECEMBER.plus(2));
    System.out.println("十二月減兩個月" + Month.DECEMBER.minus(2));
    System.out.println("=====================");
    LocalDate now = LocalDate.now();
    System.out.println("year plus:" + now.plusYears(1));
    System.out.println("month plus:" + now.plusMonths(1));
    System.out.println("week plus:" + now.plusWeeks(1));
    System.out.println("day plus:" + now.plusDays(1));
    System.out.println("format:" + now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    // 單獨改變欄位
    System.out.println("with: " + now.withMonth(1));
    System.out.println("=====================");
    LocalTime now2 = LocalTime.now();
    System.out.println("hour plus:" + now2.plusHours(1));
    System.out.println("minutes plus:" + now2.plusMinutes(1));
    System.out.println("seconds plus:" + now2.plusSeconds(1));
    System.out.println("nano plus:" + now2.plusNanos(1));
    System.out.println("format:" + now2.format(DateTimeFormatter.ofPattern("hh|mm")));
    // 單獨改變欄位
    System.out.println("with: " + now2.withHour(1));

  }
}
