package lesson05_state;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSleep2 {
    public static void main(String[] args) throws InterruptedException {
        tenDown();  //测试倒计时
        printCurrentTime();//测试打印时间
    }

    public static  void tenDown() throws InterruptedException {
        int num = 10;
        while(true){
            Thread.sleep(1000);
            System.out.println(num--);
            if(num<=0) break;
        }
    }

    public static void printCurrentTime() throws InterruptedException {
        Date currentDate = new Date(System.currentTimeMillis());//获取系统时间
        while(true){
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(currentDate));
            Thread.sleep(1000);
            currentDate = new Date(System.currentTimeMillis());//更新时间

        }
    }
}
