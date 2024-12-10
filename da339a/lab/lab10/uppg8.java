public class uppg8{
  public static void main(String args[]){
    int[] array = {32, 27, 64, 18, 95, 14, 90, 70, 60, 37}; 
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i] + "\t");
    }
    System.out.println();
    array = selectionSort(array);
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i] + "\t");
    }

  }

  public static int[] selectionSort(int[] a1){
    for(int i = 0; i < a1.length; i++){
      int min = i;
      for(int j = 0; j < a1.length; j++){
        if(a1[min] > a1[j]){
          min = j;
        }
      }
      if(i != min){
        int temp = a1[i];
        a1[i] = a1[min];
        a1[min] = temp;
      }
    }
    return a1;
  }
}

