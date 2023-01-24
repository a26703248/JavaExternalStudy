package study.reflection;

import java.io.Serializable;

public class Creature<T> implements Serializable {
  private Character gender;
  public Double weight;

  private void breath() {
    System.out.println("呼吸");
  }

  public void eat() {
    System.out.println("吃東西");
  }

}
