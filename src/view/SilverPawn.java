package view;

import java.awt.Color;

public class SilverPawn extends Pawn {

    public SilverPawn(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
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
    }
}
