public class uppg7ny {
  public static void main(String args[]){
    int[][] a1 = {
      {1,4},
      {11,12},
      {6,3}   
    };

    int[][] a2 = {
      {20,4},
      {2,30},
    };

    if(a1.length != a2.length){
      System.out.println("fel storlekar");
      return;
    }

    for(int i = 0; i < a1.length; i++){
      if(a1[i].length != a2[i].length){
        System.out.println("fel storlekar");
        return;
      }
    }

    int[][] res = new int[a1.length][a1[0].length];
    for(int i = 0; i < a1.length; i++){
      for(int j = 0; j < a1[i].length; j++){
        if(a1[i][j] >= a2[i][j]){
          res[i][j] = a1[i][j];
          System.out.print("a1:" + res[i][j] + "\t");
        }else{
          res[i][j] = a2[i][j];
          System.out.print("a2:" + res[i][j] + "\t");
        } 
      }
      System.out.println();
    }
  }
}
