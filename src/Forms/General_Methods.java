package Forms;

import java.awt.HeadlessException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import Utils.DDBBConnection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class General_Methods {
    
    //String Columns[];
    
    private int User;

    public General_Methods() {
    }
    
    //Inicio Pertenece a InicioJF 
    public boolean Inicio(String Email, String Password, InicioJF Ob) {
        try {
            DDBBConnection Conect = new DDBBConnection();
            ResultSet result = Conect.SendAndRecibe("select password, id_user from `ies9021_database`.users where email='" + Email.toLowerCase() + "';");
            if(result.next()){
                if(result.getString(1).equals(Password)){
                    JOptionPane.showMessageDialog(Ob,"Logiando");
                    User=result.getInt(2);
                    return true;
                }else{
                    JOptionPane.showMessageDialog(Ob, "Contraseña Incorrecta");
                }
            }else{
                JOptionPane.showMessageDialog(Ob, "Email no registrado");
            }
            return false;
            

        } catch (HeadlessException | SQLException e) {
            System.out.println("Fail detected\n");
            System.out.println(e.getMessage());
        }
        return false;
    }
    //Existe Pertenece a InicioJF
    public boolean Existe(String Email){
        try{
        DDBBConnection Conect = new DDBBConnection();
        String Query= "SELECT email FROM `ies9021_database`.users WHERE email='"+Email.toLowerCase()+"';";
        ResultSet result=Conect.SendAndRecibe(Query);
            boolean ret=false;
            if(result.next()){ret=true;}
            result.close();
            return ret;
            
        } catch (SQLException e) {
            System.out.println("Error en: IJFM\nError:"+e.getMessage());
        }
        
        return false;
    }
    //SendMessage Pertenece a InicioJF
    public void SendMessage(String Destinatario){
        
        //final String username = "FreeByPass163@gmail.com";
        //final String password = "gvyh kuab cqjp tnyd"; 

        try {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        String Query = "SELECT * FROM ies9021_database.email_sender;";
        //ResultSet RS=Conect.SendAndRecibe(Query);
        //
        //
        String correoEnvia = "FreeByPass163@gmail.com";//username;
        String PSW="gvyh kuab cqjp tnyd";
        String contrasena = PSW;//password;
        String destinatario = Destinatario;
        String asunto = "RECUPERACION DE CONTRASEÑA";
        String mensaje = "Su contraseña es: ";
        
        
        MimeMessage mail = new MimeMessage(sesion);
        
        
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia,contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
            
            
        } catch (AddressException ex) {
            Logger.getLogger(General_Methods.class.getName()).log(Level.SEVERE, "Error in: SendMessage", ex);
        } catch (MessagingException ex) {
            Logger.getLogger(General_Methods.class.getName()).log(Level.SEVERE, "Error in: SendMessage", ex);
        } 
        
        
    }
    /*PintarTabla se conecta a la base de Datos para obtener el nombre de las columnas
        de la base de datos y colocarlos en la tabla que tengamos en nuestro JTable
    */
    public String[] Columns(String Table){
        try {
            DatabaseMetaData metaData=DDBBConnection.Conectar().getMetaData();
            ResultSet Columns =metaData.getColumns(null,null,Table,null);
            ArrayList<String> ColumnsNames=new ArrayList<String>();
            while(Columns.next()){
                if(!UnWantedColumnsNames(Columns.getString("COLUMN_NAME"))){
                    ColumnsNames.add(Columns.getString("COLUMN_NAME"));
                }
            }
            Columns.close();
            String Colum[] = new String[ColumnsNames.size()];
            int count=0;
            for (String CN : ColumnsNames) {
                Colum[count]=CN;
                count++;
            }
            return Colum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void PintarTabla(String Table, JTable jTable){
        DefaultTableModel Tabla= (DefaultTableModel) jTable.getModel();
        try {
            DatabaseMetaData metaData=DDBBConnection.Conectar().getMetaData();
            ResultSet Columns =metaData.getColumns(null,null,Table,null);
            ArrayList<String> ColumnsNames=new ArrayList<String>();
            while(Columns.next()){
                if(!UnWantedColumnsNames(Columns.getString("COLUMN_NAME"))){
                    ColumnsNames.add(Columns.getString("COLUMN_NAME"));
                }
            }
            Columns.close();
            Tabla.setColumnCount(ColumnsNames.size());
            int count=0;
            for (String CN : ColumnsNames) {
                JTableHeader tableHeader = jTable.getTableHeader();
                TableColumnModel tableColumnModel = tableHeader.getColumnModel();
                TableColumn tableColumn = tableColumnModel.getColumn(count);
                tableColumn.setHeaderValue(CN);
                tableHeader.repaint();
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*Existen nombres por defecto que se agregan a nuestro JTable
    Si encuentran alguno que no este en la lista, por favor agregarlo*/
    private boolean UnWantedColumnsNames(String Name){
        String[] unwantedColumnNames = {
            "USER", "CURRENT_CONNECTIONS", "TOTAL_CONNECTIONS",
            "MAX_SESSION_CONTROLLED_MEMORY", "MAX_SESSION_TOTAL_MEMORY"
        };
        for (String unwantedName : unwantedColumnNames) {
            if (unwantedName.equalsIgnoreCase(Name)) {
                return true;
            }
        }
        return false;
    }
    
    public void PintarComboBox(String Table, JComboBox jCombo){
        try {
            DatabaseMetaData metaData=DDBBConnection.Conectar().getMetaData();
            ResultSet Columns =metaData.getColumns(null,null,Table,null);
            ArrayList<String> ColumnsNames=new ArrayList<String>();
            while(Columns.next()){
                if(!UnWantedColumnsNames(Columns.getString("COLUMN_NAME"))){
                    ColumnsNames.add(Columns.getString("COLUMN_NAME"));
                }
            }
            Columns.close();
            String Colum[] = new String[ColumnsNames.size()];
            int count=0;
            for (String CN : ColumnsNames) {
                Colum[count]=CN;
                count++;
            }
            //return Colum
            jCombo.setModel(new DefaultComboBoxModel<>(Colum));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void CallAdd(String Tabla,boolean Mode,Object[] Datos){ //Mode True=Add False=Modify
        Object X;
        switch (Tabla.toLowerCase()) {
            case "career_word_key":
                X = new Career_Word_Key_JF(Mode,Datos,User);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Nothing Here Boy");
                break;
        }
    }
    
    public Object[] IDK(int Cantidad,JTable GIJF){
        Object Fila[]=new Object[Cantidad];
        System.out.println("Cant"+Cantidad);
            for(int i=0;i<Cantidad;i++){
                System.out.println(i+" asdasd");
                System.out.println(GIJF.getValueAt(GIJF.getSelectedRow(), i).toString()+" "+GIJF.getSelectedRow()+"  "+ i);
                
            Fila[i]=GIJF.getValueAt(GIJF.getSelectedRow(), i);
            if(Fila[i]==null){Fila[i]="";}
                System.out.println(Fila[i].toString());
            }
        return Fila;
    }
    
}




