package co.edu.sena.universidad.rest.services;



import co.edu.sena.adsi.universidad.jpa.entities.MateriasElectivas;
import co.edu.sena.adsi.universidad.jpa.sessions.MateriasElectivasFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ofelia
 */
@Path("materiasElectivas")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MateriasElectivasREST {
     @EJB
    private MateriasElectivasFacade puestosEJB;
    
    @GET
    public List<MateriasElectivas> findAll(){
        return puestosEJB.findAll();
    }
    
    @POST
    public void create(MateriasElectivas materiasElectivas){
        puestosEJB.create(materiasElectivas);
    }
}
