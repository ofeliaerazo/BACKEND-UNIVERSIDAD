/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.universidad.jpa.sessions;

import co.edu.sena.adsi.universidad.jpa.entities.Electivas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adsi1261718
 */
@Stateless
public class ElectivasFacade extends AbstractFacade<Electivas> {

    @PersistenceContext(unitName = "co.edu.sena.adsi_Universidad_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElectivasFacade() {
        super(Electivas.class);
    }
    
}
