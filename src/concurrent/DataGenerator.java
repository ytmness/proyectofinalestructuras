package concurrent;

import util.MyArrayList;
import util.MyList;
import util.MyRandom;

/**
 * Clase utilitaria para generar datos de prueba aleatorios.
 * Genera las colecciones especificadas en el proyecto:
 * - 100 elementos aleatorios
 * - 50,000 elementos aleatorios
 * - 100,000 elementos aleatorios
 * - 100,000 elementos aleatorios entre 1 y 5
 */
public class DataGenerator {
    private MyRandom random;
    
    public DataGenerator() {
        this.random = new MyRandom();
    }
    
    public DataGenerator(long seed) {
        this.random = new MyRandom(seed);
    }
    
    /**
     * Genera un arreglo de enteros aleatorios.
     * @param size Tamaño del arreglo
     * @return Arreglo de enteros aleatorios
     */
    public int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }
    
    /**
     * Genera un arreglo de enteros aleatorios en un rango específico.
     * @param size Tamaño del arreglo
     * @param min Valor mínimo (inclusive)
     * @param max Valor máximo (inclusive)
     * @return Arreglo de enteros aleatorios en el rango especificado
     */
    public int[] generateRandomArray(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }
    
    /**
     * Convierte un arreglo de enteros a una MyArrayList de Integer.
     * @param arr El arreglo a convertir
     * @return MyArrayList con los mismos valores que el arreglo
     */
    public MyArrayList arrayToMyArrayList(int[] arr) {
        MyArrayList list = new MyArrayList(arr.length);
        for (int value : arr) {
            list.add(value);
        }
        return list;
    }
    
    /**
     * Genera todas las colecciones de prueba especificadas en el proyecto.
     * Genera 12 colecciones: 6 como int[] y 6 como MyArrayList.
     * @return Lista de colecciones individuales (no pares)
     */
    public MyList generateAllCollections() {
        MyList collections = new MyList();
        
        System.out.println("Generando datos de prueba...");
        
        // 100 elementos aleatorios - como int[]
        System.out.println("Generando 100 elementos (array)...");
        int[] arr100 = generateRandomArray(100);
        collections.add(new SortingTask.CollectionItem(arr100, null, "100 elementos (array)"));
        
        // 100 elementos aleatorios - como MyArrayList
        MyArrayList list100 = arrayToMyArrayList(arr100);
        collections.add(new SortingTask.CollectionItem(null, list100, "100 elementos (ArrayList)"));
        
        // 1,000 elementos aleatorios - como int[]
        System.out.println("Generando 1,000 elementos (array)...");
        int[] arr1k = generateRandomArray(1000);
        collections.add(new SortingTask.CollectionItem(arr1k, null, "1,000 elementos (array)"));
        
        // 1,000 elementos aleatorios - como MyArrayList
        MyArrayList list1k = arrayToMyArrayList(arr1k);
        collections.add(new SortingTask.CollectionItem(null, list1k, "1,000 elementos (ArrayList)"));
        
        // 10,000 elementos aleatorios - como int[]
        System.out.println("Generando 10,000 elementos (array)...");
        int[] arr10k = generateRandomArray(10000);
        collections.add(new SortingTask.CollectionItem(arr10k, null, "10,000 elementos (array)"));
        
        // 10,000 elementos aleatorios - como MyArrayList
        MyArrayList list10k = arrayToMyArrayList(arr10k);
        collections.add(new SortingTask.CollectionItem(null, list10k, "10,000 elementos (ArrayList)"));
        
        // 50,000 elementos aleatorios - como int[]
        System.out.println("Generando 50,000 elementos (array)...");
        int[] arr50k = generateRandomArray(50000);
        collections.add(new SortingTask.CollectionItem(arr50k, null, "50,000 elementos (array)"));
        
        // 50,000 elementos aleatorios - como MyArrayList
        MyArrayList list50k = arrayToMyArrayList(arr50k);
        collections.add(new SortingTask.CollectionItem(null, list50k, "50,000 elementos (ArrayList)"));
        
        // 100,000 elementos aleatorios - como int[]
        System.out.println("Generando 100,000 elementos (array)...");
        int[] arr100k = generateRandomArray(100000);
        collections.add(new SortingTask.CollectionItem(arr100k, null, "100,000 elementos (array)"));
        
        // 100,000 elementos aleatorios - como MyArrayList
        MyArrayList list100k = arrayToMyArrayList(arr100k);
        collections.add(new SortingTask.CollectionItem(null, list100k, "100,000 elementos (ArrayList)"));
        
        // 100,000 elementos aleatorios entre 1 y 5 - como int[]
        System.out.println("Generando 100,000 elementos rango 1-5 (array)...");
        int[] arr100kRange = generateRandomArray(100000, 1, 5);
        collections.add(new SortingTask.CollectionItem(arr100kRange, null, "100,000 elementos 1-5 (array)"));
        
        // 100,000 elementos aleatorios entre 1 y 5 - como MyArrayList
        MyArrayList list100kRange = arrayToMyArrayList(arr100kRange);
        collections.add(new SortingTask.CollectionItem(null, list100kRange, "100,000 elementos 1-5 (ArrayList)"));
        
        System.out.println("Datos de prueba generados exitosamente.\n");
        return collections;
    }
}

