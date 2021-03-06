package view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
    private Board board;
    private LeftPanel leftPanel;
    public MainFrame(){
        board = new Board();
        leftPanel = new LeftPanel();
        setTitle("Checkers");
        setLayout();
        setResizable(false);
        setVisible(true);
    }
    private void setLayout(){
        getContentPane().setBackground(SpecialColors.getMainColor());
        setBackground(SpecialColors.getMainColor());
        JPanel mainPanel = new JPanel(new BorderLayout());
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
