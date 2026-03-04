package benchmark;

import algorithms.*;
import utils.GeneradorDatos;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Medidor {
    private static final String RUTA = "src/resultados/tiempos.csv";
    private static final int NUM_EJECUCIONES = 5; // 5 repeticiones para cada prueba
    
    public void iniciarPruebas() {
        File folder = new File("src/resultados");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        try (FileWriter writer = new FileWriter(RUTA)) {
            writer.write("Algoritmo,Tamaño,Versión,Ej_1_ms,Ej_2_ms,Ej_3_ms,Ej_4_ms,Ej_5_ms\n");
            
            // Crear instancias de los algoritmos
            Factorial factorial = new Factorial();
            Fibonacci fibonacci = new Fibonacci();
            BusquedaLineal busqueda = new BusquedaLineal();
            OrdenamientoBurbuja ordenamiento = new OrdenamientoBurbuja();
            
            // PRUEBAS DE FACTORIAL
            pruebasFactorial(factorial, writer);
            
            // PRUEBAS DE FIBONACCI
            pruebasFibonacci(fibonacci, writer);
            
            // PRUEBAS DE BÚSQUEDA LINEAL
            pruebasBusquedaLineal(busqueda, writer);
            
            // PRUEBAS DE ORDENAMIENTO BURBUJA
            pruebasOrdenamientoBurbuja(ordenamiento, writer);
            
            System.out.println("✓ Pruebas completadas. Resultados en: " + RUTA);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ========== FACTORIAL ==========
    private void pruebasFactorial(Factorial factorial, FileWriter writer) throws IOException {
        System.out.println("\n📊 Midiendo FACTORIAL...");
        
        int[] tamaños = {5, 10, 15, 20, 25, 30};
        
        for (int tamaño : tamaños) {
            // VERSIÓN ITERATIVA
            double[] tiemposIter = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                long inicio = System.nanoTime();
                long resultado = factorial.factorialIterativo(tamaño);
                long fin = System.nanoTime();
                tiemposIter[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "Factorial", tamaño, "Iterativa", tiemposIter);
            System.out.println("  Iterativo (n=" + tamaño + "): " + promediar(tiemposIter) + " ms");
            
            // VERSIÓN RECURSIVA
            double[] tiemposRec = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                long inicio = System.nanoTime();
                long resultado = factorial.factorialRecursivo(tamaño);
                long fin = System.nanoTime();
                tiemposRec[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "Factorial", tamaño, "Recursiva", tiemposRec);
            System.out.println("  Recursivo (n=" + tamaño + "): " + promediar(tiemposRec) + " ms");
        }
    }
    
    // ========== FIBONACCI ==========
    private void pruebasFibonacci(Fibonacci fibonacci, FileWriter writer) throws IOException {
        System.out.println("\n📊 Midiendo FIBONACCI...");
        
        int[] tamaños = {10, 15, 20, 25};
        
        for (int tamaño : tamaños) {
            // VERSIÓN ITERATIVA
            double[] tiemposIter = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                long inicio = System.nanoTime();
                long resultado = fibonacci.fibonacciIterativo(tamaño);
                long fin = System.nanoTime();
                tiemposIter[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "Fibonacci", tamaño, "Iterativa", tiemposIter);
            System.out.println("  Iterativo (n=" + tamaño + "): " + promediar(tiemposIter) + " ms");
            
            // VERSIÓN RECURSIVA (solo hasta n=20 porque es exponencial)
            if (tamaño <= 20) {
                double[] tiemposRec = new double[NUM_EJECUCIONES];
                for (int e = 0; e < NUM_EJECUCIONES; e++) {
                    long inicio = System.nanoTime();
                    long resultado = fibonacci.fibonacciRecursivo(tamaño);
                    long fin = System.nanoTime();
                    tiemposRec[e] = (fin - inicio) / 1_000_000.0;
                }
                escribirFila(writer, "Fibonacci", tamaño, "Recursiva", tiemposRec);
                System.out.println("  Recursivo (n=" + tamaño + "): " + promediar(tiemposRec) + " ms");
            }
        }
    }
    
    // ========== BÚSQUEDA LINEAL ==========
    private void pruebasBusquedaLineal(BusquedaLineal busqueda, FileWriter writer) throws IOException {
        System.out.println("\n📊 Midiendo BÚSQUEDA LINEAL...");
        
        int[] tamaños = {1000, 5000, 10000, 50000};
        
        for (int tamaño : tamaños) {
            int[] datos = GeneradorDatos.generarArrayAleatorio(tamaño);
            int objetivo = datos[tamaño / 2];
            
            // VERSIÓN ITERATIVA
            double[] tiemposIter = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                long inicio = System.nanoTime();
                int resultado = busqueda.busquedaLinealIterativa(datos, objetivo);
                long fin = System.nanoTime();
                tiemposIter[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "BusquedaLineal", tamaño, "Iterativa", tiemposIter);
            System.out.println("  Iterativa (n=" + tamaño + "): " + promediar(tiemposIter) + " ms");
            
            // VERSIÓN RECURSIVA
            double[] tiemposRec = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                long inicio = System.nanoTime();
                int resultado = busqueda.busquedaLinealRecursiva(datos, objetivo, 0);
                long fin = System.nanoTime();
                tiemposRec[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "BusquedaLineal", tamaño, "Recursiva", tiemposRec);
            System.out.println("  Recursiva (n=" + tamaño + "): " + promediar(tiemposRec) + " ms");
        }
    }
    
    // ========== ORDENAMIENTO BURBUJA ==========
    private void pruebasOrdenamientoBurbuja(OrdenamientoBurbuja ordenamiento, FileWriter writer) throws IOException {
        System.out.println("\n📊 Midiendo ORDENAMIENTO BURBUJA...");
        
        int[] tamaños = {100, 500, 1000, 2000};
        
        for (int tamaño : tamaños) {
            // VERSIÓN ITERATIVA
            double[] tiemposIter = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                int[] datos = GeneradorDatos.generarArrayAleatorio(tamaño);
                long inicio = System.nanoTime();
                ordenamiento.ordenamientoBurbujaIterativo(datos);
                long fin = System.nanoTime();
                tiemposIter[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "OrdenamientoBurbuja", tamaño, "Iterativa", tiemposIter);
            System.out.println("  Iterativa (n=" + tamaño + "): " + promediar(tiemposIter) + " ms");
            
            // VERSIÓN RECURSIVA
            double[] tiemposRec = new double[NUM_EJECUCIONES];
            for (int e = 0; e < NUM_EJECUCIONES; e++) {
                int[] datos = GeneradorDatos.generarArrayAleatorio(tamaño);
                long inicio = System.nanoTime();
                ordenamiento.ordenamientoBurbujaRecursivo(datos, datos.length);
                long fin = System.nanoTime();
                tiemposRec[e] = (fin - inicio) / 1_000_000.0;
            }
            escribirFila(writer, "OrdenamientoBurbuja", tamaño, "Recursiva", tiemposRec);
            System.out.println("  Recursiva (n=" + tamaño + "): " + promediar(tiemposRec) + " ms");
        }
    }
    
    // Método auxiliar para escribir una fila del CSV
    private void escribirFila(FileWriter writer, String algoritmo, int tamaño, String version, double[] tiempos) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(algoritmo).append(",");
        sb.append(tamaño).append(",");
        sb.append(version);
        
        for (double tiempo : tiempos) {
            sb.append(",").append(String.format("%.6f", tiempo));
        }
        
        sb.append("\n");
        writer.write(sb.toString());
    }
    
    // Método auxiliar para calcular el promedio
    private double promediar(double[] tiempos) {
        double suma = 0;
        for (double t : tiempos) {
            suma += t;
        }
        return suma / tiempos.length;
    }
}
