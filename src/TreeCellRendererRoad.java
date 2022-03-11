import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class TreeCellRendererRoad implements TreeCellRenderer{
	
	private final JLabel label;
	public TreeCellRendererRoad() {
		label= new JLabel();
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		// TODO Auto-generated method stub
		Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
        if (userObject instanceof RoadElement element) {
            URL imageUrl = getClass().getResource(element.getFlagIcon());
            if (imageUrl != null) {
            	ImageIcon icon = new ImageIcon(imageUrl);
                label.setIcon(icon);
            }
            label.setText(element.getName());
        } else {
            label.setIcon(null);
            label.setText(value.toString());
        }
        return label;
    
	}

}
