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

public class AuthentifierClientAction extends Action {

    public AuthentifierClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        Client c = service.authentifierClient(login, password);
        System.out.println(c);
        Employe e = service.authentifierEmploye(login, password);
        System.out.println(e);

        if(c!=null){
            request.setAttribute("client",c);
            session.setAttribute("user", c.getId());
            session.setAttribute("userType", "client");
        }else if(e!=null){
            request.setAttribute("employe",e);
            session.setAttribute("user", e.getId());
            session.setAttribute("userType", "employe");
        }
       
    
    }   
}
