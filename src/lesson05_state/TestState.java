package lesson05_state;

import java.util.concurrent.ThreadLocalRandom;

public class TestState   {

    public static void main(String[] args) {

        Thread thread = new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("/////////");
        });
        Thread.State state = thread.getState();
        System.out.println(state);

        thread.start();
        state = thread.getState();
        System.out.println(state);
        while(state!= Thread.State.TERMINATED){
            System.out.println(state);
            state =thread.getState();
        }
    }
}
