package nl.hu.s3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User findUser(String username) throws SQLException {
        try(Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")){

            var statement = conn.prepareStatement("SELECT * FROM USERS where username = ?");
            statement.setString(1, username);
            User result = null;
            ResultSet resultset = statement.executeQuery();
            if(resultset.next()){
                result = new User(resultset.getString("username"), resultset.getString("password"));
            }
            return result;
        }
    }
}
