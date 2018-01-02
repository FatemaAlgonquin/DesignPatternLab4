/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import transferobjects.Course;
import transferobjects.Registry;
import transferobjects.Student;
import transferobjects.Tuition;

/**
 *
 * @author fatema
 */
public interface RegistryDAO {
    List<Registry> getAllStudents();
    void addRegisterStudent(int student_num, String course_num, String term, int year);
    Registry getStudentByStudentNumber(Integer studentnumber);
    void updateStudent(Integer studentNumber,Course courseNumber);
    void deleteStudent(Integer studentNumber, String course);
    void deleteRegisteredStudent(Integer studentNumber);
    
}
