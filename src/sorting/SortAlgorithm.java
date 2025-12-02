package sorting;

import util.MyArrayList;

/**
 * Interfaz que define el contrato para los algoritmos de ordenamiento.
 * Cada algoritmo debe implementar métodos para ordenar tanto arreglos primitivos
 * como MyArrayList de enteros.
 */
public interface SortAlgorithm {
    /**
     * Ordena un arreglo de enteros primitivos.
     * @param arr El arreglo a ordenar (se modifica in-place)
     */
    void sort(int[] arr);
    
    /**
     * Ordena una MyArrayList de enteros.
     * @param list La lista a ordenar (se modifica in-place)
     */
    void sort(MyArrayList list);
    
    /**
     * Retorna el nombre del algoritmo.
     * @return El nombre del algoritmo de ordenamiento
     */
    String getName();
}

