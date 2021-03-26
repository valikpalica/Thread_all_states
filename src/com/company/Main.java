package com.company;

import java.util.concurrent.TimeUnit;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        SecondThread secondThread = new SecondThread();
        Thread thread2  = new Thread(secondThread);
        System.out.println(thread2.getState());
        thread2.start();
        System.out.println(thread2.getState());
        GetSleep();
        System.out.println(thread2.getState());
        CheckStatus(thread2);

       /* Container container = new Container();
        Major major = new Major(container);
        Thread major_thread = new Thread(major);
        Minor minor = new Minor(container);
        Thread minor_thread = new Thread(minor);
        System.out.println("Major create "+major_thread.getState());
        major_thread.start();
        minor_thread.start();
        major_thread.join();
        System.out.println("Major end "+major_thread.getState());*/

//        Sharing sharing = new Sharing();
//        Thread thread1 = new Thread(new T1(sharing),"sleeped ");
//        Thread thread2 = new Thread(new T2(sharing), "jober");
//        thread1.start();//1
//        TimeUnit.SECONDS.sleep(1);
//        thread2.start();
    }


    public  static  void GetSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
    public static  void CheckStatus(Thread thread) throws InterruptedException {
        if (thread.getState().toString().equals("TIMED_WAITING")){
            thread.join();
            System.out.println(thread.getState());
        }
    }

}


class SecondThread implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i =0;i<5;i++){
            System.out.println("second thread running item "+ i );
        }
    }
}



/*

class T1 implements Runnable{
    Sharing sharing;

    public T1(Sharing sharing) {
        this.sharing = sharing;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sharing.getInt();
        System.out.println(Thread.currentThread().getName()+ " "+ Thread.currentThread().getState());
    }
}
class T2 implements Runnable{
    Sharing sharing;

    public T2(Sharing sharing) {
        this.sharing = sharing;
    }

    @Override
    public void run() {
        while (true){
            sharing.getInt();
        }
    }
}




class Container{
    private int _count = 0;
    synchronized public void put() throws InterruptedException {
        _count++;
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("minor "+Thread.currentThread().getState());
        notify();
    }
    synchronized public void get() throws  InterruptedException{
        while (_count == 0){
            System.out.println("!");
            wait();
            System.out.println("Major "+Thread.currentThread().getState());
        }
        _count--;
        notify();
    }
}
class Major implements Runnable{
    Container _container;
    Major(Container container){
        this._container = container;
    }
    @Override
   public void run() {
        try {
            System.out.println("Major "+Thread.currentThread().getState());
            _container.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
class Minor implements Runnable{
    Container _container;
    Minor(Container container){
        this._container = container;
    }
    @Override
     public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            _container.put();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//
//    while (true);
    }
}
class Sharing{
    public synchronized int getInt(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
*/
