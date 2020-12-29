package Run;

import Controller.Controller;
import Model.Model;
import View.MainFrame;

public class Run {
    public static Run instance;
    MainFrame mainFrame = new MainFrame();
    public Run(){
        new Controller(new Model(), mainFrame);
        mainFrame.pack();
    }
    public void newGame(){
        mainFrame.dispose();
        instance = new Run();
    }
    public static void main(String[] args){
        instance = new Run();
    }
}
