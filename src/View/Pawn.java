package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public abstract class Pawn extends JPanel {
    protected int xCoordinate;
    protected int yCoordinate;
    protected int previousXcoordinate = xCoordinate;
    protected int previousYcoordinate = yCoordinate;
    protected Boolean clicked = false;
    protected Boolean enable = false;
    protected Boolean queenStatus = false;
    protected Color color;
    protected Pawn(Board board, int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        setPawn();
    }
    public Boolean isClicked(){
        return clicked;
    }
    public Boolean isEnable(){
        return enable;
    }
    public Boolean isQueen(){ return queenStatus; }
    public void setEnable(Boolean flag){
        enable = flag;
    }
    public void setClicked(Boolean flag){
        clicked = flag;
    }
    private void setQueenStatus(){
        queenStatus = true;
    }
    protected abstract void setPawn();
    public void setPawnAsQueen(){
        setLayout(new GridLayout(3,3,5,5));
        for(int i = 0 ; i < 9 ; i++){
            if(i!=4){
                JPanel crownPart = new JPanel();
                crownPart.setBackground(new Color(218,165,32));
                add(crownPart);
            }
            else{
                JPanel panel = new JPanel();
                panel.setBackground(color);
                add(panel);
            }
        }
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setQueenStatus();
    }
    public abstract void setCoordinates(int xCoordinate, int yCoordinate);
    public int getxCoordinate(){
        return xCoordinate;
    }
    public int getyCoordinate(){
        return yCoordinate;
    }
    public void addNewMouseListener(MouseListener mouseListener){
        addMouseListener(mouseListener);
    }
    public void setUnableColor(){
        setBackground(Color.RED);
    }
    public void setNormalColor(){
        setBackground(color);
    }
}
