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
import edu.uprm.cse.datastructures.cardealer.util.HashTableOA;

@Path("/cars")
public class CarManager {
	private final HashTableOA<Long, Car> carTable = CarTable.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)//returns all cars in car list
	public Car[] getAllCars() {
		Car[] allCars = new Car[carTable.getValues().size()];
		for (int i = 0; i < allCars.length; i++) {
			allCars[i]= carTable.getValues().get(i);
		}
		return allCars;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)//returns car given a specific id
	public Car getCar(@PathParam("id") long id) {
		for (int i = 0; i < carTable.getValues().size(); i++) {
			if(carTable.getValues().get(i).getCarId()==id) {
				return carTable.getValues().get(i);
			}
		}
		throw new NotFoundException(new JsonError("Error", "Car" + id + " not found"));
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)//add a car to the car list
	public Response addCar(Car obj) {
		if(carTable.contains(obj.getCarId())) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		carTable.put(obj.getCarId(), obj);
		return Response.status(201).build();
	}
	
	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)//updates car information in the car list 
	public Response updateCar(Car obj) {
		for (int i = 0; i < carTable.size(); i++) {
			if(carTable.getValues().get(i).getCarId() == obj.getCarId()) {
				carTable.remove(carTable.getValues().get(i).getCarId());
				carTable.put(obj.getCarId(),obj);
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("/{id}/delete")//deletes a car in the car list
	public Response deleteCar(@PathParam("id") long id) {
		for (int i = 0; i < carTable.size(); i++) {
			if(carTable.getValues().get(i).getCarId()==id) {
				carTable.remove(carTable.getValues().get(i).getCarId());
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
