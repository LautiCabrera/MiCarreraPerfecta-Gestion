/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeCellEditor;
import Utils.Conexion;

/**
 *
 * @author BTF
 */
public class General_Interface_JF extends javax.swing.JFrame {
    
    
    /**
     * Creates new form Carrer_Word_Key_JF
     */
    
    General_Methods GM=new General_Methods();
    String Columns[];
    DefaultTableModel Tabla;
    ResultSet RS;
    int SelectedRow=-1;
    
    public General_Interface_JF() {
        initComponents();
        ConfigurationStart();
    }
    
    private void ConfigurationStart(){
        Columns=GM.Columns("users");
        PintarTablaColumns();
        PintarComboBox();
        jTable1.setSelectionMode(0);//2 Varios rangos; 1 Un rango; 0 Una fila
    }
    
    private void ResetButtons(){
        BTNAdd.setText("Añadir");
        BTNAdd.setEnabled(true);
        BTNModify.setText("Modificar");
        BTNModify.setEnabled(false);
        BTNDelete.setText("Borrar");
        BTNDelete.setEnabled(false);
        SelectedRow=-1;
    }
    
    private void PintarTablaColumns(){
        Tabla= (DefaultTableModel) jTable1.getModel();
        Tabla.setColumnCount(Columns.length);
        int count=0;
        for (String CN : Columns) {
                JTableHeader tableHeader = jTable1.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(CN);
                tableHeader.repaint();
                count++;
        }
    }
    
    private void PintarComboBox(){
        CBSelector.setModel(new DefaultComboBoxModel<>(Columns));
    }
    
    private void PintarTablaRows(ResultSet RSult){
        try{
        Tabla.setRowCount(0);
        Object O[]= new Object[Columns.length];
        if(RSult!=null){
            while(RSult.next()){
                for(int i=0;i<Columns.length;i++){
                        O[i]=RSult.getObject(Columns[i]);
                }
                Tabla.addRow(O);
            }
            O=null;
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void enableCellEditing(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LABTittle = new javax.swing.JLabel();
        BTNRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BTNDelete = new javax.swing.JButton();
        BTNModify = new javax.swing.JButton();
        BTNAdd = new javax.swing.JButton();
        BTNSearch = new javax.swing.JButton();
        TXTSearch = new javax.swing.JTextField();
        CBSelector = new javax.swing.JComboBox<>();
        BTNSelect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LABTittle.setText("jLabel1");

        BTNRefresh.setText("Refrescar");
        BTNRefresh.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNRefresh.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNRefreshActionPerformed(evt);
            }
        });

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        BTNDelete.setText("Borrar");
        BTNDelete.setEnabled(false);
        BTNDelete.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNDelete.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteActionPerformed(evt);
            }
        });

        BTNModify.setText("Modificar");
        BTNModify.setEnabled(false);
        BTNModify.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNModify.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNModifyActionPerformed(evt);
            }
        });

        BTNAdd.setText("Añadir");
        BTNAdd.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNAdd.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddActionPerformed(evt);
            }
        });

        BTNSearch.setText("Buscar");
        BTNSearch.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNSearch.setPreferredSize(new java.awt.Dimension(82, 24));

        TXTSearch.setText("Buscar");

        BTNSelect.setText("Select *");
        BTNSelect.setMaximumSize(new java.awt.Dimension(82, 24));
        BTNSelect.setPreferredSize(new java.awt.Dimension(82, 24));
        BTNSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(LABTittle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TXTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BTNRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTNAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNModify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LABTittle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNModify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        // TODO add your handling code here:
        jTable1.clearSelection();
        ResetButtons();
    }//GEN-LAST:event_BTNRefreshActionPerformed

    private void BTNSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSelectActionPerformed
        // TODO add your handling code here:
        Conexion con =new Conexion();
        RS=con.SendAndRecibe("select * from `ies9021_database`.users");
        PintarTablaRows(RS);
        ResetButtons();
    }//GEN-LAST:event_BTNSelectActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() != SelectedRow){
            ResetButtons();
            BTNModify.setEnabled(true);
            BTNDelete.setEnabled(true);
            SelectedRow=jTable1.getSelectedRow();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        // TODO add your handling code here:
        if(BTNDelete.getText().equals("Borrar")){
            BTNAdd.setText("Aceptar");
            BTNModify.setText("Cancelar");
            BTNDelete.setEnabled(false);
        }else{
            ResetButtons();
        }
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModifyActionPerformed
        // TODO add your handling code here:
        if(BTNModify.getText().equals("Modificar")){
            JOptionPane.showMessageDialog(null,"Ongoing");
            //Insertar La misma tabla de Añadir pero con funcionalidad de botones distinta
        }else{
            jTable1.clearSelection();
            ResetButtons();
        }
        
    }//GEN-LAST:event_BTNModifyActionPerformed

    private void BTNAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Ongoing");
        /*  Añadir la ventana de su parte, funcionara a base de Switch
            o podemos usar General Methods para lograrlo
        */
    }//GEN-LAST:event_BTNAddActionPerformed

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
            java.util.logging.Logger.getLogger(General_Interface_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(General_Interface_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(General_Interface_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(General_Interface_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new General_Interface_JF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAdd;
    private javax.swing.JButton BTNDelete;
    private javax.swing.JButton BTNModify;
    private javax.swing.JButton BTNRefresh;
    private javax.swing.JButton BTNSearch;
    private javax.swing.JButton BTNSelect;
    private javax.swing.JComboBox<String> CBSelector;
    private javax.swing.JLabel LABTittle;
    private javax.swing.JTextField TXTSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
