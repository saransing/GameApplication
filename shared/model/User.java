package GameApp.shared.model;

import java.io.Serializable;

/**
 * A class represents a User object.
 *
 * @author Adrian Bugiel
 * @version 1.0
 */
public class User implements Serializable {

    private String username;
    private String email;
    private String country;
    private String address;
    private String password;
    private boolean isAdmin;

    /**
     * 6 arguments constructor.
     *
     * @param email    user's email address
     * @param country  user's country of origin
     * @param address  user's address of residence
     * @param username user's profile name
     * @param password user's password
     * @param isAdmin  true if user is an admin
     */
    public User(String email, String country, String address, String username,
                String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.country = country;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Gets user's username.
     *
     * @return String representation of a user's name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets user's email address.
     *
     * @return String representation of a user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user's email address.
     *
     * @param email user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Gets user's country of origin.
     *
     * @return String representation of a user's country of origin
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets user's country of origin.
     *
     * @param country user's country of origin
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets user's address of residence.
     *
     * @return String representation of a user's address of residence
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets user's address of residence.
     *
     * @param address user's address of residence
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Gets user's password.
     *
     * @return String representation of a user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user's password.
     *
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Sets true if user is an admin.
     *
     * @param admin if user is an admin
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Gets true if user is an admin.
     *
     * @return Boolean representation if user is admin or is not
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }
}
