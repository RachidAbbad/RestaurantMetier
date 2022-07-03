package com.sigl.session;

import com.sigl.entities.*;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.EJB;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;
import java.util.Map;

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
        public String deleteMenu(@PathParam("id") long id) {
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

        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/{idCmd}")
        public void deleteCommande(@PathParam("idCmd") long id) {
            metier.deleteCommande(id);
        }

    }


    @Path("/login")
    public static class Auth{
        public Auth(){}

        @POST
        @Path("")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        public Client login(@FormParam("email") String email,
                                  @FormParam("password") String password) {
            System.out.println(email + " " + password);
            return metier.login(email, password);
        }
    }

    @Path("/files")
    public static class Files {

        private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/BIG CHOIX/Desktop/git/RestaurantWeb/target/RestaurantWeb-1.0-SNAPSHOT/Images/";

        public Files(){}

        @POST
        @Path("/upload")
        @Consumes("multipart/form-data")
        public Response uploadFile(MultipartFormDataInput input) {

            String fileName = "";

            Map<String, List<InputPart>> formParts = input.getFormDataMap();

            List<InputPart> inPart = formParts.get("file");

            for (InputPart inputPart : inPart) {

                try {

                    // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
                    MultivaluedMap<String, String> headers = inputPart.getHeaders();
                    fileName = parseFileName(headers);

                    // Handle the body of that part with an InputStream
                    InputStream istream = inputPart.getBody(InputStream.class,null);

                    fileName = SERVER_UPLOAD_LOCATION_FOLDER + fileName;

                    saveFile(istream,fileName);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            String output = "File saved to server location : " + fileName;

            return Response.status(200).entity(output).build();
        }

        // Parse Content-Disposition header to get the original file name
        private String parseFileName(MultivaluedMap<String, String> headers) {

            String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

            for (String name : contentDispositionHeader) {

                if ((name.trim().startsWith("filename"))) {

                    String[] tmp = name.split("=");

                    String fileName = tmp[1].trim().replaceAll("\"","");

                    return fileName;
                }
            }
            return "randomName";
        }

        // save uploaded file to a defined location on the server
        private void saveFile(InputStream uploadedInputStream,
                              String serverLocation) {

            try {
                OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
                int read = 0;
                byte[] bytes = new byte[1024];

                outpuStream = new FileOutputStream(new File(serverLocation));
                while ((read = uploadedInputStream.read(bytes)) != -1) {
                    outpuStream.write(bytes, 0, read);
                }
                outpuStream.flush();
                outpuStream.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


}
