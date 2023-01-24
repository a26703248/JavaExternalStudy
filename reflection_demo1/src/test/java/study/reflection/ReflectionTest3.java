package study.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class ReflectionTest3 {

  // 取得指定屬性
  @Test
  public void test18() throws Exception {
    Class clazz = Person.class;
    Person p = (Person) clazz.newInstance();
    Field age = clazz.getField("age");
    age.set(p, 101);
    int pAge = (int) age.get(p);
    System.out.println(pAge);
    Field name = clazz.getDeclaredField("name");
    name.setAccessible(true);
    name.set(p, "Tom");
    System.out.println(name.get(p));
  }

  // 取得指定方法
  @Test
  public void test19() throws Exception {
    Class clazz = Person.class;
    Person p = (Person) clazz.newInstance();
    Method sayHi = clazz.getDeclaredMethod("sayHi", String.class);
    sayHi.setAccessible(true);

    String result = (String) sayHi.invoke(p, "Alice");
    System.out.println(result);

    // 不透過 newInstance 取得 private static 方法
    // private static void display();
    Method display = clazz.getDeclaredMethod("display");
    display.setAccessible(true);
    // 若為 static 方法則可將類別填 null
    // Object invoke = display.invoke(Person.class);
    Object invoke = display.invoke(null);
    // 返回為 void 則為 null
    System.out.println(invoke); // null

  }

  // 取得指定建構子
  @Test
  public void test20() throws Exception {
    Class clazz = Person.class;
    Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class);
    declaredConstructor.setAccessible(true);
    Person instance = (Person) declaredConstructor.newInstance("Tom");
    System.out.println(instance);
  }

}
