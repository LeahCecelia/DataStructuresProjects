import java.util.Random;

public class reporting1{
  
  //methods that create deliverables
  private static void analysis(String args){
    if("heap".compareTo(args) == 0){
      for(int i = 1000; i <= 1000000; i = i*10){
        printer(heapSorted(i), i, "sorted");
        printer(heapReverseSorted(i), i, "reverse sorted");
        printer(heapRandom(i), i, "random");
      }
    }
    if("quick".compareTo(args) == 0){
      for(int i = 1000; i <= 1000000; i = i*10){
        printer(quickSorted(i), i, "sorted");
        printer(quickReverseSorted(i), i, "reverse sorted");
        printer(quickRandom(i), i, "random");
        
      }
    }
    if("merge".compareTo(args) == 0){
      for(int i = 1000; i <= 1000000; i = i*10){
        printer(mergeSorted(i), i, "sorted");
        printer(mergeReverseSorted(i), i, "reverse sorted");
        printer(mergeRandom(i), i, "random");
      }
    }
    
  }
  
  //a method for printing the relevant results of each sorting based on an array of data, the sorted array size, and the type of array sorte
  private static void printer(long[] data, int arraySize, String type){
    System.out.println("For a " + type + " array of size " + arraySize + " the times are as follows");
    for(int i = 0; i < data.length; i++){
      System.out.print(data[i] + " ");
    }
    System.out.println(" ");
    if(type.compareTo("random") == 0){
      System.out.println("mean value of " + type +" array data: " + meanVal(data));
      System.out.println("variance value of " + type + " array data: " + varianceVal(data));
    }
  }
  
  //actual testing methods for time
  
  //testing for heapSort
  //random array gets heapSorted
  private static long[] heapRandom(int length){
    long[] data = new long[10];
    for(int i = 0; i < 10; i++){
      int[] ranArray = generateRandom(length, (i+1)*10);
      long estTime = timeHeap(ranArray);
      data[i] = estTime;
    }
    return data;
  }
  
  //times sorted heap
  private static long[] heapSorted(int length){
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = generateSorted(length);
      long estTime = timeHeap(array);
      data[i] = estTime;
    }
    return data;
  }
  
  //times reverse sorted heap
  private static long[] heapReverseSorted(int length){
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = generateSorted(length);
      long estTime = timeHeap(array);
      data[i] = estTime;
    }
    return data;
  }
  
  //heap timing helper method
  private static long timeHeap(int[] array){
    System.gc();
    long startingTime = System.nanoTime();
    Sorting.heapSort(array);
    long estTime = System.nanoTime() - startingTime;
    return estTime;
  }
  
  //testing for quickSort
  //random array gets quickSorted
  private static long[] quickRandom(int length){
    long[] data = new long[10];
    for(int i = 0; i < 10; i++){
      int[] ranArray = generateRandom(length, (i+1)*10);
      long estTime = timeQuick(ranArray);
      data[i] = estTime;
    }
    return data;
  }
  
  //times sorted quickSort
  private static long[] quickSorted(int length){
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = generateSorted(length);
      long estTime = timeQuick(array);
      data[i] = estTime;
    }
    return data;
  }
  
  //times reverse sorted quickSort
  private static long[] quickReverseSorted(int length){
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = generateReverse(length);
      long estTime = timeQuick(array);
      data[i] = estTime;
    }
    return data;
  }
  
  //quick sort timing helper method
  private static long timeQuick(int[] array){
    System.gc();
    long startingTime = System.nanoTime();
    Sorting.quickSort(array);
    long estTime = System.nanoTime() - startingTime;
    return estTime;
  }
  
   //testing for quickSort
  //random array gets quickSorted
  private static long[] mergeRandom(int length){
    long[] data = new long[10];
    for(int i = 0; i < 10; i++){
      int[] ranArray = generateRandom(length, (i+1)*10);
      long estTime = timeMerge(ranArray);
      data[i] = estTime;
    }
    return data;
  }
  
  //times sorted quickSort
  private static long[] mergeSorted(int length){
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = generateSorted(length);
      long estTime = timeMerge(array);
      data[i] = estTime;
    }
    return data;
  }
  
  //times reverse sorted mergeSort
  private static long[] mergeReverseSorted(int length){
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = generateReverse(length);
      long estTime = timeMerge(array);
      data[i] = estTime;
    }
    return data;
  }
  
  //helper method for timing mergeSort
  private static long timeMerge(int[] array){
    System.gc();
    long startingTime = System.nanoTime();
    Sorting.mergeSort(array);
    long estTime = System.nanoTime() - startingTime;
    return estTime;
  }
  
  
  //methods to generate array for testing
  //generates a sorted array
  private static int[] generateSorted(int length){
    int[] array = new int[length];
    for(int i = 0; i < length; i++){
      array[i] = 0;
    }
    return array;
  }
  
  //generates a reverse sorted array
  private static int[] generateReverse(int length){
    int[] array = new int[length];
    for(int i = 0; i < length; i++){
      array[i] = length - i;
    }
    return array;
  }
  
  //generates a random array using a seed
  private static int[] generateRandom(int length, long seed){
    int[] array = new int[length];
    Random rando = new Random(seed);
    for(int i = 0; i < length; i++){
      array[i] = rando.nextInt(length*2);
    }
    return array;
  }
  
  //stats methods
  //generates the mean based on an array of values
  private static double meanVal(long[] data){
    double sum =0;
    for(int i = 0; i < data.length; i++){
      sum = sum + (double)data[i];
    }
    return sum/(double)data.length;
  }
  
  //takes the variance of a set of values 
  private static double varianceVal(long[] data){
    double variance = 0;
    double mean = meanVal(data);
    for(int i = 0; i < data.length; i++){
      variance = variance + ((data[i] - mean)*(data[i] - mean));
    }
    return variance/(data.length-1);
  }
  
  //the main method
  public static void main(String args[]){
    System.out.println("Heap Sort:");
    analysis("heap");
    System.out.println("Quick Sort:");
    analysis("quick");
    System.out.println("Merge Sort:");
    analysis("merge");
  }
}