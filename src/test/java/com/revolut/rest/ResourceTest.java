package com.revolut.rest;

import static junit.framework.TestCase.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class ResourceTest extends JerseyTest {
    
    //TODO add more cases
    
    @Override
    protected Application configure() {
        return new ResourceConfig(Resource.class);
    }


    @Test
    public void testGetUsersAndReturnAllUsers() {
        Response output = target("/api/v1/users").request().get();
        assertEquals("should return status 200", 200, output.getStatus());        
        assertNotNull("Should return an User list", output.getEntity());
    }

    
    @Test
    public void testGetUserWithWrongIdAndReturn404() {
        Response output = target("/api/v1/users/xxx").request().get();
        output.getStatus();
        assertEquals("should return status 404", 404, output.getStatus());
    }    
    
    
    @Test
    public void testGetTransfersAndReturnAllTransfers() {
        Response output = target("/api/v1/transfers").request().get();
        assertEquals("should return status 200", 200, output.getStatus());
        assertNotNull("Should return a Transfer list", output.getEntity());
    }    
    
}
