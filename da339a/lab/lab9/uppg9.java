public class uppg9{
    public static void main(String[] args){
        int[][] a1 = {
            {1,4},
            {3,2}
        };
        int[][] a2 = {
            {3,2},
            {3,5}
        };

        if(a1.length == a2.length && a1[0].length == a2[0].length){
            int[][] res = compare(a1, a2);
    
            for(int i = 0; i < a1.length; i++){
                for(int j = 0; j < a1[0].length; j++){
                    System.out.print(a1[i][j] + "\t");
                }
                System.out.println();
            }


            System.out.println();

            for(int i = 0; i < a2.length; i++){
                for(int j = 0; j < a2[0].length; j++){
                    System.out.print(a2[i][j] + "\t");
                }
                System.out.println();
            }
        
            System.out.println();

            for(int i = 0; i < res.length; i++){
                for(int j = 0; j < res[0].length; j++){
                    System.out.print(res[i][j] + "\t");
                }
                System.out.println();
            }
        }else{
            System.out.println("Error");
        }

    }

    public static int[][] compare(int[][] i1, int[][] i2){
        int[][] r = new int[i1.length][i1[0].length];
        for(int i = 0; i < i1.length; i++){
            for(int j = 0; j < i1[0].length; j++){
                if(i1[i][j] >= i2[i][j]){
                    r[i][j] = i1[i][j];
                }else{
                    r[i][j]=i2[i][j];
                }
            }
        }
        return r;
    }   
}
