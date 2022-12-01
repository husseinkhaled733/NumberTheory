package org.example;

public class Main {
    public static void main(String[] args) {
        NumberTheory num = new NumberTheory();

        for (int i = 2; i < 100; i++) {
            if(num.MillerTest(i) != num.isPrime(i)) {
                System.out.println(i);
            }
        }
    }
}