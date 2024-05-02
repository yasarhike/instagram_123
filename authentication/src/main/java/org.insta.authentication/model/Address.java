package org.insta.authentication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.insta.authentication.groups.ValidationOrder;

/**
 * <p>
 * Contains the address details of the user.
 * </p>
 *
 * @author Mohamed Yasar k
 * @version 1.0 6 Feb 2024
 */
public final class Address {

    @NotNull(message = "Country must not be null", groups = ValidationOrder.class)
    private String country;

    @Size(min = 2, max = 2, message = "Country code must be 2 characters", groups = ValidationOrder.class)
    private String countryCode;
    @NotBlank(message = "State must not be blank", groups = ValidationOrder.class)
    private String state;
    @Positive(message = "Door number must be positive", groups = ValidationOrder.class)
    private int doorNumber;
    @NotBlank(message = "Street name must not be blank", groups = ValidationOrder.class)
    @Size(max = 100, message = "Street name must be less than or equal to 100 characters", groups = ValidationOrder.class)
    private String streetName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(final int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(final String code) {
        this.countryCode = code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", state='" + state + '\'' +
                ", doorNumber=" + doorNumber +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}

