package study;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class NIO2 {
  public static void main(String[] args) {
    NIO2 nio2 = new NIO2();
  }

  public void demo1() {
    // 讀取資料夾內所有檔案路徑
    try (Stream<Path> list = Files.list(Paths.get("src/main/data/resources"));) {
      list.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void demo2() {
    // 讀檔
    try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/data/demo1.txt"))) {
      lines.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void demo3() {
    try (
        FileReader fr = new FileReader("src/main/resources/data/demo2.csv");
        FileWriter fw = new FileWriter("src/main/resources/target/demo3.json");) {

      List<CSVBean> res = new CsvToBeanBuilder<CSVBean>(fr)
          .withType(CSVBean.class).build().parse();

      new Gson().toJson(res, fw);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void demo4() {
    try (
        FileReader fr = new FileReader("src/main/resources/data/demo2.csv");
        FileWriter fw = new FileWriter("src/main/resources/target/demo2.csv");) {
      List<CSVBean> origin = new CsvToBeanBuilder<CSVBean>(fr)
          .withType(CSVBean.class)
          .build()
          .parse();

      StatefulBeanToCsv<CSVBean> sbtc = new StatefulBeanToCsvBuilder<CSVBean>(fw)
          .build();
      sbtc.write(origin);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
