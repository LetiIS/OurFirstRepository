package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame {

    private KeyValueComboBoxModel<String, String> faculties = new KeyValueComboBoxModel<String, String>();
    private KeyValueComboBoxModel<String, String> kafs = new KeyValueComboBoxModel<String, String>();
    private int facId;
    private int kafId;
    private int level;
    private EducationYear year;
    private static final int firstYear = 2000;
    private static final int yearCount = 100;
    private static final String[] educationYears
            = EducationYear.getStringArray(EducationYear.getEducationYears(firstYear, yearCount));

    public MainFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        initData();
        initActions();
    }

    private void initComponents() {
        searchPanel = new JPanel();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 1));
        mainPanel.setBackground(Color.BLUE);

        lbFac = new javax.swing.JLabel();
        cbFaculties = new javax.swing.JComboBox();
        lbKaf = new javax.swing.JLabel();
        cbKafs = new javax.swing.JComboBox();
        rbBachelors = new javax.swing.JRadioButton();
        rbMasters = new javax.swing.JRadioButton();
        rbSpecialists = new javax.swing.JRadioButton();
        lbEducationYear = new javax.swing.JLabel();
        spEducationYears = new javax.swing.JSpinner();
        btFind = new javax.swing.JButton();

    }

    

    private JComboBox cbFaculties;
    private JComboBox cbKafs;
    private JLabel lbFac;
    private JLabel lbKaf;
    private JLabel lbEducationYear;
    private ButtonGroup grLevel;
    private JRadioButton rbBachelors;
    private JRadioButton rbMasters;
    private JRadioButton rbSpecialists;
    private JSpinner spEducationYears;
    private JButton btFind;
    private JPanel mainPanel;
    private JPanel searchPanel;
}
