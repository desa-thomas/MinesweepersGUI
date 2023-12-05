import java.util.Scanner;

public class Tester {

    public static void main(String [] args){

        Minesweeper minesweeper = new Minesweeper(10,10,10);

        Scanner in = new Scanner(System.in);
        minesweeper.startGame(in);



    }
}
