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

public class MovieService implements MovieServiceI{
    private DataSource dataSource;
    private Connection connection;
    private MovieExecServiceI movieExecService;
    private StudioServiceI studioService;

    public MovieService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
        movieExecService = new MovieExecService();
        studioService = new StudioService();
    }


    @Override
    public List<Movie> getAll() throws SQLException {
        try {
            List<Movie> movies = new ArrayList<>();
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM movie")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setTitle(resultSet.getString("title"));
                    movie.setYear(resultSet.getInt("year"));
                    movie.setLength(resultSet.getInt("length"));
                    movie.setInColor(resultSet.getString("incolor").charAt(0));

//                    String studioName = resultSet.getString("studioname");
//                    Studio studio = studioService.getByName(studioName);
//                    movie.setStudio(studio);

                    int producerId = resultSet.getInt("producerc");
                    //System.out.println(producerId);
                    movie.setProducer(movieExecService.getById(producerId));

                    movies.add(movie);
                }
                return movies;
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