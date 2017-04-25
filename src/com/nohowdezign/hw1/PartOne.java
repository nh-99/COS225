package com.nohowdezign.hw1;

/**
 * Created by Noah on 1/26/2017.
 */
public class PartOne {

    public static void main(String[] args) {
        PartOne one = new PartOne();
        //one.questionOne();
        one.questionTwo();
    }

    public void questionOne() {
        double x = 2.0;
        double y = 1.5;
        int m = 15;
        int n = 4;

        System.out.println(n * 4 / 3);
        System.out.println(x + m * n - 1);
        System.out.println(33 % 7 - (1+x) * n);
        System.out.println(15 + x / y + n);
        System.out.println(3 * (double) 1/2);
        System.out.println(m % n);
        System.out.println(x * y + n);
        System.out.println((int) x * (int) y);
        System.out.println((double) m/n);
        System.out.println(++n * m);
    }

    public void questionTwo() {
        int n = 2;
        System.out.println("I saw " + n + " squirrels\ntoday" + ".");
        System.out.print("\"In spring I hope to see " + n + n + " squirrels.\"");
    }

}
