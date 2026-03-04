package algorithms;

public class OrdenamientoBurbuja {
    
    // VERSIÓN ITERATIVA
    public void ordenamientoBurbujaIterativo(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // VERSIÓN RECURSIVA
    public void ordenamientoBurbujaRecursivo(int[] arr, int n) {
        if (n == 1) return;
        
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        ordenamientoBurbujaRecursivo(arr, n - 1);
    }
}
