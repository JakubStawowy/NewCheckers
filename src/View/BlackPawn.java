package View;

import java.awt.*;

public class BlackPawn extends Pawn {
    public BlackPawn(Board board, int xCoordinate, int yCoordinate) {
        super(board, xCoordinate, yCoordinate);
    }

    @Override
    protected void setPawn() {
        color = Color.BLACK;
        setBackground(color);
    }

    @Override
    public void setCoordinates(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.previousXcoordinate = xCoordinate;
        this.previousYcoordinate = yCoordinate;
    }
}
