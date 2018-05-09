package csc435hw06.jdbi;

import csc435hw06.core.Location;
import org.jdbi.v3.core.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {

    /*
     * Mapper class. Maps Data database query results to the Location class. Sets columns in each value to variables in the Location class.
     */

    public Location map(ResultSet resultSet, org.jdbi.v3.core.statement.StatementContext statementContext) throws SQLException {
        return new Location(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("latitude"), resultSet.getInt("longitude"));
    }
}
