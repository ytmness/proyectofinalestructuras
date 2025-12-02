package concurrent;

/**
 * Clase que almacena los resultados de la ejecución de un algoritmo de ordenamiento.
 * Contiene métricas de rendimiento como cantidad de colecciones ordenadas,
 * tiempo promedio y si completó todas las tareas.
 * También rastrea tiempos separados para arrays y ArrayLists.
 */
public class Result {
    private String algorithmName;
    private int collectionsSorted;
    private int totalCollections;
    private long totalTime;
    private double avgTime;
    private boolean completedAll;
    
    // Estadísticas separadas por tipo de estructura
    private int arraysSorted;
    private int arrayListsSorted;
    private long totalArrayTime;
    private long totalArrayListTime;
    private double avgArrayTime;
    private double avgArrayListTime;
    
    public Result(String algorithmName, int totalCollections) {
        this.algorithmName = algorithmName;
        this.totalCollections = totalCollections;
        this.collectionsSorted = 0;
        this.totalTime = 0;
        this.avgTime = 0.0;
        this.completedAll = false;
        this.arraysSorted = 0;
        this.arrayListsSorted = 0;
        this.totalArrayTime = 0;
        this.totalArrayListTime = 0;
        this.avgArrayTime = 0.0;
        this.avgArrayListTime = 0.0;
    }
    
    public void addCollection(long timeMs, boolean isArray) {
        collectionsSorted++;
        totalTime += timeMs;
        avgTime = (double) totalTime / collectionsSorted;
        
        if (isArray) {
            arraysSorted++;
            totalArrayTime += timeMs;
            avgArrayTime = (double) totalArrayTime / arraysSorted;
        } else {
            arrayListsSorted++;
            totalArrayListTime += timeMs;
            avgArrayListTime = (double) totalArrayListTime / arrayListsSorted;
        }
    }
    
    public void setCompletedAll(boolean completed) {
        this.completedAll = completed;
    }
    
    public String getAlgorithmName() {
        return algorithmName;
    }
    
    public int getCollectionsSorted() {
        return collectionsSorted;
    }
    
    public int getTotalCollections() {
        return totalCollections;
    }
    
    public double getAvgTime() {
        return avgTime;
    }
    
    public boolean isCompletedAll() {
        return completedAll;
    }
    
    public int getArraysSorted() {
        return arraysSorted;
    }
    
    public int getArrayListsSorted() {
        return arrayListsSorted;
    }
    
    public double getAvgArrayTime() {
        return avgArrayTime;
    }
    
    public double getAvgArrayListTime() {
        return avgArrayListTime;
    }
}

