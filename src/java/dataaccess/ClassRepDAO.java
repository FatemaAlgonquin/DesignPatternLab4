/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import transferobjects.ClassRep;
import transferobjects.Course;

/**
 *
 * @author fatema
 */
public interface ClassRepDAO {
    List<ClassRep> getAllClassReps();
    void addClassRep(ClassRep classrep);
    List<ClassRep> getClassRepByStudentNum(int studentNumber);
    void deleteClassRep(ClassRep classRep);

    
}
