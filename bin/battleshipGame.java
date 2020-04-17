
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;


class GameUI extends JFrame {
    /**
     * The GameUI (Main frame) contain these JPanels (you can find out these idea in 'Game UI.png'):
     * Main Function Panel          : Have some main function button, like 'Start game', 'Play with other people', etc.
     * Gaming Area Panel            : The main gaming panel. Include player's and enemy's panel.
     * Ships placement panel        : Let user drag ships into gaming area before start a new game. Include 'Reset button' and 'Ships icon'.
     * Ships icon explaintion panel : Explain what color/icon indicate which ship.
     * Battle Text Panel            : Record the battle log. Every battle information will show at here.
     * 
     * After a game begin, 'Ships icon explaintion panel' will replace 'Ships placement panel' to tell user some basic information.
     * When a game end, it will replace back as origin.
     * 
     * The building order:
     * MFP -> BTP -> GAP -> SPP -> SIEP
     * 
     */

    //Main function panel. Every important functional button will be placed here.
    JPanel mainFunctionPanel;
    //Output status and battle log. Almost every game information will updated in here.
    JPanel battleTextPanel;
    //The gaming area. Both player's and enemy's field pane will be placed into this panel.
    JPanel gamingAreaPanel;
    //Store ships object and icon to let user insert ships into gaming area.
    JPanel shipsPlacementPanel;
    //When game start, it will tell which color/icon indicate what status or ship.
    JPanel shipsIconExplaintionJPanel;
 

    //Start a game. "When a game started, it'll capture 'Stop', otherwise, it will capture 'Start'." -> Deprecated
    JButton btnStartGame;
    //Connect and play with other player by pick-to-pick.
    JButton btnPlayWithOthers;
    //Display setting frame to let user modify some little param.
    JButton btnSetting;
    //Exit game.
    JButton btnExitGame;


    //Output information and status at here.
    JTextArea battleLogTextArea;
    //Put a scroll pane into battleLogTextArea.
    JScrollPane battleLogScrollPane;


    /**
     * The field will split into two parts: visiable part, and excutting part.
     * The actual function will run in excutting part, and update visiable part to sync two parts.
     * 
     */
    //Player's visiable and excutting part arraylist definition.
    ArrayList<JLabel> playerFieldVisiablePart = new ArrayList<JLabel>();
    ArrayList<SeaGrid> playerFieldExcuttingPart = new ArrayList<SeaGrid>();
    //Enemy's visiable and excutting part arraylist definition.
    ArrayList<JLabel> enemyFieldVisiablePart = new ArrayList<JLabel>();
    ArrayList<SeaGrid> enemyFieldExcuttingPart = new ArrayList<SeaGrid>();

    //These labels are created to show which field is whos.
    JLabel lblPlayerFieldInfo = new JLabel("Player side");
    JLabel lblEnemyFieldInfo = new JLabel("Enemy side");

    //These panel will fill with visiable part which instead of the sea grid.
    JPanel playerFieldPane = new JPanel(null);  //Set layout = null
    JPanel enemyFieldPane = new JPanel(null);




    //Store the side length of the field.
    //The actual field size will be fieldSideLength ^ 2.
    final int fieldSideLength = 16;

    //Define each grid's side length.
    final int gridSideLength = 20;

    //Store the standard font params and create font style.
    final String fontName = "Arial";
    final int fontStyle = Font.PLAIN;
    final int fontSize = 16;
    final Font standardFontStyle = new Font(fontName, fontStyle, fontSize);


    //Convert specific grid's location to target arraylist index.
    //Notice that field will render x first, then be y.
    private int convertGridLocationToArraylistIndex(int x, int y) {
        return y * fieldSideLength + x;
    }

    //Convert specific grid's arraylist index to target grid location.
    private Point convertArraylistIndexToGridLocation(int index) {
        return new Point(index % fieldSideLength, index / fieldSideLength);
    }


    //Return a GridBagConstraints. Any component implement GridBagLayout need this.
    private GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;
        gbc.insets = insets;
        gbc.fill = fill;
        
