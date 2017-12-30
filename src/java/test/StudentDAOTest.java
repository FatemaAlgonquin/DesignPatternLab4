/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import dataaccess.StudentDAO;
import transferobjects.Student;
import dataaccess.StudentDAOImpl;

/**
 *
 * @author fatema
 */
public class StudentDAOTest {
    public static void main(String[]args){
        Student student = new Student(01,"Fatema", "Zohora", java.sql.Date.valueOf("1998-09-04"), java.sql.Date.valueOf("2017-09-04"));
        StudentDAO studentDAOImpl = new StudentDAOImpl();
        studentDAOImpl.addStudent(student);
    
    }
    
            
    
    
}
