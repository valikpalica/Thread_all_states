package com.company;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Main{

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        Checker checker = new Checker(resource);
        Thread minor = new Thread(checker);
        System.out.println(minor.getState());
        minor.start();
        System.out.println(minor.getState());
        GetSleep();
        CheckStatus(minor,resource);
        System.out.println(minor.getState());
        GetSleep();
        System.out.println(minor.getState());
        minor.join();
        System.out.println(minor.getState());
    }


    public  static  void GetSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
    public static  void CheckStatus(Thread thread,Resource resource) throws InterruptedException {
        if (thread.getState().toString().equals("WAITING")){
            System.out.println(thread.getState());
            resource.notif();
        }

    }
}


class Checker implements Runnable{
    Resource res;
    Checker(Resource res){
        this.res = res;
    }
    @Override
    public void run() {
        try {
            res.check();
            for (int i =0;i<5;i++){
                System.out.println("second thread running item "+ i );
            }
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Resource{
    synchronized public void  check() throws InterruptedException {
            wait();
    }
    synchronized public void notif(){
        notifyAll();
    }
}




