package study;

import java.util.Optional;

public class OptionalDemo {
  public static void main(String[] args) {
    // 當為空時拋出 java.lang.NullPointerException,有則包裝成 Optional 物件
    Optional<Object> of = Optional.of(1);
    System.out.println(of);
    // 返回 Optional.empty 物件
    Optional<Object> empty = Optional.empty();
    System.out.println(empty);
    // 為空時返回 Optional.empty 物件,有則包裝成 Optional 物件
    Optional<Object> ofNullable = Optional.ofNullable(null);
    System.out.println(ofNullable);
  }
}
