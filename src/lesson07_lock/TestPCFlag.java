package lesson07_lock;

public class TestPCFlag {
    public static void main(String[] args) {
         TVShow tvShow = new TVShow();
         new Actor(tvShow).start();
         new Watcher(tvShow).start();
    }
}

//表演者
class Actor extends Thread {
    TVShow tv ;

    public Actor(TVShow tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                tv.play("快乐大本营播放中...");
            }else{
                tv.play("抖音：记录美好生活..");
            }
        }
    }
}

//观看者
class Watcher extends Thread {
    TVShow tv ;

    public Watcher(TVShow tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
                tv.watch();
        }
    }
}

//节目
class TVShow {
    //表演的节目
    String voice;
    //表演者表演T
    //观看者观看F
    boolean flag = true;

    //表演
    public synchronized void play(String voice) {
        if (!flag) {
            //flag=false 观看者正在观看，表演者需要等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了：" + voice);
        //通知观众观看
        this.notifyAll();  //通知唤醒
        this.voice = voice;
        this.flag = !this.flag;
    }

    //观看
    public synchronized void watch() {
        //flag=true 表示表演者正在表演  观看者需要等待
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了："+voice);
        //通知表演者表演
        this.notifyAll();
        this.flag = !this.flag;
    }
}