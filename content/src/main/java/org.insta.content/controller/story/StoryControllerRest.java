package org.insta.content.controller.story;

import org.insta.content.model.story.Story;
import org.insta.content.service.story.StoryService;
import org.insta.content.service.story.StoryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * RESTful controller class for managing story operations within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for adding, removing, and retrieving stories.
 * </p>
 *
 * @author [Author Name]
 * @version 1.0 [Date]
 * @see StoryService
 */
@Path("/story")
public final class StoryControllerRest {

    private static StoryControllerRest storyControllerRest;
    private final StoryService storyService;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private StoryControllerRest() {
        storyService = StoryServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of the StoryControllerRest class.
     * </p>
     *
     * @return The singleton instance of StoryControllerRest class.
     */
    public static StoryControllerRest getInstance() {
        return storyControllerRest == null ? new StoryControllerRest() : storyControllerRest;
    }

    /**
     * <p>
     * Endpoint for adding a story.
     * </p>
     *
     * @param story the Story object to add
     * @return the response in JSON format
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addStory(final Story story) {
        return storyService.addStory(story);
    }

    /**
     * <p>
     * Endpoint for removing a story.
     * </p>
     *
     * @param id the ID of the story to remove
     * @return the response in JSON format
     */
    @DELETE
    @Path("/remove/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeStory(@PathParam("storyId") final int id) {
        return storyService.removeStory(id);
    }

    /**
     * <p>
     * Endpoint for retrieving a story.
     * </p>
     *
     * @param id the ID of the story to retrieve
     * @return the response in JSON format
     */
    @GET
    @Path("/get/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getStory(@PathParam("storyId") final int id) {
        return storyService.getStory(id);
    }
}
