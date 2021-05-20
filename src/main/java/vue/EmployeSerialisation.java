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
import metier.modele.Employe;


/**
 *
 * @author MariaLemes
 */
public class EmployeSerialisation extends Serialisation {
    
        @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject resp = new JsonObject();
        
        //Lecture des attributs de la requete
        Employe e = (Employe)request.getAttribute("employe");
        
        if(e != null){
        
        resp.addProperty("connexion",true);
        
        JsonObject employe = new JsonObject();
        employe.addProperty("id", e.getId());
        employe.addProperty("nom",e.getNom());
        employe.addProperty("prenom",e.getPrenom());
        employe.addProperty("mail",e.getMail());
        employe.addProperty("telephone",e.getTelephone());
        employe.addProperty("genre",e.getGenre());
        employe.addProperty("nbConsultations",e.getNbConsultations());
        
        resp.add("employe",employe);
        }else{
            resp.addProperty("connexion",false);
        }
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        String json = gson.toJson(resp);
        System.out.println(json);

        gson.toJson(resp, out);
        out.close();
    }
    
}
