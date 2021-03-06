package ie.gmit.sw.Jersey;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import ie.gmit.sw.Model.Bookings;
import ie.gmit.sw.rmi.DatabaseService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws Exception 
     * @throws RemoteException 
     * @throws MalformedURLException 
     */
	@GET
	@Path("/showOrder")
	@Produces(MediaType.APPLICATION_XML)
    public List<Bookings> getIt() throws MalformedURLException, RemoteException, Exception {
    	
    	DatabaseService ds;
    	ds = (DatabaseService) Naming.lookup("rmi://127.0.0.1:1099/database");

    	
        return ds.read();
    }
	
	// add order to the database
	@POST
	@Path("/addOrder")
	@Produces(MediaType.APPLICATION_XML)
	// all parameters
    public void getIt1(@FormParam("name") String name, @FormParam("start_date") String startdate, @FormParam("end_date") String enddate, @FormParam("carmake") String carmake, @FormParam("carreg") String carreg) throws MalformedURLException, RemoteException, Exception {
	
    	
    	DatabaseService ds;
    	ds = (DatabaseService) Naming.lookup("rmi://127.0.0.1:1099/database");

        ds.create(new Bookings(name, startdate, enddate, carmake, carreg));
    }
	
	// update similar to add
	@POST
	@Path("/UpdateOrder")
	//@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void UpdateOrder(@FormParam("name") String name, @FormParam("start_date") String startdate, @FormParam("end_date") String enddate, @FormParam("carmake") String carmake, @FormParam("carreg") String carreg) throws MalformedURLException, RemoteException, NotBoundException, SQLException {
		DatabaseService ds;
		ds = (DatabaseService) Naming.lookup("rmi://127.0.0.1:1099/database");
		ds.update(new Bookings(name, startdate, enddate, carmake, carreg));
	}


}
