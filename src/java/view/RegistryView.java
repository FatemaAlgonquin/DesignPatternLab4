/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import business.CoursesLogic;
import business.RegistryLogic;
import business.StudentsLogic;
import business.TuitionLogic;
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
import transferobjects.Course;
import transferobjects.Registry;
import transferobjects.Student;


/**
 *
 * @author fatema
 */
public class RegistryView extends HttpServlet{
    protected void displayRegisteredStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Courses</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Registered students  View at " + request.getContextPath() + "</h1>");
            RegistryLogic logic = new RegistryLogic();
            List<Registry> registries = logic.getAllRegisteredStudents();
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Student Number</td>");
            out.println("<td>Course Code</td>");
            out.println("<td>Term</td>");
            out.println("<td>Year</td>");
            out.println("</tr>");
            for(Registry registry : registries){
                out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",registry.getStudentNum(),registry.getCourseNumber(),registry.getTerm(),registry.getYear());
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void generateNewStudentRegistrationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            StudentsLogic student = new StudentsLogic();
            List<Student> students = student.getAllStudents();
            StringBuilder studentNumber = new StringBuilder();
            
           // population the courses from the course table in html form
            for(Student s : students){
                studentNumber.append("<option value=\"").append(s.getStudentNum()).append("\">").append(s.getStudentNum()).append("</option>");
                studentNumber.append(System.getProperty("line.separator")); 
                // <option value="CST8300">Achieving Success in Changing Environments</option>
            }
            
            CoursesLogic course = new CoursesLogic();
            List<Course> allCourses = course.getAllCourses();
            StringBuilder sb=new StringBuilder();  
                     
            // population the courses from the course table in html form
            for(Course c : allCourses){
                sb.append("<option value=\"" + c.getCode() + "\">" + c.getName() +"</option>");
                sb.append(System.getProperty("line.separator")); 
                // <option value="CST8300">Achieving Success in Changing Environments</option>
            }
           
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("html/student_registration_form.html").getFile());
            
            try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
                        if(line.contains("__COURSE_TEMPLATE__")){
                            //instead of __COURSE_TEMPLATE__ add the course options that populated from course table
                            line = sb.toString();
                        }
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
    
    protected void generateDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             ClassLoader classLoader = getClass().getClassLoader();
             File file = new File(classLoader.getResource("html/registered_student_delete_by_id_form.html").getFile());
             
             RegistryLogic student = new RegistryLogic();
             List<Registry> students = student.getAllRegisteredStudents();
             StringBuilder studentNumber = new StringBuilder();
            
           // population the courses from the course table in html form
             for(Registry s : students){
                studentNumber.append("<option value=\"").append(s.getStudentNum()).append("\">").append(s.getStudentNum()).append("</option>");
                studentNumber.append(System.getProperty("line.separator")); 
                // <option value="CST8300">Achieving Success in Changing Environments</option>
            }
             
            CoursesLogic course = new CoursesLogic();
            List<Course> allCourses = course.getAllCourses();
            StringBuilder sb=new StringBuilder();  
                     
            // population the courses from the course table in html form
            for(Course c : allCourses){
                sb.append("<option value=\"" + c.getCode() + "\">" + c.getCode() +"</option>");
                sb.append(System.getProperty("line.separator")); 
                // <option value="CST8300">Achieving Success in Changing Environments</option>
            }
            
            try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(line.contains("__STUDENT_TEMPLATE__")){
                            //instead of __COURSE_TEMPLATE__ add the course options that populated from course table
                            line = studentNumber.toString();
                    }
                    if(line.contains("__COURSE_TEMPLATE__")){
                            //instead of __COURSE_TEMPLATE__ add the course options that populated from course table
                            line = sb.toString();
                    }
					
                    out.println(line + "\n");
		}
		scanner.close();
	        } catch (IOException e) {
		e.printStackTrace();
	       }
             
         }catch(Exception e){
             System.out.println("something wrong");
             
         }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo.equals("/all_students_form") ){
                displayRegisteredStudents(request, response);
        }
        if(pathInfo.equals("/add_student_form") ){
                generateNewStudentRegistrationForm(request, response);
        }
        if(pathInfo.equals("/delete_student_form") ){
                generateDeleteForm(request, response);
        } 
    }
    
    
    protected void registerNewStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // read form fields
            int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
            String courseNumber = request.getParameter("Course");
            String term = request.getParameter("term");
            int year = Integer.parseInt(request.getParameter("year"));
           
            //Student student = new Student(studentNumber,firstName,lastName,sqlDOBDate,enrolled);
            
            RegistryLogic logic = new RegistryLogic();
            logic.registerStudent(studentNumber,courseNumber,term,year);
                        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student information serach by id</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>New student information at " + request.getContextPath() + "</h1>");
            out.println("<h4>New student added in the Registry table </h4>");
            out.println("</body>");
            out.println("</html>");
           
        }catch(Exception e){
            System.out.println("exception happens");
        }
    } 
    
    protected void processDeleteRegisteredStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
             String courseName = request.getParameter("Course");
             //StudentsLogic logic = new StudentsLogic();
             //logic.deleteStudent(studentNumber);
             RegistryLogic logic1 = new RegistryLogic();
             logic1.deleteStudent(studentNumber,courseName);
             
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Delete confirmation page</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Students View at " + request.getContextPath() + "</h1>");
            
            out.print("<h3> Registered student is deleted. </h3>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
             System.out.println("something wrong");
             
         }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //createNewStudent(request, response);
        
        //registerNewStudent(request, response); 
        
        String pathInfo = request.getPathInfo();
        
        if(pathInfo == null || pathInfo == "//"){
                 registerNewStudent(request, response); 
        } 
        if(pathInfo.equals("/delete") ){
                processDeleteRegisteredStudent(request, response);
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
