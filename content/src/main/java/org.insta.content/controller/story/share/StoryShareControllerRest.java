package org.insta.content.controller.story.share;

import org.insta.content.service.story.share.StoryShareService;
import org.insta.content.service.story.share.StoryShareServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * RESTful controller class for managing story sharing operations within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for sharing and unsharing stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareService
 */
@Path("/storyshare")
public final class StoryShareControllerRest {

    private static StoryShareControllerRest storyShareControllerRest;
    private final StoryShareService storyShareService;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private StoryShareControllerRest() {
        storyShareService = StoryShareServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of the StoryShareControllerRest class.
     * </p>
     *
     * @return The singleton instance of StoryShareControllerRest class.
     */
    public static StoryShareControllerRest getInstance() {
        return storyShareControllerRest == null ? new StoryShareControllerRest() : storyShareControllerRest;
    }

    /**
     * <p>
     * Shares a story with the specified user.
     * </p>
     *
     * @param storyId the ID of the story to share
     * @param userId  the ID of the user to share with
     * @return the ID of the shared story if successful, otherwise -1
     */
    @Path("/add/{userId}/{storyId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyShare(@PathParam("storyId") final int storyId,
                             @PathParam("userId") final int userId) {
        return storyShareService.storyShare(storyId, userId);
    }

    /**
     * <p>
     * Unshares a story.
     * </p>
     *
     * @param storyId the ID of the story to unshare
     * @return A byte contains the response of the request.
     */
    @DELETE
    @Path("/remove/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] storyUnShare(@PathParam("storyId") final int storyId) {
        return storyShareService.storyUnShare(storyId);
    }
}
