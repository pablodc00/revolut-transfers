package com.revolut.rest;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.revolut.dao.Dao;
import com.revolut.dao.impl.TransferDao;
import com.revolut.dao.impl.UserDao;
import com.revolut.model.Transfer;
import com.revolut.model.User;
import com.revolut.service.TransferService;
import com.revolut.service.UserService;
import com.revolut.service.impl.TransferServiceImpl;
import com.revolut.service.impl.UserServiceImpl;
import com.revolut.util.BusinessException;
import com.revolut.util.TransferConstants;

@Path("api/v1")
public class Resource {
    
    private static final Logger LOGGER = Logger.getLogger(Resource.class.getName());
    
    private UserService userService;
    private TransferService transferService;
    
    public Resource() {
        Dao<User> userDao = new UserDao();
        this.userService = new UserServiceImpl(userDao);
        this.transferService = new TransferServiceImpl(userDao, new TransferDao());
    }
    
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    //TODO add pagination and filters
    public List<User> getUsers() {
        return userService.getUsers();
    }    

    @GET
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") String userId) {
        Optional<User> oUser = userService.getUserById(userId);
        if (oUser.isPresent()) {
            return Response.ok(oUser.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("transfers")
    @Produces(MediaType.APPLICATION_JSON)
    //TODO add pagination and filters
    public List<Transfer> getTransfers() {
        return transferService.getTransfers();
    }
    
    @GET
    @Path("transfers/{transferId}")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response getTransferById(@PathParam("transferId") String transferId) {
        Optional<Transfer> oTransfer = transferService.getTransferById(transferId);
        if (oTransfer.isPresent()) {
            return Response.ok(oTransfer.get(), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
    
    @POST
    @Path("/makeTransfer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response makeTransfer(Transfer transfer, @Context UriInfo uriInfo) {
        String transferId;
        try {
            transferId = transferService.makeTransfer(transfer.getSenderId(), transfer.getReceiverId(), transfer.getAmount());
            LOGGER.log(Level.INFO, "Transfer ID: {}", transferId);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(TransferConstants.BASE_URI + "/api/v1/transfers/" + transferId);
            return Response.created(uriBuilder.build()).build();
            
        } catch (BusinessException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            ex.printStackTrace();
            return Response.status(Status.NOT_ACCEPTABLE).build();
        }        
    }
}
