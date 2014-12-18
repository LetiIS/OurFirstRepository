package pack;

import java.awt.Component;
import java.util.AbstractMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author abelash
 */
public class ComboBoxRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        Object item = value;
        if (item instanceof AbstractMap.SimpleEntry) {
            item = ((AbstractMap.SimpleEntry) item).getValue();
        }
        return super.getListCellRendererComponent(list, item, index, isSelected, cellHasFocus);

    }
}
