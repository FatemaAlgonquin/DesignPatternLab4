/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import transferobjects.Course;
import transferobjects.Student;
import transferobjects.Tuition;

/**
 *
 * @author fatema
 */
public interface RegistryDAO {
    List<Tuition> getAllStudents();
    void addStudent(Student student);
    Student getStudentByStudentNumber(Integer studentnumber);
    void updateStudent(Integer studentNumber,Course courseNumber);
    void deleteStudent(Integer studentNumber);
    
}
