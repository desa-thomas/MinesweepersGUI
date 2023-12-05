import javax.swing.*;
import java.util.Scanner;

public class Tester {

    public static void main(String [] args){

        Minesweeper minesweeper = new Minesweeper(10,10,10);
        Scanner in = new Scanner(System.in);

        JFrame frame = new JFrame();
        JPanel boardP = new boardPanel(minesweeper);

        frame.setContentPane(boardP);

        frame.setLocationRelativeTo(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




    }
}
