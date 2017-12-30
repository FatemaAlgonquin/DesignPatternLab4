/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import java.util.List;
import transferobjects.Student;

/**
 *
 * @author fatema
 */
public interface StudentDAO {
    List<Student> getAllStudents();
    void addStudent(Student student);
    Student getStudentByStudentNumber(Integer studentnumber);
    void updateStudent(Integer studentNumber, String fName);
    void deleteStudent(Integer studentNumber);
    
    // method to be implemented for bonus
	//List<Course> getAuthorsByFirstName(String firstName);
	//Course getAuthorByAuthorId(Integer authorID);
	//void updateAuthor(Course author);
	//void deleteAuthor(Course author);
    
}
