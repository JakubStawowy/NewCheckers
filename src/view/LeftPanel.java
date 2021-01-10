package view;

import view.warningwindows.ExitGameWindow;
import view.warningwindows.RestartGameWindow;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;

public class LeftPanel extends JPanel {
    private JPanel colorPanel;
    public LeftPanel(){
        setLayout();
    }
    private void setLayout(){
        setLayout(new GridLayout(4,1,0,20));
        JButton newGameButton = new MyButton("Restart Game", event -> {
            RestartGameWindow restartGameWindow = new RestartGameWindow();
            restartGameWindow.open();
        });
        JButton exitGameButton = new MyButton("Exit", event->{
            ExitGameWindow exitGameWindow = new ExitGameWindow();
            exitGameWindow.open();
        });
        JLabel turnLabel = new JLabel("Now is the turn of player:");
        turnLabel.setForeground(Color.WHITE);
        colorPanel = createColorPanel();
        colorPanel.setBackground(SpecialColors.getSilverPawnColor());
        JLabel l = new JLabel("Player's turn");
        l.setForeground(Color.WHITE);
        l.setHorizontalAlignment(JLabel.CENTER);
        colorPanel.add(l);
        setColorPanelSilver();
        setBackground(SpecialColors.getMainColor());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(createSubPanel(turnLabel));
        add(colorPanel);
        add(createSubPanel(newGameButton));
        add(createSubPanel(exitGameButton));
    }
    private JPanel createSubPanel(Component component){
        JPanel subPanel = new JPanel(new GridLayout(1,1));
        subPanel.setBorder(BorderFactory.createEmptyBorder(40,20,40,20));
        subPanel.setBackground(SpecialColors.getMainColor());
        subPanel.add(component);
        return subPanel;
    }
    private JPanel createColorPanel(){
        JPanel panel= new JPanel(new GridLayout(1,1));
        panel.setBackground(SpecialColors.getMainColor());
        return panel;
    }
    public void setColorPanelBlack(){
        colorPanel.setBackground(Color.BLACK);
    }
    public void setColorPanelSilver(){
        colorPanel.setBackground(SpecialColors.getSilverPawnColor());
    }
}
