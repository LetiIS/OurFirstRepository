package pack;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author abelash
 */

public class KeyValueComboBoxModel<K, V>
        extends AbstractListModel
        implements ComboBoxModel {

    private Map<K, V> elements = new TreeMap<K, V>();
    private Map.Entry<K, V> selected;
    private Map<Integer, K> indexKeyMap = new TreeMap<Integer, K>();
    private Map<K, Integer> keyIndexMap = new TreeMap<K, Integer>();

    @Override
    public int getSize() {
        return elements.size();
    }

    @Override
    public Map.Entry<? extends K, ? extends V> getElementAt(int index) {
        K key = indexKeyMap.get(index);
        return new AbstractMap.SimpleEntry(key, elements.get(key));
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem == null || !(anItem instanceof Map.Entry)) {
            return;
        }
        if (selected == null || !selected.equals(anItem)) {
            Map.Entry entry = (Map.Entry)anItem;
            if (elements.containsKey(entry.getKey())) {
                selected = entry;
                fireContentsChanged(this, -1, -1);
            }
        }
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    public void put(K key, V value) {
        if (!elements.containsKey(key)) {
            int index = elements.size();
            keyIndexMap.put(key, index);
            indexKeyMap.put(index, key);
        }
        elements.put(key, value);
        fireContentsChanged(this, 0, elements.size());
    }

    public void clear() {
        if (!elements.isEmpty()) {
            int lastIndex = elements.size() - 1;
            elements.clear();
            indexKeyMap.clear();
            keyIndexMap.clear();
            selected = null;
            fireIntervalRemoved(this, 0, lastIndex);
        } else {
            selected = null;
        }
    }

    public void setSelectedItemWithKey(K key) {
        if (elements.containsKey(key)) {
            selected = new AbstractMap.SimpleEntry<K, V>(key, elements.get(key));
            fireContentsChanged(this, -1, -1);
        } else {
            throw new IllegalArgumentException("ComboBoxModel doesn't contain element with key " + key);
        }
    }

    public K getSelectedKey() {
        if (selected == null) {
            return null;
        }
        return selected.getKey();
    }

    public V getSelectedValue() {
        if (selected == null) {
            return null;
        }
        return selected.getValue();
    }

       
}
