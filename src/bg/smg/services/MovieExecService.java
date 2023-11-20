package bg.smg.services;

import bg.smg.model.Movie;
import bg.smg.model.MovieExec;
import bg.smg.model.Studio;
import bg.smg.util.DBManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieExecService implements MovieExecServiceI {

    private DataSource dataSource;
    private Connection connection;

    MovieExecService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
    }

    @Override
    public MovieExec getById(int id)  throws SQLException {
        try {
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM movieexec WHERE cert=?")) {
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