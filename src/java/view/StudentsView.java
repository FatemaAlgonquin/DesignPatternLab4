/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.StudentsLogic;
import business.TuitionLogic;
import dataaccess.StudentDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.Student;
import transferobjects.Tuition;

/**
 *
 * @author fatema
 */
public class StudentsView extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void getAllStudentsView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Students</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Students View at " + request.getContextPath() + "</h1>");
            StudentsLogic logic = new StudentsLogic();
            List<Student> students = logic.getAllStudents();
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>First Name</td>");
            out.println("<td>Last Name</td>");
            out.println("</tr>");
            for(Student student : students){
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", student.getStudentNum(), student.getFName(),student.getLName());
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    
    protected void createNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // read form fields
            int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
            String firstName = request.getParameter("fName");
            String lastName = request.getParameter("lName");
            String DOB = request.getParameter("dob");
            double tuition = Double.parseDouble(request.getParameter("tuition"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf.parse(DOB);
            java.sql.Date sqlDOBDate = new Date(date.getTime());
            //System.out.println("String converted to java.sql.Date :" + sqlDate);
            
            Date enrolled = new Date(new java.util.Date().getTime());
     
            Student student = new Student(studentNumber,firstName,lastName,sqlDOBDate,enrolled);
            
            StudentsLogic logic = new StudentsLogic();
            logic.addStudent(student, tuition);
            
           

            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student information serach by id</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>New student information at " + request.getContextPath() + "</h1>");
            
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>First Name</td>");
            out.println("<td>Last Name</td>");
            out.println("</tr>");
            out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", student.getStudentNum(), student.getFName(),student.getLName());
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
           
        }catch(Exception e){
            System.out.println("exception happens");
        }
    } 
    
    protected void processStudentByIdRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
             //StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
             //Student student = studentDAOImpl.getStudentByStudentNumber(studentNumber);
             StudentsLogic logic = new StudentsLogic();
             Student student = logic.findStudentById(studentNumber);
             
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student information serach by id</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Students View at " + request.getContextPath() + "</h1>");
            
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>First Name</td>");
            out.println("<td>Last Name</td>");
            out.println("</tr>");
            out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", student.getStudentNum(), student.getFName(),student.getLName());
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
             System.out.println("something wrong");
             
         }
    }
    
    
    
    protected void updateStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
             String firstName = request.getParameter("fName");
             StudentsLogic logic = new StudentsLogic();
             logic.updateStudentFirstName(studentNumber,firstName);
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student information serach by id</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Students View at " + request.getContextPath() + "</h1>");
            out.print("<h3> student information is updated </h3>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
             System.out.println("something wrong");
             
         }
    }
    
    
    protected void deleteStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
             //String firstName = request.getParameter("fName");
             StudentsLogic logic = new StudentsLogic();
             logic.deleteStudentById(studentNumber);
             
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Delete confirmation page</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Students View at " + request.getContextPath() + "</h1>");
            
            out.print("<h3> student is deleted. </h3>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
             System.out.println("something wrong");
             
         }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAllStudentsView(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //createNewStudent(request, response);
        String pathInfo = request.getPathInfo();
        
        if(pathInfo == null || pathInfo == "//"){
                 createNewStudent(request, response);
        } 
        if(pathInfo.equals("/search") ){
                processStudentByIdRequest(request, response);
        }
        if(pathInfo.equals("/update") ){
                updateStudentById(request, response);
        }
        if(pathInfo.equals("/delete") ){
                deleteStudentById(request, response);
        }
        
        
        else{
             response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()){
             out.print("<h3> invalid url ... </h3");
         }
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
