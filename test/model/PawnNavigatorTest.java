package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnNavigatorTest {

    @Test
    void getMoveIndexes() {

        final int[][] testIndexes1 = {{4, 4, 4, 6, 5, 5}, {-1, -1, -1, -1, -1, -1}};
        final int[][] testIndexes2 = {{3, 3, 5, 5, 5, 5}, {4, 4, -1, -1, -1, -1}};
        final int[][] testIndexes3 = {{5, 5, 5, 5, 1, 1}, {4, 4, 2, 2, -1, -1}};
        final int pawnIndex = 5;

        final PawnNavigator pawnNavigator = new PawnNavigator();
        final BoardInitiator boardInitiator = new BoardInitiator();

        Pawn[][] pawnBoard = boardInitiator.getInitialPawnBoard();
        int[][] moveIndexes = pawnNavigator.getMoveIndexes(pawnBoard, pawnIndex, pawnIndex, Pawn.BLACK, -1);
        for (int i = 0; i < moveIndexes.length ; i++) {
            for (int j = 0; j < moveIndexes[0].length; j++) {
                assertEquals(testIndexes1[i][j], moveIndexes[i][j]);
            }
        }

        pawnBoard[4][4] = Pawn.BLACK;
        moveIndexes = pawnNavigator.getMoveIndexes(pawnBoard, pawnIndex, pawnIndex, Pawn.BLACK, -1);

        for (int i = 0; i < moveIndexes.length ; i++) {
            for (int j = 0; j < moveIndexes[0].length; j++) {
                assertEquals(testIndexes2[i][j], moveIndexes[i][j]);
            }
        }

        pawnBoard[2][2] = Pawn.BLACK;
        pawnBoard[1][1] = Pawn.EMPTY;
        moveIndexes = pawnNavigator.getMoveIndexes(pawnBoard, pawnIndex, pawnIndex, Pawn.BLACK, -1);
        for (int i = 0; i < moveIndexes.length ; i++) {
            for (int j = 0; j < moveIndexes[0].length; j++) {
                assertEquals(testIndexes3[i][j], moveIndexes[i][j]);
            }
        }
    }

    @Test
    void getQueenMoveIndexes() {
        final int[][] testIndexes1 = {{4, 4, 4, 6, 5, 5, 6, 4, 6, 6, 5, 5}, {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
        final int boardSize = 8;
        final int pawnIndex = 5;
        final PawnNavigator pawnNavigator = new PawnNavigator();

        Pawn[][] pawnBoard = new Pawn[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                pawnBoard[i][j] = Pawn.EMPTY;
                if(i==pawnIndex && j==pawnIndex)
                    pawnBoard[i][j] = Pawn.BLACKQUEEN;
            }
        }
        int[][] queenMoveIndexes = pawnNavigator.getQueenMoveIndexes(pawnBoard, pawnIndex, pawnIndex, Pawn.SILVER);

        for (int i = 0; i < queenMoveIndexes.length; i++) {
            for (int j = 0; j < queenMoveIndexes[0].length; j++) {
                assertEquals(testIndexes1[i][j], queenMoveIndexes[i][j]);
            }
        }
    }
}