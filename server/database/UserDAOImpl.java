package GameApp.server.database;

import GameApp.shared.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class responsible for the user table communication in the database.
 *
 * @author Kevin Kluka, Adrian Bugiel, Andreea Asimine
 * @version 1.0
 */
public class UserDAOImpl implements UserDAO {

    /**
     * 0 arguments constructor.
     */
    public UserDAOImpl() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection details to a database.
     *
     * @return Connection  to a database
     * @throws SQLException if a connection to a database fails
     */
    private Connection getConnection() throws SQLException {
        return ConnectDatabase.getConnection();
    }

    /**
     * Adds user to a database.
     *
     * @param user added to the database
     */
    @Override
    public User create(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO \"user\"(email, country, address, user_name, \"password\", isadmin) VALUES (?,?,?,?,?,?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getCountry());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setBoolean(6, user.getIsAdmin());

            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all the user's usernames.
     *
     * @return List of String objects
     */
    @Override
    public List<User> readByUsername(String username) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE user_name LIKE ?");
            statement.setString(1, "%" + username + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> result = new ArrayList<>();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                String name = resultSet.getString("user_name");
                String address = resultSet.getString("address");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("isAdmin");

                User user = new User(email, country, address, name, password, isAdmin);
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets user from database by an email.
     *
     * @param email user's email
     * @return User object
     */
    @Override
    public User findUserByEmail(String email) {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE email = ?");

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String country = resultSet.getString("country");
                String address = resultSet.getString("address");
                String username = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("isadmin");

                return new User(email, country, address, username, password, isAdmin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Gets all the user's usernames.
     *
     * @return List of String objects
     */
    @Override
    public List<String> getAllUsernames() {

        ArrayList<String> usernames = new ArrayList<>();
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT user_name from \"user\"");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("user_name");
                usernames.add(username);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    /**
     * Updates user's information in the database.
     *
     * @param user User object
     */
    @Override
    public void update(User user) {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE \"user\" SET country = ?, address = ?, user_name = ?, \"password\" = ?, isadmin = ? WHERE email = ?");

            statement.setString(1, user.getCountry());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.getIsAdmin());
            statement.setString(6, user.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bans user from the system.
     *
     * @param user User object
     */
    @Override
    public void delete(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE \"user\" SET country = 'USER_BANNED', address = 'USER_BANNED', user_name = 'USER_BANNED', \"password\" = null, isadmin = false WHERE email = ?");

            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * True if email is present in the database with the correct password.
     *
     * @param email    user's email
     * @param password user's password
     * @return Boolean representation of user's email with password in the database
     */
    @Override
    public boolean loginCon(String email, String password) {

        try (Connection connection = getConnection()) {
            String SQL = "SELECT * FROM \"user\" WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (password.equals(resultSet.getString("password"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets a user which is currently logged in.
     *
     * @param email    user's email
     * @param password user's password
     * @return User object
     */
    @Override
    public User getLoggedUser(String email, String password) {
        User loggedUser = null;
        if (loginCon(email, password)) {
            loggedUser = findUserByEmail(email);
        }
        return loggedUser;
    }


    /**
     * Gets all the registered users from the database.
     *
     * @return List of User objects
     */
    @Override
    public List<User> getAllUsers() {
        {
            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT email, user_name, address, country FROM  \"user\" WHERE isadmin = false AND user_name != 'USER_BANNED'");
                List<User> users = new ArrayList<>();
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String username = resultSet.getString("user_name");
                    String address = resultSet.getString("address");
                    String country = resultSet.getString("country");

                    User user = new User(email, country, address, username, "", false);
                    users.add(user);
                }
                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
