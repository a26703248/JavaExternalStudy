package study.reflection;

import java.io.FileInputStream;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class ReflectionTest1 {
  /*
   * 類別加載流程
   * Load(.class) -> Link(JRE) -> Initialize(JVM)
   * Load: 將 .class 檔讀取至記憶體中並建立java.lang.Class物件,此過程由ClassLoader加載完成
   * Link: 將記憶體中類別合併制 JRE 中
   * Initialize: JVM 負責對類別進行初始化
   */

  // 反射建立實體
  @Test
  public void test1() throws Exception {
    Class clazz = Person.class;
    Constructor constructor = clazz.getConstructor(String.class, Integer.class);
    Object obj = constructor.newInstance("Tom", 12);
    Person person = (Person) obj;
    System.out.println(person.toString());

    Field age = clazz.getDeclaredField("age");
    age.set(obj, 10);
    System.out.println(person.toString());

    Method show = clazz.getDeclaredMethod("show");
    show.invoke(person);

  }

  // 反射取得私有建構子、方法、屬性
  @Test
  public void test2() throws Exception {
    Class clazz = Person.class;
    // 私有建構子
    Constructor cons = clazz.getDeclaredConstructor(String.class);
    cons.setAccessible(true);
    Object obj2 = cons.newInstance("Hellen");
    Person p2 = (Person) obj2;
    System.out.println(p2.toString());

    // 私有屬性
    Field name = clazz.getDeclaredField("name");
    name.setAccessible(true);
    name.set(p2, "Allan");
    System.out.println(p2.toString());

    // 私有方法
    Method sayHi = clazz.getDeclaredMethod("sayHi", String.class);
    sayHi.setAccessible(true);
    Object result = sayHi.invoke(p2, "Alice");
    String str = (String) result;
    System.out.println(str);
  }

  // 取得物件 class 物件
  @Test
  public void test3() throws Exception {
    // 1. Person.class
    Class<Person> clazz1 = Person.class;
    System.out.println(clazz1);

    // 2. getClass
    Person p2 = new Person();
    Class<? extends Person> clazz2 = p2.getClass();
    System.out.println(clazz2);

    // 3. Class.forName
    Class<?> clazz3 = Class.forName("study.reflection.Person");
    System.out.println(clazz3);

    // 4. ClassLoader
    ClassLoader classLoader = ReflectionTest1.class.getClassLoader();
    Class<?> clazz4 = classLoader.loadClass("study.reflection.Person");
    System.out.println(clazz4);

    // 都指向相同記憶體位址(執行時起建立的類別將會緩存一段時間,期間可以通過不同方式獲取)
    // true
    System.out.println(clazz1 == clazz2);
    // true
    System.out.println(clazz1 == clazz3);
    // true
    System.out.println(clazz1 == clazz4);
  }

  // 可取得 Class 類別的型態
  @Test
  public void test4() {
    Class c1 = Object.class;
    Class c2 = Comparable.class;
    Class c3 = String[].class;
    Class c4 = int[][].class;
    Class c5 = ElementType.class;
    Class c6 = Override.class;
    Class c7 = int.class;
    Class c8 = void.class;
    Class c9 = Class.class;

    int[] a = new int[10];
    int[] b = new int[100];
    Class c10 = a.getClass();
    Class c11 = b.getClass();
    // 只要類別型態與維度相同,就為相同 Class 物件
    // true
    System.out.println(c10 == c11);
  }

  // ClassLoader 類型
  @Test
  public void test5() {
    ClassLoader classLoader = ReflectionTest1.class.getClassLoader();
    System.out.println(classLoader);

    ClassLoader classLoader2 = classLoader.getParent();
    System.out.println(classLoader2);

    // BootstrapClassLoader 因為為系統加載器所以無法取得
    ClassLoader classLoader3 = classLoader2.getParent();
    System.out.println(classLoader3);
  }

  // 使用 ClassLoader 讀取靜態資源文件
  @Test
  public void test6() throws Exception {
    Properties prop = new Properties();
    FileInputStream fis = new FileInputStream("src/jdbc.properties");
    prop.load(fis);

    // ClassLoader classLoader = ReflectionTest1.class.getClassLoader();
    // InputStream is = classLoader.getResourceAsStream("src/test/jdbc.properties");
    // prop.load(is);

    String user = prop.getProperty("user");
    String password = prop.getProperty("password");
    System.out.println(user + " " + password );
  }

  // 取得執行時期類別
  /* newInstance
   * 1. 必須要有無參數建構子才能使用 newInstance 建立實體
   * 2. private 建構子無法直接 newInstance 建立實體
   */
  @Test
  public void test7()  throws Exception {
    Class<Person> clazz = Person.class;
    Person p = clazz.newInstance();
    System.out.println(p);
  }

  // 反射動態性
  @Test
  public void test8() {
    int num = new Random().nextInt(3);
    String classPath = null;
    switch (num) {
      case 0:
        classPath = "java.util.Date";
        break;
      case 1:
        classPath = "java.lang.Object";
        break;
      case 2:
        classPath = "study.reflection.Person";
        break;
    }
    try {
      Object obj = getInstance(classPath);
      System.out.println(obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object getInstance(String classPath) throws Exception {
    Class clazz = Class.forName(classPath);
    return clazz.newInstance();
  }

}
