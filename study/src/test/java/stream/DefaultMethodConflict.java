package stream;

public class DefaultMethodConflict {
  public static void main(String[] args) {
    Demo3 demo3 = new Demo3();
    demo3.print();
    Demo5 demo5 = new Demo5();
    demo5.print();
  }
}

// 1. 類別方法大於介面預設方法
interface Demo1 {
  default void print() {
    System.out.println("demo1");
  };
}

class Demo2 {
  public void print() {
    System.out.println("demo2");
  }
}

class Demo3 extends Demo2 implements Demo1 {
}

// 2. 相同介面同時被繼承預設方法會以子類介面為優先

interface Demo4 extends Demo1 {
  default void print() {
    System.out.println("demo4");
  };
}

class Demo5 implements Demo1, Demo4{}

// 3. 若繼承類別或介面中同時有相同方法簽章且未有繼承關係,則編譯不過
interface Demo7 {
  default void print() {
    System.out.println("demo7");
  };
}

// compile fail
// class Demo6 implements Demo1, Demo7{}

