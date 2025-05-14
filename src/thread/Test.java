package thread;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) {
//        Thread thread = new Thread02();
//        thread.setName("nb");
//        thread.start();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + "-->" + i);
//        }
//        Thread t2 = Thread02.currentThread();
//        System.out.println(t2.getName());

        JFrame x = new JFrame();
        JPanel p = new JPanel();
        JPanel left = new JPanel();
        left.setSize(800,600);
        left.setBackground(Color.red);
        p.add(left);
        //p.add(new JLabel("asfaef"));
        x.add(p);
        //x.setSize(1000,800);
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setVisible(true);
    }
}

