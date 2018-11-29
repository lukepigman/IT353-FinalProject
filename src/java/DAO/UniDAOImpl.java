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
import model.UniBean;

/**
 *
 * @author awturne
 */
public class UniDAOImpl implements UniDAO{

    @Override
    public int createProfile(UniBean aProfile) {
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
            insertString = "INSERT INTO itkstu.UNIVERSITY VALUES ('"
                    + aProfile.getUniID()
                    + "','" + aProfile.getUniPass()
                    + "','" + aProfile.getMajor()
                    + "','" + aProfile.getState()
                    + "','" + aProfile.getTown()
                    + "','" + aProfile.getZip()
                    + "','" + aProfile.getACTReq()
                    + "','" + aProfile.getSATReq()
                    + "','" + aProfile.getAbout()
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

        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
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
            UniBean aLoginBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                String uniID = rs.getString("UNIVERSITYID");
                String uniPass = rs.getString("UNIPASS");
                String LOCAL = rs.getString("LOCATION");
                String town = rs.getString("TOWN");
                String zip = rs.getString("ZIP");
                String act= rs.getString("ACTREQ");
                String sat = rs.getString("SATREQ");
                String major = rs.getString("MAJOR");
                String about = rs.getString("ABOUT");
                

                
                // make a ProfileBean object out of the values
                aLoginBean = new UniBean(uniID, uniPass, major, LOCAL, town, zip, act, sat, about);
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
    public ArrayList findUniversity(String code) {
        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
                + "WHERE UNIVERSITYID = '" + code +"'";
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
    }
    
    @Override
    public ArrayList findLogin(String userID, String password) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
                + "WHERE USERID = '" + userID +"' and PASSWORD = '" + password +"'" ;
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
        
    }
    
    

    @Override
    public int updateProfile(StudentBean stu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
