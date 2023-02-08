package study;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NIO2 {
  public static void main(String[] args) {

    // 賭取資料夾內所有檔案路徑
    try (Stream<Path> list = Files.list(Paths.get("src/main/resources"));) {
      list.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 讀檔
    try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/demo1.txt"))) {
      lines.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
