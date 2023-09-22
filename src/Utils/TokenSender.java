/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import static Utils.DDBBConnection.SendQuery;
import java.sql.SQLException;
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
    public static void SendMessage(String Destinatario){
        
        //final String username = "FreeByPass163@gmail.com";
        //final String password = "gvyh kuab cqjp tnyd"; 
        
        try {
            boolean Send=false;
        String query="SELECT * FROM ies9021_database.email_sender WHERE mail LIKE 'F%';";
        ResultSetIES9021 RSI=SendQuery(query);
        if(RSI.State){
            RSI.RS.next();
            Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", RSI.RS.getString("host"));
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", RSI.RS.getString("port"));
            propiedad.setProperty("mail.smtp.auth", "true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        
        //ResultSet RS=SendQuery(query);
        //
        //
        String correoEnvia = RSI.RS.getString("mail");//username;
        //String PSW="gvyh kuab cqjp tnyd";
        String contrasena = PPassword(RSI.RS.getString(3));//password;
        String destinatario = Destinatario;
        String asunto = "TOKEN Programacion 2";
        String Token=TokenGen();
        String mensaje = "Su token es: "+Token;
            System.out.println(contrasena+" CON");
            System.out.println(Token+"tok");
            if(SendTokenDB(Token,Destinatario)){
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
        } catch (MessagingException | SQLException ex) {
            Logger.getLogger(General_Methods.class.getName()).log(Level.SEVERE, "Error in: SendMessage", ex);
        } 
        
        
    }
    
    
    private static String TokenGen(){
        String Token="";
        for(int i=0;i<6;i++){
            if(i%2==0){Token=Token+String.valueOf(Math.random()*9);
            }else{Token=Token+String.valueOf((char)((Math.random()*5)+97));
            }
        }
        
        return Token;
    }
    
    private static boolean SendTokenDB(String Token,String Email){
        try{
        String Query= "UPDATE `ies9021_database`.`users` SET `Last_token` = '"+
         Token+"', `f_token` = current_time() WHERE email='"+Email.toLowerCase()+"';";
        ResultSetIES9021 RS=DDBBConnection.SendQuery(Query);
        return RS.State;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    private static String PPassword(String PASS){
        String newPass="";
            for(int i=PASS.length();i>=0;i++){
                newPass=newPass+PASS.charAt(i);
            }
        return null;
    }
}
