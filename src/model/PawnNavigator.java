package model;

import java.util.Arrays;

public class PawnNavigator extends BoardLocator {
    public int[][] getMoveIndexes(final Pawn[][] pawnBoard, final int xCoordinate, final int yCoordinate, final Pawn enemyPawn, final int direction){

        int x1 = xCoordinate, x2 = xCoordinate, y1 = yCoordinate, y2 = yCoordinate, x3 = xCoordinate, y3 = yCoordinate;
        int xk1 = -1, xk2 = -1, yk1 = -1, yk2 = -1;
        int xd = xCoordinate + direction, x2d = xCoordinate+2*direction, x3d = xCoordinate+3*direction, x4d = xCoordinate+4*direction;

        Pawn queen;
        if(enemyPawn == Pawn.BLACK)
            queen = Pawn.BLACKQUEEN;
        else
            queen = Pawn.SILVERQUEEN;
        Pawn emptyColor = Pawn.EMPTY;

        try {
            /*
             * Po obu stronach pusto
             * */

            if (isOneStepLeft(xCoordinate, yCoordinate, direction) && isOneStepRight(xCoordinate, yCoordinate, direction) && pawnBoard[xd][yCoordinate - 1] != enemyPawn && pawnBoard[xd][yCoordinate + 1] != enemyPawn && pawnBoard[xd][yCoordinate - 1] != queen && pawnBoard[xd][yCoordinate + 1] != queen) {

                x1 += direction;
                y1 -= 1;
                x2 += direction;
                y2 += 1;
            }
            /*
             * Po prawej stronie dwa pionki a po lewej pusto
             * */
            else if (isVerticallyOnBoard(xCoordinate, 2 * direction) && ((yCoordinate == 6 && (pawnBoard[xd][yCoordinate + 1] == enemyPawn || pawnBoard[xd][yCoordinate + 1] == queen)) || (yCoordinate + 2 <= 7 && yCoordinate - 1 >= 0 && (pawnBoard[xd][yCoordinate + 1] == enemyPawn || pawnBoard[xd][yCoordinate + 1] == queen) && pawnBoard[x2d][yCoordinate + 2] != emptyColor)) && pawnBoard[xd][yCoordinate - 1] == emptyColor) {

                x1 += direction;
                y1 -= 1;
            }
            /*
             * Po lewej stronie dwa pionki a po prawej pusto
             * */
            else if (isVerticallyOnBoard(xCoordinate, direction) && ((yCoordinate == 1 && (pawnBoard[xd][yCoordinate - 1] == enemyPawn || pawnBoard[xd][yCoordinate - 1] == queen)) || (yCoordinate - 2 >= 0 && yCoordinate + 1 <= 7 && (pawnBoard[xd][yCoordinate - 1] == enemyPawn || pawnBoard[xd][yCoordinate - 1] == queen) && pawnBoard[x2d][yCoordinate - 2] != emptyColor)) && pawnBoard[xd][yCoordinate + 1] == emptyColor) {

                x1 += direction;
                y1 += 1;
            }
            /*
             * pionek na prawej krawędzi
             * */
            else if (isVerticallyOnBoard(xCoordinate, direction) && yCoordinate == 0 && pawnBoard[xd][yCoordinate + 1] == emptyColor) {

                x1 += direction;
                y1 += 1;
            }
            /*
             * pionek na lewej krawędzi
             * */
            else if (isVerticallyOnBoard(xCoordinate, direction) && yCoordinate == 7 && pawnBoard[xd][yCoordinate - 1] == emptyColor) {

                x1 += direction;
                y1 -= 1;
            }
            /*
             * po lewo przeciwnik
             * */
            else if (isTwoStepLeft(xCoordinate, yCoordinate, direction) && (pawnBoard[xd][yCoordinate - 1] == enemyPawn || pawnBoard[xd][yCoordinate - 1] == queen) && pawnBoard[x2d][yCoordinate - 2] == emptyColor) {

                /*
                 * Podwojne bicie zlamane lewo-prawo
                 * */
                if (isVerticallyOnBoard(xCoordinate, 4 * direction) && (pawnBoard[x3d][yCoordinate - 1] == enemyPawn || pawnBoard[x3d][yCoordinate - 1] == queen) && pawnBoard[x4d][yCoordinate] == emptyColor) {

                    x3 += 4 * direction;
                    xk1 = xCoordinate + direction;
                    xk2 = xCoordinate + 3 * direction;
                    yk1 = yCoordinate - 1;
                    yk2 = yCoordinate - 1;
                }
                /*
                 * Podwojne bicie proste w lewo
                 * */
                else if (isFourStepLeft(xCoordinate, yCoordinate, direction) && (pawnBoard[x3d][yCoordinate - 3] == enemyPawn || pawnBoard[x3d][yCoordinate - 3] == queen) && pawnBoard[x4d][yCoordinate - 4] == emptyColor) {

                    x3 += 4 * direction;
                    y3 -= 4;
                    xk1 = xCoordinate + direction;
                    xk2 = xCoordinate + 3 * direction;
                    yk1 = yCoordinate - 1;
                    yk2 = yCoordinate - 3;
                }
                /*
                 * Bicie pojedyncze po lewej
                 * */
                else {
                    x1 += 2 * direction;
                    y1 -= 2;
                    xk1 = xCoordinate + direction;
                    yk1 = yCoordinate - 1;
                }
            }
            /*
             * Po prawej przeciwnik
             * */
            else if (isTwoStepRight(xCoordinate, yCoordinate, direction) && (pawnBoard[xd][yCoordinate + 1] == enemyPawn || pawnBoard[xd][yCoordinate + 1] == queen) && pawnBoard[x2d][yCoordinate + 2] == emptyColor) {

                /*
                 * Podwojne bicie zlamane prawo-lewo
                 * */
                if (isVerticallyOnBoard(xCoordinate, 4 * direction) && (pawnBoard[x3d][yCoordinate + 1] == enemyPawn || pawnBoard[x3d][yCoordinate + 1] == queen) && pawnBoard[x4d][yCoordinate] == emptyColor) {

                    x3 += 4 * direction;
                    xk1 = xCoordinate + direction;
                    xk2 = xCoordinate + 3 * direction;
                    yk1 = yCoordinate + 1;
                    yk2 = yCoordinate + 1;
                }
                /*
                 * Podwojne bicie proste w prawo
                 * */
                else if (isFourStepRight(xCoordinate, yCoordinate, direction) && (pawnBoard[x3d][yCoordinate + 3] == enemyPawn || pawnBoard[x3d][yCoordinate + 3] == queen) && pawnBoard[x4d][yCoordinate + 4] == emptyColor) {

                    x3 += 4 * direction;
                    y3 += 4;
                    xk1 = xCoordinate + direction;
                    xk2 = xCoordinate + 3 * direction;
                    yk1 = yCoordinate + 1;
                    yk2 = yCoordinate + 3;
                }
                /*
                 * Bicie pojedyncze w prawo
                 * */
                else {
                    x2 += 2 * direction;
                    y2 += 2;
                    xk1 = xCoordinate + direction;
                    yk1 = yCoordinate + 1;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Array Index exception: "+e.getMessage());
        }
        return new int[][]{{x1, y1, x2, y2, x3, y3}, {xk1, yk1, xk2, yk2, -1, -1}};
    }

    public int[][] getQueenMoveIndexes(final Pawn[][] pawnBoard, final int xCoordinate, final int yCoordinate, final Pawn enemyPawn){

        int[][] moveInedexes1 = getMoveIndexes(pawnBoard, xCoordinate, yCoordinate, enemyPawn, -1);
        int[][] moveInedexes2 = getMoveIndexes(pawnBoard, xCoordinate, yCoordinate, enemyPawn, 1);
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
}
