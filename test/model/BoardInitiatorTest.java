package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardInitiatorTest {
    private final int boardSize = 8;
    @Test
    void getInitialBoard() {
        Boolean[] initialBoard = new Boolean[]{
                true, false, true, false, true, false, true, false
        };
        BoardInitiator boardInitiator = new BoardInitiator();

        for (int i = 0; i < initialBoard.length; i++) {
            assertEquals(initialBoard[i], boardInitiator.getInitialBoard()[0][i]);
        }
    }

    @Test
    void getInitialPawnBoard() {
        Pawn[][] pawnBoard = new Pawn[][]{
                {Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY},
                {Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK},
                {Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY},
                {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
                {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
                {Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER},
                {Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY},
                {Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER, Pawn.EMPTY, Pawn.SILVER},
        };
        BoardInitiator boardInitiator = new BoardInitiator();
        for (int i = 0; i < pawnBoard.length; i++) {
            for (int j = 0; j < pawnBoard.length; j++) {
                assertEquals(pawnBoard[i][j], boardInitiator.getInitialPawnBoard()[i][j]);
            }
        }
    }
}