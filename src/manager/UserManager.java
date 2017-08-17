package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManager {


    private DBConnectionProvider provider;
    private Connection connection;
    private List<User> userList;

    public UserManager() {
        provider = DBConnectionProvider.getInstance();
        connection = provider.getConnection();
        userList = new ArrayList<>();
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user(`name`," +
                "`surname`,`age`,`email`, `password`)" +
                " VALUES('" + user.getName() + "','"
                + user.getSurname() + "'," + user.getAge() + ",'"
                + user.getEmail() + "','" +
                user.getPassword() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

    }


    public List<User> getUserList() throws SQLException {
        String query = "SELECT * FROM user;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            int age = resultSet.getInt(4);
            String email = resultSet.getString(5);
            String password = resultSet.getString(6);

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setAge(age);
            user.setEmail(email);
            user.setPassword(password);
            userList.add(user);

        }

        return userList;
    }


    public void updateUserData(int id, int age) throws SQLException {

        String query = "UPDATE user SET `age` = " + "'" + age + "'" + "WHERE `id` = " + "'" + id + "'";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(query);
        if (i > 0)
            System.out.println("Successful update. Number of updated data : " + i);
        else
            System.out.println("Not successful update. User with " + id + " id not found.");


    }

    public void deleteUserById(int id) throws SQLException {
        String query = "DELETE FROM `user` WHERE `id` = " + "'" + id + "'" ;
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(query);
        if (i > 0)
        System.out.println("Deleted data: " + i);
        else
            System.out.println("No user with " + id + " id found.");


    }
}





