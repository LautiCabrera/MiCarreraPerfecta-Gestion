/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.HeadlessException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
//import static Utils.DDBBConnection.*;
import Forms.InicioJF;
import Forms.Career_Word_Key_JF;
import static Utils.DDBBConnection.SendQuery;
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
    private String Nombre;

    public General_Methods() {
    }
    
    //Inicio Pertenece a InicioJF 
    public boolean Inicio(String Email, String TokenI, InicioJF Ob) {
        try {
            String Query=("select id_user, `name`, Last_token='"+TokenI+"', ((select minute_token from ies9021_database.settings ) < timestampdiff(minute,f_token,current_time())) from ies9021_database.users where email='"+Email+"' Limit 5;");
            ResultSetIES9021 result = SendQuery("select Last_token, id_user from `ies9021_database`.users where email='" + Email.toLowerCase() + "';");
            if(result.State){
                if(result.RS.next()){
                    if(result.RS.getInt(4)==1){
                        if(result.RS.getInt(3)==1){
                            JOptionPane.showMessageDialog(Ob,"Logiando");
                            User=result.RS.getInt(1);
                            Nombre=result.RS.getNString(2);
                            return true;
                        }else{
                            JOptionPane.showMessageDialog(Ob, "Token Incorrecto");
                        }
                    }else{
                        JOptionPane.showMessageDialog(Ob, "Token Vencido");
                    }
                }else{
                    JOptionPane.showMessageDialog(Ob, "Email Incorrecto");
                }
            }
            return false;
            

        } catch (HeadlessException | SQLException e) {
            System.out.println("Fail detected\n");
            System.out.println(e.getMessage());
        }
        return false;
    }
    //Existe Pertenece a InicioJF
    public int Existe(String Email){
        try{
        String Query= "SELECT enabled_state FROM `ies9021_database`.users WHERE email='"+Email.toLowerCase()+"';";
        ResultSetIES9021 RS21= DDBBConnection.SendQuery(Query);
        ResultSet result=RS21.RS;
            System.out.println("Alfa");
            int ret=-1;
            if(result.next()){
                System.out.println("Beta");
                ret+=1+result.getInt(1);}
            return ret;
        } catch (SQLException e) {
            System.out.println("Error en: GEM.Existe\nError:"+e.getMessage());
        }
        
        return -1;
    }
    
    /*PintarTabla se conecta a la base de Datos para obtener el nombre de las columnas
        de la base de datos y colocarlos en la tabla que tengamos en nuestro JTable
    */
    public String[] Columns(String Table){
        try {
            DatabaseMetaData metaData=DDBBConnection.Conectar().getMetaData();
            ResultSet Columns =metaData.getColumns(null,null,Table,null);
            ArrayList<String> ColumnsNames=new ArrayList();
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
    
    public String[] Paginas(int Paginador,String Tabla){
        String query="SELECT COUNT(*) FROM "+Tabla+";";
        ResultSetIES9021 RSI=SendQuery(query);
        if(RSI.State){
            try {
                RSI.RS.next();
                int regs=RSI.RS.getInt(1);
                String[] Pags;
                if(regs%Paginador==0&&regs!=0){
                    Pags= new String[regs/Paginador];
                    for(int i=0;i<Pags.length;i++){
                        Pags[i]=(i+1)+"";
                    }
                    return Pags;
                }else{
                    Pags= new String[(regs/Paginador)+1];
                    for(int i=0;i<Pags.length;i++){
                        Pags[i]=(i+1)+"";
                    }
                    return Pags;
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    public void CallAdd(String Tabla,boolean Mode,Object[] Datos){ //Mode True=Add False=Modify
        Object X;
        switch (Tabla.toLowerCase()) {
            case "career_word_key":
                X= new Career_Word_Key_JF(Mode,Datos,User);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Nothing Here Boy");
                break;
        }
    }
    
    public Object[] DatosAModificar(int Cantidad,JTable GIJF){
        Object Fila[]=new Object[Cantidad];
            for(int i=0;i<Cantidad;i++){
                System.out.println(GIJF.getValueAt(GIJF.getSelectedRow(), i).toString()+" "+GIJF.getSelectedRow()+"  "+ i);
                Fila[i]=GIJF.getValueAt(GIJF.getSelectedRow(), i);
                if(Fila[i]==null){Fila[i]="";}
                System.out.println(Fila[i].toString());
            }
        return Fila;
    }
    
    public boolean Deleteable(String Tabla){
        switch(Tabla.toLowerCase()){
            //Aqui ingresamos las clases que pueden ser borrables
            case "":
                return true;
            default:
                JOptionPane.showMessageDialog(null, Tabla+" No puede ser borrado desde este sector");
                return false;
        }
    }
    
    public void DELETING(String Tabla,JTable GIJF){
        switch(Tabla.toLowerCase()){
            //Aqui ingresen sus metodos para borrar
            case "":
                break;
            default:
                JOptionPane.showMessageDialog(null, Tabla+" No puede ser borrado desde este sector");
                break;
        }
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
        
        String Query="SELECT * FROM ies9021_database.email_sender;";
        //ResultSet RS=Conect.SendAndRecibe(Query);
        //
        //
        String correoEnvia = "FreeByPass163@gmail.com";//username;
        String PSW="gvyh kuab cqjp tnyd";
        String contrasena = PSW;//password;
        String destinatario = Destinatario;
        String asunto = "TOKEN Programacion 2";
        String Token=TokenGen();
        String mensaje = "Su token es: "+Token;
        
        SendTokenDB(Token,Destinatario);
        
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
    
    
    private String TokenGen(){
        String Token="";
        for(int i=0;i<6;i++){
            if(i%2==0){Token=Token+String.valueOf(Math.random()*9);
            }else{Token=Token+String.valueOf((char)((Math.random()*5)+97));
            }
        }
        
        return Token;
    }
    
    private void SendTokenDB(String Token,String Email){
        try{
        String Query= "UPDATE `ies9021_database`.`users` SET `Last_token` = '"+
         Token+"', `f_token` = current_time() WHERE email='"+Email.toLowerCase()+"';";
        ResultSetIES9021 RS=DDBBConnection.SendQuery(Query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}




