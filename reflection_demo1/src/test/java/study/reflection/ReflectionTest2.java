package study.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ReflectionTest2 {
  // 取得類別物件所有屬性結構
  @Test
  public void test9() {
    Class<Person> clazz = Person.class;
    // 取得所有類別及繼承父類別宣告為 public 屬性結構
    Field[] fields = clazz.getFields();
    Arrays.stream(fields).forEach(System.out::println);
    System.out.println("*******************************");

    // 取得當前類別所有屬性結構,不包含父類別
    Field[] declaredFields = clazz.getDeclaredFields();
    Arrays.stream(declaredFields).forEach(System.out::println);
    System.out.println("*******************************");

  }

  // 取得屬性權限、變數型態及變數名稱
  @Test
  public void test10() {
    Class<Person> clazz = Person.class;
    Field[] declaredFields = clazz.getDeclaredFields();
    Arrays.stream(declaredFields).forEach(f -> {
      // 1. 權限修飾符
      int modifiers = f.getModifiers();
      System.out.print(Modifier.toString(modifiers) + "\t");
      // 2. 變數型態
      Class type = f.getType();
      System.out.print(type.getName() + "\t");
      // 3. 變數名稱
      String name = f.getName();
      System.out.println(name);
    });

  }

  // 取得類別物件所有方法結構
  @Test
  public void test11() {
    Class<Person> clazz = Person.class;
    // 取得所有類別及繼承父類別宣告為 public 方法結構
    Method[] methods = clazz.getMethods();
    Arrays.stream(methods).forEach(System.out::println);
    System.out.println("*********************************************");

    // 取得當前類別所有方法結構,不包含父類別
    Method[] declaredMethods = clazz.getDeclaredMethods();
    Arrays.stream(declaredMethods).forEach(System.out::println);
    System.out.println("*********************************************");

  }

  // 取得方法權限、方法名稱、方法註解、參數型態及參數名稱
  @Test
  public void test12() {
    Class<Person> clazz = Person.class;
    Method[] declaredMethods = clazz.getDeclaredMethods();
    Arrays.stream(declaredMethods).forEach(f -> {
      // 1. 方法註解
      Annotation[] annotations = f.getAnnotations();
      Arrays.stream(annotations).forEach(System.out::println);

      // 2. 權限修飾符
      int modifiers = f.getModifiers();
      System.out.print(Modifier.toString(modifiers) + "\t");

      // 3. 返回型態
      Class<?> returnType = f.getReturnType();
      System.out.print(returnType.getName() + "\t");

      // 4. 方法名稱
      String name = f.getName();
      System.out.print(name + "\t");
      System.out.print("(");

      // 5. 參數列表
      Class<?>[] parameterTypes = f.getParameterTypes();
      if(!(parameterTypes == null || parameterTypes.length == 0)){
        for (int i = 0; i < parameterTypes.length; i++) {
          if(i == parameterTypes.length -1){
            System.out.print(parameterTypes[i].getName() + " args_" + i);
            break;
          }
          System.out.print(parameterTypes[i].getName() + "args_" + i + ",");
        }
      }
      System.out.print(")");

      // 6. 方法拋出異常
      Class<?>[] exceptionTypes = f.getExceptionTypes();
      if(!(exceptionTypes == null || exceptionTypes.length == 0)){
        System.out.print("throws ");
        for (int i = 0; i < exceptionTypes.length; i++) {
          if(i == exceptionTypes.length - 1){
            System.out.print(exceptionTypes[i].getName());
            break;
          }
          System.out.print(exceptionTypes[i].getName() + ",");
        }
      }

      System.out.println();
    });
  }

  // 取得類別物件建構子結構
  @Test
  public void test13() {
    // 取得類別所有宣告為 public 方法結構
    Class clazz = Person.class;
    Constructor[] constructors = clazz.getConstructors();
    Arrays.stream(constructors).forEach(System.out::println);
    System.out.println("*********************************************");

    Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
    Arrays.stream(declaredConstructors).forEach(System.out::println);
    System.out.println("*********************************************");
    // 與方法相同可獲得權限等相關資訊 略...
  }

  // 取得父類別物件結構
  @Test
  public void test14() {

    Class clazz = Person.class;
    Class superclass = clazz.getSuperclass();
    System.out.println(superclass);

    // 帶泛型
    Type genericSuperclass = clazz.getGenericSuperclass();
    System.out.println(genericSuperclass);

    // 父類別的泛型
    Type genericSuperclass2 = clazz.getGenericSuperclass();
    ParameterizedType pt = (ParameterizedType) genericSuperclass2;
    Type[] actualTypeArguments = pt.getActualTypeArguments();
    Arrays.stream(actualTypeArguments).forEach(t -> System.out.println(((Class)t).getSimpleName()));
  }

  // 取得類別繼承介面
  @Test
  public void test15() {
    Class clazz = Person.class;
    Class[] interfaces = clazz.getInterfaces();
    Arrays.stream(interfaces).forEach(System.out::println);
    // 父類別繼承介面
    Class[] superInterfaces = clazz.getSuperclass().getInterfaces();
    Arrays.stream(superInterfaces).forEach(System.out::println);
  }

  // 取得模組
  @Test
  public void test16() {
    Class clazz = Person.class;
    Package package1 = clazz.getPackage();
    System.out.println(package1);
  }

  // 取得類別註解
  @Test
  public void test17() {
    Class clazz = Person.class;
    Annotation[] annotations = clazz.getAnnotations();
    Arrays.stream(annotations).forEach(System.out::println);
    System.out.println("*******************************");
    MyAnnotation annotation = (MyAnnotation)clazz.getAnnotation(MyAnnotation.class);
    System.out.println(annotation.value());
  }


}
