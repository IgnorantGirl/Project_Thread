package lesson01;

//创建线程方式二：实现runnable接口，重写run()方法，执行线程需要丢入runnable接口实现类，调用start()方法
public class TestThread02 implements  Runnable{
    public int Id;
    public TestThread02(int id){
        this.Id =id;
    }
    public static void main(String[] args) {
        TestThread02 thread02 = new TestThread02(666);
        //创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(thread02).start();
        for(int i=0;i<200;i++){
            System.out.println("我是主线程"+"   "+i);
        }
    }

    @Override
    public void run() {
        for(int i=0;i<200;i++){
            System.out.println("我是线程"+Id+"   "+i);
        }
    }
}
