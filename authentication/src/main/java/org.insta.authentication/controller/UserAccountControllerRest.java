package org.insta.authentication.controller;


import org.insta.authentication.groups.ValidationOrder;
import org.insta.authentication.model.User;
import org.insta.authentication.service.UserAccountService;
import org.insta.authentication.service.UserAccountServiceImplementation;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Managing user accounts provides REST endpoints to create, retrieve, update, and delete user profiles.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
@Path("/authentication")
public final class UserAccountControllerRest {

    private static UserAccountControllerRest userAccountControllerRest;
    private final UserAccountService userAccountServiceImplementation;
    private final ObjectValidator<User, ValidationOrder> objectValidator = new ObjectValidator<>();

    /**
     * Restrict object creation outside the class.
     */
    private UserAccountControllerRest() {
        userAccountServiceImplementation = UserAccountServiceImplementation.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountControllerRest class.
     * </p>
     *
     * @return Singleton instance of UseAccountControllerRest class.
     */
    public static UserAccountControllerRest getInstance() {
        return userAccountControllerRest == null
                ? new UserAccountControllerRest() : userAccountControllerRest;
    }

    /**
     * <p>
     * Create a new user profile.
     * </p>
     *
     * @param user The {@link User} object representing the user profile to be created.
     * @return The profile data as a byte array if the profile is successfully created, otherwise null.
     */
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public byte[] createUser(final User user) {
       return userAccountServiceImplementation.createProfile(user);
    }

    /**
     * <p>
     * Deletes the user profile associated with the given ID.
     * </p>
     *
     * @param id The unique identifier of the user profile to be deleted.
     * @return The profile data as a byte array if the profile is successfully deleted, otherwise null.
     */
    @DELETE
    @Path("/remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deleteProfile(@PathParam("id") final int id) {
        return userAccountServiceImplementation.deleteProfile(id);
    }

    /**
     * <p>
     * Updates the user profile with the provided information.
     * </p>
     *
     * @param user The {@link User} object containing updated profile information.
     * @return The validated user profile data as a byte array if the profile is successfully updated, otherwise null.
     */
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public byte[] updateDetails(final User user) {
        return userAccountServiceImplementation.updateProfile(user);
    }

    /**
     * <p>
     * Retrieves the user profile based on the provided ID.
     * </p>
     *
     * @param id The unique identifier of the user profile.
     * @return The profile data as a byte array if found, otherwise null.
     */
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public byte[] getProfile(@PathParam("id") final int id) {
        return userAccountServiceImplementation.getProfile(id);
    }
}
