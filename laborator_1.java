import java.util.Scanner;
class Factorial {
    static int Fib(int x) {
        int a,b,c;
        a = b = 1;
        int nr = 3;
        while(nr <= x) {
            c = a + b;
            nr++;
            a=b;
            b=c;
        }
        return b;

    }
    static boolean isPrime(int x) {
        int i;
        if(x == 0 || x == 1) {
            return false;
        }
        if (x == 2)
        {
            return true;
        }

        if (x % 2 == 0) {
            return false;
        }
        for(i=3 ; i*i<=x ; i+=2)
        {
            if (x%i == 0)
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner myNumber = new Scanner(System.in);
        System.out.println("Scrie un numar: ");

        int number = myNumber.nextInt();
        int factorial = 1;
        int i;
        for(i=1;i<=number;i++)
            factorial *= i;
        
        System.out.println("Factorialul numarului dat este: " + factorial);
        System.out.println("Numarul este prim: " + isPrime(number));
        System.out.println("Al n-lea nr Fib este: " + Fib(number));
    }
}