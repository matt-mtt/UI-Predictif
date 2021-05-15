/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author MariaLemes
 */
public class InscrireClientAction extends Action{

    public InscrireClientAction(Service service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("prenom");
        String tel = request.getParameter("telephone");
        String date = request.getParameter("dateNaissance");
        String mail = request.getParameter("login");
        String password = request.getParameter("password");
        
        try{
            Date dateNaissance = simpleDateFormat.parse(date);

            Client c = new Client(nom, prenom, dateNaissance,  adresse, tel, mail, password);
            
            c = service.inscrireClient(c);
            System.out.println(c);

            if(c!=null){
                request.setAttribute("client",c);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
