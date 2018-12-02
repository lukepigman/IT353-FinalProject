/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.StudentDAOImpl;
import DAO.UniDAOImpl;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.StudentBean;
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

    
    public UniController()
    {
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

    /**
     * @param updateStatus the updateStatus to set
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }
    
    public String createLogin() {
        UniDAOImpl aProfileDAO = new UniDAOImpl();    // Creating a new object each time.
        int status = aProfileDAO.createProfile(theModel); // Doing anything with the object after this?
        if (status == 1)
            return "UniLog.xhtml"; // navigate to "response.xhtml"
        else
            return "error.xhml"; 
    }
    
    public String findProfile()
    {
        UniDAOImpl aLoginDAO = new UniDAOImpl();
        ArrayList result = aLoginDAO.findUserID(getTheModel().getUniID());
        setTheModel((UniBean) result.get(0));
        if (getTheModel() != null)
        {
            return "StudentUpdate.xhtml";
        }
        else
            return "error.xhtml";
    }
    
    public String login()
    {
        UniDAOImpl aLogin = new UniDAOImpl();
        ArrayList result  = aLogin.findLogin(theModel.getUniID(),theModel.getUniPass()); 
        setTheModel((UniBean) result.get(0));
//        theModel.setUniID(result.get(0).toString());
        if(theModel != null)
        {
            return "UniLog.xhtml";
        }
        else
        {
            setLoginStatus("Login Failed");
            return "StudentLogin.xhtml"; 
        }
    }
}

