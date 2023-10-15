package Forms;

import Models.University;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.SEND;
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
public class Career_Word_Key_JF1 extends javax.swing.JFrame {
    /*
        RSS = ResultSetString
        RSI = ResultSetIes
        UNII = UNIversity Id
        CAMPI = CAMPus Id
        SCR = Selected Career Row
        SWKR = Selected Word Key Row
    */
    ArrayList<String[]> RSS;
    ResultSetIES9021 RSI;
    int UNII[],CAMPI[],SCR=-1,SWKR=-1;
    DefaultTableModel TablaCareer,TablaWordK;
    
    public Career_Word_Key_JF1() {
        initComponents();
        ConfigurationStart();
    }
    
    private void ConfigurationStart(){
        SetUniCB();
        PintarTablaColumns();
    }
    
    private void FullReset(){
        BTNCareerS.setEnabled(false);
        LimpiarTablas(TablaCareer);
        WordKReset();
        SCR=-1;
    }
    
    private void WordKReset(){
        BTNWordSe.setEnabled(false);
        BTNADD.setEnabled(false);
        BTNDEL.setEnabled(false);
        JCBSearchType.setSelectedIndex(0);
        LimpiarTablas(TablaWordK);
        TXTWordsearch.setText("");
        TXTWordsearchFocusLost(null);
        SWKR=-1;
    }
    
    private DefaultComboBoxModel SetComboBox(String SS,String TT,String WW,String MS){
        try{
        ObtenerDatos(SS,TT,WW);
        int DI[]=new int[RSS.size()+1];
        String JCBData[]=new String[RSS.size()+1];
        DI[0]=-1;
        JCBData[0]=MS;
        for(int i=0;i<RSS.size();i++){
            DI[i+1]=Integer.parseInt(RSS.get(i)[0]);//ID's
            JCBData[i+1]=RSS.get(i)[1];//Nombres
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
        String SS="id_university, name",
               TT="ies9021_database.university",
               WW=null,
               MS="Seleccione la Universidad";
        JCBUniversity.setModel(SetComboBox(SS, TT, WW, MS));
    }
    
    private void SetCamCB(int id_Uni){
        String SS="id_campus, name",
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
        TablaCareer= (DefaultTableModel) JTCareer.getModel();
        TablaCareer.setColumnCount(Carreras.length);
        TablaWordK= (DefaultTableModel) JTWordKey.getModel();
        TablaWordK.setColumnCount(Palabras.length);
        int count=0;
        for (String CR : Carreras) {
                JTableHeader tableHeader = JTCareer.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(CR);
                tableHeader.repaint();
                count++;
        }
        count=0;
        for (String PAL : Palabras) {
                JTableHeader tableHeader = JTWordKey.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(PAL);
                tableHeader.repaint();
                count++;
        }
    }
    
    private void PintarTablasRows(DefaultTableModel Tabla,Object id){
        String ID= String.valueOf(id);
        System.out.println("PintarTablasRows ID = "+ID);
        try{
            Tabla.setRowCount(0);
            Object O[]= new Object[Tabla.getColumnCount()];
            ArrayList<String[]> Filler;
            String SS,TT,WW;
            if(O.length>2){
                if(Integer.parseInt(ID)>0){
                    SS="c.id_career, c.`name`, c.`description` ";
                    TT="ies9021_database.career c " +
                       "inner join ies9021_database.campus_career cc ON c.id_career = cc.id_career";
                    WW="cc.id_campus="+ID+";";
                    Filler=SEND(SS, TT, WW);
                }else{
                    SS="distinct c.id_career, c.`name`, c.`description` ";
                    TT="ies9021_database.career c"
                      +"INNER JOIN ies9021_database.campus_career cc ON c.id_career = cc.id_career"
                      +"INNER JOIN ies9021_database.campus ca ON cc.id_campus = ca.id_campus";
                    WW="ca.id_university = "+UNII[JCBUniversity.getSelectedIndex()]+";";
                    Filler=SEND(SS, TT, WW);
                }
            }else{
                    SS="wk.id_word_key, word ";
                    TT="ies9021_database.words_key wk"
                     + "inner join ies9021_database.career_word_key cwk ON wk.id_word_key = cwk.id_word_key";
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
    
    private void LimpiarTablas(DefaultTableModel Tabla){
        for (int i = Tabla.getRowCount()-1; i >= 0; i--) {
            Tabla.removeRow(i);
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
        JLCareer = new javax.swing.JLabel();
        JLWord = new javax.swing.JLabel();
        JCBCampus = new javax.swing.JComboBox<>();
        BTNCareerS = new javax.swing.JButton();
        TXTWordsearch = new javax.swing.JTextField();
        BTNWordSe = new javax.swing.JButton();
        BTNDEL = new javax.swing.JButton();
        BTNADD = new javax.swing.JButton();
        JCBSearchType = new javax.swing.JComboBox<>();
        BTNMan = new javax.swing.JButton();

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
        JTWordKey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTWordKeyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTWordKey);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JLCareer.setText("Carreras");

        JLWord.setText("Words Key");

        JCBCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBCampusActionPerformed(evt);
            }
        });

        BTNCareerS.setText("Seleccionar");
        BTNCareerS.setEnabled(false);
        BTNCareerS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCareerSActionPerformed(evt);
            }
        });

