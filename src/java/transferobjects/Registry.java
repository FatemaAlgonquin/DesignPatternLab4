/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transferobjects;
import java.sql.Date;

/**
 *
 * @author fatema
 */
public class Registry {
    private int studentNumner;
    private String courseNumber;
    private String term;
    private int year;
    
    public Registry(){
        
    }
    
    public Registry(int studentNumner,String courseNumber, String term, int year){
        
        setStudentNumber(studentNumner);
        setCourseNumber(courseNumber);
        setTerm(term);
        setYear( year);      
    }
    
    public int getStudentNum(){
        return studentNumner;
    }
    
    public void setStudentNumber(int studentNumner){
        this.studentNumner = studentNumner;
    }
    
    public String courseNumber(){
        return courseNumber;
    }
    
    public void setCourseNumber(String courseNumber){
        this.courseNumber = courseNumber;
    }
    
    public String getTerm(){
        return term;
    }
    
    public void setTerm(String term){
        this.term = term;
    }
    
    public int getYear(){
        return year;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
}
