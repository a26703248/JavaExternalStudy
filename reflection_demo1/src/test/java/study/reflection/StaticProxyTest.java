package study.reflection;

import org.junit.jupiter.api.Test;

interface ClothFactory{
  void produceCloth();
}

class ProxyClothFactory implements ClothFactory{

  private ClothFactory factory;

  public ProxyClothFactory(ClothFactory factory){
    this.factory = factory;
  }

  @Override
  public void produceCloth() {
    System.out.println("代理工廠準備工作");
    factory.produceCloth();
    System.out.println("代理工廠結束工作");
  }
}
class NikeClothFactory implements ClothFactory{

  @Override
  public void produceCloth() {
    System.out.println("Nick 製作 T-shirt");
  }
}

public class StaticProxyTest {

  @Test
  public void test1() {
    // 1. 建立實體
    NikeClothFactory nike = new NikeClothFactory();
    // 2. 建立代理物件並傳入實際執行物件
    ProxyClothFactory proxy = new ProxyClothFactory(nike);
    // 3. 呼叫代理與實際執行物件共同實作介面方法
    proxy.produceCloth();
    System.out.println("**********************************");

    NikeClothFactory nikeClothFactory = new NikeClothFactory();
    ClothFactory proxyInstance = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
    proxyInstance.produceCloth();
  }

}
