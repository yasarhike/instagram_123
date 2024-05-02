package org.insta.authentication.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Hold the location details of the user.
 * </p>
 *
 * @author Mohamed Yasar k
 * @version 1.0 6 Feb 2024
 */
public final class Country {

    public static final Map<String, Country> countries = new HashMap<>();

    static {
        loadCountry();
    }
    @NotNull(message = "Country name must not be null")
    private final String name;
    @NotNull(message = "States list must not be null")
    private final List<String> states;
    @NotNull(message = "Country code must not be null")
    private final String code;

    public Country(final String name, final List<String> states, final String regexCode) {
        this.name = name;
        this.states = states;
        this.code = regexCode;
    }

    private static void loadCountry() {
        countries.put("91", new Country("india", new ArrayList<>(List.of("tamilnadu", "kerala", "maharashtra")),
                "[6-9][0-9]{9}"));
        countries.put("61", new Country("australia", new ArrayList<>(List.of("orange", "sydney", "sunbury")),
                "[0-9]{8}"));
        countries.put("213", new Country("algeria", new ArrayList<>(List.of("oran", "mila", "saida")),
                "[0-9]{9}"));
        countries.put("india", countries.get("91"));
        countries.put("australia", countries.get("61"));
        countries.put("algeria", countries.get("213"));
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public List<String> getState() {
        return this.states;
    }
}
