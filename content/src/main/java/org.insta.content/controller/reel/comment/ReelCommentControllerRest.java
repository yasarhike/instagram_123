package org.insta.content.controller.reel.comment;

import org.insta.content.model.Comment;
import org.insta.content.service.reel.comment.ReelCommentService;
import org.insta.content.service.reel.comment.ReelCommentServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * <p>
 * RESTful controller class for managing comments on reels within the Instagram application.
 * </p>
 *
 * <p>
 * This class provides endpoints for adding and removing comments on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelCommentServiceImpl
 */
@Path("/reelcomment")
public final class ReelCommentControllerRest {

    private static ReelCommentControllerRest reelCommentControllerRest;
    private final ReelCommentService reelCommentService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelCommentControllerRest() {
        reelCommentService = ReelCommentServiceImpl.getInstance();
    }

    /**
     * <p>
     * Creates a singleton instance of the ReelCommentControllerRest class if it is not already created.
     * </p>
     *
     * @return Singleton instance of ReelCommentControllerRest class.
     */
    public static ReelCommentControllerRest getInstance() {
        return reelCommentControllerRest == null ? new ReelCommentControllerRest() : reelCommentControllerRest;
    }

    /**
     * <p>
     * Endpoint for adding a comment to a reel.
     * </p>
     *
     * @param comment the comment object to be added
     * @return the response in JSON format
     */
    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] add(final Comment comment) {
        return reelCommentService.addComment(comment);
    }

    /**
     * <p>
     * Endpoint for removing a comment from a reel.
     * </p>
     *
     * @param commentId the ID of the comment to be removed
     * @return the response in JSON format
     */
    @Path("/remove/{commentId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deleteComment(@PathParam("commentId") final int commentId) {
        return reelCommentService.deleteComment(commentId);
    }
}
