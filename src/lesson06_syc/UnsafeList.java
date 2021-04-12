package lesson06_syc;

import java.util.ArrayList;
import java.util.List;

//线程不安全的集合
public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            //往list中加入线程的名字
            //安全的话应该1000都加入，不安全的话可能有覆盖情况
            //加🔒
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();

        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }
}
