package org.insta.content.controller.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reelsshare")
public final class ReelShareControllerRest {

    private static ReelShareControllerRest reelShareControllerRest;
    private final ReelShareDAO reelShareDAO;
    private final ObjectValidator objectValidator;

    private ReelShareControllerRest() {
        reelShareDAO = ReelShareDAO.getInstance();
        objectValidator = new ObjectValidator();
    }

    public static ReelShareControllerRest getInstance() {
        return reelShareControllerRest == null ? new ReelShareControllerRest() : reelShareControllerRest;
    }

    @Path("/add/{reelId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] reelShare(@PathParam("reelId") final int userId,
                            @PathParam("userId") final int reelId) {
        return objectValidator.forSuccessResponse(reelShareDAO.reelShare(userId, reelId), new byte[]{});
    }

    @Path("/remove/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeShare(@PathParam("id") final int id) {
        return objectValidator.manualResponse(reelShareDAO.removeShare(id));
    }
}
