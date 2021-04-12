package lesson03_static_proxy;


/**
 * 静态代理模式总结：
 * 真是对象和代理对象都要实现同一个接口
 * 代理对象要代理真实角色
 * 好处：
 * 代理对象可以做很多真是对象做不了的事情
 * 真实对象专注做自己的事情
 */
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();//你要结婚
        WeddingCompany company = new WeddingCompany(you);
        company.HappyMarry();

        new Thread(()->System.out.println("我爱你")).start();
        new WeddingCompany((new You())).HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

//真实角色，你去结婚
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("秦老师要结婚了，超开心！");
    }
}

//代理角色，帮助你结婚
class WeddingCompany implements Marry {

    //代理谁==>真是目标角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry(); //这是真实对象调用其方法的地方
        after();
    }

    private void after() {
        System.out.println("结婚之后，交付尾款！");

    }

    private void before() {
        System.out.println("结婚之前，布置现场！");

    }
}