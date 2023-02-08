package study;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FunctionDefaultMethod {
  public static void main(String[] args) {
    // Function
    Function<Integer, Integer> add2 = x -> x + 2;
    Function<Integer, Integer> mult3 = x -> x * 3;
    Function<Integer, Integer> mult3add2 = add2.compose(mult3); // before
    Function<Integer, Integer> add2mult3 = add2.andThen(mult3); // after
    System.out.println("mult3add2: " + mult3add2.apply(1));
    System.out.println("add2mult3: " + add2mult3.apply(1));
    System.out.println("===================================");
    // Consumer
    Consumer<String> c1 = (n) -> System.out.println("第一次印出:" + n);
    Consumer<String> c2 = (n) -> System.out.println("第二次印出:" + n);
    Consumer<String> printTrace  = c2.andThen(c1);
    Stream.of("this", "is", "a", "java", "code").forEach(printTrace);
    System.out.println("===================================");
    // Predicate
    Predicate<Integer> p1 = n -> n%2 == 0;
    Predicate<Integer> p2 = n -> n >= 6;
    Predicate<Integer> p1Orp2 = p1.or(p2);
    Predicate<Integer> p1Andp2 = p1.and(p2);
    Predicate<Integer> p1Non = p1.negate();
    Predicate<Integer> p2Non = p2.negate();
    Stream.of(1, 2, 4, 6).filter(p1Orp2).forEach(System.out::println);
    System.out.println("===================================");
    Stream.of(1, 2, 4, 6).filter(p1Andp2).forEach(System.out::println);
    System.out.println("===================================");
    Stream.of(1, 2, 4, 6).filter(p1Non).forEach(System.out::println);
    System.out.println("===================================");
    Stream.of(1, 2, 4, 6).filter(p2Non).forEach(System.out::println);
  }
}
