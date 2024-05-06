package org.insta.content.controller.reel.share;

import org.insta.content.service.reel.share.ReelShareService;
import org.insta.content.service.reel.share.ReelShareServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * <p>
 * RESTful controller class for managing sharing operations for reels within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for sharing and removing shares on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelShareServiceImpl
 */
@Path("/reelshare")
public final class ReelShareControllerRest {

    private static ReelShareControllerRest reelShareControllerRest;
    private final ReelShareService reelShareService;

    /**
     * <p>
     * Restrict object creation outside of the class.
     * </p>
     */
    private ReelShareControllerRest() {
        reelShareService = ReelShareServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of the ReelShareControllerRest class.
     * </p>
     *
     * @return The singleton instance of the ReelShareControllerRest class.
     */
    public static ReelShareControllerRest getInstance() {
        return reelShareControllerRest == null ? new ReelShareControllerRest() : reelShareControllerRest;
    }

    /**
     * <p>
     * Endpoint for sharing a reel with a user.
     * </p>
     *
     * @param reelId the ID of the reel to share
     * @param userId the ID of the user to share with
     * @return the response in JSON format
     */
    @Path("/add/{reelId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelShare(@PathParam("reelId") final int userId,
                            @PathParam("userId") final int reelId) {
        return reelShareService.reelShare(userId, reelId);
    }

    /**
     * <p>
     * Endpoint for removing a shared reel.
     * </p>
     *
     * @param id the ID of the shared reel to remove
     * @return the response in JSON format
     */
    @Path("/remove/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeShare(@PathParam("id") final int id) {
        return reelShareService.removeShare(id);
    }
}
