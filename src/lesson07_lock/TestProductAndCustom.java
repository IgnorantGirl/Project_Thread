package lesson07_lock;

//测试：生产者消费者模式-->利用缓冲区解决：管程法

//需要：生产者，消费者，缓冲区，产品
public class TestProductAndCustom {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
       Productor productor = new Productor(container);
       Consumer consumer = new Consumer(container);
       productor.start();
       consumer.start();
    }
}

//生产者
class Productor extends Thread{
    SynContainer container ;
    public Productor(SynContainer container){
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产者生产了"+i+"只鸡");
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer container;
    //构造方法
    public Consumer(SynContainer container){
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费者消费---->第"+container.pop().id+"只鸡");
        }
    }
}
//产品
class Chicken{
    int id;
    public Chicken(int id){
        this.id=id;
    }
}

//缓冲区
class SynContainer{

    //需要一个容器大小
    Chicken[] chickens = new Chicken[10];
    int count=0;
    //生产者放入产品
    public synchronized void push(Chicken chicken){
        //如果容器满了，需要等待消费者消费
        if(count==chickens.length){
            //通知消费者消费，生产等待
            try {
                this.wait();
              //  System.out.println("生产者等待消费者消费");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[count] = chicken;
        count++;
        //可以通知消费者消费
        this.notifyAll();
    }
    //消费者消费产品
    public synchronized Chicken pop(){
        //判断是否消费
        if(count==0){
            try {
                //等待生产者生产，消费者等待
                this.wait();
                //System.out.println("消费者等待生产者生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果可以消费
        count--;
        Chicken chicken = chickens[count];

        //吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }
}