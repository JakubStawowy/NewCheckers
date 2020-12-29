package View;

import java.awt.*;

public class SilverPawn extends Pawn {

    public SilverPawn(Board board, int xCoordinate, int yCoordinate) {
        super(board, xCoordinate, yCoordinate);
        enable = true;
    }
    @Override
    public void setPawn() {
        color = new Color(128,128,128);
        setBackground(new Color(128,128,128));
    }

    @Override
    public void setCoordinates(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.previousXcoordinate = xCoordinate;
        this.previousYcoordinate = yCoordinate;
    }
}
