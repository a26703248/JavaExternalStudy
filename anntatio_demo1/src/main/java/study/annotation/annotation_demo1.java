package study.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public class annotation_demo1 {
  /**
   * 常用註解:
   * @Override - 檢查該方法是否是重寫方法。如果發現其父類別,或者是參照的介面中並沒有該方法時,會報編譯錯誤。
   * @Deprecated - 標記過時方法。如果使用該方法,會報編譯警告(以過期可繼續使用,建議更新)。
   * @SuppressWarnings - 指示編譯器去忽略註解中聲明的警告('unused')。
   *
   * 作用在其他註解的註解或者說元註解(修飾現有的註解):
   * @Retention(RetentionPolicy) - 標識這個註解怎麼儲存(生命週期)，是只在代碼中，還是編入class檔案中，或者是在執行時可以通過反射存取(預設為 CLASS)。
   *    RetentionPolicy.SOURCE - 不會出現在 *.class 檔案中
   *    RetentionPolicy.CLASS - 會出現在 *.class 檔案中,但不會出現在執行時期檔案中
   *    RetentionPolicy.RUNTIME - 會出現在 *.class 和執行時期中,只有該選像才可以通過反射獲得(因反射是在執行時期運行)
   * @Target(ElementType[]) - 標記這個註解可用在方法、類別或屬性(不只定則任何地方都可使用)。
   *    ElementType.TYPE - 可使用在 interface,class,enum
   *    ElementType.FIELD - 可使用在屬性(包含enum建構子)
   *    ElementType.METHOD - 可使用在 method
   *    ElementType.PARAMETER - 可使用在傳入參數
   *    ElementType.CONSTRUCTOR - 可使用在建構子
   *    ElementType.LOCAL_VARIABLE - 可使用在基本型態
   *    ElementType.ANNOTATION_TYPE - 可使用在註解 Annotation 上
   *    ElementType.PACKAGE - 可使用在 package 目錄上
   *    ElementType.TYPE_PARAMETER - 可使用在類別泛型中型態參數(class Test<@TestAnn T>()) JDK1.8
   *    ElementType.TYPE_USE - 可使用在 變數泛型型態、throws異常 和 強轉類型 上(List<@TestAnn String> list)JDK1.8
   *    ElementType.MODULE - 可使用在 import 模塊中JDK1.8
   * @Documented - 標記這些註解是否包含在說明文件(javadoc)中(預設不保留,加上則保留)。
   * @Inherited - 標記註解後,使用到被標記註解的類別若有子類別則會具有相同註解(預設下,註解並沒有繼承於任何繼承性,可通過反射可獲取)
   *
   * 從 Java 7 開始，額外添加了 3 個註解:
   * @SafeVarargs - Java 7 開始支援，忽略任何使用參數為泛型變數的方法或建構函式呼叫產生的警告。
   * @FunctionalInterface - Java 8 開始支援，標識一個匿名函式或函數式介面。
   * @Repeatable - Java 8 開始支援，標識某註解可以在同一個聲明上使用多次。
   *
   * 常用文檔說明註解
   * @author - 標示類別作者屬名
   * @version - 標示類別或使用JDK(建議)版本
   * @see - 標示內容所參考到的其他方法
   * @since - 標示方法建立時間
   * @param - 標示方法傳入參數(例: @param 傳入參數名稱 傳入類別型態 傳入參數說明)
   * @return - 標示方法返回參數(例: @return 返回類別型態 返回參數說明)
   * @exception(@throws) - 標示方法內有拋出異常，若未處理則無法使用(例: @exception 異常類別型態 異常說明)
   *
   * 常用文檔說明註解補充:
   * 1. @param,@return,@exception 只用於方法
   * 2. @param,@exception 一個方法內可使用多次
   *
   * 自訂註解補充:
   * 1. 自訂註解通常都會指定 @Retention 和 @Target
   *
   */
  public static void main(String[] args) {
    Class myUser1Class = MyUser.class;
    Annotation[] annotations1 = myUser1Class.getAnnotations();
    Arrays.stream(annotations1).forEach(System.out::println);

    System.out.println("****************************");
    Class myUser2Class = MyUser2.class;
    Annotation[] annotations2 = myUser2Class.getAnnotations();
    Arrays.stream(annotations2).forEach(System.out::println);
  }

  static void invokeAnnotation(){
    System.out.println();
  }
}

// @MyAnnotation("MyUser")
@MyAnnotation2("MyUser")
@MyAnnotation2("MyUser")
class MyUser{

  @MyAnnotation2
  private Integer aaa;

  @MyAnnotation2
  public void name() {
    System.out.println();
  }
}

class MyUser2 extends MyUser{
}



/*
 * 若 annotation 中無屬性宣告則為雕飾註解(類似 @Override)
 */
// @Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation1{
  MyAnnotation2[] value();
}

@Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.TYPE)
// @Inherited
@Repeatable(MyAnnotation1.class)
@interface MyAnnotation2 {
  String value() default "Nothing";
}
