package com.hao.newbegin.thread;

/**
 * @author zhhao
 * @date 2022-01-19 15:29
 * 测试有一个入参的lambda表达式
 */
public class TestLambda2 {
    //3,静态内部类
   static class Love2 implements ILove{
        @Override
        public void love(int a) {
            System.out.println("I love you===>"+a);
        }
    }
    public static void main(String[] args) {
        ILove love=new Love1();
        love.love(1);
        //静态内部类
        Love2 love2=new Love2();
        love2.love(2);

        //4，局部内部类
        class Love3 implements ILove{
            @Override
            public void love(int a) {
                System.out.println("I love you===>"+a);
            }
        }
        Love3 love3=new Love3();
        love3.love(3);

        //5,匿名内部类
        new ILove(){
            @Override
            public void love(int a) {
                System.out.println("I love you===>"+a);
            }
        }.love(4);

        //6,lambda表达式
       love= (int a)->{
            System.out.println("I love you===>"+a);
        };
       love.love(5);


       //简化lambda表达式
       //简化1：去掉传入的参数类型
        love=(a)->{
            System.out.println("I love you===>"+a);
        };
        //简化2：简化括号
        love=a->{
            System.out.println("I love you===>"+a);
        };
        //简化3：去掉花括号,如果有多行就不能去掉花括号，需要用代码块包裹
        love=a-> System.out.println("I love you===>"+a);
        //前提是必须是函数式接口，即只有一个抽象方法
        //多个参数也可以去掉参数类型，要去掉就都去掉，必须加上括号
    }
}
//1,定义一个接口
interface ILove{
    void love(int a);
}
//2，定义实现类
class Love1 implements ILove{
    @Override
    public void love(int a) {
        System.out.println("I love you===>"+a);
    }
}