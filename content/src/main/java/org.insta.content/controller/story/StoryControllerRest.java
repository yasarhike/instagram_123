package org.insta.content.controller.story;

import org.insta.content.dao.story.StoryServiceDAO;
import org.insta.content.dao.story.StoryServiceDAOImpl;
import org.insta.content.groups.StoryValidator;
import org.insta.content.model.story.Story;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/storys")
public final class StoryControllerRest {

    private static StoryControllerRest storyControllerRest;
    private final StoryServiceDAO storyServiceDAO;
    private final ObjectValidator<Story, StoryValidator> objectValidator;

    private StoryControllerRest() {
        storyServiceDAO = StoryServiceDAOImpl.getInstance();
        objectValidator = new ObjectValidator<>();
    }

    public static StoryControllerRest getInstance() {
        return storyControllerRest == null ? new StoryControllerRest() : storyControllerRest;
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addStory(final Story story) {
        final byte[] violations = objectValidator.validate(story, StoryValidator.class);
        return violations != null && violations.length > 0 ? objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(storyServiceDAO.addStory(story), violations);
    }

    @DELETE
    @Path("/remove/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeStory(@PathParam("storyId") final int id) {
        return objectValidator.manualResponse(storyServiceDAO.removeStory(id));
    }

    @GET
    @Path("/get/{storyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getStory(@PathParam("storyId") final int id) {
        final Story story = storyServiceDAO.getStory(id);
        return story != null ? objectValidator.objectResponse(story)
                : objectValidator.manualResponse(false);
    }
}
