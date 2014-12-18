package pack;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author abelash
 */
public class AllInformationMainPanel extends JPanel {

    private List<ProfGroupRecordListPanel> list;

    public static void main(String[] args) {
        JFrame f = new JFrame("TEST - ProfGroupRecordPanel");

        f.setLayout(new GridLayout(1, 1));
        f.add(new AllInformationMainPanel(AllInformationMainPanel.createDataList(21, 1, EducationYear.getEducationYear("2013/2014"))));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public AllInformationMainPanel() {
    }

    public AllInformationMainPanel(List<ProfGroupRecordListPanel> list) {
        this.list = list;
        setBackground(Color.red);
        initPanels();
    }

    private void initPanels() {
        createTitle();
        setLayout(new GridLayout(1, 1));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(list.size() + 1, 1));
        mainPanel.add(titlePanel);
        for (ProfGroupRecordListPanel p : list) {
            mainPanel.add((ProfGroupRecordListPanel) p);
        }
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setWheelScrollingEnabled(true);
        add(mainScrollPane);
    }

    private void createTitle() {
        titlePanel = new JPanel();

        JLabel lbNCode = new JLabel("      КОД      ", JLabel.CENTER);
        JLabel lbNName = new JLabel("НАЗВАНИЕ НАПРАВЛЕНИЯ", JLabel.CENTER);
        JLabel lbPCode = new JLabel("      КОД      ", JLabel.CENTER);
        JLabel lbPName = new JLabel("НАЗВАНИЕ ПРОФИЛЯ", JLabel.CENTER);
        JLabel lbGroups = new JLabel("ГРУППЫ", JLabel.CENTER);

        GroupLayout layout = new GroupLayout(titlePanel);
        titlePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNName, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPName, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbGroups, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbNName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbPCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbPName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbGroups, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
        );
    }

    public static List<ProfGroupRecordListPanel> createDataList(int kafId, int level, EducationYear year) {

        List<ProfGroupRecordListPanel> list = new ArrayList<ProfGroupRecordListPanel>();
        /*
         ЗАПОЛНЯЕМ СПИСОК list ДАННЫМИ ИЗ БД
         */
        List<ProfGroupRecordPanel> p;
        int currentCourse = 0;
        String currentProfileId = "";
        String currentProfileName = "";
        String currentTrendId = "";
        String currentTrendName = "";
        String[][] groups;
        int index;
        try {
            Connection conn = ConnectionToDB.getConnection();
            try {
                Statement stat = conn.createStatement();
                ResultSet res = stat.executeQuery(""
                        + "SELECT "
                        + "(" + year.getFirstYear() + " - g.formYear + 1) as courseNum, "
                        + "t.trend_Id as tId, "
                        + "t.trendName as tName, "
                        + "p.profile_Id as pId, "
                        + "p.profileName as pName, "
                        + "g.group_Id as gId, "
                        + "g.groupNumber as gNum "
                        + "FROM "
                        + "Trends t RIGHT JOIN ((Levels l INNER JOIN Profiles p ON l.level_Id = p.level_Id) RIGHT JOIN Groups g ON p.profile_Id = g.profile_Id) ON t.trend_Id = p.trend_Id "
                        + "WHERE"
                        + "(" + year.getFirstYear() + " - g.formYear >= 0) AND "
                        + "(" + year.getFirstYear() + " - g.formYear <= l.semCount / 2 - 1) AND "
                        + "(p.department_Id = " + kafId + ") AND "
                        + "(p.level_Id = " + level + ") "
                        + "ORDER BY "
                        + "g.formYear DESC, "
                        + "g.groupNumber");

                if (res.isBeforeFirst()) {
                    //инициализируем необходимые переменные
                    p = new ArrayList<ProfGroupRecordPanel>();
                    index = -1;
                    groups = new String[10][2];

                    boolean firstIteration = true;

                    while (res.next())//перебираем записи
                    {
                        if (firstIteration) {
                            //записываем данные первой записи
                            currentCourse = res.getInt("courseNum");
                            currentProfileId = res.getString("pId");
                            currentProfileName = res.getString("pName");
                            currentTrendId = res.getString("tId");
                            currentTrendName = res.getString("tName");
                            firstIteration = false;
                        }
                        if (currentCourse != res.getInt("courseNum"))//если номер курса поменялся
                        {
                            String[][] groupsRealSize = new String[index + 1][2];
                            for (int i = 0; i < index + 1; i++) {
                                groupsRealSize[i][0] = groups[i][0];
                                groupsRealSize[i][1] = groups[i][1];
                            }
                            //добавляем готовую строку таблицы данных
                            p.add(new ProfGroupRecordPanel(
                                    currentTrendId,
                                    currentTrendName,
                                    currentProfileId,
                                    currentProfileName,
                                    groupsRealSize));
                            //заносим собранные данные за последний курс
                            list.add(new ProfGroupRecordListPanel(currentCourse, p));
                            //обнуляем список
                            p = new ArrayList<ProfGroupRecordPanel>();
                            //начинаем формировать новый список групп ('gId', 'gNum')
                            //и записываем текущую группу
                            groups = new String[10][2];
                            index = 0;
                            groups[index][0] = res.getString("gId");
                            groups[index][1] = res.getString("gNum");

                            //записываем данные текущей записи
                            currentCourse = res.getInt("courseNum");
                            currentProfileId = res.getString("pId");
                            currentProfileName = res.getString("pName");
                            currentTrendId = res.getString("tId");
                            currentTrendName = res.getString("tName");
                        } else//если номер курса тот же
                        {
                            if (!currentProfileId.equals(res.getString("pId")))//если поменялся профиль
                            {
                                String[][] groupsRealSize = new String[index + 1][2];
                                for (int i = 0; i < index + 1; i++) {
                                    groupsRealSize[i][0] = groups[i][0];
                                    groupsRealSize[i][1] = groups[i][1];
                                }
                                //добавляем готовую строку таблицы данных
                                p.add(new ProfGroupRecordPanel(
                                        currentTrendId,
                                        currentTrendName,
                                        currentProfileId,
                                        currentProfileName,
                                        groupsRealSize));

                                //начинаем формировать новый список групп ('gId', 'gNum')
                                //и записываем текущую группу
                                groups = new String[10][2];
                                index = 0;
                                groups[index][0] = res.getString("gId");
                                groups[index][1] = res.getString("gNum");
                                //записываем данные текущей записи
                                currentCourse = res.getInt("courseNum");
                                currentProfileId = res.getString("pId");
                                currentProfileName = res.getString("pName");
                                currentTrendId = res.getString("tId");
                                currentTrendName = res.getString("tName");
                            } else//если профиль тот же
                            {
                                //записываем текущую группу
                                index++;
                                groups[index][0] = res.getString("gId");
                                groups[index][1] = res.getString("gNum");
                            }
                        }
                    }
                    String[][] groupsRealSize = new String[index + 1][2];
                    for (int i = 0; i < index + 1; i++) {
                        groupsRealSize[i][0] = groups[i][0];
                        groupsRealSize[i][1] = groups[i][1];
                    }
                    //добавляем готовую строку таблицы данных
                    p.add(new ProfGroupRecordPanel(
                            currentTrendId,
                            currentTrendName,
                            currentProfileId,
                            currentProfileName,
                            groupsRealSize));
                    //добавляем инфу за последний курс
                    list.add(new ProfGroupRecordListPanel(currentCourse, p));
                }

            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<ProfGroupRecordListPanel> getList() {
        return list;
    }

    public void setList(List<ProfGroupRecordListPanel> list) {
        this.list = list;
    }

    private JPanel titlePanel;
    private JPanel mainPanel;
}
