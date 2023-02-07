package stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

  public static void main(String[] args) {
    Stream.of(1, 2, 3, 4, 5)
        .forEach(System.out::println);

    Arrays.stream(new Integer[] { 1, 2, 3, 4, 5 })
        .forEach(System.out::println);

    Stream.iterate(1, n -> ++n)
        .limit(5)
        .forEach(System.out::println);

    Stream.generate(Math::random)
        .limit(5)
        .forEach(System.out::println);

    IntStream.range(1, 5)
        .forEach(System.out::println);

    IntStream.rangeClosed(1, 5)
        .forEach(System.out::println);

  }
}
