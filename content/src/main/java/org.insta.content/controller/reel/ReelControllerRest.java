package org.insta.content.controller.reel;

import org.insta.content.model.reel.Reel;
import org.insta.content.service.reel.ReelService;
import org.insta.content.service.reel.ReelServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Implementation of the ReelControllerRest class for managing reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelService
 */
@Path("/reel")
public final class ReelControllerRest {

    private static ReelControllerRest reelControllerRest;
    private final ReelService reelService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelControllerRest() {
        reelService = ReelServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelControllerRest class.
     * </p>
     *
     * @return The singleton instance of ReelControllerRest class.
     */
    public static ReelControllerRest getInstance() {
        return reelControllerRest == null ? new ReelControllerRest() : reelControllerRest;
    }

    /**
     * <p>
     * Endpoint for removing a reel.
     * </p>
     *
     * @param reelId the ID of the reel to remove
     * @return the response in JSON format
     */
    @DELETE
    @Path("/remove/{reelid}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeReel(@PathParam("reelid") final Integer reelId) {
        return reelService.removeReel(reelId);
    }

    /**
     * <p>
     * Endpoint for adding a reel for the specified user.
     * </p>
     *
     * @param reel the Reel object to add
     * @return the response in JSON format
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addReel(final Reel reel) {
        return reelService.addReel(reel);
    }

    /**
     * <p>
     * Endpoint for retrieving a reel.
     * </p>
     *
     * @param reelId the ID of the reel to retrieve
     * @return the response in JSON format
     */
    @GET
    @Path("/get/{reelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getReel(@PathParam("reelId") final int reelId) {
        return reelService.getReel(reelId);
    }
}
