package study;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;

public class TemporalDemo {
  public static void main(String[] args) {
    // TemporalAdjusters 時間調整器
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
    System.out.println("range:" + start + " ~ " + end);
    end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
    System.out.println("range:" + start + " ~ " + end);
    end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
    System.out.println("range:" + start + " ~ " + end);
    // TemporalQueries
    System.out.println(LocalDate.now().query(TemporalQueries.precision()));
    System.out.println(ZonedDateTime.now().query(TemporalQueries.zone()));
    System.out.println(ZonedDateTime.now().query(TemporalQueries.zoneId()));
    TemporalDemo temporalDemo = new TemporalDemo();
    long range = LocalDate.now().query(n -> temporalDemo.daysUntilPirateDay(n));
    System.out.println("今天距離下個聖誕節還有 " + range + " 天");
  }

  // 計算目標節日距離現在有幾天(若已過則會計算下年)
  public long daysUntilPirateDay(TemporalAccessor temporal) {
    int day = temporal.get(ChronoField.DAY_OF_MONTH);
    int month = temporal.get(ChronoField.MONTH_OF_YEAR);
    int year = temporal.get(ChronoField.YEAR);
    LocalDate date = LocalDate.of(year, month, day);
    LocalDate tlapd = LocalDate.of(year, Month.DECEMBER, 25); // 目標節日日期
    if (date.isAfter(tlapd)) { // 判斷節日是否已過
      tlapd = tlapd.plusYears(1);
    }
    return ChronoUnit.DAYS.between(date, tlapd);
  }

}
