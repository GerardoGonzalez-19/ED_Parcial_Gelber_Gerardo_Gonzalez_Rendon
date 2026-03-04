package utils;

import java.util.Random;

public class GeneradorDatos {
    private static Random random = new Random();
    
    // Generar array de números aleatorios
    public static int[] generarArrayAleatorio(int tamaño) {
        int[] arr = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
    
    // Copiar un array (para no modificar el original)
    public static int[] copiarArray(int[] arr) {
        int[] copia = new int[arr.length];
        System.arraycopy(arr, 0, copia, 0, arr.length);
        return copia;
    }
}
