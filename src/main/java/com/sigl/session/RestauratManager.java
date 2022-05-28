package com.sigl.session;

import com.sigl.entities.Categorie;
import com.sigl.entities.Commande;
import com.sigl.entities.LigneCommande;
import com.sigl.entities.Menu;

import javax.ejb.EJB;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationPath("/api")
public class RestauratManager extends Application {

    public RestauratManager(){}

    @EJB
    private static SessionImplLocal metier;

    @Path("/menu")
    public static class Menus{
        public Menus(){}

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("")
        public List<Menu> getAllMenus() {
            return metier.getAllMenus();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        public Menu getAllMenus(@PathParam("id") long id) {
            return metier.getMenu(id);
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/searchByTitle/{title}")
        public List<Menu> searchMenusByTitle(@PathParam("title") String title) {
            return metier.searchMenusByTitle(title);
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/searchByCategorie/{categorie}")
        public List<Menu> searchMenusByCategorie(@PathParam("categorie") long categorie) {
            return metier.searchMenusByCategorie(categorie);
        }

        @POST
        @Path("")
        @Consumes("application/json")
        public void addMenu(Menu m) {
            metier.addMenu(m);
        }

        @PUT
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        @Consumes("application/json")
        public String editMenu(@PathParam("id") long id , Menu m) {
            try{
                metier.updateMenu(id,m);
                return "{'status' : true , 'message' : 'Le menu a été modifié avec succè' }";
            }catch (Exception ex){

                    return "{'status' : false , 'message' : 'Le menu n'a été pas modifié avec succè' , 'error' : "+ex.getMessage()+"}";
            }
        }

        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        @Consumes("application/json")
        public String editMenu(@PathParam("id") long id) {
            try{
                metier.deleteMenu(id);
                return "{'status' : true , 'message' : 'Le menu a été supprimé avec succè'}";
            }catch (Exception ex){
                return "{'status' : false ,'message' : 'Le menu n'a été pas supprimé avec succè', 'error' : "+ex.getMessage()+"}";
            }
        }
    }

    @Path("/categorie")
    public static class Categories{
        public Categories(){}

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("")
        public List<Categorie> getAllCategories() {
            return metier.getAllCategories();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        public Categorie getCategory(@PathParam("id") long id) {
            return metier.getCategorie(id);
        }

        @POST
        @Path("")
        @Consumes("application/json")
        public void addCategorie(Categorie c) {
            metier.addCategorie(c);
        }


        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{id}")
        @Consumes("application/json")
        public String editMenu(@PathParam("id") long id) {
            try{
                metier.deleteCategorie(id);
                return "{'status' : true , 'message' : 'La categorie a été supprimé avec succè'}";
            }catch (Exception ex){
                return "{'status' : false ,'message' : 'La categorie n'a été pas supprimé avec succè', 'error' : "+ex.getMessage()+"}";
            }
        }
    }

    @Path("/commande")
    public static class Commandes{
        public Commandes(){}

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("")
        public List<Commande> getAllCommandes() {
            return metier.getAllCommandes();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/byClient/{idClient}")
        public List<Commande> getCommandesByIdClient(@PathParam("idClient") long id) {
            return metier.getCommandesByIdClient(id);
        }

        @POST
        @Path("")
        @Consumes("application/json")
        public void addCommande(Commande c) {
            metier.addCommande(c);
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{idCmd}")
        public Commande getLignesCommande(@PathParam("idCmd") long id) {
            return metier.getLignesCommande(id);
        }


    }

}
