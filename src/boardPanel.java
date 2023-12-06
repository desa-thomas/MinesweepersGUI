import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    private Minesweeper board;
    private JLabel [][] tileLabels;
    private int width;
    private int length;

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

        this.board.addPropertyChangeListener(new tileRevealListener());

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

                tileLabels[x][y].setFont(new Font("Serif", Font.BOLD, 20));
                tileLabels[x][y].setHorizontalAlignment(SwingConstants.CENTER);

            }
        }
    }

    /*
    Action listener class
    prints to console the coordinates of the tile that has been pressed,,, for troubleshooting purposes
     */
    private class tileListener implements MouseListener{

        private final int x;
        private final int y;
        private final JLabel tileLabel;

        public tileListener(int x, int y){
            this.x = x;
            this.y = y;
            tileLabel = tileLabels[x][y];
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            /*
            e.getButton() == 3 is right click
            e.getButton == 1 is left click
             */
            if(board.isBomb(x, y)){
                tileLabel.setText("B");
            }
            else if (e.getButton() == 1){
                tileLabel.setText(board.checkAdjacency(x, y) +"");
                board.revealTile(x, y);
                board.printGameBoard();
            }

            else{
                tileLabel.setText("*");
            }
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

    private class tileRevealListener implements PropertyChangeListener{

        //if tile is revealed recursively
        @Override
        public void propertyChange(PropertyChangeEvent evt) {

            //Used the old value and new value attributes to communicate
            // the coordinates of the tile that's been revealed

            if(evt.getPropertyName().equals(Minesweeper.COORDINATE_REVEALED)) {
                int x = (int) evt.getOldValue();
                int y = (int) evt.getNewValue();

                //if it's not a bomb check adj
                if(!board.isBomb(x, y)) {
                    tileLabels[x][y].setText(board.checkAdjacency(x, y) + "");
                }
            }

            else{
                int z = (int) evt.getOldValue();

                if(!board.isBomb(z, z)) {
                    tileLabels[z][z].setText((board.checkAdjacency(z, z)) + "");
                }
            }

        }
    }
}
