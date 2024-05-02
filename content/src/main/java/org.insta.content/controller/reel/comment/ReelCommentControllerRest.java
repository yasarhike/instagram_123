package org.insta.content.controller.reel.comment;

import org.insta.content.dao.reel.comment.ReelCommentDAO;
import org.insta.content.groups.CommentValidator;
import org.insta.content.model.Comment;
import org.insta.content.model.groups.ReelsGroup;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/reelscomment")
public final class ReelCommentControllerRest {

    private static ReelCommentControllerRest reelCommentControllerRest;
    private final ReelCommentDAO reelCommentDAO;
    private final ObjectValidator<Comment, ReelsGroup> objectValidator;

    private ReelCommentControllerRest() {
        reelCommentDAO = ReelCommentDAO.getInstance();
        objectValidator = new ObjectValidator<>();
    }

    public static ReelCommentControllerRest getInstance() {
        return reelCommentControllerRest == null ? new ReelCommentControllerRest() : reelCommentControllerRest;
    }
    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] add(final Comment comment) {
        final byte[] violations = objectValidator.validate(comment, CommentValidator.class);

        return violations != null && violations.length > 0 ?
                objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(reelCommentDAO.addComment(comment), violations);
    }

    @Path("/remove/{commentId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deleteComment(@PathParam("commentId") final int commentId) {
        return objectValidator.manualResponse(reelCommentDAO.deleteComment(commentId));
    }
}
