package stream;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class ObjectStream {

	@Test
	public void test() {
	    // 物件序列化
	     Person p1 = new Person("小明", 25);
	     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
	       oos.writeObject(p1); // 寫入
	       oos.flush(); // 更新
	     } catch (Exception e) {
	       e.printStackTrace();
	     }

	    // 物件反序列化
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
	      Object obj = ois.readObject();
	      Person p2 = (Person) obj;
	      System.out.println(p2);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}



}
