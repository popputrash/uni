public class spelplan{

    public static void main(String[] args){

        int[][] board = {
            {1,1,2,3,3},
            {2,1,1,2,3},
            {3,2,2,1,2},
            {3,3,2,2,1},
        };


        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }

        boolean[][] checkedBoard = checker(board);
        for(int i = 0; i < checkedBoard.length; i++){
            for(int j = 0; j < checkedBoard[0].length; j++){
                System.out.print(checkedBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean[][] checker(int[][] input){
        boolean[][] solved = new boolean[input.length][input[0].length];       
        int sum = 0;

        int[][] directions = {   
            {-1,1},//vänster upp
            {0,1},//upp
            {1,1},//höger upp
            {-1,0},//vänster
            {1,0},//höger
            {-1,-1},//vänster ner
            {0,-1},//ner
            {1,-1}//höger ner
        };
        //gå igenom varje cell och räkna ihop summan av dess grannar.
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                
                //går igenom listan för directions o kollar on index ligger i array
                //isåfall adderas summa ihop
                for(int[] dir : directions){
                    if(0 <= i+dir[0] && i+dir[0] < input.length && 0 <= j+dir[1] && j+dir[1] < input[0].length){
                        sum += input[i+dir[0]][j+dir[1]];
                    }
                }
                if(sum >= 15 || input[i][j] >= 3){
                    solved[i][j] = false;
                }else{
                    solved[i][j] = true;
                }
                sum = 0;
            }
        }
        return solved;
    }
}

