/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Forms;

import static Utils.JsonDataFetcher.selectQuery;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Usuario
 */
public class managment_JF extends javax.swing.JFrame {
    
    ArrayList<String[]> RSS;
    int ADMIN[];
    DefaultTableModel TablaTX;
    
    
    /** Creates new form managment_JF */
    public managment_JF() {        
        initComponents();
        TablaTX = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int columns) {
                return false;
            }
        };
        TextoTabla.setModel(TablaTX);
        SetAdminCB();
        
    }

    private void SetAdminCB() {
        String SS = "id_management, name_management",
                TT = "ies9021_database.management",
                WW = null,
                MS[] = {"Seleccione Administracíon","Todo tipo"};
        JCB.setModel(SetComboBox(SS, TT, WW, MS));
    }
    
    private DefaultComboBoxModel SetComboBox(String SS, String TT, String WW, String MS[]) {
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
            ADMIN=DI;
            return (new DefaultComboBoxModel<>(JCBData));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private ArrayList ObtenerDatos(String SS, String TT, String WW) {
        return new ArrayList<>(selectQuery(SS, TT, WW));
    } 
    
   private void PintarTablas(boolean X, Object id) {
        String ID = String.valueOf(id), Carreras[];
        
            Carreras = new String[]{"ID", "Nombre", "Administracion", "Ultima Modificacion"};
       

        TablaTX.setColumnIdentifiers(Carreras);

//        System.out.println("PintarTablasRows ID = " + ID
//                          +"\ncolumns = " + TablaCareer.getColumnCount());
        try {
            TablaTX.setRowCount(0);
            ArrayList<String[]> Filler;
            String SS, TT, WW;
            SS = "id_university, name, id_management,f_update";
                    TT = "ies9021_database.university ";    
            if(X){
                WW="id_management="+ID;
            }else{
                WW=null;            
            }
            //System.out.println("Query = Select "+SS+" FROM "+TT+" WHERE "+WW);
            Filler = ObtenerDatos(SS, TT, WW);
            if (!Filler.isEmpty()) {
                for (String[] rowData : Filler) {
                    TablaTX.addRow(rowData);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JCB = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextoTabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBActionPerformed(evt);
            }
        });

        jTextField2.setText("Privada: 1");

        jTextField3.setText("Pública: 2");

        TextoTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TextoTabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCB, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    
    private void JCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBActionPerformed
      if (JCB.getSelectedIndex() > 1) {
            PintarTablas(true, ADMIN[JCB.getSelectedIndex()]);
        } else if (JCB.getSelectedIndex() == 1) {
            PintarTablas(false, -1);
        }
    }//GEN-LAST:event_JCBActionPerformed

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
            java.util.logging.Logger.getLogger(managment_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(managment_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(managment_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(managment_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new managment_JF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCB;
    private javax.swing.JTable TextoTabla;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
