package stream;

import java.io.Serializable;

/*
 * 物件要序列化物件必須要滿足,才可執行序列化
 * 1. 需 implements Serializable, Deserialize 標示介面
 * 2. 需要提供一個版本定義靜態常數
 * 3. 必須注意類別內部所有屬性都可序列化(implements Serializable, Deserialize 介面)
 *
 * 注意點:
 * transient(暫時性的部會序列化), static(靜態修飾符方法屬性不歸物件管理) 不可在序列化物件內使用(不會報錯)
 */
public class Person implements Serializable{
	  private String name;
	  private Integer age;
	  private static final long serialVersionUID = 1L;

	  public Person() {
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
	    return "Person [age=" + age + ", name=" + name + "]";
	  }
}