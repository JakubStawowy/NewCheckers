package Model;

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