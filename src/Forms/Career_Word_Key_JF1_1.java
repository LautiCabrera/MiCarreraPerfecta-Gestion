package Forms;

import Models.University;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.selectQuery;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
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
        RSI = ResultSetIes
        UNII = UNIversity Id
        CAMPI = CAMPus Id
        SCR = Selected Career Row
        SWKR = Selected Word Key Row
     */
    ArrayList<String[]> RSS;
    int UNII[], CAMPI[], SCR = -1;
    boolean NotShowAgain=false;
    DefaultTableModel TablaCareer;

    public Career_Word_Key_JF1_1() {
        initComponents();
        ConfigurationStart();
    }

    private void ConfigurationStart() {
        SetUniCB();
        PintarTablaColumns(false);
    }

    private void FullReset() {
        BTNRegen.setEnabled(false);
        LimpiarTablas(TablaCareer);
        SCR = -1;
    }


    private DefaultComboBoxModel SetComboBox(String SS, String TT, String WW, String MS) {
        try {
            RSS=ObtenerDatos(SS, TT, WW);
            int DI[] = new int[RSS.size() + 1];
            String JCBData[] = new String[RSS.size() + 1];
            DI[0] = -1;
            JCBData[0] = MS;
            for (int i = 0; i < RSS.size(); i++) {
                DI[i + 1] = Integer.parseInt(RSS.get(i)[0]);//ID's
                JCBData[i + 1] = RSS.get(i)[1];//Nombres
            }
            if (TT.endsWith("campus")) {
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
                MS = "Seleccione la Universidad";
        JCBUniversity.setModel(SetComboBox(SS, TT, WW, MS));
    }

    private void SetCamCB(int id_Uni) {
        String SS = "id_campus, name",
                TT = "ies9021_database.campus",
                WW = "id_university = " + id_Uni,
                MS = "Todos los Campus";
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

    private void PintarTablaColumns(boolean X) {
        String[] Carreras;
        if(X){Carreras = new String[]{"ID", "Nombre", "Palabras Claves","Ultima Modificacion"};
        }else{Carreras = new String[]{"ID", "Nombre", "Campus", "Palabras Claves","Ultima Modificacion"};}
        
        TablaCareer = (DefaultTableModel) JTCareer.getModel();
        TablaCareer.setColumnCount(Carreras.length);
        int count = 0;
        for (String CR : Carreras) {
            JTableHeader tableHeader = JTCareer.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(count);
            tableColumn.setHeaderValue(CR);
            tableHeader.repaint();
            count++;
        }
    }

    private void PintarTablasRows(DefaultTableModel Tabla, Object id) {
        String ID = String.valueOf(id);
        System.out.println("PintarTablasRows ID = " + ID);
        try {
            Tabla.setRowCount(0);
            ArrayList<String[]> Filler;
            String SS, TT, WW;
            if (Tabla.getColumnCount() > 4) {
                if (Integer.parseInt(ID) > 0) {
                    SS = "c.id_career, c.`name`, c.`description` ";
                    TT = "ies9021_database.career c "
                            + "inner join ies9021_database.campus_career cc ON c.id_career = cc.id_career";
                    WW = "cc.id_campus=" + ID + ";";
                } else {
                    SS = "c.id_career, c.`name`, c.`description` ";
                    TT = "ies9021_database.career c"
                            + "INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career"
                            + "INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus";
                    WW = "ca.id_university = " + UNII[JCBUniversity.getSelectedIndex()] + ";";
                    }
            } else {
                SS = "wk.id_word_key, word ";
                TT = "ies9021_database.words_key wk"
                        + "inner join ies9021_database.career_word_key cwk ON wk.id_word_key = cwk.id_word_key";
                WW = "where cwk.id_career=" + ID + ";";
            }
            Filler = ObtenerDatos(SS, TT, WW);
            if (!Filler.isEmpty()) {
                Object O[][] = new Object[Filler.size()][];
                for (int i = 0; i < Filler.size(); i++) {
                    O[i]=Filler.get(i);
                }
                Tabla.setDataVector(O, null);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void LimpiarTablas(DefaultTableModel Tabla) {
        for (int i = Tabla.getRowCount() - 1; i >= 0; i--) {
            Tabla.removeRow(i);
        }
    }
    
    private void Regen(){
        BTNRegen.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JCBUniversity = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTCareer = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        JLCareer = new javax.swing.JLabel();
        JLWord = new javax.swing.JLabel();
        JCBCampus = new javax.swing.JComboBox<>();
        BTNRegen = new javax.swing.JButton();
        BTNSave = new javax.swing.JButton();
        BTNMan = new javax.swing.JButton();
        TXTSearchID = new javax.swing.JTextField();
        BTNSearchID = new javax.swing.JButton();
        BTNLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JCBUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBUniversityActionPerformed(evt);
            }
        });

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        JLCareer.setText("Carreras");

        JLWord.setText("Words Key");

        JCBCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBCampusActionPerformed(evt);
            }
        });

        BTNRegen.setText("Regenerar");
        BTNRegen.setEnabled(false);
        BTNRegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRegenActionPerformed(evt);
            }
        });

        BTNSave.setText("Guardar");
        BTNSave.setEnabled(false);

        BTNMan.setText("?");

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

        BTNSearchID.setText("Buscar");
        BTNSearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSearchIDActionPerformed(evt);
            }
        });

        BTNLoad.setText("Cargar");
        BTNLoad.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLCareer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTNRegen))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TXTSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTNSearchID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNMan))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLWord)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(BTNSave)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JCBCampus, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(TXTSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTNSearchID))
                        .addComponent(JCBUniversity))
                    .addComponent(BTNMan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLCareer)
                    .addComponent(BTNRegen)
                    .addComponent(BTNLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JLWord)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNSave)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBUniversityActionPerformed
        // TODO add your handling code here:
        if (JCBUniversity.getSelectedIndex() > 0) {
            SetCamCB(UNII[JCBUniversity.getSelectedIndex()]);
        } else {
            JCBCampus.setModel(null);
        }
        FullReset();
    }//GEN-LAST:event_JCBUniversityActionPerformed

    private void JCBCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBCampusActionPerformed
        // TODO add your handling code here:
        if (JCBCampus.getSelectedIndex() > 0) {
            PintarTablasRows(TablaCareer, CAMPI[JCBCampus.getSelectedIndex()]);
        } else if (JCBCampus.getSelectedIndex() == 0) {
            PintarTablasRows(TablaCareer, -1);
        }
        FullReset();
    }//GEN-LAST:event_JCBCampusActionPerformed

    private void JTCareerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTCareerMouseClicked
        // TODO add your handling code here:
        if (JTCareer.getSelectedRow() != SCR) {
            BTNRegen.setEnabled(true);
            SCR = JTCareer.getSelectedRow();
        } else {
            BTNRegen.setEnabled(true);
            SCR = JTCareer.getSelectedRow();
        }
    }//GEN-LAST:event_JTCareerMouseClicked

    private void BTNRegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRegenActionPerformed
        // TODO add your handling code here:
        if(NotShowAgain){
            Regen();
        }else{
            Career_Word_Key_JF1_2 CWKJF12= new Career_Word_Key_JF1_2(this);
            if(CWKJF12.Confirmacion){
                NotShowAgain=CWKJF12.CHKB;
                Regen();
            }
            CWKJF12.dispose();
        }
    }//GEN-LAST:event_BTNRegenActionPerformed

    private void BTNSearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSearchIDActionPerformed
        // TODO add your handling code here:
        if(!(TXTSearchID.getText().isBlank()||TXTSearchID.getText().isEmpty()
                ||TXTSearchID.getForeground().equals(Color.lightGray))){
            PintarTablasRows(TablaCareer, TXTSearchID.getText());
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
            if (!(Character.isDigit(c)|| c == 8 || c == 127
                    || c == '\n' || c == '\t' )) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_TXTSearchIDKeyTyped

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
    private javax.swing.JTextField TXTSearchID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
