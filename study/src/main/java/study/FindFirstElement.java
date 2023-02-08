package study;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindFirstElement {
  public static void main(String[] args) {
    // 若集合為非順序集合則每次返回結果有可能會不相同,但若持續相同則可加入或移除足夠多元素強制重新排序
    Set<Integer> content = new HashSet<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 8, 5));
    Integer firstInt = content.stream()
        .filter(n -> n % 2 == 0)
        .findFirst().orElse(0);
    System.out.println("first: " + firstInt);
  }
}
