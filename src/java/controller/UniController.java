/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.StudentDAOImpl;
import DAO.UniDAOImpl;
import java.util.ArrayList;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.UniBean;

/**
 *
 * @author awturne
 */
@ManagedBean
@SessionScoped
public class UniController {

    private UniBean theModel;
    private String loginStatus;
    private String updateStatus;
    private String result;
    private String recover;

    public UniController() {
        theModel = new UniBean();
    }

    /**
     * @return the theModel
     */
    public UniBean getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(UniBean theModel) {
        this.theModel = theModel;
    }

    /**
     * @return the loginStatus
     */
    public String getLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatus the loginStatus to set
     */
    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * @return the updateStatus
     */
    public String getUpdateStatus() {
        return updateStatus;
    }
    
    public String getRecover(){
        return recover;
    }
    
    public void setRecover(String recover){
        this.recover = recover;
    }

    /**
     * @param updateStatus the updateStatus to set
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String createLogin() {
        UniDAOImpl aProfileDAO = new UniDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.createProfile(theModel); // Doing anything with the object after this?
        if (status == 1) {
            return "UniLog.xhtml"; // navigate to "response.xhtml"
        } else {
            return "error.xhml";
        }
    }

    public String findProfile() {
        UniDAOImpl aLoginDAO = new UniDAOImpl();
        ArrayList result = aLoginDAO.findUserID(getTheModel().getUniID());
        setTheModel((UniBean) result.get(0));
        if (getTheModel() != null) {
            return "UniUpdate.xhtml";
        } else {
            return "error.xhtml";
        }
    }

    public String login() {
        UniDAOImpl aLogin = new UniDAOImpl();
        ArrayList result = aLogin.findLogin(theModel.getUniID(), theModel.getUniPass());
        setTheModel((UniBean) result.get(0));
//        theModel.setUniID(result.get(0).toString());
        if (theModel != null) {
            return "UniLog.xhtml";
        } else {
            setLoginStatus("Login Failed");
            return "StudentLogin.xhtml";
        }
    }

    public void updateThis() {
        UniDAOImpl aProfileDAO = new UniDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.updateProfile(theModel); // Doing anything with the object after this?
        if (status != 0) {
            System.out.print(theModel.getMajor());
            setUpdateStatus("Record updated successfully ");
        } else {
            setUpdateStatus("Record update failed!");
        }

    }

    public void recoverPass() {
        UniDAOImpl aLoginDAO = new UniDAOImpl();
        ArrayList result = aLoginDAO.recoverPass(theModel.getUniID());
        setTheModel((UniBean) result.get(0));
        if (getTheModel() != null) {
            sendPasswordEmail();
            setRecover("an Email will be sent to ??" //+ theModel.getEmail() 
                    + "your password is: " + theModel.getUniPass());
        } else {
            setRecover("error.xhtml");
        }
    }
    
    public void sendPasswordEmail() {
        
        // Recipient's email ID needs to be mentioned.
        String to = theModel.getUniEmail();

        // Sender's email ID needs to be mentioned
        String from = "IT353Uconnect@gmail.com";
        
        // Assuming you are sending email from this host
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("IT353Uconnect@gmail.com" , "itkstuadmin");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Password");

            // Send the actual HTML message, as big as you like
       
            message.setContent("<p>Hello " + theModel.getUniEmail() + ", your password is: " + theModel.getUniPass() +"</p>" , "text.html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
