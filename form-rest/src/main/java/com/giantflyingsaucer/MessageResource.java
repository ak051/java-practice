package com.giantflyingsaucer;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/messages")
public class MessageResource {
 
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@FormParam("name") String name,
                                @FormParam("message") String message,
                                @FormParam("thelist") List<String> list) {
        if(name.trim().length() > 0 && message.trim().length() > 0 && !list.isEmpty()) {
            // Note 1: Normally you would persist the new message to a datastore
            // of some sort. I'm going to pretend I've done that and
            // use a unique id for it that obviously points to nothing in
            // this case.
            // Note 2: The way I'm returning the data should be more like the commented
            // out piece, I am being verbose for the sake of showing you how to
            // get the values and show that it was read.
            return Response.created(URI.create("/messages/" + String.valueOf(UUID.randomUUID()))).entity(
                    name+ ": " + message + " --> the items: " + list.get(0) + " - " + list.get(1)).build();
 
            // This is a more real world "return"
            //return Response.created(URI.create("/messages/" + String.valueOf(UUID.randomUUID()))).build();
        }
        return Response.status(Response.Status.PRECONDITION_FAILED).build();
    }
}
