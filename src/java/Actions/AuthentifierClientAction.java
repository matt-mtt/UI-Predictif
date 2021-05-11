/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        Client c = service.authentifierClient(login, password);
        System.out.println(c);

        if(c!=null){
            JsonObject resp = new JsonObject();
            resp.addProperty("connexion",true);
            JsonObject client = new JsonObject();
            client.addProperty("id", c.getId());
            client.addProperty("nom",c.getNom());
            client.addProperty("prenom",c.getPrenom());
            client.addProperty("mail",c.getMail());
            resp.add("client",client);

            gson.toJson(resp, out);
            out.close();

        }
       
    }
    
}
