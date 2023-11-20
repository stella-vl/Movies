package bg.smg.services;

import bg.smg.model.MovieExec;

import java.sql.SQLException;

public interface MovieExecServiceI {
    public MovieExec getById(int id) throws SQLException;
}