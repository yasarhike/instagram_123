package org.insta.authentication.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.insta.authentication.groups.ValidationOrder;


public final class User {

    @Positive(message = "User id must be positive", groups = ValidationOrder.class)
    private int userId;

    @Pattern(regexp = "^[a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*$")
    @NotNull(message = "Name must not be null", groups = ValidationOrder.class)
    private String name;

    @Size(min = 2, max = 14)
    @NotNull(message = "Mobile number must not be null", groups = ValidationOrder.class)
    private String mobileNumber;

    @Email
    @NotNull(message = "Email must not be null", groups = ValidationOrder.class)
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", groups = ValidationOrder.class)
    @NotNull(message = "Password must not be null", groups = ValidationOrder.class)
    private String password;

    @Valid
    @NotNull(message = "Address must not be null", groups = ValidationOrder.class)
    private final Address address;

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

    @Override
    public String toString() {
        return String.join(" ","userName : ", this.name
        , "\nmobileNumber : " , this.mobileNumber
        , "\nemail : ", this.email
        , this.address.toString());
    }
}
