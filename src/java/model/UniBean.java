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
public class UniBean {
    private String uniID;
    private String uniEmail;
    private String uniPass;
    private String uniName;
    private String major;
    private String state;
    private String town;
    private String zip;
    private String ACTReq;
    private String SATReq;
    private String about;

    
    public UniBean()
    {
        
    }
    
    public UniBean(String uniID, String uniPass, String uniEmail ,String uniName, String major, String state, String town, String zip, String ACTReq, String SATReq, String about)
    {
        this.uniID = uniID;
        this.uniPass = uniPass;
        this.uniName = uniName;
        this.major = major;
        this.state = state;
        this.town = town;
        this.zip = zip;
        this.ACTReq = ACTReq;
        this.SATReq = SATReq;
        this.about = about;
        this.uniEmail = uniEmail;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the ACTReq
     */
    public String getACTReq() {
        return ACTReq;
    }

    /**
     * @param ACTReq the ACTReq to set
     */
    public void setACTReq(String ACTReq) {
        this.ACTReq = ACTReq;
    }

    /**
     * @return the SATreq
     */
    public String getSATReq() {
        return SATReq;
    }

    /**
     * @param SATReq the SATReq to set
     */
    public void setSATReq(String SATReq) {
        this.SATReq = SATReq;
    }

    /**
     * @return the about
     */
    public String getAbout() {
        return about;
    }

    /**
     * @param about the about to set
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * @return the uniID
     */
    public String getUniID() {
        return uniID;
    }

    /**
     * @param uniID the uniID to set
     */
    public void setUniID(String uniID) {
        this.uniID = uniID;
    }

    /**
     * @return the uniPass
     */
    public String getUniPass() {
        return uniPass;
    }

    /**
     * @param uniPass the uniPass to set
     */
    public void setUniPass(String uniPass) {
        this.uniPass = uniPass;
    }

    /**
     * @return the uniName
     */
    public String getUniName() {
        return uniName;
    }

    /**
     * @param uniName the uniName to set
     */
    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    /**
     * @return the uniEmail
     */
    public String getUniEmail() {
        return uniEmail;
    }

    /**
     * @param uniEmail the uniEmail to set
     */
    public void setUniEmail(String uniEmail) {
        this.uniEmail = uniEmail;
    }
    
    public String toString(){
        return uniID;
    }
    
    
}
