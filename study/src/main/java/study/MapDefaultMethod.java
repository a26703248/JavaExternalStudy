package study;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapDefaultMethod {

  public static void main(String[] args) {
    // 使用記憶體 stack 做存放,效率較差
    System.out.println(MapDefaultMethod.fib(5L));
    // 使用快取
    // System.out.println(new Demo().fib(5L));
    System.out.println("=================================");
    // 統計文字出現次數
    String content = "NSA agent walks into a bar.Bartender says," +
        "'Hey, I have a new joke for you.' Agent says,'heard it'.";
    Map<String, Integer> counts = new Demo().countWord(content, "NSA", "agent", "joke");
    counts.forEach((k, v) -> System.out.println(k + ":" + v));


  }

  static long fib(long i) {
    System.out.println(i);
    if (i == 0)
      return 0;
    if (i == 1)
      return 1;
    return fib(i - 1) + fib(i - 2);
  }

}

class Demo {

  Map<Long, BigInteger> cache = new HashMap<>();

  BigInteger fib(long i) {
    System.out.println(i);
    if (i == 0)
      return BigInteger.ZERO;
    if (i == 1)
      return BigInteger.ONE;
    return cache.computeIfAbsent(i, n -> fib(n - 2).add(fib(n - 1)));
  }

  Map<String, Integer> countWord(String passage, String... strings) {
    Map<String, Integer> wordCounts = new HashMap<>();
    Arrays.stream(strings).forEach(s -> wordCounts.put(s, 0));
    Arrays.stream(passage.split(" ")).forEach(word -> wordCounts.computeIfPresent(word, (key, val) -> val + 1));
    return wordCounts;
  }
}
