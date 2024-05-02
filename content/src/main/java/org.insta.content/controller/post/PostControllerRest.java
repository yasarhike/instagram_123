package org.insta.content.controller.post;

import org.insta.content.model.post.Post;
import org.insta.content.service.post.PostService;
import org.insta.content.service.post.PostServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Manages posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
@Path("/posts")
public final class PostControllerRest {

    private static PostControllerRest postControllerRest;
    private final PostService postService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostControllerRest() {
        postService = PostServiceImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostControllerRest class.
     * </p>
     *
     * @return The singleton instance of PostControllerRest class.
     */
    public static PostControllerRest getInstance() {
        return postControllerRest == null ? new PostControllerRest() : postControllerRest;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post Refer to the {@link Post} of the user.
     * @return True if the post is added successfully, otherwise false.
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] addPost(final Post post) {
        return postService.addPost(post);
    }

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to postId of the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deletePost(@PathParam("id") final int postId) {
        return postService.removePost(postId);
    }

    /**
     * <p>
     * Updates the post with the specified ID.
     * </P>
     *
     * @param post Refer to {@link Post} of the user.
     * @return True if the post is updated successfully, otherwise false.
     */
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public byte[] updatePost(final Post post) {
        return postService.updatePost(post);
    }

    /**
     * <p>
     * Retrieve the post with the specified ID.
     * </P>
     *
     * @param postId refers to the postId of the user.
     * @return True if the post is updated successfully, otherwise false.
     */
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public byte[] getPost(@PathParam("id") final int postId) {
       return postService.getPost(postId);
    }
}
