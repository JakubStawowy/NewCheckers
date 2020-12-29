package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Board board;
    private LeftPanel leftPanel;
    public MainFrame(){
        board = new Board();
        setTitle("Checkers");
        setLayout();
        setResizable(false);
        setVisible(true);
    }
    private void setLayout(){
        getContentPane().setBackground(SpecialColors.getMainColor());
        setBackground(SpecialColors.getMainColor());
        JPanel mainPanel = new JPanel(new BorderLayout());
        leftPanel = new LeftPanel();
        mainPanel.setBackground(SpecialColors.getMainColor());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(board, BorderLayout.EAST);

        add(mainPanel);
    }
    public Board getBoard(){
        return board;
    }
    public LeftPanel getLeftPanel(){
        return leftPanel;
    }
}
