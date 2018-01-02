/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferobjects.ClassRep;
import transferobjects.Course;
import transferobjects.Registry;

/**
 *
 * @author fatema
 */
public class ClassRepDAOImpl implements ClassRepDAO{
    private static final String ADD_CLASSREP="INSERT INTO Registrar.ClassRep (student_num,course_num, term, year) VALUES(?, ?, ?, ?)";

    @Override
    public List<ClassRep> getAllClassReps() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addClassRep(ClassRep classrep) {
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(ADD_CLASSREP);){
                pstmt.setInt(1, (classrep.getStudentNum()));
                pstmt.setString(2, classrep.courseNumber());
                pstmt.setString(3, classrep.getTerm());
                pstmt.setInt(4, classrep.getYear());
               
                int i = pstmt.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    

    @Override
    public List<ClassRep> getClassRepByStudentNum(int studentNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteClassRep(ClassRep classRep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
