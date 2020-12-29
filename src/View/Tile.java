package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel {
    private Color backgroundColor;
    private Color hoverColor;
    private int xCoordinate;
    private int yCoordinate;
    private Boolean enabled;
    private Pawn pawn = null;
    private final MouseListener basicMouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(backgroundColor);
        }
    };
    public Tile(Color backgroundColor, Color hoverColor, int xCoordinate, int yCoordinate){
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        enabled = false;
        setTile();
    }
    private void setTile(){
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(backgroundColor);
        int tileSize = 80;
        setPreferredSize(new Dimension(tileSize, tileSize));
        addMouseListener(basicMouseListener);
    }
    public void addPawn(Pawn pawn){
        this.pawn = pawn;
        add(pawn);
        revalidate();
        repaint();
    }
    public void removePawn(){
        this.pawn = null;
        for(Component c: getComponents())
            remove(c);
        revalidate();
        repaint();
    }
    public Pawn getPawn(){
        return pawn;
    }
    public int getxCoordinate(){
        return xCoordinate;
    }
    public int getyCoordinate(){
        return yCoordinate;
    }
    public void setColors(Color backgroundColor, Color hoverColor){
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        setBackground(backgroundColor);
    }

    public MouseListener getBasicMouseListener() {
        return basicMouseListener;
    }
    public void _setEnabled(Boolean flag){
        enabled = flag;
    }
    public boolean isEnabled(){
        return enabled;
    }

}
