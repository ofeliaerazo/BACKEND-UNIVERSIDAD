package co.edu.sena.universidad.rest.services;


import co.edu.sena.adsi.universidad.jpa.entities.Usuarios;
import co.edu.sena.adsi.universidad.jpa.sessions.UsuariosFacade;
import co.edu.sena.universidad.rest.auth.DigestUtil;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioREST {

    @EJB
    private UsuariosFacade usuarioEJB;
    
      public List<Usuarios> findAll(
            @QueryParam("codigo") String codigo,
            @QueryParam("nombres") String nombres,
            @QueryParam("apellidos") String apellidos,
            @QueryParam("email") String email,
            @QueryParam("documento") String documento,
            @QueryParam("direccion") Boolean direccion) {

        return usuarioEJB.
                sexo, activo, tipoDocumento);
    }
   
    @GET
    @Path("{id}")
    public Usuarios findById(
            @PathParam("id") Integer id) {
        return usuarioEJB.find(id);
    }

   
    @POST
    public Response create(Usuarios usuario) {
        System.out.println("TEST");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        
       
        try {
            
            if (usuarioEJB.findUsuarioByEmail(usuario.getEmail()) == null) {
                if (usuarioEJB.findUsuarioByDocumento(usuario.getDocumento()) == null) {
                    
                    usuario.setContraseña(DigestUtil.cifrarPassword(usuario.getContraseña()));
                    usuarioEJB.create(usuario);
                    
                    return Response.status(Response.Status.CREATED)
                            .entity(gson.toJson("El usuario se registro correctamente"))
                            .build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(gson.toJson("EL numero de documento ya existe."))
                            .build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(gson.toJson("El email ya existe.."))
                        .build();
            }
        } catch (Exception e) {
            System.out.println("ofelia" + e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Error al registrar el usuario"))
                    .build();
        }
    }

    /**
     * Edita un usuario
     *
     * @param id
     * @param usuario
     * @return
     */
    @PUT
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Usuarios usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
       

        try {
              usuario.setContraseña(DigestUtil.cifrarPassword(usuario.getContraseña()));

            usuarioEJB.edit(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson("El usuario se actualizo correctamente"))
                    .build();

        } catch (Exception e) {
            System.out.println("Err" + e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Error al actualizar el usuario"))
                    .build();
        }
    }
}
