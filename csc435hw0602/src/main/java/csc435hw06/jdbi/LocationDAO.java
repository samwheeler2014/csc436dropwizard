package csc435hw06.jdbi;


import csc435hw06.core.Location;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;


import java.util.List;

/*
 * Location Data Access Object. This class defines two queries to the Location table from the MySQL database.
 * Both queries are select queries for get methods. These queries will be used in LocationResource. For both
 * queries, the entire row(s) is/are selected, so a rowmapper is registered to both queries. LocationMapper
 * is the rowmapper used; LocationMapper will map an entire row from the Location table to a Location object.
 */

public interface LocationDAO {

    /*
     * @params long      the id of a row in the Location table
     * @return Location  the Location with that id
     * Performs a select query on the Location table of the database. Used to get a specific Location.
     */
    @SqlQuery("Select * from Location where id = :id;")
    //registers the LocationMapper to this DAO. LocationMapper will map the result of the select query to a Location object
    @RegisterRowMapper(LocationMapper.class)
    Location findNameById(@Bind("id") long id);

    /*
     * @return List<Location>  a list of all Locations
     * Performs a select query on the Location table of the database. Used to show the user all Locations in the database.
     */
    @SqlQuery("Select * from Location;")
    //registers the LocationMapper to this DAO. LocationMapper will map the result of the select query to a Location object
    @RegisterRowMapper(LocationMapper.class)
    List<Location> findAll();

}