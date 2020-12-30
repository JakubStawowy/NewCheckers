package Model;

public class BoardInitiator {
    final int boardSize = 8;
    public Boolean[][] getInitialBoard(){
        Boolean[][] initialBoard = new Boolean[boardSize][boardSize];
        for(int i = 0 ; i < boardSize ; i++)
            for(int j = 0 ; j < boardSize ; j++)
                initialBoard[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
        return initialBoard;
    }

    public Pawn[][] getInitialPawnBoard(){
        Pawn[][] pawnBoard = new Pawn[boardSize][boardSize];
        for(int i = 0 ; i < boardSize ; i++)
            for(int j = 0 ; j < boardSize ; j++)
                if(((i==1||i==5||i==7) && j%2==1)||((i==0||i==2||i==6) && j%2==0)){
                    if(i<4)
                        pawnBoard[i][j] = Pawn.BLACK;
                    else
                        pawnBoard[i][j] = Pawn.SILVER;
                }else{
                    pawnBoard[i][j] = Pawn.EMPTY;
                }
        return pawnBoard;
    }
}
