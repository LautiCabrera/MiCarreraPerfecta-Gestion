package Forms;

import Models.Career_Word_Key;
import Models.University;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.selectQuery;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author BTF
 */
public class Career_Word_Key_JF1_1 extends javax.swing.JFrame {

    /*
        RSS = ResultSetString
        UNII = UNIversity Id
        CAMPI = CAMPus Id
        SCR = Selected Career Row
        UW= User Working
     */
    ArrayList<String[]> RSS;
    int UNII[], CAMPI[], SCR = -1, UW;
    boolean NotShowAgain = false;
    DefaultTableModel TablaCareer;

    public Career_Word_Key_JF1_1() {
        initComponents();
        ConfigurationStart();
    }
    
    public Career_Word_Key_JF1_1(int User) {
        initComponents();
        UW=User;
        ConfigurationStart();
    }

    private void ConfigurationStart() {
        TablaCareer= new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int columns){
                return false;
            }
        };
        JTCareer.setModel(TablaCareer);
        SetUniCB();
//        PintarTablaColumns(false);
    }

    private void TableReset() {
        BTNRegen.setEnabled(false);
        BTNSave.setEnabled(false);
        BTNLoad.setEnabled(false);
        LimpiarTablas(TablaCareer);
        SCR = -1;
        TXAWordsK.setText("");
        TXAWordsKFocusLost(null);
    }
    
    private void SearchReset(){
        JCBUniversity.setSelectedIndex(0);
        JCBUniversityActionPerformed(null);
    }

    private DefaultComboBoxModel SetComboBox(String SS, String TT, String WW, String[] MS) {
        try {
            RSS = ObtenerDatos(SS, TT, WW);
            int Siz = MS.length, DI[] = new int[RSS.size() + Siz];
            String JCBData[] = new String[RSS.size() + Siz];
            for (int i = -Siz; i < 0; i++) {
                DI[i + Siz] = i;
                JCBData[i + Siz] = MS[i + Siz];
            }
            for (int i = 0; i < RSS.size(); i++) {
                DI[i + Siz] = Integer.parseInt(RSS.get(i)[0]);//ID's
                JCBData[i + Siz] = RSS.get(i)[1];//Nombres
            }
            if (Siz > 1) {
                CAMPI = DI;
            } else {
                UNII = DI;
            }
            return (new DefaultComboBoxModel<>(JCBData));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void SetUniCB() {
        String SS = "id_university, name",
                TT = "ies9021_database.university",
                WW = null,
                MS[] = {"Seleccione la Universidad"};
        JCBUniversity.setModel(SetComboBox(SS, TT, WW, MS));
    }

    private void SetCamCB(int id_Uni) {
        String SS = "id_campus, name",
                TT = "ies9021_database.campus",
                WW = "id_university = " + id_Uni,
                MS[] = {"Seleccione un Campus", "Todos los Campus"};
        JCBCampus.setModel(SetComboBox(SS, TT, WW, MS));
    }

    /*
     String Query="SELECT distinct c.* FROM ies9021_database.career c "
                +"INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career"
                +"INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus WHERE ca.id_university = "+2+";";
        
     */
    private ArrayList ObtenerDatos(String SS, String TT, String WW) {
        return new ArrayList<>(selectQuery(SS, TT, WW));
    }

//    private void PintarTablaColumns(boolean X) {
//        String[] Carreras;
//        if (X) {
//            Carreras = new String[]{"ID", "Nombre", "Palabras Claves", "Ultima Modificacion"};
//        } else {
//            Carreras = new String[]{"ID", "Nombre", "Campus", "Palabras Claves", "Ultima Modificacion"};
//        }
//        
//        TablaCareer = (DefaultTableModel) JTCareer.getModel();
//        TablaCareer.setColumnIdentifiers(Carreras);
//        JOptionPane.showMessageDialog(this, "HALLO!");
//    }

    private void PintarTablas(boolean X, Object id) {
        String ID = String.valueOf(id), Carreras[];
        if (X) {
            Carreras = new String[]{"ID", "Nombre", "Palabras Claves", "Ultima Modificacion"};
        } else {
            Carreras = new String[]{"ID", "Nombre", "Campus", "Palabras Claves", "Ultima Modificacion"};
        }
        
        TablaCareer.setColumnIdentifiers(Carreras);
        
        System.out.println("PintarTablasRows ID = " + ID
                          +"\ncolumns = " + TablaCareer.getColumnCount());
        try {
            TablaCareer.setRowCount(0);
            ArrayList<String[]> Filler;
            String SS, TT, WW;
            if (TablaCareer.getColumnCount() > 4) {
                if (Integer.parseInt(ID) > 0) {
                    SS = "c.id_career, c.`name`, ca.`name`, COUNT(cwk.id_career_word_key), c.f_update";
                    TT = "ies9021_database.career c "
                            + "INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career "
                            + "INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus"
                            + " LEFT JOIN ies9021_database.career_word_key cwk ON c.id_career = cwk.id_career";
                    WW = "c.id_career = " + ID
                            + " GROUP BY c.id_career, c.`name`, c.f_update, ca.`name`;";
                } else {
                    SS = "c.id_career, c.`name`, ca.`name`, COUNT(cwk.id_career_word_key), c.f_update";
                    TT = "ies9021_database.career c "
                            + "INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career "
                            + "INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus"
                            + " LEFT JOIN ies9021_database.career_word_key cwk ON c.id_career = cwk.id_career";
                    WW = "ca.id_university = " + UNII[JCBUniversity.getSelectedIndex()]
                            + " GROUP BY c.id_career, c.`name`, c.f_update, ca.`name`;";
                }
            } else {
                SS = "c.id_career, c.`name`, COUNT(cwk.id_word_key), c.f_update";
                TT = "ies9021_database.career c "
                        + "inner join ies9021_database.campus_career cc ON c.id_career = cc.id_career "
                        + "left join ies9021_database.career_word_key cwk ON c.id_career = cwk.id_career ";
                WW = "cc.id_campus=" + ID + " GROUP BY c.id_career, c.name, c.f_update;";
            }
            System.out.println("Query = Select "+SS+" FROM "+TT+" WHERE "+WW);
            Filler = ObtenerDatos(SS, TT, WW);
            if (!Filler.isEmpty()) {
                for (String[] rowData : Filler) {
                   TablaCareer.addRow(rowData);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private String Cargar(int Val){
        String List="",
                TT=" ies9021_database.words_key W "
                + "inner join career_word_key CW ON W.id_word_key = CW.id_word_key "
              ,WW="CW.id_career = "+Val;
        ArrayList<String[]> Palabras= ObtenerDatos("word", TT, WW);
        for (String[] Palabra : Palabras) {
            List+=Arrays.toString(Palabra)+", ";
        }
        List=List.substring(0, List.length()-2).replace("]", "").replace("[", "");
        return List;
    }
    
    private void LimpiarTablas(DefaultTableModel Tabla) {
        for (int i = Tabla.getRowCount() - 1; i >= 0; i--) {
            Tabla.removeRow(i);
        }
    }

    private void Regen(String ID) {
        BTNRegen.setEnabled(false);
        try{
        Career_Word_Key CWK = new Career_Word_Key();
        ResultSetIES9021 RSI=CWK.Delete(ID);
        if(RSI.getState()){
            JOptionPane.showMessageDialog(this, "Operacion Realizada con Exito","COMPLETE",JOptionPane.PLAIN_MESSAGE);
            if(JCBCampus.isEnabled()){
                JCBCampusActionPerformed(null);
            }else{
                PintarTablas(false, ID);
            }
        }else{
            System.out.println(RSI.getClarification());
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        BTNLoad = new javax.swing.JButton();
        BTNSearchID = new javax.swing.JButton();
        TXTSearchID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TXAWordsK = new javax.swing.JTextArea();
        BTNRegen = new javax.swing.JButton();
        JLWord = new javax.swing.JLabel();
        JLCareer = new javax.swing.JLabel();
        JCBCampus = new javax.swing.JComboBox<>();
        JCBUniversity = new javax.swing.JComboBox<>();
        BTNMan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTCareer = new javax.swing.JTable();
        BTNSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTNLoad.setText("Cargar");
        BTNLoad.setEnabled(false);
        BTNLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNLoadActionPerformed(evt);
            }
        });

        BTNSearchID.setText("Buscar");
        BTNSearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSearchIDActionPerformed(evt);
            }
        });

        TXTSearchID.setForeground(Color.lightGray);
        TXTSearchID.setText("Buscar por ID");
        TXTSearchID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTSearchIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTSearchIDFocusLost(evt);
            }
        });
        TXTSearchID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTSearchIDKeyTyped(evt);
            }
        });

        TXAWordsK.setColumns(20);
        TXAWordsK.setForeground(Color.lightGray);
        TXAWordsK.setRows(5);
        TXAWordsK.setText("Ingrese las palabras clave separadas por coma");
        TXAWordsK.setEnabled(false);
        TXAWordsK.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXAWordsKFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXAWordsKFocusLost(evt);
            }
        });
        TXAWordsK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXAWordsKKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(TXAWordsK);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        BTNRegen.setText("Regenerar");
        BTNRegen.setEnabled(false);
        BTNRegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRegenActionPerformed(evt);
            }
        });

        JLWord.setText("Words Key");

        JLCareer.setText("Carreras");

        JCBCampus.setEnabled(false);
        JCBCampus.setPreferredSize(new java.awt.Dimension(250, 35));
        JCBCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBCampusActionPerformed(evt);
            }
        });

        JCBUniversity.setPreferredSize(new java.awt.Dimension(250, 35));
        JCBUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBUniversityActionPerformed(evt);
            }
        });

        BTNMan.setText("?");

        JTCareer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTCareer.getTableHeader().setReorderingAllowed(false);
        JTCareer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTCareerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTCareer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );

        BTNSave.setText("Guardar");
        BTNSave.setEnabled(false);
        BTNSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(BTNSave)
                .addGap(311, 311, 311))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(JLCareer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTNLoad)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BTNRegen))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(TXTSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(BTNSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                            .addComponent(BTNMan))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(JLWord)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(456, Short.MAX_VALUE)
                .addComponent(BTNSave)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTNMan))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JLCareer)
                        .addComponent(BTNRegen)
                        .addComponent(BTNLoad))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(JLWord)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBUniversityActionPerformed
        // TODO add your handling code here:
        TableReset();
        if (JCBUniversity.getSelectedIndex() > 0) {
            JCBCampus.setEnabled(true);
            SetCamCB(UNII[JCBUniversity.getSelectedIndex()]);
        } else {
            DefaultComboBoxModel Mod = (DefaultComboBoxModel) JCBCampus.getModel();
            Mod.removeAllElements();
            JCBCampus.setEnabled(false);
        }
    }//GEN-LAST:event_JCBUniversityActionPerformed

    private void JCBCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBCampusActionPerformed
        // TODO add your handling code here:
        TableReset();
        if (JCBCampus.getSelectedIndex() > 1) {
//            PintarTablaColumns(true);
            PintarTablas(true, CAMPI[JCBCampus.getSelectedIndex()]);
        } else if (JCBCampus.getSelectedIndex() == 1) {
//            PintarTablaColumns(false);
            PintarTablas(false, -1);
        }
    }//GEN-LAST:event_JCBCampusActionPerformed

    private void JTCareerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTCareerMouseClicked
        // TODO add your handling code here:
            SCR = JTCareer.getSelectedRow();
            int Val;
            if(TablaCareer.getColumnCount()>4){
                Val=Integer.parseInt(TablaCareer.getValueAt(SCR, 3).toString());
            }else{
                Val=Integer.parseInt(TablaCareer.getValueAt(SCR, 2).toString());
            }
            if(Val>1){BTNLoad.setEnabled(true);BTNRegen.setEnabled(true);
            }else{BTNLoad.setEnabled(false);BTNRegen.setEnabled(false);}
            BTNSave.setEnabled(true);
            TXAWordsK.setEnabled(true);
    }//GEN-LAST:event_JTCareerMouseClicked

    private void BTNRegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRegenActionPerformed
        // TODO add your handling code here:
        String Val=String.valueOf(TablaCareer.getValueAt(SCR, 0).toString());
        if (NotShowAgain) {
            Regen(Val);
        } else {
            Career_Word_Key_JF1_2 CWKJF12 = new Career_Word_Key_JF1_2(this);
            CWKJF12.setVisible(true);
            if (CWKJF12.Confirmacion) {
                NotShowAgain = CWKJF12.CHKB;
                Regen(Val);
                System.out.println("HAJIMARU!!");
            }
            CWKJF12.dispose();
        }
    }//GEN-LAST:event_BTNRegenActionPerformed

    private void BTNSearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSearchIDActionPerformed
        // TODO add your handling code here:
        if (!(TXTSearchID.getText().isBlank() || TXTSearchID.getText().isEmpty()
                || TXTSearchID.getForeground().equals(Color.lightGray))) {
            SearchReset();
            PintarTablas(false, TXTSearchID.getText());
        }
    }//GEN-LAST:event_BTNSearchIDActionPerformed

    private void TXTSearchIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTSearchIDFocusGained
        // TODO add your handling code here:
        if (TXTSearchID.getForeground().equals(Color.lightGray)) {
            TXTSearchID.setForeground(Color.black);
            TXTSearchID.setText("");
        }
    }//GEN-LAST:event_TXTSearchIDFocusGained

    private void TXTSearchIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTSearchIDFocusLost
        // TODO add your handling code here:
        if (TXTSearchID.getText().isBlank() || TXTSearchID.getText().isEmpty()) {
            TXTSearchID.setForeground(Color.lightGray);
            TXTSearchID.setText("Buscar por ID");
        }
    }//GEN-LAST:event_TXTSearchIDFocusLost

    private void TXTSearchIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTSearchIDKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (TXTSearchID.getText().length() >= 9) {
            evt.consume();
        } else {
            if (!(Character.isDigit(c) || c == 8 || c == 9 || c == 127
                    || c == '\n' || c == '\t')) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_TXTSearchIDKeyTyped

    private void BTNLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNLoadActionPerformed
        // TODO add your handling code here:
        int Val=Integer.parseInt(TablaCareer.getValueAt(SCR, 0).toString());
        TXAWordsK.setText(Cargar(Val));
        TXAWordsK.setForeground(Color.black);
    }//GEN-LAST:event_BTNLoadActionPerformed

    private void BTNSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSaveActionPerformed
        // TODO add your handling code here:
        if(!(TXAWordsK.getForeground().equals(Color.lightGray)||TXAWordsK.getText().isBlank()||TXAWordsK.getText().isEmpty())){
        Object[] Opcion ={"Si","No"};
        if(JOptionPane.showOptionDialog(this, "¿Está seguro de que desea guardar las siguientes palabras?"
                , "Confirmación", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, Opcion, Opcion[0])==JOptionPane.YES_OPTION){
            TXAWordsK.setEnabled(false);
            String TX=TXAWordsK.getText(), TXA[];
            TX=TX.replace(",,", ",");
            if(TX.charAt(0)==44){TX=TX.substring(1);}
            TXA=TX.split(",");
            System.out.println(Arrays.toString(TXA));
            JOptionPane.showMessageDialog(this, "Deez Nutz");
            //Enviar Datos al enzo
            //WordsKey WK = new WordsKey()
            //WK.createWORD
            //Confirmar el envio de datos
            //Buscar los datos
        }
        TXAWordsK.setEnabled(true);
        }
    }//GEN-LAST:event_BTNSaveActionPerformed

    private void TXAWordsKFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXAWordsKFocusGained
        // TODO add your handling code here:
        if(TXAWordsK.getForeground().equals(Color.lightGray)){
            TXAWordsK.setForeground(Color.black);
            TXAWordsK.setText("");
            if(SCR!=-1){BTNSave.setEnabled(true);}
        }
    }//GEN-LAST:event_TXAWordsKFocusGained

    private void TXAWordsKFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXAWordsKFocusLost
        // TODO add your handling code here:
        String TXA=TXAWordsK.getText().replace(",", "");
        if(TXA.isBlank()||TXA.isEmpty()){
            TXAWordsK.setForeground(Color.lightGray);
            TXAWordsK.setText("Ingrese las palabras clave separadas por coma");
        }
    }//GEN-LAST:event_TXAWordsKFocusLost

    private void TXAWordsKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXAWordsKKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String TXA=TXAWordsK.getText();
        if (!(Character.isAlphabetic(c) || c == 8 || c == 127
            || c == 44 || c == '\t' || c == 9)) {
            evt.consume();
        }
        else if(c==44&&TXA.contains(",,")){
            TXAWordsK.setText(TXA.replace(",,", ",")+",");
            evt.consume();}
        if(TXA.contains(",,")){TXAWordsK.setText(TXA.replaceAll(",,", ","));}
    }//GEN-LAST:event_TXAWordsKKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Career_Word_Key_JF1_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNLoad;
    private javax.swing.JButton BTNMan;
    private javax.swing.JButton BTNRegen;
    private javax.swing.JButton BTNSave;
    private javax.swing.JButton BTNSearchID;
    private javax.swing.JComboBox<String> JCBCampus;
    private javax.swing.JComboBox<String> JCBUniversity;
    private javax.swing.JLabel JLCareer;
    private javax.swing.JLabel JLWord;
    private javax.swing.JTable JTCareer;
    private javax.swing.JTextArea TXAWordsK;
    private javax.swing.JTextField TXTSearchID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
