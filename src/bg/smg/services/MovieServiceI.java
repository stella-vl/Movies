package bg.smg.services;

import bg.smg.model.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieServiceI {
    public List<Movie> getAll() throws SQLException;
}