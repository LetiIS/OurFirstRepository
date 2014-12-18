package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
//import javax.swing.table.*;

/**
 *
 * @author abelash
 */
public class ProfGroupRecordListPanel extends JPanel {

    private int courseNumber;
    private List<ProfGroupRecordPanel> profs;

    public static void main(String[] args) {
        JFrame f = new JFrame("TEST - ProfGroupRecordPanel");
        List<ProfGroupRecordPanel> p = new ArrayList<ProfGroupRecordPanel>();
        p.add(new ProfGroupRecordPanel("111111.11", "aaaaaaa", "222222.22", "bbbbbbb", 
                new String[][] {
                    {"1", "1111"},
                    {"2", "2222"},
                    {"3", "3333"},
                    {"4", "4444"}}));
        p.add(new ProfGroupRecordPanel("333333.33", "ccccccc", "444444.44", "ddddddd", 
                new String[][] {
                    {"5", "5555"},
                    {"6", "6666"},
                    {"7", "7777"},
                    {"8", "8888"}}));
        f.add(new ProfGroupRecordListPanel(1,p), BorderLayout.NORTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public ProfGroupRecordListPanel() {
    }

    public ProfGroupRecordListPanel(int courseNumber, List<ProfGroupRecordPanel> profs) {
        this.courseNumber = courseNumber;
        this.profs = profs;
        initPanels();
        setBackground(Color.LIGHT_GRAY);
    }

    private void initPanels() {
        setLayout(new GridLayout(profs.size() + 1, 1));
        add(new JLabel(courseNumber + "КУРС", JLabel.CENTER));
        for (ProfGroupRecordPanel p : profs) {
            add((ProfGroupRecordPanel)p);
        }
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Collection<ProfGroupRecordPanel> getProfs() {
        return profs;
    }

    public void setProfs(List<ProfGroupRecordPanel> profs) {
        this.profs = profs;
    }
}