        gbc.weighty = 0.1;
        return gbc;
    }
    private GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;
        gbc.insets = insets;
        gbc.fill = fill;
        
        gbc.weighty = 0.1;
        return gbc;
    }

    //Create main main function panel, include almost every game-effect funtional button.
    private void createMainFunctionPanel(Container container) {
        //Create panel and set layout as GridBagLayout.
        //mainFunctionPanel = new JPanel(new GridBagLayout());  NOPE. We'll set this up at createGameUI()
        
        //Define new buttons.
        btnStartGame = new JButton("Start");
        btnSetting = new JButton("Setting");
        btnPlayWithOthers = new JButton("Play with others");
        btnExitGame = new JButton("Exit");

        //Put these buttons into main function panel.
        mainFunctionPanel.add(btnStartGame, getGBC(0, 0, 1, 1, 1, new Insets(1, 1, 1, 1)));
        mainFunctionPanel.add(btnSetting, getGBC(1, 0, 1, 1, 1, new Insets(1, 1, 1, 1)));
        mainFunctionPanel.add(btnPlayWithOthers, getGBC(2, 0, 1, 1, 1, new Insets(1, 1, 1, 1)));
        mainFunctionPanel.add(btnExitGame, getGBC(3, 0, 1, 1, 1, new Insets(1, 1, 1, 1)));

        //Add Main function panel into contentpane.
        //container.add(mainFunctionPanel); NOPE. We'll use GridBagLayout at 

    }

    //Create battle text panel. Include battle text log, which will output gaming information and status.
    private void createBattleTextPanel(Container container) {

    }

    //Create gaming area panel, include with both player's and enemy's field pane.
    private void createGamingAreaPanel(Container container) {
        //Create panel and set layout as null.
        //gamingAreaPanel = new JPanel(null);   NOPE. We'll set this up at createGameUI()

        //Render both player's and enemy's field
        for (int y = 0; y < fieldSideLength; y++) {
            for (int x = 0; x < fieldSideLength; x++) {
                //Visiable part.
                playerFieldVisiablePart.add(new JLabel());
                enemyFieldVisiablePart.add(new JLabel());

                //Excutting part.
                playerFieldExcuttingPart.add(new SeaGrid(new Point(x, y)));
                enemyFieldExcuttingPart.add((new SeaGrid(new Point(x, y))));
            }
        }

        //Add these grid into field pane.
        for (Iterator<JLabel> iterator = playerFieldVisiablePart.iterator(); iterator.hasNext(); )
            playerFieldPane.add(iterator.next());
        for (Iterator<JLabel> iterator = enemyFieldVisiablePart.iterator(); iterator.hasNext(); )
            enemyFieldPane.add(iterator.next());
        
        //Add both these field into gaming area panel.
        gamingAreaPanel.add(playerFieldPane);
        gamingAreaPanel.add(enemyFieldPane);

        //Make some labels below these field panel to show information.
        gamingAreaPanel.add(lblPlayerFieldInfo);
        gamingAreaPanel.add(lblEnemyFieldInfo);

        //Last, add gaming area into cp.
        //container.add(gamingAreaPanel);   NOPE. We'll set this up at createGameUI()
    }

    //Create ships placement panel, include reset button and ships icon.
    private void createShipsPlacementPanel(Container container) {
        
    }



    //Add components.
    private void createGameUIComponents() {
        Container cp = getContentPane();
        cp.setLayout(new GridBagLayout());  //Set layout.

        //Create a new layout configuration class.
        GBC gbc = new GBC();

        //Create panels.
        mainFunctionPanel = new JPanel(new GridBagLayout());
        gamingAreaPanel = new JPanel(null);

        //Call these function to define and create components in each panel.
        createMainFunctionPanel(cp);
        System.out.println("Main Function Panel components created successful.");
        createGamingAreaPanel(cp);
        System.out.println("Gaming Area Panel components created successful.");

        //Put these panels into contentpane.
        cp.add(mainFunctionPanel, gbc.getGBC(0, 0, 3, 1, 1, new Insets(1, 1, 1, 1), 1.0, 0.01));
        System.out.println("Main Function Panel has been added into content pane.");
        cp.add(gamingAreaPanel, gbc.getGBC(1, 1, 1, 1, 1, new Insets(1, 1, 1, 1), 0.6, 0.8));
        
        System.out.println("Gaming Area Panel has been added into content pane.");

    }

    //Setup components' location and size from Main function panel.
    private void setupMainFunctionPanelLocationAndSize() {

    }

    //Setup components' location and size from Gaming Area Panel. Also gamingArea itself.
    private void setupGamingAreaPanelLocationAndSize() {
        //The width between two field pane.
        final int widthBetweenTwoPane = 25;

        //The actual size of gaming area panel will be the components prefered size add space in four direction.
        final int spareLeft = 10, spareRight = 10, spareTop = 20, spareBotton = 10;

        //Setup field's size and location
        enemyFieldPane.setSize(fieldSideLength*gridSideLength, fieldSideLength*gridSideLength);
        enemyFieldPane.setLocation(spareLeft, spareTop);
        playerFieldPane.setSize(fieldSideLength*gridSideLength, fieldSideLength*gridSideLength);
        playerFieldPane.setLocation(playerFieldPane.getX()+enemyFieldPane.getWidth()+widthBetweenTwoPane, spareTop);

        //Setup SeaGrid visiable part's location and size.
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

        //Set field information labels' size.
        final Dimension fieldInfoLabelSize = new Dimension(100, 20);
        lblPlayerFieldInfo.setSize(fieldInfoLabelSize);
        lblEnemyFieldInfo.setSize(fieldInfoLabelSize);

        //Set field information label below and middle of field pane.
        final int spaceBetweenFieldPaneAndInfoPane = 5;
        Point playerFieldPaneLocation = new Point();    //I define this for more convinent calculate.
        playerFieldPaneLocation.x = playerFieldPane.getX()+(playerFieldPane.getWidth()-lblPlayerFieldInfo.getWidth())/2;
        playerFieldPaneLocation.y = playerFieldPane.getY()+playerFieldPane.getHeight()+spaceBetweenFieldPaneAndInfoPane;
        lblPlayerFieldInfo.setLocation(playerFieldPaneLocation);
        Point enemyFieldPaneLocation = new Point();
        enemyFieldPaneLocation.x = enemyFieldPane.getX()+(enemyFieldPane.getWidth()-lblEnemyFieldInfo.getWidth())/2;
        enemyFieldPaneLocation.y = enemyFieldPane.getY()+enemyFieldPane.getHeight()+spaceBetweenFieldPaneAndInfoPane;
        lblEnemyFieldInfo.setLocation(enemyFieldPaneLocation);

        //Last, setup gaming areea panel's location and size.
        //gamingAreaPanel.setLocation(10, 10);
        gamingAreaPanel.setSize(playerFieldPane.getX()+playerFieldPane.getWidth()+spareRight, lblPlayerFieldInfo.getY()+lblPlayerFieldInfo.getHeight()+spareBotton);

        
    }

    
    //Setup components' location and size
    private void setupComponentsLocationAndSize() {
        //Gaming Area.
        setupGamingAreaPanelLocationAndSize();
        
    }

    //setup components' details from gaming area panel.
    private void setupGamingAreaPanelDetails() {
        gamingAreaPanel.setBorder(BorderFactory.createTitledBorder("hi"));

        //Setup each grid's background color and border.
        for (Iterator<JLabel> iterator = enemyFieldVisiablePart.iterator(); iterator.hasNext(); ) {
            JLabel tmp = iterator.next();

            tmp.setBackground(Color.LIGHT_GRAY);
            tmp.setOpaque(true);
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
        for (Iterator<JLabel> iterator = playerFieldVisiablePart.iterator(); iterator.hasNext(); ) {
            JLabel tmp = iterator.next();       //Store JLabel from iterator from player visiable part for config.

            tmp.setBackground(Color.CYAN);
            tmp.setOpaque(true);
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));  //Set color.
        }

        //Set field info label font.
        lblPlayerFieldInfo.setFont(standardFontStyle);
        lblEnemyFieldInfo.setFont(standardFontStyle);

        //Let the text be center in field info label.
        lblPlayerFieldInfo.setHorizontalAlignment(JLabel.CENTER);
        lblPlayerFieldInfo.setVerticalAlignment(JLabel.CENTER);
        lblEnemyFieldInfo.setHorizontalAlignment(JLabel.CENTER);
        lblEnemyFieldInfo.setVerticalAlignment(JLabel.CENTER);

        //Make a border suround field pane.
        // playerFieldPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // enemyFieldPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));



    }

    //Setup components' details, like border, background, font, etc.
    private void setupComponentsDetails() {
        //Gaming Area Panel.
        setupGamingAreaPanelDetails();

        
    }


    //Setup Game UI main setting.
    private void setupGameUI() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //getContentPane().setFont(new Font("Arial", Font.PLAIN, 20));

        setPreferredSize(new Dimension(1000, 500));
        pack();
    }

    //Constructor
    GameUI() {
        super();
        
        

        createGameUIComponents();
        setupComponentsLocationAndSize();
        setupComponentsDetails();

        setupGameUI();

    }
}

