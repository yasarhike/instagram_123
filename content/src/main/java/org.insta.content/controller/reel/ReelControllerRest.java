package org.insta.content.controller.reel;

import org.insta.content.dao.reel.ReelServiceDAOImpl;
import org.insta.content.groups.ReelValidator;
import org.insta.content.model.reel.Reel;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reels")
public final class ReelControllerRest {

    private static ReelControllerRest reelControllerRest;
    private final ReelServiceDAOImpl reelServiceDAO;
    private final ObjectValidator<Reel, ReelValidator> objectValidator;

    private ReelControllerRest() {
        reelServiceDAO = ReelServiceDAOImpl.getInstance();
        objectValidator = new ObjectValidator<>();
    }

    public static ReelControllerRest getInstance() {
        return reelControllerRest == null ? new ReelControllerRest() : reelControllerRest;
    }

    @DELETE
    @Path("/remove/{reelid}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeReel(@PathParam("reelid") final Integer reelId) {
        return objectValidator.manualResponse(reelServiceDAO.removeReel(reelId));
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addReel(final Reel reel) {
        final byte[] violations = objectValidator.validate(reel, ReelValidator.class);
        return violations != null && violations.length > 0 ? objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(reelServiceDAO.addReel(reel), violations);
    }

    @GET
    @Path("/get/{reelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getReel(@PathParam("reelId") final int reelId) {
        final Reel reel = reelServiceDAO.getReel(reelId);
        return reel != null ? objectValidator.objectResponse(reel)
                : objectValidator.manualResponse(false);
    }
}
