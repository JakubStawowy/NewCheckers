package view.warningwindows;

import view.SpecialColors;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;

public abstract class WarningWindow extends JFrame {
    protected JButton firstButton;
    protected JButton secondButton;
    protected JPanel mainPanel;
    protected JPanel buttonsPanel;
    protected JLabel label;
    public WarningWindow(){
        setTitle("Warning");
        setLayout();
    }
    private void setLayout(){
        mainPanel = new JPanel(new GridLayout(2,1, 20, 20));
        mainPanel.setBackground(SpecialColors.getMainColor());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setPreferredSize(new Dimension(400,200));
        buttonsPanel = new JPanel(new GridLayout(1,2, 20, 0));
        buttonsPanel.setBackground(SpecialColors.getMainColor());
        label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        setComponents();
        setButton(firstButton);
        setButton(secondButton);
        buttonsPanel.add(firstButton);
        buttonsPanel.add(secondButton);

        mainPanel.add(label);
        mainPanel.add(buttonsPanel);

        add(mainPanel);
    }
    public void open(){
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void setButton(JButton button){

        button.setBackground(SpecialColors.getButtonColor());
        button.setForeground(Color.WHITE);
        button.setBorder(null);
    }
    protected abstract void setComponents();
    public void setFirstButton(ActionListener actionListener){
        firstButton.addActionListener(actionListener);
    }
    public static void main(String[] args){
        WarningWindow warningWindow = new RestartGameWindow();
        warningWindow.open();
    }
}