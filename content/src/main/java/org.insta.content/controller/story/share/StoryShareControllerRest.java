package org.insta.content.controller.story.share;

import org.insta.content.dao.story.share.StoryShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/storysshare")
public final class StoryShareControllerRest {

    private static StoryShareControllerRest storyShareControllerRest;
    private final StoryShareDAOImpl storyShareDAOImpl;
    private final ObjectValidator objectValidator;

    private StoryShareControllerRest() {
        storyShareDAOImpl = StoryShareDAOImpl.getInstance();
        objectValidator = new ObjectValidator();
    }


    public static StoryShareControllerRest getInstance() {
        return storyShareControllerRest == null ? new StoryShareControllerRest() : storyShareControllerRest;
    }

    @Path("/add/{userId}/{storyId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyShare(@PathParam("storyId") final int storyId,
                             @PathParam("userId") final int userId) {
        return objectValidator.forSuccessResponse(storyShareDAOImpl.addShare(storyId, userId), new byte[]{});
    }

    @DELETE
    @Path("/remove/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyUnShare(@PathParam("storyId") final int storyId) {
        return objectValidator.manualResponse(storyShareDAOImpl.removeShare(storyId));
    }
}
