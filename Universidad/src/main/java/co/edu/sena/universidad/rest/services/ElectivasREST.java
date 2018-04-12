package co.edu.sena.universidad.rest.services;



import co.edu.sena.adsi.universidad.jpa.entities.Electivas;
import co.edu.sena.adsi.universidad.jpa.entities.MateriasElectivas;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.codigo;
import co.edu.sena.adsi.universidad.jpa.sessions.ElectivasFacade;
import co.edu.sena.adsi.universidad.jpa.sessions.RolesFacade;
import co.edu.sena.adsi.universidad.jpa.sessions.UsuariosFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ofelia
 */
@Path("materiasElectivas")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ElectivasREST {
    
    
     @EJB
    private ElectivasFacade electivasEJB;
    
      @EJB
    private UsuariosFacade usuariosEJB;
      
       @EJB
    private RolesFacade rolesEJB;
    
    @GET
    public List<Electivas> findAll(){
        return electivasEJB.findAll();
    }
    
    @POST
    public void create(Electivas electivas){
        electivasEJB.create(electivas);
    }
    
    public Response escoger(
            @QueryParam("codigoUniversitario") String codigoUniversitario){
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            
            try {
                Electivas electivas = null;
                
                if(UsuariosFacade.find(codigo) != null){
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("el usuario escogio  su materia electiva")).build();
             
                 }
                for (Electivas electivas: )
                else{
                    
                }
                
            }
             catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error: " + e.getLocalizedMessage())).build();
        }
    
    }
}
    
    

