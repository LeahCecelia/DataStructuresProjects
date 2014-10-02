import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class reporting2{
  
  //reads the file to be sorted
  private static int[] readFile(String inputName) throws FileNotFoundException{
    FileReader freader = new FileReader("/Users/johndoe/Desktop/testFilesP4/"+ inputName);
    try{
      BufferedReader reader = new BufferedReader(freader); 
      //int i = 0;
      String string = " ";
      ArrayList<Integer> input = new ArrayList<Integer>();
      while(string != null){
        string = reader.readLine();
        if(string !=null)
          input.add(Integer.parseInt(string));
      }
      int[] inputInt = new int[input.size()];
      for(int i = 0; i < input.size(); i++)
        inputInt[i] = input.get(i).intValue();
      return inputInt;
    }
    catch (IOException x) {
      System.err.format("IOException: %s%n", x);
    }
    return null;
  }
  
  //makes a copy of the original array
  private static int[] copy(int[] array){
    int[] newArray = new int[array.length];
    for(int i = 0; i < array.length; i++)
      newArray[i] = array[i];
    return newArray;
  }
  
  //executes heap sort and produces the time value
  private static long heapSort(int[] original) throws FileNotFoundException{
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = copy(original);
      long estTime = timeHeap(array);
      data[i] = estTime;
      if(i==0)
        writeFile("lcn12HS.txt", array);
    }
    long time = 0;
    for(int j = 0; j < data.length; j++)
      time = time + data[j];
    return time/(long)3.0;
  }
  
  //heap timing helper method
  private static long timeHeap(int[] array){
    System.gc();
    long startingTime = System.nanoTime();
    Sorting.heapSort(array);
    long estTime = System.nanoTime() - startingTime;
    return estTime;
  }
  
  
  //executes quick sort and produces the time value
  private static long quickSort(int[] original) throws FileNotFoundException{
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = copy(original);
      long estTime = timeQuick(array);
      data[i] = estTime;
      if(i==0)
        writeFile("lcn12QS.txt", array);
    }
    long time = 0;
    for(int j = 0; j < data.length; j++)
      time = time + data[j];
    return time/(long)3.0;
  }
  
  //quick timing helper method
  private static long timeQuick(int[] array){
    System.gc();
    long startingTime = System.nanoTime();
    Sorting.quickSort(array);
    long estTime = System.nanoTime() - startingTime;
    return estTime;
  }
  
  //executes merge sort and produces the time value
  private static long mergeSort(int[] original) throws FileNotFoundException{
    long[] data = new long[3];
    for(int i = 0; i < 3; i++){
      int[] array = copy(original);
      long estTime = timeMerge(array);
      data[i] = estTime;
      if(i==0)
        writeFile("lcn12MS.txt", array);
    }
    long time = 0;
    for(int j = 0; j < data.length; j++)
      time = time + data[j];
    return time/(long)3.0;
  }
  
  //merge timing helper method
  private static long timeMerge(int[] array){
    System.gc();
    long startingTime = System.nanoTime();
    Sorting.mergeSort(array);
    long estTime = System.nanoTime() - startingTime;
    return estTime;
  }
  
  //writes the sorted arrays to new files
  private static void writeFile(String fileName, int[] array) throws FileNotFoundException{
    try{
      File newFile = new File("/Users/johndoe/Desktop/testFilesP4/" + fileName);
      FileWriter output = new FileWriter(newFile);
      BufferedWriter writer = new BufferedWriter(output);
      for(int i = 0; i < array.length; i++){
        writer.write(array[i] + "\n");
      }
      writer.close();
    }
    catch(IOException e){
    }
    
  }
  
  public static void main(String[] args) throws FileNotFoundException{
    int[] array = readFile(args[0]);
    System.out.println("HSlcn12 = " + heapSort(array) + "; QSlcn12 = " + quickSort(array) + "; MSlcn12 = " + mergeSort(array));
  }
}