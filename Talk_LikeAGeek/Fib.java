

import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;


class FibonacciTailRecursion {
    public static long fibonacciTail(int n) {
        return fibonacciHelper(n, 0, 1);
    }
    
    private static long fibonacciHelper(int n, long a, long b) {
        if (n == 0)
            return a;
        return fibonacciHelper(n - 1, b, a + b);
    }
    
    public static void main(String[] args) {
        int n = 10;
       // System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));
    }
}


 class FibonacciTailRecursive {

    // Tail-recursive helper method.
    // 'n' is the remaining count,
    // 'a' is the current Fibonacci number,
    // 'b' is the next Fibonacci number.
    private static BigInteger fibonacciTail(int n, BigInteger a, BigInteger b) {
        if (n == 0) {
            return a;
        }
        // Tail recursive call: update values and decrement n.
        return fibonacciTail(n - 1, b, a.add(b));
    }

    // Public method to compute the nth Fibonacci number.
    // It delegates to the tail-recursive helper with start values 0 and 1.
    public static BigInteger fibonacciTailBig(int n) {
        return fibonacciTail(n, BigInteger.ZERO, BigInteger.ONE);
    }

    public static void main(String[] args) {
        int n = 100; // Example: Compute the 100th Fibonacci number.
       // BigInteger result = fibonacci(n);
       // System.out.println("Fibonacci(" + n + ") = " + result);
    }
}

public class Fib {

    private static Map<Integer, Long> memo = new HashMap<>();
    
    public static long fibonacciM(int n) {
        if (n <= 1)
            return n;
        if (memo.containsKey(n))
            return memo.get(n);
        
        long result = fibonacciM(n - 1) + fibonacciM(n - 2);
        memo.put(n, result);
        return result;
    }

     public static BigInteger fibonacciB(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);
        
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger temp = a.add(b);
            a = b;
            b = temp;
        }
        return b;
    }

     public static long fibonacciR(int n) {
        if (n <= 1) return n;
        return fibonacciR(n - 1) + fibonacciR(n - 2);
    }

    public static long fibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
    
    public static void main(String[] args) {

        if ( args.length != 1 ) {

		System.out.println("Give an argument");
		return;
        }
        int n = Integer.parseInt(args[0]);
      //  System.out.println("Fibonacci(" + n + ") = " + fibonacciM(n));
        System.out.println("Fibonacci(" + n + ") = " + fibonacciB(n));
        System.out.println("Fibonacci(" + n + ") = " + FibonacciTailRecursive.fibonacciTailBig(n));


    }
}
