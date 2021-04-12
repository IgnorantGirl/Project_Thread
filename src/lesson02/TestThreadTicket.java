package lesson02;

public class TestThreadTicket implements  Runnable{

    //票数
    private int ticketNums = 10;
    @Override
    public void run() {

        while(true){
            if(ticketNums<=0) break;
            try {
                Thread.sleep(2000);  //模拟延时
                System.out.println(Thread.currentThread().getName()+"--->"+ticketNums--+"票");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestThreadTicket threadTicket = new TestThreadTicket();

        //开启三个线程
        new Thread(threadTicket,"小明").start();
        new Thread(threadTicket,"老师").start();
        new Thread(threadTicket,"黄牛党").start();

    }
}
