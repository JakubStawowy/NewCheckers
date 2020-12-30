package Model;

public class PawnController {
    private final int boardSize = 8;
    private PawnNavigator pawnNavigator;
    public PawnController(){
        pawnNavigator = new PawnNavigator();
    }
    public Boolean[][] getPriorityBoard(final Pawn[][] pawnBoard, final Pawn color, final Pawn enemyColor, final int direction){
        Boolean[][] priorityBoard = new Boolean[boardSize][boardSize];
        boolean attack = false;
        for(int i = 0 ; i < pawnBoard.length ; i++)
            for(int j = 0 ; j < pawnBoard[i].length ; j++)
                priorityBoard[i][j] = pawnBoard[i][j] != enemyColor && pawnBoard[i][j] != Pawn.EMPTY;

        for(int i = 0 ; i < pawnBoard.length ; i++)
            for (int j = 0; j < pawnBoard[i].length; j++){
                if(priorityBoard[i][j]){
                    if(pawnBoard[i][j] == color){
                        int[][] moveIndexes = pawnNavigator.getMoveIndexes(pawnBoard, i, j,enemyColor, direction);
                        for (int k = 0; k < moveIndexes[1].length; k++) {
                            if(moveIndexes[1][k] != -1){
                                priorityBoard[i][j] = true;
                                attack = true;
                                break;
                            }
                            else
                                priorityBoard[i][j] = false;
                        }
                    }
                    else if(pawnBoard[i][j] == Pawn.BLACKQUEEN || pawnBoard[i][j] == Pawn.SILVERQUEEN){
                        int[][] moveIndexes = pawnNavigator.getQueenMoveIndexes(pawnBoard, i, j,enemyColor);
                        for (int k = 0; k < moveIndexes[1].length; k++) {
                            if(moveIndexes[1][k] != -1){
                                priorityBoard[i][j] = true;
                                attack = true;
                                break;
                            }
                            else
                                priorityBoard[i][j] = false;
                        }
                    }
                }
            }
        if(!attack)
            for(int i = 0 ; i < pawnBoard.length ; i++)
                for(int j = 0 ; j < pawnBoard[i].length ; j++)
                    priorityBoard[i][j] = pawnBoard[i][j] != enemyColor && pawnBoard[i][j] != Pawn.EMPTY;
        return priorityBoard;
    }

    public Boolean[][] getTurnBoard(final Pawn[][] pawnBoard, final Pawn pawn){
        Boolean[][] turnBoard = new Boolean[boardSize][boardSize];
        Pawn queen;
        if(pawn == Pawn.BLACK)
            queen = Pawn.BLACKQUEEN;
        else
            queen = Pawn.SILVERQUEEN;
        for(int i = 0 ; i < boardSize ; i++)
            for(int j = 0 ; j < boardSize ; j++)
                turnBoard[i][j] = pawnBoard[i][j] == pawn || pawnBoard[i][j] == queen;

        return turnBoard;
    }
}
