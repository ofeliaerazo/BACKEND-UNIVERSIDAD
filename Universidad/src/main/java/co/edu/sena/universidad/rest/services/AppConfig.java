/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.universidad.rest.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;


@ApplicationPath("api")
public class AppConfig extends ResourceConfig{

    public AppConfig() {
         packages("co.edu.sena.universidad.rest.services;co.edu.sena.universidad.rest.auth");
        register(RolesAllowedDynamicFeature.class);
         }
}