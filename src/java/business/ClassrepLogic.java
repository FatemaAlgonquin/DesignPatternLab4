/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.ClassRepDAO;
import dataaccess.ClassRepDAOImpl;
import dataaccess.RegistryDAO;
import dataaccess.RegistryDAOImpl;
import java.util.List;
import transferobjects.ClassRep;
import transferobjects.Registry;

/**
 *
 * @author fatema
 */
public class ClassrepLogic {
    private static final int COURSE_CODE_MAX_LENGTH = 45;
    private static final int COURSE_NAME_MAX_LENGTH = 45;

    private ClassRepDAO classRepDAO = null;
    private RegistryLogic registryLogic = new RegistryLogic();
    

    public ClassrepLogic() {
        classRepDAO = new ClassRepDAOImpl();
    }
    
    //public void addClassRep(int studentNumber, String course_num, String term, int year) throws javax.xml.bind.ValidationException {
        //classRepDAO.addClassRep(studentNumber, course_num, term, year);
    //}
    
    public void addClassRep (int studentnumber){
        Registry registry = registryLogic.getStudentByStudentNumber(studentnumber);
        ClassRep classRep = new ClassRep(registry.getStudentNum(),registry.getCourseNumber(),registry.getTerm(),registry.getYear());
        classRepDAO.addClassRep(classRep); 
    }
    
    public List<ClassRep> getAllClassReps() {
        return classRepDAO.getAllClassReps();
    }

    
    
}
