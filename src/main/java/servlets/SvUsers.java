/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.User;

/**
 *
 * @author jorge
 */
@WebServlet(name = "SvUsers", urlPatterns = {"/SvUsers"})
public class SvUsers extends HttpServlet {
    
    ControladoraLogica control=new ControladoraLogica();
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);
         
         List<User> listUsers=new ArrayList<User>();
         listUsers=control.getAllUsers();
         HttpSession mySesion= request.getSession();
         mySesion.setAttribute("listUser", listUsers);
         System.out.println("la lista de usuario es ****"+listUsers.get(0));
         response.sendRedirect("viewUsers.jsp");
         
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String userName=request.getParameter("name");
        String userPassword=request.getParameter("password");
        String userRol=request.getParameter("rol");
        
        control.createUser(userName,userPassword, userRol);
      
    
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
