package co.edu.sena.adsi.universidad.jpa.sessions;

import co.edu.sena.adsi.universidad.jpa.entities.Usuarios;
import co.edu.sena.adsi.universidad.jpa.entities.Usuarios_;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.apellidos;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.codigoUniversitario;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.contraseña;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.documento;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.estado;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.id;
import static co.edu.sena.adsi.universidad.jpa.entities.Usuarios_.nombres;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author adsi1261718
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "co.edu.sena.adsi_Universidad_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
        /**
     * Busca usuario por email
     *
     * @param email
     * @return Usuario
     */
    public Usuarios findUsuarioByEmail(String email) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.where(cb.equal(usuario.get(Usuarios_.email), email));
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        }
    }
    
    /**
     * Busca usuario por documento
     *
     * @param documento
     * @return Usuario
     */
    public Usuarios findUsuarioByDocumento(String documento) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.where(cb.equal(usuario.get(Usuarios_.documento), documento));
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public List<Usuarios> findUsers(Integer id, String codigoUniversitario, String nombres,
            String apellidos, String email, String documento, String contraseña,
            Boolean estado){
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        
        Predicate data = cb.conjunction();
        
        if(id != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.id), id));
        }
        
        if(codigoUniversitario != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.codigoUniversitario), codigoUniversitario));
        }
        
        if(nombres != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.nombres), nombres));
        }
        
        if(apellidos != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.apellidos), apellidos));
        }
        
        if(email != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.email), email));
        }
        
        if(documento != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.documento), documento));
        }
        if(contraseña != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.contraseña), contraseña));
        }
        if(estado != null){
            data = cb.and(data, cb.equal(usuario.get(Usuarios_.estado), estado));
        }
 
        
        
        
               
        cq.where(data);
        cq.orderBy(cb.asc(usuario.get(Usuarios_.nombres)));
        TypedQuery<Usuarios> tq = em.createQuery(cq);
        
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
         
}
