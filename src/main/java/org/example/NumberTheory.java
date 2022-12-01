package org.example;

import java.util.ArrayList;
import java.util.List;

public class NumberTheory {

    // takes a number and returns an arraylist of all primes < number
    public ArrayList<Integer> sieveOfEratosthenes(int number) {
        ArrayList<Integer> v = new ArrayList<>();
        ArrayList<Boolean> isPrime = new ArrayList<>();
        for (int i = 0; i < number + 1; i++) isPrime.add(true);

        isPrime.set(0, false);
        isPrime.set(1, false);

        for (int i = 2; i * i <= number; i++) {
            if (isPrime.get(i)) {
                for (int j = i * 2; j <= number; j += i) {
                    isPrime.set(j, false);
                }
            }
        }

        for (int i = 0; i < isPrime.size(); ++i) {
            if (isPrime.get(i)) v.add(i);
        }

        return v;
    }

    // takes a number and returns an arraylist of all the prime factors of that number
    public ArrayList<Long> TrialDivision(long number) {
        ArrayList<Long> a = new ArrayList<>();
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                a.add(i);
                while (number % i == 0) number /= i;
            }
        }
        if (number != 1) a.add(number);
        return a;
    }

    // returns {gcd(a,b) , s , t}
    public ArrayList<Long> ExtendedEuclidean(long a, long b) {

        ArrayList<Long> r = new ArrayList<>();
        ArrayList<Long> s = new ArrayList<>();
        ArrayList<Long> t = new ArrayList<>();
        ArrayList<Long> q = new ArrayList<>();
        r.add(a);
        r.add(b);
        s.add(1L);
        s.add(0L);
        t.add(0L);
        t.add(1L);
        q.add(0L);
        q.add(a / b);
        int j = 2;
        while ((r.get(j - 2) % r.get(j - 1)) != 0) {
            r.add(r.get(j - 2) - (r.get(j - 1) * q.get(j - 1)));
            s.add(s.get(j - 2) - (s.get(j - 1) * q.get(j - 1)));
            t.add(t.get(j - 2) - (t.get(j - 1) * q.get(j - 1)));
            q.add((r.get(j - 1) / r.get(j)));
            j++;
        }
        return new ArrayList<>(List.of(r.get(j - 1), s.get(j - 1), s.get(j - 1)));
    }



    public long ChineseRemainderTheorem(ArrayList<Long> a,ArrayList<Long> m){

        long M = 1,x = 0;
        for (Long num : m) M *= num;

        // x = a1*M1*y1 + ...

        for (int i = 0; i < a.size(); i++)
            x += ((a.get(i) % M) * ((M / m.get(i))%M) * (modInverse(M/m.get(i),m.get(i))%M))%M ;

        return x;
    }


    public boolean MillerTest(long number) {

        if (number < 3) return number % 2 == 0;
        if (number % 2 == 0) return false;

        // n-1 = 2^k * m
        long n = number - 1, k = 0, a;
        while (n % 2 == 0) {
            n /= 2;
            k++;
        }

        n = number - 1;
        long m = n / (long) Math.pow(2, k);
        a = (long) (Math.random() * (n - 1) + 2);

        long b = (long) Math.pow(a, m) % number;
        if (b == 1) return false;
        else if (b == -1 || b == n) return true;

        while (m != n) {
            b = (b * b) % number;
            m *= 2;

            if (b == 1) return false;
            else if (b == n) return true;
        }

        return false;
    }

    int modInverse(long a, long m) {
        ArrayList<Long> result = ExtendedEuclidean(a, m);
        long d = result.get(0);
        if (d != 1) return 1;
        return (int) ((result.get(1) + m) % m);
    }

    public boolean isPrime(long n) {
        if (n == 2) return true;
        if (n < 2 || n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
