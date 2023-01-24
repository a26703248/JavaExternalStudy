package study.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

interface Human{

  String getBelief();

  void eat(String food);

}

class SuperMan implements Human{

  @Override
  public String getBelief() {
    return "I belief I can fly!";
  }

  @Override
  public void eat(String food) {
    System.out.println("我喜歡吃" + food);
  }

}

class ProxyFactory {

  /**
   * 透過調用此方法返回代理物件
   * @param obj 被代理物件
   * @return Object 代理物件
   */
  public static Object getProxyInstance(Object obj){

    MyInvocationHandler handler = new MyInvocationHandler();

    handler.band(obj);

    return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
  }
}

class MyInvocationHandler implements InvocationHandler{

  // 需使用被代理物件進行賦值
  private Object obj;

  /**
   * 綁定代理物件
   * @param obj 被代理物件
   */
  public void band(Object obj) {
    this.obj = obj;
  }

  /**
   * 當通過代理物件呼叫方法 a 時,會自動呼叫 invoke 方法
   * 將被代理物件要執行方法些再 invoke 內
   * @param proxy Object 代理物件
   * @param method Method 被代理物件呼叫方法
   * @param args Object[] 傳入方法參數
   * @return Object 方法執行完結果
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    HumanUtil hu = new HumanUtil();
    hu.method1();
    Object result = method.invoke(obj , args);

    hu.method2();
    return result;
  }

}

class HumanUtil {
  public void method1() {
    System.out.println("*************************通用方法1*************************");
  }

  public void method2() {
    System.out.println("*************************通用方法2*************************");
  }
}

public class DynamicProxyTest {

  @Test
  public void test1() {
    SuperMan superMan = new SuperMan();
    Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
    String belief = proxyInstance.getBelief();
    System.out.println(belief);
    proxyInstance.eat("臭豆腐");
  }
}
