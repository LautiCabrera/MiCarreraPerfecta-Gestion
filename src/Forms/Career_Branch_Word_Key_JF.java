/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Models.Career_Word_Key;
import Models.WordsKey;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import Utils.JsonDataFetcher;
import static Utils.JsonDataFetcher.selectQuery;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Career_Branch_Word_Key_JF extends javax.swing.JFrame {

    ArrayList<String[]> RSS;
    int UNII[], CAMPI[], SCR = -1, SBR = -1, UW, CareerSelected, BranchSelected;
    boolean NotShowAgain = false;
    DefaultTableModel TablaCareer, TablaBranch;
    Tablon_JF TJF;

    /**
     * Creates new form Career_Branch_Word_Key_JF
     */
    public Career_Branch_Word_Key_JF() {
        initComponents();
        configuracionInicio();
    }
    
        public Career_Branch_Word_Key_JF(Tablon_JF TJF) {
        initComponents();
        UW=TJF.getUser();
        this.TJF =TJF;
        configuracionInicio();
    }

    private void configuracionInicio() {
        TablaCareer = (DefaultTableModel) JTCareer.getModel();
        TablaBranch = (DefaultTableModel) JTBranch.getModel();
        SetUniCB();
        PintarTablas(true, TablaCareer);
        PintarTablasRamas();
    }
//obtenemos los datos de la base de datos
    private DefaultComboBoxModel SetComboBox(String SS, String TT, String WW, String MS) {
        try {
            RSS = ObtenerDatos(SS, TT, WW);
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
//seleccion de universidades y campus
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

    private ArrayList ObtenerDatos(String SS, String TT, String WW) {
        return new ArrayList<>(selectQuery(SS, TT, WW));
    }
//muestro en cada tabla las "caracteristicas" a mostrar y luego busco las palabras
    private void PintarTablas(boolean X, DefaultTableModel Tabla) {
        String Carreras[];
        if (X) {
            Carreras = new String[]{"ID", "Nombre", "Palabras Claves", "Ultima Modificacion"};
        } else {
            Carreras = new String[]{"ID", "Nombre", "Campus", "Palabras Claves", "Ultima Modificacion"};
        }

        Tabla.setColumnIdentifiers(Carreras);
    }

    private void PintarTablasRamas() {
        PintarTablas(true, TablaBranch);
        ArrayList<String[]> Filler;
        String SS, TT, WW;
        SS = "distinct b.id_branch,b.name,(SELECT count(*) FROM ies9021_database.branch_words_key WHERE id_branch=b.id_branch),b.f_update ";
        TT = "ies9021_database.branch b"
                + " LEFT JOIN ies9021_database.branch_words_key bwk ON b.id_branch=bwk.id_branch ";
        Filler = ObtenerDatos(SS, TT, null);
        for (String[] strings : Filler) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println(Filler.isEmpty() + " está vacio ");
        if (!Filler.isEmpty()) {
            for (String[] rowData : Filler) {
                TablaBranch.addRow(rowData);
            }
        }

    }

    private void BuscarPalabra(String id_c, String id_b) {
        ArrayList<String[]> Filler;
        String SS, TT, WW, List = "";
        SS = "distinct word ";
        TT = "ies9021_database.words_key wk iNNER JOIN ies9021_database.branch_words_key b  ON  b.id_word_key= wk.id_word_key "
                + "INNER JOIN ies9021_database.career_word_key c ON   c.id_word_key= wk.id_word_key";
        WW = "c.id_career= " + id_c + " and b.id_branch= " + id_b;

        Filler = ObtenerDatos(SS, TT, WW);
        if (!Filler.isEmpty()) {
            for (String[] rowData : Filler) {
                List += Arrays.toString(rowData) + ", ";
            }
        } else {
            System.out.println(Filler.isEmpty() + " no tiene relacion ");
        }
        TXAPalabras.setText(List);
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
                    SS = "c.id_career, c.`name`, c.f_update, count(cwk.*)";
                    TT = "ies9021_database.career c "
                            + " inner join ies9021_database.campus_career cc ON c.id_career = cc.id_career "
                            + " inner join ies9021_database.career_word_key cwk ON c.id_career = cwk.id_career ";
                    WW = "cc.id_campus=" + ID + " AND cwk.id_career = c.id_career;";
                } else {
                    SS = "c.id_career, c.`name`, c.`description` ";
                    TT = "ies9021_database.career c"
                            + " INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career "
                            + " INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus ";
                    WW = "ca.id_university = " + UNII[JCBUniversity.getSelectedIndex()] + ";";
                }
            } else {
                SS = "c.id_career, c.name,(SELECT count(*) FROM ies9021_database.career_word_key WHERE id_career=c.id_career), c.f_update ";
                TT = "ies9021_database.career c"
                        + " INNER JOIN ies9021_database.campus_career cc ON c.id_career=cc.id_career ";
                WW = " cc.id_campus=" + ID + ";";
            }
            Filler = ObtenerDatos(SS, TT, WW);
            for (String[] strings : Filler) {
                System.out.println(Arrays.toString(strings));
            }
            System.out.println(Filler.isEmpty() + " está vacio ");
            if (!Filler.isEmpty()) {
                for (String[] rowData : Filler) {
                    TablaCareer.addRow(rowData);
                }
            }

        } catch (NumberFormatException e) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        JTCareer = new javax.swing.JTable();
        JCBUniversity = new javax.swing.JComboBox<>();
        JCBCampus = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXAPalabras = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTBranch = new javax.swing.JTable();
        BTSearch = new javax.swing.JButton();
        Age = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        JCBUniversity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JCBUniversity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBUniversityActionPerformed(evt);
            }
        });

        JCBCampus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JCBCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBCampusActionPerformed(evt);
            }
        });

        TXAPalabras.setColumns(20);
        TXAPalabras.setRows(5);
        jScrollPane2.setViewportView(TXAPalabras);

        JTBranch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTBranch.getTableHeader().setReorderingAllowed(false);
        JTBranch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTBranchMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(JTBranch);

        BTSearch.setText("Buscar");
        BTSearch.setEnabled(false);
        BTSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTSearchActionPerformed(evt);
            }
        });

        Age.setText("Agregar Palabras");
        Age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addComponent(BTSearch))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Age, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Age)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//mostrar las universidades que hay
    private void JCBUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBUniversityActionPerformed
        if (JCBUniversity.getSelectedIndex() > 0) {
            SetCamCB(UNII[JCBUniversity.getSelectedIndex()]);
        } else {
            JCBCampus.setModel(null);
        }
    }//GEN-LAST:event_JCBUniversityActionPerformed
