package concurrent;

import sorting.SortAlgorithm;
import util.MyArrayList;
import util.MyList;

/**
 * Tarea que ejecuta un algoritmo de ordenamiento de forma concurrente.
 * Implementa Runnable para ser ejecutada en un hilo separado.
 * 
 * Esta clase gestiona la ejecución repetida del algoritmo sobre diferentes
 * colecciones de datos hasta que expire el tiempo límite especificado.
 */
public class SortingTask implements Runnable {
    private SortAlgorithm algorithm;
    private MyList collections;
    private long endTime;
    private Result result;
    
    /**
     * Clase auxiliar para almacenar una colección individual (array O MyArrayList).
     */
    public static class CollectionItem {
        public int[] array;
        public MyArrayList list;
        public String description;
        public boolean isArray; // true si es array, false si es lista
        
        public CollectionItem(int[] array, MyArrayList list, String description) {
            if (array != null && list != null) {
                throw new IllegalArgumentException("Debe ser solo array o solo lista, no ambos");
            }
            if (array == null && list == null) {
                throw new IllegalArgumentException("Debe tener array o lista");
            }
            this.array = array;
            this.list = list;
            this.description = description;
            this.isArray = (array != null);
        }
        
        /**
         * Crea una copia profunda de la colección.
         */
        public CollectionItem copy() {
            if (isArray) {
                return new CollectionItem(array.clone(), null, description);
            } else {
                MyArrayList listCopy = new MyArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    listCopy.add(list.get(i));
                }
                return new CollectionItem(null, listCopy, description);
            }
        }
    }
    
    /**
     * Constructor de la tarea de ordenamiento.
     * 
     * @param algorithm El algoritmo de ordenamiento a ejecutar
     * @param collections Lista de colecciones individuales a ordenar (12 en total)
     * @param endTime Timestamp en milisegundos cuando debe terminar la ejecución
     */
    public SortingTask(SortAlgorithm algorithm, MyList collections, long endTime) {
        this.algorithm = algorithm;
        this.collections = collections;
        this.endTime = endTime;
        this.result = new Result(algorithm.getName(), collections.size()); // Una colección por item
    }
    
    @Override
    public void run() {
        String algoPrefix = "[" + algorithm.getName() + "]";
        System.out.println(algoPrefix + " Iniciando ejecución en hilo " + Thread.currentThread().getName());
        
        // Crear copias de las colecciones para cada iteración
        MyList workingCollections = new MyList();
        for (int i = 0; i < collections.size(); i++) {
            CollectionItem item = collections.get(i);
            workingCollections.add(item.copy());
        }
        
        int expectedCollections = collections.size(); // 12 colecciones en total
        boolean firstIterationComplete = false;
        
        // Ejecutar mientras no haya expirado el tiempo
        while (System.currentTimeMillis() < endTime) {
            int collectionsInThisIteration = 0;
            
            // Iterar sobre todas las colecciones
            for (int i = 0; i < workingCollections.size(); i++) {
                CollectionItem item = workingCollections.get(i);
                
                // Verificar si aún hay tiempo antes de cada ordenamiento
                if (System.currentTimeMillis() >= endTime) {
                    break;
                }
                
                // Ordenar según el tipo de colección
                String tipoEstructura = item.isArray ? "ARRAY" : "ArrayList";
                System.out.println(algoPrefix + " Ordenando " + tipoEstructura + " - " + item.description);
                
                long startTime = System.currentTimeMillis();
                if (item.isArray) {
                    algorithm.sort(item.array);
                } else {
                    algorithm.sort(item.list);
                }
                long endSort = System.currentTimeMillis();
                long duration = endSort - startTime;
                
                // Solo contar si el ordenamiento terminó dentro del tiempo límite
                if (endSort < endTime) {
                    result.addCollection(duration, item.isArray);
                    collectionsInThisIteration++;
                    System.out.println(algoPrefix + " ✓ " + tipoEstructura + " completado en " + duration + " ms");
                } else {
                    // El ordenamiento terminó después del tiempo límite
                    long tiempoExcedido = endSort - endTime;
                    System.out.println(algoPrefix + " ⚠ " + tipoEstructura + " DESCALIFICADO - Excedió " + 
                        tiempoExcedido + " ms del límite (tomó " + duration + " ms)");
                    break;
                }
            }
            
            // Registrar si completó la primera iteración completa
            if (!firstIterationComplete) {
                if (collectionsInThisIteration >= expectedCollections) {
                    firstIterationComplete = true;
                }
            }
            
            // Si aún hay tiempo, preparar nuevas copias para la siguiente iteración
            if (System.currentTimeMillis() < endTime) {
                workingCollections.clear();
                for (int i = 0; i < collections.size(); i++) {
                    CollectionItem item = collections.get(i);
                    workingCollections.add(item.copy());
                }
            } else {
                // Tiempo expirado, salir del bucle principal
                break;
            }
        }
        
        // Marcar como completo si logró ordenar todas las colecciones en la primera iteración
        result.setCompletedAll(firstIterationComplete);
        
        System.out.println(algoPrefix + " FINALIZADO - Colecciones ordenadas: " + 
            result.getCollectionsSorted() + " de " + result.getTotalCollections() + 
            " (Arrays: " + result.getArraysSorted() + ", ArrayLists: " + result.getArrayListsSorted() + ")");
    }
    
    /**
     * Obtiene el resultado de la ejecución.
     * @return El objeto Result con las métricas de rendimiento
     */
    public Result getResult() {
        return result;
    }
}

