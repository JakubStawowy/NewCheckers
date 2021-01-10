package view;


import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseListener;

public class Board extends JPanel{

    private Tile[][] board;

    public Board(){
        board = new Tile[8][8];
        createLayout();
        setVisible(true);
    }
    private void createLayout(){
        setBackground(SpecialColors.getMainColor());
        setLayout(new GridLayout(8,8));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    public void addTile(Tile tile){
        board[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
        add(tile);
    }
    public void enableTile(Tile tile, MouseListener mouseListener){
        tile.addMouseListener(mouseListener);
        tile.setColors(Color.WHITE, Color.WHITE);
        tile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tile._setEnabled(true);
    }
    public void disableTile(Tile tile){
        for(MouseListener mouseListener: tile.getMouseListeners())
            tile.removeMouseListener(mouseListener);
        tile.addMouseListener(tile.getBasicMouseListener());
        tile.setColors(SpecialColors.getTileBackgroundColor1(), SpecialColors.getTileHoverColor1());
        tile.setCursor(Cursor.getDefaultCursor());
        tile._setEnabled(false);
    }
    public Tile[][] getBoard(){
        return board;
    }
}
