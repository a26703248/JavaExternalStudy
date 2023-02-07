package stream;

public class StringStream {
  public static void main(String[] args) {
    String content = "this is a java code1234";
    StringBuilder forward = content.toUpperCase()
        .codePoints()
        .filter(Character::isLetterOrDigit)
        .collect(StringBuilder::new,
            StringBuilder::appendCodePoint,
            StringBuilder::append);
    System.out.println(forward);
  }
}
