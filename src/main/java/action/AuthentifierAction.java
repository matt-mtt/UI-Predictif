/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.google.gson.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.service.Service;

/**
 *
 * @author MariaLemes
 */

public class AuthentifierAction extends Action {

    public AuthentifierAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        Client c = null;
        Employe emp = null;
        
       try{ 
            c = service.authentifierClient(login, password);
            System.out.println(c);
            emp = service.authentifierEmploye(login, password);
            System.out.println(emp);
       }catch(Exception e){
           e.printStackTrace();
               
       }finally{
        if(c!=null){
            request.setAttribute("client",c);
            session.setAttribute("user", c.getId());
            session.setAttribute("userType", "client");
        }else if(emp!=null){
            request.setAttribute("employe",emp);
            session.setAttribute("user", emp.getId());
            session.setAttribute("userType", "employe");
        }
    }
    
    }   
}
