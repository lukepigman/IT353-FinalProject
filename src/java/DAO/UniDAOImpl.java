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
public class UniDAOImpl implements UniDAO {

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
            String myDB = "jdbc:derby://10.110.10.26:1527/dgarg_fall2018_Project353";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO itkstu.UNIVERSITY VALUES ('"
                    + aProfile.getUniID()
                    + "','" + aProfile.getUniPass()
                    + "','" + aProfile.getUniName()
                    + "','" + aProfile.getMajor()
                    + "','" + aProfile.getState()
                    + "','" + aProfile.getTown()
                    
                    + "','" + aProfile.getZip()
                    + "','" + aProfile.getACTReq()
                    + "','" + aProfile.getSATReq()
                    + "','" + aProfile.getAbout() 
                    + "','" + aProfile.getUniEmail()
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
                + "WHERE UNIVERSITYID = '" + userID + "'";

        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
    }

    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aProfileBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            String myDB = "jdbc:derby://10.110.10.26:1527/dgarg_fall2018_Project353";
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
                String uniPass = rs.getString("PASSWORD");
                String uniName = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String major = rs.getString("MAJOR");
                String LOCAL = rs.getString("STATE");
                String town = rs.getString("TOWN");
                String zip = rs.getString("ZIP");
                String act = rs.getString("ACTREQ");
                String sat = rs.getString("SATREQ");
                String about = rs.getString("ABOUT");

                // make a ProfileBean object out of the values
                aLoginBean = new UniBean(uniID, uniPass,  email, uniName, major, LOCAL, town, zip, act, sat, about);
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
                + "WHERE UNIVERSITYID = '" + code + "'";

        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
    }

    @Override
    public ArrayList findLogin(String userID, String password) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
                + "WHERE UNIVERSITYID = '" + userID + "' and PASSWORD = '" + password + "'";

        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;

    }
    @Override
    public ArrayList findUniversities(String userID) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
                + "WHERE UNIVERSITYID LIKE '" + userID +"%' OR NAME LIKE '" + userID + "%' OR TOWN LIKE '" + userID +  "%' OR STATE LIKE '" + userID + "%'";

        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;

    }
    
    @Override
    public ArrayList findShowcaseUniversities() {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
                + "WHERE ZIP = '1'";

        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;

    }

    @Override
    public int updateProfile(StudentBean stu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateProfile(UniBean pro) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://10.110.10.26:1527/dgarg_fall2018_Project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String updateString;
            Statement stmt = DBConn.createStatement();

            // SQL UPDATE Syntax [http://www.w3schools.com]:
            // UPDATE table_name
            // SET column1=value, column2=value2,...
            // WHERE some_column=some_value
            // Note: Notice the WHERE clause in the UPDATE syntax. The WHERE clause specifies which record or records that should be updated. If you omit the WHERE clause, all records will be updated!
            updateString = "UPDATE ITKSTU.UNIVERSITY SET "
                    + "STATE = '" + pro.getState() + "', "
                    + "EMAIL= '" + pro.getUniEmail() + "', "
                    + "TOWN = '" + pro.getTown() + "', "
                    + "ZIP = '" + pro.getZip() + "' ,"
                    + "ACTREQ = '" + pro.getACTReq() + "' ,"
                    + "SATREQ = '" + pro.getSATReq() + "' ,"
                    + "ABOUT = '" + pro.getAbout() + "' ,"
                    + "PASSWORD = '" + pro.getUniPass() + "' ,"
                    + "MAJOR = '" + pro.getMajor() + "' ,"
                    + "NAME = '" + pro.getUniName() + " ' "
                    + "WHERE UNIVERSITYID = '" + pro.getUniID() + "'";
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
    public ArrayList recoverPass(String userID) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY "
                + "WHERE UNVERSITYID = '" + userID + "' ";

        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;

    }
    @Override
    public void updateShowcase(String userID) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
         Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://10.110.10.26:1527/dgarg_fall2018_Project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String updateString;
            Statement stmt = DBConn.createStatement();
            
            updateString = "UPDATE ITKSTU.UNIVERSITY SET "
                    + "ZIP = '1' WHERE UNIVERSITYID = '" + userID + "'";
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("USERS updateString = " + updateString);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        

    }
    
    @Override
    public ArrayList findAll() {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        String query = "SELECT * FROM ITKSTU.UNIVERSITY ";
//                
                
        ArrayList aLoginCollection = selectProfilesFromDB(query);
        return aLoginCollection;
        
    }
    
    @Override
    public void removeUpdateShowcase(String userID) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
         Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://10.110.10.26:1527/dgarg_fall2018_Project353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String updateString;
            Statement stmt = DBConn.createStatement();
            
            updateString = "UPDATE ITKSTU.UNIVERSITY SET "
                    + "ZIP = '0' WHERE UNIVERSITYID = '" + userID + "'";
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("USERS updateString = " + updateString);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        

    }

}
