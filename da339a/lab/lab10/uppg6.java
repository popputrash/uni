public class uppg6{
  public static void main(String args[]){
    int[] array = {0,12,13,14,13,0,0,15,15,0,13,0,0,15,34};
    printA(array);
    array = compress(array);
    printA(array);
  }
  public static int[] compress(int[] a1){
    for(int i = 0; i < a1.length; i++){
      if(a1[i] == 0){
        for(int j = i+1; j < a1.length; j++){
          if(a1[j] != 0){
            int temp = a1[j];
            a1[j] = a1[i];
            a1[i] = temp;
            break;
          }
        }
      }
    }
    return a1;
  }
  
  public static void printA(int[] a){
    for(int i = 0; i < a.length; i++){
      System.out.print(a[i] +" ");
    }
    System.out.println();
  }
}
