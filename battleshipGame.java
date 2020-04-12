
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


class gameFrame extends JFrame {

    /**
     * The field will split into two parts: visiable part, and excutting part.
     * The actual function will run in excutting part, and update visiable part to sync two parts.
     * 
     */
    //Player's visiable and excutting part arraylist definition.
    ArrayList<JLabel> playerFieldVisiablePart = new ArrayList<JLabel>();
    ArrayList<seaGrid> playerFieldExcuttingPart = new ArrayList<seaGrid>();
    //Enemy's visiable and excutting part arraylist definition.
    ArrayList<JLabel> enemyFieldVisiablePart = new ArrayList<JLabel>();
    ArrayList<seaGrid> enemyFieldExcuttingPart = new ArrayList<seaGrid>();

    //These panel will fill with visiable part which instead of the sea grid.
    JPanel playerFieldPane = new JPanel(null);  //Set layout = null
    JPanel enemyFieldPane = new JPanel(null);

    //Store the side length of the field.
    //The actual field size will be fieldSideLength ^ 2.
    final int fieldSideLength = 16;

    //Define each grid's side length.
    final int gridSideLength = 20;
    
    //Convert specific grid's location to target arraylist index.
    //Notice that field will render x first, then be y.
    private int convertGridLocationToArraylistIndex(int x, int y) {
        return y * fieldSideLength + x;
    }

    //Convert specific grid's arraylist index to target grid location.
    private Point convertArraylistIndexToGridLocation(int index) {
        return new Point(index % fieldSideLength, index / fieldSideLength);
    }

    //Add components
    private void addComponents() {
        Container cp = getContentPane();
        cp.setLayout(null);     //We'll place components by giving location directly.

        
        //Render both player's and enemy's field
        for (int y = 0; y < fieldSideLength; y++) {
            for (int x = 0; x < fieldSideLength; x++) {
                //Visiable part
                playerFieldVisiablePart.add(new JLabel());
                enemyFieldVisiablePart.add(new JLabel());

                //Excutting part
                playerFieldExcuttingPart.add(new seaGrid(new Point(x, y)));
                enemyFieldExcuttingPart.add((new seaGrid(new Point(x, y))));
            }
        }

        //Add these grid into field pane
        for (Iterator<JLabel> iterator = playerFieldVisiablePart.iterator(); iterator.hasNext(); )
            playerFieldPane.add(iterator.next());
        for (Iterator<JLabel> iterator = enemyFieldVisiablePart.iterator(); iterator.hasNext(); )
            enemyFieldPane.add(iterator.next());
        
        //Add both these field into conatiner pane
        cp.add(playerFieldPane);
        cp.add(enemyFieldPane);

    }

    //Setup components' location and size
    private void setupComponentsLocationAndSize() {
        //Setup field's size and location


        //Setup JLabels' location and size.
        for (int x = 0; x < fieldSideLength; x++) {
            for (int y = 0; y < fieldSideLength; y++) {
                JLabel playerGrid = playerFieldVisiablePart.get(convertGridLocationToArraylistIndex(x, y));
                //playerFieldVisiablePart.get(convertGridLocationToArraylistIndex(x, y)).setLocation(x * gridSideLength, y * gridSideLength);
                playerGrid.setLocation(x*gridSideLength, y*gridSideLength);
                playerGrid.setSize(gridSideLength, gridSideLength);

                
                JLabel enemyGrid = enemyFieldVisiablePart.get(convertGridLocationToArraylistIndex(x, y));
                //Notice that the rendering order on the enemy side is horizontal-reverse.
                enemyGrid.setLocation((fieldSideLength-x-1)*gridSideLength, (fieldSideLength-y-1)*gridSideLength);
                enemyGrid.setSize(gridSideLength, gridSideLength);
            }
        }
    }

    //Setup components' details.
    private void setupComponentsDetails() {
        
        
    }


    //Setup frame
    private void setupFrame() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(200, 200));
        
    }

    //Constructor
    gameFrame() {
        super();

        setupFrame();
        
    }
}

//The basic size unit of every kind of ships. It'll be placed at field pane.
class seaGrid {

    //Store location
    public final Point location;

    seaGrid(Point p) {
        location = p;   //Initialize location.
        
    }
}

//Basic information and functions of ships
class ships {

    //Store the size of ship
    public final int SIZE;

    ships(int SIZE) {   //Constructor
        this.SIZE = SIZE;

    }
}


/**
 * The submarine unit.
 * Size             :   3
 * Max placements   :   2
 */
class submarine {
    //Size
    public final int SIZE = 3;
    
}

/**
 * The destoryer unit.
 * Size             :   2
 * Max placements   :   2
 */
class destroyer {
    //Size
    public final int SIZE = 2;

}

/**
 * The curiser unit.
 * Size             :   3
 * Max placements   :   3
 */
class curiser {
    //Size
    public final int SIZE = 3;

}

/**
 * The battleship unit.
 * Size             :   4
 * Max placements   :   2
 */
class battleship {
    //Size
    public final int SIZE = 4;

}

/**
 * The aricraft carrier unit.
 * Size             :   4
 * Max placements   :   1
 */
class aricraftCarrier {
    //Size
    public final int SIZE = 4;

}

public class battleshipGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                new gameFrame();
            }
        });
    }
}