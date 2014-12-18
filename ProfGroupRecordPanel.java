package pack;

import javax.swing.*;

/**
 *
 * @author abelash
 */
public class ProfGroupRecordPanel extends JPanel {

    private String nCode; 
    private String nName;
    private String pCode;
    private String pNode;
    private KeyValueComboBoxModel<String, String> groups;
    
    public static void main(String[] args) {
        JFrame f = new JFrame("TEST - ProfGroupRecordPanel");
        f.add(new ProfGroupRecordPanel("111111.11","aaaaaaa","222222.22","bbbbbbb", 
                new String[][] {
                    {"1", "3391"},
                    {"2", "3392"},
                    {"3", "3393"},
                    {"4", "3394"},
                    {"5", "3395"}}));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public ProfGroupRecordPanel() {
        initComponents();
    }

    public ProfGroupRecordPanel(String nCode, String nName, String pCode, String pNode, String[][] groups) {
        this.nCode = nCode;
        this.nName = nName;
        this.pCode = pCode;
        this.pNode = pNode;
        this.groups = new KeyValueComboBoxModel<String, String>();
        
        for (int i = 0; i < groups.length; i++) {
            this.groups.put((String)groups[i][0], (String)groups[i][1]);
        }
        initComponents();
        setValuesToUI();
        cbGroups.setRenderer(new ComboBoxRenderer());
        
        tfNCode.setEditable(false);
        tfNName.setEditable(false);
        tfPCode.setEditable(false);
        tfPName.setEditable(false);
    }

    private void initComponents() {

        tfNCode = new JTextField();
        tfNName = new JTextField();
        tfPCode = new JTextField();
        tfPName = new JTextField();
        cbGroups = new JComboBox();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tfNCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNName, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPName, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbGroups, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGroups, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
        );
    }
    
    private void setValuesToUI() {
        tfNCode.setText(nCode);
        tfNName.setText(nName);
        tfPCode.setText(pCode);
        tfPName.setText(pNode);
        cbGroups.setModel(groups);
    }
    
    public String getnCode() {
        return nCode;
    }
    
    public void setnCode(String nCode) {
        this.nCode = nCode;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpNode() {
        return pNode;
    }

    public void setpNode(String pNode) {
        this.pNode = pNode;
    }

    public KeyValueComboBoxModel<String, String> getGroups() {
        return groups;
    }
    
    public void setGroups(KeyValueComboBoxModel<String, String> groups) {
        this.groups = groups;
    }
    
    public void setGroups(String[][] groups) {
        this.groups = new KeyValueComboBoxModel<String, String>();
        
        for (int i = 0; i < groups.length; i++) {
            this.groups.put(groups[i][0], groups[i][1]);
        }
    }

    private JTextField tfNCode;
    private JTextField tfNName;
    private JTextField tfPCode;
    private JTextField tfPName;
    private JComboBox cbGroups;
}
