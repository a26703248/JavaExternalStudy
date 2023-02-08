package study;

import java.util.Random;

public class RandomStream {
  public static void main(String[] args) {
    Random r = new Random();
    // long, int, double
    // ints()
    r.ints().limit(10).sorted().forEach(System.out::println);
    System.out.println("===============================");
    // ints(long streamSize)
    r.ints(1L).sorted().forEach(System.out::println);
    System.out.println("===============================");
    // ints(int randomNumberOrigin, int randomNumberBound)
    r.ints(1, 10).limit(10).sorted().forEach(System.out::println);
    System.out.println("===============================");
    // ints(long streamSize, int randomNumberOrigin, int randomNumberBound)
    r.ints(10, 1, 10).sorted().forEach(System.out::println);

  }
}
