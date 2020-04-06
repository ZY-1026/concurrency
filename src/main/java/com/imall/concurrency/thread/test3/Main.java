package com.imall.concurrency.thread.test3;

class PublicVar {
    public String username = "A";
    public String passwd = "AA";

    synchronized public void setValue(String username, String passwd) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.passwd = passwd;
            System.out.println("setValue method thread name = "
                    + Thread.currentThread().getName() + "  username = "
                    + username + "   passwd=" + passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void getValue() {
        System.out.println("getValue method thread name = "
                + Thread.currentThread().getName() + "  username = "
                + username + "   passwd=" + passwd);
    }
}


class ThreadA extends Thread {
    private PublicVar publicVar;

    public ThreadA(PublicVar publicVar) {
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B", "BB");
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PublicVar publicVar = new PublicVar();
        ThreadA threadA = new ThreadA(publicVar);
        threadA.start();
        threadA.sleep(200);
        publicVar.getValue();
    }
}
