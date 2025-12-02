package util;

/**
 * Implementación propia de generador de números aleatorios.
 * Usa un algoritmo LCG (Linear Congruential Generator) simple.
 */
public class MyRandom {
    private long seed;
    private static final long MULTIPLIER = 1103515245L;
    private static final long INCREMENT = 12345L;
    private static final long MODULUS = 2147483648L; // 2^31
    
    public MyRandom() {
        this.seed = System.currentTimeMillis() % MODULUS;
    }
    
    public MyRandom(long seed) {
        this.seed = seed % MODULUS;
    }
    
    public int nextInt() {
        seed = (MULTIPLIER * seed + INCREMENT) % MODULUS;
        return (int) seed;
    }
    
    public int nextInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be positive");
        }
        return Math.abs(nextInt()) % bound;
    }
}

