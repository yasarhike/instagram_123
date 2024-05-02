package org.insta.content.controller.post.comment;

import org.insta.content.dao.post.comment.PostCommentDAOImpl;
import org.insta.content.groups.CommentValidator;
import org.insta.content.model.Comment;
import org.insta.content.model.groups.ReelsGroup;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.function.Consumer;

/**
 * <p>
 *     Manages Post comments.
 * </p>
 *
 * @see Comment
 * @see ReelsGroup
 * @see PostCommentDAOImpl
 * @see ObjectValidator
 * @see CommentValidator
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
@Path("/postscomment")
public final class PostCommentControllerRest {

    private static PostCommentControllerRest postCommentControllerRest;
    private final PostCommentDAOImpl postCommentDAOImpl;
    private final ObjectValidator<Comment, ReelsGroup> objectValidator;

    private PostCommentControllerRest() {
        postCommentDAOImpl = PostCommentDAOImpl.getInstance();
        objectValidator = new ObjectValidator<>();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentControllerRest class.
     *</p>
     *
     * @return The singleton instance of PostCommentControllerRest class.
     */
    public static PostCommentControllerRest getInstance() {
        return postCommentControllerRest == null ? new PostCommentControllerRest() : postCommentControllerRest;
    }

    /**
     * <p>
     * Adds a comment for the specified post.
     *</p>
     *
     * @param comment The comment to be added.
     * @return The response containing the result of the operation.
     */
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postComment( final Comment comment) {
        final byte[] violations = objectValidator.validate(comment, CommentValidator.class);

        return violations != null && violations.length > 0 ?
                objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(postCommentDAOImpl.postComment(comment), violations);
    }

    /**
     * <p>
     * Removes a comment with the specified ID.
     * </p>
     *
     * @param id The ID of the comment to be removed.
     * @return The response containing the result of the operation.
     */
    @Path("/remove/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] removeComment(@PathParam("id") final int id) {
        return objectValidator.manualResponse(postCommentDAOImpl.deleteComment(id));
    }
}
