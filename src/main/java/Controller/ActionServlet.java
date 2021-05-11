/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author Matt
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * http://localhost:8080/DASI/ActionServlet?todo=connecter&login=matthieu.moutot@insa-lyon.fr&password=toto
     */
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String todo = request.getParameter("todo");
        System.out.println("todo : "+todo);
        
        if ("connecter".equals(todo))
        {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            System.out.println("Login : "+login);
            System.out.println("Password : "+password);
            

            Service s = new Service();
            Client c = s.authentifierClient(login, password);
            if (c != null)
            {
                
                JsonObject resp = new JsonObject();//the whole thing 
                resp.addProperty("connexion",true);
                JsonObject client = new JsonObject();//the client thing
                client.addProperty("id", c.getId());
                client.addProperty("nom",c.getNom());
                client.addProperty("renom",c.getPrenom());
                client.addProperty("mail",c.getMail());
                resp.add("client",client);
                
                PrintWriter out = response.getWriter();
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                gson.toJson(resp, out);
                out.close();
                
                System.out.println("Le client trouve : "+c.toString());
            } else {
                System.out.println("Aucun client trouve avec les identificants saisis");
            }
            
                    

        } 
    }

    
  @Override
  public void init() throws ServletException {
    super.init();
    JpaUtil.init();
  }

  @Override
  public void destroy() {
    JpaUtil.destroy();
    super.destroy();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