        TXTWordsearch.setText("Palabra Clave");
        TXTWordsearch.setEnabled(false);
        TXTWordsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTWordsearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTWordsearchFocusLost(evt);
            }
        });

        BTNWordSe.setText("Buscar");
        BTNWordSe.setEnabled(false);

        BTNDEL.setText("Borrar");
        BTNDEL.setEnabled(false);

        BTNADD.setText("Añadir");
        BTNADD.setEnabled(false);

        JCBSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empieza con", "Entre", "Termina con" }));

        BTNMan.setText("?");

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
                        .addComponent(JLWord)
                        .addGap(18, 18, 18)
                        .addComponent(TXTWordsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCBSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTNWordSe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(BTNADD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTNDEL))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLCareer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTNCareerS)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JCBUniversity, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCBCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNMan)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JCBCampus, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(JCBUniversity))
                    .addComponent(BTNMan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLCareer)
                    .addComponent(BTNCareerS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLWord)
                    .addComponent(TXTWordsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNWordSe)
                    .addComponent(BTNDEL)
                    .addComponent(BTNADD)
                    .addComponent(JCBSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JCBUniversityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBUniversityActionPerformed
        // TODO add your handling code here:
        if(JCBUniversity.getSelectedIndex()>0){
            SetCamCB(UNII[JCBUniversity.getSelectedIndex()]);
        }
        else{
            JCBCampus.setModel(null);
        }
        FullReset();
    }//GEN-LAST:event_JCBUniversityActionPerformed

    private void JCBCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBCampusActionPerformed
        // TODO add your handling code here:
        if(JCBCampus.getSelectedIndex()>0){
            PintarTablasRows(TablaCareer, CAMPI[JCBCampus.getSelectedIndex()]);
        }else if(JCBCampus.getSelectedIndex()==0){
            PintarTablasRows(TablaCareer, -1);
        }
        FullReset();
    }//GEN-LAST:event_JCBCampusActionPerformed

    private void JTCareerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTCareerMouseClicked
        // TODO add your handling code here:
        if(JTCareer.getSelectedRow()!=SCR&&JTWordKey.getRowCount()<=0){
            BTNCareerS.setEnabled(true);
            SCR=JTCareer.getSelectedRow();
        }else{
            BTNCareerS.setEnabled(true);
            SCR=JTCareer.getSelectedRow();
            WordKReset();
        }
    }//GEN-LAST:event_JTCareerMouseClicked

    private void TXTWordsearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTWordsearchFocusGained
        // TODO add your handling code here:
        if(TXTWordsearch.getForeground().equals(Color.lightGray)){
            TXTWordsearch.setForeground(Color.black);
            TXTWordsearch.setText("");
        }
    }//GEN-LAST:event_TXTWordsearchFocusGained

    private void TXTWordsearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTWordsearchFocusLost
        // TODO add your handling code here:
        if(TXTWordsearch.getText().isBlank()||TXTWordsearch.getText().isEmpty()){
            TXTWordsearch.setForeground(Color.lightGray);
            TXTWordsearch.setText("Palabra Clave");
        }
    }//GEN-LAST:event_TXTWordsearchFocusLost

    private void BTNCareerSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCareerSActionPerformed
        // TODO add your handling code here:
        PintarTablasRows(TablaWordK, TablaCareer.getValueAt(JTCareer.getSelectedRow(), 0));
        TXTWordsearch.setEnabled(true);
        BTNWordSe.setEnabled(true);
    }//GEN-LAST:event_BTNCareerSActionPerformed

    private void JTWordKeyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTWordKeyMouseClicked
        // TODO add your handling code here:
        if(JTWordKey.getSelectedRow()!=SWKR){
            BTNADD.setEnabled(true);
            BTNDEL.setEnabled(true);
        }
    }//GEN-LAST:event_JTWordKeyMouseClicked

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
    private javax.swing.JButton BTNADD;
    private javax.swing.JButton BTNCareerS;
    private javax.swing.JButton BTNDEL;
    private javax.swing.JButton BTNMan;
    private javax.swing.JButton BTNWordSe;
    private javax.swing.JComboBox<String> JCBCampus;
    private javax.swing.JComboBox<String> JCBSearchType;
    private javax.swing.JComboBox<String> JCBUniversity;
    private javax.swing.JLabel JLCareer;
    private javax.swing.JLabel JLWord;
    private javax.swing.JTable JTCareer;
    private javax.swing.JTable JTWordKey;
    private javax.swing.JTextField TXTWordsearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
