package org.insta.content.controller.reel.like;

import org.insta.content.dao.reel.like.ReelLikeDAO;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reelslike")
public final class ReelLikeControllerRest {

    private static ReelLikeControllerRest reelLikeControllerRest;
    private final ReelLikeDAO reelLikeDAO;
    private final ObjectValidator objectValidator;

    private ReelLikeControllerRest() {
        reelLikeDAO = ReelLikeDAO.getInstance();
        objectValidator = new ObjectValidator();
    }

    public static ReelLikeControllerRest getInstance() {
        return reelLikeControllerRest == null ? new ReelLikeControllerRest() : reelLikeControllerRest;
    }

    @Path("/add/{reelId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelLike(@PathParam("reelId") final int reelId, @PathParam("userId") final int userId) {
        return objectValidator.forSuccessResponse(reelLikeDAO.reelLike(reelId, userId), new byte[]{});
    }

    @Path("/remove/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelUnlike(@PathParam("id") final int id) {
        return objectValidator.manualResponse(reelLikeDAO.reelUnlike(id));
    }
}
