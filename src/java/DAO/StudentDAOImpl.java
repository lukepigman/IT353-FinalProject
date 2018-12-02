/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.StudentBean;

/**
 *
 * @author awturne
 */
public class StudentDAOImpl implements StudentDAO{
    
    @Override
    public int createProfile(StudentBean aProfile) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/Project353";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO itkstu.STUDENT VALUES ('"
                    + aProfile.getFirstName()
                    + "','" + aProfile.getLastName()
                    + "','" + aProfile.getUserID()
                    + "','" + aProfile.getEmail()
                    + "','" + aProfile.getPassword()
                    + "','" + aProfile.getPasswordConfirm()
                    + "','" + aProfile.getQuestion()
                    + "','" + aProfile.getAnswer()
                    + "','" + aProfile.getGPA()
                    + "','" + aProfile.getACT()
                    + "','" + aProfile.getSAT()
                    + "','" + aProfile.getSchoolChoice()
                    + "','" + aProfile.getMajorChoice()
                    + "')";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public ArrayList findUserID(String userID) {

        String query = "SELECT * FROM ITKSTU.STUDENT "
                + "WHERE userID = '" + userID +"'";
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
    }
    
    
    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aProfileBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            String myDB = "jdbc:derby://localhost:1527/project353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            StudentBean aLoginBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userID = rs.getString("userID");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String passwordConfirm = rs.getString("passwordConfirm");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                String GPA = rs.getString("GPA");
                String ACT = rs.getString("ACT");
                String SAT = rs.getString("SAT");
                String school = rs.getString("SchoolChoice");
                String major = rs.getString("MajorChoice");

                
                // make a ProfileBean object out of the values
                aLoginBean = new StudentBean(firstName, lastName, userID, email, password, passwordConfirm,  question, answer, GPA, ACT, SAT, school, major);
                // add the newly created object to the collection
                aProfileBeanCollection.add(aLoginBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aProfileBeanCollection;
    }

    @Override
    public int updateProfile(StudentBean pro) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String updateString;
            Statement stmt = DBConn.createStatement();

            // SQL UPDATE Syntax [http://www.w3schools.com]:
            // UPDATE table_name
            // SET column1=value, column2=value2,...
            // WHERE some_column=some_value
            // Note: Notice the WHERE clause in the UPDATE syntax. The WHERE clause specifies which record or records that should be updated. If you omit the WHERE clause, all records will be updated!
            updateString = "UPDATE ITKSTU.STUDENT SET "
                    + "FIRSTNAME = '" + pro.getFirstName() + "', "
                    + "LASTNAME = '" + pro.getLastName() + "', "
                    + "PASSWORD = '" + pro.getPassword() + "', "
                    + "PASSWORDCONFIRM = '" + pro.getPasswordConfirm() + "' ,"
                    + "EMAIL = '" + pro.getEmail() + "' ,"
                    + "QUESTION = '" + pro.getQuestion() + "' ,"
                    + "ANSWER = '" + pro.getAnswer() + "' ,"
                    + "GPA = '" + pro.getGPA() + "' ,"
                    + "ACT = '" + pro.getACT() + "' ,"
                    + "SAT = '" + pro.getSAT() + "' ,"
                    + "SCHOOLCHOICE = '" + pro.getSchoolChoice() + "' ,"
                    + "MAJORCHOICE = '" + pro.getMajorChoice() + "'"
                    + "WHERE USERID = '" + pro.getUserID() + "'";
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("USERS updateString = " + updateString);
            
           
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;

    } 
@Override
    public ArrayList recoverPass(String userID, String question, String answer, String email) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.STUDENT "
               + "WHERE USERID = '" + userID +"' and EMAIL = '" + email +"'  and QUESTION = '" + question + "' and ANSWER = '" + answer + "'";
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
        
    }


    @Override
    public ArrayList findStudentGPA(String GPA) {
    
        String query = "SELECT * FROM ITKSTU.STUDENT "
                + "WHERE GPA = '" + GPA +"'";
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
    }
    
    @Override
    public ArrayList findLogin(String userID, String password) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.STUDENT "
                + "WHERE USERID = '" + userID +"' and PASSWORD = '" + password +"'" ;
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
        
    }
    
    @Override
    public ArrayList findAll() {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.STUDENT ";
//                + "WHERE USERID = '" + userID +"' and PASSWORD = '" + password +"'" ;
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
        
    }
    
}
