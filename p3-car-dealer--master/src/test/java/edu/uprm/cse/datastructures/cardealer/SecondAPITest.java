package edu.uprm.cse.datastructures.cardealer;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarTable;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SecondAPITest {

    private HttpServer server;
    private WebTarget target;
    private static final String BASE_URI = "http://localhost:8080/cardealer/";

    @Before
    public void setUp() throws Exception {
        // start the server
        server = startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    private HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.uprm.cse.datastructures.cardealer package
        final ResourceConfig rc = new ResourceConfig().packages("edu.uprm.cse.datastructures.cardealer");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    private Car[] getCars() {
        return target.path("cars").request().get(Car[].class);
    }

    private Response updateCar(Car car) {
        return target.path("cars/" + car.getCarId() + "/update").request(MediaType.APPLICATION_JSON).put(Entity.entity(car, MediaType.APPLICATION_JSON));
    }

    private Car getCar(Car car) {
        return target.path("cars/"+ car.getCarId()).request().get(Car.class);
    }

    private Response addCar(Car car1) {
        return target.path("cars/add").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(car1, MediaType.APPLICATION_JSON));
    }

    private Response getGetCarResponse(Car car) {
        return target.path("cars/" + car.getCarId()).request(MediaType.APPLICATION_JSON)
                .get();
    }

    private Response deleteCar(Car car) {
        return target.path("cars/" + car.getCarId() + "/delete").request(MediaType.APPLICATION_JSON)
                .delete();
    }
    
    @Test
    public void SaturateAttempt() {
    	 CarTable.resetCars();
    	 Car car1 = new Car(1,"Toyota", "RAV4", "LE",15000.50);
    	 Car car2 = new Car(2,"Toyota", "RAV4", "SLE",15000.50);
    	 Car car3 = new Car(3,"Toyota", "RAV4", "XLE",15000.50);
    	 Car car4 = new Car(4,"Audi", "RAV4", "LE",15000.50);
    	 Car car5 = new Car(5,"BMW", "RAV4", "LE",15000.50);
    	 Car car6 = new Car(6,"Bentley", "RAV4", "LE",15000.50);
    	 Car car7 = new Car(7,"Ferrari", "RAV4", "LE",15000.50);
    	 Car car8 = new Car(8,"Ford", "RAV4", "LE",15000.50);
    	 Car car9 = new Car(9,"Ram", "RAV4", "LE",15000.50);
    	 Car car10 = new Car(10,"Buggati", "RAV4", "LE",15000.50);
    	 Car car11 = new Car(11,"Suzuki", "RAV4", "LE",15000.50);
    	 Car car12 = new Car(12,"Subaru", "RAV4", "LE",15000.50);
    	 
    	 
    	 
    	 addCar(car1);
    	 addCar(car2);
    	 addCar(car3);
    	 addCar(car4);
    	 addCar(car5);
    	 addCar(car6);
    	 addCar(car7);
    	 addCar(car8);
    	 addCar(car9);
    	 addCar(car10);
    	 addCar(car11);
    	 addCar(car12);
    	 
    	 
    	 Car[] cars = getCars();

         // Check cars are in order
         assertArrayEquals("CarTable not in order", new Car[]{car4, car6, car5, car10, car7, car8, car9, car12, car11, car1,car2,car3}, cars);
         CarTable.resetCars();
    	 
    	 
    }
}