package study.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressWarnings("unused")
@MyAnn("Person")
@MyAnnotation("hi")
public class Person extends Creature<String> implements Comparable<String>, MyInterface {
  private String name;
  public Integer age;
  int id;

  public Person() {
  }
  @MyAnnotation("abc")
  private Person(String name) {
    this.name = name;
  }
  public Person(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
  @Override
  public String toString() {
    return "Person [name=" + name + ", age=" + age + "]";
  }

  @MyAnnotation
  public void show() throws NullPointerException {
    System.out.println("name:" + name + ", " + "age: " + age);
  }

  private String sayHi(String target) throws IllegalArgumentException, IllegalAccessException {
    return target + " Hi!";
  }
  @Override
  public void info() {
    System.out.println("我是人");
  }
  @Override
  public int compareTo(String arg0) {
    return 0;
  }

  private static void display() {
    System.out.println("display");
  }

}

@Retention(RetentionPolicy.SOURCE)
@interface MyAnn{
  String value() default "Nothing";
}
