package Run;

import Controller.Controller;
import Model.CheckersRepository;
import View.MainFrame;

public class Run {
    public static Run instance;
    MainFrame mainFrame = new MainFrame();
    public Run(){
        new Controller(new CheckersRepository(), mainFrame);
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
