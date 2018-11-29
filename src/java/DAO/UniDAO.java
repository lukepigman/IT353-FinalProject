/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import model.StudentBean;
import model.UniBean;

/**
 *
 * @author awturne
 */
public interface UniDAO {
    public int createProfile(UniBean stu);
    public ArrayList findUserID(String userID);
    public ArrayList findUniversity(String code);
    public int updateProfile(StudentBean stu);
    public ArrayList findLogin(String userID, String password);
}
