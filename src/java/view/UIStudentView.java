/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.StudentsLogic;
import dataaccess.StudentDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.Student;

/**
 *
 * @author fatema
 */
public class UIStudentView extends HttpServlet{
    
    protected void generateNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //Path data_file = Paths.get("html/Student.html");
            
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("html/student_new_form.html").getFile());
            
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
    
   
    
    
    protected void generateSearchForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             ClassLoader classLoader = getClass().getClassLoader();
             File file = new File(classLoader.getResource("html/student_search_by_id_form.html").getFile());
            
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
    
    protected void generateUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             ClassLoader classLoader = getClass().getClassLoader();
             File file = new File(classLoader.getResource("html/student_update_by_id_form.html").getFile());
            
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
    
    protected void generateDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             ClassLoader classLoader = getClass().getClassLoader();
             File file = new File(classLoader.getResource("html/student_delete_by_id_form.html").getFile());
            
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
       String pathInfo = request.getPathInfo();
        if(pathInfo.equals("/new_form") ){
                generateNewForm(request, response);
        }
        if(pathInfo.equals("/serach_form") ){
                generateSearchForm(request, response);
        }
        if(pathInfo.equals("/update_form") ){
                generateUpdateForm(request, response);
        }
        if(pathInfo.equals("/delete_form") ){
                generateDeleteForm(request, response);
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
