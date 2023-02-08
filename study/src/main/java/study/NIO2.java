package study;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.opencsv.CSVReader;

public class NIO2 {
  public static void main(String[] args) {
    NIO2 nio2 = new NIO2();
    nio2.demo3();
  }

  static void demo1() {
    // 讀取資料夾內所有檔案路徑
    try (Stream<Path> list = Files.list(Paths.get("src/main/resources"));) {
      list.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void demo2() {
    // 讀檔
    try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/demo1.txt"))) {
      lines.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void demo3() {
    try (
        CSVReader csvr = new CSVReader(new FileReader("src/main/resources/demo2.csv"));
        FileWriter fw = new FileWriter("src/main/resources/demo3.json");) {

      List<DemoOne> res = csvr.readAll()
          .stream()
          .map(DemoOne::new)
          .collect(Collectors.toList());

      new Gson().toJson(res, fw);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

class DemoOne {
  private String seq;
  private String name;

  public DemoOne() {
  }

  public DemoOne(String seq, String name) {
    this.seq = seq;
    this.name = name;
  }

  public DemoOne(String... strings) {
    this.seq = strings[0];
    this.name = strings[1];
  }

  public String getSeq() {
    return seq;
  }

  public void setSeq(String seq) {
    this.seq = seq;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
