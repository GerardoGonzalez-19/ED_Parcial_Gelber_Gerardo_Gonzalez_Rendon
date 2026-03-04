package benchmark;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("EXAMEN PARCIAL - ESTRUCTURA DE DATOS");
        System.out.println("Análisis de Algoritmos Iterativos vs Recursivos");
        System.out.println("=".repeat(50));
        
        Medidor medidor = new Medidor();
        medidor.iniciarPruebas();
    }
}
