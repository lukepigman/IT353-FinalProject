/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.StudentDAOImpl;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    
    
    
    public StudentController()
    {
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
        if (status == 1)
            return "StudentEn.xhtml";
        else
            return "index.xhtml"; 
    }
    
    public String login() 
    {
        StudentDAOImpl aLoginDAO = new StudentDAOImpl();    // Creating a new object each time.
        ArrayList result  = aLoginDAO.findLogin(theModel.getUserID(),theModel.getPassword()); 
        setTheModel((StudentBean) result.get(0));
        if(theModel != null)
        {
            return "StudentEn.xhtml";
        }
        else
        {
            setLoginStatus("Login Failed");
            return "StudentLogin.xhtml"; 
        }
    }
    
    public String findProfile()
    {
        StudentDAOImpl aLoginDAO = new StudentDAOImpl();
        ArrayList result = aLoginDAO.findUserID(getTheModel().getUserID());
        setTheModel((StudentBean) result.get(0));
        if (getTheModel() != null)
        {
            return "StudentUpdate.xhtml";
        }
        else
            return "error.xhtml";
    }
    
    public void updateThis() {
        StudentDAOImpl aProfileDAO = new StudentDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.updateProfile(getTheModel()); // Doing anything with the object after this?
        if (status != 0) {
            setUpdateStatus("Record updated successfully ");
        } else {
            setUpdateStatus("Record update failed!");
        }

    }
    
    public void search()
    {
        int counter = 0;
        StudentDAOImpl aStu = new StudentDAOImpl();
        ArrayList result1 = aStu.findStudentGPA(searchTest);
        if(result1.size() > 0)
        {
            counter++;
            result = result1.get(0).toString();
            System.out.println(result);
        }
        else
        {
           counter++; 
           result = Integer.toString(counter);
        }
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
}