package main.other;

import java.math.BigInteger;

public class KaratsubaMultiplication {

    /*
     * Please pass only integer numbers
     */
    public static BigInteger multiply(Number x, Number y) {
        return multiply(new BigInteger(x.toString()), new BigInteger(y.toString()));
    }

    public static BigInteger multiply(BigInteger x, BigInteger y) {

        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);    // optimize this parameter
        // if less than regular multiplication is faster for this numbers

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger abcd = multiply(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
    }
}
