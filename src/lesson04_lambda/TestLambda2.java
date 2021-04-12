package lesson04_lambda;

public class TestLambda2 {

    //静态内部类
    static class Love2 implements ILove {

        @Override
        public void love(int a) {
            System.out.println("I love " + a);
        }
    }

    public static void main(String[] args) {
        ILove love = new Love();
        love.love(1);

        love = new Love2();
        love.love(2);
        //局部内部类
        class Love3 implements ILove {

            @Override
            public void love(int a) {
                System.out.println("I love " + a);
            }
        }
        love = new Love3();
        love.love(3);

        //匿名内部类
        love = new ILove() {
            @Override
            public void love(int a) {
                System.out.println("I love "+a);
            }
        };
        love.love(4);
        //lambda 标识简化
        love = (int a) -> {
            System.out.println("I love " + a);
        };
        love.love(5);
        //简化1.去掉参数类型
        love = (a) -> {
            System.out.println("I love " + a);
        };
        love.love(6);

        //简化2.简化括号
        love= a->{
            System.out.println("I love "+a);
        };
        love.love(7);
        //简化3 去掉花括号
        love=a-> System.out.println("I love "+a);
        love.love(8);
    }
}

interface ILove {
    void love(int a);
}

class Love implements ILove {

    @Override
    public void love(int a) {
        System.out.println("I love " + a);
    }
}