//buscar los campus
    private void JCBCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBCampusActionPerformed
        if (JCBCampus.getSelectedIndex() > 0) {
            PintarTablasRows(TablaCareer, CAMPI[JCBCampus.getSelectedIndex()]);
        } else if (JCBCampus.getSelectedIndex() == 0) {
            PintarTablasRows(TablaCareer, -1);
        }
    }//GEN-LAST:event_JCBCampusActionPerformed

    private void BTSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTSearchActionPerformed

        BuscarPalabra(JTCareer.getValueAt(SCR, 0).toString(), JTBranch.getValueAt(SBR, 0).toString());

    }//GEN-LAST:event_BTSearchActionPerformed
//clickeo con mouse para buscar relacion
    private void JTCareerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTCareerMouseClicked
        // TODO add your handling code here:
            SCR = JTCareer.getSelectedRow();
            CareerSelected = Integer.parseInt(TablaCareer.getValueAt(SCR, 0).toString());        
        if (SCR != -1 && SBR != -1) {
            BTSearch.setEnabled(true);
        }
    }//GEN-LAST:event_JTCareerMouseClicked

    private void JTBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTBranchMouseClicked
        // TODO add your handling code here:
            SBR = JTBranch.getSelectedRow();
             BranchSelected = Integer.parseInt(TablaBranch.getValueAt(SCR, 0).toString());
        if (SCR != -1 && SBR != -1) {
            BTSearch.setEnabled(true);
        }
    }//GEN-LAST:event_JTBranchMouseClicked
//enviar al enzo
    private void AgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgeActionPerformed
        // TODO add your handling code here:
        if (!(TXAPalabras.getForeground().equals(Color.lightGray) || TXAPalabras.getText().isEmpty())) {
            Object[] Opcion = {"Si", "No"};
            if (JOptionPane.showOptionDialog(this, "¿Está seguro de que desea guardar las siguientes palabras?",
                     "Confirmación", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, Opcion, Opcion[0]) == JOptionPane.YES_OPTION) {
                TXAPalabras.setEnabled(false);
                String TXA = TXAPalabras.getText();
                TXA = TXA.replace(",,", ",");
                if (TXA.charAt(0) == 44) {
                    TXA = TXA.substring(1);
                }
                if (TXA.endsWith(",")) {
                    TXA = TXA.substring(0, TXA.length() - 1);
                }
                JOptionPane.showMessageDialog(this, "Deez Nutz");
                List<String> Lista = new ArrayList<>();
                for (String THX : TXA.split(",")) {
                    String Word = THX;
                    Lista.add(Word);
                }
                //Enviar Datos al enzo
                WordsKey WK = new WordsKey();
                WK.createWord(Lista, UW, this);
                Career_Word_Key CWK = new Career_Word_Key();
                CWK.CreateCWK(TXA, CareerSelected, UW);
                
                //Confirmar el envio de datos
                //Buscar los datos
            }
            TXAPalabras.setEnabled(true);
        }

    }//GEN-LAST:event_AgeActionPerformed

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
            java.util.logging.Logger.getLogger(Career_Branch_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Career_Branch_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Career_Branch_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Career_Branch_Word_Key_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Career_Branch_Word_Key_JF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Age;
    private javax.swing.JButton BTSearch;
    private javax.swing.JComboBox<String> JCBCampus;
    private javax.swing.JComboBox<String> JCBUniversity;
    private javax.swing.JTable JTBranch;
    private javax.swing.JTable JTCareer;
    private javax.swing.JTextArea TXAPalabras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
