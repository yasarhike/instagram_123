package org.insta.content.controller.reel.like;

import org.insta.content.service.reel.like.ReelLikeServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * RESTful controller class for managing likes on reels within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for adding and removing likes on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelLikeServiceImpl
 */
@Path("/reellike")
public final class ReelLikeControllerRest {

    private static ReelLikeControllerRest reelLikeControllerRest;
    private final ReelLikeServiceImpl reelLikeService;

    /**
     * <p>
     * Restrict object creation outside of the class.
     * </p>
     */
    private ReelLikeControllerRest() {
        reelLikeService = ReelLikeServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of the ReelLikeControllerRest class.
     * </p>
     *
     * @return The singleton instance of the ReelLikeControllerRest class.
     */
    public static ReelLikeControllerRest getInstance() {
        return reelLikeControllerRest == null ? new ReelLikeControllerRest() : reelLikeControllerRest;
    }

    /**
     * <p>
     * Endpoint for adding a like to a reel.
     * </p>
     *
     * @param reelId the ID of the reel
     * @param userId the ID of the user
     * @return the response in JSON format
     */
    @Path("/add/{reelId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelLike(@PathParam("reelId") final int reelId, @PathParam("userId") final int userId) {
        return reelLikeService.reelLike(reelId, userId);
    }

    /**
     * <p>
     * Endpoint for removing a like from a reel.
     * </p>
     *
     * @param id the ID of the like to be removed
     * @return the response in JSON format
     */
    @Path("/remove/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelUnlike(@PathParam("id") final int id) {
        return reelLikeService.reelUnlike(id);
    }
}
