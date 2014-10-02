public class Sorting{
  
  public static void heapSort(int[] array){
    //builds heap in place initially
    buildHeap(array); 
    int numItems = array.length;
    int endUnsorted = numItems - 1; 
    //loops, moving the largest element to the end of the unsorted section and shortening the heap
    while (endUnsorted > 0) { 
      int largestRemaining = removeMax(array, endUnsorted); 
      array[endUnsorted] = largestRemaining; 
      endUnsorted--; 
    }
    
  }
  
  public static void quickSort(int[] array){
    //begins a recursive call
    thisQuickSort(array, 0, array.length-1);
  }
  
  public static void mergeSort(int[] array){
    int[] temp = new int[array.length];
    int sec =1;
    int i =0;
    //iterates through the array multiple times, each time looking at larger sections
    for(; !(sec > array.length); sec = sec*2){
      int start1 = 0; 
      int start2 = sec; 
      //iterates through array, merging each section appropriately
      for(; start1 < array.length;){
        if(i%2 == 0){ //if even, copies from array into temporary array
          merge(start1, start2, start1, array, temp, sec);
          //printArray(temp);
        }
        else if(i%2 == 1){ //if odd, copies from temp back to array
          merge(start1, start2, start1, temp, array, sec);
          //printArray(array);
        }
        start1 = start2 + sec;
        start2 = start2 +(sec*2);
      }
      i++;
      
    }
    //copies the final sorted array into the original 
    if(i%2 == 1){
      for(int r=0; r < array.length; r++)
        array[r] = temp[r];
    }
  }
  
  public static void printArray(int[] array){
    for(int i = 0; i < array.length; i++)
      System.out.print(array[i] +", ");
    System.out.println();
  }
  
  //helper methods for mergeSort
  //takes a section of the array and merges it, writing it into the other array
  private static void merge(int start1, int start2, int newSpot, int[] array, int[] temp, int sec){
    int i = start1;
    int j = start2;
    //if there's no second section, copies first section
    if(start2 >= array.length){
      for(; i < array.length; i++){
        temp[newSpot] = array[i];
        newSpot++;
      }
    }
    else{
      for(;newSpot < start2+sec && newSpot < temp.length ; newSpot++){
        int placer = -1;
        if(i >= j || i >= start1 + sec){ //if first section is all copied, copies rest of second section
          placer = array[j];
          j++;
        }
        else if(j >= start2 +sec || j >= array.length){ //if second section is all copied, copies rest of first section
          placer = array[i];
          i++;
        }
        else if(array[j] < array[i]){
          placer = array[j];
          j++;
        }
        else if(array[i] <= array[j]){
          placer = array[i];
          i++;
        }
        temp[newSpot] = placer; 
        //System.out.println("(" + i + ", " + j + ")_" + newSpot);
      }
    }
  }
  
  //helper methods for heapSort
  //builds the heap initially
  private static void buildHeap(int[] array){
    for(int i = (array.length-2)/2; i >= 0; i--){
      siftDown(array, i, array.length); 
    }
  }
  
  //standard sift down method for a heap
  private static void siftDown(int[] heap, int i, int end){
    int item = heap[i]; //item to sift
    int parent = i;
    int child = 2*parent +1;
    while(child < end){  //parent less than child, moves down if same
      if(child + 1 < end && heap[child] < heap[child+1])
        child = child + 1;
      if(item >= heap[child])
        break;
      heap[parent] = heap[child];
      heap[child] = item;
      parent = child;
      child = 2*parent + 1;

    }
    heap[parent] = item; 
  }
  
  //returns the max value of the heap and resifts the new heap
  private static int removeMax(int[] array, int end){
    int max = array[0];
    array[0] = array[end];
    siftDown(array, 0, end);
    return max;
  }
  
  //helper methods for quickSort
  //method that recursively functions to sort the array
  private static void thisQuickSort(int[] array, int first, int last){
    if(last==first){
      //do nothing, recursive call is finished
    }
    else{
      //splits the array and sorts each side
      int split = partition(array, first, last);
      thisQuickSort(array, split + 1, last);
      thisQuickSort(array, first, split);
    }
  }
  
  //splits the array or sub-array in half and moves all items to abide by the partition
  private static int partition(int[] array, int first, int last){
    int pivot = (array[first] + array[last])/2;
    int i = first -1;
    int j = last + 1;
    while (true) { 
      do { 
        i++; 
      } while (array[i] < pivot); 
      do { 
        j--; 
      } while (array[j] > pivot); 
      if (i < j) 
        swap(array, i, j); 
      else 
        return j; 
    } 
    
  }
  
  //swaps two items in an array
  private static void swap(int[] array, int i, int j){
    int swappee = array[i];
    array[i] = array[j];
    array[j] = swappee;
  }
  
  
  
}