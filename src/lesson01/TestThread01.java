package lesson01;

//创建线程方式一：继承thread类，重写run()方法，调用start()方法开启线程

//总结：注意，线程开启不一定立即执行，由CPU调度执行
public class TestThread01 extends  Thread{
    public int Id;
    public TestThread01(int id){
        this.Id =id;
    }
    @Override
    public void run() {
        for(int i=0;i<200;i++){
            System.out.println("我是线程"+Id+"   "+i);
        }
    }

    public static void main(String[] args) {
         Thread thread = new TestThread01(555);
         thread.start();
        for(int i=0;i<200;i++){
            System.out.println("我是主线程"+"   "+i);
        }
    }
}
