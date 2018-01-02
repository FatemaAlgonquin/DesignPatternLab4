/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.RegistryDAO;
import dataaccess.RegistryDAOImpl;
import java.util.List;
import transferobjects.Registry;
import transferobjects.Student;


/**
 *
 * @author fatema
 */
public class RegistryLogic {
    
    private static final int COURSE_CODE_MAX_LENGTH = 45;
    private static final int COURSE_NAME_MAX_LENGTH = 45;

    private RegistryDAO registryDAO = null;
    

    public RegistryLogic() {
        registryDAO = new RegistryDAOImpl();
    }

    public List<Registry> getAllRegisteredStudents() {
        return registryDAO.getAllStudents();
    }
    
     public void registerStudent(int studentNumber, String course_num, String term, int year) throws javax.xml.bind.ValidationException {
        registryDAO.addRegisterStudent(studentNumber,course_num, term,year);
    }
     
    public void deleteStudent(int studentNumber, String course){
        registryDAO.deleteStudent(studentNumber, course);
    }
    
    public void deletStudent(int studentNumber){
        Registry registry = registryDAO.getStudentByStudentNumber(studentNumber);
        if(registry != null){
            registryDAO.deleteRegisteredStudent(studentNumber);
        }
    }
    public Registry getStudentByStudentNumber(int studentNumber){
        return registryDAO.getStudentByStudentNumber(studentNumber);
        
    }
        
  
    
}
