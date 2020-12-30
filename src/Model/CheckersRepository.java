package Model;

public class CheckersRepository {

    private Pawn[][] pawnBoard;
    private Boolean[][] turnBoard;
    private PawnController pawnController;
    private PawnNavigator pawnNavigator;
    private BoardInitiator boardInitiator;

    public CheckersRepository(){

        pawnController = new PawnController();
        pawnNavigator = new PawnNavigator();
        boardInitiator = new BoardInitiator();
        pawnBoard = boardInitiator.getInitialPawnBoard();

    }

    public Pawn[][] getPawnBoard(){
        return pawnBoard;
    }
    public Boolean[][] getInitialBoard(){
        return boardInitiator.getInitialBoard();
    }

    public Boolean[][] getTurnBoard(){
        return turnBoard;
    }

    public void changeTurn(final Pawn pawn){
        turnBoard = pawnController.getTurnBoard(pawnBoard, pawn);
    }
    public Boolean[][] getPriorityBoard(final Pawn pawn, final Pawn enemyPawn, final int direction){
        return pawnController.getPriorityBoard(pawnBoard, pawn, enemyPawn, direction);
    }

    public int[][] getMoveIndexes(final int xCoordinate, final int yCoordinate, final Pawn enemyPawn, final int direction){
        return pawnNavigator.getMoveIndexes(pawnBoard, xCoordinate, yCoordinate, enemyPawn, direction);
    }

    public int[][] getQueenMoveIndexes(final int xCoordinate, final int yCoordinate, final Pawn enemyPawn){
        return pawnNavigator.getQueenMoveIndexes(pawnBoard, xCoordinate, yCoordinate, enemyPawn);
    }
    public Pawn getBlackPawn(){
        return Pawn.BLACK;
    }
    public Pawn getSilverPawn(){
        return Pawn.SILVER;
    }
    public Pawn getBlackQueenPawn(){
        return Pawn.BLACKQUEEN;
    }
    public Pawn getSilverQueenPawn(){
        return Pawn.SILVERQUEEN;
    }
    public Pawn getEmptyPawn(){
        return Pawn.EMPTY;
    }
}
