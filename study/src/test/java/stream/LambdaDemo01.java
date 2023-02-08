package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LambdaDemo01 {

  public static void main(String[] args) {
    Integer a = 1234;
    System.out.println(Objects.nonNull(a) + ":" + a);
    a = null;
    System.out.println(Objects.isNull(a) + ":" + a);
    // System.out.println(Objects.requireNonNull(a));
    // 拋異常且可自訂訊息內容
    // System.out.println(Objects.requireNonNull(a, "is not"));
    // System.out.println(Objects.requireNonNull(a, () -> new String("is not!!!")));

    List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5);
    int total = 0;
    // 因為 total 為區域變數則無法被內部類或是lambda使用
    // asList.forEach(n -> total += n);
  }
}
