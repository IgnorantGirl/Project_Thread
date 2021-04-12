package lesson05_state;

//模拟网络延时：发现问题的发生性
public class TestSleep implements Runnable{

    //票数
    private int tickedNums = 10;
    @Override
    public void run() {
     while(true){
         if(tickedNums<=0) break;
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println(Thread.currentThread().getName()+"-->拿到了第"+(tickedNums--)+"张票");
     }
    }

    public static void main(String[] args) {
        TestSleep sleepThread = new TestSleep();
        new Thread(sleepThread,"小明").start();
        new Thread(sleepThread,"老师").start();
        new Thread(sleepThread,"黄牛党").start();

    }
}
