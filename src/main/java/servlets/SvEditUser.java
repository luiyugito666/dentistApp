/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.User;


@WebServlet(name = "SvEditUser", urlPatterns = {"/SvEditUser"})
public class SvEditUser extends HttpServlet {
    ControladoraLogica control=new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         int idUser=Integer.parseInt(String.valueOf(request.getParameter("id")));
         User userEdit=control.getUserById(idUser);
         
         System.out.println("el usuario es "+userEdit.getUserName());
         
         HttpSession mySession=request.getSession();
         mySession.setAttribute("userEdit", userEdit);
        response.sendRedirect("editUser.jsp");
       
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String userName=request.getParameter("name");
        String password=request.getParameter("password");
        String rol=request.getParameter("rol");
        
        User usu= (User)request.getSession().getAttribute("userEdit");
        usu.setUserName(userName);
        usu.setPassword(password);
        usu.setRol(rol);
        
        control.editUser(usu);
        response.sendRedirect("SvUsers");
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
