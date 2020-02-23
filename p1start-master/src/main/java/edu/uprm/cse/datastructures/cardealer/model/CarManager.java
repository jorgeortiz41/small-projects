package edu.uprm.cse.datastructures.cardealer.model;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/cars")
public class CarManager {
	private final CircularSortedDoublyLinkedList<Car> carList = CarList.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)//returns all cars in car list
	public Car[] getAllCars() {
		Car[] allCars = new Car[carList.size()];
		for (int i = 0; i < carList.size(); i++) {
			allCars[i]= carList.get(i);
		}
		return allCars;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)//returns car given a specific id
	public Car getCar(@PathParam("id") long id) {
		for (int i = 0; i < carList.size(); i++) {
			if(carList.get(i).getCarId()==id) {
				return carList.get(i);
			}
		}
		throw new NotFoundException(new JsonError("Error", "Car" + id + " not found"));
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)//add a car to the car list
	public Response addCar(Car obj) {
		carList.add(obj);
		return Response.status(201).build();
	}
	
	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)//updates car information in the car list 
	public Response updateCar(Car obj) {
		for (int i = 0; i < carList.size(); i++) {
			if(carList.get(i).getCarId() == obj.getCarId()) {
				carList.remove(carList.get(i));
				carList.add(obj);
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("/{id}/delete")//deletes a car in the car list
	public Response deleteCar(@PathParam("id") long id) {
		for (int i = 0; i < carList.size(); i++) {
			if(carList.get(i).getCarId()==id) {
				carList.remove(carList.get(i));
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
