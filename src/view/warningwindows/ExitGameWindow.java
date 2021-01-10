package view.warningwindows;

import view.MyButton;

public class ExitGameWindow extends WarningWindow {

    @Override
    protected void setComponents() {
        label.setText("Exit game?");
        firstButton = new MyButton("Exit", event->System.exit(0));
        secondButton = new MyButton("Cancel", event->dispose());
    }
}
