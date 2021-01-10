package view.warningwindows;


import run.Run;
import view.MyButton;

public class RestartGameWindow extends WarningWindow {

    @Override
    protected void setComponents(){
        label.setText("Restart game?");
        firstButton = new MyButton("Restart", event->{
            Run.instance.newGame();
            dispose();
        });
        secondButton = new MyButton("Cancel", event->dispose());
    }
}
