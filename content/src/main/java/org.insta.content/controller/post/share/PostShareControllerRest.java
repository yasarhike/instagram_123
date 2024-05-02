package org.insta.content.controller.post.share;

import org.insta.content.dao.post.share.PostShareDAOImpl;
import org.insta.content.model.common.IdSetter;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 *     Manages post share.
 * </p>
 *
 * @see PostShareDAOImpl
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
@Path("/postsshare")
public class PostShareControllerRest {

    private static PostShareControllerRest postShareControllerRest;
    private final PostShareDAOImpl postShareDAOImpl;
    private final ObjectValidator objectValidator;
    private final IdSetter idSetter;

    private PostShareControllerRest() {
        postShareDAOImpl = PostShareDAOImpl.getInstance();
        objectValidator = new ObjectValidator();
        idSetter = IdSetter.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareControllerRest class.
     * </p>
     *
     * @return The singleton instance of PostShareControllerRest class.
     */
    public static PostShareControllerRest getInstance() {
        return postShareControllerRest == null ? new PostShareControllerRest() : postShareControllerRest;
    }

    /**
     * <p>
     * Shares the specified post by the specified user.
     * </p>
     *
     * @param userId The ID of the user who shares the post.
     * @param postId The ID of the post to be shared.
     * @return The ID of the shared post.
     */
    @Path("/add/{postId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postShare(@PathParam("userId") final int userId,
                            @PathParam("postId") final int postId) {
        return objectValidator.forSuccessResponse(postShareDAOImpl.postShare(userId, postId), new byte[]{});
    }

    /**
     * <p>
     * Removes the share with the specified ID.
     *</p>
     *
     * @param shareId The ID of the share to be removed.
     * @return True if the share is removed successfully, otherwise false.
     */
    @Path("/remove/{shareId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] postUnShare(@PathParam("shareId") final int shareId) {
        return objectValidator.manualResponse(postShareDAOImpl.removeShare(shareId));
    }
}
