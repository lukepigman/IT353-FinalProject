/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import model.StudentBean;

/**
 *
 * @author awturne
 */
public interface StudentDAO {
    public int createProfile(StudentBean stu);
    public ArrayList findUserID(String userID);
    public ArrayList findStudentGPA(String GPA);
    public int updateProfile(StudentBean stu);
    public ArrayList findLogin(String userID, String password);
    
}
