public class uppg8ny{
  public static void main(String args[]){
    int[] array = {32, 27, 64, 18, 95, 14, 90, 70, 60, 37}; 
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i] + "\t");
    }
    System.out.println();
    for(int i = 0; i < array.length; i++){
      int min = i;
      for(int j = i + 1; j < array.length; j++){
        if(array[min] > array[j]){
          min = j;
        }
      }
      if(i != min){
        int temp = array[i];
        array[i] = array[min];
        array[min] = temp;
      }
    }
    for(int i = 0; i < array.length; i++){
      System.out.print(array[i] + "\t");
    }
  }
}
