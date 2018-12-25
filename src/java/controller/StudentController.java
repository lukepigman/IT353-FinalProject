/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.StudentDAOImpl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.StudentBean;

/**
 *
 * @author awturne
 */
@ManagedBean
@SessionScoped
public class StudentController {

    private StudentBean theModel;
    private String loginStatus;
    private String updateStatus;
    private String searchStatus;
    private String result;
    private String searchTest;
    private String text;
    private String subject;
    private String recover;
    private Date date2;
    private ArrayList<StudentBean> result2;
    private List<StudentBean> result3;
    private boolean logged = false;

    public StudentController() {
        theModel = new StudentBean();
    }

    /**
     * @return the theModel
     */
    public StudentBean getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(StudentBean theModel) {
        this.theModel = theModel;
    }

    public String getResult() {
        return result;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setResult(String result) {
        this.result = result;
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

    /**
     * @param updateStatus the updateStatus to set
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    /**
     * @return the searchStatus
     */
    public String getSearchStatus() {
        return searchStatus;
    }

    /**
     * @param searchStatus the searchStatus to set
     */
    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String createLogin() {
        StudentDAOImpl aProfileDAO = new StudentDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.createProfile(theModel); // Doing anything with the object after this?
        if (status == 1) {
            return "StudentEn.xhtml";
        } else {
            return "index.xhtml";
        }
    }

    public String login() {
        StudentDAOImpl aLoginDAO = new StudentDAOImpl();    // Creating a new object each time.
        ArrayList result = aLoginDAO.findLogin(theModel.getUserID(), theModel.getPassword());
        setTheModel((StudentBean) result.get(0));
        if (theModel != null) {
            if(theModel.getUserID().equals("admin") && theModel.getPassword().equals("admin")){
                return "admin.xhtml";
            }
            logged = true;
            return "index2.xhtml";
            
        } else {
            setLoginStatus("Login Failed");
            return "StudentLogin.xhtml";
        }
    }

    public String findProfile() {
        StudentDAOImpl aLoginDAO = new StudentDAOImpl();
        ArrayList result = aLoginDAO.findUserID(getTheModel().getUserID());
        setTheModel((StudentBean) result.get(0));
        if (getTheModel() != null) {
            return "StudentUpdate.xhtml";
        } else {
            return "error.xhtml";
        }
    }
    
//     public String selectProfile() {
//        StudentDAOImpl aLoginDAO = new StudentDAOImpl();
//        ArrayList result = aLoginDAO.findUserID(getTheModel().getUserID());
//        setTheModel((StudentBean) result.get(0));
//        if (getTheModel() != null) {
//            return "StuDetails.xhtml";
//        } else {
//            return "error.xhtml";
//        }
//     }
        
        
    

     public void search()
    {
        int counter = 0;
        result = " ";
        
        StudentDAOImpl aStu = new StudentDAOImpl();
        //setResult1(aStu.findStudentGPA(getSearchTest()));
        ArrayList result1 = aStu.findStudentGPA(searchTest);
        result2 = result1;
        setTheModel(new StudentBean());
        
        
        
    }
    public String grabDetails(String a){
        for (int i = 0; i < result2.size();i++){
            if(result2.get(i).getUserID().equals(a)){
                setTheModel(result2.get(i));
                return "StuDetails.xhtml";
            } 
        }
        
        
        return "error.xhtml";
    }
    
    public void grab(){
        result = result2.get(0).toString();
        
        
       
        
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

//    /**
//     * @return the result1
//     */
//    public ArrayList getResult1() {
//        return result1;
//    }
//
//    /**
//     * @param result1 the result1 to set
//     */
//    public void setResult1(ArrayList result1) {
//        this.result1 = result1;
//    }

    /**
     * @return the result2
     */
    public ArrayList getResult2() {
        return result2;
    }

    /**
     * @param result2 the result2 to set
     */
    public void setResult2(ArrayList result2) {
        this.result2 = result2;
    }

    /**
     * @return the result3
     */
    public List<StudentBean> getResult3() {
        return result3;
    }

    /**
     * @param result3 the result3 to set
     */
    public void setResult3(List<StudentBean> result3) {
        this.result3 = result3;
    }

    public String sendEmail() {

        // Recipient's email ID needs to be mentioned.
        String to = theModel.getEmail();

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
            message.setSubject( subject, "text/html");

            // Send the actual HTML message, as big as you like
            message.setContent( text , "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return "UniLog.xhtml";
    }
    

    public void sendPasswordEmail(String myEmail) {

        // Recipient's email ID needs to be mentioned.
        String to = myEmail;

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
            message.setSubject("Password");

            // Send the actual HTML message, as big as you like
            message.setContent("<p>Hello " + theModel.getEmail() + ", your password is: " + theModel.getPassword() + "</p>", "text/html;charset=UTF-8");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void updateThis() {
        StudentDAOImpl aProfileDAO = new StudentDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.updateProfile(theModel); // Doing anything with the object after this?
        if (status != 0) {
            setUpdateStatus("Record updated successfully ");
        } else {
            setUpdateStatus("Record update failed!");
        }

    }

    /**
     * @return the updateStatus
     */
    public void recoverPass() {
        StudentDAOImpl aLoginDAO = new StudentDAOImpl();
        ArrayList result1 = aLoginDAO.recoverPass(theModel.getUserID(), theModel.getEmail(), theModel.getQuestion(), theModel.getAnswer());
        setTheModel((StudentBean) result1.get(0));
        if (getTheModel() != null) {
            sendPasswordEmail(theModel.getEmail());
            recover = "an Email will be sent to " + theModel.getEmail();
        } else {
            recover = "error.xhtml";
        }
    }
    public String clearBean() {
        setTheModel((StudentBean) null);
        logged = false;
        if (getTheModel() == null) {
            return "index2.xhtml";
        } else {
            return "error.xhtml";
        }
    }

  

 
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

    /**
     * @return the date2
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    /**
     * @return the logged
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * @param logged the logged to set
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

}
