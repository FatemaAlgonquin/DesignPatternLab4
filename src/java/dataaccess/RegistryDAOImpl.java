/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferobjects.Course;
import transferobjects.Registry;
import transferobjects.Student;

/**
 *
 * @author fatema
 */
public class RegistryDAOImpl implements RegistryDAO{
     //private static final String GET_BY_CODE_COURSES = "SELECT course_num, name FROM Courses WHERE name = ?";
     private static final String GET_ALL_STUDENTS = "SELECT student_num, course_num, term, year  FROM Registrar.Registry ORDER BY student_num";
     private static final String REGISTER_STUDENTS="INSERT INTO Registrar.Registry (student_num,course_num, term, year) VALUES(?, ?, ?, ?);";
     private static final String DELETE_REGISTERED_STUDENT_FROM_COURSE = "DELETE FROM Registrar.Registry WHERE student_num = ?  AND course_num = ?";
     private static final String DELETE_REGISTERED_STUDENT = "DELETE FROM Registrar.Registry WHERE student_num = ?";
     private static final String GET_REGISTERED_STUDENT = "SELECT * FROM Registrar.Registry WHERE student_num = ?";
    @Override
    public List<Registry> getAllStudents() {
        List<Registry> registries = Collections.EMPTY_LIST;
        Registry registry;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement( GET_ALL_STUDENTS);
            rs = pstmt.executeQuery();
            registries = new ArrayList<>(100);
            while( rs.next()){
                registry = new Registry();
                registry.setStudentNumber(rs.getInt("student_num"));
                registry.setCourseNumber(rs.getString("course_num"));
                registry.setTerm(rs.getString("term"));
                registry.setYear(rs.getInt("year"));
                registries.add(registry);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return registries;
        
    }

    @Override
    public void addRegisterStudent(int student_num, String course_num, String term, int year) {
        
        try( Connection con = new DataSource().createConnection();
                PreparedStatement pstmt = con.prepareStatement(REGISTER_STUDENTS);){
                pstmt.setInt(1, student_num);
                pstmt.setString(2, course_num);
                pstmt.setString(3, term);
                pstmt.setInt(4, year);
               
                int i = pstmt.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @Override
    public Registry getStudentByStudentNumber(Integer studentnumber) {
        Connection con = null;
        Registry registry = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();    
            pstmt = con.prepareStatement(GET_REGISTERED_STUDENT);
            pstmt.setInt(1,studentnumber);
            rs = pstmt.executeQuery();
            
            registry = new Registry();
            rs.next();
            registry.setStudentNumber(rs.getInt("student_num"));
            registry.setCourseNumber(rs.getString("course_num"));
            registry.setTerm(rs.getString("term"));
            registry.setYear(rs.getInt("year"));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return registry;
    }


    @Override
    public void updateStudent(Integer studentNumber, Course courseNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteStudent(Integer studentNumber, String course) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();    
            pstmt = con.prepareStatement(DELETE_REGISTERED_STUDENT_FROM_COURSE);
            pstmt.setInt(1,studentNumber);
            pstmt.setString(2,course);
            
            //pstmt.executeUpdate();
            pstmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
    }

    @Override
    public void deleteRegisteredStudent(Integer studentNumber) {
           Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            DataSource ds = new DataSource();
            con = ds.createConnection();    
            pstmt = con.prepareStatement(DELETE_REGISTERED_STUDENT);
            pstmt.setInt(1,studentNumber);
            pstmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
    }
   
    
}
