package Model;

import java.util.Arrays;

public final class Model extends BoardLocator {
    private final int boardSize = 8;
    private enum pawn{
        SILVER, BLACK, SILVERQUEEN, BLACKQUEEN, EMPTY
    }
    private Boolean[][] initialBoard = new Boolean[boardSize][boardSize];
    private Boolean[][] turnBoard = new Boolean[boardSize][boardSize];
    private Boolean[][] priorityBoard = new Boolean[boardSize][boardSize];
    private pawn[][] pawnBoard = new pawn[boardSize][boardSize];

    public Model(){
        setInitialBoard();
        setPawns();
    }
    private void setInitialBoard(){
        for(int i = 0 ; i < boardSize ; i++)
            for(int j = 0 ; j < boardSize ; j++)
                initialBoard[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
    }
    private void setPawns(){
        for(int i = 0 ; i < boardSize ; i++)
            for(int j = 0 ; j < boardSize ; j++)
                if(((i==1||i==5||i==7) && j%2==1)||((i==0||i==2||i==6) && j%2==0)){
                    if(i<4)
                        pawnBoard[i][j] = pawn.BLACK;
                    else
                        pawnBoard[i][j] = pawn.SILVER;
                }else{
                    pawnBoard[i][j] = pawn.EMPTY;
                }
    }
    public void changeTurn(pawn t){
        pawn queen;
        if(t == pawn.BLACK)
            queen = pawn.BLACKQUEEN;
        else
            queen = pawn.SILVERQUEEN;
        for(int i = 0 ; i < boardSize ; i++)
            for(int j = 0 ; j < boardSize ; j++)
                turnBoard[i][j] = pawnBoard[i][j] == t || pawnBoard[i][j] == queen;
    }
    public Boolean[][] getTurnBoard(){
        return turnBoard;
    }
    public int[][] getMoveIndexes(final int xCoordinate, final int yCoordinate, final pawn enemyColor, final int direction){
        int x1 = xCoordinate, x2 = xCoordinate, y1 = yCoordinate, y2 = yCoordinate, x3 = xCoordinate, y3 = yCoordinate;
        int xk1 = -1, xk2 = -1, yk1 = -1, yk2 = -1;
        int xd = xCoordinate + direction, x2d = xCoordinate+2*direction, x3d = xCoordinate+3*direction, x4d = xCoordinate+4*direction;

        pawn queen;
        if(enemyColor == pawn.BLACK)
            queen = pawn.BLACKQUEEN;
        else
            queen = pawn.SILVERQUEEN;
        pawn emptyColor = pawn.EMPTY;
        /*
         * Po obu stronach pusto
         * */

        if(isOneStepLeft(xCoordinate, yCoordinate, direction) && isOneStepRight(xCoordinate, yCoordinate, direction) && pawnBoard[xd][yCoordinate-1] != enemyColor && pawnBoard[xd][yCoordinate+1] != enemyColor && pawnBoard[xd][yCoordinate-1] != queen && pawnBoard[xd][yCoordinate+1] != queen){

            x1+=direction;
            y1-=1;
            x2+=direction;
            y2+=1;
        }
        /*
        * Po prawej stronie dwa pionki a po lewej pusto
        * */
        else if(isVerticallyOnBoard(xCoordinate, 2*direction) && ((yCoordinate == 6 && (pawnBoard[xd][yCoordinate+1] == enemyColor || pawnBoard[xd][yCoordinate+1] == queen)) || (yCoordinate+2<=7 && yCoordinate-1>=0 && (pawnBoard[xd][yCoordinate+1] == enemyColor || pawnBoard[xd][yCoordinate+1] == queen) && pawnBoard[x2d][yCoordinate+2] != emptyColor)) && pawnBoard[xd][yCoordinate-1] == emptyColor){

            x1+=direction;
            y1-=1;
        }
        /*
        * Po lewej stronie dwa pionki a po prawej pusto
        * */
        else if(isVerticallyOnBoard(xCoordinate, direction) && ((yCoordinate == 1 && (pawnBoard[xd][yCoordinate-1] == enemyColor || pawnBoard[xd][yCoordinate-1] == queen)) || (yCoordinate-2>=0 && yCoordinate+1<=7 && (pawnBoard[xd][yCoordinate-1] == enemyColor || pawnBoard[xd][yCoordinate-1] == queen) && pawnBoard[x2d][yCoordinate-2] != emptyColor)) && pawnBoard[xd][yCoordinate+1] == emptyColor){

            x1+=direction;
            y1+=1;
        }
        /*
         * pionek na prawej krawędzi
         * */
        else if(isVerticallyOnBoard(xCoordinate, direction) && yCoordinate == 0 && pawnBoard[xd][yCoordinate+1] == emptyColor){

            x1+=direction;
            y1+=1;
        }
        /*
         * pionek na lewej krawędzi
         * */
        else if(isVerticallyOnBoard(xCoordinate, direction) && yCoordinate == 7 && pawnBoard[xd][yCoordinate-1] == emptyColor){

            x1+=direction;
            y1-=1;
        }
        /*
         * po lewo przeciwnik
         * */
        else if(isTwoStepLeft(xCoordinate, yCoordinate, direction) && (pawnBoard[xd][yCoordinate-1] == enemyColor || pawnBoard[xd][yCoordinate-1] == queen) && pawnBoard[x2d][yCoordinate - 2] == emptyColor){

            /*
             * Podwojne bicie zlamane lewo-prawo
             * */
            if(isVerticallyOnBoard(xCoordinate, 4*direction) && (pawnBoard[x3d][yCoordinate - 1] == enemyColor || pawnBoard[x3d][yCoordinate - 1] == queen) && pawnBoard[x4d][yCoordinate] == emptyColor){

                x3+=4*direction;
                xk1 = xCoordinate+direction;
                xk2 = xCoordinate+3*direction;
                yk1 = yCoordinate-1;
                yk2 = yCoordinate-1;
            }
            /*
             * Podwojne bicie proste w lewo
             * */
            else if(isFourStepLeft(xCoordinate, yCoordinate, direction) && (pawnBoard[x3d][yCoordinate - 3] == enemyColor || pawnBoard[x3d][yCoordinate - 3] == queen) && pawnBoard[x4d][yCoordinate-4] == emptyColor){

                x3+=4*direction;
                y3-=4;
                xk1 = xCoordinate+direction;
                xk2 = xCoordinate+3*direction;
                yk1 = yCoordinate-1;
                yk2 = yCoordinate-3;
            }
            /*
             * Bicie pojedyncze po lewej
             * */
            else{
                x1+=2*direction;
                y1-=2;
                xk1 = xCoordinate+direction;
                yk1 = yCoordinate-1;
            }
        }
        /*
         * Po prawej przeciwnik
         * */
        else if(isTwoStepRight(xCoordinate, yCoordinate, direction) && (pawnBoard[xd][yCoordinate+1] == enemyColor || pawnBoard[xd][yCoordinate+1] == queen) && pawnBoard[x2d][yCoordinate + 2] == emptyColor) {

            /*
             * Podwojne bicie zlamane prawo-lewo
             * */
            if (isVerticallyOnBoard(xCoordinate, 4*direction) && (pawnBoard[x3d][yCoordinate + 1] == enemyColor || pawnBoard[x3d][yCoordinate + 1] == queen) && pawnBoard[x4d][yCoordinate] == emptyColor) {

                x3 += 4 * direction;
                xk1 = xCoordinate+direction;
                xk2 = xCoordinate+3*direction;
                yk1 = yCoordinate+1;
                yk2 = yCoordinate+1;
            }
            /*
             * Podwojne bicie proste w prawo
             * */
            else if (isFourStepRight(xCoordinate, yCoordinate, direction) && (pawnBoard[x3d][yCoordinate + 3] == enemyColor || pawnBoard[x3d][yCoordinate + 3] == queen) && pawnBoard[x4d][yCoordinate + 4] == emptyColor) {

                x3 += 4 * direction;
                y3 += 4;
                xk1 = xCoordinate+direction;
                xk2 = xCoordinate+3*direction;
                yk1 = yCoordinate+1;
                yk2 = yCoordinate+3;
            }
            /*
             * Bicie pojedyncze w prawo
             * */
            else {
                x2 += 2 * direction;
                y2 += 2;
                xk1 = xCoordinate+direction;
                yk1 = yCoordinate+1;
            }
        }
        return new int[][]{{x1, y1, x2, y2, x3, y3}, {xk1, yk1, xk2, yk2, -1, -1}};
    }
    public int[][] getQueenMoveIndexes(final int xCoordinate, final int yCoordinate, final pawn color){

        int[][] moveInedexes1 = getMoveIndexes(xCoordinate, yCoordinate, color, -1);
        int[][] moveInedexes2 = getMoveIndexes(xCoordinate, yCoordinate, color, 1);
        final int lenght1 = moveInedexes1[0].length;
        final int lenght2 = moveInedexes2[0].length;
        final int lenght3 = moveInedexes1[1].length;
        final int lenght4 = moveInedexes2[1].length;
        if(moveInedexes1[0][0] < xCoordinate-1 || moveInedexes1[0][2] < xCoordinate-1 || moveInedexes1[0][4] < xCoordinate-1){
            for (int i = 0; i < lenght2; i++) {
                if(i%2 == 0)
                    moveInedexes2[0][i] = xCoordinate;
                else
                    moveInedexes2[0][i] = yCoordinate;
            }
            Arrays.fill(moveInedexes2[1], -1);
        }
        if(moveInedexes2[0][0] > xCoordinate+1 || moveInedexes2[0][2] > xCoordinate+1 || moveInedexes2[0][4] > xCoordinate+1){
            for (int i = 0; i < lenght1; i++) {
                if(i%2 == 0)
                    moveInedexes1[0][i] = xCoordinate;
                else
                    moveInedexes1[0][i] = yCoordinate;
            }
            Arrays.fill(moveInedexes1[1], -1);
        }
        int[][] moveIndexes = new int[2][lenght1 + lenght2];
        System.arraycopy(moveInedexes1[0], 0, moveIndexes[0], 0, lenght1);
        System.arraycopy(moveInedexes2[0], 0, moveIndexes[0], lenght1, lenght2);
        System.arraycopy(moveInedexes1[1], 0, moveIndexes[1], 0, lenght3);
        System.arraycopy(moveInedexes2[1], 0, moveIndexes[1], lenght3, lenght4);
        return moveIndexes;
    }
    public Boolean[][] getInitialBoard(){
        return initialBoard;
    }
    public Boolean[][] getPriorityBoard2(final pawn color, final pawn enemyColor, final int direction){
        boolean attack = false;
        for(int i = 0 ; i < pawnBoard.length ; i++)
            for(int j = 0 ; j < pawnBoard[i].length ; j++)
                priorityBoard[i][j] = pawnBoard[i][j] != enemyColor && pawnBoard[i][j] != pawn.EMPTY;

        for(int i = 0 ; i < pawnBoard.length ; i++)
            for (int j = 0; j < pawnBoard[i].length; j++){
                if(priorityBoard[i][j]){
                    if(pawnBoard[i][j] == color){
                        int[][] moveIndexes = getMoveIndexes(i, j,enemyColor, direction);
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
                    else if(pawnBoard[i][j] == pawn.BLACKQUEEN || pawnBoard[i][j] == pawn.SILVERQUEEN){
                        int[][] moveIndexes = getQueenMoveIndexes(i, j,enemyColor);
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
                    priorityBoard[i][j] = pawnBoard[i][j] != enemyColor && pawnBoard[i][j] != pawn.EMPTY;
        return priorityBoard;
    }
    public pawn[][] getPawnBoard(){
        return pawnBoard;
    }
    public pawn getBlackTile(){
        return pawn.BLACK;
    }
    public pawn getSilverTile(){
        return pawn.SILVER;
    }
    public pawn getBlackQueenTile(){
        return pawn.BLACKQUEEN;
    }
    public pawn getSilverQueenTile(){
        return pawn.SILVERQUEEN;
    }
    public pawn getEmptyTile(){
        return pawn.EMPTY;
    }
    public int getBoardSize(){
        return boardSize;
    }
}

abstract class BoardLocator{
    private final int boardSize = 7;
    protected boolean isVerticallyOnBoard(int xCoordinate, int step){
        return xCoordinate+step <= boardSize && xCoordinate+step >= 0;
    }
    protected boolean isOneStepLeft(int xCoordinate, int yCoordinate, int direction){
        return isVerticallyOnBoard(xCoordinate, direction) && yCoordinate-1>=0;
    }
    protected boolean isOneStepRight(int xCoordinate, int yCoordinate, int direction){
        return isVerticallyOnBoard(xCoordinate, direction) && yCoordinate+1<=boardSize;
    }
    protected boolean isTwoStepLeft(int xCoordinate, int yCoordinate, int direction){
        return isVerticallyOnBoard(xCoordinate, 2*direction) && yCoordinate-2>=0;
    }
    protected boolean isTwoStepRight(int xCoordinate, int yCoordinate, int direction){
        return isVerticallyOnBoard(xCoordinate, 2*direction) && yCoordinate+2<=7;
    }
    protected boolean isFourStepLeft(int xCoordinate, int yCoordinate, int direction){
        return isVerticallyOnBoard(xCoordinate, 4*direction) && yCoordinate-4>=0;
    }
    protected boolean isFourStepRight(int xCoordinate, int yCoordinate, int direction){
        return isVerticallyOnBoard(xCoordinate, 4*direction) && yCoordinate+4<=7;
    }
}
