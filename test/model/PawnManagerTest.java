package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnManagerTest {

    @Test
    void getPriorityBoard() {
        assertDoesNotThrow(() -> {
            final PawnManager pawnManager = new PawnManager();
            final BoardInitiator boardInitiator = new BoardInitiator();
            final Pawn[][] pawnBoard = boardInitiator.getInitialPawnBoard();
            final Boolean[][] priorityBoard = pawnManager.getPriorityBoard(pawnBoard, Pawn.SILVER, Pawn.BLACK, -1);
            Boolean[][] booleanBoard = boardInitiator.getInitialBoard();

            for (int i = 0; i < booleanBoard.length; i++)
                for (int j = 0; j < booleanBoard.length; j++) {
                    if(i<5 && booleanBoard[i][j])
                        booleanBoard[i][j] = false;
                    assertEquals(booleanBoard[i][j], priorityBoard[i][j]);
                }
        });
    }

    @Test
    void getTurnBoard() {

        assertDoesNotThrow(() -> {
            final PawnManager pawnManager = new PawnManager();
            final BoardInitiator boardInitiator = new BoardInitiator();
            final Pawn[][] pawnBoard = boardInitiator.getInitialPawnBoard();
            final Boolean[][] turnBoard = pawnManager.getTurnBoard(pawnBoard, Pawn.BLACK);
            Boolean[][] booleanBoard = boardInitiator.getInitialBoard();
            for (int i = 0; i < booleanBoard.length; i++)
                for (int j = 0; j < booleanBoard.length; j++) {
                    if(i>2 && booleanBoard[i][j])
                        booleanBoard[i][j] = false;

                    assertEquals(booleanBoard[i][j], turnBoard[i][j]);
                }
        });
    }
}