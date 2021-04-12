package lesson08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试线程池
public class TestPool {
    public static void main(String[] args) {
        //1.创建服务，创建线程池
        //newFixedThreadPool  参数为线程池的大小
        ExecutorService service = Executors.newFixedThreadPool(10);
        //2.启动线程 执行
        service.execute(new TestThread());
        service.execute(new TestThread());
        service.execute(new TestThread());
        service.execute(new TestThread());

        //3.关闭连接
    }
}
class TestThread implements  Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}