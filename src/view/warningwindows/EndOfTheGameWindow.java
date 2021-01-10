package view.warningwindows;


import view.MyButton;


public class EndOfTheGameWindow extends WarningWindow{
    public EndOfTheGameWindow(String text){
        label.setText(text);
    }
    @Override
    protected void setComponents(){
        firstButton = new MyButton("Restart game", event->dispose());
        secondButton = new MyButton("Exit game", event->System.exit(0));
    }
}