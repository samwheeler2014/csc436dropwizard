package csc435hw06.resources;

import csc435hw06.core.Location;
import csc435hw06.jdbi.LocationDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.jdbi.v3.core.Jdbi;

//maps this resource to the url "/location". All methods will be accessible under this path.
@Path("/location")
//this resource both takes in and returns json
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationResource {

    /*
     * A Location resource. Defines the GET methods for the Location aspects of the project. Defines the
     * logic for the GET methods in this resource. Uses DAO interfaces to interact with database.
     */

    private LocationDAO locationdao;
    final Jdbi jdbi;

    public LocationResource(LocationDAO locationDAO, Jdbi j) {
        this.locationdao = locationDAO;
        jdbi = j;
    }

    /*
     * @param @PathParam("id") long  The id of a Location defined in the database, given in the url path
     * @return List<Location>        List of all Data in the database corresponding to the given Location
     * GET method taking in one path parameter. Uses the LocationDAO to get the Location from database that corresponds to
     * the given id and displays as json to the user.
     */
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Location fetch(@PathParam("id")long id) {
        return locationdao.findNameById(id);
    }

    /*
     * @return List<Location>  List of all Data in the database
     * GET method taking in no parameters. Uses the LocationDAO to get all Locations from the database and display as json to user.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Location> fetchAll() {
        return locationdao.findAll();
    }

}
