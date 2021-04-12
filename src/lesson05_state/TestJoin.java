package lesson05_state;

public class TestJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <500; i++) {
            System.out.println("vip coming..."+i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread tread = new Thread(testJoin);
        tread.start();
        for (int i = 0; i < 100; i++) {
            if(i==50)  tread.join();
            System.out.println("main coming ..."+i);
        }
    }


}
