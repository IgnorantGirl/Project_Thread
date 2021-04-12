package lesson05_state;

//测试守护线程
//上帝守护你
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread godThread = new Thread(god);
        godThread.setDaemon(true);  //设置为守护线程
        godThread.start();

        new Thread(you).start();
    }
}

class God implements Runnable{

    @Override
    public void run() {
       while (true){
           System.out.println("我是守护线程！");
       }
    }
}
//你
class You implements Runnable{

    @Override
    public void run() {
       for(int i=0;i<35600;i++){
           System.out.println("我活着很快乐！不要丧！"+i);
       }
        System.out.println("Over!!!");
    }
}
