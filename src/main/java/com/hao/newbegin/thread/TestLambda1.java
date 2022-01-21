package com.hao.newbegin.thread;

/**
 * @author zhhao
 * @date 2022-01-19 14:57
 * 没有入参的lambda表达式及各个方法的演化
 */
public class TestLambda1 {
    //3,静态内部类
    static class Like2 implements Ilike{
        @Override
        public void lamdda() {
            System.out.println("I like lambda2");
        }
    }
    public static void main(String[] args) {
        Ilike ilike=new Like();//通过接口初始化实现类
        ilike.lamdda();
        //静态内部类
        Like2 like2=new Like2();
        like2.lamdda();

        //4,局部内部类
        class Like3 implements Ilike{
            @Override
            public void lamdda() {
                System.out.println("I like lambda3");
            }
        }
        Like3 like3=new Like3();
        like3.lamdda();

        //5,匿名内部类，没有类的名称，必须借助接口或者父类
        new Ilike(){
            @Override
            public void lamdda() {
                System.out.println("I like lambda4");
            }
        }.lamdda();
        //6,用lambda简化
        ilike=()->{
            System.out.println("I like lambda5");
        };
        ilike.lamdda();
    }
}
//1,定义一个函数式接口
interface Ilike{
    void lamdda();
}
//2,实现类
class Like implements Ilike{
    @Override
    public void lamdda() {
        System.out.println("I like lambda");
    }
}