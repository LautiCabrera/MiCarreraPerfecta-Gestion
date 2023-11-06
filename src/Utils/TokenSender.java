/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import static Utils.DDBBConnection.QueryVerification;
import static Utils.DDBBConnection.SendQuery;
import static Utils.JsonDataFetcher.SEND;
import static Utils.JsonDataFetcher.selectQuery;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;

/**
 *
 * @author BTF
 */
public abstract class TokenSender {
    
    //SendMessage Pertenece a InicioJF
    public static void SendMessage(String Destinatario,String User){
        
        //final String username = "FreeByPass163@gmail.com";
        //final String password = "gvyh kuab cqjp tnyd"; 
        
        try {
            boolean Send=false;
        //String query="SELECT * FROM ies9021_database.email_sender WHERE mail LIKE 'F%';";
        String TT="ies9021_database.email_sender", WW=" mail LIKE 'F%';";
        ArrayList<String[]> RSI=new ArrayList<>(selectQuery("*",TT,WW));
        if(!RSI.isEmpty()){
            //RSI.getDatos();
            Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", RSI.get(0)[4]);//"host"
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", RSI.get(0)[3]);//"port"
            propiedad.setProperty("mail.smtp.auth", "true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        
        //ResultSet RS=SendQuery(query);
        //
        //
        String correoEnvia = String.valueOf(RSI.get(0)[1]);//username;
        //String PSW="gvyh kuab cqjp tnyd";
        String contrasena = PPassword(RSI.get(0)[2]);//password;
        String destinatario = Destinatario;
        String asunto = "TOKEN Programacion 2";
        String Token=TokenGen();
        String mensaje = "Su token es: "+Token;
            //System.out.println(contrasena+" CON");
            //System.out.println(Token+" tok");
            if(SendTokenDB(Token,User)){
                MimeMessage mail = new MimeMessage(sesion);
             
                mail.setFrom(new InternetAddress (correoEnvia));
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                mail.setSubject(asunto);
                mail.setText(mensaje);
            
                Transport transporte = sesion.getTransport("smtp");
                transporte.connect(correoEnvia,contrasena);
                transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                transporte.close();
                Send=true;
            }
        }
         
            if(Send){JOptionPane.showMessageDialog(null, "Correo enviado");
            }else{JOptionPane.showMessageDialog(null, "No enviado, intente más tarde");}
            
            
        } catch (AddressException ex) {
            Logger.getLogger(General_Methods.class.getName()).log(Level.SEVERE, "Error in: SendMessage", ex);
        } catch (HeadlessException | MessagingException ex) {
            Logger.getLogger(General_Methods.class.getName()).log(Level.SEVERE, "Error in: SendMessage", ex);
        } 
        
        
    }
    
    
    private static String TokenGen(){
        String Token="";
        for(int i=0;i<6;i++){
            if(i%2==0){Token=Token+String.valueOf((int)(Math.random()*9));
            }else{Token=Token+String.valueOf((char)((int)(Math.random()*5)+97));
            }
        }
        
        return Token;
    }
    
    private static boolean SendTokenDB(String Token,String User){
        try{
        String Query= "UPDATE ies9021_database.users SET Last_token = '"+
         Token+"', f_token = current_time() WHERE id_user = '"+User+"';";
            System.out.println(Query);
        //ResultSetIES9021 RS=DDBBConnection.SendQuery(Query);
        if(QueryVerification(Query)){
        SendQuery(Query);
        return true;
        }else{
            return false;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    private static String PPassword(String PASS){
        String newPass="";
            for(int i=PASS.length()-1;i>=0;i--){
                newPass=newPass+PASS.charAt(i);
            }
        return newPass;
    }
}
