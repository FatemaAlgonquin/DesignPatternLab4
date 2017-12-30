/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import dataaccess.StudentDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.Student;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatema
 */
public class StudentView extends HttpServlet{
     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    protected void processFromRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //Path data_file = Paths.get("html/Student.html");
            
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("html/Student.html").getFile());
            
            try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();		
                        out.println(line + "\n");
		}
		scanner.close();
	        } catch (IOException e) {
		e.printStackTrace();
	       }
        }
    }
    
    
     protected void processStudentByIdRequest(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException{
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
             
             Student student = studentDAOImpl.getStudentByStudentNumber(id);
             ClassLoader classLoader = getClass().getClassLoader();
             File file = new File(classLoader.getResource("html/StudentByStudentNumber.html").getFile());
            
            try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();		
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
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ParseException 
     */
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // read form fields
            int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
            String firstName = request.getParameter("fName");
            String lastName = request.getParameter("lName");
            String DOB = request.getParameter("dob");
            //String enrolled = request.getParameter("enrolled");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date date = sdf.parse(DOB);
            java.sql.Date sqlDOBDate = new Date(date.getTime());
            //System.out.println("String converted to java.sql.Date :" + sqlDate);
            
            Date enrolled = new Date(new java.util.Date().getTime());
     
            Student student = new Student(studentNumber,firstName,lastName,sqlDOBDate,enrolled);
            StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
            studentDAOImpl.addStudent(student);
            
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("html/StudentCreated.html").getFile());
                    
            try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
                    
			String line = scanner.nextLine();		
                        out.println(line + "\n");
		}

		scanner.close();

	        } catch (IOException e) {
		e.printStackTrace();
	       }
        }catch(Exception e){
            System.out.println("exception happens");
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
        
        // decide whether to processFormReqest or processStudentByIdRequest();
        String pathInfo = request.getPathInfo();
        
         
        
        if(pathInfo == null || pathInfo == "//"){
                processFromRequest(request, response);
        }
        else{
            String validStNumber = pathInfo.replace("/", "");
            int id = Integer.parseInt(validStNumber);
            processStudentByIdRequest(request, response, id);
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
        /**
         * try {
            processPostRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StudentView.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        
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
