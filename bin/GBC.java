import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * This program is made to simplify my layout-work arguments.
 * It only contains method with (or related to) GridBagConstraints and GridBagLayout.
 */

public class GBC {
    //Return a GridBagConstraints. Any component implement GridBagLayout need this.
    public GridBagConstraints getGBC(int gridx, int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        return gbc;
    }
    public GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;        
        return gbc;
    }
    public GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;
        gbc.fill = fill;
        return gbc;
    }
    public GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;
        gbc.insets = insets;
        return gbc;
    }
    public GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;
        gbc.insets = insets;
        gbc.fill = fill;
        return gbc;
    }
    public GridBagConstraints getGBC(int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx; gbc.gridy = gridy;
        gbc.gridwidth = gridwidth; gbc.gridheight = gridheight;
        gbc.insets = insets;
        gbc.fill = fill;
        gbc.weightx = weightx; gbc.weighty = weighty;
        return gbc;
    }
}