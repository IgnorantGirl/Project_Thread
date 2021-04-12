package lesson06_syc;

import java.util.concurrent.CopyOnWriteArrayList;

public class SafeListTest {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(list.size());
    }
}