package org.insta.content.controller.story.like;

import org.insta.content.service.story.like.StoryLikeService;
import org.insta.content.service.story.like.StoryLikeServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Implemented class for managing story like operations.
 * </p>
 *
 * @see StoryLikeService
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
@Path("/storylike")
public final class StoryLikeControllerRest {

    private static StoryLikeControllerRest storyLikeControllerRest;
    private final StoryLikeService storyLikeService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryLikeControllerRest() {
        storyLikeService = StoryLikeServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeControllerRest class.
     * </p>
     *
     * @return The singleton instance of StoryLikeControllerRest class.
     */
    public static StoryLikeControllerRest getInstance() {
        return storyLikeControllerRest == null ? new StoryLikeControllerRest() : storyLikeControllerRest;
    }

    /**
     * <p>
     * Endpoint for adding a like to a specific story.
     * </p>
     *
     * @param userId the ID of the user
     * @param storyId the ID of the story
     * @return the response in JSON format
     */
    @Path("/add/{userId}/{storyId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyLike(@PathParam("userId") final int userId,
                            @PathParam("storyId") final int storyId) {
        return storyLikeService.storyLike(userId, storyId);
    }

    /**
     * <p>
     * Endpoint for removing a like from a story.
     * </p>
     *
     * @param id the ID of the like to be removed
     * @return the response in JSON format
     */
    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyUnLike(@PathParam("id") final int id) {
       return storyLikeService.storyUnlike(id);
    }
}
