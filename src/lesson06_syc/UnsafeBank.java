package lesson06_syc;

public class UnsafeBank {
    public static void main(String[] args) {
       Account account = new Account(1000,"结婚基金");
       Drawing drawingThread1 = new Drawing(account,50,"boy");
        Drawing drawingThread2 = new Drawing(account,100,"girl");
        drawingThread2.start();
        drawingThread1.start();
    }
}

//账户
class Account {
    int money;     //余额
    String name;   //卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread {
    Account account;    //账户
    int drawingMoney;   //取多少钱
    int handleMoney;    //手里多少钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //synchronized 默认锁的是this 这个类本身
    //此时应该锁的是账户，错译采取synchronized块的方式
    @Override
    public   void run() {
        //锁的对象就是变化的量，需要增删改的量
        synchronized (account){

            //判断有没有钱
            if (account.money - drawingMoney <= 0) {
                System.out.println(Thread.currentThread().getName() + ":卡里没钱了！");
                return;
            }
            //延时：暴露问题
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡里余额=余额-你取的钱
            account.money = account.money-drawingMoney;
            //持有的钱=持有的前+取出的前
            handleMoney = handleMoney+drawingMoney;
            System.out.println(account.name + "余额为：" + account.money);
            //Thread.currentThread().getName()与this.getName()相等，因为继承了Thread
            System.out.println(this.getName()+ "手里的钱为：" + handleMoney);
        }
    }
}