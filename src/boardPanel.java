import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class boardPanel extends JPanel{

    /*
    Attributes
    board - the game of minesweeper currently being played
    tileLabels - list of JButtons that represent the tiles
        the size of this list will be length+1 and width+1 to
        match with how I made the original minesweeper boards
        for the console... because it would be too much of a
        pain to go and change everything
     */
    Minesweeper board;
    JLabel [][] tileLabels;
    int width;
    int length;

    /*
    Constructor - calls setPanel method
     */
    public boardPanel(Minesweeper board){
        setPanel(board);

    }

    /*
    sets up or resets the board panel with the given minesweeper object
    public because the method with the frame containing this panel may use it to
    start another game
     */
    public void setPanel(Minesweeper board){
        this.board = board;
        this.length = board.getLength();
        this.width = board.getWidth();

        tileLabels = new JLabel[length+1][width+1];

        this.setLayout(new GridLayout(width, length));

        /*
        tile Labels setup
        tileLabels[0][0] = null
         */
        for (int x = 1; x < length+1; x++){
            for (int y = 1; y < width+1; y++){

                tileLabels[x][y] = new JLabel();
                this.add(tileLabels[x][y]);

                tileLabels[x][y].addMouseListener(new tileListener(x, y));

                tileLabels[x][y].setBorder(BorderFactory.createLineBorder(Color.black));

            }
        }
    }

    /*
    Action listener class
    prints to console the coordinates of the tile that has been pressed,,, for troubleshooting purposes
     */

    private static class tileListener implements MouseListener{

        private int x;
        private int y;

        public tileListener(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getButton());
            System.out.println("["+x+", "+y+"]");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
