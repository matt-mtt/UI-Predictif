/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Medium;
import metier.modele.Spirite;
import metier.service.Service;

/**
 *
 * @author gustavobertoldi
 */
public class RecupererMediums extends Action{

    public RecupererMediums(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        
        String type = request.getParameter("type");
        
        List<Medium> mediums;
        
        switch (type)
        {
            case "spirite" :
                mediums = new LinkedList(service.listerSpirite());
                break;
           
            case "cartomancien" :
                mediums = new LinkedList(service.listerCartomancien());
                break;
                
            case "astrologue":
                mediums = new LinkedList(service.listerAstrologue());
                break;
        }
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        Client c = service.authentifierClient(login, password);
        System.out.println(c);

        if(c!=null){
            request.setAttribute("client",c);
        }
       
    
    } 
}
