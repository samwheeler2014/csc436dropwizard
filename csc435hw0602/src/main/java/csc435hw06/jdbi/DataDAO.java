package csc435hw06.jdbi;

import csc435hw06.core.Data;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


import java.util.List;

public interface DataDAO {

    /*
     * Data Data Access Object. This class defines five queries to the Data table from the MySQL database.
     * There are three select queries, an insert query, and a delete query. These queries will be used in LocationResource.
     * For most queries, the entire row(s) is/are selected, so a rowmapper is registered to both queries. DataMapper
     * is the rowmapper used; DataMapper will map an entire row from the Data table to a Data object.
     */


    /*
     * @return List<Data>  list of all Data
     * Performs a select query on the Data table of the database. Used to get all Data in the database.
     */
    @SqlQuery("select * from Data;")
    //registers the DataMapper to this DAO. DataMapper will map the result of the select query to a Data object
    @RegisterRowMapper(DataMapper.class)
    List<Data> findAll();

    /*
     * @params long        the id of a row in the Location table
     * @return List<Data>  list of all Data with that location_id
     * Performs a select query on the Data table of the database. Used to get all Data for a given Location.
     * Each row of the Data table has the id of its corresponding Location as a foreign key.
     */
    @SqlQuery("select * from Data where location_id = :id;")
    //registers the DataMapper to this DAO. DataMapper will map the result of the select query to a Data object
    @RegisterRowMapper(DataMapper.class)
    List<Data> findByLocId(@Bind("id") long id);

    /*
     * @params long     the id of a new Data object
     *         long     the id of the corresponding Location
     *         String   the date and time returned by the API
     *         float    the precision returned by the API
     *         float    the pressure returned by the API
     *         String   the value returned by the API
     * Performs an insert query on the Data table using the given parameters. Accesses an external API for all
     *  parameters except for id and location_id.
     */
    @SqlUpdate("insert into Data values (?, ?, ?, ?, ?, ?);")
    void insert(long id, long location_id, String data_date, float precision, float pressure, String value);

    /*
     * @params long   the id of a Location
     * Performs a delete query on the database. Deletes all rows of the Data table corresponding to the given Location.
     */
    @SqlUpdate("delete from Data where location_id = :id;")
    //registers the DataMapper to this DAO. DataMapper will map the result of the select query to a Data object
    @RegisterRowMapper(DataMapper.class)
    void deleteByLocId(@Bind("id") long id);

    /*
     * @return long  the id of the last row in the Data table.
     * Gets the id of the last row in the Data table. Used to calculate ids for the insert query.
     */
    @SqlQuery("Select id from Data order by id DESC limit 1;")
    long findLastId();



}
