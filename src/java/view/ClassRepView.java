/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.ClassrepLogic;
import business.CoursesLogic;
import business.RegistryLogic;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.ClassRep;
import transferobjects.Course;
import transferobjects.Registry;

/**
 *
 * @author fatema
 */
public class ClassRepView extends HttpServlet{
     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Courses</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Class representatives View at " + request.getContextPath() + "</h1>");
            ClassrepLogic logic = new ClassrepLogic();
            List<ClassRep> classreps = logic.getAllClassReps();
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>Course Code</td>");
            out.println("<td>Course Name</td>");
            out.println("</tr>");
            for(ClassRep classrep : classreps){
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", classrep.getStudentNum(), classrep.getCourseNumber(),
                        classrep.getTerm(),classrep.getYear());
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void generateAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //Path data_file = Paths.get("html/Student.html");
            
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("html/classrep_add_form.html").getFile());
            
            RegistryLogic student = new RegistryLogic();
             List<Registry> students = student.getAllRegisteredStudents();
             StringBuilder studentNumber = new StringBuilder();
            
           // population the courses from the course table in html form
             for(Registry s : students){
                studentNumber.append("<option value=\"").append(s.getStudentNum()).append("\">").append(s.getStudentNum()).append("</option>");
                studentNumber.append(System.getProperty("line.separator")); 
                // <option value="CST8300">Achieving Success in Changing Environments</option>
            }
   
            try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
                        if(line.contains("__STUDENT_TEMPLATE__")){
                            //instead of __COURSE_TEMPLATE__ add the course options that populated from course table
                            line = studentNumber.toString();
                    }
                    
                        out.println(line + "\n");
		}
		scanner.close();
	        } catch (IOException e) {
		e.printStackTrace();
	       }
        }
    }
    
    protected void processAddClassRep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
             //String courseName = request.getParameter("Course");
             //StudentsLogic logic = new StudentsLogic();
             //logic.deleteStudent(studentNumber);
             ClassrepLogic logic1 = new ClassrepLogic();
             logic1.addClassRep(studentNumber);
             
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Class representative is added.</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Students View at " + request.getContextPath() + "</h1>");
            
            out.print("<h3> Class representative is added . </h3>");
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
        //processRequest(request, response);
        String pathInfo = request.getPathInfo();
        if(pathInfo.equals("/add_classrep_form") ){
                generateAddForm(request, response);
        }
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
        
        
        String pathInfo = request.getPathInfo();
        
        if(pathInfo == null || pathInfo == "//"){
                 processRequest(request, response); 
        } 
        if(pathInfo.equals("/addclassrep") ){
                processAddClassRep(request, response);
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
    

