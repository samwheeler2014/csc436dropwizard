package csc435hw06.resources;

import csc435hw06.core.*;
import csc435hw06.jdbi.DataDAO;
import csc435hw06.jdbi.LocationDAO;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

//maps this resource to the url "/data". All methods will be accessible under this path.
@Path("/data")
//this resource both takes in and returns json
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataResource {

    /*
     * A Data resource. Defines the GET, POST, and DELETE methods for the Data aspects of the project. Defines the
     * logic for the GET, POST, and DELETE methods in this resource. Uses DAO interfaces to interact with database.
     */

    private DataDAO datadao;
    //used for insert query
    private LocationDAO locationdao;
    final Jdbi jdbi;

    public DataResource (DataDAO d, LocationDAO l, Jdbi j) {
        datadao = d;
        locationdao = l;
        jdbi = j;
    }

    /*
     * @return List<Data>  List of all Data in the database
     * GET method taking in no parameters. Uses the DataDAO to get all Data from the database and display as json to user.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Data> fetchAll() {
        return datadao.findAll();
    }

    /*
     * @param @PathParam("id") long  The id of a Location defined in the database, given in the url path
     * @return List<Data>            List of all Data in the database corresponding to the given Location
     * GET method taking in one path parameter. Uses the DataDAO to get all Data from the database that corresponds to
     * the given Location and displays as json to the user.
     */
    @GET
    //maps query to "/data/{id}", user must supply id as a number (long)
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Data> fetch(@PathParam("id")long id) {
        return datadao.findByLocId(id);
    }

    /*
     * @param @PathParam("id") long       The id of a Location defined in the database, given in the url path
     *        @PathParam("date") String   A year given at the end of the path, given as "/data/{id}?date={date}"
     * @return List<Data>                 List of all Data in the database corresponding to the given Location
     * POST method taking in one path parameter and one query parameter. Accesses external API to get data for given
     * year and Location. Uses LocationDAO and DataDAO to add all found data to the Data table of the database.
     * Uses the DataDAO to get all Data from the database that corresponds to the given Location and displas as
     * json to the user.
     */
    @GET
    //maps query to "/data/{id}/{date}", user must supply id as a number (long) and date in YYYY format
    @Path("/{id}/{date}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Data> update(@PathParam("id")long id, @PathParam("date") String date) throws IOException {
        //get the id of the last row in the Data table -- used to calculate id of new Data
        long i = datadao.findLastId();
        //increment the id -- cannot have two of the same id
        i++;
        //get the Location based on the location_id given
        Location l = locationdao.findNameById(id);
        //use location and date to get json from api -- done in JsonToWeather for resource neatness
        JsonToWeather jtw = new JsonToWeather();
        //map the JSON result from the url into a Weather object for parsing and for database use.
        Weather w = jtw.toWeather(l, date);
        //will get multiple data for the built url, iterate through all and insert into database
        for (Readings r : w.getData()) {
            //increment the id -- cannot have two of the same id
            i++;
            //use the datadao to insert into the table
            datadao.insert(i, l.getId(), w.getTime(), r.getPrecision(), r.getPressure(), r.getValue());
        }
        //confirm to user that data based on url input was successfully added to database
        return datadao.findByLocId(id);
    }

    /*
     * @param @PathParam("id") long   the id of a Location, given in the url path
     * @return List<Data>             List of all Data in the Data table of the database
     * DELETE method taking in one path parameter. Deletes from the table all data corresponding to the given Location.
     */
    @DELETE
    //maps query to "/data/{id}", user must supply id as a number (long)
    @Path("/{id}")
    public List<Data> delete(@PathParam("id")long id) {
        datadao.deleteByLocId(id);
        //confirm to user that data based on user input was successfully deleted from database
        return datadao.findAll();
    }

}
