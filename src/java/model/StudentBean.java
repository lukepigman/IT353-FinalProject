/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author awturne
 */
public class StudentBean {
    private String firstName;
    private String lastName;
    private String userID;
    private String email;
    private String password;
    private String passwordConfirm;
    private String question;
    private String answer;
    private String GPA;
    private String ACT;
    private String SAT;
    private String schoolChoice;
    private String majorChoice;

    public StudentBean()
    {
        
    }
    
    public StudentBean(String firstName, String lastName, String userID, String email, String password, String passwordConfirm, String question, String answer, String GPA, String ACT, String SAT, String schoolChoice, String majorChoice)
    {
        this.firstName =firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.question = question;
        this.answer = answer;
        this.GPA = GPA;
        this.ACT = ACT;
        this.SAT = SAT;
        this.schoolChoice = schoolChoice;
        this.majorChoice = majorChoice;
    }
    
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the GPA
     */
    public String getGPA() {
        return GPA;
    }

    /**
     * @param GPA the GPA to set
     */
    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    /**
     * @return the ACT
     */
    public String getACT() {
        return ACT;
    }

    /**
     * @param ACT the ACT to set
     */
    public void setACT(String ACT) {
        this.ACT = ACT;
    }

    /**
     * @return the SAT
     */
    public String getSAT() {
        return SAT;
    }

    /**
     * @param SAT the SAT to set
     */
    public void setSAT(String SAT) {
        this.SAT = SAT;
    }

    /**
     * @return the schoolChoice
     */
    public String getSchoolChoice() {
        return schoolChoice;
    }

    /**
     * @param schoolChoice the schoolChoice to set
     */
    public void setSchoolChoice(String schoolChoice) {
        this.schoolChoice = schoolChoice;
    }

    /**
     * @return the majorChoice
     */
    public String getMajorChoice() {
        return majorChoice;
    }

    /**
     * @param majorChoice the majorChoice to set
     */
    public void setMajorChoice(String majorChoice) {
        this.majorChoice = majorChoice;
    }
    
    public String toString(){
        return userID + " " + email;
    }
    
    
}