class hehe {
    GameUI haha;
    hehe(GameUI haha) {
        this.haha = haha;

    }
    void bruh() {
        
    }
}

//The basic size unit of every kind of ships. It'll be placed at field pane.
class SeaGrid {

    //Store location
    public final Point location;

    SeaGrid(Point p) {
        location = p;   //Initialize location.
        
    }
}

//Basic information and functions of ships
class Ships {

    //Store the size of ship
    public final int SIZE;

    Ships(int SIZE) {   //Constructor
        this.SIZE = SIZE;

    }
}


/**
 * The submarine unit.
 * Size             :   3
 * Max placements   :   2
 */
class Submarine {
    //Size
    public final int SIZE = 3;
    
}

/**
 * The destoryer unit.
 * Size             :   2
 * Max placements   :   2
 */
class Destroyer {
    //Size
    public final int SIZE = 2;

}

/**
 * The curiser unit.
 * Size             :   3
 * Max placements   :   3
 */
class Curiser {
    //Size
    public final int SIZE = 3;

}

/**
 * The battleship unit.
 * Size             :   4
 * Max placements   :   2
 */
class Battleship {
    //Size
    public final int SIZE = 4;

}

/**
 * The aricraft carrier unit.
 * Size             :   4
 * Max placements   :   1
 */
class AricraftCarrier {
    //Size
    public final int SIZE = 4;

}

public class battleshipGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                new GameUI();   //Create main frame.
            }
        });
    }
}