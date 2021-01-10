package run;

import controller.Controller;
import model.CheckersRepository;
import view.MainFrame;

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
