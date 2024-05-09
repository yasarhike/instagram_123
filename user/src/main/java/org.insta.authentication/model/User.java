package org.insta.authentication.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.insta.authentication.groups.UserCredentialsValidator;

/**
 * <p>
 * Contains the user details.
 * </p>
 *
 * @author Mohamed Yasar k
 * @version 1.0 6 Feb 2024
 * @see Address
 */
public final class User {

    @Valid
    @NotNull(message = "Address must not be null", groups = UserCredentialsValidator.class)
    private final Address address;
    @PositiveOrZero(message = "User id must be positive", groups = UserCredentialsValidator.class)
    private int userId;
    @Pattern(regexp = "^[a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*$")
    @NotNull(message = "Name must not be null", groups = UserCredentialsValidator.class)
    private String name;
    @Size(min = 2, max = 14)
    @NotNull(message = "Mobile number must not be null", groups = UserCredentialsValidator.class)
    private String mobileNumber;
    @Email
    @NotNull(message = "Email must not be null", groups = UserCredentialsValidator.class)
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", groups = UserCredentialsValidator.class)
    @NotNull(message = "Password must not be null", groups = UserCredentialsValidator.class)
    private String password;

    public User() {
        this.address = new Address();
    }

    public Address getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
}
