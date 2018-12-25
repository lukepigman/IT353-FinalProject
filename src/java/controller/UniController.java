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

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    private UniBean theModel;
    private String loginStatus;
    private String updateStatus;
    private String result;
    private String recover;
    private String text;
    private String subject;
    private String searchTest;
    private ArrayList<UniBean> result2;
    private ArrayList<UniBean> showcase;

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
        int status = aProfileDAO.createProfile(getTheModel()); // Doing anything with the object after this?
        if (status == 1) {
            return "UniLog.xhtml"; // navigate to "response.xhtml"
        } else {
            return "error.xhml";
        }
    }

    public String findProfile() {
        UniDAOImpl aLoginDAO = new UniDAOImpl();
        ArrayList result1 = aLoginDAO.findUserID(getTheModel().getUniID());
        setTheModel((UniBean) result1.get(0));
        if (getTheModel() != null) {
            return "UniUpdate.xhtml";
        } else {
            return "error.xhtml";
        }
    }

    public String login() {
        UniDAOImpl aLogin = new UniDAOImpl();
        ArrayList result1 = aLogin.findLogin(getTheModel().getUniID(), getTheModel().getUniPass());
        setTheModel((UniBean) result1.get(0));

        if (getTheModel() != null) {
            return "index3.xhtml";
        } else {
            setLoginStatus("Login Failed");
            return "StudentLogin.xhtml";
        }
    }

    public void updateThis() {
        UniDAOImpl aProfileDAO = new UniDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.updateProfile(getTheModel()); // Doing anything with the object after this?
        if (status != 0) {
            System.out.print(getTheModel().getMajor());
            setUpdateStatus("Record updated successfully ");
        } else {
            setUpdateStatus("Record update failed!");
        }

    }

    public void recoverPass() {
        UniDAOImpl aLoginDAO = new UniDAOImpl();
        ArrayList result1 = aLoginDAO.recoverPass(getTheModel().getUniID());
        setTheModel((UniBean) result1.get(0));
        if (getTheModel() != null) {
            sendPasswordEmail();
            setRecover("an Email will be sent to ??" //+ theModel.getEmail() 
                    + "your password is: " + getTheModel().getUniPass());
        } else {
            setRecover("error.xhtml");
        }
    }
    
    
     public String sendEmail() {

        // Recipient's email ID needs to be mentioned.
        String to = getTheModel().getUniEmail();

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
                return new PasswordAuthentication("IT353Uconnect@gmail.com", "itkstuadmin");
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
            message.setSubject(getSubject(), "text/html");

            // Send the actual HTML message, as big as you like
            message.setContent(getText(), "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return "StudentEn.xhtml";
    }
    
    public void sendPasswordEmail() {
        
        // Recipient's email ID needs to be mentioned.
        String to = getTheModel().getUniEmail();

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
       
            message.setContent("<p>Hello " + getTheModel().getUniEmail() + ", your password is: " + getTheModel().getUniPass() +"</p>" , "text.html");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    public void search()
    {
        int counter = 0;
        result = " ";
        
        UniDAOImpl aStu = new UniDAOImpl();
        //setResult1(aStu.findStudentGPA(getSearchTest()));
        ArrayList result1 = aStu.findUniversities(searchTest);
        result2 = result1;
        setTheModel(new UniBean());
        while(result1.size() > counter)
        {
            
            result += "..." + result1.get(counter).toString() + " " + counter;
            
            
            counter++;
//            getResult3().add((StudentBean)result1.get(counter));
            //getResult2().add(result1.get(counter));
            
//           for(int i =0; result1.size() > 0; i++){
//            result += "..." + getResult1().get(i).toString(); 
//        }
        }
        
        
    }
    public String grabDetails(String a){
        for (int i = 0; i < result2.size();i++){
            if(result2.get(i).getUniID().equals(a)){
                setTheModel(result2.get(i));
                return "UniDetails.xhtml";
            } 
        }
        
        
        return "error.xhtml";
    }
    public String grabShowcaseDetails(String a){
        for (int i = 0; i < showcase.size();i++){
            if(showcase.get(i).getUniID().equals(a)){
                setTheModel(showcase.get(i));
                return "UniDetails.xhtml";
            } 
        }
        
        
        return "error.xhtml";
    }
    
    public void setToShowcase(String a){
        UniDAOImpl aStu = new UniDAOImpl();
        for (int i = 0; i < showcase.size();i++){
            if(showcase.get(i).getUniID().equals(a)){
                aStu.updateShowcase(a);
                
            } 
        }
    }
     public void removeShowcase(String a){
        UniDAOImpl aStu = new UniDAOImpl();
        for (int i = 0; i < showcase.size();i++){
            if(showcase.get(i).getUniID().equals(a)){
                aStu.removeUpdateShowcase(a);
                
            } 
        }
    }
    
    public void grabShowcase()
    {
        int counter = 0;
        result = " ";
        
        UniDAOImpl aStu = new UniDAOImpl();
        //setResult1(aStu.findStudentGPA(getSearchTest()));
        ArrayList result1 = aStu.findShowcaseUniversities();
        showcase = result1;
        
        
        
        
    }
    
    public void grabAllShowcase()
    {
        int counter = 0;
        result = " ";
        
        UniDAOImpl aStu = new UniDAOImpl();
        //setResult1(aStu.findStudentGPA(getSearchTest()));
        ArrayList result1 = aStu.findAll();
        showcase = result1;
        setTheModel(new UniBean());
        
        
        
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the searchTest
     */
    public String getSearchTest() {
        return searchTest;
    }

    /**
     * @param searchTest the searchTest to set
     */
    public void setSearchTest(String searchTest) {
        this.searchTest = searchTest;
    }

    /**
     * @return the result2
     */
    public ArrayList<UniBean> getResult2() {
        return result2;
    }

    /**
     * @param result2 the result2 to set
     */
    public void setResult2(ArrayList<UniBean> result2) {
        this.result2 = result2;
    }

    /**
     * @return the showcase
     */
    public ArrayList<UniBean> getShowcase() {
        return showcase;
    }

    /**
     * @param showcase the showcase to set
     */
    public void setShowcase(ArrayList<UniBean> showcase) {
        this.showcase = showcase;
    }

}
