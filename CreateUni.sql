/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  awturne
 * Created: Nov 27, 2018
 */

CREATE TABLE UNIVERSITY
(UNIVERSITYID VARCHAR(30),
LOCATION VARCHAR(30),
TOWN VARCHAR(30),
ZIP VARCHAR(30),
ACTREQ VARCHAR(30),
SATREQ VARCHAR(30),
ABOUT VARCHAR(50),
CONSTRAINT UNIVERSITY_ID_PK PRIMARY KEY (UNIVERSITYID));
