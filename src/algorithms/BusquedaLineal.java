package algorithms;

public class BusquedaLineal {
    
    // VERSIÓN ITERATIVA
    public int busquedaLinealIterativa(int[] arr, int objetivo) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == objetivo) {
                return i;
            }
        }
        return -1; // No encontrado
    }
    
    // VERSIÓN RECURSIVA
    public int busquedaLinealRecursiva(int[] arr, int objetivo, int indice) {
        if (indice >= arr.length) {
            return -1;
        }
        if (arr[indice] == objetivo) {
            return indice;
        }
        return busquedaLinealRecursiva(arr, objetivo, indice + 1);
    }
}
