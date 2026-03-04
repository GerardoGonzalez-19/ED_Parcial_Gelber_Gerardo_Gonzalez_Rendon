package algorithms;

public class Factorial {
    
    // VERSIÓN ITERATIVA
    public long factorialIterativo(int n) {
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    
    // VERSIÓN RECURSIVA
    public long factorialRecursivo(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorialRecursivo(n - 1);
    }
}
