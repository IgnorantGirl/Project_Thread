package lesson02;

public class MotifyRaceThread implements Runnable{
    private int raceLen = 100;
    private  String winner=null;
    @Override
    public void run() {
      for(int i=0;i<=100;i++){
          //模拟兔子睡觉
          if(Thread.currentThread().getName().equals("兔子")&& i%10==0){
              try {
                  Thread.sleep(10);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          if(Thread.currentThread().getName().equals("乌龟")&& i%12==0){
              try {
                  Thread.sleep(10);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          boolean flag = getResult(i);
          if(flag) break;
          System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步");
      }
    }

    public boolean getResult(int index){
        if(winner!=null){
            return true;
        }else if(index >= 100){
             winner = Thread.currentThread().getName();
            System.out.println("winner:"+winner);
             return true;
        }
        return false;
    }

    public static void main(String[] args) {
          MotifyRaceThread thread = new MotifyRaceThread();
          new Thread(thread,"兔子").start();
          new Thread(thread,"乌龟").start();
    }
}
