package bg.smg.services;

import bg.smg.model.MovieExec;
import bg.smg.model.Studio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudioService implements StudioServiceI {

    @Override
    public Studio getByName(String name) {
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM studio WHERE cert=?")) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.first();
                MovieExec movieExec = new MovieExec();
                movieExec.setCert(resultSet.getInt("cert"));
                movieExec.setName(resultSet.getString("name"));
                movieExec.setAddress(resultSet.getString("address"));
                movieExec.setNetworth(resultSet.getLong("networth"));
                return movieExec;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Connection valid: " + connection.isValid(5));
            }
        }
        return null;
    }
}