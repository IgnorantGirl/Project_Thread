package lesson06_syc;

//不安全的买票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();
        new Thread(station,"BC的我").start();
        new Thread(station,"NB的你们").start();
        new Thread(station,"可怕的黄牛党").start();

    }
}

class BuyTicket implements Runnable{

    int ticket  = 10;
    boolean flag = true;
    @Override
    public void run() {
        //买票
        while (flag){
            buy();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }
    //synchronized 同步方法，锁的是this
    public synchronized void buy() {
        if(ticket<=0){
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName()+"拿到了"+ticket--+"张票");
    }
}