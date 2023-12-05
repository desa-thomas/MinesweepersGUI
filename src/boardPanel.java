import javax.swing.*;
public class boardPanel extends JPanel{

    /*
    Attributes
    board - the game of minesweeper currently being played
    mineButtons - list of JButtons that represent the tiles
        the size of this list will be length+1 and width+1 to
        match with how I made the original minesweeper boards
        for the console... because it would be too much of a
        pain to go and change everything
     */
    Minesweeper board;
    JButton [][] mineButtons;

    boardPanel(){
        setPanel(10,10,10);
    }

    public void setPanel(int length, int width, int mines){
        this.board = new Minesweeper(length, width, mines);
        mineButtons = new JButton[length+1][width+1];
    }


}
