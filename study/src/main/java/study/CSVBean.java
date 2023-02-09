package study;

import com.opencsv.bean.CsvBindByPosition;

public class CSVBean {

    @CsvBindByPosition(position = 0)
    private String seq;

    @CsvBindByPosition(position = 1)
    private String name;

    public CSVBean() {
    }

    public CSVBean(String seq, String name) {
      this.seq = seq;
      this.name = name;
    }

    public CSVBean(String... strings) {
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
