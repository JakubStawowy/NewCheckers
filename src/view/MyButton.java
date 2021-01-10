package view;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton extends JButton {
    private Color buttonColor = new Color(68,48,34);
    private Color buttonHoverColor = new Color(85, 65, 51);
    public MyButton(String text, ActionListener actionListener){
        setText(text);
        addActionListener(actionListener);
        setBackground(buttonColor);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(null);
        addMouseListener(new MouseListener() {
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
                setBackground(buttonHoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(buttonColor);
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }
}
