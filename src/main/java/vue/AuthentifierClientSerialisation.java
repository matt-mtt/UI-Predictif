/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;

/**
 *
 * @author MariaLemes
 */
public class AuthentifierClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject resp = new JsonObject();
        
        //Lecture des attributs de la requete
        Client c = (Client)request.getAttribute("client");
        
        resp.addProperty("connexion",true);
        
        JsonObject client = new JsonObject();
        client.addProperty("id", c.getId());
        client.addProperty("nom",c.getNom());
        client.addProperty("prenom",c.getPrenom());
        client.addProperty("mail",c.getMail());
        resp.add("client",client);
        
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        gson.toJson(resp, out);
        out.close();
    }
    
    
}
