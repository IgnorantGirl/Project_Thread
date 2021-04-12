package lesson07_lock;

//死锁：多个线程互相抱有对方需要的资源，然后形成僵持
public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(1,"小红");
        Makeup g2 = new Makeup(2,"小兰");
        g1.start();
        g2.start();
    }
}
//口红
class Lipstick{

}
//镜子
class Mirror{

}

class  Makeup extends Thread{

    Lipstick lipstick = new Lipstick();
    Mirror mirror = new Mirror();
    int choice;  //第几个化妆者
    String name; //化妆者的名字
    public Makeup(int choice,String name){
        this.choice = choice;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  void  makeup() throws InterruptedException {
        if(choice==1){
            synchronized (lipstick){
                System.out.println(name+":正在使用口红");
                Thread.sleep(3000);

            }
            //1s中想获得镜子
            synchronized (mirror){
                System.out.println(name+":正在使用镜子");
            }
        }else {
            synchronized (mirror){
                System.out.println(name+":正在使用镜子");
                Thread.sleep(2000);

            }
            synchronized (lipstick){
                System.out.println(name+":正在使用口红");
            }
        }
    }
}
