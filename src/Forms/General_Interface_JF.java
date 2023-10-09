/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.General_Methods;
import static Utils.DDBBConnection.SendQuery;
import Utils.JsonDataFetcher;
import Utils.ResultSetIES9021;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author BTF
 */
public class General_Interface_JF extends javax.swing.JFrame {
    
    
    /**
     * Creates new form Carrer_Word_Key_JF
     */
    
    General_Methods GEM;
    String Columns[],ClassName;
    DefaultTableModel Tabla;
    int SelectedRow=-1;
    Object ClaseEnUso;
    ResultSetIES9021 RSI;
    Class Clazz;
    Method Methods[];
    
    public General_Interface_JF() {
        initComponents();
        ConfigurationStart();
    }
    
    public General_Interface_JF(String Modo,General_Methods GEM,Object Clase) {
        initComponents();
        this.GEM=GEM;
        ClaseEnUso=Clase;
        ConfigurationStart(Modo);
    }
    
    private void ConfigurationStart(){
        GEM=new General_Methods();
        ConfigurationStart("");
        Refresh();
    }
    
    private void ConfigurationStart(String Modo){
        Columns=GEM.Columns(Modo);
        System.out.println(Modo+" "+Columns.length);
        SetMethods();
        PintarTablaColumns();
        PintarComboBox();
        LABTittle.setText(Modo.toUpperCase());
        jTable1.setSelectionMode(0);//2 Varios rangos; 1 Un rango; 0 Una fila
        SetDataGetter();
        Refresh();
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
    
    private void PintarTablaRows(int Desde){
        try{
        Tabla.setRowCount(0);
        Object O[]= new Object[Columns.length];
//            System.out.println("Columns"+Columns.length);
//            for(String COL : Columns){System.out.println("get"+COL);}
//        Method[] Metodos=ClaseEnUso.getClass().getMethods();
        int Limite=RSI.getDatos().size()-Desde;
        if(Limite>=Integer.parseInt(JCBPaginador.getSelectedItem().toString())){Limite=Integer.parseInt(JCBPaginador.getSelectedItem().toString());}
        if(!RSI.getDatos().isEmpty()){
                for(int j=0;j<Limite;j++){
                    int i=0;
                    //for (int a=0;a<Metodos.length;a++){System.out.println("Metodo nombre "+Metodos[a].getName());}
                    for(Method Metodo: Methods){
                            try{
                                System.out.println("Metodo name "+Metodo.getName());
                                System.out.println("Value "+Metodo.invoke(RSI.getDatos().get(j+Desde)));
                                O[i]=Metodo.invoke(RSI.getDatos().get(j+Desde));
                                i++;
                            }catch(IllegalAccessException | InvocationTargetException e){
                                e.printStackTrace();
                            }
                    }
                    Tabla.addRow(O);
                }
            //O=null;
        }
        }catch(NumberFormatException | SecurityException e){
            e.printStackTrace();
        }
    }
    
    private void SetMethods(){
        Methods= new Method[Columns.length];
        int i=0;
        for(String COL : Columns){
            for(Method Metodo: ClaseEnUso.getClass().getMethods()){
                if(("get"+COL).equals(Metodo.getName().toLowerCase())){
                    Methods[i]=Metodo;
                    i++;
                }
            }
        }
    }
    
    private boolean isGetter(Method Metodo) {
        String MName= Metodo.getName();
        return MName.startsWith("get") && MName.length()>3 && Metodo.getParameterCount()==0;
    }
    
    private void Refresh(){
        jTable1.clearSelection();
        ObtenerDatos();
        PageReset();
        ResetButtons();
    }
    
    private void Pagina(int Pag, int Paginador){
        jTable1.clearSelection();
//        ResultSetIES9021 RS=SendQuery("select * from `ies9021_database`."+
//                LABTittle.getText().toLowerCase()+" LIMIT "+Paginador+
//                " OFFSET "+(Pag*Paginador));
        PintarTablaRows(Pag*Paginador);
        ResetButtons();
    }
    
    public String[] SetPaginas(int Paginador, int CantidadDatos){
        String[] Pags;
                if(CantidadDatos%Paginador==0&&CantidadDatos!=0){
                    Pags= new String[CantidadDatos/Paginador];
                    for(int i=0;i<Pags.length;i++){
                        Pags[i]=(i+1)+"";
                    }
                    return Pags;
                }else{
                    Pags= new String[(CantidadDatos/Paginador)+1];
                    for(int i=0;i<Pags.length;i++){
                        Pags[i]=(i+1)+"";
                    }
                    return Pags;
                }
    }
    
    private void PageReset(){
        int Paginador=20;
        System.out.println("Paginador "+Integer.valueOf(String.valueOf(JCBPaginador.getSelectedItem())));
        JCBPagina.setModel(new DefaultComboBoxModel<>(SetPaginas(Paginador,RSI.getDatos().size())));
        int Pag=JCBPagina.getSelectedIndex();
        Pagina(Pag,Paginador);
    }
    
    private <T> void SetDataGetter(){
        ClassName="ies9021_database."+ClaseEnUso.getClass().getName().substring(7).toLowerCase();
        Clazz= (Class<T>)ClaseEnUso.getClass();
        System.out.println(ClassName+" ClassName");
        RSI= new JsonDataFetcher<T>().fetchTableData(ClassName,Clazz);
        //Paginador
    }
    
    private <T> void ObtenerDatos(){
        RSI= new JsonDataFetcher<T>().fetchTableData(ClassName,Clazz);
        //Paginador
    }
    
    private <T> void ObtenerDatos(String Where){
        RSI= new JsonDataFetcher<T>().fetchTableData(ClassName,Where,Clazz);
        //Paginador
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
        JCBPaginador = new javax.swing.JComboBox<>();
        JCBPagina = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("BNLarge");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        BTNSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSearchActionPerformed(evt);
            }
        });

        TXTSearch.setForeground(Color.lightGray);
        TXTSearch.setText("Buscar");
        TXTSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TXTSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TXTSearchFocusLost(evt);
            }
        });
        TXTSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTSearchKeyTyped(evt);
            }
        });

        JCBPaginador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "20", "50", "100", "150", "200" }));
        JCBPaginador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBPaginadorActionPerformed(evt);
            }
        });

        JCBPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        JCBPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBPaginaActionPerformed(evt);
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JCBPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JCBPaginador, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(JCBPaginador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCBPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNRefreshActionPerformed
        // TODO add your handling code here:
        Refresh();
        PageReset();
    }//GEN-LAST:event_BTNRefreshActionPerformed

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
            if(GEM.Deleteable(LABTittle.getText())){
            BTNAdd.setText("Aceptar");
            BTNModify.setText("Cancelar");
            }
            BTNDelete.setEnabled(false);
        }else{
            ResetButtons();
        }
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModifyActionPerformed
        // TODO add your handling code here:
        if(BTNModify.getText().equals("Modificar")){
            Object Fila[]=GEM.DatosAModificar(Columns.length, jTable1);
            GEM.CallAdd(LABTittle.getText(),false,Fila);
            //Insertar La misma tabla de Añadir pero con funcionalidad de botones distinta
        }else{
            jTable1.clearSelection();
            ResetButtons();
        }
        
    }//GEN-LAST:event_BTNModifyActionPerformed

    private void BTNAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddActionPerformed
        // TODO add your handling code here:
        if(BTNAdd.getText().equals("Añadir")){
        GEM.CallAdd(LABTittle.getText(),true,null);
        }else{
            GEM.DELETING(LABTittle.getText(),jTable1);
            jTable1.clearSelection();
            ResetButtons();
        }
        
    }//GEN-LAST:event_BTNAddActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Tablon_JF TJF=new Tablon_JF(GEM);
        TJF.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void BTNSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSearchActionPerformed
        // TODO add your handling code here:
        String Where=CBSelector.getItemAt(CBSelector.getSelectedIndex())+"= '"+TXTSearch.getText()+"'";
        ObtenerDatos(Where);
        PageReset();
        //PintarTablaRows(0);
        ResetButtons();
    }//GEN-LAST:event_BTNSearchActionPerformed

    private void TXTSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTSearchFocusGained
        // TODO add your handling code here:
        if(TXTSearch.getForeground().equals(Color.lightGray)){
            TXTSearch.setForeground(Color.BLACK);
            TXTSearch.setText("");
        }
    }//GEN-LAST:event_TXTSearchFocusGained

    private void TXTSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TXTSearchFocusLost
        // TODO add your handling code here:
        if(TXTSearch.getText().isEmpty()){
            TXTSearch.setForeground(Color.lightGray);
            TXTSearch.setText("Buscar");
        }
    }//GEN-LAST:event_TXTSearchFocusLost

    private void JCBPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBPaginaActionPerformed
        // TODO add your handling code here:
        int Paginador=Integer.getInteger(String.valueOf(JCBPaginador.getSelectedItem()));
        int Pag=JCBPagina.getSelectedIndex();
        Pagina(Pag,Paginador);
    }//GEN-LAST:event_JCBPaginaActionPerformed

    private void JCBPaginadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBPaginadorActionPerformed
        // TODO add your handling code here:
        PageReset();
    }//GEN-LAST:event_JCBPaginadorActionPerformed

    private void TXTSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTSearchKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(TXTSearch.getText().length()>=100){
            evt.consume();
        }else {
            if(!(Character.isLetterOrDigit(c) || c == 8 || c == 127 
                    || c == '\n' || c == '\t' || c == 44 || c == 46)){
                evt.consume();
            }
        }
    }//GEN-LAST:event_TXTSearchKeyTyped

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(General_Interface_JF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
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
    private javax.swing.JComboBox<String> CBSelector;
    private javax.swing.JComboBox<String> JCBPagina;
    private javax.swing.JComboBox<String> JCBPaginador;
    private javax.swing.JLabel LABTittle;
    private javax.swing.JTextField TXTSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    
}
