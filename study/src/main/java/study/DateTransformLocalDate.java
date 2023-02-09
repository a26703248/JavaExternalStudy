package study;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTransformLocalDate {
  public static void main(String[] args) {
    System.out.println("convert java.util.Date to LocalDate:");
    System.out.println(new java.util.Date()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .getClass());
    System.out.println("\nconvert java.sql.Date to LocalDate:");
    System.out.println(new java.sql.Date(2023, 2, 9)
        .toLocalDate()
        .getClass());
    System.out.println("\nconvert LocalDate to java.sql.Date:");
    System.out.println(java.sql.Date.valueOf(LocalDate.now())
        .getClass());
    System.out.println("\nconvert java.sql.Timestamp to LocalDateTime:");
    System.out.println(new java.sql.Timestamp(2023, 2, 9, 11, 30, 56, 150).toLocalDateTime()
        .getClass());
    System.out.println("\nconvert  LocalDateTime to java.sql.Timestamp:");
    System.out.println(java.sql.Timestamp.valueOf(LocalDateTime.now())
        .getClass());
  }
}
