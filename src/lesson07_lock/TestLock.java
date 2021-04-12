package lesson07_lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"One").start();
        new Thread(buyTicket,"Two").start();
        new Thread(buyTicket,"Three").start();
    }
}

class BuyTicket implements Runnable{

    int ticket  = 10;
    boolean flag = true;
    //实例化可重入类
    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        //买票
        while (flag){
            try{
                //开始锁定
                lock.lock();
                if(ticket<=0){
                    flag = false;
                    return;
                }
                System.out.println(Thread.currentThread().getName()+"拿到了"+ticket--+"张票");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                //解锁
                lock.unlock();
            }


        }
    }
}