package Forms;

import Models.University;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.SEND;
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
public class Career_Word_Key_JF1 extends javax.swing.JFrame {

    ArrayList<String[]> RSS;
    ResultSetIES9021 RSI;
    int UNII[],CAMPI[];
    DefaultTableModel Tabla1,Tabla2;
    
    public Career_Word_Key_JF1() {
        initComponents();
    }
    
    private void ConfigurationStart(){
        SetUniCB();
        PintarTablaColumns();
    }
    
    private DefaultComboBoxModel SetComboBox(String SS,String TT,String WW,String MS){
        try{
        ObtenerDatos(SS,TT,WW);
        int DI[]=new int[RSS.size()];
        String JCBData[]=new String[RSS.size()];
        DI[0]=-1;
        JCBData[0]=MS;
        for(int i=1;i<RSS.size()+1;i++){
            DI[i]=Integer.parseInt(RSS.get(i)[0]);//ID's
            JCBData[i]=RSS.get(i)[1];//Nombres
        }
        if(TT.endsWith("campus")){
            CAMPI=DI;
        }else{
            UNII=DI;
        }
        return (new DefaultComboBoxModel<>(JCBData));
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }
    
    private void SetUniCB(){
        String SS="id_universiry, name",
               TT="ies9021_database.university",
               WW=null,
               MS="Seleccione la Universidad";
        JCBUniversity.setModel(SetComboBox(SS, TT, WW, MS));
    }
    
    private void SetCamCB(int id_Uni){
        String SS="id_universiry, name",
               TT="ies9021_database.campus",
               WW="id_university = "+id_Uni,
               MS="Todos los Campus";
        JCBCampus.setModel(SetComboBox(SS, TT, WW, MS));
    }
    
    private <T> void ObtenerDatos(Class Clazz){
        RSI= new JsonDataFetcher<T>().fetchTableData(Clazz.getSimpleName(),Clazz);
    }
    /*
     String Query="SELECT distinct c.* FROM ies9021_database.career c "
                +"INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career"
                +"INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus WHERE ca.id_university = "+2+";";
        
    */
    
    private void ObtenerDatos(String SS,String TT,String WW){
        RSS=SEND(SS,TT,WW);
    }
    
    private void PintarTablaColumns(){
        String[] Carreras={"ID","Nombre","Descripcion"},
                 Palabras={"ID","Palabra Clave"};
        Tabla1= (DefaultTableModel) JTCareer.getModel();
        Tabla1.setColumnCount(Carreras.length);
        Tabla2= (DefaultTableModel) JTWordKey.getModel();
        Tabla2.setColumnCount(Palabras.length);
        int count=0;
        for (String CR : Carreras) {
                JTableHeader tableHeader = JTCareer.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(CR);
                tableHeader.repaint();
                count++;
        }
        for (String PAL : Palabras) {
                JTableHeader tableHeader = JTWordKey.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(PAL);
                tableHeader.repaint();
                count++;
        }
    }
    
    private void PintarTablasRows(DefaultTableModel Tabla,int ID){
        try{
            Tabla.setRowCount(0);
            Object O[]= new Object[Tabla.getColumnCount()];
            ArrayList<String[]> Filler;
            String SS,TT,WW;
            if(O.length>2){
                SS="c.id_career, c.`name`, c.`description` ";
                TT="ies9021_database.career c " +
                   "inner join ies9021_database.campus_career cc ON c.id_career = cc.id_career";
                WW="cc.id_campus="+ID+";";
                Filler=SEND(SS, TT, WW);
            }else{
                SS="wk.id_word_key, word from ies9021_database.words_key wk";
                TT="inner join ies9021_database.career_word_key cwk ON wk.id_word_key = cwk.id_word_key";
                WW="where cwk.id_career="+ID+";";
                Filler=SEND(SS, TT, WW);
            }
            if(!Filler.isEmpty()){
                for (int i=0; i<Filler.size();i++ ) {
                    System.arraycopy(Filler.get(i), 0, O, 0, Tabla.getColumnCount());
                }
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

        JCBUniversity = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTCareer = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTWordKey = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JCBCampus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JCBUniversity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        JTWordKey.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTWordKey.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(JTWordKey);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        JCBCampus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JCBCampus, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(JCBUniversity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBUniversityActionPerformed
        // TODO add your handling code here:
        if(JCBUniversity.getSelectedIndex()>0){
            SetCamCB(JCBUniversity.getSelectedIndex());
        }
        else{
            JCBCampus.setModel(null);
        }
        
    }//GEN-LAST:event_JCBUniversityActionPerformed

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
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Career_Word_Key_JF1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Career_Word_Key_JF1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBCampus;
    private javax.swing.JComboBox<String> JCBUniversity;
    private javax.swing.JTable JTCareer;
    private javax.swing.JTable JTWordKey;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
