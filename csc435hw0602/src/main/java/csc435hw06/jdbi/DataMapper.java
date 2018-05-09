package csc435hw06.jdbi;

import csc435hw06.core.Data;
import org.jdbi.v3.core.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapper implements RowMapper<Data> {

    /*
     * Mapper class. Maps Data database query results to the Data class. Sets columns in each value to variables in the Data class.
     */

    public Data map(ResultSet resultSet, org.jdbi.v3.core.statement.StatementContext statementContext) throws SQLException {
        return new Data(resultSet.getLong("id"), resultSet.getLong("location_id"), resultSet.getString("data_date"), resultSet.getFloat("prec"), resultSet.getFloat("pres"), resultSet.getString("value"));
    }
}
