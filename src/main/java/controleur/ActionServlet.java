package controleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import action.AuthentifierAction;
import action.InscrireClientAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;
import vue.ClientSerialisation;
import vue.EmployeSerialisation;
import vue.Serialisation;

/**
 *
 * @author MariaLemes
 */
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
        Service s = new Service();
        
        HttpSession session = request.getSession(true);
       
        String todo = request.getParameter("todo");
        
        System.out.println("****** to do = "+todo);
        
        Action action = null;
        Serialisation serialisationC = null;
        Serialisation serialisationE = null;
        
        switch(todo) {
            case "connecter" : {
                System.out.println("****** to do = "+todo);
                action = new AuthentifierAction(s);
                serialisationC = new ClientSerialisation();
                serialisationE = new EmployeSerialisation();
                
                break;
            }
            case "inscrit" :{
                System.out.println("****** to do = "+todo);
                action = new InscrireClientAction(s);
                serialisationC = new ClientSerialisation();
                break;
            }       
        }
        
        if(action !=null && (serialisationC !=null || serialisationE !=null)){
            action.execute(request);
            if(session.getAttribute("userType").equals("employe")){
                serialisationE.serialiser(request, response);
            }else{
                serialisationC.serialiser(request, response);
            }
            System.out.println("****** session id = "+session.getAttribute("user"));
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
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
     JpaUtil.destroy();
     super.destroy();
    }
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


