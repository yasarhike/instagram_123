package org.insta.content.controller.post.like;

import org.insta.content.dao.post.like.PostLikeDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 *     Manages post likes.
 * </p>
 *
 * @see PostLikeDAOImpl
 * @see ObjectValidator
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
@Path("/postslike")
public final class PostLikeControllerRest {

    private static PostLikeControllerRest postLikeControllerRest;
    private final PostLikeDAOImpl postLikeDAOImpl;
    private final ObjectValidator objectValidator;

    private PostLikeControllerRest() {
        postLikeDAOImpl = PostLikeDAOImpl.getInstance();
        objectValidator = new ObjectValidator();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeControllerRest class.
     * </p>
     *
     * @return The singleton instance of PostLikeControllerRest class.
     */
    public static PostLikeControllerRest getInstance() {
        return postLikeControllerRest == null ? new PostLikeControllerRest() : postLikeControllerRest;
    }

    /**
     * <p>
     * Adds a like for the specified post by the specified user.
     * </p>
     *
     * @param userId The ID of the user who likes the post.
     * @param postId The ID of the post to be liked.
     * @return The response containing the result of the operation.
     */
    @Path("/add/{postId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postLike(@PathParam("userId") final int userId,
                           @PathParam("postId") final int postId) {
        return objectValidator.forSuccessResponse(postLikeDAOImpl.postLike(userId, postId), new byte[]{});
    }

    /**
     * <p>
     * Removes a like for the specified post by the specified user.
     * </p>
     *
     * @param userId The ID of the user who unlikes the post.
     * @param postId The ID of the post to be unliked.
     * @return The response containing the result of the operation.
     */
    @Path("/remove/{postId}/{userId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postUnlike(@PathParam("userId") final int userId,
                             @PathParam("postId") final int postId) {
        return objectValidator.manualResponse(postLikeDAOImpl.postUnlike(userId, postId));
    }
}
