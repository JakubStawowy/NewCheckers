package Controller;

import Model.Model;
import Run.Run;
import View.*;
import View.WarningWindows.EndOfTheGameWindow;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {
    private Model model;
    private Board board;
    private MainFrame mainFrame;
    private Boolean[][] priorityBoard;
    public Controller(Model model, MainFrame mainFrame){
        this.model = model;
        this.mainFrame = mainFrame;
        this.board = mainFrame.getBoard();
        priorityBoard = model.getPriorityBoard2(model.getSilverTile(), model.getBlackTile(), -1);
        setInitialBoard();
    }

    private void setInitialBoard(){
        for(int i = 0 ; i < model.getBoardSize() ; i++)
            for(int j = 0 ; j < model.getBoardSize() ; j++){
                Pawn pawn;
                if(model.getInitialBoard()[i][j]) {
                    board.addTile(new Tile(SpecialColors.getTileBackgroundColor1(), SpecialColors.getTileHoverColor1(), i, j));
                }
                else {
                    board.addTile(new Tile(SpecialColors.getTileBackgroundColor2(), SpecialColors.getTileHoverColor2(), i, j));
                }
                if(model.getPawnBoard()[i][j] == model.getBlackTile()){
                    pawn = new BlackPawn(board, i, j);
                    pawn.addNewMouseListener(setMouseListener(
                        ()->{},
                        ()->setPawn(pawn),
                        ()->{
                            if(!(pawn.isEnable() && priorityBoard[pawn.getxCoordinate()][pawn.getyCoordinate()])){
                                pawn.setNormalColor();
                            }
                        },
                        ()->pawn.setCursor(new Cursor(Cursor.HAND_CURSOR)),
                        ()->pawn.setCursor(Cursor.getDefaultCursor())));
                    board.getBoard()[i][j].addPawn(pawn);
                }
                else if(model.getPawnBoard()[i][j] == model.getSilverTile()){
                    pawn = new SilverPawn(board, i, j);
                    pawn.addNewMouseListener(setMouseListener(
                        ()->{},
                        ()->setPawn(pawn),
                        ()->{
                            if(!(pawn.isEnable() && priorityBoard[pawn.getxCoordinate()][pawn.getyCoordinate()])){
                                pawn.setNormalColor();
                            }
                        },
                        ()->pawn.setCursor(new Cursor(Cursor.HAND_CURSOR)),
                        ()->pawn.setCursor(Cursor.getDefaultCursor())));
                    board.getBoard()[i][j].addPawn(pawn);
                }
            }
    }
    private void setTile(Tile tile, Pawn pawn){
        int[][] moveIndexes;
        final int boardSize = 8;

        if(pawn.getClass().isAssignableFrom(BlackPawn.class)) {
            if(tile.getxCoordinate() == 7)
                pawn.setPawnAsQueen();
            if(pawn.isQueen()) {
                moveIndexes = model.getQueenMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getSilverTile());
                model.getPawnBoard()[tile.getxCoordinate()][tile.getyCoordinate()] = model.getBlackQueenTile();
            }
            else {
                moveIndexes = model.getMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getSilverTile(), 1);
                model.getPawnBoard()[tile.getxCoordinate()][tile.getyCoordinate()] = model.getBlackTile();
            }
            model.changeTurn(model.getSilverTile());
            mainFrame.getLeftPanel().setColorPanelSilver();
        }
        else {
            if(tile.getxCoordinate() == 0)
                pawn.setPawnAsQueen();
            if(pawn.isQueen()) {
                moveIndexes = model.getQueenMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getBlackTile());
                model.getPawnBoard()[tile.getxCoordinate()][tile.getyCoordinate()] = model.getSilverQueenTile();
            }
            else {
                moveIndexes = model.getMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getBlackTile(), -1);
                model.getPawnBoard()[tile.getxCoordinate()][tile.getyCoordinate()] = model.getSilverTile();
            }
            model.changeTurn(model.getBlackTile());
            mainFrame.getLeftPanel().setColorPanelBlack();
        }
        for(int i = 0; i < boardSize; i++)
            for(int j = 0; j < boardSize; j++)
                if(board.getBoard()[i][j].getPawn()!=null)
                    board.getBoard()[i][j].getPawn().setEnable(model.getTurnBoard()[i][j]);
        tile.addPawn(pawn);
        pawn.setClicked(false);

        for(int i = 0; i < moveIndexes[1].length ; i+=2){
            if(moveIndexes[1][i]!=-1 && moveIndexes[1][i+1]!=-1){
                board.getBoard()[moveIndexes[1][i]][moveIndexes[1][i+1]].removePawn();
                model.getPawnBoard()[moveIndexes[1][i]][moveIndexes[1][i+1]] = model.getEmptyTile();
            }
        }
        board.getBoard()[pawn.getxCoordinate()][pawn.getyCoordinate()].removePawn();
        model.getPawnBoard()[pawn.getxCoordinate()][pawn.getyCoordinate()] = model.getEmptyTile();

        pawn.setCoordinates(tile.getxCoordinate(), tile.getyCoordinate());
        for(int i = 0; i < moveIndexes[0].length ; i+=2){
            tile = board.getBoard()[moveIndexes[0][i]][moveIndexes[0][i+1]];
            if(tile.isEnabled())
                board.disableTile(tile);
        }
        countPawns();
        if(pawn.getClass().isAssignableFrom(BlackPawn.class)){
            priorityBoard = model.getPriorityBoard2(model.getSilverTile(), model.getBlackTile(), -1);

        }
        else{
            priorityBoard = model.getPriorityBoard2(model.getBlackTile(), model.getSilverTile(), 1);
        }
    }
    private MouseListener setMouseListener(MyMouseListener mouseClicked, MyMouseListener mousePressed,MyMouseListener mouseReleased, MyMouseListener mouseEntered, MyMouseListener mouseExited){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClicked.mouseAction();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed.mouseAction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseReleased.mouseAction();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEntered.mouseAction();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseExited.mouseAction();
            }
        };
    }
    private void setPawn(Pawn pawn){
        int[][] moveIndexes;
        if(pawn.isEnable() && priorityBoard[pawn.getxCoordinate()][pawn.getyCoordinate()]){
            if(pawn.getClass().isAssignableFrom(BlackPawn.class)){
                if(pawn.isQueen()) {
                    moveIndexes = model.getQueenMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getSilverTile());
                }
                else
                    moveIndexes = model.getMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getSilverTile(), 1);
            }
            else{
                if(pawn.isQueen())
                    moveIndexes = model.getQueenMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getBlackTile());
                else
                    moveIndexes = model.getMoveIndexes(pawn.getxCoordinate(), pawn.getyCoordinate(), model.getBlackTile(), -1);
            }

            if(areMoveIndexesDifferent(moveIndexes[0], pawn.getxCoordinate(), pawn.getyCoordinate())){
                if(!pawn.isClicked()){
                    for(Tile[] tiles: board.getBoard())
                        for(Tile tile: tiles){
                            if(tile.isEnabled())
                                board.disableTile(tile);
                            if(tile.getPawn()!=null && tile.getPawn().isClicked())
                                tile.getPawn().setClicked(false);
                        }
                    for(int i = 0 ; i < moveIndexes[0].length ; i+=2){
                        if(moveIndexes[0][i] != pawn.getxCoordinate() || moveIndexes[0][i+1] != pawn.getyCoordinate()){
                            Tile tile = board.getBoard()[moveIndexes[0][i]][moveIndexes[0][i+1]];
                            if(tile.getPawn()==null && (tile.getxCoordinate()+tile.getyCoordinate())%2==0) {
                                board.enableTile(tile, setMouseListener(()->setTile(tile, pawn),()->setTile(tile, pawn),()->{}, ()->{}, ()->{}));
                            }
                        }
                    }
                    pawn.setClicked(true);
                }
                else{
                    for(int i = 0 ; i < moveIndexes[0].length ; i+=2){
                        if(moveIndexes[0][i] != pawn.getxCoordinate() || moveIndexes[0][i+1] != pawn.getyCoordinate()){
                            Tile tile = board.getBoard()[moveIndexes[0][i]][moveIndexes[0][i+1]];
                            if(tile.isEnabled())
                                board.disableTile(tile);
                        }
                    }
                    pawn.setClicked(false);
                }
            }
            else{
                pawn.setEnable(false);
                pawn.setUnableColor();
            }
        }
        else{
            pawn.setUnableColor();
        }
    }
    private void countPawns(){
        int blackPawnsNumber = 0;
        int silverPawnsNumber = 0;
        EndOfTheGameWindow endOfTheGameWindow = null;
        for(Tile[] tileRow: board.getBoard())
            for(Tile tile: tileRow){
                if(tile.getPawn()!=null)
                    if(tile.getPawn().getClass() == BlackPawn.class){
                        blackPawnsNumber++;
                    }
                    else if(tile.getPawn().getClass() == SilverPawn.class){
                        silverPawnsNumber++;
                    }
            }
        if(blackPawnsNumber == 0){
            endOfTheGameWindow = new EndOfTheGameWindow("Silver won!");
        }
        else if(silverPawnsNumber == 0){
            endOfTheGameWindow = new EndOfTheGameWindow("Black won!");
        }
        if(endOfTheGameWindow != null){
            endOfTheGameWindow.setFirstButton(event->{
                mainFrame.dispose();
                Run.instance = new Run();
            });
            endOfTheGameWindow.open();
        }
    }
    private boolean areMoveIndexesDifferent(int[] moveIndexes, int xCoordinate, int yCoordinate){
        for (int i = 0; i < moveIndexes.length; i++)
            if((i%2 == 0 && moveIndexes[i] != xCoordinate) || (i%2 == 1 && moveIndexes[i] != yCoordinate))
                return true;
        return false;
    }
    private interface MyMouseListener {
        void mouseAction();
    }
}