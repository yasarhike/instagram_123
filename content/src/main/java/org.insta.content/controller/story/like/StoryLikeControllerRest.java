package org.insta.content.controller.story.like;

import org.insta.content.dao.story.like.StoryLikeDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/storyslike")
public final class StoryLikeControllerRest {

    private static StoryLikeControllerRest storyLikeControllerRest;
    private final StoryLikeDAOImpl storyLikeDAOImpl;
    private final ObjectValidator objectValidator;

    private StoryLikeControllerRest() {
        storyLikeDAOImpl = StoryLikeDAOImpl.getInstance();
        objectValidator = new ObjectValidator();
    }

    public static StoryLikeControllerRest getInstance() {
        return storyLikeControllerRest == null ? new StoryLikeControllerRest() : storyLikeControllerRest;
    }

    @Path("/add/{userId}/{storyId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyLike(@PathParam("userId") final int userId,
                            @PathParam("storyId") final int storyId) {
        return objectValidator.forSuccessResponse(storyLikeDAOImpl.storyLike(userId, storyId), new byte[]{});
    }

    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyUnLike(@PathParam("id") final int id) {
        return objectValidator.manualResponse(storyLikeDAOImpl.storyUnlike(id));
    }
}
